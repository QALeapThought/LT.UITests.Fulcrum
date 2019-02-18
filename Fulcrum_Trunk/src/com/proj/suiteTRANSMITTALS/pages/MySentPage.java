package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants_Workflow;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.proj.util.CustomExceptions;

public class MySentPage extends TestSuiteBase{
	private static final String page="My Sent";
	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Sent Page	
	 * @param driver
	 * @param workflow
	 * @param data
	 * @return
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>data) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		subject=data.get("Subject");

		if(data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)){
			TxComplete_Status ="Closed";
			status ="Completed";			
		}
		/*	else if(data.get(Constants_Workflow.Fulcrum_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_ChangeNote)){
			TxComplete_Status   ="Sending...";
			status="Outstanding";
			subject=data.get("Tramsmittals-Subject");
		}
		else if (data.get(Constants_Workflow.Fulcrum_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_ConsultantAdvice)){
		 */
		else if((!data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE))&& data.get("Overdue").equalsIgnoreCase(Constants_FRMWRK.Yes)){
			status="Overdue";
			TxComplete_Status="Open";
			//subject=data.get("Subject");
		}	

		else if((!data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE))&& data.get("Action-Level2").equalsIgnoreCase("Forward")&& data.get("Subject").contains("FW: ")){
			TxComplete_Status   ="Open";
			status="Outstanding";
			//subject=data.get("Subject");
		}		

		else if (!data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)){
			TxComplete_Status ="Open";
			status  ="Outstanding";
			//subject=data.get("Subject");
		}


		Navigations_Fulcrum.Transmittals.navigateToMysent(driver,workflow);
		boolean subjectDisplay=TransmittalsGridUtil.waitUntilSubjectDisplays(driver, refID, testcaseName, page, workflow, subject,"My Sent");
		if(subjectDisplay==false)	{
			CustomExceptions.Exit(testcaseName, workflow+"My Sent - Subject", "Subject "+subject+" is not listed after suffiecient wait..");
		}
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,page, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,page, workflow, subject, status);
		return TxComplete_Status;
	}


}
