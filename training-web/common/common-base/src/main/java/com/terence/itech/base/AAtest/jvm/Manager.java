package com.terence.itech.base.AAtest.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiancai
 * @version $$Id: Manager, v 0.1 2020/7/8 11:29 ***  Exp $$
 */
public class Manager {
  public static int flushInterval= Manager.getInt("3");
  public static Map<String,Integer> replicas;
  static{
    initReplicas();
  }
  public static void initReplicas(){
    Boolean ifInit=false;
    Manager.replicas=new HashMap<>();
  }
  public static int getInt(String level){
    if("3".equals(level)){
      return 3;
    }
    return 1;
  }
  public Boolean isLocalData(){
    Boolean isLocal=true;
    return isLocal;
  }
  public void load(){
    System.out.println("****outPut****");
  }
}
