package com.proj.suiteDOCS;
import java.util.Hashtable;


import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;

import com.proj.navigations.Navigations_Fulcrum;

import com.proj.suiteDOCS.reusables.SiteContents;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil; 

public class DMS_TransmittalFolderView extends TestSuiteBase {

	static String res="";

	static String workFlow_Sitecontents = "Navigate to SiteContents-";
	static String workFlow_DMS = "Open DMSDocLibrary-";
	static String workFlow_CreateTxFolderView = "Create Tx Folder View-";
	

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestDMS_TransmittalFolderView(Hashtable<String,String>data
			) throws Throwable{

		System.out.println("In test");
		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);
		try{

			if(isBeforeMethodPass_docs==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}

			logsObj.log("******************************************************");		
			logsObj.log("Executing the test case: "+testcaseName);

			String transmittalIDwithAttachment=SiteContents.getTransmittalIDWithAttachment(driver_DOCS, refID, testcaseName, workFlow_Sitecontents);
			
			Navigations_Fulcrum.Settings.navigateToSiteContents(driver_DOCS, workFlow_Sitecontents );
			SiteContents.checkDMSLibraryAndOpen(driver_DOCS, workFlow_DMS , data);
			SiteContents.clickFilesOnRibbon(driver_DOCS, workFlow_CreateTxFolderView);
			SiteContents.clickNewDocument(driver_DOCS, workFlow_CreateTxFolderView);
			SiteContents.clickTxFolderView(driver_DOCS, workFlow_CreateTxFolderView);
			String Txname = SiteContents.txFolderName(driver_DOCS, workFlow_CreateTxFolderView, data);
			SiteContents.mailIds(driver_DOCS, workFlow_CreateTxFolderView, transmittalIDwithAttachment);
			
			SiteContents.saveTxFolder(driver_DOCS, workFlow_CreateTxFolderView);
			SiteContents.verifyAndClickTxFolderAdded(driver_DOCS, workFlow_CreateTxFolderView, Txname);
			SiteContents.verifyDocAttachedInTx(driver_DOCS, testcaseName);
			

	

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}
}




