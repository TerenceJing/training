package com.terence.itech.common.controller;

import com.terence.itech.base.config.ApplicationContextHolder;
import com.terence.itech.common.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>基础控制类</p>
 *
 * @author Terence
 * @date 2019/10/11 9:12
 */
public class BaseController {
  private static Logger logger= LoggerFactory.getLogger(BaseController.class);

  @Autowired
  private BaseService baseService;
  /**
   * 获取request
   *@author Terence
   *@date 2019/10/11 9:18
   */
  public HttpServletRequest getRequest() {
    return ApplicationContextHolder.getRequest();
  }
  /**
   *获取userId
   *@author Terence
   *@date 2019/10/11 9:19
   */
  public String getUserId() {
    return baseService.getUserId();
  }
}
