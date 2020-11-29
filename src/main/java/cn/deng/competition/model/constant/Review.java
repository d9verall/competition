package cn.deng.competition.model.constant;

/**
 * 审批类型.
 *
 * @author verall
 */
public enum Review {
  /**
   * 待审批
   */
  WAIT,
  /**
   * 通过
   */
  SUCCESS,
  /**
   * 未通过
   */
  FAIL;

  @Override
  public String toString() {
    return name().toUpperCase();
  }

  public Boolean isSuccess() {
    return "SUCCESS".equalsIgnoreCase(name());
  }

  public Boolean isFail() {
    return "FAIL".equalsIgnoreCase(name());
  }

  public Boolean isWait() {
    return "WAIT".equalsIgnoreCase(name());
  }
}
