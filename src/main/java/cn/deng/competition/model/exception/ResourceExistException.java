package cn.deng.competition.model.exception;

/**
 * @author verall
 */
public class ResourceExistException extends RuntimeException {
  public ResourceExistException() {
    super("资源已经存在");
  }

  public ResourceExistException(String message) {
    super(message);
  }

  public ResourceExistException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceExistException(String message, Object... params) {
    super(String.format(message, params));
  }
}
