package com.terence.itech.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Jing Tiancai
 * @date 2019/10/24 19:13
 */
@Component("KpiProcessListener")
public class KpiProcessListener implements TaskListener {
  @Override
  public void notify(DelegateTask delegateTask){
    System.out.println("leader confirm listen ……");
  }

  public void handleLeaderConfirm(DelegateTask task, String taskName){
    // 回调业务
    System.out.println("=======callback=========");
    System.out.println("assignee:"+task.getAssignee()+"-----"+taskName);
  }
}
