package com.terence.itech.base.AAtest.classloader;

import java.lang.reflect.Method;

/**
 * @author tiancai
 * @version $$Id: CustomClassLoaderTest, v 0.1 2020/9/22 17:08 ***  Exp $$
 */
public class CustomClassLoaderTest {
  /*定义了一个目录存放class文件，这个其实可以修改为可配置参数*/
  private static final String rootDir = System.getProperty("user.dir")+"\\common\\common-base\\target\\classes\\";;

  public static void main(String[] args) throws Exception {
    /*<1> 从指定的目录下查找对应的class文件，进行加载，然后创建该对象，如果加载存在则加载成功，则类加载器应为MyClassLoader*/
    MyClassLoader classLoader = new MyClassLoader(rootDir);
    Class c = classLoader.loadClass("com.terence.itech.base.AAtest.set.Person");
    Object object = c.newInstance();
    Method getNameMethod = c.getMethod("getName");
    Method getAgeMethod = c.getMethod("getAge");
    System.out.println("name:" + getNameMethod.invoke(object) + ",age:" + getAgeMethod.invoke(object));
    System.out.println("类加载器为：" + object.getClass().getClassLoader());
  }

}
