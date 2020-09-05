package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.SysUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Sys User Service.
 *
 * @author verall
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

  private final SysUserRepository sysUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public boolean existByEmailOrPhone(SysUser user) {
    return sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone());
  }

  @Override
  public SysUser save(SysUser user) {
    return sysUserRepository.save(user);
  }

  @Override
  public List<SysUser> getAll() {
    return sysUserRepository.findAll();
  }

  @Override
  public void lock(Long id) {
    SysUser user = sysUserRepository.getOne(id);
    user.setEnable(false);
    sysUserRepository.save(user);
  }

  @Override
  public void updateUser(SysUser user) {
    Long id = user.getId();
    SysUser sysUser = sysUserRepository.getOne(id);
    sysUser.setName(user.getName());
    sysUser.setPassword(user.getPassword());
    sysUser.setPhone(user.getPhone());
    sysUser.setEmail(user.getEmail());
    sysUser.setRole(user.getRole());
    sysUserRepository.save(sysUser);
  }

}
