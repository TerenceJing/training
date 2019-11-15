package com.terence.itech.test.api;


import lombok.Data;

/**
 * @author Dell
 */
@Data
class TestCaseRecord {
  @NickMapper(name="ID",desc = "序号")
  private String id;
  @NickMapper(name="TestCase",desc = "测试用例")
  private String testCase;
  @NickMapper(name="Token",desc = "认证密文")
  private String token;
  @NickMapper(name="Host",desc = "主机")
  private String host;
  @NickMapper(name="Port",desc = "端口")
  private String port;
  @NickMapper(name="Context",desc = "上下文")
  private String context;
  @NickMapper(name="Url",desc = "请求路径")
  private String url;
  @NickMapper(name="Request-Url",desc = "请求Url")
  private String requestUrl;
  @NickMapper(name="Method-Type",desc = "请求方法")
  private String method;
  @NickMapper(name="Content-Type",desc = "请求类型")
  private String contentType;
  @NickMapper(name="Input",desc = "输入")
  private String input;
  @NickMapper(name="ExpectedResult",desc = "预判结果")
  private String expected;
  @NickMapper(name="actualResult",desc = "输出结果")
  private String actual;
  @NickMapper(name="Comparision",desc = "结果")
  private String comparision;



  enum ComparisionType{
    /***/
    PASS,REJECT,ERROR
  }
}