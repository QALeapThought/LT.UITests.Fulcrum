package com.proj.suiteDOCS;
import java.util.Hashtable;
import org.testng.annotations.Test;
import com.frw.Constants.Constants_FRMWRK;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteDOCS.reusables.SiteContents;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil; 


public class DMS_CreateView extends TestSuiteBase {

	static String res="";

	static String workFlow_Sitecontents = "Navigate to SiteContents-";
	static String workFlow_DMS = "Open DMSDocLibrary-";
	static String workFlow_CreateView = "Create View-";
	static String workFlow_CreatePublicView = "Create Public View-";
	static String workFlow_CreatePersonalView = "Create Personal View-";

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestDMS_CreateView(Hashtable<String,String>data
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

			Navigations_Fulcrum.Settings.navigateToSiteContents(driver_DOCS, workFlow_Sitecontents );
			SiteContents.checkDMSLibraryAndOpen(driver_DOCS, workFlow_DMS , data);

			SiteContents.navigateToStandardView(driver_DOCS, workFlow_CreateView);
			String publicViewName = SiteContents.enterPublicViewName(driver_DOCS, workFlow_CreatePublicView, data);
			SiteContents.checkVersionCheckbox(driver_DOCS, workFlow_CreatePublicView, data);
			SiteContents.clickOk(driver_DOCS, workFlow_CreatePublicView);
			SiteContents.clickPublicView(driver_DOCS, workFlow_CreatePublicView, publicViewName);
			SiteContents.verifyVersion(driver_DOCS, workFlow_CreatePublicView);


			SiteContents.navigateToStandardView(driver_DOCS, workFlow_CreateView);
			String personalViewName = SiteContents.enterPersonalViewName(driver_DOCS, workFlow_CreatePersonalView, data);
			SiteContents.checkTypeCheckbox(driver_DOCS, workFlow_CreatePersonalView, data);
			SiteContents.clickOk(driver_DOCS, workFlow_CreatePersonalView);
			SiteContents.clickPersonalView(driver_DOCS, workFlow_CreatePersonalView, personalViewName);
			SiteContents.verifyType(driver_DOCS, workFlow_CreatePersonalView);




		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}
}




