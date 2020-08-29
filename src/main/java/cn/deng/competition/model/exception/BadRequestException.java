package cn.deng.competition.model.exception;

/**
 * @author verall
 */
public class BadRequestException extends RuntimeException {

  public BadRequestException() {
    super("请求体不合法");
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(String message, Object... params) {
    super(String.format(message, params));
  }
}
