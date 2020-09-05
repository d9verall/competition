package cn.deng.competition.service.impl;

import cn.deng.competition.model.constant.SysRole;
import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.model.exception.ResourceExistException;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author verall
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final SysUserRepository sysUserRepository;
  private final PasswordEncoder passwordEncoder;

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
    // 构建 UserDetails 接口实现类
    return sysUserRepository
        .findFirstByEmailOrPhone(username, username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("用户 %s 不存在", username)));
  }

  @Override
  public void register(SysUser user) {
    boolean exist = sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone());
    if (exist) {
      throw new ResourceExistException("用户已经存在");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(SysRole.ROLE_STUDENT);
    sysUserRepository.save(user);
  }
}
