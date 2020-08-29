package cn.deng.competition.controller;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.SysUserService;
import javax.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * .
 *
 * @author verall
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

  private final SysUserService sysUserService;

  @PermitAll
  @ResponseBody
  @GetMapping("/exist")
  public Boolean exist(@RequestParam String email,
      @RequestParam String phone) {
    return sysUserService
        .existByEmailOrPhone(
            SysUser.builder()
                .email(email)
                .phone(phone)
                .build());
  }
}
