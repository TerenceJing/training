package com.terence.itech.flowable.controller;

import com.terence.itech.base.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/24 8:28
 */
@RestController
@RequestMapping(value = "/kpi")
public class KpiController {
  @Autowired
  private RuntimeService runtimeService;
  @Resource
  private IdentityService identityService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private RepositoryService repositoryService;
  @Resource
  private ProcessEngine processEngine;
  @Resource
  private HistoryService historyService;


  /**
   *添加审批流程，并进入下一步
   */
  @RequestMapping(value = "/add")
  public BaseResult addExpense(String applyUserId,String businessId,String userId,String leaderId) {
    String diagram="KpiConfirmProcess";
    //启动流程
    if(StringUtils.isNotBlank(applyUserId)){
      identityService.setAuthenticatedUserId(applyUserId);
    }
    HashMap<String, Object> map = new HashMap<>();
    map.put("staff", userId);
    map.put("leader", leaderId);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(diagram,businessId,map);
    identityService.setAuthenticatedUserId(null);
    return BaseResult.success( processInstance.getId());
  }
  /**
   * 获取审批管理列表----待办任务
   */
  @RequestMapping(value = "/list")
  public BaseResult list(String userId) {
    List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
    List<Map> resList=new ArrayList();
    Map<String,Object> map;
    for (Task task : tasks) {
      map= new HashMap();
      System.out.println(task.toString());
      map.put("id",task.getId());
      map.put("name",task.getName());
      map.put("assignee",task.getAssignee());
      map.put("createTime",task.getCreateTime());
      map.put("executionId",task.getExecutionId());
      map.put("instanceId",task.getProcessInstanceId());
      resList.add(map);
    }
    return BaseResult.success(resList);
  }
  /**
   * 获取审批管理列表----已办任务
   */
  @RequestMapping(value = "/historyList")
  public BaseResult historyList(String userId) {
    List<HistoricTaskInstance> hisTaskList=historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished().orderByTaskCreateTime().desc().list();

    return BaseResult.success(hisTaskList);
  }

  /**
   * 提交到下一步
   * 根据条件流转到具体的下一步
   *
   */
  @RequestMapping(value = "/apply")
  public BaseResult apply(String taskId,String userId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      throw new RuntimeException("流程不存在");
    }
    //通过审核
    HashMap<String, Object> map = new HashMap<>();
    map.put("leader", userId);
    map.put("confirm",true);
    taskService.complete(taskId, map);
    return BaseResult.success("accept");
  }
  /**
   * 拒绝
   */
  @RequestMapping(value = "/reject")
  public BaseResult reject(String taskId) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("outcome", "驳回");
    taskService.complete(taskId, map);
    return BaseResult.success("reject");
  }

  /**
   *
   * 结束任务
   */
  @RequestMapping(value = "/complete")
  public BaseResult completeTask(String taskId) {
    processEngine.getTaskService().complete(taskId);
    return BaseResult.success("complete");
  }

  /**
   *
   * 结束任务
   */
  @RequestMapping(value = "/node-list")
  public BaseResult nodeList(String processInstanceId) {
    List<HistoricVariableInstance> hvis = historyService.createHistoricVariableInstanceQuery()
            .processInstanceId(processInstanceId).list();

    return BaseResult.success(hvis);
  }

  /**
   * 生成流程图
   *
   * @param processId 任务ID
   */
  @RequestMapping(value = "processDiagram")
  public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
    ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

    //流程走完的不显示图
    if (pi == null) {
      return;
    }
    Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
    //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
    String InstanceId = task.getProcessInstanceId();
    List<Execution> executions = runtimeService
            .createExecutionQuery()
            .processInstanceId(InstanceId)
            .list();

    //得到正在执行的Activity的Id
    List<String> activityIds = new ArrayList<>();
    List<String> flows = new ArrayList<>();
    for (Execution exe : executions) {
      List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
      activityIds.addAll(ids);
    }

    //获取流程图
    BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
    ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
    ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
    InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0,false);
    OutputStream out = null;
    byte[] buf = new byte[1024];
    int length = 0;
    try {
      out = httpServletResponse.getOutputStream();
      while ((length = in.read(buf)) != -1) {
        out.write(buf, 0, length);
      }
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }

}
