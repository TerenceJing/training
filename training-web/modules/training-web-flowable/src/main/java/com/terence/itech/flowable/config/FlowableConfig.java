package com.terence.itech.flowable.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * <p>防止生成的流程图中文乱码</p>
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {


  @Override
  public void configure(SpringProcessEngineConfiguration engineConfiguration) {
    engineConfiguration.setActivityFontName("宋体");
    engineConfiguration.setLabelFontName("宋体");
    engineConfiguration.setAnnotationFontName("宋体");
  }
}