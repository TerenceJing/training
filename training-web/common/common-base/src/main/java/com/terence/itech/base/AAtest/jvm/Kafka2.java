package com.terence.itech.base.AAtest.jvm;

/**
 * @author tiancai
 * @version $$Id: Kafka2, v 0.1 2020/10/22 16:41 ***  Exp $$
 */
public class Kafka2 {
  private static Manager fetcher=new Manager();
  public static void main(String[] args) throws InterruptedException {
    loadInitInfo();
    fetcher.load();
    while(true){
      loadInfo();
      Thread.sleep(1000);
    }
  }
  private static void loadInfo(){
    fetcher.load();
  }
  private static void loadInitInfo(){
    Manager manager=new Manager();
    manager.load();
  }
}
