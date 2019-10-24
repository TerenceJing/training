package com.terence.itech.base.exception;

import com.alibaba.fastjson.JSON;
import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.result.BaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>拦截外部异常</p>
 */
public class ExceptionInterceptor implements HandlerInterceptor {
  private String preResult="{\n" + "    \"type\":-1,\n" + "    \"code\": \"0x000001\",\n" + "    \"msg\":\"";
  private String postResult="\"\n" + "}";

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                         ModelAndView modelAndView) {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                              Exception e) {
  }

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) {
    if(response.getStatus()!=200){
      HttpStatus httpStatus=getHttpStatusItem(response.getStatus());
      String jsonResult= JSON.toJSONString(BaseResult.fail(ErrorCodeEnum.ERROR.ec().appendMsg("Status:"+httpStatus.value()+", "+httpStatus.getReasonPhrase())));
      renderJson(response, jsonResult, response.getStatus());
    }
    return true;
  }
  private HttpStatus getHttpStatusItem(int status){
    HttpStatus httpStatus= HttpStatus.NOT_FOUND;
    for(HttpStatus e: HttpStatus.values()){
      if(status==e.value()){
        httpStatus=e;
        break;
      }
    }
    return httpStatus;
  }
  public void renderJson(HttpServletResponse response, String jsonResult, int httpStatus) {
    response.setStatus(httpStatus);
    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    try (PrintWriter printWriter = response.getWriter()) {
      printWriter.write(jsonResult);
      printWriter.flush();
    } catch (IOException e) {
      //Ignore
    }
  }
}
