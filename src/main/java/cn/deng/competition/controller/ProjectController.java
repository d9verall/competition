package cn.deng.competition.controller;

import cn.deng.competition.model.annotation.HasAdmin;
import cn.deng.competition.model.annotation.HasAdminOrJudge;
import cn.deng.competition.model.annotation.HasJudge;
import cn.deng.competition.model.annotation.HasStudent;
import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.entity.Project;
import cn.deng.competition.model.entity.ProjectJudge;
import cn.deng.competition.model.entity.ProjectStudent;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.ProjectJudgeService;
import cn.deng.competition.service.ProjectService;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import cn.deng.competition.service.ProjectStudentService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static cn.deng.competition.util.SecurityUtil.currentUser;

/**
 * @author verall
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
@PreAuthorize("isAuthenticated()")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectStudentService projectStudentService;
    private final ProjectJudgeService projectJudgeService;

    /**
     * 创建项目
     *
     * @return 页面
     */
    @HasAdminOrJudge
    @GetMapping("/create")
    public String create() {
        return "project-create";
    }

    /**
     * 项目列表
     *
     * @return 页面
     */
    @GetMapping("/list")
    public String list() {
        return "project-list";
    }

    /**
     * 表格数据
     *
     * @return 数据
     */
    @ResponseBody
    @GetMapping("/data")
    public List<Project> data() {
        return projectService.findAll();
    }

    /**
     * 创建
     *
     * @param project 项目
     * @return 回到刘表
     */
    @HasAdminOrJudge
    @PostMapping("/create")
    public String create(Project project) {
        projectService.save(project);
        return "project-list";
    }

    /**
     * 是否开启报名
     *
     * @param ids    项目编号
     * @param result 是否开启
     * @return 列表页面
     */
    @HasAdmin
    @GetMapping("/apply")
    public String apply(@RequestParam List<Long> ids, @RequestParam Boolean result) {
        projectService.apply(ids, result);
        return "project-list";
    }

    /**
     * 审批是否通过
     *
     * @param ids      项目 id
     * @param judgeIds 评委 id
     * @param result   审批结果
     * @param reason   失败原因
     * @return 列表页面
     */
    @HasAdmin
    @GetMapping("/review")
    public String review(@RequestParam List<Long> ids,
                         @RequestParam List<Long> judgeIds,
                         @RequestParam(defaultValue = "SUCCESS") Review result,
                         @RequestParam(required = false) String reason) {
        projectService.review(ids, judgeIds, result, reason);
        return "project-list";
    }

    /**
     * 删除项目
     *
     * @param ids 删除的 id
     * @return 结果
     */
    @HasAdmin
    @GetMapping("/delete")
    public String delete(@RequestParam List<Long> ids) {
        projectService.delete(ids);
        return "project-list";
    }

    @GetMapping("/view/{id}")
    public String project(@PathVariable Long id, Model model) {
        ProjectDetail projectDetail = projectService.view(id);
        model.addAttribute("detail", projectDetail);
        return "project-view";
    }

    /**
     * 评委获取自己可评的已报名过（不排除取消报名）的项目
     *
     * @return Set<ProjectStudent>
     */
    @HasJudge
    @ResponseBody
    @GetMapping("/getProjectStudentAndJudge")
    public Set<ProjectStudent> getProjectStudentAndJudge() {
        SysUser user = currentUser();
        Set<ProjectStudent> projectStudentSet = new HashSet<>();
        for (ProjectJudge p : projectJudgeService.findByJudgeId(user.getId())) {
            projectStudentSet.addAll(projectStudentService.findAllByProjectId(p.getProjectId()));
        }
        return projectStudentSet;
    }

    /**
     * 获取学生报名的关联项目
     *
     * @return List<ProjectStudent>
     */
    @HasStudent
    @ResponseBody
    @GetMapping("/getProjectStudent")
    public List<ProjectStudent> getProjectStudent() {
        return projectStudentService.findAll();
    }

    /**
     * 学生报名
     *
     * @param projectId 关联项目编号
     * @return 1
     */
    @HasStudent
    @ResponseBody
    @PostMapping("/createProjectStudent")
    public Integer createProjectStudent(String projectId, String status, String userId) {
        ProjectStudent oldProjectStudent = projectStudentService.findByProjectIdAndStudentId(Long.parseLong(projectId), Long.parseLong(userId));
        if (oldProjectStudent != null) {
            oldProjectStudent.setStatus(status);
            projectStudentService.save(oldProjectStudent);
        } else {
            ProjectStudent projectStudent = new ProjectStudent();
            SysUser user = currentUser();
            projectStudent.setProjectId(Long.parseLong(projectId));
            projectStudent.setStudentId(user.getId());
            projectStudent.setReview(Review.WAIT);
            projectStudent.setCreateUser(user.getName());
            projectStudent.setUpdateUser(user.getName());
            projectStudent.setStatus(status);
            projectStudentService.save(projectStudent);
        }
        return 0;
    }

    /**
     * 评委审核修改报名表状态
     *
     * @param id projectStudent表的编号
     */
    @HasJudge
    @ResponseBody
    @PostMapping("/updateProjectStudent")
    public Integer updateProjectStudent(String id, String review) {
        ProjectStudent projectStudent = projectStudentService.findById(Long.parseLong(id));
        if ("SUCCESS".equals(review)) {
            projectStudent.setReview(Review.SUCCESS);
        } else {
            projectStudent.setReview(Review.FAIL);
        }
        projectStudentService.save(projectStudent);
        return 0;
    }

    /**
     * 评委打分
     *
     * @param id projectStudent表的编号
     */
    @HasJudge
    @ResponseBody
    @PostMapping("/updateScore")
    public Integer updateScore(String id, String score) {
        ProjectStudent projectStudent = projectStudentService.findById(Long.parseLong(id));
        String oldScore = projectStudent.getScore();
        if (oldScore == null || oldScore.equals("") || oldScore.length() <= 0){
            projectStudent.setScore(score);
        }else {
            projectStudent.setScore(oldScore+","+score);
        }
        projectStudentService.save(projectStudent);
        return 0;
    }

    /**
     * 获取上传路径
     */
    @Value("${web.file-upload-path}")
    private String path;
    /**
     * 学生上传作品
     *
     * @param request 请求
     */
    @HasStudent
    @ResponseBody
    @PostMapping("/upload")
    public Integer upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        String projectId = multipartRequest.getParameter("projectId");
        String userId = multipartRequest.getParameter("userId");
        ProjectStudent p = projectStudentService.findByProjectIdAndStudentId(Long.parseLong(projectId),Long.parseLong(userId));
        if (multipartFile.isEmpty()) {
            System.out.println("上传的文件为空");
        }
        String uploadName = multipartFile.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + uploadName.substring(uploadName.lastIndexOf("."));
        try {
            File folder = new File(path);
            if (!folder.exists() && !folder.isDirectory()) {
                folder.mkdirs();
                System.out.println("创建文件夹");
            } else {
                System.out.println("文件夹已存在");
            }
            FileCopyUtils.copy(multipartFile.getBytes(), new File(path + fileName));
            p.setFile(path+fileName);
            projectStudentService.save(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Data
    @NoArgsConstructor
    public static class ProjectDetail {

        /**
         * 项目
         */
        private Project project;
        /**
         * 评委
         */
        private List<ProjectJudge> judges;
        /**
         * 学生
         */
        private List<ProjectStudent> students;
    }

}
