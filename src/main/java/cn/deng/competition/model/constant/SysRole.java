package cn.deng.competition.model.constant;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 上午2:01
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
