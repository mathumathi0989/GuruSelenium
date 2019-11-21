package com.guru99.Day1;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 



public class Util {
	public  static String  url = "http://demo.guru99.com";
	public static String etitle = "Guru99 Bank Manager HomePage";
    public static int waittime = 30;
    public static String PATH = System.getProperty("user.dir");
    public static String FILE_PATH = "C:\\Users\\mathu\\git\\GuruSelenium\\Guru99_Selenium\\src\\test\\java\\testData.xls";
    public static String EXPECT_ERROR = "User or Password is not valid";
    public static Workbook book;
    public static Sheet sheet;
  
public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
	FileInputStream file = null;
	try {
		file = new FileInputStream(FILE_PATH);
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		book =  WorkbookFactory.create(file);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	sheet = book.getSheet(sheetName);
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	for (int i = 0; i<sheet.getLastRowNum(); i++) {
		for (int k = 0; k <sheet.getRow(0).getLastCellNum(); k++) {
			data[i][k] = sheet.getRow(i+1).getCell(k).toString();
		}
	}
	return data;


}
}
