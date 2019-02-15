package com.proj.util;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;

import atu.utils.windows.handler.WindowElement;
import atu.utils.windows.handler.WindowHandler;
import atu.utils.windows.handler.exceptions.WindowsHandlerException;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.library.commonMethods;
import com.report.reporter.Reporting;

public class Dialogs {

	public static void userAuthentication(WebDriver driver,String browser,String url,String title,String username,String password) throws WindowsHandlerException, InterruptedException{
		WindowElement windowElement;
		WindowElement authwindowElement = null;
		AutoITUtil.loadJocobDLL();
		Thread.sleep(3000);

		WindowHandler handle=AutoIT_ActionsUtil.getHandler();

		if(browser.equalsIgnoreCase("ie")){
			if(commonMethods.getBrowserVersion(driver).contains("11")){
				// windowElement=AutoIT_ActionsUtil.getDialog(handle,"WebDriver - Internet Explorer");
				windowElement=AutoIT_ActionsUtil.getDialog(handle,title+" - Internet Explorer");
			}else{
				//windowElement=AutoIT_ActionsUtil.getDialog(handle,"WebDriver - Windows Internet Explorer");
				windowElement=AutoIT_ActionsUtil.getDialog(handle,title+" - Windows Internet Explorer");
			}			
			authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Windows Security");
			user_auth_ie(handle, authwindowElement, username, password);
		}
		else if(browser.equalsIgnoreCase("chrome")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,url+" - Google Chrome");
			authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Authentication Required");
		}
		else if (browser.equalsIgnoreCase("firefox")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,"Mozilla Firefox Start Page - Mozilla Firefox");
			authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Authentication Required");
		}


		/*	List<WindowElement> editItems=AutoIT_ActionsUtil.elementsByLocalizedControlType(handle, authwindowElement, "edit");
		WindowElement usernameElement=editItems.get(0);
		AutoIT_ActionsUtil.type(handle, usernameElement, username);

		AutoIT_ActionsUtil.clear(handle, editItems.get(2));		
		AutoIT_ActionsUtil.type(handle, editItems.get(2), password);

		List<WindowElement> buttonItems=AutoIT_ActionsUtil.elementsByLocalizedControlType(handle, authwindowElement, "button");
		AutoIT_ActionsUtil.clickElement(handle, buttonItems.get(0));	
		 */

	}


	public static void userAuth_robo(String domain,String userName,String password) throws Exception {

		//wait - increase this wait period if required
		Thread.sleep(5000);

		//create robot for keyboard operations
		Robot rb = new Robot();

		//Enter user name by ctrl-v
		StringSelection username = new StringSelection(domain+"\\"+userName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);    
		Reporting.logStep("Login - User Name", "Successfully entered "+userName, Constants_FRMWRK.Pass);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		//tab to password entry field
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);

		//Enter password by ctrl-v
		StringSelection pwd = new StringSelection(password);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
		Reporting.logStep( "Login - Password", "Successfully entered "+password, Constants_FRMWRK.Pass);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		//press enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		//wait
		Thread.sleep(5000);
	}   


	public static void browse(String browser,String filePath,String dialogName) throws WindowsHandlerException, InterruptedException{
		WindowElement windowElement = null;
		WindowElement uploadElement=null;
		AutoITUtil.loadJocobDLL();
		Thread.sleep(3000);

		WindowHandler handle=AutoIT_ActionsUtil.getHandler();

		if(browser.equalsIgnoreCase("ie")){// dialogname reg expression for all browsers
			windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Windows Internet Explorer");
			uploadElement = handle.findElementByName(windowElement,"Choose File to Upload");
		}else if (browser.equalsIgnoreCase("chrome")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Google Chrome"); 
			uploadElement = handle.findElementByName(windowElement,"Open");
		}
		else if(browser.equalsIgnoreCase("firefox")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Mozilla Firefox");
			uploadElement = handle.findElementByName(windowElement,"File Upload");
		}

		WindowElement fileNamePath = handle.findElementByNameAndClassName(
				uploadElement, "File name:", "Edit");
		handle.typeKeys(fileNamePath, filePath);

		WindowElement openButton = handle.findElementByName(uploadElement,
				"Open");
		handle.click(openButton);
	}

	private static void user_auth_ie(WindowHandler handle,WindowElement authwindowElement,String username,String password) throws WindowsHandlerException{
		WaitUtil.pause(3000L);
		WindowElement userNameElement=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "User name");
		AutoIT_ActionsUtil.clear(handle, userNameElement);		
		AutoIT_ActionsUtil.type(handle, userNameElement, username);

		WaitUtil.pause(1000L);
		WindowElement passwordElement=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "Password");
		AutoIT_ActionsUtil.clear(handle, passwordElement);		
		AutoIT_ActionsUtil.type(handle, passwordElement, password);

		WaitUtil.pause(1000L);
		WindowElement button_OK=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "OK");
		AutoIT_ActionsUtil.clickElement(handle, button_OK);	
	}
	
public static String ViewDownloads(WebDriver driver,String notificationPageTitle,String download_dailogName,String filePath,String refid,String testcasename,String workflow) throws Exception{
		String flag =Constants_FRMWRK.False;
		try{
			File file =new File(filePath);
			if(file.exists()){
				file.delete();
			}
			/*Thread.sleep(5000);
			AutoITUtil.openDownloadsWindow(driver);
			Thread.sleep(3000);*/
			AutoITUtil.loadJocobDLL();
			WindowHandler handle=AutoIT_ActionsUtil.getHandler();
			
			
			Thread.sleep(5000);
			//String notificationPageTitle="Mail - My Inbox - Internet Explorer";
			boolean yes=handle.isWindowPresent(notificationPageTitle);
			WindowElement windowElementNotification=AutoIT_ActionsUtil.getDialog(handle,notificationPageTitle);
			
			boolean isDisplayed=false;
			
			do{
				WindowElement windowElementNotificationopen=AutoIT_ActionsUtil.elementByName(handle, windowElementNotification, "Open");
				if(AutoIT_ActionsUtil.isElementDisplayed(handle, windowElementNotificationopen)==true){
					isDisplayed=true;
					break;
				}
				Thread.sleep(5000);
			}while (isDisplayed==false);
			
			
			
			AutoITUtil.openDownloadsWindow(driver);
			Thread.sleep(3000);

			WindowElement windowElement=AutoIT_ActionsUtil.getDialog(handle,download_dailogName);
			WindowElement button_clearList=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Clear list");
			AutoIT_ActionsUtil.clickElement(handle, button_clearList);


			List<WindowElement> elements=AutoIT_ActionsUtil.elementsByLocalizedControlType(handle, windowElement, "split button");
			AutoIT_ActionsUtil.clickElement(handle, elements.get(1));
			AutoITUtil.robo_click_saveAs();

			

			//*********************** Save As Dialog ***********************************************************
			WindowElement saveAsDailog_element=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Save As");		Thread.sleep(1000);
			AutoITUtil.robo_click_backspace();
			WindowElement editElement=AutoIT_ActionsUtil.elementByClassName(handle, saveAsDailog_element, "Edit");

			AutoIT_ActionsUtil.clear(handle, editElement);		
			AutoIT_ActionsUtil.type(handle, editElement, filePath);

			WindowElement button_save=AutoIT_ActionsUtil.elementByName(handle, saveAsDailog_element, "Save");
			AutoIT_ActionsUtil.clickElement(handle, button_save);
			WindowElement button_close=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Close");
			AutoIT_ActionsUtil.clickElement(handle, button_close);
			Thread.sleep(3000);			
			Reporting.logStep(driver, refid, testcasename, workflow+"-Download File", "Sucessfully downloaded the file at "+filePath, Constants_FRMWRK.Pass);
			flag=Constants_FRMWRK.True;
		}catch (Throwable t){
			t.printStackTrace();
			Reporting.logStep(driver, refid, testcasename,workflow+"-Download File",   "Unable to download the file "+filePath+" due to -->"+commonMethods.getStackTrace(t), Constants_FRMWRK.Fail);
		}
		return flag;
	}

public static String checkDefaultDialog(WebDriver driver,String browser,String title,String dailogName,String refid,String testcasename,String workflow,String buttonToClick) throws Exception{
	String flag =Constants_FRMWRK.False;
	try{
		
		WindowElement windowElement;
		WindowElement dialogwindowElement = null;
		AutoITUtil.loadJocobDLL();
		Thread.sleep(3000);

		WindowHandler handle=AutoIT_ActionsUtil.getHandler();

		if(browser.equalsIgnoreCase("ie")){
			if(commonMethods.getBrowserVersion(driver).contains("11")){
				// windowElement=AutoIT_ActionsUtil.getDialog(handle,"WebDriver - Internet Explorer");
				windowElement=AutoIT_ActionsUtil.getDialog(handle,title+" - Internet Explorer");
			}else{
				//windowElement=AutoIT_ActionsUtil.getDialog(handle,"WebDriver - Windows Internet Explorer");
				windowElement=AutoIT_ActionsUtil.getDialog(handle,title+" - Windows Internet Explorer");
			}			
			dialogwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, dailogName);
			
		}
		
		
		if(buttonToClick.equalsIgnoreCase("Cancel")){
			WindowElement button_toClick=AutoIT_ActionsUtil.elementByName(handle, dialogwindowElement, "Cancel");
			AutoIT_ActionsUtil.clickElement(handle, button_toClick);
			Reporting.logStep(driver, refid, testcasename, workflow+"-Check Default Dialog-"+dailogName, " Dialog exists and Click Cancel button ", Constants_FRMWRK.Pass);
		}
		else if(buttonToClick.equalsIgnoreCase("Apply")){
			WindowElement button_toClick=AutoIT_ActionsUtil.elementByName(handle, dialogwindowElement, "Apply");
			AutoIT_ActionsUtil.clickElement(handle, button_toClick);
			Reporting.logStep(driver, refid, testcasename, workflow+"-Check Default Dialog-"+dailogName, " Dialog exists and Click Apply button ", Constants_FRMWRK.Pass);
		}
		else if(buttonToClick.equalsIgnoreCase("Print")){
			WindowElement button_toClick=AutoIT_ActionsUtil.elementByName(handle, dialogwindowElement, "Print");
			AutoIT_ActionsUtil.clickElement(handle, button_toClick);
			Reporting.logStep(driver, refid, testcasename, workflow+"-Check Default Dialog-"+dailogName, " Dialog exists and Click Print button ", Constants_FRMWRK.Pass);
		}
		flag =Constants_FRMWRK.True;
	}catch (Throwable t){
		t.printStackTrace();
		Reporting.logStep(driver, refid, testcasename,workflow+"-Check Default Dialog",   "Unable to Locate the dialog -"+dailogName+" due to -->"+commonMethods.getStackTrace(t), Constants_FRMWRK.Fail);
	}
	return flag;
}
}
