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
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/19 下午4:10
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

  private final SysUserService sysUserService;

  @ResponseBody
  @GetMapping("/exist/{user}")
  public Boolean exist(@PathVariable String user) {
    return sysUserService.existByEmailOrPhone(new SysUser().setEmail(user).setPhone(user));
  }

}
