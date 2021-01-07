package com.terence.itech.base.AAtest.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义类加载器要:
 * (1)继承ClassLoader;
 * (2)重写findClass方法，这是JVM留给开发者的入口；
 * 为什么重写findClass方法，为什么将它设计为开发者入口？？？
 */
public class MyClassLoader extends ClassLoader {
  private String rootDir;

  public MyClassLoader(String rootDir){
    this.rootDir=rootDir;
  }
  public MyClassLoader(String rootDir,ClassLoader classLoader){
    super(classLoader);
    this.rootDir=rootDir;
  }
  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    //<1>.根据类的全路径（包含包名）类名和放置的目录确定类文件的路径
    String className=name.substring(name.lastIndexOf(".")+1)+".class";
    String classFile=rootDir+ File.separator+className;
    FileInputStream fileInputStream=null;
    byte[] classData=null;
    try {
      //<2>.将class文件读取到字节数组
      fileInputStream = new FileInputStream(new File(classFile));
      classData = new byte[fileInputStream.available()];
      fileInputStream.read(classData,0,classData.length);
      //<3>.将字节数据创建一个class
      return defineClass(name,classData,0,classData.length);
    }catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (fileInputStream != null){
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    //<4>如果父类加载器不是自定义的，上面的加载过程没加载成功，则此调用会throw ClassNotFoundException
    return super.findClass(name);
  }
}

/**
 *ClassLoader源码解读:
 * 核心就是加载类的方法： loadClass()
 * （1）获取锁；
 * （2）findLoadedClass(name)查找类；若被加载过，则从缓存中获取类并返回(类加载的缓存机制)（源码调用了native方法）。
 * （3）如果没有加载过这个类，则开始加载该类：
 *  （3.1）查看当前类是否有父类：
 *  （3.2）如果有父类，则先加载父类（加载原理一样，递归调用）；
 *  （3.3）如果没有父类，则直接 findBootstrapClassOrNull(name)，查找启动类加载器缓存中是否存在该类（源码调用了native方法）；
 * （4）如果仍然没有加载到该类，就会调用findClass加载类。
 *
 * So,只要开发者继承ClassLoader并重写findClass让其执行findClass,就可以加载自定义的类。
 */
