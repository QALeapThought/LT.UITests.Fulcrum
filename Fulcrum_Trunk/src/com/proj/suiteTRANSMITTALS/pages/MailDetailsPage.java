package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.ApplicationMethods;
import com.report.reporter.Reporting;

public class MailDetailsPage extends Transmittals_EntryPage{

	static String className=MailDetailsPage.class.getSimpleName();
	private static Xls_Reader xlsReader_objects_Transmittals=new Xls_Reader(Constants.OR_TRANS_Path);

	static String  input;

	private static Hashtable<String,String>objects_step_Transmittals=null;
	private static Hashtable<String,String>objects_locatorType_Transmittals=null; 
	private static Hashtable<String,String>objects_objectType_Transmittals=null;
	private static Hashtable<String,String>objects_objectLocator_Transmittals=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(MailDetailsPage.class,  xlsReader_objects_Transmittals, "Objects_Transmittals", "Transmittals");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}
	/**
	 * Validate the given value/text with actual displayed in the application for the field under test.
	 * @param driver
	 * @param refid
	 * @param testcasename
	 * @param workflow
	 * @param data
	 * @param transmittalData
	 * @param fieldName
	 * @param dataFetchFromFlag RunTimeData,TestData,GivenData
	 */

	private static void validateInputData(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,Hashtable<String,String>transmittalData,String fieldName,String dataFetchFromFlag,String input){
		String validate_data = "";
		if(dataFetchFromFlag.equalsIgnoreCase("RunTimeData")){
			validate_data=transmittalData.get(fieldName);
		}
		else if(dataFetchFromFlag.equalsIgnoreCase("TestData")){
			validate_data=data.get(fieldName);
		}		
		else{
			validate_data=input;
		}

		if(!commonMethods.checkKeyandgetValue(data, fieldName).equalsIgnoreCase(Constants_FRMWRK.False)||!commonMethods.checkKeyandgetValue(transmittalData, fieldName).equalsIgnoreCase(Constants_FRMWRK.False)){
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View - Mail Details-"+fieldName, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, validate_data);
		}


	}







	public static void Validate_MailDetails(WebDriver driver,String validationPage,String refid,String testcasename,String workflow,Hashtable<String,String>data,Hashtable<String,String>transmittalData) throws Throwable{
		String fieldName;

		TransmittalsGridUtil.searchSubjectAndOpenRecord(driver,validationPage, workflow, transmittalData.get("Subject"));
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		//To Users
		fieldName="To";
		String field_step="Tramsmittals-View - Mail Details-"+fieldName;
		String locator_default=objects_objectLocator_Transmittals.get(field_step);
		if(Transmittals_EntryPage.getRecieverUserCount(transmittalData)>1){			

			String[] recipients=Transmittals_EntryPage.getReciepientNames(transmittalData);
			for (String recipient :recipients){		
				String locator=objects_objectLocator_Transmittals.get(field_step);
				locator=commonMethods.replaceString("userName", locator, recipient);
				objects_objectLocator_Transmittals.put(field_step, locator);
				validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "GivenData",recipient);

				objects_objectLocator_Transmittals.put(field_step, locator_default);

			}

		}else{
			String locator=objects_objectLocator_Transmittals.get(field_step);
			locator=commonMethods.replaceString("userName", locator, transmittalData.get(fieldName));
			objects_objectLocator_Transmittals.put(field_step, locator);
			validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);
			objects_objectLocator_Transmittals.put(field_step, locator_default);
		}

		//Subject
		fieldName="Subject";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Send Date 
		fieldName="Send Date";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);
		//Sender		
		fieldName="Sender";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Mail ID 
		fieldName="Mail ID";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Reason for Issue
		fieldName="Reason For Issue";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Mail Type
		fieldName="Mail Type";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Contract#
		fieldName="Contract#";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Response Required
		fieldName="Response Required";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Due Date
		fieldName="Due Date";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);


		//Mail Status
		fieldName="Mail Status";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Completed Date
		if(!transmittalData.get(fieldName).equalsIgnoreCase("Outstanding")){
			fieldName="Completed Date";
			transmittalData.put(fieldName, DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy"));
			validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		}

		//Notify All?
		fieldName="Notify All?";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);
		//Notify All?

		//Single Response?
		fieldName="Single Response per Org?";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		//Descipline
		fieldName="Discipline";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);
		//Document Type
		fieldName="Document Type";
		validateInputData(driver, refid, testcasename, workflow, data, transmittalData, fieldName, "RunTimeData",input);

		fieldName="Review Sheet";
		if(!commonMethods.checkKeyandgetValue(data, fieldName).equalsIgnoreCase(Constants_FRMWRK.False)&&!data.get(fieldName).equalsIgnoreCase("")){
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View - Mail Details-"+fieldName, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("Review Sheet"));
		}
	}

	public static void verify_MailedDocuments_ApprovalStatus(WebDriver driver,String testcasename,String workflow,Hashtable<String,String>data,Hashtable<String,String>transmittalData) throws Throwable{
		int recipients_count=1;
		if(transmittalData.get("To").contains(Constants.delimiter_data)){
			String [] recps=commonMethods.splitString(transmittalData.get("To"), Constants.delimiter_data);
			recipients_count=recps.length;
		}
		if((transmittalData.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase("Approve")|| transmittalData.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase("Accept"))&& (transmittalData.get(Constants_Workflow.reciever_counter_key).equalsIgnoreCase(String.valueOf(recipients_count)))){
			

			String key_data_attachDoc_documentRegister="AttachDocuments";

			if(!data.get(key_data_attachDoc_documentRegister).isEmpty()){			
				String key_data_attachDoc_attachDocumentNames="AttachDocumentName";
				if(data.get(key_data_attachDoc_attachDocumentNames).contains(Constants.delimiter_data)){
					String[] getattachDocuments=commonMethods.splitString(data.get(key_data_attachDoc_attachDocumentNames), Constants.delimiter_data);

					for (String doc :getattachDocuments){
						Navigations_Fulcrum.navigateToMailedDocuments(driver,workflow);
						TransmittalsGridUtil.searchMailedDocumentAndCheck_Approval_Status(driver,testcasename, "Mailed Documents", workflow, doc, data.get("Action-Level2"));
					}

				}else{
					Navigations_Fulcrum.navigateToMailedDocuments(driver,workflow);
					TransmittalsGridUtil.searchMailedDocumentAndCheck_Approval_Status(driver,testcasename, "Mailed Documents", workflow, data.get(key_data_attachDoc_attachDocumentNames), data.get("Action-Level2"));
				}
			}

		}

	}

}
