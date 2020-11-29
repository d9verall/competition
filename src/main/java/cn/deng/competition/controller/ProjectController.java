package cn.deng.competition.controller;

import cn.deng.competition.model.annotation.HasAdmin;
import cn.deng.competition.model.annotation.HasAdminOrJudge;
import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.entity.Project;
import cn.deng.competition.model.entity.ProjectJudge;
import cn.deng.competition.model.entity.ProjectStudent;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.ProjectService;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author verall
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
@PreAuthorize("isAuthenticated()")
public class ProjectController {

  private final ProjectService projectService;

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
