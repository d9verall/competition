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
  ADMIN,
  /**
   * 评委
   */
  JUDGE,
  /**
   * 学生
   */
  STUDENT;

  @Override
  public String toString() {
    return this.name().toUpperCase();
  }
}
