package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;


public class FUL_Transmittals_HelpLink extends TestSuiteBase{

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();
	private static String workflow_l1="Level-1:-Initiation of Transmittal";
	private static String workflow_help="Verify Help Link in New Transmittal form ";
	private static String workflow_end=" || ";



	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFUL_Transmittals_HelpLink(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");



		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String condition="";
			condition=" ["+data.get("TxType")+"]";

			workflow_l1=workflow_l1+condition+workflow_end;
			
			Navigations_Fulcrum.Transmittals.navigateToNewTransmittal(driver_TRANS,workflow_help);
			commonMethods.pageLoadWait(driver_TRANS);
			Transmittals_EntryPage.checkTransmittalHelpEnabled(driver_TRANS, refID, testcaseName, workflow_help);
			Transmittals_EntryPage.clickTransmittalHelpLink(driver_TRANS, workflow_help, refID, testcaseName);
			Transmittals_EntryPage.validate_TransmittalhelpWindow(driver_TRANS,refID,testcaseName,workflow_help);
						
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}
}
