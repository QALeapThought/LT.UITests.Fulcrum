package com.proj.suiteTRANSMITTALS.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.ElementPresence;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.Users;
import com.proj.util.AutoITUtil;
import com.proj.util.CustomExceptions;
import com.proj.util.Dialogs;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.ApplicationMethods;
import com.proj.utilFulcrum.LoginLib;
import com.proj.utilFulcrum.WebTableUtil;
import com.proj.utilFulcrum.WebTable_Generic;
import com.proj.utilFulcrum.WorkArounds;
import com.report.reporter.Reporting;
@SuppressWarnings("unused")
public class Transmittals_EntryPage extends TestSuiteBase{

	static String res="";
	static String input="";
	static String className=Transmittals_EntryPage.class.getSimpleName();
	private static Xls_Reader xlsReader_objects_Transmittals=new Xls_Reader(Constants.OR_TRANS_Path);

	public static LinkedHashMap<String,String>recipientListwithActionStatus=new LinkedHashMap<String,String>();

	protected static Hashtable<String,String>objects_step_Transmittals=null;
	protected static Hashtable<String,String>objects_locatorType_Transmittals=null; 
	protected static Hashtable<String,String>objects_objectType_Transmittals=null;
	protected static Hashtable<String,String>objects_objectLocator_Transmittals=null;

	protected static Hashtable<String,String>objects_step_Transmittals_toolbar=null;
	protected static Hashtable<String,String>objects_locatorType_Transmittals_toolbar=null; 
	protected static Hashtable<String,String>objects_objectType_Transmittals_toolbar=null;
	protected static Hashtable<String,String>objects_objectLocator_Transmittals_toolbar=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(Transmittals_EntryPage.class,  xlsReader_objects_Transmittals, "Objects_Transmittals", "Transmittals");
			fetchObjectRepository.getObjects(Transmittals_EntryPage.class,  xlsReader_objects_Transmittals, "Objects_Transmittals_Toolbar", "Transmittals_toolbar");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}

	public static void switchToTramsmittalEditFrame(WebDriver driver,String refID,String testcaseName,String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
	}
	public static void switchToTramsmittalViewFrame(WebDriver driver,String refID,String testcaseName,String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
	}

	public static void switchToDocumentsListFrame(WebDriver driver) throws Throwable{
		commonMethods.switchToSingleFrame(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.frame_documentList);
	}
	/**
	 * Clicks on Send button of a Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 * @throws Throwable 
	 */
	public static void clickSend(WebDriver driver,String workFlow,String testcasename) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Send", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	/**
	 * Clicks on Cancel button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable 
	 */
	public static void clickCancel(WebDriver driver,String refid,String testcasename,String workFlow,String buttonIndex) throws Throwable{
		String locator=objects_objectLocator_Transmittals.get("Tramsmittals-Cancel");
		locator=commonMethods.replaceString("index", locator, buttonIndex);
		KeyMethods.f_performAction(driver, refid, testcasename, workFlow, "Cancel", objects_locatorType_Transmittals.get("Tramsmittals-Cancel"), objects_objectType_Transmittals.get("Tramsmittals-Cancel"), locator, input);
	}
	public static void clickToolbarCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Cancel", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
	}
	/**
	 * Clicks on Submit button of a Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 */
	public static void clickSubmit(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Submit", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}

	/**
	 * Clicks on Delegate button of a Transmittal
	 * @author Anupama
	 * @param driver
	 * @param workFlow
	 * @throws Throwable 
	 */
	public static void clickDelegate(WebDriver driver,String testcasename, String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Delegate", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}


	/**
	 * Clicks on Cancel button of a View Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 */
	public static void clickViewCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
	}
	/**
	 * Clicks on Complete Action button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */
	public static void clickCompleteAction(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Complete Action", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Complete Action -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	/**
	 * Clicks on Forward button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */
	public static void clickForward(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Forward", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Forward -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	/**
	 * Clicks on ReplyAll button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */

	public static void clickReplyAll(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-ReplyAll", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-ReplyAll -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}

	public static void clickCloseTransmittal(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Close Transmittal", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Close Transmittal -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	public static void clickTransmittalHelpLink(WebDriver driver,String workFlow,String refID,String testcasename) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-Help", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		waitInvisiblilityofWorkingTitle(driver);	

	}

	public static void clickActionsGridToggleIcon(WebDriver driver,String workFlow,String refID,String testcasename) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalViewFrame(driver, refID, testcasename, workFlow);

		String step="Tramsmittals-View -Actions Grid-Expandable Icon";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, step, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);

	}
	

	public static String checkTransmittalHelpEnabled(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Help-Is Enabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, "Tramsmittals-Help-Is Enabled-Failure", "Please refer above details for more details");
		}
		return res;
	}

	public static void validate_TransmittalhelpWindow(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		int helpWindow=ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		System.out.println(helpWindow);
		if(helpWindow==2){
			Reporting.logStep(driver, refID, testcasename, workflow+"Help Window", "Help Window is displayed", Constants_FRMWRK.Pass);
		}else{
			Reporting.logStep(driver, refID, testcasename, workflow+"Help Window", "Help Window is not displayed", Constants_FRMWRK.Fail);
		}
		ApplicationMethods.closeAllDialogs(driver, refID, testcasename);

	}

	public static String checkCancelIsEnabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel - IsEnabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;

	}
	public static String checkCancelIsDisabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel - IsDisabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;

	}

	public static String checkCloseTransmittalIsEnabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Close Transmittal - IsEnabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;

	}

	public static String checkCloseTransmittalIsDisabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Close Transmittal - IsDisabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;

	}
	/**
	 * Waits until the Working On it Tile disappears from page
	 * @author shaikka
	 * @param driver
	 */
	public static void waitInvisiblilityofWorkingTitle(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, com.proj.objectRepository.ObjRepository.heading_working, Constants_TimeOuts.Overlay_disappear);
		System.out.println("Working.. invisibility.."+tt);
	}

	public static int getRecieverUserCount(Hashtable<String,String>data){
		int flag=1;
		if(data.get("To").contains(Constants.delimiter_data)){
			String[] receivers=commonMethods.splitString(data.get("To"), Constants.delimiter_data);
			flag=receivers.length;			
		}
		logsObj.log("Receiver count is "+flag);
		return flag;
	}

	public static String[] getReciepientNames(Hashtable<String,String>data){
		int flag=1;
		String[] recipients = null;
		if(data.get("To").contains(Constants.delimiter_data)){
			recipients=commonMethods.splitString(data.get("To"), Constants.delimiter_data);

		}
		logsObj.log("Recipients Names are"+recipients);
		return recipients;
	}
	/**
	 * Enter the require details in To field for a Transmittal
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	private static String enterTo(WebDriver driver,String testcasename,String workFlow,String data) throws Throwable{
		ApplicationMethods.waitForOverlayToDisappear(driver);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-To", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, workFlow+"To-Failure", "Due to above failure could not continue execution ,Please refer above details for more details");
		}
		return res;
	}
	private static String enterToMutiple(WebDriver driver,String testcasename,String workFlow,String data) throws Throwable{
		ApplicationMethods.waitForOverlayToDisappear(driver);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-To Mutiple", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, workFlow+"To-Failure", "Due to above failure could not continue execution ,Please refer above details for more details");
		}
		return res;
	}
	/**
	 * Enter the required details to create the Transmittal record.
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	public static Hashtable<String, String> createAndSendTransmittalRecord(String appName,WebDriver driver,String refID,String testcasename,String workFlow,Hashtable<String,String> data) throws Throwable{
		Hashtable<String,String>returnData = new Hashtable<String,String>();

		String step_name;

		step_name="To";
		if(getRecieverUserCount(data)>1){

			String[] receivers=commonMethods.splitString(data.get(step_name), Constants.delimiter_data);
			String recp_prv="";
			String recp_cur="";
			String my_res="";
			int counter=1;
			for (String rec:receivers){
				if(counter==1){
					recp_cur=enterTo( driver,testcasename, workFlow, rec);
				}else{
					recp_cur=enterToMutiple(driver,testcasename, workFlow, rec);
				}


				if(receivers.length!=counter){
					my_res=recp_prv+recp_cur+Constants.delimiter_data;
				}else{
					my_res=recp_prv+recp_cur;
				}
				recp_prv=my_res;
				counter=counter+1;
			}
			returnData.put(step_name, recp_prv);
		}else{
			res=enterTo(driver,testcasename, workFlow, data.get(step_name));
			returnData.put(step_name, res);
		}


		returnData.put("Tramsmittals-ToCount",String.valueOf(getRecieverUserCount(data)));

		step_name="CC";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		returnData.put(step_name, res);
		step_name="Subject";		
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name)+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		returnData.put(step_name, res);
		step_name="Is Confidential";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		String isConfi=res;
		returnData.put(step_name, res);

		step_name="Mail Type";
		if(!isConfi.equalsIgnoreCase(Constants_FRMWRK.Tick)){			
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			returnData.put(step_name, res);
		}else{			
			returnData.put(step_name, data.get(step_name));
		}
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);

		step_name="Response Required";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		returnData.put(step_name, res);

		step_name="Reason For Issue";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		returnData.put(step_name, res);


		step_name="Discipline";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			returnData.put(step_name, res);
		}

		step_name="Document Type";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			returnData.put(step_name, res);
		}

		step_name="Contract#";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		returnData.put(step_name, res);

		String dueDate = null;
		/*dueDate=setDueDate(data.get("Action-Level2"));*/
		
		
		step_name="Due Date";
		if(data.get(step_name).equalsIgnoreCase("Overdue")){
			returnData.put("Overdue", Constants_FRMWRK.Yes);
		}else{
			returnData.put("Overdue", Constants_FRMWRK.No);
		}
		dueDate=setDueDate(data.get(step_name));		
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, dueDate);
		returnData.put(step_name, res);

		step_name="Message";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		returnData.put(step_name, res);

		step_name="Single Response per Org?";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			if(data.get(step_name).equalsIgnoreCase(Constants_FRMWRK.Tick)){
				returnData.put(step_name, Constants_FRMWRK.Yes);
			}else{
				returnData.put(step_name, Constants_FRMWRK.No);
			}
		}


		step_name="Notify All?";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			if(data.get(step_name).equalsIgnoreCase(Constants_FRMWRK.Tick)){
				returnData.put(step_name, Constants_FRMWRK.Yes);
			}else{
				returnData.put(step_name, Constants_FRMWRK.No);
			}
		}


		AttachDocumentPage.NewMail.attachMailDocuments(driver, refID, testcasename, workFlow, data);

		clickSend(driver, workFlow,testcasename);
		WaitUtil.pause(Constants_TimeOuts.processToComplete);
		ApplicationMethods.closeAllDialogs(driver, refID, testcasename);

		returnData.put("Send Date", DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy"));
		returnData.put("Sender", data.get("Login_User"));
		returnData.put("Action-Level2", data.get("Action-Level2"));

		return returnData;
	}

	/**
	 * Edit the Transmittal record with required details and Submit
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public static void editAndSubmitTransmittalRecord(WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data,String action,String userloginName,LinkedHashMap<String,String>actionStatusData) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		String condition="Response Required";

		if(data.get(condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_ACCEPT)|| data.get(condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_APPROVE)){
			if(action.equalsIgnoreCase("APPROVED") || action.equalsIgnoreCase("REJECTED") || action.equalsIgnoreCase("OVERDUE")){
				if(action.equalsIgnoreCase("OVERDUE")){
					//Hard cording the Overdue action to reject as per the test case need to evaluate later
					//action="REJECTED";
					action="APPROVED";
				}
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Approve/Reject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
			}
			else if(action.equalsIgnoreCase("ACCEPTED") || action.equalsIgnoreCase("DECLINED")){
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Approve/Reject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
			}


		}
		else if(data.get(condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_REVIEW)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Comments", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
		}			

		
		clickSubmit(driver, workFlow);
		if(data.get("To").contains(Constants.delimiter_data)&& action.equalsIgnoreCase("Rejected")){
			actionStatusData=Transmittals_EntryPage.updateRecipientsActionStatusToComplete(data.get("To"));
		}else{
			actionStatusData=Transmittals_EntryPage.updateRecipientActionStatusToComplete(userloginName);
		}

		Transmittals_EntryPage.validateActionStatus(driver, refID, testcaseName, workFlow ,actionStatusData);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}
	/**
	 * Forward the tramsmittal with required details
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static Hashtable<String,String> forwardAndSendTransmittalRecord(WebDriver driver,String refID,String testcasename,String workFlow,Hashtable<String,String>data,Hashtable<String,String>transmittalData) throws Throwable {		
		//Hashtable<String,String>returnData = new Hashtable<String,String>();
		clickForward(driver, workFlow);
		ApplicationMethods.waitForOverlayToDisappear(driver);
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcasename, workFlow);
		transmittalData.put("Sender", transmittalData.get("To"));
		res=enterTo(driver,testcasename, workFlow, data.get("ForwardTo"));
		transmittalData.put("To", res);

		String step_name="Mail Type";
		if(!data.get("Is Confidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			transmittalData.put(step_name, res);
		}else{			
			transmittalData.put(step_name, data.get(step_name));
		}
		step_name="Response Required";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		transmittalData.put(step_name, res);

		step_name="Single Response per Org?";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			if(data.get(step_name).equalsIgnoreCase(Constants_FRMWRK.Tick)){
				transmittalData.put(step_name, Constants_FRMWRK.Yes);
			}else{
				transmittalData.put(step_name, Constants_FRMWRK.No);
			}
		}


		step_name="Notify All?";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			if(data.get(step_name).equalsIgnoreCase(Constants_FRMWRK.Tick)){
				transmittalData.put(step_name, Constants_FRMWRK.Yes);
			}else{
				transmittalData.put(step_name, Constants_FRMWRK.No);
			}
		}

		step_name="Reason For Issue";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		transmittalData.put(step_name, res);

		String dueDate = null;
		dueDate=setDueDate(data.get("Action-Level2"));
		transmittalData.put("Action-Level2", data.get("Action-Level2"));
		step_name="Due Date";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, dueDate);
		transmittalData.put(step_name, res);

		step_name="Contract#";
		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		transmittalData.put(step_name, res);

		step_name="Discipline";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){

			WebElement element=ElementPresence.elementDisplayed(driver, objects_locatorType_Transmittals.get("Tramsmittals-"+step_name), objects_objectLocator_Transmittals.get("Tramsmittals-"+step_name), Constants_TimeOuts.Element_optional_TimeOut);
			commonMethods.getViewOfElement(driver, element, browserName);
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			transmittalData.put(step_name, res);
		}

		step_name="Document Type";
		if(!commonMethods.checkKeyandgetValue(data, step_name).equalsIgnoreCase(Constants_FRMWRK.False)){
			res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
			transmittalData.put(step_name, res);
		}






		String step_name_forward="Parent Mail Message";
		step_name="Message";
		res=KeyMethods.f_fetchElementDetails(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name_forward, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));


		res=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, "Tramsmittals-"+step_name, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(step_name));
		transmittalData.put(step_name, res);




		clickSend(driver, workFlow,testcasename);
		WaitUtil.pause(Constants_TimeOuts.processToComplete);		
		//WorkArounds.getViewPortOfPage(driver,browserName);
		ApplicationMethods.closeAllDialogs(driver, refID, testcasename);

		return transmittalData;
	}
	/**
	 * ReplyAll the transmittal with required details
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static void replyAllAndSendTransmittalRecord(String appName,WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{		
		clickReplyAll(driver, workFlow);
		ApplicationMethods.waitForOverlayToDisappear(driver);
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
		Hashtable<String,String>returnData = new Hashtable<String,String>();
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-ReplyAll", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		if (res.isEmpty())
		{
			CustomExceptions.Exit(testcaseName, "Tramsmittals-ReplyAll -Failure", "Please refer above details for more details");
		}

		if(appName.equals(Constants.App_Fluid)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Issue Reason", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("IssueReason"));
		}
		if(!data.get("IsConfidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-TxType", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("TxType"));
			returnData.put("Tramsmittals-TxType", res);
		}else{			
			returnData.put("Tramsmittals-TxType", data.get("TxType"));
		}
		clickSend(driver, workFlow,testcaseName);
		WorkArounds.getViewPortOfPage(driver,browserName);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}

	/**
	 * Delegate and send transmittal to a user
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static void delegateAndSendTransmittalRecord(String appName,WebDriver driver,String testcasename,String workFlow,Hashtable<String,String>data) throws Throwable{		
		clickDelegate(driver, testcasename, workFlow);
		System.out.println(data.get("DelegateTo"));
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		WorkArounds.deFocusCursor(driver);
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-DelegateTo", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("DelegateTo"));
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

	}


	public static void verifyAttachedFiles(WebDriver driver,String testcasename,String refid,String workflow,Hashtable<String,String>data) throws Throwable{
		if(data.get(Constants_Workflow.Fulcrum_WorkFlow_Data_RESREQ_Condition).equalsIgnoreCase(Constants_Workflow.Fulcrum_WorkFlow_REVIEW) && !data.get("Review Sheet").isEmpty()){
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-View - Attached Review Document Files").replaceAll("docName", data.get("Review Sheet"));
			objects_objectLocator_Transmittals.put("Tramsmittals-View - Attached Review Document Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View - Attached Review Document Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("Review Sheet"));

		}
		String key_data_attachDoc_documentRegister="AttachDocuments";
		if(!data.get(key_data_attachDoc_documentRegister).isEmpty()){			
			String key_data_attachDoc_attachDocumentNames="AttachDocumentName";
			String key_step_attachDoc="Tramsmittals-View -Transmittal Files";

			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			if(data.get(key_data_attachDoc_attachDocumentNames).contains(Constants.delimiter_data)){
				String[] getattachDocuments=commonMethods.splitString(data.get(key_data_attachDoc_attachDocumentNames), Constants.delimiter_data);
				String locator_default=objects_objectLocator_Transmittals.get(key_step_attachDoc);

				for (int doc=0;doc<getattachDocuments.length;doc++){					
					String locator=objects_objectLocator_Transmittals.get(key_step_attachDoc).replaceAll("docName", getattachDocuments[doc]);
					objects_objectLocator_Transmittals.put(key_step_attachDoc, locator);
					try{
						KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, key_step_attachDoc, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, getattachDocuments[doc]);
					}catch (Throwable t){}
					objects_objectLocator_Transmittals.put(key_step_attachDoc, locator_default);
				}				

			}else{
				String locator_default=objects_objectLocator_Transmittals.get(key_step_attachDoc);
				String locator=objects_objectLocator_Transmittals.get(key_step_attachDoc).replaceAll("docName", data.get(key_data_attachDoc_attachDocumentNames));
				objects_objectLocator_Transmittals.put(key_step_attachDoc, locator);
				try{
					KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, key_step_attachDoc, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(key_data_attachDoc_attachDocumentNames));
				}catch (Throwable t){}
				objects_objectLocator_Transmittals.put(key_step_attachDoc, locator_default);

			}

		}	

		String key_data_supportDoc_documentRegister="AttachSupportDocuments";
		if(!data.get(key_data_supportDoc_documentRegister).isEmpty()){
			String key_data_supportDoc_attachDocumentNames="AttachSupportDocumentName";
			String key_step_supportDoc="Tramsmittals-View -Supporting Document Files";

			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			if(data.get(key_data_supportDoc_attachDocumentNames).contains(Constants.delimiter_data)){
				String[] getattachDocuments=commonMethods.splitString(data.get(key_data_supportDoc_attachDocumentNames), Constants.delimiter_data);
				String locator_default=objects_objectLocator_Transmittals.get(key_step_supportDoc);
				for (int doc=0;doc<getattachDocuments.length;doc++){					
					String locator=objects_objectLocator_Transmittals.get(key_step_supportDoc).replaceAll("docName", getattachDocuments[doc]);
					objects_objectLocator_Transmittals.put(key_step_supportDoc, locator);
					try{
						KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, key_step_supportDoc, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, getattachDocuments[doc]);
					}catch (Throwable t){

					}
					objects_objectLocator_Transmittals.put(key_step_supportDoc, locator_default);
				}	


			}else{
				String locator_default=objects_objectLocator_Transmittals.get(key_step_supportDoc);
				String locator=objects_objectLocator_Transmittals.get(key_step_supportDoc).replaceAll("docName", data.get(key_data_supportDoc_attachDocumentNames));
				objects_objectLocator_Transmittals.put(key_step_supportDoc, locator);
				try{
					KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, key_step_supportDoc, objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get(key_data_supportDoc_attachDocumentNames));
				}catch (Throwable t){}
				objects_objectLocator_Transmittals.put(key_step_supportDoc, locator_default);
			}
		}

	}

	public static void verifyAttachedFilesAndClick(String appName,WebDriver driver,String testcasename,String refid,String workflow,Hashtable<String,String>data) throws Throwable{

		if(!data.get("AttachDocuments").isEmpty()){	
			int counter=1;
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			
			if(data.get("AttachDocumentName").contains(Constants.delimiter_data)){
				String[] docs=commonMethods.splitString(data.get("AttachDocumentName"), Constants.delimiter_data);

				for (String doc :docs){
					downloadAMailedAttachedDocumentFile(driver, testcasename, refid, workflow, data, doc);
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					if (counter%3 == 0){
						AutoITUtil.robo_pageDown();
					}
					counter=counter+1;
				}
			}else{
				downloadAMailedAttachedDocumentFile(driver, testcasename, refid, workflow, data, data.get("AttachDocumentName"));				
			}
		}		
		if(!data.get("AttachSupportDocuments").isEmpty()){
			int counter=1;
			
			if(data.get("AttachSupportDocumentName").contains(Constants.delimiter_data)){
				String[] docs=commonMethods.splitString(data.get("AttachSupportDocumentName"), Constants.delimiter_data);

				for (String doc :docs){
					downloadAMailedSupportDocumentFile(driver, testcasename, refid, workflow, data, doc);
					WaitUtil.pause(Constants_TimeOuts.sync_element_load);
					if (counter%4 == 0){
						AutoITUtil.robo_pageDown();
						WaitUtil.pause(Constants_TimeOuts.sync_element_load);
					}
					counter=counter+1;
				}
			}else{
				downloadAMailedSupportDocumentFile(driver, testcasename, refid, workflow, data, data.get("AttachSupportDocumentName"));				
			}


		}
	}

	private static void downloadAMailedAttachedDocumentFile(WebDriver driver,String testcasename,String refid,String workflow,Hashtable<String,String>data,String documentName) throws Throwable {
		String step_transmittal_files="Tramsmittals-View -Transmittal Files";
		String step_transmittal_files_download="Tramsmittals-View -Transmittal Files-Download";
		String constantForTxDoc="DownloadTX_";

		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		String locator_default=objects_objectLocator_Transmittals.get(step_transmittal_files);

		String locator_download_default=objects_objectLocator_Transmittals.get(step_transmittal_files_download);

		String locator=objects_objectLocator_Transmittals.get(step_transmittal_files).replaceAll("docName", documentName);
		objects_objectLocator_Transmittals.put(step_transmittal_files_download, locator);

		KeyMethods.f_performAction(driver,refid,testcasename,workflow,step_transmittal_files_download,objects_locatorType_Transmittals,objects_objectType_Transmittals,objects_objectLocator_Transmittals,input);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		if(browserName.equalsIgnoreCase(Constants.browserie)){
			ExplicitWaitUtil.waitforNumberOfWindows(driver, Constants_TimeOuts.sync_element_load, 2);
			ExplicitWaitUtil.waitforNumberOfWindows(driver, Constants_TimeOuts.sync_frame_load, 1);			
			
			String downloadFile= Constants.DataFileLocation_Transmittal+constantForTxDoc+documentName;
			//Dialogs.ViewDownloads(driver,"View Downloads - Internet Explorer", downloadFile,refid,testcasename,workflow);
			Dialogs.ViewDownloads(driver, Constants.pageTitle_MyInbox+Constants.pageTitle_IE, Constants.dialogTitle_Downloads+Constants.pageTitle_IE, downloadFile,refid,testcasename,workflow);
			objects_objectLocator_Transmittals.put(step_transmittal_files,locator_default);
			objects_objectLocator_Transmittals.put(step_transmittal_files_download,locator_download_default);

		}
		
	}

	private static void downloadAMailedSupportDocumentFile(WebDriver driver,String testcasename,String refid,String workflow,Hashtable<String,String>data,String documentName) throws Throwable {
		String step_transmittal_files="Tramsmittals-View -Supporting Document Files";
		String step_transmittal_files_download="Tramsmittals-View -Supporting Document Files-Download";
		String constantForTxDoc="DownloadSupport_";

		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		String locator_default=objects_objectLocator_Transmittals.get(step_transmittal_files);

		String locator_download_default=objects_objectLocator_Transmittals.get(step_transmittal_files_download);

		String locator=objects_objectLocator_Transmittals.get(step_transmittal_files).replaceAll("docName", documentName);
		objects_objectLocator_Transmittals.put(step_transmittal_files_download, locator);

		KeyMethods.f_performAction(driver,refid,testcasename,workflow,step_transmittal_files_download,objects_locatorType_Transmittals,objects_objectType_Transmittals,objects_objectLocator_Transmittals,input);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		if(browserName.equalsIgnoreCase(Constants.browserie)){
			ExplicitWaitUtil.waitforNumberOfWindows(driver, Constants_TimeOuts.sync_element_load, 2);
			ExplicitWaitUtil.waitforNumberOfWindows(driver, Constants_TimeOuts.sync_frame_load, 1);	
			
			String downloadFile= Constants.DataFileLocation_Transmittal+constantForTxDoc+documentName;
			Dialogs.ViewDownloads(driver,Constants.pageTitle_MyInbox+Constants.pageTitle_IE,Constants.dialogTitle_Downloads+Constants.pageTitle_IE, downloadFile,refid,testcasename,workflow);

			objects_objectLocator_Transmittals.put(step_transmittal_files,locator_default);
			objects_objectLocator_Transmittals.put(step_transmittal_files_download,locator_download_default);

		}
		
	}


	private static String getDueDateInUS(String action){
		String dueDate;  
		dueDate=DateUtil.getCurrentDateInRequiredDateFormat("MM/dd/yyyy");
		if(action.equalsIgnoreCase("OverDue")){
			dueDate=DateUtil.dateIncremterInUSFormat(dueDate, -2);
		}else{
			dueDate=DateUtil.dateIncremterInUSFormat(dueDate, 2);
		}

		return dueDate;
	}
	private static String getDueDateInNZ(String action){
		String dueDate;	
		dueDate=DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy");
		if(action.equalsIgnoreCase("OverDue")){
			dueDate=DateUtil.dateIncremterInNonUSFormat(dueDate, -2);
		}else{
			dueDate=DateUtil.dateIncremterInNonUSFormat(dueDate, 2);
		} 

		return dueDate;
	}

	public static String setDueDate(String action){
		String duedate;
		duedate=getDueDateInNZ(action);
		/*if(appName.equalsIgnoreCase(Constants.App_Fulcrum)){

		}else{*/
		//duedate=getDueDateInUS(action);
		//}
		return duedate;
	}

	static void clickEditTab(WebDriver driver,String refid,String testcasename,String workflow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Edit", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);

	}

	//   Actions 

	public static void validateActionsCount(WebDriver driver,String refid,String testcasename,String workflow,LinkedHashMap<String,String>actionStatusData){
		String step="Tramsmittals-View - Actions Completed";
		WebElement element=ElementPresence.elementDisplayed(driver, objects_locatorType_Transmittals.get(step), objects_objectLocator_Transmittals.get(step), Constants_TimeOuts.Element_optional_TimeOut);

		if(element==null){
			Reporting.logStep(driver, refid, testcasename, workflow+"Validate the actions count",  objects_objectLocator_Transmittals.get(step)+" is not displayed." , Constants_FRMWRK.Fail);
		}else{
			String act_text=element.getText();			

			int count_comp = Collections.frequency(new ArrayList<String>(actionStatusData.values()), "Completed");
			int count_total = actionStatusData.size();					

			StringBuilder sp=new StringBuilder();
			String exp_text=sp.append("(").append(count_comp).append(" of ").append(count_total).append(" completed)").toString();
			if(act_text.equals(exp_text)){
				Reporting.logStep(driver, refid, testcasename, workflow+"Validate the actions count",  "Actions count actual:-"+act_text+" matches with the expected:-"+exp_text , Constants_FRMWRK.Pass);
			}else{
				Reporting.logStep(driver, refid, testcasename, workflow+"Validate the actions count",  "Actions count actual:-"+act_text+" doesnot match with the expected:-"+exp_text , Constants_FRMWRK.Fail);
			}
		}

	}
	public static LinkedHashMap<String,String>loadRecipientActionStatus(Hashtable<String,String>transmittalData,String action){
		recipientListwithActionStatus.clear();
		String key="To";
		/*	if(action.equalsIgnoreCase("Reply")||action.equalsIgnoreCase("Forward")){
			key="Tramsmittals-ForwardReplyTo";
		}*/

		if(transmittalData.get(key).contains(Constants.delimiter_data)){
			String[] recipients=commonMethods.splitString(transmittalData.get(key), Constants.delimiter_data);
			for (String recp:recipients){
				recipientListwithActionStatus.put(recp, "Not Started");
			}
		}else{
			recipientListwithActionStatus.put(transmittalData.get(key), "Not Started");
		}
		return recipientListwithActionStatus;
	}
	public static void validateActionStatus(WebDriver driver,String refid,String testcasename,String workflow,LinkedHashMap<String,String>recipientList) throws Throwable{
		clickActionsGridToggleIcon(driver, workflow, refid, testcasename);	

		for (String recipientName : recipientList.keySet())  
		{ 
			// search  for value 
			String actionStatus = recipientList.get(recipientName); 
			System.out.println("Key = " + recipientName + ", Value = " + actionStatus); 

			//WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcasename,  workflow+"-Check Action status for "+recipientName, Constants_FRMWRK.FindElementByID,  objects_objectLocator_Transmittals.get("Tramsmittals-View -Actions Grid"), "contains", recipientName, "Text", actionStatus, 3, 4);
			res=WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcasename,  workflow+"-Check Action status for "+recipientName, Constants_FRMWRK.FindElementByID,  objects_objectLocator_Transmittals.get("Tramsmittals-View -Actions Grid"), "contains", recipientName, "Text", actionStatus, Constants_Workflow.Fulcrum_View_Column_AssignedTo, Constants_Workflow.Fulcrum_View_Column_Status);
		}

		validateActionsCount(driver, refid, testcasename, workflow, recipientList);

	}

	public static LinkedHashMap<String,String>updateRecipientActionStatusToComplete(String recipientName){

		recipientListwithActionStatus.put(recipientName, "Completed");
		return recipientListwithActionStatus;
	}

	public static LinkedHashMap<String,String>updateRecipientsActionStatusToComplete(String recipientsName){
		if(recipientsName.contains(Constants.delimiter_data)){
			String[] recipients=commonMethods.splitString(recipientsName, Constants.delimiter_data);
			for (String recp:recipients){
				recipientListwithActionStatus.put(recp, "Completed");
			}
		}
		return recipientListwithActionStatus;
	}


	public static void validateMailedDocumentSentFlag(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		String page ="File Storage";

		Navigations_Fulcrum.FileStroage.navigateToADocumentLibray(driver, refid, testcasename, workflow, data.get("AttachDocuments"));
		AttachDocumentPage.selectSmartFolder(driver, workflow, refid, testcasename, page, data, "AttachDocuments", "Attach");

		if(data.get("AttachDocumentName").contains(Constants.delimiter_data)){
			String[] docs=commonMethods.splitString(data.get("AttachDocumentName"), Constants.delimiter_data);

			for (String doc :docs){
				WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,workflow+" "+page+">>"+data.get("AttachDocumentName")+" - Malied Doc -Sent?",Constants.container_property_summary, data.get("AttachDocuments"),"contains",doc,Constants.Webtable_actionColumnType_Text,"Yes",Constants_Workflow.Fulcrum_View_Column_Name,Constants_Workflow.Fulcrum_View_Column_Sent);
			}
		}else{
			res=WebTable_Generic.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,workflow+" "+page+">>"+data.get("AttachDocumentName")+" - Malied Doc -Sent?",Constants.container_property_summary, data.get("AttachDocuments"),"contains",data.get("AttachDocumentName"),Constants.Webtable_actionColumnType_Text,"Yes",Constants_Workflow.Fulcrum_View_Column_Name,Constants_Workflow.Fulcrum_View_Column_Sent);
		}
	}



}
