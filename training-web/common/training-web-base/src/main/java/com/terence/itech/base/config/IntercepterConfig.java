package com.terence.itech.base.config;

import com.terence.itech.base.exception.handler.ExceptionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>拦截器注册配置</p>
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

  @Bean
  public ExceptionInterceptor getExceptionInterceptor() {
    return new ExceptionInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 异常拦截
    registry.addInterceptor(getExceptionInterceptor());
  }

}
