package com.terence.itech.flowable.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>审批结点信息</p>
 *
 * @author Terence
 * @date 2019/10/31 17:06
 */
@Data
public class TaskNode {
  /**结点*/
  private String node;
  private String nodeName;
  /**当前结点参数*/
  private String assignee;
  /**当前结点处理人*/
  private String userId;
  private String userName;
  /**当前结点处理意见*/
  private String advice;
  /**送达时间*/
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date startTime;
  /**处理时间*/
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date endTime;
}
