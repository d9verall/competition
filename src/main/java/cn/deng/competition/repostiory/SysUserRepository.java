package cn.deng.competition.repostiory;

import cn.deng.competition.base.BaseRepository;
import cn.deng.competition.model.entity.SysUser;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author verall
 * @date 2020/7/18 下午10:43
 */
@Repository
public interface SysUserRepository extends BaseRepository<SysUser> {

  /**
   * 通过邮箱或者手机号查询用户是否存在
   *
   * @param email 邮箱
   * @param phone 手机号
   * @return 是否存在
   */
  boolean existsByEmailOrPhone(String email, String phone);

  /**
   * 通过邮箱查找用户是否存在
   *
   * @param email 邮箱
   * @return 结果
   */
  boolean existsByEmail(String email);

  /**
   * 通过手机号查找用户是否存在
   *
   * @param phone 手机号
   * @return 结果
   */
  boolean existsByPhone(String phone);

  /**
   * 通过邮箱或者手机号查询用户
   *
   * @param email 邮箱
   * @param phone 手机号
   * @return 用户
   */
  Optional<SysUser> findFirstByEmailOrPhone(String email, String phone);

}
