package cn.deng.competition.service;

import cn.deng.competition.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author verall
 */
public interface AuthService extends UserDetailsService {

  /**
   * 注册用户
   *
   * @param user 用户
   */
  void register(SysUser user);
}
