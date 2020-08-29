package cn.deng.competition.model.constant;

/**
 * .
 *
 * @author verall
 */
public enum SysRole {
  /**
   * 管理员
   */
  ROLE_ADMIN,
  /**
   * 评委
   */
  ROLE_JUDGE,
  /**
   * 学生
   */
  ROLE_STUDENT;

  @Override
  public String toString() {
    return this.name().toUpperCase();
  }
}
