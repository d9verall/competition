package cn.deng.competition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 授权控制器.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/17 下午10:54
 */
@Controller
public class AuthController {

  /**
   * 跳转到登录页面 login.html
   * @return login
   */
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

}
