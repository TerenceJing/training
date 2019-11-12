package com.terence.itech.base.config;

import com.terence.itech.base.auth.CorsFilter;
import com.terence.itech.base.auth.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/11/12 16:01
 */
@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean allowCorsFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new CorsFilter());
    registration.addUrlPatterns("/*");
    registration.setOrder(1);
    return registration;
  }

  @Bean
  public FilterRegistrationBean allowTokenFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new TokenFilter());
    registration.addUrlPatterns("/*");
    registration.setOrder(2);
    return registration;
  }
}
