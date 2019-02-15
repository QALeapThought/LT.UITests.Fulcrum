package com.proj.utilFulcrum;





import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.PopUpUtil;
import com.frw.util.WaitUtil;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.Driver;
import com.proj.library.ElementPresence;
import com.proj.library.LocalDriverManager;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.reusables.Users;
import com.proj.util.Clicks;
import com.proj.util.CustomExceptions;
import com.report.reporter.Reporting;



/**
 * 
 * @author shaikka
 *
 */
public class ApplicationMethods extends TestBase{
	static boolean  newlogin;

	/*public static WebDriver launch0365Login(String browser,String refID,String testcasename,String url,String userName,String password) throws Throwable{
	WebDriver driver = null;
	browserName=browser;
	String httpProtocol="https://";
	driver=Driver.launchBrowser(browser);
	LocalDriverManager.setWebDriver(driver);
	driver=LocalDriverManager.getDriver();
	if (browser.equalsIgnoreCase("ie")){
		String title;
		url=httpProtocol+url;
		commonMethods.navigateURL(driver,url);
		//parentwindow = driver.getWindowHandle();
		//driver.switchTo().window(parentwindow);
		O365_loginwithCredentials(driver,refID,testcasename, userName, password,browser,url);
		System.out.println("Logged in...");
	}

	return driver;

}*/

	
	/**
	 * closes all pop-up windows displayed
	 * @author shaikk
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 */
	public static void closeAllDialogs(WebDriver driver,String refID,String testcaseName){
		int counter=1;
		commonMethods.switchToDefaultPage(driver);
		int frameCount=getApplicationFrameCount(driver);
		Boolean isView=false;
		System.out.println("Number of Frame before Close icons are "+frameCount);
		logsObj.log("Number of Frame before Close icons are "+frameCount);
		int closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.icon_close, Constants_TimeOuts.Save_TimeOut);
		if(frameCount!=0 && closeIcons==0){
			WorkArounds.getViewPortOfPage(driver,browserName);
			isView=true;
		}
		if(frameCount!=0){			
			closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.icon_close, Constants_TimeOuts.Save_TimeOut);
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);		
			System.out.println("Number of Close icons displayed are "+closeIcons);
			logsObj.log("Number of Close icons displayed are "+closeIcons);
			if(closeIcons!=0 && counter <10){				
				List <WebElement> elements=ExplicitWaitUtil.waitForVisibilityOfElements(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.icon_close,Constants_TimeOuts.Element_TimeOut);
				System.out.println("Number of Close icons elements are "+elements.size());
				logsObj.log("Number of Close icons elements are "+elements.size());
				for (WebElement element :elements){
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
					try{
						if(element!=null && element.isDisplayed()==true){
							commonMethods.getViewOfElement(driver, element,browserName);					
							//element.click();
							Clicks.actionsClick(driver, element);
							if(isView==true){
								Reporting.logStep(driver, "Close popup windows", "Closed all Popup Windows with view port recovery", Constants_FRMWRK.Warning);
							}							
						}
					}catch(StaleElementReferenceException st){
						System.out.println("closeAllDialogs :- stale..");
					}
					//WaitUtil.pause(3);		
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				}
				closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.icon_close,Constants_TimeOuts.Save_TimeOut);	
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				if(closeIcons!=0 && counter <10){
					counter=counter+1;
					System.out.println("Number of Close icons are still "+elements.size());
					closeAllDialogs(driver, refID, testcaseName);
				}else if (elements.size()==0 && counter <10){
					//Reporting.logStep(driver, "Closing the popup dialogs", "All Opened dialogs are closed", Constants_FRMWRK.Pass);
				}else if (elements.size()!=0 && counter >=10){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, "Closing the popup windows", "Unable to close all opened dialogs after 10 attempts", Constants_FRMWRK.Fail);
				}
			}
		}

	}
	/**
	 * fetches the frame count from the page displayed
	 * @author shaikk
	 * @param driver
	 * @return
	 */
	public static int getApplicationFrameCount(WebDriver driver){
		int flag=0;
		commonMethods.switchToDefaultPage(driver);
		flag=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.frame_single,Constants_TimeOuts.sync_frame_load);
		return flag;
	}
	public static int getApplicationFrameCount(WebDriver driver,String frameName){
		int flag=0;
		commonMethods.switchToDefaultPage(driver);
		String locator=commonMethods.replaceString("name", ObjRepository.frame_pattern, frameName);
		flag=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH,locator,Constants_TimeOuts.sync_frame_load);
		return flag;
	}
	/**
	 * Switches to latest DLG frame displayed
	 * @author shaikk
	 * @param driver
	 * @param testcasename
	 * @throws Throwable
	 */
	public static int switchToLatestDLGframe(WebDriver driver,String testcasename) throws Throwable{
		String frameName;
		//WaitUtil.pause(Constants_TimeOuts.sync_frame_load);
		int frames=0;
		try{
			frames=getApplicationFrameCount(driver,ObjRepository.frame_DLG);
			if(frames==0){				
			}
			else if(frames!=0){
				frameName=ObjRepository.frame_dlg_list_pattern;
				frameName=frameName.replaceAll("framelist", String.valueOf(frames));
				commonMethods.switchToFrameFromDefault(driver, testcasename, Constants_FRMWRK.FindElementByXPATH, frameName);
			}
		}catch (Throwable t ){
			CustomExceptions.Exit(testcasename, "Switch to latest DLG frame -Failure", "Unable to switch the latest frame expected due to error-"+commonMethods.getStackTrace(t));
		}

		return frames;
	}
	
	/**
	 * Fetches the site name
	 * @author shaikk
	 * @param url
	 * @return
	 */
	public static String getSiteName(String url){
		String flag;
		if(url.contains(Constants.App_Fluid)){
			flag=Constants.App_Fluid;
		}else{
			flag=Constants.App_Fulcrum;
		}
		return flag;	
	}
	/**
	 * Waits for Overlay to disappear
	 * @author shaikk
	 * @param driver
	 */
	public static void waitForOverlayToDisappear(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.overlay_working, Constants_TimeOuts.Overlay_disappear);
		System.out.println("Overlay Working on it.. invisibility.."+tt);
	}
	/**
	 * fetches subsite from the url
	 * @author shaikk
	 * @param mainSite
	 * @param subsiteName
	 * @return
	 */
	public static String getSubsite(String mainSite,String subsiteName){
		String patternSubsite="/SitePages";
		return mainSite.replaceAll(patternSubsite, "/"+subsiteName+patternSubsite);

	}

	@SuppressWarnings("unused")
	private static void waitforHomePage() throws Throwable{
		ExplicitWaitUtil.waitForElementTobeActionable(LocalDriverManager.getDriver(), Constants_FRMWRK.FindElementByXPATH, ObjRepository.homePage_element, Constants_TimeOuts.Page_TimeOut);
	}

	/**
	 * Waits for Overlay to disappear
	 * @author shaikk
	 * @param driver
	 */
	public static void waitForElementToDisappear(WebDriver driver,String objectLocator){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, objectLocator, Constants_TimeOuts.Overlay_disappear);
		System.out.println("Overlay Working on it.. invisibility.."+tt);
	}

	/**
	 * Waits for Overlay to disappear
	 * @author shaikk
	 * @param driver
	 */
	public static void waitForSpinnerToDisappear(WebDriver driver,String objectLocator){
		commonMethods.switchToDefaultPage(driver);
		boolean isSpinnerDisplayed=false;
		int counter=1;
		if(isSpinnerDisplayed==false && counter<=3){
			isSpinnerDisplayed=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, objectLocator, Constants_TimeOuts.Overlay_disappear);
			counter=counter+1;
		}

		System.out.println("Waiting for Spinner Please Wait... invisibility.."+isSpinnerDisplayed);
	}

	/**
	 * closes all pop-up windows displayed with cancel button
	 *  @author shaikk
	 *  @date May 18 2017
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @throws Throwable 
	 */
/*	public static void closeAllDialogswithCancel(WebDriver driver,String refID,String testcasename) throws Throwable{
		int counter=1;
		commonMethods.switchToDefaultPage(driver);
		int frameCount=getApplicationFrameCount(driver);
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		//Boolean isView=false;
		System.out.println("Number of Frame before Cancel are "+frameCount);
		logsObj.log("Number of Frame before Cancel are "+frameCount);
		int closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_cancel, Constants_TimeOuts.Save_TimeOut);
		if(frameCount!=0 && closeIcons==0){
			WorkArounds.getViewPortOfPage(driver,browserName);
			isView=true;
		}
		if(frameCount!=0){	
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_cancel, Constants_TimeOuts.Save_TimeOut);
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);		
			System.out.println("Number of Cancel buttons displayed are "+closeIcons);
			logsObj.log("Number of Cancel buttons displayed are "+closeIcons);
			if(closeIcons!=0 && counter <10){	
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				List <WebElement> elements=ExplicitWaitUtil.waitForVisibilityOfElements(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_cancel,Constants_TimeOuts.Element_TimeOut);
				System.out.println("Number of Cancel button elements are "+elements.size());
				logsObj.log("Number of Cancel elements are "+elements.size());
				for (WebElement element :elements){
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
					try{
						if(element!=null && element.isDisplayed()==true){
							//commonMethods.getViewOfElement(driver, element,browserName);					
							//element.click();
							commonMethods.actionsClick(driver, element);
							if(isView==true){
								Reporting.logStep(driver, "Close popup windows", "Closed all Popup Windows with view port recovery", Constants_FRMWRK.Warning);
							}							
						}
					}catch(StaleElementReferenceException st){
						System.out.println("closeAllDialogs :- stale..");
					}
					//WaitUtil.pause(3);		
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				}
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_cancel,Constants_TimeOuts.Save_TimeOut);	
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				if(closeIcons!=0 && counter <10){
					counter=counter+1;
					System.out.println("Number of Cancel buttons are still "+elements.size());
					closeAllDialogswithCancel(driver, refID, testcaseName);
				}else if (elements.size()==0 && counter <10){
					//Reporting.logStep(driver, "Closing the popup dialogs", "All Opened dialogs are closed", Constants_FRMWRK.Pass);
				}else if (elements.size()!=0 && counter >=10){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, "Closing the popup windows", "Unable to close all opened dialogs with cancel after 10 attempts", Constants_FRMWRK.Fail);
				}
			}
		}

	}*/
	/**
	 * closes all pop-up windows displayed with cancel button
	 *  @author shaikk
	 *  @date May 18 2017
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @throws Throwable 
	 */
/*	public static void closeAllDialogswithClose(WebDriver driver,String refID,String testcasename) throws Throwable{
		int counter=1;
		commonMethods.switchToDefaultPage(driver);
		int frameCount=getApplicationFrameCount(driver);
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		//Boolean isView=false;
		System.out.println("Number of Frame before Close are "+frameCount);
		logsObj.log("Number of Frame before Close are "+frameCount);
		int closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_close, Constants_TimeOuts.Save_TimeOut);
		if(frameCount!=0 && closeIcons==0){
			WorkArounds.getViewPortOfPage(driver,browserName);
			isView=true;
		}
		if(frameCount!=0){			
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_close, Constants_TimeOuts.Save_TimeOut);
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);		
			System.out.println("Number of Close buttons displayed are "+closeIcons);
			logsObj.log("Number of Close buttons displayed are "+closeIcons);
			if(closeIcons!=0 && counter <10){	
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				List <WebElement> elements=ExplicitWaitUtil.waitForVisibilityOfElements(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_close,Constants_TimeOuts.Element_TimeOut);
				System.out.println("Number of Close button elements are "+elements.size());
				logsObj.log("Number of Close elements are "+elements.size());
				for (WebElement element :elements){
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
					try{
						if(element!=null && element.isDisplayed()==true){
							//commonMethods.getViewOfElement(driver, element,browserName);					
							//element.click();
							commonMethods.actionsClick(driver, element);
							if(isView==true){
								Reporting.logStep(driver, "Close popup windows", "Closed all Popup Windows with view port recovery", Constants_FRMWRK.Warning);
							}							
						}
					}catch(StaleElementReferenceException st){
						System.out.println("closeAllDialogs :- stale..");
					}
					//WaitUtil.pause(3);		
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				}
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				closeIcons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_close,Constants_TimeOuts.Save_TimeOut);	
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);	
				if(closeIcons!=0 && counter <10){
					counter=counter+1;
					System.out.println("Number of Close buttons are still "+elements.size());
					closeAllDialogswithClose(driver, refID, testcaseName);
				}else if (elements.size()==0 && counter <10){
					//Reporting.logStep(driver, "Closing the popup dialogs", "All Opened dialogs are closed", Constants_FRMWRK.Pass);
				}else if (elements.size()!=0 && counter >=10){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, "Closing the popup windows", "Unable to close all opened dialogs with cancel after 10 attempts", Constants_FRMWRK.Fail);
				}
			}
		}

	}*/
	/**
	 * Close all dialogs with Cancel and Close buttons.
	 * @author ShaikK
	 * @date May 18 2017
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @throws Throwable 
	 */
/*	public static void closeAllDialogswithCancelAndClose(WebDriver driver,String refID,String testcasename) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		//	System.out.println("ook");
		int cancelButtons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_cancel, Constants_TimeOuts.Save_TimeOut);
		if (cancelButtons!=0){
			closeAllDialogswithCancel(driver, refID, testcaseName);
		}
		int closebuttons=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.button_close, Constants_TimeOuts.Save_TimeOut);
		if (closebuttons!=0){
			closeAllDialogswithClose(driver, refID, testcaseName);
		}
	}*/

/*	public static void isSyncElementDisplayed(WebDriver driver,int timeout){
		ExplicitWaitUtil.waitForVisibilityOfElements(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.search_inlineSearch, timeout);

	}*/

	

	public static void waitForLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(expectation);
		} catch (Throwable error) {
			System.err.println("Timeout waiting for Page Load Request to complete.");
		}


		driver.manage().timeouts().implicitlyWait(Constants_TimeOuts.Implicit_Element_TimeOut, TimeUnit.SECONDS);
	}

	public static int waitForApplicationFrameCount(WebDriver driver,String frameName,int expectedFrameCount){
		int flag=0;
		int counter=0;
		String locator=commonMethods.replaceString("name", ObjRepository.frame_pattern, frameName);

		while(expectedFrameCount!=flag && counter<=5){
			commonMethods.switchToDefaultPage(driver);		
			flag=ExplicitWaitUtil.getVisibleElementsSize(driver, Constants_FRMWRK.FindElementByXPATH,locator,Constants_TimeOuts.sync_frame_load);
			if(expectedFrameCount==flag){
				System.out.println("Expected Frames matches with actual "+expectedFrameCount+"--"+flag+" after counter-"+counter);
			}
		}

		return flag;
	}

/*	public static void waitForCheckListOverlayToDisappear(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.overlay_checkList, Constants_TimeOuts.Overlay_disappear);
		System.out.println("Overlay Working on it.. invisibility.."+tt);
	}*/

	
/*	private static WebDriver checkProlensHomePageLoadedCompletely(boolean isHomePagedisplayed,WebDriver driver,String refId,String testcasename,String workflow,String browser,String url,String userName,String password) throws Throwable{
		WebDriver driv;
		if(isHomePagedisplayed==true && Constants_ConfigProperties.LandingSite.toLowerCase().contains("prolens")){
			driv= WorkArounds.prolenhomePagerelaunch(driver, refId, testcasename, workflow, browser, url, userName, password);
		}else{
			driv=driver;
		}
		return driv;

	}*/


	/*public static boolean checkProlensLandingPageTitle(WebDriver driver){
		boolean isProlenslandingPagedisplayed=false;
		String title = "";
		if(Constants_ConfigProperties.environment.equalsIgnoreCase("QA")){
			title=ObjRepository.browserpageTile_ProlensPortal;
		}
		else if (Constants_ConfigProperties.environment.equalsIgnoreCase("QA2")){
			title=ObjRepository.browserpageTile_ProlensPortal2;
		}		

		isProlenslandingPagedisplayed=ExplicitWaitUtil.waitTitle(driver, Constants_TimeOuts.Page_Load_TimeOut, title);

		return isProlenslandingPagedisplayed;
	}*/
	
	
}
