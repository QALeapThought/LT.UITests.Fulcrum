package com.proj.suiteDOCS;
import java.util.Hashtable;
import org.testng.annotations.Test;
import com.frw.Constants.Constants_FRMWRK;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteDOCS.reusables.SiteContents;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil; 


public class DmsDocumentLibrary extends TestSuiteBase{
	static String res="";

	static String workFlow_search = "Search for DMSDocLibrary-";
	static String workFlow_Create = "Create DMSDocLibrary-";
	static String workFlow_validate = "Validate DMSDocLibrary-";

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestDmsDocumentLibrary(Hashtable<String,String>data
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
			SiteContents.checkDMSLibraryAndRemove(driver_DOCS, workFlow_search , data);
			SiteContents.addAnApp(driver_DOCS, workFlow_Create);
			SiteContents.clickDMSDocumentLibrary(driver_DOCS, workFlow_Create);
			SiteContents.enterDMSname(driver_DOCS, workFlow_Create, data);
			SiteContents.clickCreate(driver_DOCS, workFlow_Create);
			SiteContents.openDMSLibrary(driver_DOCS, workFlow_validate,data);
	
			
		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}
}
