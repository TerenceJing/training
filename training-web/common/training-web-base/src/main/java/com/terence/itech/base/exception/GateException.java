package com.terence.itech.base.exception;


import com.terence.itech.base.errorcode.ErrorCodeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>业务异常的关卡注解</p>
 *
 * @author Terence
 * @date 2019/10/12 13:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GateException {
  ErrorCodeEnum error() default ErrorCodeEnum.FAILED;
}
