package com.terence.itech.base.annotion;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义获取配置信息
 * @author tiancai
 * @version $$Id: AuthProperties, v 0.1 2020/10/22 15:04 ***  Exp $$
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth",ignoreInvalidFields = false)
public class AuthProperties {
  private boolean enabled = true;
  private boolean validate;
  private boolean tokenValidateSwitch;
  private String tokenGenerateKey;
  private String tokenGenerateIss;

}
