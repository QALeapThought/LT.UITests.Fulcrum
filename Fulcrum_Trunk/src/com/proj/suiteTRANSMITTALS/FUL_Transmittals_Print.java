package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.util.CustomExceptions;
import com.proj.util.Dialogs;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ToolbarsUtil;


public class FUL_Transmittals_Print extends TestSuiteBase{

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();
	private static String workflow_print="Verify Print in Transmittals ";
	


	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFUL_Transmittals_Print(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");



		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			
			Navigations_Fulcrum.Transmittals.navigateToMyinbox(driver_TRANS,workflow_print);
			commonMethods.pageLoadWait(driver_TRANS);
			ToolbarsUtil.clickList(driver_TRANS, workflow_print);
			ToolbarsUtil.List.clickPrint(driver_TRANS, workflow_print);
			commonMethods.pageLoadWait(driver_TRANS);
			WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
			//commonMethods.getWindow(driver_TRANS, refID, testcaseName, workflow_print, "Print");
			//Dialogs.checkDefaultDialog(driver_TRANS, "Print", refID, testcaseName, workflow_print, "Cancel");
			Dialogs.checkDefaultDialog(driver_TRANS, browserName, "Mail - My Inbox", "Print", refID, testcaseName, workflow_print, "Cancel");
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}
}
