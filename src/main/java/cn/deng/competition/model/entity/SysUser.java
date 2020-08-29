package cn.deng.competition.model.entity;

import static javax.persistence.EnumType.STRING;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.model.constant.SysRole;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户实体类.
 *
 * @author verall
 */
@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Entity(name = "sys_user")
@Table(name = "sys_user")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity implements UserDetails, Serializable {

  /**
   * 密码
   */
  private String password;

  /**
   * 电话
   */
  private String phone;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 角色
   */
  @Enumerated(STRING)
  private SysRole role = SysRole.STUDENT;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return getEnable();
  }
}
