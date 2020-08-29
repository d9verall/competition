package cn.deng.competition.util;

import cn.deng.competition.model.entity.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author verall
 */
public class SecurityUtil {

  private SecurityUtil() {
  }

  public static SysUser currentUser() {
    return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public static void reloadCurrentUser(SysUser principal) {
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(principal,
            principal.getPassword(), principal.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  public static void logout() {
    SecurityContextHolder.clearContext();
  }

}
