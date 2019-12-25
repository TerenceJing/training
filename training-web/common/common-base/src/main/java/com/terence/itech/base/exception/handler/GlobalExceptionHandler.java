package com.terence.itech.base.exception.handler;

import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.exception.BusinessException;
import com.terence.itech.base.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/9/29 15:35
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {
  private static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
  /**
   *拦截内部业务异常
   *@author Terence
   *@date 2019/9/29 15:40
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public BaseResult handleBusinessException(BusinessException e) {
    //业务日志已经输出过日志，无需再输出了
    return BaseResult.fail(1,e.getCode(),e.getMsg());
  }
  /**
   *拦截内部其他异常
   *@author Terence
   *@date 2019/10/12 18:49
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public BaseResult notFount(RuntimeException e) {
    String msg= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+". Status:"+ HttpStatus.INTERNAL_SERVER_ERROR.value();
    logger.error(msg,e);
    return BaseResult.fail(ErrorCodeEnum.FAILED.ec().appendMsg(msg));
  }
}
