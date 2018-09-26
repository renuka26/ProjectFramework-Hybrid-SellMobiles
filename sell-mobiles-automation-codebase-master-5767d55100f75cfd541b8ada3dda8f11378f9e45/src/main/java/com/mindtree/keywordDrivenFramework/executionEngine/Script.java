package com.mindtree.keywordDrivenFramework.executionEngine;
import com.mindtree.keywordDrivenFramework.config.ActionKeywords;
import com.mindtree.keywordDrivenFramework.utility.EmailUtility;
import com.mindtree.keywordDrivenFramework.utility.ExcelUtils;
import com.mindtree.keywordDrivenFramework.utility.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
public class Script extends JPanel{
public static ActionKeywords actionKeywords;
public static String sActionKeyword,sPageObject;
public static Method method[];
public static boolean bResult;
public static FileInputStream fileInput = null;
public static Properties prop;
public static int iTestStep;
public static int iTestLastStep;
public static String sTestCaseID;
public static String sRunMode;
public static String testcase = "Test Cases";
public static String teststep = "Test Steps";
public static ExtentReports extent;
public static ExtentTest test;
public static ExtentTestInterruptedException testexception;
	


	
	@Test
	public void run() throws Exception{
		
		
		DOMConfigurator.configure("log4j.xml");
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();		
		File file = new File(".//src/main/java/com/mindtree/keywordDrivenFramework/config/config.properties");
		try{
				fileInput = new FileInputStream(file);
			}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			 try{
				 prop = new Properties();
				 prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	//Extent report
			    
	extent = new ExtentReports(prop.getProperty("extentreportpath"), true);
	extent.loadConfig(new File(prop.getProperty("extentreportconfig")));
	extent.addSystemInfo("Environment","QA");
			 
	String sPath = prop.getProperty("testsheetspath");
	ExcelUtils.setExcelFile(sPath);
	Script script = new Script();
	script.execute_TestCase();
	extent.flush();
	extent.close();	
	}
	
	@AfterSuite
	public void sendMail() throws EmailException, MalformedURLException {
		EmailUtility.sendMail();
		System.out.println("======== mail sent successfuy ======");
	}
	
	private void execute_TestCase() throws Exception {
	    	int iTotalTestCases = ExcelUtils.getRowCount(testcase);
			for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
				bResult = true;
				sTestCaseID = ExcelUtils.getCellData(iTestcase,0,testcase); 
				sRunMode = ExcelUtils.getCellData(iTestcase,2,testcase);
				if(sRunMode.equals("Yes")){
			test = extent.startTest("TEST = " + iTestcase);
					test.assignAuthor("Onkar");
					test.assignCategory("Environment","QA");
					iTestStep = ExcelUtils.getRowContains(sTestCaseID, 0 ,teststep);
					iTestLastStep = ExcelUtils.getTestStepsCount(teststep, sTestCaseID, iTestStep);
					Log.startTestCase(sTestCaseID);
					test.log(LogStatus.INFO, "Test started" + sTestCaseID);
					bResult=true;
					for(;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelUtils.getCellData(iTestStep, 3 ,teststep);
			    		sPageObject = ExcelUtils.getCellData(iTestStep,2, teststep);
			    		execute_Actions();
							if(bResult==false){
								ExcelUtils.setCellData("FAIL",iTestcase,3,testcase);
								Log.endTestCase(sTestCaseID);
								test.log(LogStatus.FAIL, "Test Failed" + sTestCaseID);
								extent.endTest(test);
								break;
							}

						}	
					if(bResult==true){
					ExcelUtils.setCellData("PASS",iTestcase,3,testcase);
					Log.endTestCase(sTestCaseID);
					test.log(LogStatus.PASS, "Test Passed" + sTestCaseID);
					extent.endTest(test);
						}
					}
				}
 		}		
	

	   private static void execute_Actions() throws Exception {
		   for(int i=0;i<method.length;i++){
			   if(method[i].getName().equals(sActionKeyword)){
					method[i].invoke(actionKeywords,sPageObject);
					if(bResult==true){
						ExcelUtils.setCellData("PASS", iTestStep,4,teststep);
					//	EmailUtility.sendMail();
					//	System.out.println("mail sent pased");
						break;
					}else{
						ExcelUtils.setCellData("FAIL", iTestStep, 4 , teststep);
						actionKeywords.closeBrowser("");
					//	EmailUtility.sendMail();
					//	System.out.println("mail sent failed");
						
						break;
						}
					}
				}
	     }		
		        
	}
	


