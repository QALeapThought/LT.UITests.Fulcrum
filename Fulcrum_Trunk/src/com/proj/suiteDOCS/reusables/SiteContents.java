package com.proj.suiteDOCS.reusables;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.PopUpUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteDOCS.TestSuiteBase;
import com.proj.suiteDOCS.pages.Documents_EntryPage;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.ApplicationMethods;
import com.proj.utilFulcrum.WebTableUtil;
import com.proj.utilFulcrum.WebTableUtil_old;
import com.proj.utilFulcrum.WebTable_Generic;
import com.report.reporter.Reporting;

public class SiteContents extends TestSuiteBase {

	static String input="";	
	static String res ="";
	static String smartFoldername="";
	static String className=SiteContents.class.getSimpleName();
	private static Xls_Reader xlsReader_objects_Settings=new Xls_Reader(Constants.OR_Settings_Path);
	@SuppressWarnings("unused")
	private static Hashtable<String,String>objects_step_SiteContents=null;
	private static Hashtable<String,String>objects_locatorType_SiteContents=null; 
	private static Hashtable<String,String>objects_objectType_SiteContents=null;
	private static Hashtable<String,String>objects_objectLocator_SiteContents=null;
	@SuppressWarnings("unused")
	private static Hashtable<String,String>objects_step_App=null;
	private static Hashtable<String,String>objects_locatorType_App=null; 
	private static Hashtable<String,String>objects_objectType_App=null;
	private static Hashtable<String,String>objects_objectLocator_App=null;

	static {		
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(SiteContents.class,  xlsReader_objects_Settings, "Objects_SiteContents", "SiteContents");
			fetchObjectRepository.getObjects(SiteContents.class,  xlsReader_objects_Settings, "Objects_App", "App");

		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);
          //Reporting.logStep(driver, workflow+" "+page+" - Transmittal ID", "Transmittal ID :-"+res+" is displayed for the record "+subject, Constants_FRMWRK.Pass);    
		}
	}
	public static void addAnApp(WebDriver driver,String workFlow) throws Exception{

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Add an app", objects_locatorType_SiteContents, objects_objectType_SiteContents, objects_objectLocator_SiteContents, input);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_SiteContents.get("Add an app"), " Navigate Failure - My Sent", "Please refer above details for more details");
		}
		
	}
	public static void clickDMSDocumentLibrary(WebDriver driver,String workFlow){

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "DMS Document Library", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);

	}
	public static String enterDMSname(WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Name for DMS", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("DMSname"));
		return res;

	}
	public static void clickCreate(WebDriver driver,String workFlow) throws Exception{
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Create - DMS", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		Documents_EntryPage.waitInvisiblilityofPopUpDMSDocumentLibrary(driver);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("Create - DMS"), " Navigate Failure - My Sent", "Please refer above details for more details");
		}


	}
	public static void openDMSLibrary(WebDriver driver,String workFlow,Hashtable<String,String>data){
		String DmsLibName = data.get("DMSname");
		String locator= objects_objectLocator_App.get("Verify DMS Document").replaceAll("DmsTitle", DmsLibName);
		objects_objectLocator_App.put("Verify DMS Document", locator);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Verify DMS Document", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		
	}

	public static void clickSmartFolder(WebDriver driver,String workFlow,Hashtable<String,String>data){

		String locator= objects_objectLocator_App.get("Smart Folder Name").replaceAll("smartFolderName", smartFoldername);
		objects_objectLocator_App.put("Smart Folder Name", locator);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "DMS Smart Folder", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		WaitUtil.pause(3);
	}

	public static void clickFilesOnRibbon(WebDriver driver,String workFlow) throws Exception{
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Files-Ribbon", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("Files-Ribbon"), " Navigate Failure - My Sent", "Please refer above details for more details");
		}

	}
	public static void clickNewDocument(WebDriver driver,String workFlow) throws Exception{
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "New Document dropdown", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("New Document dropdown"), " Navigate Failure - My Sent", "Please refer above details for more details");
		}
	}

	public static void clickDMSSmartFolder(WebDriver driver,String workFlow) throws Exception{
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "DMS Smart Folder", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("DMS Smart Folder"), " Navigate Failure - My Sent", "Please refer above details for more details");
		}
	}

	public static String enterSmartFolderName(WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{

		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		String smartFoldername = KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("SmartFolderName")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd MM yyyy hhmmss"));
		WaitUtil.pause(7);
		return smartFoldername;
	}
	public static void clickSave(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Save-Smart Folder", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		WaitUtil.pause(10);
	}


	public static void clickDmsBreadcrumb(WebDriver driver,String workFlow,Hashtable<String,String>data) {
		String DmsLibName = data.get("DMSname");
		String locator= objects_objectLocator_App.get("Breadcrumb").replaceAll("DmsTitle", DmsLibName);
		objects_objectLocator_App.put("Breadcrumb", locator);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Breadcrumb", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		WaitUtil.pause(5);

	}
	public static void clickRemove(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Breadcrumb-Remove", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		WaitUtil.pause(6);
		PopUpUtil.checkDefaultPopup(driver, workFlow +"-Check for the validation popup", Constants.ok);
		WaitUtil.pause(6);
	}

	public static void checkDMSLibraryAndOpen(WebDriver driver,String workFlow,Hashtable<String,String>data){
		System.out.println("in dms");
		String DmsLibName = data.get("DMSname");
		String locator= objects_objectLocator_App.get("Breadcrumb").replaceAll("DmsTitle", DmsLibName);
		objects_objectLocator_App.put("Breadcrumb", locator);
		boolean isDmsLibraryAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Breadcrumb"), objects_objectLocator_App.get("Breadcrumb"));

		if (isDmsLibraryAvailable == true){
			//Reporting.logStep(driver, refID, testcaseName, "Search for DMSDocLibrary-Check if available" ," DMS document library 'Automation DMS Library' is available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Search for DMSDocLibrary-Check if available","DMS document library 'Automation DMS Library' is available",Constants_FRMWRK.Pass );
			WaitUtil.pause(3);
			openDMSLibrary(driver_DOCS, workFlow,data);
		}
		
		
	}
	public static void checkDMSLibraryAndRemove(WebDriver driver,String workFlow,Hashtable<String,String>data){

		String DmsLibName = data.get("DMSname");
		String locator= objects_objectLocator_App.get("Breadcrumb").replaceAll("DmsTitle", DmsLibName);
		objects_objectLocator_App.put("Breadcrumb", locator);
		boolean isDmsLibraryAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Breadcrumb"), objects_objectLocator_App.get("Breadcrumb"));

		int count=1;
		while(count <2){
			
			if (isDmsLibraryAvailable == true){
				//Reporting.logStep(driver, refID, testcaseName, "isDmsLibraryAvailable" ," Is Automation DMS Library available" , Constants_FRMWRK.Pass );
				Reporting.logStep(driver, refID, testcaseName, workFlow + "Verify if DMS library is available","DMS Document library is avaiable",Constants_FRMWRK.Pass );
				WaitUtil.pause(3);
				clickDmsBreadcrumb(driver_DOCS, workFlow, data);
				clickRemove(driver_DOCS, workFlow);
				count++;
			}
			else{
				break; 
			}

		}
		
	}



	/**
	 * Enter the required details to create smart folder
	 * @throws Throwable
	 */

	public static Hashtable<String, String> enterMetadataForSmartfolder(WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data) throws Throwable {
		Hashtable<String,String>returnData = new Hashtable<String,String>();

		res=KeyMethods.f_performAction(driver,refID, testcaseName, workFlow, "Doc.Status", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Doc.Status"));
		returnData.put("Doc.Status", res);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "External Ref", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("External Ref")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		returnData.put("External Ref", res);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Deliverable Ref", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Deliverable Ref")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		returnData.put("Deliverable Ref", res);


		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Contract#", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Contract#"));
		returnData.put("Contract#", res);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "DMS Suppiler", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("DMS Supplier"));
		returnData.put("DMS Suppiler", res);


		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Document Type1", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Document Type1"));
		returnData.put("Document Type1", res);

		//res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Street", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Street"));
		//returnData.put("Street", res);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Category", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Category"));
		returnData.put("Category", res);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Workstream", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Workstream"));
		returnData.put("Workstream", res);

		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		clickSave(driver, workFlow);

		WaitUtil.pause(6);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
		return returnData;



	}
	public static void browseAFile(WebDriver driver,String refID, String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver,testcasename);
		String filename=Constants.DataFileLocation_Documents+data.get("Choose a file");
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Choose a file", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, filename);
	}

	public static void openSmartFolder(WebDriver driver,String workFlow,Hashtable<String,String>data,String smartFoldername) throws Exception{

		String locator= objects_objectLocator_App.get("Smart Folder Name").replaceAll("smartFolderName", smartFoldername);

		objects_objectLocator_App.put("Smart Folder Name", locator);
		boolean isSmartfolderAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Smart Folder Name"), objects_objectLocator_App.get("Smart Folder Name"));

		if (isSmartfolderAvailable== true){
			//Reporting.logStep(driver, refID, testcaseName, "Create Smartfolder-Check for newly added smart folder" ," Newly added Smart folder is available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check for newly added smart folder","Newly added Smart folder is available",Constants_FRMWRK.Pass );
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Smart Folder Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
			
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(objects_objectLocator_App.get("Smart Folder Name"), " Navigate Failure - Smart Folder Name", "Please refer above details for more details");
			}
		}

	}

	public static void clickUploadDocInRibbon (WebDriver driver,String workFlow) throws Exception{
		clickFilesOnRibbon(driver_DOCS, workFlow);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Upload Document", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("Upload Document"), " Navigate Failure - Upload Document", "Please refer above details for more details");
		}
	}


	public static void clickOk(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "OK", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		WaitUtil.pause(8);
	}

	public static void clickGenerateFileName(WebDriver driver,String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Generate File Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
	
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_App.get("Generate File Name"), " Navigate Failure - Generate File Name", "Please refer above details for more details");
		}
	}


	public static void generateFileName(WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Zone", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App,data.get("Zone"));
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, objects_objectLocator_App.get("Zone")+" Select taxonomy item Failure - Generate File Name", "Please refer above details for more details");
		}		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Discipline", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Discipline"));
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, objects_objectLocator_App.get("Discipline")+" Select taxonomy item Failure - Generate File Name", "Please refer above details for more details");
		}
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Document Type", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Document Type1"));
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, objects_objectLocator_App.get("Discipline")+" Select taxonomy item Failure - Generate File Name", "Please refer above details for more details");
		}
		clickSave(driver, workFlow);
		
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, objects_objectLocator_App.get("Document Type")+" Navigate Failure - Generate File Name", "Please refer above details for more details");
		}
		
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}

	public static String getDocumentName(WebDriver driver,String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		WaitUtil.pause(6);
		String getDocName=KeyMethods.f_fetchElementDetails(driver, refID, testcaseName, workFlow, "Get Document Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App,input);
		return getDocName;
	}

	public static void enterTitle(WebDriver driver,String testcasename,String workFlow, String getDocName) throws Exception{
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Title", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, getDocName);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, workFlow+"File Register-Title", "Error while entering the title,hence exiting the test");
		}
		
	}

	public static void verifyDocAddedInSmartFolder (WebDriver driver,String workFlow, String getDocName){
		String locator= objects_objectLocator_App.get("NewDocName").replaceAll("NewDocName", getDocName);
		objects_objectLocator_App.put("NewDocName", locator);
		boolean isNewDocAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("NewDocName"), objects_objectLocator_App.get("NewDocName"));

		if (isNewDocAvailable == true){
			//Reporting.logStep(driver, refID, testcaseName, "Upload Document-Check newly added document" ," New Document with Generate file name is available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check newly added document","New Document with Generate file name is available",Constants_FRMWRK.Pass );
			WaitUtil.pause(3);
		}
	}

	public static void clickLibraryOnRibbon(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Library-Ribbon", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		

	}

	public static void clickCreateView(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Create View", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		

	}
	public static void clickStandardView(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Standard View", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		
	}

	public static void navigateToStandardView(WebDriver driver,String workFlow){
		clickLibraryOnRibbon(driver, workFlow);
		clickCreateView(driver, workFlow);
		clickStandardView(driver, workFlow);
	}
	
	public static void clickPublicViewRadioButton(WebDriver driver,String workFlow,Hashtable<String,String>data){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Public View", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Create Public view"));
		WaitUtil.pause(3);
	}

	public static String enterPublicViewName(WebDriver driver,String workFlow,Hashtable<String,String>data)throws Throwable{
		String publicViewName= KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "View Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("View Name1")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("ddMMyyyy hhmmss"));
		return publicViewName;
	}

	public static void checkVersionCheckbox(WebDriver driver,String workFlow,Hashtable<String,String>data) {

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Version checkbox", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Version Checkbox"));
		WaitUtil.pause(6);

	}

	public static void clickPublicView(WebDriver driver,String workFlow,String publicViewName){

		String locator1= objects_objectLocator_App.get("Public view").replaceAll("textPublic", publicViewName);
		objects_objectLocator_App.put("Public view", locator1);
		boolean istextPublicAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Public view"), objects_objectLocator_App.get("Public view"));

		if (istextPublicAvailable== true){

			//Reporting.logStep(driver, refID, testcaseName, "Create Public view -Check for newly added public view" ," Newly added public view is available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check for newly added public view","Newly added public view is available",Constants_FRMWRK.Pass );
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Public view", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
			WaitUtil.pause(6);
		}
		else{
			//Reporting.logStep(driver, refID, testcaseName, "Create Public view -Check for newly added public view" ," Newly added public view is not available" , Constants_FRMWRK.Fail );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check for newly added public view","Newly added public view is not available",Constants_FRMWRK.Pass );
		}
	}
	public static void verifyVersion(WebDriver driver,String workFlow){

		boolean isVersionAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Version-"), objects_objectLocator_App.get("Version-"));

		if (isVersionAvailable== false){

			//Reporting.logStep(driver, refID, testcaseName, "Version - Version column should not be available" ," Version column is not available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Version column should not be available","Version column is not available",Constants_FRMWRK.Pass );
			WaitUtil.pause(3);
		}
		else{
			//Reporting.logStep(driver, refID, testcaseName, "Version - Version column should not be available" ," Version column is available" , Constants_FRMWRK.Fail );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Version column should not be available","Version column is available",Constants_FRMWRK.Pass );
		}
	}


	public static String enterPersonalViewName(WebDriver driver,String workFlow,Hashtable<String,String>data)throws Throwable{
		String personalViewName= KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "View Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("View Name2")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("ddMMyyyy hhmmss"));
		return personalViewName;
	}


	public static void clickPersonalViewRadioButton(WebDriver driver,String workFlow,Hashtable<String,String>data){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Personal View", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Create Personal View"));
		WaitUtil.pause(3);
	}

	public static void checkTypeCheckbox(WebDriver driver,String workFlow,Hashtable<String,String>data) {

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Type checkbox", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("Type checkbox"));
		WaitUtil.pause(6);

	}


	public static void clickPersonalView(WebDriver driver,String workFlow,String personalViewName) throws Throwable{
		
		String locator2= objects_objectLocator_App.get("Personal view").replaceAll("textPersonal", personalViewName);
		objects_objectLocator_App.put("Personal view", locator2);
		boolean istextPersonalAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Personal view"), objects_objectLocator_App.get("Personal view"));

		if (istextPersonalAvailable== true){
			
			//Reporting.logStep(driver, refID, testcaseName, "Create Personal view -Check for newly added personal view" ," Newly added personal view is available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check for newly added personal view","Newly added personal view is available",Constants_FRMWRK.Pass );
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Personal view", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
			WaitUtil.pause(6);
		}
		else{
			//Reporting.logStep(driver, refID, testcaseName, "Create Personal view -Check for newly added personal view" ," Newly added personal view is not available" , Constants_FRMWRK.Fail );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Check for newly added personal view","Newly added personal view is not available",Constants_FRMWRK.Pass );
		}

	}
	public static void verifyType(WebDriver driver,String workFlow){

		boolean isTypeAvailable = commonMethods.isElementDisplayed(driver, objects_locatorType_App.get("Type-"), objects_objectLocator_App.get("Type-"));

		if (isTypeAvailable== false){

			//Reporting.logStep(driver, refID, testcaseName, "Type - Type column should not be available" ," Type column is not available" , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Type column should not be available","Type column is not available",Constants_FRMWRK.Pass );
			WaitUtil.pause(3);
		}
		else{
			//Reporting.logStep(driver, refID, testcaseName, "Type - Type column should not be available" ," Type column is available" , Constants_FRMWRK.Fail );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "Type column should not be available","Type column is available",Constants_FRMWRK.Pass );
		}
	}
	public static void clickTxFolderView (WebDriver driver,String workFlow){

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Transmittal Folder View", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		
	}

	public static String txFolderName(WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		String Txname = KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tx folder Name", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, data.get("TX Folder Name")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("ddMMyyyy hhmmss"));
		return Txname;
	}
	
	public static void mailIds(WebDriver driver,String workFlow,String mailIDWithAttachment){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Mail Ids", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, mailIDWithAttachment);
		
	}
	public static void saveTxFolder(WebDriver driver,String workFlow){
		clickSave(driver_DOCS, workFlow);
		Documents_EntryPage.waitInvisiblilityofNewFolder(driver);
		WaitUtil.pause(8);
		
	}
	
	public static void verifyAndClickTxFolderAdded(WebDriver driver,String workFlow, String Txname) throws Throwable{
		//WebTableUtil.searchforDataInsearchColumnAndClickInactionableColumn(driver, testcaseName, workFlow+"Open Tx folder added", ObjRepository.container_dmsLibrary, Txname, "Link", Txname, 5,5 );	
	}
	
	public static void verifyDocAttachedInTx(WebDriver driver,String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver_DOCS, testcaseName);
		int record = WebTable_Generic.getNumberOfRecordsFromTable(driver_DOCS, testcaseName, workFlow, ObjRepository.container_documentRegister, 1);
		if (record > 0)
		{
			//Reporting.logStep(driver, refID, testcaseName, "Create TX Folder View - List of documents attached to the Tx should be displayed " ,record+" documents are displayed " , Constants_FRMWRK.Pass );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "-List of documents attached to the Tx should be displayed",record+" documents are displayed",Constants_FRMWRK.Pass );
		}else{
			//Reporting.logStep(driver, refID, testcaseName, "Create TX Folder View - List of documents attached to the Tx should be displayed" ,"No documents are displayed" , Constants_FRMWRK.Fail );
			Reporting.logStep(driver, refID, testcaseName, workFlow + "-List of documents attached to the Tx should be displayed","No documents are displayed",Constants_FRMWRK.Pass );
		}		
	}
	
	public static String getTransmittalIDWithAttachment(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		Navigations_Fulcrum.Transmittals.navigateToMyinbox(driver,workflow);
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "TransmittalID with attachments", objects_locatorType_App, objects_objectType_App, objects_objectLocator_App, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, "Fetch Transmittal ID with Attachment from Inbox-Failure", "Could not fetch the Transmittal ID with Attachment from inbox due to above error");
		}
		return res;
	}
	
}
	
	
	







