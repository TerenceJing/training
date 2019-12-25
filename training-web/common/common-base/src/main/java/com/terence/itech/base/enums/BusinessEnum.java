package com.terence.itech.base.enums;

import lombok.AllArgsConstructor;

/**
 * <p></p>
 *
 * @author Jing Tiancai
 * @date 2019/12/16 17:14
 */
public interface BusinessEnum {

  @AllArgsConstructor
  enum Type implements BaseEnum{
    /***/
    TODO("todo", "待办任务"),
    DONE("done", "已办任务"),
    ;
    private String code;
    private String name;
    @Override
    public String getCode() {
      return code;
    }
    @Override
    public String getName() {
      return name;
    }
  }
  @AllArgsConstructor
  enum AuthTypeEnum  implements BaseEnum{

    /**
     * 使用用户登录方式
     */
    USER("USER","用户登录方式"),
    /**
     * 使用应用登录方式
     */
    APP("APP","应用登录方式"),

    ;

    private String code;
    private String name;

    @Override
    public String getCode() {
      return code;
    }
    @Override
    public String getName() {
      return name;
    }
  }
}
