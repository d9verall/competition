package cn.deng.competition.model.exception;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/7/18 下午10:47
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
