package com.proj.navigations;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.ElementPresence;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.report.reporter.Reporting;

public class Navigations_Fulcrum extends TestBase{
	static String res="";
	static String input="";	
	static String className=Navigations_Fulcrum.class.getSimpleName();
	private static String locator_menu_pattern=".//*[@class='expanded-item' and contains(text(),'objectlocator')]";
	private static String locator_submenu_pattern="//*[contains(@class,'sub-menu-wrap')]/descendant :: * [text()='objectlocator']";

	private static Xls_Reader xlsReader_objects_Navigation=new Xls_Reader(Constants.OR_Nav_Path);

	private static Hashtable<String,String>objects_step_Navigation=null;
	private static Hashtable<String,String>objects_locatorType_Navigation=null; 
	private static Hashtable<String,String>objects_objectType_Navigation=null;
	private static Hashtable<String,String>objects_objectLocator_Navigation=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Navigation Class");
		try {
			fetchObjectRepository.getObjects(Navigations_Fulcrum.class,xlsReader_objects_Navigation, "Objects_Navigation", "Navigation");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}

	public static String checkSubMenusDisplayed(WebDriver driver) throws Throwable{
		String flag=Constants_FRMWRK.False;
		WebElement element=null;

		try{
			element=ExplicitWaitUtil.waitForElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.container_subMenu, Constants_TimeOuts.generic_TimeOut) ;
		}catch(Exception ex){

		}

		if(element!=null){
			if(element.isDisplayed()==true){
				WaitUtil.pause(1);
				flag=Constants_FRMWRK.True;
			}			
		}
		return flag;
	}
	public static void navigateToTramsmittals(WebDriver driver,String workflow) throws Throwable{
		commonMethods.switchToDefaultPage(driver);
		if(checkSubMenusDisplayed(driver).equalsIgnoreCase(Constants_FRMWRK.False)){
			String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Transmittals"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get("Usersite Menu - Transmittals"), objects_locatorType_Navigation.get("Usersite Menu - Transmittals"), objects_objectType_Navigation.get("Usersite Menu - Transmittals"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - Transmittals", "Please refer above details for more details");
			}
		}

	}

	public static void navigateToDocumentsAndFileStorage(WebDriver driver,String workflow) throws Throwable{
		commonMethods.switchToDefaultPage(driver);
		String step="Usersite Menu - Document & File Storage";
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(step));
		/*	//WebElement element=ElementPresence.elementDisplayed(driver, objects_locatorType_Navigation.get(step), locator, Constants_TimeOuts.Element_TimeOut);
		WebElement element=ExplicitWaitUtil.waitForPresenceOfElement(driver, objects_locatorType_Navigation.get(step), locator, Constants_TimeOuts.Element_TimeOut);
		if(element!=null){
			System.out.println("File Stroage Menu - is didsplayed");
			commonMethods.getViewOfElement(driver, element, browserName);
			System.out.println("File Stroage Menu - View Ported");
		}		*/
		//if(checkSubMenusDisplayed(driver).equalsIgnoreCase(Constants_FRMWRK.False)){

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get(step), objects_locatorType_Navigation.get(step), objects_objectType_Navigation.get(step),locator , "");
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+objects_step_Navigation.get(step), "Please refer above details for more details");
		}
		//}

	}

	public static void navigateToActionRequired(WebDriver driver) throws Throwable{
		commonMethods.switchToDefaultPage(driver);		
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Action Required"));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite Menu - Action Required"), objects_locatorType_Navigation.get("Usersite Menu - Action Required"), objects_objectType_Navigation.get("Usersite Menu - Action Required"),locator , "");
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - Usersite Menu - Action Required", "Please refer above details for more details");
		}


	}

	public static void navigateToActionsOverdue(WebDriver driver) throws Throwable{
		commonMethods.switchToDefaultPage(driver);		
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Actions Overdue"));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite Menu - Actions Overdue"), objects_locatorType_Navigation.get("Usersite Menu - Actions Overdue"), objects_objectType_Navigation.get("Usersite Menu - Actions Overdue"),locator , "");
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - Usersite Menu - Usersite Menu - Actions Overdue", "Please refer above details for more details");
		}


	}

	public static void navigateToMailedDocuments(WebDriver driver,String workflow) throws Throwable{
		commonMethods.switchToDefaultPage(driver);		
		String step="Usersite Menu - Mailed Documents";
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(step));

		/*WebElement element=ExplicitWaitUtil.waitForPresenceOfElement(driver, objects_locatorType_Navigation.get(step), locator, Constants_TimeOuts.Element_TimeOut);
		if(element!=null){
			commonMethods.getViewOfElement(driver, element, browserName);
			System.out.println("File Stroage Menu - View Ported");
		}	*/
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get(step), objects_locatorType_Navigation.get(step), objects_objectType_Navigation.get(step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+step, "Please refer above details for more details");
		}


	}

	public static String navigateToActionRequiredAndGetCount(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		commonMethods.switchToDefaultPage(driver);		
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Usersite Menu - Action Required Count",objects_locatorType_Navigation, objects_objectType_Navigation, objects_objectLocator_Navigation, input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_Navigation.get("Usersite Menu - Action Required Count"), " Navigate Failure - Usersite Menu - Action Required", "Please refer above details for more details");
		}

		return res;

	}
	public static String navigateToActionOverAndGetCount(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		commonMethods.switchToDefaultPage(driver);		
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Usersite Menu - Actions Overdue Count",objects_locatorType_Navigation, objects_objectType_Navigation, objects_objectLocator_Navigation, input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(objects_objectLocator_Navigation.get("Usersite Menu - Actions Overdue Count"), " Navigate Failure - Usersite Menu - Action Required", "Please refer above details for more details");
		}

		return res;

	}

	public static class Transmittals{
		public static void navigateToNewTransmittal(WebDriver driver,String workflow) throws Throwable{
			navigateToTramsmittals(driver,workflow);
			WaitUtil.pause(1);
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite SubMenu - New Transmittal"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite SubMenu - New Transmittal"), objects_locatorType_Navigation.get("Usersite SubMenu - New Transmittal"), objects_objectType_Navigation.get("Usersite SubMenu - New Transmittal"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - New Transmittal", "Please refer above details for more details");
			}			
		}

		public static void navigateToMysent(WebDriver driver,String workflow) throws Throwable{
			navigateToTramsmittals(driver,workflow);
			String key_step="Usersite SubMenu - My Sent";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - My Sent", "Please refer above details for more details");
			}
			PageLoadWaitUtil.waitForPagetoLoadCompletely(driver, Constants_TimeOuts.Page_Load_TimeOut);
		}

		public static void navigateToMyinbox(WebDriver driver,String workflow) throws Throwable{
			navigateToTramsmittals(driver,workflow);
			String key_step="Usersite SubMenu - My Inbox";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - My Inbox", "Please refer above details for more details");
			}
			
			PageLoadWaitUtil.waitForPagetoLoadCompletely(driver, Constants_TimeOuts.Page_Load_TimeOut);
		}

	}


	public static class FileStroage{

		public static void navigateToADocumentLibray(WebDriver driver,String refid,String testcaseName,String workflow,String SubMenuName) throws Throwable{
			navigateToDocumentsAndFileStorage(driver,workflow);
			String key_step="Usersite SubMenu";
			String loctor_default=objects_objectLocator_Navigation.get(key_step);			

			boolean subMenuDisplay=navigateToSubSiteDocumentLibrary(driver, workflow);
			if(subMenuDisplay==false){
				String locator=objects_objectLocator_Navigation.get(key_step).replaceAll("objectlocator", SubMenuName);
				objects_objectLocator_Navigation.put(key_step, locator);
				res=KeyMethods.f_performAction(driver, refid, testcaseName, workflow, key_step, objects_locatorType_Navigation, objects_objectType_Navigation, objects_objectLocator_Navigation, input);
				if(res.equals(Constants_FRMWRK.False)){
					CustomExceptions.Exit(locator, workflow+" Navigate Failure - "+SubMenuName, "Please refer above details for more details");
					objects_objectLocator_Navigation.put(key_step, loctor_default);
				}
				objects_objectLocator_Navigation.put(key_step, loctor_default);
			}			
		}

		public static boolean navigateToSubSiteDocumentLibrary(WebDriver driver,String workflow) throws Throwable{
			String key_step="Usersite SubMenu - Document Library";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			boolean subMenuDisplay=ElementPresence.isElementDisplayed(driver, objects_locatorType_Navigation.get(key_step), locator, Constants_TimeOuts.sync_element_load);
			if(subMenuDisplay==true){
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
				if(res.equals(Constants_FRMWRK.False)){
					CustomExceptions.Exit(locator, workflow+" Navigate Failure - "+key_step, "Please refer above details for more details");
				}
			}
			return subMenuDisplay;
		}


	}


	public static class Settings{

		public static void navigateToSettingsWheel(WebDriver driver,String workFlow) throws Exception{
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Settings Wheel", objects_locatorType_Navigation, objects_objectType_Navigation, objects_objectLocator_Navigation, input);
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(objects_objectLocator_Navigation.get("Settings Wheel"), " Navigate Failure - My Sent", "Please refer above details for more details");
			}			
		}

		public static void navigateToSiteContents(WebDriver driver,String workFlow) throws Exception{
			navigateToSettingsWheel(driver,workFlow);
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Site contents", objects_locatorType_Navigation, objects_objectType_Navigation, objects_objectLocator_Navigation, input);
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(objects_objectLocator_Navigation.get("Site contents"), " Navigate Failure - My Sent", "Please refer above details for more details");
			}
		}
	}
}



