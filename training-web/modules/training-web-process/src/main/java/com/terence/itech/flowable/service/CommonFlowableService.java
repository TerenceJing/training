package com.terence.itech.flowable.service;


import com.terence.itech.base.result.BasePageData;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/25 10:38
 */
public interface CommonFlowableService {

  /**
   *启动流程
   * applyUserId:当前登陆用户Id
   * businessId:业务单据Id
   * processId:流程图的Id
   * variables:执行流程任务所需要的参数
   *@author Terence
   *@date 2019/10/25 10:50
   */
  String launchProcess(String applyUserId, String businessId, String processId, Map<String, Object> variables);
  /**
   *完成本步审批任务，并进入到下一步
   *taskId:当前任务所需id
   *variables:执行当前任务所需要的参数
   *@author Terence
   *@date 2019/10/25 10:53
   */
  String complete2next(String taskId, Map<String, Object> variables);

  /**
   *结束任务
   *@author Terence
   *@date 2019/10/25 10:57
   */
  String complete(String taskId);

  /**
   *获取userId的任务列表
   *@author Terence
   *@date 2019/10/25 10:59
   */
  BasePageData getProcessList(Integer pageNo, Integer pageSize, String userId, String type, String listType);

  /**
   *获取所有任务列表
   *@author Terence
   *@date 2019/10/25 12:12
   */
  List getAllList(String userId);

  List getNextStepInfo(String processDefId, String taskId);
  /**
   *将任务推到下一步
   *@author Terence
   *@date 2019/10/28 16:15
   */
  void complete2next(String taskId, String condition, String advice);
  void complete2next(String taskId, String condition, String advice, String nextAssignee);

  /**
   *获取流程审批结点信息
   *@author Terence
   *@date 2019/10/28 16:15
   */
  List getTaskNodeList(String processDefId, String processId);

}
