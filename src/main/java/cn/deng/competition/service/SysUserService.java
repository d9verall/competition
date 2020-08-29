package cn.deng.competition.service;

import cn.deng.competition.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Sys User Service.
 *
 * @author verall
 */
public interface SysUserService {

  /**
   * 根据邮箱/手机查询用户是否存在
   *
   * @param user 用户
   * @return 结果
   */
  boolean existByEmailOrPhone(SysUser user);

  /**
   * 保存/更新用户信息
   *
   * @param user 用户
   * @return 保存后的用户
   */
  SysUser save(SysUser user);
}
