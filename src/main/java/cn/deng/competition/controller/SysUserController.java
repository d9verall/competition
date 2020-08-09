package cn.deng.competition.controller;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @ResponseBody
  @GetMapping("/exist/{user}")
  public Boolean exist(@PathVariable String user) {
    return sysUserService
        .existByEmailOrPhone(
            SysUser.builder()
                .name(user)
                .email(user)
                .phone(user)
                .build());
  }

}
