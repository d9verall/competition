package cn.deng.competition.controller;

import cn.deng.competition.model.constant.Review;
import cn.deng.competition.model.constant.SysRole;
import cn.deng.competition.model.entity.Project;
import cn.deng.competition.model.entity.ProjectStudent;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.ProjectService;
import cn.deng.competition.service.ProjectStudentService;
import cn.deng.competition.service.SysUserService;
import cn.deng.competition.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 *
 * @author verall
 */
@Controller
@RequestMapping("/dashboard")
@PreAuthorize("isAuthenticated()")
public class DashboardController {

    final
    ProjectService projectService;
    final
    ProjectStudentService projectStudentService;
    final
    SysUserService sysUserService;

    public DashboardController(ProjectService projectService, ProjectStudentService projectStudentService, SysUserService sysUserService) {
        this.projectService = projectService;
        this.projectStudentService = projectStudentService;
        this.sysUserService = sysUserService;
    }

    @GetMapping
    @PreAuthorize("isFullyAuthenticated()")
    public String index(Model model) {
        List<Project> projectList = projectService.findAll();
        List<ProjectStudent> projectStudentList = projectStudentService.findAll();
        List<SysUser> sysUserList = sysUserService.getAll();
        int reviewCount = 0;
        int applyCount = 0;
        int studentApplyReviewCount = 0;
        int studentFileCount = 0;
        int judgeFileCount = 0;
        int judgeCount = 0;
        int studentCount = 0;
        String scoreNameList = "";
        String scoreDataList = "";
        for (Project project : projectList) {
            if (project.getReview() != Review.WAIT) {
                reviewCount++;
            }
            if (project.getApply()) {
                applyCount++;
            }
        }
        for (ProjectStudent projectStudent : projectStudentList) {
            if (projectStudent.getReview() != Review.WAIT) {
                studentApplyReviewCount++;
            }
            if (projectStudent.getFile() != null) {
                studentFileCount++;
            }
            if (projectStudent.getScore() != null) {
                scoreNameList += (sysUserService.findById(projectStudent.getStudentId()).getUsername())+',';
                scoreDataList += (scoreString(projectStudent))+',';
                judgeFileCount++;
            }
        }
        for (SysUser sysUser : sysUserList) {
            if (sysUser.getRole() == SysRole.ROLE_STUDENT) {
                studentCount++;
            }
            if (sysUser.getRole() == SysRole.ROLE_JUDGE) {
                judgeCount++;
            }
        }
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("applyCount", applyCount);
        model.addAttribute("studentApplyReviewCount", studentApplyReviewCount);
        model.addAttribute("studentFileCount", studentFileCount);
        model.addAttribute("judgeFileCount", judgeFileCount);
        model.addAttribute("judgeCount", judgeCount);
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("scoreNameList", scoreNameList);
        model.addAttribute("scoreDataList", scoreDataList);
        model.addAttribute("studentApplyCount", projectStudentList.size());
        return "index";
    }

    @GetMapping("/profile")
    @PreAuthorize("isFullyAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("user", SecurityUtil.currentUser());
        return "profile";
    }

    @GetMapping("/alerts")
    public String alerts() {
        return "alerts";
    }

    @GetMapping("/blank")
    public String blank() {
        return "blank";
    }

    @GetMapping("/buttons")
    public String buttons() {
        return "buttons";
    }

    @GetMapping("/charts")
    public String charts() {
        return "charts";
    }

    @GetMapping("/copy-content")
    public String copyContent() {
        return "copycontent";
    }

    @GetMapping("/data-tables")
    public String datatables() {
        return "datatables";
    }

    @GetMapping("/dropdowns")
    public String dropdowns() {
        return "dropdowns";
    }

    @GetMapping("/form-advanceds")
    public String formAdvanceds() {
        return "form_advanceds";
    }

    @GetMapping("/form-basics")
    public String formBasics() {
        return "form_basics";
    }

    @GetMapping("/modals")
    public String modals() {
        return "modals";
    }

    @GetMapping("/popovers")
    public String popovers() {
        return "popovers";
    }

    @GetMapping("/progress-bar")
    public String progressBar() {
        return "progress-bar";
    }

    @GetMapping("/simple-tables")
    public String simpleTables() {
        return "simple-tables";
    }

    @GetMapping("/ui-colors")
    public String uiColors() {
        return "ui-colors";
    }

    public String scoreString(ProjectStudent projectStudent) {
        String allScore = projectStudent.getScore();
        String[] singleScore = allScore.split(",");
        int totalScore = 0;
        for (String s : singleScore) {
            totalScore += Integer.parseInt(s);
        }
        return Integer.toString(totalScore);
    }
}
