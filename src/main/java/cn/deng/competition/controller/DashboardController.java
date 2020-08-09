package cn.deng.competition.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author verall
 */
@Controller
@RequestMapping("/dashboard")
@PreAuthorize("isAuthenticated()")
public class DashboardController {

  @GetMapping
  public String index() {
    return "index";
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

}
