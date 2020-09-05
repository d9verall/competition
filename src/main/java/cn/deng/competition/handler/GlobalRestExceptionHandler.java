package cn.deng.competition.handler;

import cn.deng.competition.model.exception.BadRequestException;
import cn.deng.competition.model.exception.ResourceExistException;
import cn.deng.competition.model.exception.ResourceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author verall
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public String paramExceptionHandler(MethodArgumentNotValidException e) {
    BindingResult exceptions = e.getBindingResult();
    // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
    if (exceptions.hasErrors()) {
      List<ObjectError> errors = exceptions.getAllErrors();
      if (!errors.isEmpty()) {
        // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
        FieldError fieldError = (FieldError) errors.get(0);
        return fieldError.getDefaultMessage();
      }
    }
    return "请求参数错误";
  }

  @ExceptionHandler({BadRequestException.class, ResourceExistException.class, ResourceNotFoundException.class})
  public HttpEntity<?> exceptionHandler(Exception e) {
    Map<String, String> result = new HashMap<>(1);
    result.put("message", e.getMessage());
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
  }
}
