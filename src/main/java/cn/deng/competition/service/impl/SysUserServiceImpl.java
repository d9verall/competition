package cn.deng.competition.service.impl;

import cn.deng.competition.model.entity.SysUser;
import cn.deng.competition.model.exception.ResourceExistException;
import cn.deng.competition.repostiory.SysUserRepository;
import cn.deng.competition.service.SysUserService;
import cn.deng.competition.util.AvatarUtil;
import java.util.List;
import javax.transaction.Transactional;
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
    if (sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone())) {
      throw new ResourceExistException("用户已经存在");
    }
    if (id == -1) {
      user.setId(null);
      user.setAvatar(AvatarUtil.generate());
      sysUserRepository.save(user);
      return;
    }
    SysUser sysUser = sysUserRepository.getOne(id);
    sysUser.setName(user.getName());
    sysUser.setPassword(user.getPassword());
    sysUser.setPhone(user.getPhone());
    sysUser.setEmail(user.getEmail());
    sysUser.setRole(user.getRole());
    sysUserRepository.save(sysUser);
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public int importUser(List<SysUser> users) {
    users.forEach(user -> {
          user.setAvatar(AvatarUtil.generate())
              .setPassword(passwordEncoder.encode(user.getPassword()));
          if (sysUserRepository.existsByEmailOrPhone(user.getEmail(), user.getPhone())) {
            throw new ResourceExistException("用户 %s 或 %s 已经存在", user.getEmail(), user.getPhone());
          }
        }
    );
    List<SysUser> sysUsers = sysUserRepository.saveAll(users);
    return sysUsers.size();
  }

}
