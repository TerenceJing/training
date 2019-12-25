package com.terence.itech.base.enums;

import lombok.AllArgsConstructor;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/11/5 17:26
 */
public interface ProcessEnum {
  /**
   *族类
   *@author Terence
   *@date 2019/10/22 14:47
   */
  @AllArgsConstructor
  enum ProcessListType{
    /**code:流程图的名称 */
    TODO("todo", "待办任务"),
    DONE("done", "已办任务"),
    ;
    private String code;
    private String name;

    public String getCode() {
      return code;
    }

    public String getName() {
      return name;
    }
  }
}
