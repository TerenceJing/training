package com.terence.itech.base.exception;


import com.terence.itech.base.errorcode.ErrorCode;
import com.terence.itech.base.errorcode.ErrorCodeEnum;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>处理内部业务异常</p>
 *
 * @author JingTiancai
 * @date 2019-09-29 00:16
 */
@Data
public class BusinessException extends RuntimeException  {
  private String code;
  private String msg;
  private static Logger logger= LoggerFactory.getLogger(BusinessException.class);
  void setBusinessException(String code,String msg){
    this.code=code;
    this.msg=msg;
  }
  void setBusinessException(ErrorCode ec){
    this.setBusinessException(ec.getCode(),ec.getMsg());
  }
  void setBusinessException(ErrorCodeEnum ece){
    this.setBusinessException(ece.getCode(),ece.getMsg());
  }
  public BusinessException() {
    setBusinessException(ErrorCodeEnum.FAILED.ec());
    logger.error(this.getCode()+"."+this.getMsg());
  }
  public BusinessException(String msg) {
    this.msg=msg;
    logger.error(msg);
  }
  public BusinessException(ErrorCode ec) {
    logger.error(ec.getCode()+"."+ec.getMsg());
    setBusinessException(ec);
  }
  public BusinessException(ErrorCode ec, Throwable cause) {
    logger.error(ec.getCode()+"."+ec.getMsg(), cause);
    setBusinessException(ec);
  }
  public BusinessException(ErrorCodeEnum ece) {
    logger.error(ece.getCode()+"."+ece.getMsg());
    setBusinessException(ece);
  }
  public BusinessException(ErrorCodeEnum ece, Throwable cause) {
    logger.error(ece.getCode()+"."+ece.getMsg(), cause);
    setBusinessException(ece);
  }
  public BusinessException(String msg,Throwable cause) {
    ErrorCode e= ErrorCodeEnum.FAILED.ec().appendMsg(msg);
    setBusinessException(e);
    logger.error(e.getCode()+"."+e.getMsg(),cause);
  }

  /**
   * 查看是否是业务异常，不是则包装成业务异常
   * gateException（）系列方法
   *@author Jing Tiancai
   *@date 2019/9/29 14:35
   */
  public static BusinessException gateException(Exception ex) {
    return gateException(ErrorCodeEnum.FAILED,ex);
  }
  public static BusinessException gateException(ErrorCodeEnum ece) {
    return gateException(ece,null);
  }
  public static BusinessException gateException(ErrorCodeEnum ece,Exception ex) {
    if(ex instanceof BusinessException){
      return (BusinessException)ex;
    }else{
      return new BusinessException(ece,ex);
    }
  }
  public static BusinessException gateException(ErrorCode e, Exception ex) {
    if(ex instanceof BusinessException){
      return (BusinessException)ex;
    }else{
      return new BusinessException(e,ex);
    }
  }
}
