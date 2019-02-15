package com.proj.library;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.frw.wait.ExplicitWaitUtil;
import com.google.common.base.Throwables;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.util.CustomExceptions;
import com.proj.util.ErrorUtil;
import com.report.reporter.Reporting;






public class commonMethods extends TestBase{



	static commonMethods cmd;
	public static boolean isDBcon=false;
	public static WebDriver popupdriver=null;
	public static WebDriver popupdriver_bef=null;



	public static commonMethods getInstance(){
		if (cmd == null){
			cmd=new commonMethods();
			logsObj.log("Creating commonFunctions instance...");
		}

		return cmd;
	}

	/**
	 * Navigates to the url specified
	 * @author khshaik
	 * @param URL
	 * @throws Throwable
	 */
	public static void navigateURL(WebDriver driver,String URL) throws Throwable{
		driver.get(URL);
		/*		parentwindow = driver.getWindowHandle();
		ie_Certification(driver);*/
		logsObj.log("launching the AUT "+URL);

	}





	/**
	 * Splits the string
	 * @author khshaik
	 * @param stringToSplit
	 * @param delimiter
	 * @return
	 */
	public static String[] splitString(String stringToSplit,String delimiter){
		String[] flag={Constants_FRMWRK.Fail};

		try{
			String[] result = stringToSplit.split(delimiter);
			if (result.length < 2) {						 
				throw new IllegalArgumentException("String not in correct format");					 
			} else {
				flag=result;
			}
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);	
			logsObj.logError("Unable to split the string-"+stringToSplit+" due to erro-", t);
		}


		return flag;

	}


	/**
	 * Checks the test case status
	 * @author khshaik		
	 * @param isTestPass
	 */
	public static void isTestcasePass(boolean isTestPass){
		if(isTestPass==false){
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}		
	}

	/**
	 * Replace a variable 
	 * @author SAHAMED
	 * @param stringTobeReplaced
	 * @param valueToReplace
	 * @return error details for failure and replaced string for success
	 */
	public static String replaceVariableinString(String stringTobeReplaced,String valueToReplace){
		String flag=stringTobeReplaced;

		try{
			flag=stringTobeReplaced.replaceAll("variable", valueToReplace);
			logsObj.log("The Replaced string is--"+flag);
		}catch(Throwable t){
			flag=Constants_FRMWRK.Error+t;
			logsObj.logError("Cannot Replace string due to error--",t);
		}


		return flag;
	}

	/**
	 * replace a require character in the string
	 * @author SAHAMED
	 * @date Mar 3 2014
	 * @param ToReplaceCharacter
	 * @param stringTobeReplaced
	 * @param valueToReplace
	 * @return error details for failure and replaced string for success
	 */
	public static String replaceString(String ToReplaceCharacter,String stringTobeReplaced,String valueToReplace){
		String flag=stringTobeReplaced;

		try{
			flag=stringTobeReplaced.replaceAll(ToReplaceCharacter, valueToReplace);
			logsObj.log("The Replaced string is--"+flag);
		}catch(Throwable t){
			flag=Constants_FRMWRK.Error+t;
			logsObj.log("Cannot Replace string due to error--"+t);
		}


		return flag;
	}
	/**
	 * Fetches number of keys available in the given Hashtable
	 * @author sahamed
	 * @Date Feb 18 2014
	 * @param hash
	 * @return
	 */
	public static int Hashtable_NumberofKeys(Hashtable<String,String>hash){
		int number=0;
		try{
			if (!hash.isEmpty()){
				@SuppressWarnings("rawtypes")
				Set keys=hash.keySet();
				number=keys.size();
			}
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
		}

		return number;
	}



	/**
	 * Click the certification link on IE Browser
	 * @author sahamed
	 * @Date Jun 2 2014
	 * 
	 */

	public static void ie_Certification(WebDriver driver){
		try{
			if("Certificate Error: Navigation Blocked".equals(driver.getTitle())) {
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				logsObj.log("Certificate Error message is displayed and clicked for ie browser");
			}
		}catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			logsObj.logError("Unable to click the certificate error for ie browser due to error", t);
		}

	}

	public static String getBrowserVersion(WebDriver driver){
		//String name = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		browserVersion = ((RemoteWebDriver) driver).getCapabilities().getVersion();
		return browserVersion;
	}


	public static void switchToDefaultPage(WebDriver driver){
		driver.switchTo().defaultContent();
	}

	/**
	 * @author: sahamed    
	 * @param : identifyBy:By Element is String element
	 * @param : frameName: String which takes the frame name
	 * @return: return a String Pass for Success and Fail for failure
	 * @throws Exception 
	 */
	public static String switchToFrameFromDefault(WebDriver driver,String testcaseName,String locatorType, String frameName) throws Throwable {	
		String flag=Constants_FRMWRK.Pass;
		logsObj.log("Before switchToFrameFromDefault");

		try{
			driver.switchTo().defaultContent();			
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			logsObj.log ("unable to switch to Default frame");
			flag=Constants_FRMWRK.Fail;
			CustomExceptions.Exit(testcaseName, "switchToFrameFromDefault-Switch to default frame", "Unable to switch to default due to error->"+e+" ,hence cannot execute further test steps..");
		}

		logsObj.log("Sucessfully switched to default frame..Next is "+frameName+" frame..");
		try{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frameName)));
			//driver.switchTo().frame(ElementMethods.fetchElement(driver, locatorType, frameName));
			logsObj.log("Sucessfully switched to "+frameName+" frame from default..");
		}catch (Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			logsObj.log ("unable to switch to login frame");
			flag=Constants_FRMWRK.Fail;
			CustomExceptions.Exit(testcaseName, "switchToFrameFromDefault-Switch to "+frameName+" frame from default", "Unable to switch to "+frameName+" frame from defaultdue to error->"+e+" ,hence cannot execute further test steps..");
		}
		return flag;
	}

	public static String switchToSingleFrame(WebDriver driver,String locatorType, String frameName) throws Throwable{		
		String flag=Constants_FRMWRK.Pass;
		try{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frameName)));
			//driver.switchTo().frame(ElementMethods.fetchElement(driver, locatorType, frameName));
			logsObj.log("Sucessfully switched to "+frameName+" frame..");
		}catch(Throwable e){
			isTestPass=Constants_FRMWRK.FalseB;
			ErrorUtil.addVerificationFailure(e);
			logsObj.log("Unable to switch to the frame "+frameName+" due to -->"+e.getCause());
			flag=Constants_FRMWRK.Fail;
			CustomExceptions.Exit(testcaseName, "switchToSingleFrame-Switch to "+frameName+" frame ", "Unable to switch to "+frameName+" frame from login due to error->"+e+" ,hence cannot execute further test steps..");
		}

		return flag;
	}

	public static String getCurrentFrameName(WebDriver driver) {
		String str= (String)((JavascriptExecutor) driver).executeScript("return window.frameElement ? window.frameElement.name : 'dsp1';");
		WaitUtil.pause(1);
		return str;
	}

	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	public static void getViewOfElement(WebDriver driver,WebElement element,String browserType){
		if(browserType.equalsIgnoreCase(Constants.browserie)){
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}

	}

	public static String getSelectedOptionFromDropdown(WebElement element){
		Select dropdown = new Select(element);
		return dropdown.getFirstSelectedOption().getText();	    
	}
	public static String getBrowserName(WebDriver driver){
		String browsername = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		return browsername;
	}

	public static String getOS () {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		}else if (os.contains("mac")) {
			return "Mac";
		}else if (os.contains("sunos")) {
			return "Solaris";
		}else {
			return "Other";
		}
	}
	public static String getOSVersion () {
		String os = System.getProperty("os.name").toLowerCase();
		String[] osversion=os.split(" ");
		return osversion[1];
	}
	private static void killIEProcess(WebDriver driver){
		try {			
			String browsername=getBrowserName(driver);
			if(browsername.equalsIgnoreCase("internet explorer")){
				String version =getOSVersion();

				if(version.contains(".")){
					if(Double.valueOf(version)>=8){
						Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
						Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
					}else{
						Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
					}
				}
				else{
					if(Integer.valueOf(version)>=8){
						Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
					}else{
						Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
					}
				}

				//Runtime.getRuntime().exec("taskkill /F /IM conhost.exe");
				Thread.sleep(2000L);
				logsObj.log("IE listed processes have been killed..");
				System.out.println("Killed IE processes..");
			}

		} catch (Exception e) {
			e.printStackTrace();		    
			logsObj.log("Unable to kill IE listed processes have been killed.. due to "+Throwables.getStackTraceAsString(e));
		}
	}


	public static void pageLoadWait(WebDriver driver) throws Throwable{
		try{
			/*PageLoadWaitUtil.waitForPageToLoad(driver);
			PageLoadWaitUtil.waitForAjax(driver);*/
			PageLoadWaitUtil.completePageLoad(driver, Constants_TimeOuts.Page_TimeOut);
		}catch (Exception ex){

		}
	}

	public static void cleanProcess(WebDriver driver,String browserType){
		if (browserType.equalsIgnoreCase(Constants.browserie)){
			killIEProcess(driver);
		}


	}
	/**
	 * Verifies the given locator displayed in the page.
	 * Return true for success otherwise false 
	 * @author shaik
	 * @date Aug 17 2016
	 * @param driver
	 * @param locatorType
	 * @param locator
	 * @return
	 */


	public static boolean isElementDisplayed(WebDriver driver,String locatorType,String locator){
		boolean flag=Constants_FRMWRK.FalseB;
		WebElement element=null;

		try{
			element = ExplicitWaitUtil.waitForElement(driver, locatorType, locator, Constants_TimeOuts.Element_TimeOut);
		}catch(Throwable t){
			//t.printStackTrace();
		}		
		if(element!=null){
			if(element.isDisplayed()==true){
				WaitUtil.pause(1);
				flag=Constants_FRMWRK.TrueB;
			}			
		}
		return flag;
	}

	public static boolean isElementDisplayed(WebDriver driver,String locatorType,String locator,int timeout){
		boolean flag=Constants_FRMWRK.FalseB;
		WebElement element=null;

		try{			
			element = ExplicitWaitUtil.waitForElement(driver, locatorType, locator, timeout);
		}catch(Throwable t){
			//t.printStackTrace();
		}		
		if(element!=null){
			if(element.isDisplayed()==true){
				WaitUtil.pause(1);
				flag=Constants_FRMWRK.TrueB;
			}			
		}
		return flag;
	}

	public static boolean isElementEnabled(WebDriver driver,String locatorType,String locator,int timeout){
		boolean flag=Constants_FRMWRK.FalseB;
		WebElement element=null;

		try{			
			element = ExplicitWaitUtil.waitForElement(driver, locatorType, locator, timeout);
		}catch(Throwable t){
			//t.printStackTrace();
		}		
		if(element!=null){
			if(element.isEnabled()==true){
				WaitUtil.pause(1);
				flag=Constants_FRMWRK.TrueB;
			}			
		}
		return flag;
	}

	public static boolean isElementDisplayedByAttribute(WebDriver driver,String locatorType,String locator,int timeout){
		boolean flag=Constants_FRMWRK.FalseB;
		WebElement element=null;

		try{			
			element = ExplicitWaitUtil.waitForElement(driver, locatorType, locator, timeout);
			System.out.println(element.getAttribute("disabled"));
			System.out.println(element.getAttribute("disabled").equalsIgnoreCase("disabled"));
			System.out.println("Enabled-"+element.isEnabled());
			if(element!=null){
				if(element.getAttribute("disabled").equalsIgnoreCase("") || element.getAttribute("disabled").equalsIgnoreCase("disabled") ){
					WaitUtil.pause(1);
					flag=Constants_FRMWRK.TrueB;
				}			
			}

		}catch(Throwable t){
			//t.printStackTrace();
		}
		return flag;
	}

	public static String getParentWindow(WebDriver driver){
		return driver.getWindowHandle(); // save the current window handle.	
	}
	public static void switchToWindow(WebDriver driver,String windowHandle){
		driver.switchTo().window(windowHandle);	
	}
	public static WebDriver getWindow(WebDriver driver,String refid,String testcasename,String workflow,String title){
		String flag=Constants_FRMWRK.False;
		WebDriver popup = null;
		String parent=getParentWindow(driver);
		Set<String> windowIterator = driver.getWindowHandles();
		if(windowIterator.size()>1){ 
			System.err.println("No of windows :  " + windowIterator.size());
			return popup;
		}       
		for (String s : windowIterator) {
			String windowHandle = s; 
			if(!windowHandle.equalsIgnoreCase(parent)){
				popup = driver.switchTo().window(windowHandle);
				System.out.println("Window Title : " + popup.getTitle());
				System.out.println("Window Url : " + popup.getCurrentUrl());
				if (popup.getTitle().equals(title) ){
					System.out.println("Selected Window Title : " + popup.getTitle());
					flag=Constants_FRMWRK.True;
					break;
				}
			}			

		}
		System.out.println("Window Title :" + popup.getTitle());
		System.out.println();
		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			Reporting.logStep(driver,refid,testcasename,workflow+"-Switch to Window","Not able to switch to window with title-"+title,Constants_FRMWRK.Fail);
			popup=null;

		}else{
			Reporting.logStep(driver,refid,testcasename,workflow+"-Switch to Window","Sucessfully switched to window with title-"+title,Constants_FRMWRK.Pass);

		}
		return popup;
	}
	
	public static void maximiseBrowser(WebDriver driver){
		driver.manage().window().maximize();
	}
	public static String getPageTitle(WebDriver driver){
		return driver.getTitle();
	}
	/**
	 * Refresh the current displayed browser page
	 * @author ShaikK
	 * @param driver
	 */
	public static void refreshPage(WebDriver driver,String refId,String testcasename,String workflow,String statusToReport){
		driver.navigate().refresh();
		Reporting.logStep(driver, refId, testcasename, workflow+"Page refresh", "The Page is refreshed as workaround to continue the test", statusToReport);
	}
	
	public static String report_stackTrace(Exception t){
		return Arrays.toString(t.getStackTrace());
	}
	
	/**
	 * Checks the key under search is available under the given hashtable
	 * @author ShaikK
	 * @param data
	 * @param keyUnderSearch
	 * @return returns key value if available else false.
	 */
	public static String checkKeyandgetValue(Hashtable<String,String>data,String keyUnderSearch){
		String flag=Constants_FRMWRK.False;
		Set<String> keys_list=data.keySet();

		for(String eachKey:keys_list){
			if(eachKey.equalsIgnoreCase(keyUnderSearch)){
				flag=data.get(eachKey);
				return flag;
			}
		}
		return flag;

	}
}









