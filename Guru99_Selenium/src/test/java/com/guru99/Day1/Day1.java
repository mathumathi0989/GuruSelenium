package com.guru99.Day1;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Day1 {
    static WebDriver driver;
  static String baseURL;
  
    @BeforeMethod
    public void tearup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseURL = Util.url;
        driver.get(baseURL + "/V4/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Util.waittime, TimeUnit.SECONDS);
    }

    @DataProvider
    public Object[][] getdata() throws InvalidFormatException {
    Object data[][] = 	Util.getTestData("Data");
    return data;
    }
    @Test(dataProvider = "getdata")
    public void login(String username, String password) {
    	String actualBoxtitle;
          	    
       	 driver.findElement(By.name("uid")).clear();
           driver.findElement(By.name("uid")).sendKeys(username);
           driver.findElement(By.name("password")).clear();
           driver.findElement(By.name("password")).sendKeys(password);
           driver.findElement(By.name("btnLogin")).submit();
           try
           { 
           Alert alt = driver.switchTo().alert();
        	actualBoxtitle = alt.getText();
        	alt.accept();
        	if (actualBoxtitle.contains(Util.EXPECT_ERROR)) { 
        		System.out.println("Test case : Passed"); 
        	} else {
        		System.out.println("Test case: Failed");
        	}
        }
        catch (NoAlertPresentException e) {
        	String atitle = driver.getTitle();
        	if (atitle.contains(Util.etitle)) {
        		System.out.println("Test case SS: Passed");
        		 String amgr = driver.findElement(By.xpath("(//*[@class='heading3'])[2]/td")).getText();
        		  System.out.println(amgr);
        	} else {
        		System.out.println("Test case SS: Failed");
        		  String amgr = driver.findElement(By.xpath("(//*[@class='heading3'])[2]/td")).getText();
        		  System.out.println(amgr);
        	}
    }
           catch (Exception e) {
        	 
           }
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    }