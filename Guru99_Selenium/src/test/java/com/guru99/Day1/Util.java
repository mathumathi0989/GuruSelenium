package com.guru99.Day1;
 
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 



public class Util {

	private static final String HSSFCell = null;
	public  static String  url = "http://demo.guru99.com";
	//public  static String   username = "mngr233053";
	//public  static String  password = "qapEqEh";
	 public static String etitle = "Guru99 Bank Manager HomePage";
    public static int waittime = 30;
    public static String FILE_PATH = "testData.xls";
    public static String FILE_NAME = "testData";
    public static String SHEET_NAME = "Data";
  public static String EXPECT_ERROR = "User or Password is not valid";
    public static void main(String... args) throws IOException {
    
    	readFromExcel("C:\\Users\\mathu\\eclipse-workspace\\guru99\\Guru99_Selenium\\src\\test\\java\\testData.xls");
    }
public static String[][] getdatafromExcel (String FilePath, String SheetName, String FileName){
	String [][] tabArray = null;
	//Workbook wb = Workbook.getWorkbook(new File(FilePath));
	
	// Workbook wb =  Workbook.;
	return null ;
	
}
public static void readFromExcel(String file) throws IOException{
    HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
    HSSFSheet myExcelSheet = myExcelBook.getSheet("Data");
    
    HSSFRow row = myExcelSheet.getRow(1);
    
    String userName = row.getCell(1).getStringCellValue();
    String password = row.getCell(2).getStringCellValue();
    
    System.out.println(userName + " - " + password);
  
    
    myExcelBook.close();
    
}

}
