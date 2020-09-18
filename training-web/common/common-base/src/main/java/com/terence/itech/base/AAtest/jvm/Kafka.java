package com.terence.itech.base.AAtest.jvm;

/**
 * @author tiancai
 * @version $$Id: Kafka, v 0.1 2020/7/8 11:28 ***  Exp $$
 */
public class Kafka {
  public  static  void main(String[] args) {
    Manager manager=new Manager();
    Boolean local=manager.isLocalData();
    System.out.println(local);
  }
}
