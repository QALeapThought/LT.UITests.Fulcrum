package com.proj.suiteTRANSMITTALS.reusables;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.utilFulcrum.WebTableUtil;
import com.proj.utilFulcrum.WebTable_Generic;

public class TransmittalsGridUtil extends TestSuiteBase{


	static String res="";
	static String input="";
	static WebDriver dr; //Remove this after getting new logStep method


	/**
	 * Searches for the subject record in the grid and tick the record 
	 * @author Shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @throws Throwable 
	 */
	public static String searchSubjectAndTickRecord(WebDriver driver,String workflow,String subject) {
		return WebTableUtil.searchforDataInsearchColumnAndTickInactionableColumn(driver, testcaseName, workflow+" My Sent - Subject", ObjRepository.container_transmittals, subject, 4, 1);

	}
	/**
	 * Search the record with Subject and Open
	 * @author shaikka
	 * @param driver
	 * @param page
	 * @param workflow
	 * @param subject
	 * @return
	 * @throws Throwable 
	 */
	public static String searchSubjectAndOpenRecord(WebDriver driver,String page,String workflow,String subject){
		String flag="";
		flag=WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,workflow+" "+page+" - Subject","summary", ObjRepository.container_transmittals,"equals",subject,Constants.Webtable_actionColumnType_Link,subject,Constants_Workflow.Fulcrum_View_Column_Subject,Constants_Workflow.Fulcrum_View_Column_Subject);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		return flag;
	}
	/**
	 * Searches for the subject record in the grid and validates the  TxComplete_Status of the record 
	 * @author shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @param TxComplete_Status
	 * @throws Throwable 
	 */
	public static String searchSubjectAndCheck_TxComplete_Status(WebDriver driver,String page,String workflow,String subject,String TxComplete_Status){
		return WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,workflow+" "+page+" - TxComplete_Status","summary", ObjRepository.container_transmittals,"equals",subject,Constants.Webtable_actionColumnType_Text,TxComplete_Status,Constants_Workflow.Fulcrum_View_Column_Subject,Constants_Workflow.Fulcrum_View_Column_Complete);
	}
	/**
	 * Searches for the subject record in the grid and validates the status of the record 
	 * @author shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @param status
	 * @throws Throwable 
	 */
	public static String searchSubjectAndCheck_Status(WebDriver driver,String page,String workflow,String subject,String status) {
		return WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,workflow+" "+page+" - Status","summary", ObjRepository.container_transmittals,"equals",subject,Constants.Webtable_actionColumnType_Text,status,Constants_Workflow.Fulcrum_View_Column_Subject,Constants_Workflow.Fulcrum_View_Column_Status);
	}

	public static String searchSubject(WebDriver driver,String testcasename,String page,String workflow,String subject){
		return WebTableUtil.searchforDataInsearchColumn_WOR(driver, testcasename, workflow+" "+page+" - Subject", ObjRepository.container_transmittals, subject, 4);

	}
	public static String searchSubjectAndGetTransmittalID(WebDriver driver,String page,String workflow,String subject) {
		return WebTableUtil.searchforDataInsearchColumnAndFetchDataInactionableColumn_WOR(driver, testcaseName, workflow+" "+page+" - Transmittal ID", ObjRepository.container_transmittals, subject, "text", 4, 3);

	}

	public static String searchMailedDocumentAndCheck_Approval_Status(WebDriver driver,String testcasename,String page,String workflow,String documentname,String approval_Status) {
		return WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcasename,workflow+" "+page+" - Approval Status","summary", ObjRepository.container_documentRegister,"equals",documentname,Constants.Webtable_actionColumnType_Text,approval_Status,Constants_Workflow.Fulcrum_View_Column_Name,Constants_Workflow.Fulcrum_View_Column_Approval_Status);
	}


	public static boolean waitUntilSubjectDisplays(WebDriver driver,String refId,String testcasename,String page,String workflow,String subject,String pagetorefresh) throws Throwable{
		boolean flag=false;
		int counter=0;

		do{
			
			String subjectexists=searchSubject(driver, testcasename, page, workflow, subject);
			if(subjectexists.equalsIgnoreCase(Constants_FRMWRK.True)){
				flag=true;
				break;
			}
			
			WaitUtil.pause(5);
			if(pagetorefresh.endsWith("My Sent")){
				//commonMethods.refreshPage(driver, refId, testcasename, workflow,Constants_FRMWRK.Warning);
				Navigations_Fulcrum.Transmittals.navigateToMysent(driver,workflow);
			}
			
			PageLoadWaitUtil.waitForPagetoLoadCompletely(driver, Constants_TimeOuts.Page_TimeOut);
			counter=counter+1;
		}while (flag==Constants_FRMWRK.FalseB && counter<=24);	


		return flag;
	}
	
	
}
