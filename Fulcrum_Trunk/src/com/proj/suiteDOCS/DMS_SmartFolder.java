	package com.proj.suiteDOCS;
import java.util.Hashtable;
import org.testng.annotations.Test;
import com.frw.Constants.Constants_FRMWRK;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteDOCS.reusables.SiteContents;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil; 


public class DMS_SmartFolder extends TestSuiteBase{
	static String res="";

	static String workFlow_search = "Search for DMSDocLibrary-";
	static String workFlow_Create = "Create DMSDocLibrary-";
	static String workFlow_smartFolder = "Create Smartfolder-";
	static String workFlow_uploadDocument = "Upload Document-";

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestDMS_SmartFolder(Hashtable<String,String>data
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

			Navigations_Fulcrum.Settings.navigateToSiteContents(driver_DOCS, workFlow_search );
			SiteContents.checkDMSLibraryAndOpen(driver_DOCS, workFlow_search , data);
			
			SiteContents.clickFilesOnRibbon(driver_DOCS, workFlow_smartFolder);
			SiteContents.clickNewDocument(driver_DOCS, workFlow_smartFolder);
			SiteContents.clickDMSSmartFolder(driver_DOCS, workFlow_smartFolder);
			String smartFoldername = SiteContents.enterSmartFolderName(driver_DOCS, workFlow_smartFolder, data);
			SiteContents.enterMetadataForSmartfolder(driver_DOCS, refID,testcaseName,workFlow_smartFolder,  data);
			SiteContents.openSmartFolder(driver_DOCS, workFlow_smartFolder, data,smartFoldername);
			SiteContents.clickUploadDocInRibbon(driver_DOCS, workFlow_uploadDocument);
			SiteContents.browseAFile(driver_DOCS, smartFoldername, testcaseName, workFlow_uploadDocument, data);
			SiteContents.clickOk(driver_DOCS, workFlow_uploadDocument);
			SiteContents.clickGenerateFileName(driver_DOCS, workFlow_uploadDocument);
			SiteContents.generateFileName(driver_DOCS, workFlow_uploadDocument, data);
			String getDocName= SiteContents.getDocumentName(driver_DOCS, workFlow_uploadDocument);
			SiteContents.enterTitle(driver_DOCS, testcaseName,workFlow_uploadDocument, getDocName);
			SiteContents.clickSave(driver_DOCS, workFlow_smartFolder);
			SiteContents.verifyDocAddedInSmartFolder(driver_DOCS, workFlow_uploadDocument, getDocName);
		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}
}
