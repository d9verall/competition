package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Sys User Service.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 下午10:43
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

  private final SysUserRepository sysUserRepository;

  @Override
  public boolean existByEmailOrPhone(SysUser user) {
    return sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone());
  }

  /**
   * 获取用户信息，交给 Spring security 进行检验以及构建用户登录对象
   *
   * @param username 用户名 -> 邮箱/手机号
   * @return 用户
   * @throws UsernameNotFoundException 用户未找到异常
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 通过邮箱/手机号查询用户信息，如果不存在，抛出用户不存在异常
    SysUser user = sysUserRepository.findFirstByEmailOrPhone(username, username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("用户 %s 不存在", username)));
    // 构建 UserDetails 接口实现类
    return User.builder()
        .username(username)
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}
