package com.terence.itech.module.common.service.impl;

import com.terence.itech.module.common.controller.vo.UserVO;
import com.terence.itech.module.common.service.DepUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/11/5 17:45
 */
@Service
public class DepUserServiceImpl implements DepUserService {
  @Override
  public List<Object> getEmployeesByUserId(String userId) {
    return null;
  }

  @Override
  public List<String> getSubStaffsLikeName(String userId, String name) {
    return null;
  }

  @Override
  public String getDepLeaderIdByStaffId(String userId) {
    return null;
  }

  @Override
  public String getUserNameByUserId(String userId) {
    return null;
  }

  @Override
  public String getFamilyType(String postId) {
    return null;
  }

  @Override
  public UserVO getUserInfo(String userId) {
    return null;
  }

}
