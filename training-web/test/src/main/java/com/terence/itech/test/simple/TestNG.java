package com.terence.itech.test.simple;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * <p></p>
 *
 * @author Jing Tiancai
 * @date 2019/11/13 15:04
 */
public class TestNG {
  @DataProvider(name="user")
  public Object[][] createUser(Method m){
    return new Object[][] { { "root", "root" }, { "test", "root" }, { "test", "test" } };
  }
  @Test(dataProvider = "user")
  public void verifyUser(String username,String password){
    System.out.println("Verify User : (" + username + ":" + password+ ")");
    assert username.equals(password);
  }
}
