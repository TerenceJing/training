package com.terence.itech.base.auth;

import com.terence.itech.base.enums.AuthTypeEnum;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Token 拦截器，校验token有效性
 */
public class TokenCheckInterceptor implements HandlerInterceptor {
    @Value("${auth.token.validate.switch:true}")
    private Boolean tokenValidate;
    private String jsonResult="{\n" + "    \"type\":-1,\n" + "    \"code\": \"0x000001\",\n" + "    \"msg\": \"token认证失败\"\n" + "}";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //预请求不拦截
        if (StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), RequestMethod.OPTIONS.name())) {
            return true;
        }

        //获取在请求头或参数中的token
        String tokenStr = httpServletRequest.getParameter("token");
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(tokenStr) && StringUtils.isBlank(token)) {
            renderJson(httpServletResponse, jsonResult, 401);
            return false;
        }
        if (StringUtils.isBlank(token)) {
            token = tokenStr;
        }

        //开发环境下的特殊操作！
        if (!tokenValidate) {
            httpServletRequest.setAttribute("userId", token);
            return true;
        }

        //校验token
        Claims claims;
        try {
            claims = JJWHelper.getClaims(token);
        } catch (Exception e) {
            renderJson(httpServletResponse, jsonResult, 401);
            return false;
        }

        AuthTypeEnum authType = AuthTypeEnum.valueOf(claims.get("authType", String.class));
        httpServletRequest.setAttribute("authType", authType.name());
        switch (authType) {
            case USER:
                httpServletRequest.setAttribute("userId", claims.get("userId", String.class));
                break;
            case APP:
                httpServletRequest.setAttribute("appId", claims.get("appId", String.class));
                break;
            default:
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                                Exception e) throws Exception {
    }

    private void renderJson(HttpServletResponse response, String jsonResult, int httpStatus) {
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