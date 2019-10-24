package com.terence.itech.base.config;

import com.terence.itech.base.exception.BusinessException;
import com.terence.itech.base.exception.GateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>用切面对注解异常进行拦截，在接口层进行异常处理</p>
 * @author Terence
 */
@Aspect
@Component
public class GateExceptionAspect {
  @Pointcut("@annotation(com.terence.itech.base.exception.GateException)")
  public void annotationPointCut(){}

  @AfterThrowing(pointcut = "annotationPointCut()",throwing = "e")
  public void afterThrowing(JoinPoint joinPoint, Exception e){
    if (e instanceof BusinessException) {
      return;
    }
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    GateException gate = method.getAnnotation(GateException.class);
    throw BusinessException.gateException(gate.error(),e);
  }

}
