package cn.deng.competition.model.entity;

import static javax.persistence.EnumType.STRING;

import cn.deng.competition.model.constant.SysRole;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户实体类.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 上午1:52
 */
@Data
@Entity(name = "sys_user")
@Table(name = "sys_user")
@Accessors(chain = true)
public class SysUser {

  /**
   * id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 名字
   */
  private String name;

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
