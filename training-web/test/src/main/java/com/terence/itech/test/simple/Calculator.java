package com.terence.itech.test.simple;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Jing Tiancai
 * @date 2019/11/13 15:39
 */
public class Calculator {

  @DataProvider(name="num")
  public Iterator<Object[]> Numbers() throws IOException {
    return new CSVData("add.csv");
  }
  @Test(dataProvider="num")
  public void testAdd(Map<String, String> data){
    float num1=Float.parseFloat(data.get("n1"));
    float num2=Float.parseFloat(data.get("n2"));
    float expectedResult=Float.parseFloat(data.get("r1"));
    Float actual=add(num1, num2);
    Assert.assertEquals(actual, expectedResult);
  }

  public float add(float a ,float b){
    return a+b;
  }
}
