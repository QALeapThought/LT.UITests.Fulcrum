package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.PageLoadWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.ElementPresence;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.utilFulcrum.ApplicationMethods;
import com.proj.utilFulcrum.WebTableUtil;
import com.proj.utilFulcrum.WebTable_Generic;

public class AttachDocumentPage extends Transmittals_EntryPage{
	static String input;

	protected static void clickAttachDocumentRibbonIcon(WebDriver driver,String refid,String testcasename,String workflow,String typeOfDoc) throws Throwable{
		String key_step_attachDoc="Tramsmittals-Attach Document";
		if(!typeOfDoc.contains("Attach")){
			key_step_attachDoc="Tramsmittals-Attach Support Document";
		}

		KeyMethods.f_performAction(driver, refid, testcasename, workflow,key_step_attachDoc , objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		ApplicationMethods.waitForOverlayToDisappear(driver);
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
	}

	protected static void clickAttach(WebDriver driver,String refid,String testcasename,String workflow){
		String key_button_attach="Tramsmittals-Attach";

		KeyMethods.f_performAction(driver, refid, testcasename, workflow,key_button_attach , objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		ApplicationMethods.waitForOverlayToDisappear(driver);
	}


	protected static String selectDocumentRegister(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,String typeOfDoc) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);

		String key_step_attachDocRegister="Tramsmittals-Document Registry";
		String key_data_attachDoc_documentRegister="AttachDocuments";
		if(!typeOfDoc.contains("Attach")){
			key_data_attachDoc_documentRegister="AttachSupportDocuments";
		}		
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow,key_step_attachDocRegister , objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, data.get(key_data_attachDoc_documentRegister));
		switchToDocumentsListFrame(driver);		
		return key_data_attachDoc_documentRegister;
	}

	protected static void selectSmartFolder(WebDriver driver,String workflow,String refID,String testcasename,String page,Hashtable<String,String>data,String key_data_attachDoc_documentRegister,String typeOfDoc) throws Throwable{


		String key_data_attachDoc_folder="AttachDocuments-Folder";
		if(!typeOfDoc.contains("Attach")){
			key_data_attachDoc_folder="AttachSupportDocuments-Folder";
		}

		if(!data.get(key_data_attachDoc_folder).equalsIgnoreCase("")){
			WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcasename,workflow+" "+page+" - Select a Smart Folder",Constants.container_property_summary, data.get(key_data_attachDoc_documentRegister),"equals",data.get(key_data_attachDoc_folder),Constants.Webtable_actionColumnType_DESCENDANT_LINK_1,data.get(key_data_attachDoc_folder),Constants_Workflow.Fulcrum_View_Column_Title,Constants_Workflow.Fulcrum_View_Column_Name);
			PageLoadWaitUtil.waitForPagetoLoadCompletely(driver, Constants_TimeOuts.Page_Load_TimeOut);
		}
	}

	protected static void selectDocumentRegisterAndSmartFolder(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,String page,String typeOfDoc) throws Throwable{

		String documentRegister=selectDocumentRegister(driver, refid, testcasename, workflow, data, typeOfDoc);
		selectSmartFolder(driver, workflow, refid, testcasename, page, data, documentRegister, typeOfDoc);
	}

	protected static void selectADocument(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,String page,String typeOfDoc,String documentFullName) throws Throwable{
		String key_data_attachDoc_documentRegister="AttachDocuments";
		String step_verify="Tramsmittals-Verify Attached File";

		if(!typeOfDoc.contains("Attach")){
			key_data_attachDoc_documentRegister="AttachSupportDocuments";
		}


		boolean fileAttached=false;
		int counter=0;
		while(counter<=3 && fileAttached==false){
			String[] docname=commonMethods.splitString(documentFullName, "\\.");
			//WebTableUtil.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcaseName, workflow+" "+page+" - Select a Document", data.get(key_data_attachDoc_documentRegister), docname[0],"Text", docname[0], 2, 3);
			WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcasename,workflow+" "+page+" - Select a Document",Constants.container_property_summary, data.get(key_data_attachDoc_documentRegister),"equals",docname[0],Constants.Webtable_actionColumnType_DESCENDANT_LINK_1,docname[0],Constants_Workflow.Fulcrum_View_Column_Name,Constants_Workflow.Fulcrum_View_Column_Name);
			String step_locator=objects_objectLocator_Transmittals.get(step_verify);
			step_locator=commonMethods.replaceString("docName", step_locator,  docname[0]);							

			fileAttached=ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, step_locator,Constants_TimeOuts.sync_element_load);

			counter=counter+1;
			if(counter==3){
				break;
			}
			if(fileAttached==false){
				selectDocumentRegisterAndSmartFolder(driver, refid, testcasename, workflow, data, page, typeOfDoc);

			}

		}
	}

	protected static void verify_AttachedDocuments_NewMail(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,String typeOfDoc) throws Throwable{
		//verify after attach		
		String key_step_attachedDoc="Tramsmittals-Attached Transmittal Files";

		String key_data_attachDoc_attachDocumentNames="AttachDocumentName";
		if(!typeOfDoc.contains("Attach")){
			key_data_attachDoc_attachDocumentNames="AttachSupportDocumentName";
			key_step_attachedDoc="Tramsmittals-Attached Support Document Files";
		}		
		String locator_default=objects_objectLocator_Transmittals.get(key_step_attachedDoc);
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		if(!data.get(key_data_attachDoc_attachDocumentNames).contains(Constants.delimiter_data)){	
			String[] docname=commonMethods.splitString(data.get(key_data_attachDoc_attachDocumentNames), "\\.");
			String locator=objects_objectLocator_Transmittals.get(key_step_attachedDoc).replaceAll("docName", docname[0]);
			objects_objectLocator_Transmittals.put(key_step_attachedDoc, locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-Attached Transmittal Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, docname[0]);
			objects_objectLocator_Transmittals.put(key_step_attachedDoc, locator_default);

		}else{
			String[] getattachDocuments=commonMethods.splitString(data.get(key_data_attachDoc_attachDocumentNames), Constants.delimiter_data);
			String default_locator=objects_objectLocator_Transmittals.get(key_step_attachedDoc);

			for (int doc=0;doc<getattachDocuments.length;doc++){
				String[] docname=commonMethods.splitString(getattachDocuments[doc], "\\.");					
				String locator=objects_objectLocator_Transmittals.get(key_step_attachedDoc).replaceAll("docName",docname[0]);
				objects_objectLocator_Transmittals.put(key_step_attachedDoc, locator);
				try{
					KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, key_step_attachedDoc, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, docname[0]);

				}catch (Throwable t){}
				objects_objectLocator_Transmittals.put(key_step_attachedDoc, default_locator);
			}

		}		
	}


	protected static void attachReviewSheet(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		String step_data="Review Sheet";
		String step_locator_reviewsheet_ribbon_button="Tramsmittals-Attach Review Sheet";
		String step_locator_reviewsheet_browse_button="Tramsmittals-Browse";
		String step_locator_reviewsheet_browse_ok_button="Tramsmittals-Browse-OK";
		String step_locator_reviewsheet_browse_file="Tramsmittals-Attached Review Document Files";


		if(data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_REVIEW) && !data.get(step_data).isEmpty()){
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow,step_locator_reviewsheet_ribbon_button , objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
			ApplicationMethods.waitForOverlayToDisappear(driver);
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String fileName=Constants.DataFileLocation_Transmittal+data.get(step_data);
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow,step_locator_reviewsheet_browse_button , objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, fileName);
			if(!res.equalsIgnoreCase(Constants_FRMWRK.Fail)){
				res=KeyMethods.f_performAction(driver, refid, testcasename, workflow,step_locator_reviewsheet_browse_ok_button , objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				String locator=objects_objectLocator_Transmittals.get(step_locator_reviewsheet_browse_file).replaceAll("docName", data.get(step_data));
				objects_objectLocator_Transmittals.put(step_locator_reviewsheet_browse_file, locator);
				res=KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, step_locator_reviewsheet_browse_file, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_data));

			}

		}
	}

	protected static void attachDocument_logic(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data,String typeofDocument) throws Throwable{
		String page ="Attach Document";	
		String key_data_attachDoc_documentRegister="AttachDocuments";
		String key_data_attachDoc_attachDocumentNames="AttachDocumentName";


		if(!typeofDocument.contains("Attach")){
			page ="Attach Support Document";
			key_data_attachDoc_documentRegister="AttachSupportDocuments";
			key_data_attachDoc_attachDocumentNames="AttachSupportDocumentName";
		}

		if(!data.get(key_data_attachDoc_documentRegister).isEmpty()){
			clickAttachDocumentRibbonIcon(driver, refid, testcasename, workflow,typeofDocument);

			if(!data.get(key_data_attachDoc_attachDocumentNames).contains(Constants.delimiter_data)){
				AttachDocumentPage.selectDocumentRegisterAndSmartFolder(driver, refid, testcasename, workflow, data, page, typeofDocument);
				AttachDocumentPage.selectADocument(driver, refid, testcasename, workflow, data, page, typeofDocument,data.get(key_data_attachDoc_attachDocumentNames));
			}

			else{

				String[] getattachDocuments=commonMethods.splitString(data.get(key_data_attachDoc_attachDocumentNames), Constants.delimiter_data);

				for (int doc=0;doc<getattachDocuments.length;doc++){
					AttachDocumentPage.selectDocumentRegisterAndSmartFolder(driver, refid, testcasename, workflow, data, page, typeofDocument);
					AttachDocumentPage.selectADocument(driver, refid, testcasename, workflow, data, page, typeofDocument, getattachDocuments[doc]);
				}


			}

			AttachDocumentPage.clickAttach(driver, refid, testcasename, workflow);
		}

		AttachDocumentPage.verify_AttachedDocuments_NewMail(driver, refid, testcasename, workflow, data, typeofDocument);

	}









	protected static class NewMail{

		protected static void attachMailDocuments(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
			
			Transmittals_EntryPage.clickEditTab(driver, refid, testcasename, workflow);
			
			attachReviewSheet(driver, refid, testcasename, workflow, data);			
			AttachDocumentPage.attachDocument_logic(driver, refid, testcasename, workflow, data, "Attach");
			AttachDocumentPage.attachDocument_logic(driver, refid, testcasename, workflow, data, "Support");
		}

	}


}
