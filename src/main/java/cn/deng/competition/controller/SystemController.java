package cn.deng.competition.controller;

import cn.deng.competition.model.annotation.HasAdmin;
import cn.deng.competition.model.constant.SysRole;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.service.SysUserService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author verall
 */
@HasAdmin
@Controller
@RequiredArgsConstructor
@RequestMapping("/system")
public class SystemController {

  private final SysUserService sysUserService;

  @GetMapping("/user")
  public String user(Model model) {
    List<SysUser> users = sysUserService.getAll();
    model.addAttribute("users", users);
    model.addAttribute("student", countByRole(users, SysRole.ROLE_STUDENT));
    model.addAttribute("admin", countByRole(users, SysRole.ROLE_ADMIN));
    model.addAttribute("judge", countByRole(users, SysRole.ROLE_JUDGE));
    if (!model.containsAttribute("action")) {
      model.addAttribute("action", false);
    }
    return "user";
  }

  @GetMapping("/user/{id}")
  public String deleteUser(Model model, @PathVariable Long id) {
    sysUserService.lock(id);
    model.addAttribute("action", true);
    return "forward:/system/user";
  }

  private Long countByRole(List<SysUser> users, SysRole role) {
    return Optional.ofNullable(users)
        .orElseGet(Collections::emptyList)
        .stream().filter(user -> user.getRole().equals(role)).count();
  }
}
