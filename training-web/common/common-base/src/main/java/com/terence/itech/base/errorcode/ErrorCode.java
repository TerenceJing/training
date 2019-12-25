package com.terence.itech.base.errorcode;

import org.apache.commons.lang3.StringUtils;

/**
 *<p>错误码承接类</p>
 * 为什么使用错误码类对应承接错误码枚举类，因为枚举类的消息改变是永久性的，改变的是枚举类本身。
 *@author Terence
 *@date 2019/10/12 11:13
 */
public class ErrorCode {
  private String code;
  private String msg;
  private String english;
  ErrorCode() {  }
  ErrorCode(String code, String msg, String english) {
    this.code = code;
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

  /**
   *补充参数
   *@author Terence
   *@date 2019/10/12 10:57
   */
  public ErrorCode cpp(String ...params) {
    this.setMsg(String.format(this.getMsg(),params));
    this.setEnglish(String.format(this.getEnglish(),params));
    return this;
  }
  /**
   *追加提示信息
   *@author Terence
   *@date 2019/10/12 10:57
   */
  public ErrorCode appendMsg(String msg) {
    if(StringUtils.isBlank(this.getMsg())){
      this.setMsg(msg);
    }else{
      this.setMsg(this.getMsg()+". "+msg);
    }
    if(StringUtils.isBlank(this.getEnglish())){
      this.setEnglish(msg);
    }else{
      this.setEnglish(this.getEnglish()+". "+msg);
    }
    return this;
  }
}
