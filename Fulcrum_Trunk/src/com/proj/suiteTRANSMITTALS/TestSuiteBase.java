package com.proj.suiteTRANSMITTALS;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.base.TestBase;
import com.proj.library.Driver;
import com.proj.library.LocalDriverManager;
import com.proj.library.commonMethods;
import com.proj.listener.TestsListenerAdapter;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.LoginLib;
import com.report.reporter.Reporting;


public class TestSuiteBase extends TestBase {

	public static WebDriver driver_TRANS=null;
	static boolean isBeforeMethodPass_trans=Constants_FRMWRK.TrueB;

	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		moduleName=Constants.Module_TRANS;
		currentSuite_Sheetname=moduleName+" Suite";
		current_suiteCounter++;

		initialize();
		String SuiteFilePath=htmlresultsFileLoc+moduleName+" Suite"+".html";

		initializeReports(SuiteFilePath);

		logsObj.log("the run mode of Suite "+ Constants.SUITE_TRANS_SUITENAME+" is yes..");

		browserName=Constants_ConfigProperties.browserType_TRANS;


	}

	@AfterSuite(alwaysRun = true)  

	public void aftSuite() throws Throwable{
		try{
			Reporting.closeTagsForHTMLReportingEmail(TestBase.currentSuite_bfw, System.getProperty("user.dir")+"//Results", TestBase.CONFIG.getProperty("publishedResultsLocation"), TestBase.CONFIG.getProperty("emailFrom"), TestBase.CONFIG.getProperty("emailUser"), TestBase.CONFIG.getProperty("emailPassword"), TestBase.CONFIG.getProperty("emailReceipients"), TestBase.CONFIG.getProperty("emailSubject"), TestBase.CONFIG.getProperty("emailMessage"));
		}catch(Exception ex){
			System.out.println("HTML CLOSURE..");
			ex.printStackTrace();
		}
		
		Driver.close(driver_TRANS,browserName);
		driver_TRANS=null;		
	}

	@BeforeMethod
	public static void befMethod() throws Throwable{
		try {
			
			driver_TRANS=LoginLib.launchBrowserAndlogIntoApplication(browserName, testcaseName, TestBase.login_userName, TestBase.login_password);
			//driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName,  TestBase.login_userName, TestBase.login_password);
			//driver_Prolens=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.corporateSiteName,  proLens_login_userName, proLens_login_password);
			
			logsObj.log("Before method success for "+testcaseName);
			isBeforeMethodPass_trans=Constants_FRMWRK.TrueB;


		} catch (Throwable t) {
			isTestPass=Constants_FRMWRK.FalseB;
			logsObj.log("Not able to login into the application due to error "+t+" hence cannot continue execution of "+testcaseName);
			try{
				CustomExceptions.Exit(testcaseName, "Log into the application", "Not able to login into the application due to error "+t+" hence cannot continue execution of "+testcaseName);
			} catch (Exception e) {
				isTestPass=Constants_FRMWRK.FalseB;
				isBeforeMethodPass_trans=Constants_FRMWRK.FalseB;
			}
		}
	}

	@AfterMethod
	public static void aftMethod(){
		logsObj.log("Aft method "+testcaseName);
		try {
			if (!isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				LoginLib.logOutFromApplicationAndcloseBrowser(LocalDriverManager.getDriver(),refID,testcaseName);
				TestExecutionUtil.resultTest(isTestPass,suiteTRNSxls, scenarioName,testcaseName);
				logsObj.log(" after test of "+testcaseName+"-AfterTest successful");			
			}
			else {
				TestExecutionUtil.resultTest(isTestPass,suiteTRNSxls, scenarioName,testcaseName);
				commonMethods.cleanProcess(LocalDriverManager.getDriver(), browserName);
			}
		} catch (Throwable t) {
			commonMethods.cleanProcess(LocalDriverManager.getDriver(), browserName);
			TestExecutionUtil.resultTest(isTestPass,suiteTRNSxls, scenarioName,testcaseName);
			logsObj.log("Not able to logout to the application due to error "+t+" hence cannot continue execution of "+testcaseName);

		}
	}
	
	@AfterTest
	public static void aftTest(){
		logsObj.log("Aft Test "+scenarioName);
		TestExecutionUtil.resultScenario(TestsListenerAdapter.isScenarionPass, suiteTRNSxls, scenarioName);
	}
}
