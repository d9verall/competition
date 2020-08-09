package cn.deng.competition.repostiory;

import cn.deng.competition.model.entity.SysUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * .
 *
 * @author verall
 * @date 2020/7/18 下午10:43
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

  /**
   * 通过邮箱或者手机号查询用户是否存在
   *
   * @param email 邮箱
   * @param phone 手机号
   * @return 是否存在
   */
  boolean existsByEmailOrPhone(String email, String phone);

  /**
   * 通过邮箱或者手机号查询用户
   *
   * @param email 邮箱
   * @param phone 手机号
   * @return 用户
   */
  Optional<SysUser> findFirstByEmailOrPhone(String email, String phone);

}
