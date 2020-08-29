package cn.deng.competition.controller;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.model.exception.BadRequestException;
import cn.deng.competition.service.AuthService;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 授权控制器.
 *
 * @author verall
 */
@Controller
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * 跳转到登录页面 login.html
   *
   * @return login
   */
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  /**
   * 用户注册接口
   *
   * @param userRegister 用户注册 DTO
   * @return 结果
   */
  @ResponseBody
  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody UserRegister userRegister) {
    if (!userRegister.password.equals(userRegister.rePassword)) {
      throw new BadRequestException("两次密码不一致");
    }
    authService.register(userRegister.toSysUser());
    return ResponseEntity.ok().build();
  }

  @Data
  @NoArgsConstructor
  private static class UserRegister {

    @NotEmpty(message = "姓名不能为空")
    private String name;
    @Email(message = "邮件格式不合法")
    private String email;
    @Size(min = 11, max = 11, message = "手机号码不合法")
    private String phone;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "密码不能为空")
    private String rePassword;

    public SysUser toSysUser() {
      return SysUser.builder()
          .name(name)
          .email(email)
          .phone(phone)
          .password(password)
          .enable(true)
          .build();
    }
  }
}
