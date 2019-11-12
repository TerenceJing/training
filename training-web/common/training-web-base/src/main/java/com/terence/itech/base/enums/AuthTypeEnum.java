package com.terence.itech.base.enums;

import lombok.AllArgsConstructor;

/**
 * 说明：登录方式枚举
 *
 */
@AllArgsConstructor
public enum AuthTypeEnum {

    /**
     * 使用用户登录方式
     */
    USER("USER","USER","用户登录方式"),

    /**
     * 使用应用登录方式
     */
    APP("APP","APP","应用登录方式"),

    ;

    private String code;
    private String name;
    private String desc;



    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
