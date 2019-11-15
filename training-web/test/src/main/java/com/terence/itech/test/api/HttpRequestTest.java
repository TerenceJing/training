package com.terence.itech.test.api;

import com.jayway.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Dell
 */
public class HttpRequestTest implements ITest {

  @Override
  public String getTestName() {
    return "API Test";
  }

  private String filePath = "";

  private XSSFWorkbook wb;
  private XSSFSheet inputSheet = null;
  private XSSFSheet outputSheet = null;
  private XSSFSheet resultSheet = null;

  private int totalCase = 0;
  private int failedCase = 0;
  private String startTime = "";
  private String endTime = "";
  private List<TestCaseRecord> testCaseRecordList;

  @BeforeTest
  @Parameters("file")
  public void setup(@Optional("workBook.xlsx") String fileName) throws IOException {
    System.out.println("BeforeTest========================读取测试用例文件");
    File directory = new File(".");
    String path = ".src.main.resources.";
    filePath = directory.getCanonicalPath() + path.replaceAll("\\.", Matcher.quoteReplacement("\\")) + fileName;
    try {
      wb = new XSSFWorkbook(new FileInputStream(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    inputSheet = wb.getSheet("Input");
    //移除对比结果
    SheetUtils.removeSheetByName(wb, "Output");
    SheetUtils.removeSheetByName(wb, "Result");
    outputSheet = wb.createSheet("Output");
    resultSheet = wb.createSheet("Result");

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    startTime = sf.format(new Date());
  }

  @DataProvider(name = "data")
  protected Iterator<Object[]> testProvider(ITestContext context) {
    List<Object[]> tests = new ArrayList<>();
    testCaseRecordList= DataHelper.readFromSheet(inputSheet);
    totalCase=testCaseRecordList.size();
    for (TestCaseRecord record:  testCaseRecordList) {
        tests.add(new Object[]{record});
    }
    return tests.iterator();
  }

  @Test(dataProvider = "data", description = "ReqGenTest")
  public void apiTest(TestCaseRecord record) {
    Response response = null;
    System.out.println("Test:(id:" + record.getId() + ",testCase:" + record.getTestCase() + ")");
    HttpRequest httpRequest = new HttpRequest();
    try {
      httpRequest.createRequest(record);
      response = httpRequest.sendRequest(record);
    } catch (Exception e) {
      Assert.fail("Problem using HTTPRequestGenerator to generate response: " + e.getMessage());
    }

    try {
      String expectedResult = record.getExpected();
      record.setActual(response.getBody().asString());
      boolean pass = DataHelper.compare(expectedResult, record.getActual());
      if (pass) {
        //将结果写入OutputSheet,不带颜色
        record.setComparision(TestCaseRecord.ComparisionType.PASS.name());
        DataHelper.writeData(outputSheet, record, true);
      } else {
        failedCase++;
        record.setComparision(TestCaseRecord.ComparisionType.REJECT.name());
        //将结果写入OutputSheet,带颜色，底色是红色
        DataHelper.writeData(outputSheet, record, false);
      }
    } catch (Exception e) {
      String error = "Error:" + e.getMessage();
      record.setActual(error);
      record.setComparision(TestCaseRecord.ComparisionType.ERROR.name());
      DataHelper.writeData(outputSheet, record, false);
      failedCase++;
      Assert.fail("Problem to assert Response and baseline messages: " + e.getMessage());
    }
}

  @AfterTest
  public void teardown() {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    endTime = sf.format(new Date());
    DataHelper.writeData(resultSheet, totalCase, failedCase, startTime, endTime);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(filePath);
      wb.write(fileOutputStream);
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
