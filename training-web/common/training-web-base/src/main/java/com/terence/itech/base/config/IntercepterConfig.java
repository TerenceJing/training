package com.terence.itech.base.config;

import com.terence.itech.base.auth.TokenCheckInterceptor;
import com.terence.itech.base.exception.handler.ExceptionInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>拦截器注册配置</p>
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
  @Value("${auth.validate.allow-url-patterns}")
  private String allowPatterns;
  @Value("${auth.validate.refuse-url-patterns}")
  private String refusePatterns;


  @Bean
  public TokenCheckInterceptor tokenCheckInterceptor() {
    return new TokenCheckInterceptor();
  }
  @Bean
  public ExceptionInterceptor getExceptionInterceptor() {
    return new ExceptionInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 异常拦截
    registry.addInterceptor(getExceptionInterceptor());
    //Token校验拦截器
    registry.addInterceptor(tokenCheckInterceptor()).addPathPatterns(allowPatterns.split(",")).excludePathPatterns(refusePatterns.split(","));
  }
}
