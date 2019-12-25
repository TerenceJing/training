package com.terence.itech.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * <p>kpi处理流程的监听器</p>
 *
 * @author Terence
 * @date 2019/10/24 19:13
 */
@Component("KpiProcessListener")
public class KpiProcessListener implements TaskListener {
  @Override
  public void notify(DelegateTask delegateTask) {
    System.out.println("leader confirm listen ……");
  }
  /**
   * 考核状态-0
   * 99a3d8a9-fc6c-11e9-ac4d-005056c00008
   * @author Terence
   * @date 2019/10/31 12:47
   */
  public void handleLeaderBack(DelegateTask task, String taskName) {
    String businessId=getBusinessIdFromProcess(task.getProcessInstanceId());
    this.updateStatus(businessId,"1");
  }

  /**
   * 考核状态-1
   *
   * @author Terence
   * @date 2019/10/31 12:47
   */
  public void handleStaffSubmit(DelegateTask task, String taskName) {
    String businessId=getBusinessIdFromProcess(task.getProcessInstanceId());
    this.updateStatus(businessId,"2");
  }



  /**
   * 考核状态-2
   *
   * @author Terence
   * @date 2019/10/31 12:47
   */
  public void handleLeaderConfirm(Execution execution, String executionName) {
    String businessId=getBusinessIdFromProcess(execution.getProcessInstanceId());
    this.updateStatus(businessId,"3");
  }
  /**
   *获取业务单据号
   *@author Terence
   *@date 2019/10/31 13:35
   */
  private String getBusinessIdFromProcess(String processId){
    ProcessInstance instance= CommandContextUtil.getExecutionEntityManager().findById(processId);
    return instance.getBusinessKey();
  }
  /**
   *更新考核状态
   *走流程的指标要设置为禁止修改的状态
   *@author Terence
   *@date 2019/10/31 13:35
   */
  private void updateStatus(String businessId, String status){
   System.out.println(String.format("====业务单据号为%s的业务状态已更新为%s",businessId,status));
  }

}
