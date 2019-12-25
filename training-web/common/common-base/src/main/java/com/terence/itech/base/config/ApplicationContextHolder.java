package com.terence.itech.base.config;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * @author Terence
 */
public class ApplicationContextHolder {
  public static Map<String,Object> getParameters(){
    Map<String,Object> params=new HashMap<>();
    return params;
  }
  public static HttpServletRequest getRequest(){
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}
