package cn.deng.competition.controller;

import javax.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 授权控制器.
 *
 * @author verall
 */
@Controller
public class AuthController {

  /**
   * 跳转到登录页面 login.html
   * @return login
   */
  @PermitAll
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

}
