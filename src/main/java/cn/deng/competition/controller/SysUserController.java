package cn.deng.competition.controller;

import static cn.deng.competition.util.SecurityUtil.logout;
import static cn.deng.competition.util.SecurityUtil.reloadCurrentUser;
import static org.springframework.http.ResponseEntity.ok;

import cn.deng.competition.model.annotation.HasAdmin;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.SysUserService;
import java.util.List;
import java.util.Objects;

import cn.deng.competition.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户相关.
 *
 * @author verall
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

  private final SysUserService sysUserService;
  private final PasswordEncoder passwordEncoder;

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

  /**
   * 获取当前用户
   * @return SysUser
   */
  @ResponseBody
  @GetMapping("/currentUser")
  public SysUser currentUser() {
    return SecurityUtil.currentUser();
  }

  /**
   * 修改用户基本信息,需要具有完整的权限
   *
   * @param sysUser 用户修改后的基本信息
   * @return 用户页面
   */
  @PreAuthorize("isFullyAuthenticated()")
  @PostMapping("/profile")
  public String profile(Model model, SysUser sysUser) {
    // 获取到当前登陆的用户信息
    SysUser user = currentUser();
    // 只允许修改 name、email、phone 三个字段
    BeanUtils.copyProperties(user, sysUser, "name", "email", "phone");
    // 重新加载用户信息
    sysUserService.save(sysUser);
    reloadCurrentUser(sysUserService.save(sysUser));
    model.addAttribute("profile", true);
    model.addAttribute("user", sysUser);
    return "profile";
  }

  /**
   * 修改用户密码,需要具有完整的权限
   *
   * @param password   密码信息
   * @param rePassword 重复密码信息
   * @return 登陆页面
   */
  @PreAuthorize("isFullyAuthenticated()")
  @PostMapping("/password")
  public String password(Model model, @RequestParam String password,
      @RequestParam String rePassword) {
    // 获取当前登陆用户信息
    SysUser user = currentUser();
    // 对比密码是否一致
    if (!StringUtils.equals(password, rePassword)) {
      model.addAttribute("error", "两次密码不一致");
      return "profile";
    }
    // 一致就保存进去
    user.setPassword(passwordEncoder.encode(password));
    sysUserService.save(user);
    // 退出当前用户
    logout();
    return "redirect:/login";
  }

  /**
   * 更新用户
   *
   * @param user 用户信息
   * @return 结果
   */
  @HasAdmin
  @ResponseBody
  @PostMapping("/update")
  public HttpEntity<?> updateUser(@RequestBody SysUser user) {
    if (Objects.nonNull(user.getPassword())) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    sysUserService.updateUser(user);
    return ok().build();
  }

  /**
   * 导入用户
   *
   * @param users 用户
   * @return 结果
   */
  @HasAdmin
  @ResponseBody
  @PostMapping("/import")
  public HttpEntity<?> importUser(@RequestBody List<SysUser> users) {
    int count = sysUserService.importUser(users);
    return ok(count);
  }


  /**
   * 获取评委
   *
   * @return 评委
   */
  @ResponseBody
  @GetMapping("/judge")
  public HttpEntity<?> getJudge() {
    return ok(sysUserService.getJudge().stream()
        .map(user -> user.setPassword(null).setEmail(null).setPhone(null)));
  }

}

