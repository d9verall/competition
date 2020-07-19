package cn.deng.competition.service;

import cn.deng.competition.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Sys User Service.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 下午10:41
 */
public interface SysUserService extends UserDetailsService {

  /**
   * 根据邮箱/手机查询用户是否存在
   *
   * @param user 用户
   * @return 结果
   */
  boolean existByEmailOrPhone(SysUser user);

}
