package cn.deng.competition.model.entity;

import static javax.persistence.EnumType.STRING;

import cn.deng.competition.base.BaseEntity;
import cn.deng.competition.model.constant.SysRole;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

/**
 * 用户实体类.
 *
 * @author verall
 */
@Data
@SuperBuilder
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name = "sys_user")
@Table(name = "sys_user")
@Where(clause = "enable = true")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

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
  private SysRole role;

}
