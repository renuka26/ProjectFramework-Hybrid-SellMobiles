package com.mindtree.keywordDrivenFramework.config;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.iTestStep;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.teststep;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.sTestCaseID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mindtree.keywordDrivenFramework.executionEngine.Script;
import com.mindtree.keywordDrivenFramework.utility.ExcelUtils;
import com.mindtree.keywordDrivenFramework.utility.Log;
import com.mindtree.keywordDrivenFramework.utility.ScreenshotUtility;
import com.relevantcodes.extentreports.LogStatus;
import static com.mindtree.keywordDrivenFramework.executionEngine.Script.test;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import  static com.mindtree.keywordDrivenFramework.executionEngine.Script.prop;
	public class ActionKeywords {
	public static  WebDriver driver;	 
	public static  void openBrowser(String object){
		try {
			Log.info("opening chrome browser");
			String  path = prop.getProperty("chromepath");
			System.setProperty("webdriver.chrome.driver",path);
			driver = new ChromeDriver();
			test.log(LogStatus.PASS,"opening browser"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_openBrowser_pass_"+sTestCaseID+iTestStep+".png")));
			}catch (Exception e) {
			Log.info("Not able to open Browser --- " + e.getMessage());
			try{
				test.log(LogStatus.FAIL,"Not able to open Browser "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_openBrowser_fail_"+sTestCaseID+iTestStep+".png")));
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
		}
	
	
	
	public static void openurl(String object) throws IOException
	{
		try{
				driver.get(object);
				test.log(LogStatus.PASS,"opening url "+ object + test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_url_pass_"+sTestCaseID+iTestStep+".png")));
			}catch (Exception e) {
			 Log.info("Not able to open"+ object + e.getMessage());
			 try {
				test.log(LogStatus.FAIL,"Not able to open "+ object + test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_url_fail_"+sTestCaseID+iTestStep+".png")));
			 	}catch (IOException e1) {
				e1.printStackTrace();
			 	}
			Script.bResult = false;
		}
	}
	
	public  static  void waitFor(String object) throws Exception{
		Thread.sleep(5000);
		}
 
	public  static  void maxwindow(String object) throws Exception{
		driver.manage().window().maximize();
		}
 
	
	
	public  static   void sendkeys(String object){
		try {
			
			String data = ExcelUtils.getCellData(iTestStep,5, teststep);
			Log.info("Entering "+ data +" in text box");
			driver.findElement(By.xpath(object)).sendKeys(data);
			 test.log(LogStatus.PASS,"Entering "+ data +" in text box "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+sTestCaseID+iTestStep+"sendkeys_pass.png")));
		
			}catch (Exception e) {
			Log.info("not able to enter data in text box " + e.getMessage());
			try {
				test.log(LogStatus.PASS,"not able to enter data in text box"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+sTestCaseID+iTestStep+"sendkeys_fail.png")));			
				} catch (IOException e1){
				e1.printStackTrace();
			}
			 
			Script.bResult = false;
		}
		}
 
	public  static  void click(String object){
		try {
			driver.findElement(By.xpath(object)).click();
			Log.info("clicking on "+ object);
			 test.log(LogStatus.PASS,"clicking on "+ object +test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+sTestCaseID+iTestStep+"_click_pass.png")));
			
			}catch (Exception e) {
			Log.info("Not able to click on "+ object + e.getMessage());
		try{
				 test.log(LogStatus.FAIL,"not able to click on "+ object +test.addScreenCapture("Screenshot_"+sTestCaseID+iTestStep+"_click_fail.png"));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
		}
	
	public  static  void checktext(String object){
		try {
			
			String  actual = driver.findElement(By.xpath(object)).getText();
			String expected = ExcelUtils.getCellData(iTestStep,5, teststep);
			if(actual.equals(expected))
			{
				Log.info("checking text "+ object);
				test.log(LogStatus.PASS,"Text Matched "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+sTestCaseID+iTestStep+"_click_pass.png")));
			}
			else
			{
				Log.info("Text not matching.");
				try{
					 test.log(LogStatus.FAIL,"Text not Matching"+test.addScreenCapture("Screenshot_"+sTestCaseID+iTestStep+"_click_fail.png"));
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				Script.bResult = false;
			}
			
			}catch (Exception e) {
			Log.info("Text not Matching" + e.getMessage());
			try{
				 test.log(LogStatus.FAIL,"Text not Matching"+test.addScreenCapture("Screenshot_"+sTestCaseID+iTestStep+"_click_fail.png"));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
		}
	
	
	
	
	
	
	
	public  static  void checkcount(String object){
		try {
			
			 
			 List<WebElement> list = driver.findElements(By.xpath(object));
			 int n = list.size();
//			 String data = ExcelUtils.getCellData(iTestStep,5, teststep);
//			 int actual = Integer.parseInt(data);
			 if(n == 6 || n==7)
			 {
				 Log.info("counting  "+ object);
				 test.log(LogStatus.PASS,"countig "+ object +test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_"+sTestCaseID+iTestStep+"_click_pass.png")));
			 }
			 else
			 {
				 Log.info("number of elements is not matching ");
				 test.log(LogStatus.FAIL,"number of elements is not matching "+test.addScreenCapture("Screenshot_"+sTestCaseID+iTestStep+"_click_fail.png"));
				 Script.bResult = false;
			 }
		
		}catch (Exception e) {
			Log.info("Not able to check number of elements" + e.getMessage());
		try{
				 test.log(LogStatus.FAIL,"Not able to check number of elements "+test.addScreenCapture("Screenshot_"+sTestCaseID+iTestStep+"_click_fail.png"));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			Script.bResult = false;
		}
		}
	
	
//	public  static void clickable(String object){
//		try {
//			Log.info("Button is clickable");
//			driver.findElement(By.xpath(object)).click();
//			 test.log(LogStatus.PASS,"Checking clickacle or not "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_clickable_pass_"+sTestCaseID+iTestStep+".png")));
//			 driver.close();
//		} catch (Exception e) {
//			Log.info("Not clickable " + e.getMessage());
//			 try {
//				test.log(LogStatus.FAIL,"Element is not clickable "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_clickable_fail_"+sTestCaseID+iTestStep+".png")));
//			} catch (IOException e1){
//				e1.printStackTrace();
//			}
//
//			Script.bResult = false;
//		}
//	}
	
	
	
	public  static void closeBrowser(String object){
			try {
				Log.info("closing browser");
				 test.log(LogStatus.PASS,"Closing browser"+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_closebrowser_pass_"+sTestCaseID+iTestStep+".png")));
				 driver.close();
			} catch (Exception e) {
				Log.info("Not able to close " + e.getMessage());
				 try {
					test.log(LogStatus.INFO,"Not able to close browser "+test.addScreenCapture(ScreenshotUtility.getScreenshot("Screenshot_closebrowser_fail_"+sTestCaseID+iTestStep+".png")));
				} catch (IOException e1){
					e1.printStackTrace();
				}

				Script.bResult = false;
			}
		}
	
	
	
	
	}