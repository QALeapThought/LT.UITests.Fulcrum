package com.proj.suiteTRANSMITTALS.reusables;

import java.util.Hashtable;

import com.frw.util.ExcelUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.base.TestBase;

public class Users extends TestBase{
	
	public static Xls_Reader xlsReader_genericdata=new Xls_Reader(Constants.DataFileLocation_Transmittal+"Generic_Data.xlsx");
	
	public static Hashtable<String,String>users_type=null;
	public static Hashtable<String,String>users_username=null; 
	public static Hashtable<String,String>users_password=null;
	public static Hashtable<String,String>users_accountName=null;
	public static Hashtable<String,String>users_siteaccess=null;
	public static Hashtable<String,String>users_site_homepagetitle=null;
	public static Hashtable<String,String>users_username_useraccount=null;
	
	
	
	public static void fetchUsers(){
		String sheetname="";
		if(Constants_ConfigProperties.environment.equalsIgnoreCase("TEST")){
			sheetname="user_credentials_test";
		}
		else if (Constants_ConfigProperties.environment.equalsIgnoreCase("QA2")){
			sheetname="user_credentials_QA2";
		}
		
		logsObj.log("fetchusers method triggred for test case:"+testcaseName);		
		users_accountName=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Account Name","Account Name");
		users_username=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Account Name","Username");
		users_password=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Account Name","Password");
		users_site_homepagetitle=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Account Name","Home Page Title");
		users_type=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Type","Account Name");
		users_siteaccess=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Username","Site Access");
		users_username_useraccount=ExcelUtil.getParameters(xlsReader_genericdata,sheetname,"Username","Account Name");
		
		//LinkedHashMap<String, Hashtable<String, String>> user_details=CheckListDesignerPage.getAllRowsdataFromExcel_2(xlsReader_usersdata, "user_credentials");
	}
	
	/*public static LinkedHashMap<String, Hashtable<String, String>> fetchUsers_2(){
		String sheetname="";
		if(Constants_ConfigProperties.environment.equalsIgnoreCase("QA")){
			sheetname="user_credentials_QA";
		}
		else if (Constants_ConfigProperties.environment.equalsIgnoreCase("QA2")){
			sheetname="user_credentials_QA2";
		}
		LinkedHashMap<String, Hashtable<String, String>> user_details=CheckListDesignerPage.getAllRowsdataFromExcel_2(xlsReader_genericdata,sheetname );
		return user_details;
	}*/

	
	public static void setLoginUser(String user){
		TestBase.login_userName= Users.users_username.get(user);		
		String accountName=Users.users_username_useraccount.get(TestBase.login_userName);
		TestBase.login_password=Users.users_password.get(accountName);
		TestBase.landingPage_title=Users.users_site_homepagetitle.get(accountName);
	}
}
