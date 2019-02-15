package com.proj.utilFulcrum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
import com.proj.library.KeyMethods;
import com.proj.library.LocalDriverManager;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.reusables.Users;
import com.proj.util.Clicks;
import com.report.reporter.Reporting;

public class LoginLib extends TestBase{

	/**
	 * Launch browser & Log into the application (hard-coded Ref ID)
	 * @author ShaikK
	 * @param browser
	 * @param userName
	 * @param password
	 * @return
	 * @throws Throwable
	 */
	public static WebDriver launchBrowserAndlogIntoApplication(String browser,String testcasename,String userName,String password) throws Throwable{
		WebDriver driver = null;
		browserName=browser;
		String refId="LATFLD-32";
		driver=launchBrowserAndlogIntoApplication(browser, testcasename, userName, password, refId);

		/*landing_url=LoginLib.LoginGenerics.getLaunchSite(userName);

		driver=Driver.launchBrowser(browser);
		LocalDriverManager.setWebDriver(driver);
		driver=LocalDriverManager.getDriver();


		commonMethods.navigateURL(driver,landing_url);

		//chrome browser
		if(browser.equalsIgnoreCase("Chrome")||browser.equalsIgnoreCase("firefox") ){
			LoginMethods.SharePoint_loginwithCredentials(driver, refId, testcaseName, userName, password);
			commonMethods.maximiseBrowser(driver);
		}else if (browser.equalsIgnoreCase("ie")){			
			LoginMethods.SharePoint_loginwithCredentials(driver, refId, testcaseName, userName, password);
			//waitforHomePage();

		}

		try{
			PageLoadWaitUtil.waitForPageToLoad(driver);
		}catch (Exception ex){

		}
		 */
		return driver;
	}

	public static WebDriver launchBrowserAndlogIntoApplication(String browser,String testcasename,String userName,String password,String refID) throws Throwable{
		WebDriver driver = null;
		browserName=browser;
		String landing_url="";

		landing_url=LoginLib.LoginGenerics.getLaunchSite(userName);

		driver=Driver.launchBrowser(browser);
		LocalDriverManager.setWebDriver(driver);
		driver=LocalDriverManager.getDriver();


		commonMethods.navigateURL(driver,landing_url);

		//chrome browser
		if(browser.equalsIgnoreCase("Chrome")||browser.equalsIgnoreCase("firefox") ){
			LoginMethods.SharePoint_loginwithCredentials(driver, refID, testcasename, userName, password);
			commonMethods.maximiseBrowser(driver);
		}else if (browser.equalsIgnoreCase("ie")){			
			LoginMethods.SharePoint_loginwithCredentials(driver, refID, testcasename, userName, password);
			//waitforHomePage();

		}

		try{
			PageLoadWaitUtil.waitForPageToLoad(driver);
		}catch (Exception ex){

		}


		return driver;
	}


	/**
	 * Logs out from the application , closes the driver instance & kills the processes
	 * @author shaikk
	 * @param driver
	 * @param refid
	 * @param testcasename
	 * @return
	 * @throws Throwable
	 */
	public static String logOutFromApplicationAndcloseBrowser(WebDriver driver,String refid,String testcasename) throws Throwable{
		WaitUtil.pause(2);
		String flag=Constants_FRMWRK.True;	
		//if(isTestPass==Constants_FRMWRK.FalseB && LoginGenerics.checkLandingPageTitle(driver)==Constants_FRMWRK.FalseB){
		if(isTestPass==Constants_FRMWRK.FalseB){
			ApplicationMethods.closeAllDialogs(driver, refid, testcasename);

			//	ApplicationMethods.closeAllDialogswithCancelAndClose(driver, refID, testcasename);
		}
		flag=LoginMethods.SharePoint_logOutFromApplication(driver,refid,testcasename);
		PopUpUtil.checkDefaultPopup(driver, Constants.ok);
		Driver.close(driver,browserName);
		return flag;
	}



	//************************************************************************************************************
	//									LoginGenerics
	//************************************************************************************************************
	public static class LoginGenerics{
		/**
		 * fetches the site for the given user name
		 * @author ShaikK
		 * @param userName
		 * @return
		 */
		public static String getLaunchSite(String userName){
			return Constants_ConfigProperties.testSiteName+Users.users_siteaccess.get(userName)+"/";
		}

		/**
		 * Verifies the Home Page title after login for given user name
		 * @author ShaikK
		 * @param driver
		 * @return
		 */
		public static boolean checkLandingPageTitle(WebDriver driver){
			boolean isHomePagedisplayed=false;
			isHomePagedisplayed=ExplicitWaitUtil.waitTitle(driver, Constants_TimeOuts.Page_Load_TimeOut, TestBase.landingPage_title);
			return isHomePagedisplayed;
		}

		public static boolean validate_homePageLogo(WebDriver driver,int timeout){
			boolean flag=Constants_FRMWRK.FalseB;

			flag=ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.link_user, timeout);

			return flag;
		}
	}

	//************************************************************************************************************
	//									LoginMethods
	//************************************************************************************************************	
	private static class LoginMethods{


		private static String SharePoint_loginwithCredentials(WebDriver driver,String refID,String testcasename,String username,String password) throws Throwable{
			String flag=Constants_FRMWRK.False;

			if(refID.isEmpty()){
				refID="LAT-81";
			}
			String workFlow="Log Into the Application||";

			ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.login_newExperience_UserName, Constants_TimeOuts.Element_TimeOut);

			//User Name
			flag=KeyMethods.f_performAction(driver, refID, testcasename,workFlow , " User Name", Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Textbox, ObjRepository.login_newExperience_UserName, username);

			if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				return flag;
			}
			//Password
			flag=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, " Password", Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Textbox, ObjRepository.login_newExperience_Password, password);

			if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				return flag;
			}


			//Sign In
			flag=KeyMethods.f_performAction(driver, refID, testcasename, workFlow, " Sign In", Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Button, ObjRepository.login_newExperience_SignIn, "");

			if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				return flag;
			}


			boolean isHomePagedisplayed=false;

			isHomePagedisplayed=LoginGenerics.checkLandingPageTitle(driver);
			/*if(isHomePagedisplayed==false){
				WorkArounds.homePageError(driver, refID, testcasename, "",browser,url,username,password);
				isHomePagedisplayed=checkLandingPageTitle(driver);
			}*/


			if(isHomePagedisplayed==false){		
				Reporting.logStep(driver, refID,testcasename, "Log into the application -Share Point User Authenication", "Home page is not displayed after entering user credentials "+username+"--"+password, Constants_FRMWRK.Fail);
			}else{
				boolean islogodisplayed=LoginGenerics.validate_homePageLogo(driver, Constants_TimeOuts.Page_MAX_TimeOut);
				if(islogodisplayed==Constants_FRMWRK.FalseB){
					Reporting.logStep(driver, refID,testcasename, workFlow+"SharePoint User Authenication", "Not able to log into the application with user credentials "+username+"--"+password, Constants_FRMWRK.Fail);
					flag=Constants_FRMWRK.False;
				}else{
					Reporting.logStep(driver, refID,testcasename, workFlow+"SharePoint User Authenication", "Successfully able to log into the application with user credentials "+username+"--"+password, Constants_FRMWRK.Pass);
					flag=Constants_FRMWRK.True;
				}

			}

			return flag;

		}


		/**
		 * Logs out of the application
		 * @author shaikka	
		 * @param driver
		 * @return
		 * @throws Throwable
		 */

		public static String SharePoint_logOutFromApplication(WebDriver driver,String refID,String testcaseName) throws Throwable{

			String flag=Constants_FRMWRK.True;		
			WebElement element;
			/*if(isTestPass==false){
				ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);				
			}
			 */
			int counter=1;
			boolean isDisplayed=false;

			commonMethods.switchToDefaultPage(driver);
			element=ExplicitWaitUtil.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.link_user, Constants_TimeOuts.Element_TimeOut);

			if(element!=null){
				try{
					WaitUtil.pause(1);
					while (isDisplayed==false && counter<3){
						Clicks.actionsClick(driver, element);
						isDisplayed=ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.link_signOut, Constants_TimeOuts.sync_element_load);
						counter=counter+1;
					}
					Reporting.logStep(driver, refID,testcaseName, "Logout- Click on Open Menu in the Home Page", "Open Menu exists and Clicked", Constants_FRMWRK.Pass);
				}catch(Throwable t){
					isTestPass=false;
					flag=Constants_FRMWRK.False;
					Reporting.logStep(driver, refID,testcaseName, "Logout-Click on Open Menu in the Home Page", "Open Menu exists and but not Clicked"+" due to error-"+t, Constants_FRMWRK.Fail);
				}

			}else{
				isTestPass=false;
				flag=Constants_FRMWRK.False;
				Reporting.logStep(driver, refID,testcaseName, "Logout- Click on Open Menu in the Home Page", "Open Menu doesnot exists ", Constants_FRMWRK.Fail);
			}


			flag=KeyMethods.f_performAction(driver, refID, testcaseName, "Logout of Application|| ", " Sign Out", Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Link, ObjRepository.link_signOut, "");

			if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				return flag;
			}

			return flag;
		}


	}


}
