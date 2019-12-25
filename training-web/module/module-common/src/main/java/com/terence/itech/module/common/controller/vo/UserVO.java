package com.terence.itech.module.common.controller.vo;

import lombok.Data;

/**
 * <p>员工用户信息</p>
 *
 * @author Terence
 * @date 2019/10/12 9:10
 */
@Data
public class UserVO {
  private String userId;
  private String userName;
  private String sex;
  private String tel;
  private String depId;
  private String depName;
  private String postId;
  private String majorAddr;
  private String status;
  private String grade;
  private String familyId;
}
