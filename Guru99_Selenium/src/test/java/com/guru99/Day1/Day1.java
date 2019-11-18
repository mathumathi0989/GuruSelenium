package com.guru99.Day1;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Day1 {
    static WebDriver driver;
  static String baseURL;
  
    @BeforeClass
    public void tearup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = Util.url;
        driver.get(baseURL + "/V4/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Util.waittime, TimeUnit.SECONDS);
    }

    @Test
    public void login() {
    	String username;
    	String password;
    	String actualBoxtitle;
    	String [][] testData = Util.getdatafromExcel(Util.FILE_PATH, Util.SHEET_NAME, Util.SHEET_NAME);
    	for (int i = 0; i < testData.length; i++) {
    	    username = testData[i][0];
    	    password = testData[i][1]; 
    	    tearup();
    	    
    	    driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        
        driver.findElement(By.name("btnLogin")).submit();
        
        
        try
        {
        	Alert alt = driver.switchTo().alert();
			actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			if (actualBoxtitle.contains(Util.EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
			    System.out.println("Test case SS[" + i + "]: Passed"); 
			} else {
			    System.out.println("Test case SS[" + i + "]: Failed");
			}
        }
        catch (NoAlertPresentException e) {
        	
        String atitle = driver.getTitle();
        if (atitle.contains(Util.etitle)) {
        	   System.out.println("Test case SS[" + i + "]: Passed");
        				} else {
        				    System.out.println("Test case SS[" + i + "]: Failed");
        				}
        }
    	}
    	
    }
    
    @AfterClass
    public void teardown() {
        driver.quit();
    }
    
}