package cn.deng.competition.controller;

import cn.deng.competition.model.annotation.HasAdmin;
import cn.deng.competition.model.annotation.HasJudge;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author verall
 */
@Controller
@RequestMapping("/project")
@PreAuthorize("isAuthenticated()")
public class ProjectController {

  /**
   * 创建项目
   *
   * @return 页面
   */
  @HasAdmin
  @HasJudge
  @GetMapping("/create")
  public String create() {
    return "project-create";
  }

}
