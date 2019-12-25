package com.terence.itech.module.common.service.impl;

import com.terence.itech.base.config.ApplicationContextHolder;
import com.terence.itech.module.common.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/11 9:12
 */
@Service
public class BaseServiceImpl implements BaseService {
  @Override
  public String getUserId(){
    return (String) ApplicationContextHolder.getRequest().getAttribute("userId");
  }
}
