package com.terence.itech.flowable.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/25 16:43
 */
@Data
public class ProcessVO<D> {
  /**
   * 流程图名称
   */
  private String processKey;
  /**
   * 流程图定义Id;
   */
  private String processDefId;
  /**
   * 流程id
   */
  private String processId;
  /**
   * 流程名称
   */
  private String processName;
  /**
   * 发起人
   */
  private String startUserId;
  private String startUserName;
  /**
   * 发起时间
   */
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date startTime;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date endTime;

  /**
   * 当前任务Id
   */
  private String currentTaskId;
  /**
   * 业务单据号
   */
  private String currentTaskName;
  /**
   * 当前任务描述
   */
  private String currentTaskDesc;
  /**
   * 当前任务处理人
   */
  private String currentUserId;
  private String currentUserName;
  /**
   * 当前任务处理Id
   */
  private String executionId;
  /**
   * 当前任务
   */
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private Date currentTaskTime;

  /**
   * 业务单据号
   */
  private String businessId;
  /**
   * 业务数据
   */
  private D businessData;
}
