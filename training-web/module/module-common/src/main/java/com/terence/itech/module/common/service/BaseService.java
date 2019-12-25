package com.terence.itech.module.common.service;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/11 9:12
 */
public interface BaseService {
  /**
   *获取当前登录用户id，减少风险
   *从前后端会话中获取当前用户id
   *@author Terence
   *@date 2019/10/11 9:15
   */
  String getUserId();
}
