package com.terence.itech.base.errorcode;

/**
 *<p>错误码</p>
 *@author Terence
 *@date 2019/10/12 15:04
 */
public enum ErrorCodeEnum {
  /**===============================通用类型=======================================*/
  ERROR("010001","访问错误","Error"),
  FAILED("010002","执行失败","Failed"),
  TOKEN_ERROR("010003","token认证失败","Token error"),
  UNKNOW_ERROR("010004","未知错误","Unknown error"),
  REQUEAT_ERROR("010005","请求访问信息错误","Request info error"),
  DB_ERROR( "010006", "数据库异常", "DtaBase error"),
  PARAMETER_ERROR( "010007", "参数异常", "Parameter error"),
  PARAMETER_EMPTY( "010008", "参数{%s}不能为空", "Parameter {%s} cannot be empty"),
  TOKEN_VALIDATE_FAIL( "010009", "token认证失败", "Failed to validate token"),
  EXCEL_READ_ERROR("010006", "excel读取错误", "Excel read error"),

  /**=============================通用业务模块=======================================*/
  ROLE_ADD_UPDATE_FILED( "020001", "角色添加/修改失败", "Failed to add or update role"),
  ROLE_PAGE_FILED( "020002", "角色查询失败", "Failed to query role info"),
  ROLE_DELETE_FILED( "020003", "角色删除失败", "Failed to delete role info"),
  ROLE_USER_ADD_FILED( "020004", "角色用户分配失败", "Failed to add user-role relation"),
  USER_INFO_QUERY_FILED( "020005", "用户信息查询失败,员工号：{%s}", "Failed to query user info with userId {%s}"),

  USER_EMPTY( "020006", "查无此人,员工号：{%s}", "Query none with userId {%s}"),
  USER_NAME_QUERY_FILED( "020007", "用户姓名查询失败,员工号：{%s}", "Failed to query userName info with userId {%s}"),

  /**==============================流程类业务模块=======================================*/
  PROCESS_TASK_LIST_QUERY_FAILED("030009", "获取任务列表失败", "Failed to query todo list"),
  PROCESS_NEXT_INFO_ERROR("040001", "查询下一步流程信息失败", "Failed to query query next process info"),
  PROCESS_TASK_COMPLETE_FAILED("040002", "流程任务处理失败", "Failed to handle process task"),
  PROCESS_EMPTY_ERROR("040003", "流程为空", "Process null error"),

  PROCESS_KPI_BASELINE_FAILED("040004", "审核流程发起失败，总分不足100分", "Failed to launch process with less 100 score"),
  PROCESS_KPI_STATUS_FAILED("040005", "审核流程发起失败，考核指标已冻结", "Failed to launch process with frozen index"),

  ;
  private String appCode="0x";
  private String code;
  private String msg;
  private String english;
  ErrorCodeEnum(String code, String msg, String english) {
    this.code = appCode+code;
    this.msg = msg;
    this.english = english;
  }
  public String getCode() {
    return code;
  }
  public String getMsg() {
    return msg;
  }
  public String getEnglish() {
    return english;
  }

  private void setMsg(String msg) {
    this.msg = msg;
  }

  private void setEnglish(String english) {
    this.english = english;
  }
  public ErrorCode ec(){
    return new ErrorCode(this.getCode(),this.getMsg(),this.getEnglish());
  }
  public ErrorCode ec(String...params){
    String msg=String.format(this.getMsg(),params);
    String label=String.format(this.getEnglish(),params);
    return new ErrorCode(this.getCode(),msg,label);
  }
}
