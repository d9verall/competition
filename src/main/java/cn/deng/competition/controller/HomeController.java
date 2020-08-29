package cn.deng.competition.controller;

import javax.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author verall
 */
@PermitAll
@Controller
@RequestMapping
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home";
  }

}
