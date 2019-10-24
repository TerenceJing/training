package com.terence.itech.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * <p></p>
 *
 * @author Jing Tiancai
 * @date 2019/10/24 8:26
 */
public class BossTaskHandler implements TaskListener {
  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("老板");
  }
}
