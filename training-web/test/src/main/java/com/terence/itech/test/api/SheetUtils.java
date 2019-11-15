package com.terence.itech.test.api;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author Dell
 */
public class SheetUtils {
  public static void removeSheetByName(XSSFWorkbook wb, String sheetName){
    XSSFSheet sheet=wb.getSheet(sheetName);
    if(null==sheet){
      return ;
    }
    wb.removeSheetAt(wb.getSheetIndex(sheet));
  }
}
