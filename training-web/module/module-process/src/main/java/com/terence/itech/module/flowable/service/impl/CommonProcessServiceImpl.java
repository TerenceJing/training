package com.terence.itech.flowable.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.terence.itech.base.enums.ProcessEnum;
import com.terence.itech.base.errorcode.ErrorCodeEnum;
import com.terence.itech.base.exception.BusinessException;
import com.terence.itech.base.exception.helper.ParamValidateUtil;
import com.terence.itech.base.result.BasePageData;
import com.terence.itech.flowable.controller.vo.NextFlow;
import com.terence.itech.flowable.controller.vo.ProcessVO;
import com.terence.itech.flowable.controller.vo.TaskNode;
import com.terence.itech.flowable.service.CommonProcessService;
import com.terence.itech.module.common.service.DepUserService;
import com.terence.itech.tool.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/10/25 10:39
 */
@Service
public class CommonProcessServiceImpl implements CommonProcessService {
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
  @Resource
  private DepUserService depUserService;

  @Override
  public String launchProcess(String applyUserId, String businessId, String processId, Map<String, Object> variables) {
    ParamValidateUtil.validParamExist("processId", processId);
    if (StringUtils.isNotBlank(applyUserId)) {
      identityService.setAuthenticatedUserId(applyUserId);
    }
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId, businessId, variables);
    identityService.setAuthenticatedUserId(null);
    return processInstance.getId();
  }

  @Override
  public String complete2next(String taskId, Map<String, Object> variables) {
    ParamValidateUtil.validParamExist("taskId", taskId);
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    if (null != task) {
      throw new BusinessException(ErrorCodeEnum.PROCESS_EMPTY_ERROR);
    }
    if (MapUtil.isNotEmpty(variables)) {
      taskService.complete(taskId, variables);
    } else {
      taskService.complete(taskId);
    }
    return "accept";
  }

  @Override
  public String complete(String taskId) {
    ParamValidateUtil.validParamExist("taskId", taskId);
    processEngine.getTaskService().complete(taskId);
    return "complete";
  }

  @Override
  public BasePageData getProcessList(Integer pageNo, Integer pageSize, String userId, String type, String listType) {
    ParamValidateUtil.validParamExist("userId", userId);
    // 获取当前用户的待办任务列表
    BasePageData<ProcessVO> res = new BasePageData<ProcessVO>() {{
      setPageNo(pageNo);
      setPageSize(pageSize);
    }};
    int total = 0;
    List resList = null;
    if (ProcessEnum.ProcessListType.TODO.getCode().equals(listType)) {
      total = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list().size();
      List tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().listPage((pageNo - 1) * pageSize, pageSize);
      resList = completeProcessListResult(tasks, type, ProcessEnum.ProcessListType.TODO.getCode());

    }
    if (ProcessEnum.ProcessListType.DONE.getCode().equals(listType)) {
      total = historyService.createHistoricTaskInstanceQuery()
              .taskAssignee(userId)
              .finished()
              .orderByTaskCreateTime()
              .desc()
              .list().size();
      List<HistoricTaskInstance> instanceList = historyService.createHistoricTaskInstanceQuery()
              .taskAssignee(userId)
              .finished()
              .orderByTaskCreateTime()
              .desc()
              .listPage((pageNo - 1) * pageSize, pageSize);
      resList = completeProcessListResult(instanceList, type, ProcessEnum.ProcessListType.DONE.getCode());
    }
    res.setList(resList);
    res.setTotal(total);
    return res;
  }

  private void handleProcessResult(List<ProcessVO> processList) {
    processList.forEach(e -> {
      if (StringUtils.isBlank(e.getBusinessId()) || "null".equals(e.getBusinessId()) || StringUtils.isBlank(e.getStartUserId())) {
        return;
      }
      // if (PerformanceEnum.ProcessType.KPI_CONFIRM.getCode().equals(e.getProcessKey())) {
      //   e.setBusinessData(staffAssessService.getStaffAssessInfo(e.getStartUserId(), Integer.valueOf(e.getBusinessId()), false));
      // } else {
      //
      // }

    });

  }

  /**
   * 补全结果
   * 待办，当前结点即为处理结点
   * 已办，根据processInstanceId重新获取最新任务
   *
   * @author Terence
   * @date 2019/10/28 12:33
   */
  private <D extends TaskInfo> List<ProcessVO> completeProcessListResult(List<D> taskList, String type, String taskType) {
    //
    List<ProcessVO> processList = new CopyOnWriteArrayList<ProcessVO>() {{
      for (TaskInfo task : taskList) {
        add(new ProcessVO() {{
          setProcessId(task.getProcessInstanceId());
          setProcessDefId(task.getProcessDefinitionId());
          if (ProcessEnum.ProcessListType.TODO.getCode().equals(taskType)) {
            setCurrentTaskName(task.getName());
            setCurrentUserId(task.getAssignee());
            setCurrentTaskId(task.getId());
          } else {
            List<Task> currentTaskList = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
            if (CollectionUtils.isNotEmpty(currentTaskList)) {
              Task currentTask = currentTaskList.get(0);
              setCurrentTaskName(currentTask.getName());
              setCurrentUserId(currentTask.getAssignee());
              setCurrentTaskId(currentTask.getId());
            }
          }

          if (StringUtils.isNotBlank(getCurrentUserId())) {
            setCurrentUserName(depUserService.getUserNameByUserId(getCurrentUserId()));
          }
          setCurrentTaskDesc(task.getDescription());
          setExecutionId(task.getExecutionId());
          setCurrentTaskTime(task.getCreateTime());
        }});
      }
    }};
    processList.forEach(e -> {
      HistoricProcessInstance instance = ProcessEngines.getDefaultProcessEngine().
              getHistoryService().createHistoricProcessInstanceQuery().
              processInstanceId(e.getProcessId()).singleResult();
      if (StringUtils.isBlank(type) || (StringUtils.isNotBlank(type) && type.equals(instance.getProcessDefinitionKey()))) {
        e.setProcessKey(instance.getProcessDefinitionKey());
        e.setProcessName(instance.getProcessDefinitionName());
        e.setStartUserId(instance.getStartUserId());
        if (StringUtils.isNotBlank(instance.getStartUserId())) {
          e.setStartUserName(depUserService.getUserNameByUserId(instance.getStartUserId()));
        }
        e.setStartTime(instance.getStartTime());
        e.setEndTime(instance.getEndTime());
        e.setBusinessId(instance.getBusinessKey());
      } else {
        processList.remove(e);
      }
    });
    //根据businessId补充业务数据
    handleProcessResult(processList);
    return processList;
  }

  @Override
  public List getAllList(String userId) {
    ParamValidateUtil.validParamExist("userId", userId);
    return historyService.createHistoricTaskInstanceQuery()
            .taskAssignee(userId)
            .orderByTaskCreateTime()
            .desc()
            .list();
  }

  @Override
  public List getNextStepInfo(String processDefId, String taskId) {
    Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    String key = task.getTaskDefinitionKey();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefId);
    Process process = bpmnModel.getProcesses().get(0);
    Collection<FlowElement> flowElements = process.getFlowElements();
    List<NextFlow> result = new ArrayList<>();
    for (FlowElement flowElement : flowElements) {
      if (flowElement instanceof UserTask && key.equals(flowElement.getId())) {
        UserTask u = (UserTask) flowElement;
        List<SequenceFlow> flows = u.getOutgoingFlows();
        flows.forEach(e -> {
          NextFlow flow = new NextFlow();
          result.add(flow);
          flow.setFlowId(e.getId());
          flow.setFlowName(e.getName());
          flow.setFlowCondition(e.getConditionExpression());
          flow.setTargetId(e.getTargetFlowElement().getId());
          flow.setTargetName(e.getTargetFlowElement().getName());
          flow.setTargetDesc(e.getTargetFlowElement().getDocumentation());
          //assignee无法获取，通过同名copy获得
          BeanUtils.copyProperties(e.getTargetFlowElement(), flow);
          if (StringUtils.isNotBlank(flow.getAssignee())) {
            // 解析，下一个接口使用
            String assignee = flow.getAssignee();
            flow.setAssignee(assignee.substring(assignee.indexOf("{") + 1, assignee.indexOf("}")).trim());
          }
        });
      }
    }
    return result;
  }

  @Override
  public void complete2next(String taskId, String flowCondition, String advice) {
    complete2next(taskId, flowCondition, advice, null);
  }

  @Override
  public void complete2next(String taskId, String flowCondition, String advice, String nextAssignee) {
    Map<String, Object> variables = new HashMap<>();
    if (StringUtils.isNotBlank(flowCondition)) {
      String key = StringUtil.getKey(flowCondition);
      String value = StringUtil.getValue(flowCondition);
      variables.put(key, value);
    }
    if (StringUtils.isNotBlank(nextAssignee) && nextAssignee.contains(":")) {
      String key = nextAssignee.substring(0, nextAssignee.indexOf(":")).trim();
      String value = nextAssignee.substring(nextAssignee.indexOf(":") + 1).trim();
      variables.put(key, value);
    }
    // 添加评论
    if (StringUtils.isNotBlank(advice)) {
      String processId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
      taskService.addComment(taskId, processId, advice);
    }
    taskService.complete(taskId, variables);
  }


  @Override
  public List getTaskNodeList(String processDefId, String processId) {
    List<TaskNode> nodeList = new ArrayList<>();
    //已审批任务的审批意见(查询taskList,通过taskId查询comment)
    List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
            .processInstanceId(processId).list();
    // 对历史任务进行重排序
    htiList.sort((a, b) -> (int) (a.getCreateTime().getTime() - b.getCreateTime().getTime()));
    // 没有发生的任务，在node中同样显示出
    htiList.forEach(h -> {
      nodeList.add(new TaskNode() {{
        setNodeName(h.getName());
        setUserId(h.getAssignee());
        setStartTime(h.getCreateTime());
        setEndTime(h.getEndTime());
        setNode(h.getTaskDefinitionKey());
        List<Comment> commentList = taskService.getTaskComments(h.getId());
        //审批意见(一条意见)
        if (CollectionUtils.isNotEmpty(commentList)) {
          setAdvice(commentList.get(0).getFullMessage());
        }
      }});
    });

    // 预设审批人
    List<HistoricVariableInstance> hvis = historyService.createHistoricVariableInstanceQuery()
            .processInstanceId(processId).list();
    Process process = repositoryService.getBpmnModel(processDefId).getProcesses().get(0);
    if (null == process) {
      return nodeList;
    }
    process.getFlowElements().forEach(e -> {
      if (e instanceof UserTask) {
        AtomicBoolean has= new AtomicBoolean(false);
        nodeList.forEach(n->{
          if(n.getNode().equals(e.getId())){
            has.set(true);
          }
        });
        if(!has.get()){
          nodeList.add(new TaskNode() {{
            setNode(e.getId());
            setNodeName(e.getName());
            String assignee = ((UserTask) e).getAssignee();
            setAssignee(assignee.substring(assignee.indexOf("{") + 1, assignee.indexOf("}")).trim());
          }});
        }
      }
    });
    nodeList.forEach(e -> {
      hvis.forEach(h -> {
        JSONObject json = (JSONObject) JSONObject.toJSON(h);
        //直接取不出来，使用json间接取值
        if (StringUtils.isNotBlank(e.getAssignee())&&e.getAssignee().equals(json.getString("variableName"))) {
          e.setUserId(json.getString("textValue"));
        }
      });
      if (StringUtils.isNotBlank(e.getUserId())) {
        e.setUserName(depUserService.getUserNameByUserId(e.getUserId()));
      }
    });
    return nodeList;
  }
}
