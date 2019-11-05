package com.terence.itech.flowable.controller.vo;

import lombok.Data;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/28 13:18
 */
@Data
public class NextFlow {
  private String flowId;
  private String flowName;
  private String flowCondition;

  private String targetId;
  private String targetName;
  private String targetDesc;
  private String assignee;
}
