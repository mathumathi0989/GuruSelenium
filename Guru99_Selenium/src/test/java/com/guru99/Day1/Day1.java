package com.guru99.Day1;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
    @DataProvider(name = "getdata")
    public Object[][] getdata() throws InvalidFormatException {
    Object data[][] = 	Util.getTestData("Data");
    return data;
    }
    @DataProvider(name = "datahere")
    public Object[][] testData() {
		Object[][] data = new Object[4][2];
		data[0][0] = Util.USER_NAME;
		data[0][1] = Util.PASSWD;
		data[1][0] = "invalid";
		data[1][1] = "invalid";
		data[2][0] = "valid";
		data[2][1] = "invalid";
		data[3][0] = "invalid";
		data[3][1] = "valid";
		return data;
	}
    @Test(dataProvider = "getdata")
    public void login(String username, String password) throws Exception {
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
        	assertEquals(actualBoxtitle,Util.EXPECT_ERROR);
        }
        catch (NoAlertPresentException e) {
        	 String amgr = driver.findElement(By.xpath("(//*[@class='heading3'])[2]/td")).getText();
        	 String[] parts = amgr.split(Util.PATTERN);
				String dynamicText = parts[1];
				assertTrue(dynamicText.substring(1, 5).equals(Util.FIRST_PATTERN));
				String remain = dynamicText.substring(dynamicText.length() - 4);
				assertTrue(remain.matches(Util.SECOND_PATTERN));
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("C:/screenshot/screenshot.png"));		
    }         
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    }