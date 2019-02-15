package com.proj.suiteTRANSMITTALS.workflows;

import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_Workflow;
import com.proj.base.TestBase;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.pages.MailDetailsPage;
import com.proj.suiteTRANSMITTALS.pages.MyInboxAndActionRequiredPage_Fulcrum;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.proj.suiteTRANSMITTALS.reusables.Users;
import com.proj.util.CustomExceptions;
import com.proj.utilFulcrum.ApplicationMethods;
import com.proj.utilFulcrum.LoginLib;

public class Workflow_reusables extends Workflows{

	protected static WebDriver ActionRequired_ApproveOrSubmit(String validationPage,WebDriver driver,String refid,String testcasename,String workflow_l2,String workflow_end,String browsername,Hashtable<String,String>transmittalData,Hashtable<String,String>testData,LinkedHashMap<String,String>actionStatusData,String userloginName) throws Throwable{
		String actionRecordsCount_before="";
		String actionRecordsCount_after="";
		
		LoginLib.logOutFromApplicationAndcloseBrowser(driver, refid, testcasename);
		driver=LoginLib.launchBrowserAndlogIntoApplication(browsername, testcasename, TestBase.login_userName,TestBase.login_password, refid);

		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionRequired)){
			actionRecordsCount_before=Navigations_Fulcrum.navigateToActionRequiredAndGetCount(driver, refid, testcasename, workflow_l2);	
		}
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionsOverdue)){
			actionRecordsCount_before=Navigations_Fulcrum.navigateToActionOverAndGetCount(driver, refid, testcasename, workflow_l2);
		}


		getResult=MyInboxAndActionRequiredPage_Fulcrum.validate_TxComplete_StatusAndStatus(driver,validationPage, workflow_l2+"Before Submission||", transmittalData,testData,refid,testcasename,actionStatusData);

		Transmittals_EntryPage.verifyAttachedFiles(driver, testcasename, refid, workflow_l2, testData);


		Transmittals_EntryPage.clickCompleteAction(driver, workflow_l2);
		String action=testData.get("Action-Level2");
		if(testData.get("Action-Level2").contains("Forward")||testData.get("Action-Level2").contains("Reply")){
			action=testData.get("Action-Level3");
		}else{
			testData.get("Action-Level2");
		}
		Transmittals_EntryPage.editAndSubmitTransmittalRecord(driver, refid, testcasename, workflow_l2, transmittalData,action,userloginName,actionStatusData);
		Workflows.validateRecordinActionRequiredPageAfterSubmission(driver, testcasename,validationPage, workflow_l2, transmittalData.get("Subject"));

		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionRequired)){
			actionRecordsCount_after=Navigations_Fulcrum.navigateToActionRequiredAndGetCount(driver, refID, testcaseName, workflow_l2);
			MyInboxAndActionRequiredPage_Fulcrum.validateActionRequiredCount(driver, refid, testcasename, workflow_l2,Constants_Workflow.page_actionRequired, actionRecordsCount_before, actionRecordsCount_after);
		}
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionsOverdue)){
			actionRecordsCount_after=Navigations_Fulcrum.navigateToActionOverAndGetCount(driver, refID, testcaseName, workflow_l2);
			MyInboxAndActionRequiredPage_Fulcrum.validateActionRequiredCount(driver, refid, testcasename, workflow_l2,Constants_Workflow.page_actionsOverdue, actionRecordsCount_before, actionRecordsCount_after);
		}

		getResult=MyInboxAndActionRequiredPage_Fulcrum.validate_TxComplete_StatusAndStatus(driver,Constants_Workflow.page_myInbox, workflow_l2, transmittalData,testData.get("Action-Level2"));
		if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, workflow_l2+"- Failure", "Unable to continue the test due to above error ");
		}
		transmittalData.put("Mail Status", getResult);
		MailDetailsPage.Validate_MailDetails(driver,Constants_Workflow.page_myInbox, refid, testcasename, workflow_l2, testData, transmittalData);
		ApplicationMethods.closeAllDialogs(driver, refid, testcasename);		
		
		return driver;
	}


	protected static WebDriver RejectOrDecline(WebDriver driver,String refid,String testcasename,String workflow_l2,String workflow_end,String browsername,Hashtable<String,String>transmittalData,Hashtable<String,String>testData,LinkedHashMap<String,String>actionStatusData,String userloginName) throws Throwable{

		LoginLib.logOutFromApplicationAndcloseBrowser(driver, refid, testcasename);
		driver=LoginLib.launchBrowserAndlogIntoApplication(browsername, testcasename, TestBase.login_userName,TestBase.login_password, refid);

		getResult=MyInboxAndActionRequiredPage_Fulcrum.validate_TxComplete_StatusAndStatus(driver, Constants_Workflow.page_myInbox, workflow_l2, transmittalData, testData, refid, testcasename, actionStatusData);
		if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, workflow_l2+"- Failure", "Unable to continue the test due to above error ");
		}


		TransmittalsGridUtil.searchSubjectAndOpenRecord(driver,Constants_Workflow.page_myInbox, workflow_l2, transmittalData.get("Subject"));
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		Transmittals_EntryPage.validateActionStatus(driver, refid, testcasename, workflow_l2+"After Submission||", actionStatusData);			
		ApplicationMethods.closeAllDialogs(driver, refid, testcasename);

		return driver;

	}


	protected static LinkedHashMap<WebDriver,Hashtable<String,String>> forwardAMail(WebDriver driver,String refid,String testcasename,String workflow_l2,String workflow_end,String browsername,Hashtable<String,String>transmittalData,Hashtable<String,String>testData,LinkedHashMap<String,String>actionStatusData,String userloginName) throws Throwable{
		LinkedHashMap<WebDriver,Hashtable<String,String>>forwardedData=new LinkedHashMap<WebDriver,Hashtable<String,String>>();
		LoginLib.logOutFromApplicationAndcloseBrowser(driver, refid, testcasename);
		driver=LoginLib.launchBrowserAndlogIntoApplication(browsername, testcasename, TestBase.login_userName,TestBase.login_password, refid);

		getResult=MyInboxAndActionRequiredPage_Fulcrum.validate_TxComplete_StatusAndStatus(driver, Constants_Workflow.page_myInbox, workflow_l2, transmittalData, testData, refid, testcasename, actionStatusData);
		if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, workflow_l2+"- Failure", "Unable to continue the test due to above error ");
		}

		Transmittals_EntryPage.verifyAttachedFiles(driver, testcasename, refid, workflow_l2, testData);

		transmittalData=Transmittals_EntryPage.forwardAndSendTransmittalRecord(driver,refid,testcasename, workflow_l2, testData,transmittalData);
		forwardedData.put(driver, transmittalData);
		
		return forwardedData;
	}
	
	
	protected static void SubmitAnActionOrValidateAAlreadySubmittedAction(WebDriver driver,String refid,String testcasename,String browsername,String workflow_l2,String workflow_end,Hashtable<String,String>transmittalData,Hashtable<String,String> testData,LinkedHashMap<String,String>actionStatusData,String validationPage,int userIteration) throws Throwable{
		String data_key_TO;
		String data_key_Action_Level;
		String data_key_Action_Level_User_Counter;
		
		if(testData.get("Action-Level2").equalsIgnoreCase("Forward")){
			//data_key_TO="Tramsmittals-ForwardReplyTo";
			data_key_TO="To";
			data_key_Action_Level="Action-Level3";
			data_key_Action_Level_User_Counter=Constants_Workflow.reciever_counter_key;
		}else{
			data_key_TO="To";
			data_key_Action_Level="Action-Level2";
			data_key_Action_Level_User_Counter=Constants_Workflow.reciever_counter_key;
		}
		
		
		
		//multi user -approve/accept/reject/decline/submit from Action required.
		if(transmittalData.get(data_key_TO).contains(Constants.delimiter_data)){
			String[] recipients=commonMethods.splitString(transmittalData.get(data_key_TO), Constants.delimiter_data);
			int userCount=1;
			for (String recp:recipients){
				transmittalData.put(data_key_Action_Level_User_Counter, String.valueOf(userCount));
				Users.setLoginUser(recp);
				//action =reject or decline and not first recipient 
				if((testData.get(data_key_Action_Level).equalsIgnoreCase("Rejected")||testData.get(data_key_Action_Level).equalsIgnoreCase("Declined"))&& !transmittalData.get(data_key_Action_Level_User_Counter).equalsIgnoreCase(String.valueOf(1))){
					driver=Workflow_reusables.RejectOrDecline(driver, refid, testcasename, workflow_l2, workflow_end, browsername, transmittalData, testData, Transmittals_EntryPage.recipientListwithActionStatus, recp);
				}		
				else{
					driver=Workflow_reusables.ActionRequired_ApproveOrSubmit(validationPage, driver, refid, testcasename, workflow_l2, workflow_end, browsername, transmittalData, testData, actionStatusData, recp);
				}
				
				userCount=userCount+1;
			}
			
			MailDetailsPage.verify_MailedDocuments_ApprovalStatus(driver,testcasename, workflow_l2, testData, transmittalData);

		}
		//single user- approve/accept/reject/decline/submit from Action required.
		else{
			transmittalData.put(data_key_Action_Level_User_Counter, String.valueOf(userIteration));
			String userloginName=transmittalData.get(data_key_TO);
			Users.setLoginUser(userloginName);
			driver=Workflow_reusables.ActionRequired_ApproveOrSubmit(validationPage, driver, refid, testcasename, workflow_l2, workflow_end, browsername, transmittalData, testData, actionStatusData, userloginName);
			MailDetailsPage.verify_MailedDocuments_ApprovalStatus(driver,testcasename, workflow_l2, testData, transmittalData);
		}	
	}
}
