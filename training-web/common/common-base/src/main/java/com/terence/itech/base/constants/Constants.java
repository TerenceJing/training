package com.terence.itech.base.constants;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/11/12 16:10
 */
@Configuration
public class Constants implements EnvironmentAware {
  public static String JWT_KEY;
  public static String JWT_ISS;
  public static long JWT_EXPTIME;

  @Override
  public void setEnvironment(Environment environment) {
    JWT_KEY = environment.getProperty("token.generate.key");
    JWT_ISS = environment.getProperty("token.generate.iss");
    JWT_EXPTIME = environment.getProperty("token.generate.expire-time", Long.class);
  }
}
