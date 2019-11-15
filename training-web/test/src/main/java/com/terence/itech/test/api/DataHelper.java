package com.terence.itech.test.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that read data from XSSF sheet
 * @author Dell
 */
class DataHelper {

  static List<TestCaseRecord> readFromSheet(XSSFSheet sheet) {
    List<String> headers = new ArrayList<>();
    XSSFRow myRow;
    List<TestCaseRecord> recordList = new ArrayList<>();
    myRow = sheet.getRow(0);
    for (Cell cell : myRow) {
      headers.add(cell.getStringCellValue());
    }
    int size = 1;
    for (; (myRow = sheet.getRow(size)) != null; size++) {
      TestCaseRecord record=initRestCase(headers,myRow);
      String url="http://"+record.getHost()+":"+record.getPort()+record.getContext()+record.getUrl();
      record.setRequestUrl(url);
      recordList.add(record);
    }
    return recordList;
  }

  private static String getSheetCellValue(XSSFCell cell) {
    String value = "";
    try {
      cell.setCellType(Cell.CELL_TYPE_STRING);
      value = cell.getStringCellValue();
    } catch (NullPointerException npe) {
      return "";
    }
    return value;
  }

  private static TestCaseRecord initRestCase(List<String> headers,XSSFRow row) {
    TestCaseRecord record = new TestCaseRecord();
    try {
      for (int col = 0; col < headers.size(); col++) {
        initEntity(headers.get(col), getSheetCellValue(row.getCell(col)), record);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return record;
  }

  /**
   * 利用反射，找对应对列名
   *@date 2019/11/15 15:49
   */
  private static void initEntity(String colName, String value, TestCaseRecord record) throws IllegalAccessException {
    Field[] fields = record.getClass().getDeclaredFields();
    for (Field field : fields) {
      NickMapper annotation = field.getDeclaredAnnotation(NickMapper.class);
      if (annotation.name().equals(colName)) {
        field.setAccessible(true);
        field.set(record, value);
        field.setAccessible(false);
        break;
      }
    }
  }

  static void writeData(XSSFSheet sheet, TestCaseRecord record, boolean pass){
    // 写入表头
    int rowNum=sheet.getLastRowNum();
    XSSFRow header = null;
    if(rowNum<1){
      header=sheet.createRow(rowNum);
    }
    XSSFRow row=sheet.createRow(rowNum+1);
    Field[]  fields=record.getClass().getDeclaredFields();
    int col=0;
    try{
      for(Field field:fields){
        field.setAccessible(true);
        if(rowNum<1){
          header.createCell(col).setCellValue(field.getDeclaredAnnotation(NickMapper.class).desc());
        }
        row.createCell(col).setCellValue(field.get(record).toString());
        field.setAccessible(false);
        col++;
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  static void writeData(XSSFSheet sheet, int totalCase, int failedCase, String startTime, String endTime){

  }

  /**
   *自定义结果比较规则
   *@author Mac
   */
  public static boolean compare(String expectedResult,String actualResult){
    JSONObject expected= JSON.parseObject(expectedResult);
    JSONObject actual= JSON.parseObject(actualResult);
    if(null==expected.get("type")||null==expected.get("code")){
      return false;
    }
    if (expected.get("type").equals(actual.get("type"))&&expected.get("code").equals(actual.get("code"))){
      return true;
    }else{
      return false;
    }
  }
}