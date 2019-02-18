package com.proj.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.report.reporter.Reporting;

public class Clicks extends TestBase{

	public static void actionsClick3(WebDriver driver,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, Constants_TimeOuts.Element_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		/*Actions act =new Actions(driver);
		//act.click(element).build().perform();	
		act.click(element).perform();*/
		//element.sendKeys(Keys.ENTER);
		logsObj.log("Able to action click");

	}
	/**
	 * Clicks on elements using actions
	 *@author ShaikK
	 *@date May 15 2017
	 * @param driver
	 * @param element
	 */
	public static void actionsClick2(WebDriver driver,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, Constants_TimeOuts.Element_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions act =new Actions(driver);
		//act.click(element).build().perform();	
		act.click(element).perform();
		//element.sendKeys(Keys.ENTER);
		logsObj.log("Able to action click");

	}
	/**
	 * Clicks on elements using actions
	 *@author ShaikK
	 *@date May 15 2017
	 * @param driver
	 * @param element
	 */
	public static void actionsClick4(WebDriver driver,WebElement element){
		/*	commented on Aug 30 to check the execution*/
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		logsObj.logInfo("Before Actions click4..");
		Actions act =new Actions(driver);
		/*act.click(element).build().perform();*/
		/*Point location = element.getLocation();
		act.moveToElement(element,location.x,location.y).pause(10).click(element).build().perform();*/

		org.openqa.selenium.Dimension dim=element.getSize();
		int x =dim.width;
		int y=dim.height;
		act.moveToElement(element,x, y).perform();	
		element.sendKeys(Keys.ENTER);
		logsObj.logInfo("Able to action click");

	}

	public static void actionsdoubleClick(WebDriver driver,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, Constants_TimeOuts.Element_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions act =new Actions(driver);
		/*act.click(element).build().perform();*/
		act.moveToElement(element).doubleClick(element);
		act.build().perform();
		//element.sendKeys(Keys.ENTER);
		logsObj.log("Able to action click");

	}

	public static void actionsClick(WebDriver driver,WebElement element){
		/*	commented on Aug 30 to check the execution*/
		WebDriverWait wait = new WebDriverWait(driver, Constants_TimeOuts.Element_TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		logsObj.logInfo("Before Actions click..");
		Actions act =new Actions(driver);
		/*act.click(element).build().perform();*/

		act.moveToElement(element).click(element);
		act.build().perform();
		//element.sendKeys(Keys.ENTER);
		logsObj.logInfo("Able to action click");

	}

	public static String javaScriptClick(WebDriver driver,String refID,String testcasename,String workFlow,String step,String locatorType,String objectType,String objectLocator,WebElement element) throws Exception {
		String flag=Constants_FRMWRK.False;
		String generic_Step="Click on ";
		step=workFlow+generic_Step+step;
		try {	
			if(element!=null){
				try {
					element=ExplicitWaitUtil.waitForElementTobeActionable(driver, locatorType, objectLocator, Constants_TimeOuts.Element_clickable_TimeOut);
					logsObj.log(testcasename+"-->"+objectLocator+" exists and ready for click... ");
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				Reporting.logStep(driver, refID,testcasename, step, objectType+": "+objectLocator+" exists and Clicked using Java script ", Constants_FRMWRK.Warning);
				flag=Constants_FRMWRK.True;
			} else {
				System.out.println("Unable to click on element");
				Reporting.logStep(driver, refID,testcasename, step, objectType+": "+objectLocator+" exists but not displayed to Clicked using Java script ", Constants_FRMWRK.Fail);				
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}

		return flag;
	}

}
