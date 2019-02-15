package com.proj.utilFulcrum;



import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.ElementPresence;
import com.proj.library.commonMethods;
import com.proj.util.Clicks;
import com.report.reporter.Reporting;



public class WebTable_Generic extends TestBase{
	private static boolean isStale=false;
	/**
	 * (Not to Use)Searches and validate the data in the respective columns provided and clicks on required column
	 * @author shaikka
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param actionColumnType
	 * @param actionData
	 * @param colToSearch
	 * @param actionPerformCol
	 * @return
	 * @throws Throwable 
	 */
	public static String NOTTOUSE_searchforDataInsearchColumnAndClickInactionableLinkColumn(WebDriver driver,String testcaseName,String Step,String tableAttribute,String containerName,String searchType,String searchData,String actionColumnType,String actionData,int colToSearch,int actionPerformCol) {
		String flag=Constants_FRMWRK.False;
		String colXpath_action;
		String rowNumber="row";
		String pageNumber="page";
		int pageCount=1;
		String actual_actionData = "";
		boolean clicked=Constants_FRMWRK.FalseB;
		boolean isNextDisplayed;
		String staleDesc="";
		if(isStale==true){
			staleDesc="Recovered from stale-";
		}

		String tableattribute="//table[@"+tableAttribute+"='";

		//WebElement webtableElement=ExplicitWaitUtil.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']",Constants_TimeOuts.Element_TimeOut);
		boolean isdisplayed=ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, tableattribute+containerName+"']", Constants_TimeOuts.Element_TimeOut);
		//	if(webtableElement==null){
		if(isdisplayed==false){
			Reporting.logStep(driver, Step, staleDesc+"Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= tableattribute+"containerName']/descendant :: tr /td[ToSearchColumn]";
		if(actionColumnType.equalsIgnoreCase("LINK")){
			colXpath_action=tableattribute+"containerName']/descendant :: tr /td[actionPerformCol]/a";
		}else{
			colXpath_action=tableattribute+"containerName']/descendant :: tr /td[actionPerformCol]";
		}


		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();
		//
		do{
			int rowCount=0;

			try{
				logsObj.log("Get Grid row count for column under search");

				final List<WebElement> rows_search = WebTableUtil.waitUntilAllVisible(driver,colXpath_search);
				final List<WebElement> rows_action = WebTableUtil.waitUntilAllVisible(driver,colXpath_action);

				WaitUtil.pause(100L);
				if (rows_search==null || rows_search.size()== 0)
				{
					Reporting.logStep(driver, Step, staleDesc+"Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " As searched data records are not fetched " ,Constants_FRMWRK.Fail);

				}
				for (WebElement rowSearch : rows_search) { 

					tableData.put(pageNumber+Integer.toString(pageCount)+" "+rowNumber+Integer.toString(rowCount), rowSearch.getText());
					logsObj.log(staleDesc+"Table- "+"Page-"+pageCount+" ,RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

					actual_actionData=rowSearch.getText().trim();

					boolean condition;
					if(searchType.equalsIgnoreCase("contains")){
						condition=actual_actionData.contains(searchData.trim());
					}else{
						condition=actual_actionData.equals(searchData.trim());
					}

					if(condition){
						WaitUtil.pause(100L);				
						int counter=0;

						try{							
							while (counter< 5){
								try{
									//rows_action.get(rowCount).click();

									//WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
									if(actionColumnType.equalsIgnoreCase("LINK")){
										Clicks.actionsdoubleClick(driver, rows_action.get(rowCount));
									}else{
										Clicks.actionsClick(driver, rows_action.get(rowCount));
									}


									//commonMethods.actionsClick(driver, rows_action.get(rowCount));	
									clicked=Constants_FRMWRK.TrueB;
									logsObj.log(staleDesc+"searchforDataInColumnAndClickInAcionableColumn:Successfully clicked the record from required column in the table") ;
									break;
								}catch(StaleElementReferenceException ex){
									counter=counter+1;
									WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
								}
								WaitUtil.pause(100L);

							}


						}catch (ElementNotVisibleException e){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, Step, staleDesc+"Unable to click the record from the required column after successfully matching the expected data from search coloumn in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

						} 	
						if(clicked==Constants_FRMWRK.TrueB){
							flag=Constants_FRMWRK.True;
							Reporting.logStep(driver, Step, staleDesc+"Successfully clicked the record  from the required column after matching the expected data:-"+actionData+" with actual data:-"+actual_actionData+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
						}

						break;
					}
					rowCount++;

				}
			}
			catch(StaleElementReferenceException stl){
				isStale=true;
				logsObj.log("searchforDataInColumnAndClickInAcionableColumn:Stale exception... need to recover..") ;
				flag=NOTTOUSE_searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcaseName, Step,tableAttribute, containerName, searchType,searchData, actionColumnType,actionData, colToSearch, actionPerformCol);
				isStale=false;
				return flag;
			}
			catch(Exception e){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, Step, staleDesc+"Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
				return flag;		
			}
			if(clicked==Constants_FRMWRK.TrueB){
				break;
			}else{
				pageCount++;
				isNextDisplayed=WebTableUtil.clicknextPage(driver);
			}

		}while (isNextDisplayed==Constants_FRMWRK.TrueB && clicked==Constants_FRMWRK.FalseB);
		//
		if(clicked==Constants_FRMWRK.FalseB){
			Reporting.logStep(driver, Step, staleDesc+"Unable to click the record from the required column after matching the expected data:-"+actionData+" with actual data:-"+actual_actionData+" in the table "+colXpath_search+" for the search record "+searchData, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}
		else if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, staleDesc+"Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}

	
	/**
	 * 
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param tableAttribute
	 * @param containerName
	 * @param searchType (contains or Equals)
	 * @param searchData
	 * @param actionColumnType(Link or DESCENDANT_LINK_1 or Text)
	 * @param actionData
	 * @param colNameToSearch
	 * @param actionPerformColName
	 * @return
	 */
	public static String searchforDataInsearchColumnAndClickInactionableLinkColumn(WebDriver driver,String testcaseName,String Step,String tableAttribute,String containerName,String searchType,String searchData,String actionColumnType,String actionData,String colNameToSearch,String actionPerformColName) {
		String flag=Constants_FRMWRK.False;
		String colXpath_action;
		String rowNumber="row";
		String pageNumber="page";
		int pageCount=1;
		String actual_actionData = "";
		String action_column_actualData = "";
		boolean condition = Constants_FRMWRK.FalseB;
		boolean clicked=Constants_FRMWRK.FalseB;
		boolean matches=Constants_FRMWRK.FalseB;
		boolean isNextDisplayed;
		String staleDesc="";
		if(isStale==true){
			staleDesc="Recovered from stale-";
		}

		String tableattribute="//table[@"+tableAttribute+"='";

		//WebElement webtableElement=ExplicitWaitUtil.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']",Constants_TimeOuts.Element_TimeOut);
		boolean isdisplayed=ElementPresence.isElementDisplayed(driver, Constants_FRMWRK.FindElementByXPATH, tableattribute+containerName+"']", Constants_TimeOuts.Element_TimeOut);
		//	if(webtableElement==null){
		if(isdisplayed==false){
			Reporting.logStep(driver, Step, staleDesc+"Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= tableattribute+"containerName']/descendant :: tr /td[ToSearchColumn]";
		if(actionColumnType.equalsIgnoreCase("LINK")){
			colXpath_action=tableattribute+"containerName']/descendant :: tr /td[actionPerformCol]/a";
		}
		
		else if(actionColumnType.equalsIgnoreCase("DESCENDANT_LINK_1")){
			colXpath_action=tableattribute+"containerName']/descendant :: tr /td[actionPerformCol]/descendant :: a[1]";
		}
		
		else{
			colXpath_action=tableattribute+"containerName']/descendant :: tr /td[actionPerformCol]";
		}

		int colToSearch=getColumnNumber(driver, tableattribute+containerName+"']", colNameToSearch);
		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));
		
		int actionPerformCol=getColumnNumber(driver, tableattribute+containerName+"']", actionPerformColName);
		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();
		// start
		do{
			int rowCount=0;

			try{
				logsObj.log("Get Grid row count for column under search");

				List<WebElement> rows_search = WebTableUtil.waitUntilAllVisible(driver,colXpath_search);
				List<WebElement> rows_action = WebTableUtil.waitUntilAllVisible(driver,colXpath_action);

				WaitUtil.pause(100L);
				if (rows_search==null || rows_search.size()== 0)
				{
					Reporting.logStep(driver, Step, staleDesc+"Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " As searched data records are not fetched " ,Constants_FRMWRK.Fail);

				}					
					
				for (WebElement rowSearch : rows_search) { 

					tableData.put(pageNumber+Integer.toString(pageCount)+" "+rowNumber+Integer.toString(rowCount), rowSearch.getText());
					logsObj.log(staleDesc+"Table- "+"Page-"+pageCount+" ,RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

					actual_actionData=rowSearch.getText().trim();
															
					if(searchType.equalsIgnoreCase("contains")){
						condition=searchData.contains(actual_actionData);
					}
					if(searchType.equalsIgnoreCase("contains")&& condition==false){
						condition=actual_actionData.contains(searchData);
					}
					else if (searchType.equalsIgnoreCase("equals")){
						condition=actual_actionData.equals(searchData.trim());
					}

					if(condition){
						WaitUtil.pause(100L);				
						int counter=0;

						try{							
							while (counter< 5){
								try{
									action_column_actualData=rows_action.get(rowCount).getText();
									if(!action_column_actualData.equals(actionData)){										
										break;
									}
									
									if(!actionColumnType.equalsIgnoreCase("TEXT")){										
										if(actionColumnType.equalsIgnoreCase("LINK")){
											rows_action.get(rowCount).click();
										}else{
											Clicks.actionsClick(driver, rows_action.get(rowCount));
										}
										clicked=Constants_FRMWRK.TrueB;
										logsObj.log(staleDesc+"searchforDataInColumnAndClickInAcionableColumn:Successfully clicked the record from required column in the table") ;
										
									}else{
										matches=Constants_FRMWRK.TrueB;
										logsObj.log(staleDesc+"searchforDataInColumnAndClickInAcionableColumn:Successfully matches the record from required column in the table") ;
										
									}
									//commonMethods.actionsClick(driver, rows_action.get(rowCount));	
									break;
								}catch(StaleElementReferenceException ex){
									logsObj.log(staleDesc+"searchforDataInColumnAndClickInAcionableColumn:Click stale") ;
									counter=counter+1;
									WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
									rows_action = WebTableUtil.waitUntilAllVisible(driver,colXpath_action);
								}
								WaitUtil.pause(100L);

							}


						}catch (ElementNotVisibleException e){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, Step, staleDesc+"Unable to click the record from the required column after successfully matching the expected data from search coloumn in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

						} 	
						if(clicked==Constants_FRMWRK.TrueB){
							flag=Constants_FRMWRK.True;
							Reporting.logStep(driver, Step, staleDesc+"Successfully able to located the matching record ' "+searchData+" ' with the required action column data:-"+actionData+" in the table("+colXpath_search+") & clicked on action column", Constants_FRMWRK.Pass);
							
						}else if(matches==Constants_FRMWRK.TrueB){
							flag=Constants_FRMWRK.True;
							Reporting.logStep(driver, Step, staleDesc+"Successfully able to located the matching record ' "+searchData+" ' with the required action column data:-"+actionData+" in the table("+colXpath_search+")", Constants_FRMWRK.Pass);
						}

						break;
					}
					rowCount++;

				}
			}
			catch(StaleElementReferenceException stl){
				isStale=true;
				logsObj.log("searchforDataInColumnAndClickInAcionableColumn:Stale exception... need to recover..") ;
				flag=searchforDataInsearchColumnAndClickInactionableLinkColumn(driver,testcaseName,Step,tableAttribute,containerName,searchType,searchData,actionColumnType,actionData,colNameToSearch,actionPerformColName);
				isStale=false;
				return flag;
			}
			catch(Exception e){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, Step, staleDesc+"Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
				return flag;		
			}
			if(condition==Constants_FRMWRK.TrueB){break;}
			else if(clicked==Constants_FRMWRK.TrueB){break;}
			else if(matches==Constants_FRMWRK.TrueB){break;}
			else{
				pageCount++;
				isNextDisplayed=WebTableUtil.clicknextPage(driver);
			}

		}while (isNextDisplayed==Constants_FRMWRK.TrueB && (clicked==Constants_FRMWRK.FalseB || matches==Constants_FRMWRK.FalseB));
		//
		
		if(condition==false){
			Reporting.logStep(driver, Step, staleDesc+"Unable to locate the matching record "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}		
		else if(!actionColumnType.equalsIgnoreCase("TEXT")&& clicked==Constants_FRMWRK.FalseB){
			Reporting.logStep(driver, Step, staleDesc+"Able to located the matching record "+searchData+" but actual action column data:-"+ action_column_actualData  +"doesnt match with expected action column data:-"+actionData+" in the table "+colXpath_search , Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}
		else if(actionColumnType.equalsIgnoreCase("TEXT")&& matches==Constants_FRMWRK.FalseB){
			//Reporting.logStep(driver, Step, staleDesc+"Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
			Reporting.logStep(driver, Step, staleDesc+"Able to located the matching record "+searchData+" but actual action column data:-"+ action_column_actualData  +"doesnt match with expected action column data:-"+actionData+" in the table "+colXpath_search , Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}
		return flag;
	}




	/**
	 * Get the column number of given text.
	 * @author ShaikK
	 * @param driver
	 * @param tableattribute
	 * @param colName
	 * @return column number
	 */

	private static int getColumnNumber(WebDriver driver,String tableattribute,String colName){
		int columnNum=1;
		//String locatorForColumns=tableattribute+"/descendant :: th[@scope='col']/*[1]";
		String locatorForColumns=tableattribute+"/descendant :: th";
		locatorForColumns=commonMethods.replaceString("colName",locatorForColumns,colName);

		List<WebElement>elements=ExplicitWaitUtil.waitForPresenceOfElements(driver, Constants_FRMWRK.FindElementByXPATH, locatorForColumns, Constants_TimeOuts.Element_TimeOut);

		if(elements.size()!=0){
			for (WebElement element:elements){
				if(element.isDisplayed()==true){
					boolean getelementattribute=false;
					if(element.getAttribute("class").equalsIgnoreCase("ms-vh-div")){
						getelementattribute=true;
					}

					if(getelementattribute==true){
						if(element.getAttribute("displayname").equalsIgnoreCase(colName)){
							break;
						}
					}else {
						if(element.getText().equalsIgnoreCase(colName)){
							break;
						}	
					}
				}
				
				
				columnNum=columnNum+1;
			}


		}

		return columnNum;


	}
	
	/**
	 * 
	 * @date Sep 19 2016
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param colToSearch
	 * @return
	 * @throws Throwable
	 */
	public static Integer getNumberOfRecordsFromTable(WebDriver driver,String testcaseName,String Step,String containerName,int colToSearch) throws Throwable{
		int flag=0;

		WebElement webtableElement=ExplicitWaitUtil.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']",Constants_TimeOuts.Element_TimeOut);
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page to get the records of the grid", Constants_FRMWRK.Fail);

		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";		

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		try{
			logsObj.log("Get Grid row count for column under search");
			final List<WebElement> rows_search = WebTableUtil.waitUntilAllVisible(driver,colXpath_search);			
			flag=rows_search.size();
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;			
			Reporting.logStep(driver, Step, "getNumberOfRecordsFromTable : Could not get count of records for the given table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}

		return flag;
	}
}
