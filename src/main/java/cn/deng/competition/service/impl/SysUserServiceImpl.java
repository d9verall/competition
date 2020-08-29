package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.SysUserService;
import lombok.RequiredArgsConstructor;
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

  @Override
  public boolean existByEmailOrPhone(SysUser user) {
    return sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone());
  }

}
