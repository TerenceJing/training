package com.terence.itech.base.AAtest.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author tiancai
 * @version $$Id: Kafka, v 0.1 2020/7/8 11:28 ***  Exp $$
 */
public class Kafka {
  private static Kafka instance;
  public  static  void main(String[] args) {
    Manager manager=new Manager();
    Boolean local=manager.isLocalData();
    System.out.println(local);
  }

  @Override
  public void finalize(){
    Kafka.instance=this;
    SoftReference<Kafka> soft=new SoftReference<>(new Kafka());
    WeakReference<Kafka> weak=new WeakReference<>(new Kafka());
  }
}
