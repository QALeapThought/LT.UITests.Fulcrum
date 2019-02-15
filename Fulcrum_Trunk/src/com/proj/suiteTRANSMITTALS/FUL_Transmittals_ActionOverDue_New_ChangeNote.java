package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.Constants.Constants_Workflow;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.suiteTRANSMITTALS.workflows.Workflows;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;


public class FUL_Transmittals_ActionOverDue_New_ChangeNote extends TestSuiteBase{

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();
	private static String workflow_l1="Level-1:-Initiation of Transmittal";
	private static String workflow_l2="Level-2:-Recieve Transmittal ";
	private static String workflow_end=" || ";



	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFUL_Transmittals_ActionOverDue_New_ChangeNote(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");



		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String siteName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
			String condition="";
			condition=" ["+data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition)+"]";

			//************************************** LEVEL 1 *****************************************************************************
			String workflow_Level1=workflow_l1+condition+workflow_end;		

			transmittalData=Workflows.Level1_Initaite_Transmittal(driver_TRANS, Constants_ConfigProperties.testSiteName, workflow_Level1, data);

			//************************************** LEVEL 2 *****************************************************************************		
			driver_TRANS=Workflows.Level2_Validate_OR_Submit_OR_ApproveOrReject_OR_Forward_OR_ReplyAll_Transmittal(siteName,Constants_Workflow.page_actionsOverdue,driver_TRANS,refID,testcaseName, workflow_l2, condition, workflow_end, Constants_ConfigProperties.testSiteName, browserName, Constants_ConfigProperties.username_AutoTestAdmin, Constants_ConfigProperties.password_AutoTestAdmin, transmittalData, data,Transmittals_EntryPage.getRecieverUserCount(data));

			
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}
}
