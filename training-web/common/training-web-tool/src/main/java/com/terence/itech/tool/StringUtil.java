package com.terence.itech.tool;

import org.apache.commons.lang3.StringUtils;

/**
 * <p></p>
 *
 * @author Terence
 * @date 2019/11/1 14:56
 */
public class StringUtil {
  public static String getKey(String condition){
    return getSubCondition(condition,"key");
  }
  public static String getValue(String condition){
    return getSubCondition(condition,"value");
  }

  private static String getSubCondition(String condition,String type){
    if(StringUtils.isBlank(condition)){
      return null;
    }
    String key=null;
    String value=null;
    String name=condition.substring(condition.indexOf("{")+1,condition.lastIndexOf("}"));
    if(name.contains("=")){
      key = name.substring(0, name.indexOf("=")).trim();
      value = name.substring(name.lastIndexOf("=") + 1).trim();
    }
    if(name.contains("eq")){
      key = name.substring(0, name.indexOf("eq")).trim();
      value = name.substring(name.indexOf("\"") + 1, name.lastIndexOf("\"")).trim();
    }

    if("key".equals(type)){
      return key;
    }
    return value;
  }
  // @Test
  void test(){
    System.out.println(getKey("${next == true}"));
    System.out.println(getValue("${next == true}"));
  }

}
