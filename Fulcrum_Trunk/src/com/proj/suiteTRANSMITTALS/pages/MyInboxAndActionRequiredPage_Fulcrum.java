package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.report.reporter.Reporting;

public class MyInboxAndActionRequiredPage_Fulcrum extends TestSuiteBase{



	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(Before Submission)
	 * @author shaikka
	 * @param driver
	 * @param workflow
	 * @param returnData
	 * @param data
	 * @return
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>transmittaldata,Hashtable<String,String>data,String refid,String testcasename,LinkedHashMap<String,String>actionStatusData) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		//None
		if(transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)){
			status="Completed";
			TxComplete_Status="Closed";
			subject=transmittaldata.get("Subject");
		}
		//OverDue
		else if(!transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE) && (transmittaldata.get("Overdue").equalsIgnoreCase(Constants_FRMWRK.Yes))){
			status="Overdue";
			TxComplete_Status="Open";
			subject=transmittaldata.get("Subject");
		}
		//else if(returnData.get(Constants_Workflow.Fulcrum_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_Corresponce)|| returnData.get(Constants_Workflow.Fulcrum_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_ConsultantAdvice)||returnData.get(Constants_Workflow.Fulcrum_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_ChangeNote)){
		//Response Req not None
		else if(!transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)){	
			if(data.get("To").contains(Constants.delimiter_data)&& !transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)&& data.get("Action-Level2").equalsIgnoreCase("Rejected")){
				status="Rejected";
				TxComplete_Status="Closed";
				subject=transmittaldata.get("Subject");
			}
			else if(data.get("To").contains(Constants.delimiter_data)&& !transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)&& data.get("Action-Level2").equalsIgnoreCase("Declined")){
				status="Declined";
				TxComplete_Status="Closed";
				subject=transmittaldata.get("Subject");
			}

			else{
				status="Outstanding";
				TxComplete_Status="Open";
				subject=transmittaldata.get("Subject");	
			}

		}
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_Fulcrum.Transmittals.navigateToMyinbox(driver,workflow);
		}
		else if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionRequired))
		{
			Navigations_Fulcrum.navigateToActionRequired(driver);			
		}else {
			Navigations_Fulcrum.navigateToActionsOverdue(driver);
		}

		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);
		if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Closed")&& !status.equalsIgnoreCase("Rejected") && !status.equalsIgnoreCase("Declined")){

			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver,validationPage, workflow, subject);
			Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			Transmittals_EntryPage.validateActionStatus(driver, refid, testcasename, workflow, actionStatusData);			

		}
		return status;
	}

	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(After Submission)
	 * @author shaik
	 * @param driver
	 * @param validationPage
	 * @param workflow
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>transmittaldata,String action) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		subject=transmittaldata.get("Subject");
		
		//Not None and Approve or Accept
		/*if((!transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE))&&( action.equals("Approved")|| action.equals("Accepted"))){
			int recipients_count=1;
			if(transmittaldata.get("To").contains(Constants.delimiter_data)){
				String [] recps=commonMethods.splitString(transmittaldata.get("To"), Constants.delimiter_data);
				recipients_count=recps.length;
			}

			//if(!data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(data.get("Tramsmittals-ToCount"))){
			if(!transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(String.valueOf(recipients_count))){
				status="Outstanding";
				TxComplete_Status="Open";
				//subject=transmittaldata.get("Subject");
			}else{

				if(action.equals("Approved")){
					status="Approved";
					TxComplete_Status="Closed";
					//subject=transmittaldata.get("Subject");
				}else if (action.equals("Accepted")){
					status="Accepted";
					TxComplete_Status="Closed";
					//subject=transmittaldata.get("Subject");
				}else{
					status="Completed";
					TxComplete_Status="Closed";
				}


			}
		}*/


		// Hard coding the Ovedue to reject status as per test case.
		//Not None and reject or decline or overdue
		if(!(transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE))&& action.equals("Rejected")|| action.equals("Declined")|| action.equals("Overdue")){
			if(action.equals("Rejected")){
				status="Rejected";
			}else{
				status="Declined";
			}

			TxComplete_Status="Closed";
			//subject=transmittaldata.get("Subject");
		}

		//not None and forward or reply or delegate
		else if((!transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)) && (action.equals("Forward")||(action.equals("ReplyAll")||(action.equals("Delegate"))))){
			int recipients_count=1;

			if(!transmittaldata.get("To").contains(Constants.delimiter_data)){
				status="Outstanding";
				TxComplete_Status="Open";
				//subject=transmittaldata.get("Subject");
			}else{

				String [] recps=commonMethods.splitString(transmittaldata.get("To"), Constants.delimiter_data);
				recipients_count=recps.length;
				if(!transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(String.valueOf(recipients_count))){
					status="Outstanding";
					TxComplete_Status="Open";
					//subject=transmittaldata.get("Subject");
				}else{
					
					 if(action.equals("Approved")){
						status="Approved";
						TxComplete_Status="Closed";
						//subject=transmittaldata.get("Subject");
					}else if (action.equals("Accepted")){
						status="Accepted";
						TxComplete_Status="Closed";
						//subject=transmittaldata.get("Subject");
					}else{
						status="Completed";
						TxComplete_Status="Closed";
					}
				}
			}



		}	
		//Not None 
		/*else if(transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			//if(data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)){
			if(!transmittaldata.get("To").contains(Constants.delimiter_data)){
				status="Completed";
				TxComplete_Status="Closed";
				//subject=transmittaldata.get("Subject");
			}			
			else if(transmittaldata.get("To").contains(Constants.delimiter_data)&& transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)){
				status="Outstanding";
				TxComplete_Status="Open";
				//subject=transmittaldata.get("Subject");
			}
			else if(transmittaldata.get("To").contains(Constants.delimiter_data)&& transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(transmittaldata.get("Tramsmittals-ToCount"))){
				status="Completed";
				TxComplete_Status="Closed";
				//subject=transmittaldata.get("Subject");
			}
			else{
				status="";
				TxComplete_Status="";
				//subject=transmittaldata.get("Subject");

			}

		}*/

		else if((!transmittaldata.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_NONE))){
			int recipients_count=1;
			if(transmittaldata.get("To").contains(Constants.delimiter_data)){
				String [] recps=commonMethods.splitString(transmittaldata.get("To"), Constants.delimiter_data);
				recipients_count=recps.length;
			}			

			if(!transmittaldata.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(String.valueOf(recipients_count))){

				TxComplete_Status="Open";
				if (transmittaldata.get("Overdue").equalsIgnoreCase(Constants_FRMWRK.Yes)){
					status="Overdue";
				}else{
					status="Outstanding";
				}
			}else{
				/*status="Completed";
				TxComplete_Status="Closed";*/
				/*if (transmittaldata.get("Overdue").equalsIgnoreCase(Constants_FRMWRK.Yes)){		
					status="Overdue";
					TxComplete_Status="Closed";
				}			
				else*/ if(action.equals("Approved")){
					status="Approved";
					TxComplete_Status="Closed";
					
				}else if (action.equals("Accepted")){
					status="Accepted";
					TxComplete_Status="Closed";
					
				}else{
					status="Completed";
					TxComplete_Status="Closed";
				}
			}

		}


		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_Fulcrum.Transmittals.navigateToMyinbox(driver,workflow);
		}else{
			Navigations_Fulcrum.navigateToActionRequired(driver);
		}
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);
		
		//below is for mail details (overdue is displayed as oustanding )		
		if (transmittaldata.get("Overdue").equalsIgnoreCase(Constants_FRMWRK.Yes)&& (TxComplete_Status.equalsIgnoreCase("open")&& status.equalsIgnoreCase("Overdue"))){
			status="Outstanding";
		}

		return status;
	}

	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(After Cancel/Close Transmittal)
	 * @author shaik
	 * @param driver
	 * @param validationPage
	 * @param workflow
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable 
	 */
	/*public static String validate_CaC_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>data,String action) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		//if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)&& action.equalsIgnoreCase("CANCEL")){
		if(!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)&& action.equalsIgnoreCase("CANCEL")){
			status="Cancelling from Initiator";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}
		//else if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)&& action.equalsIgnoreCase("CLOSE")){
		else if(!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)&& action.equalsIgnoreCase("CLOSE")){
			status="Completed";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}

		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_Fulcrum.Transmittals.navigateToMyinbox(driver);
		}else{
			Navigations_Fulcrum.navigateToActionRequired(driver);
		}
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);

		return status;
	}
	 */
	public static String validate_TransmittalID(WebDriver driver,String page,String workflow,Hashtable<String,String> transmittaldata) throws Throwable{
		String subject=transmittaldata.get("Subject");
		String res=TransmittalsGridUtil.searchSubjectAndGetTransmittalID(driver, page, workflow, subject);
		if(!res.equalsIgnoreCase(Constants_FRMWRK.Error)||!res.equalsIgnoreCase(Constants_FRMWRK.False)){
			if(!res.equalsIgnoreCase("")){
				Reporting.logStep(driver, workflow+" "+page+" - Transmittal ID", "Transmittal ID :-"+res+" is displayed for the record "+subject, Constants_FRMWRK.Pass);
			}else{
				Reporting.logStep(driver, workflow+" "+page+" - Transmittal ID", "Transmittal ID :-"+res+" is not displayed for the record "+subject, Constants_FRMWRK.Fail);
			}
		}

		return res;
	}


	public static void validateActionRequiredCount(WebDriver driver,String refid,String testcasename,String workflow,String page,String count_before,String count_after){
		int current_count=Integer.valueOf(count_before);
		current_count=current_count-1;
		if(count_after.equalsIgnoreCase(String.valueOf(current_count))){
			Reporting.logStep(driver,refid,testcasename,workflow+"Validation of "+page+" records Count after Complete Action","The "+page+" records are decreased after completion Before count-"+count_before+" ,Current count-"+current_count,Constants_FRMWRK.Pass);
		}else{
			Reporting.logStep(driver,refid,testcasename,workflow+"Validation of "+page+" records Count after Complete Action","The "+page+" records not decreased after completion Before count-"+count_before+" ,Current count-"+current_count,Constants_FRMWRK.Fail);
		}
	}
}
