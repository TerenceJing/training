package com.terence.itech.module.common.controller;

import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.exception.GateException;
import com.terence.itech.base.result.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>用户</p>
 *
 * @author Terence
 * @date 2019/11/5 15:21
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @GetMapping("/get-user-info")
  @GateException(error=ErrorCodeEnum.USER_INFO_QUERY_FILED)
  public BaseResult getUserInfo(String userId){
    float i=1/0;
    return BaseResult.success(userId);
  }
}
