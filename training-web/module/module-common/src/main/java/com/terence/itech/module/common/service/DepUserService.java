package com.terence.itech.module.common.service;


import com.terence.itech.module.common.controller.vo.UserVO;

import java.util.List;

/**
 * <p>用户类接口</p>
 *
 * @author Terence
 * @date 2019/9/30 9:34
 */
public interface DepUserService {
  /**
   *获取用户全量信息（用户信息/部门信息）
   *@author Terence
   *@date 2019/9/30 9:36
   */
  UserVO getUserInfo(String userId);
  /**
   *获取用户姓名
   *@author Terence
   *@date 2019/9/30 9:36
   */
  String getUserNameByUserId(String userId);

  /**
   *@desc 根据用户Id获取所在部门所有员工信息列表
   *@author Terence
   *@date 2019/9/30 9:36
   */
  List<Object> getEmployeesByUserId(String userId);


  /**
   *得到员工岗位类型
   *@author Terence
   *@date 2019/10/22 14:18
   */
  String getFamilyType(String postId);
  /**
   *根据当前员工号查询所在部门的直接领导(主持工作的人)
   *@author Terence
   *@date 2019/10/23 11:09
   */
  String getDepLeaderIdByStaffId(String userId);


  /**
   *获取下属员工号
   *@author Terence
   *@date 2019/11/4 11:13
   */
  List<String> getSubStaffsLikeName(String userId, String name);

}
