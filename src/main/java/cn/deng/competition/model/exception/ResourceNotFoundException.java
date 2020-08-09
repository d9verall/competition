package cn.deng.competition.model.exception;

/**
 * .
 *
 * @author verall
 */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super("资源未找到");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(String message, Object... params) {
    super(String.format(message, params));
  }

}
