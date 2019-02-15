package com.proj.objectRepository;

public class ObjRepository {

	// Login page objects	old ones
	/*public static String textbox_login_UserName="logon:username:input";
	public static String textbox_login_Password="logon:userPassword:input";	
	public static String button_login_LoginBeforeCredentials=".//*[@class='realme_button_padding']";
	public static String button_login_Login="logon:logon";	*/
	
	
	//0365 login objects
		public static String textbox_login_UserName=".//*[@id='cred_userid_inputtext']";
		public static String textbox_login_Password=".//*[@id='cred_password_inputtext']";	
		public static String button_login_Signin=".//*[@id='cred_sign_in_button']";
		public static String button_login_OtherUserLink=".//*[@id='use_another_account_link']";
		public static String logo_homepage_exelens=".//*[@id='ctl00_pmTopNav']";
		//new experience
		public static String login_newExperience=".//*[conatins(text(),'new sign-in experience!')]";
		public static String login_newExperience_OtherUserLink=".//*[@id='otherTileText']";
		public static String login_newExperience_UserName=".//input[@id='userNameInput']";
		public static String login_newExperience_Password=".//input[@id='passwordInput']";
		public static String login_newExperience_Next=".//*[@value='Next']";
		public static String login_newExperience_SignIn=".//*[@id='submitButton']";
		public static String login_newExperience_Yes=".//*[@value='Yes']";
	
	//Homepage
	public static String homePage_element="crlHomeImage";
	public static String browserpageTile_mainsite_Home="Fulcrum Test";
	public static String browserpageTile_subsite_Home="Home - CON1";
	
	
	//Overlays
	public static String heading_working=".//*[@title='Working on it...']";
	public static String overlay_working=".//*[text()='Working on it...']";
	public static String popup_AddingDMSDocumentLibrary=".//*[@title='Adding DMS Document Library']";
	public static String overlay_loadingAppForInformation=".//*[text()='Loading App information...']";
	public static String heading_NewFolder=".//*[@title='New Folder']";
	
	//logout objects
	//public static String link_user="zz5_Menu";
	public static String menu_logout="zz5_Menu";
	public static String link_user="//*[@title='Open Menu']";
	public static String link_signOut="//*[@class='ms-core-menu-title' and text()='Sign Out']";

	//frame objects	
	public static String frame_single=".//iframe[contains(@id,'DlgFrame')]";	
	public static String frame_double="(.//iframe[contains(@id,'DlgFrame')])[2]";
	public static String frame_list_pattern="(.//iframe[contains(@id,'DlgFrame')])[framelist]";
	public static String frame_documentList=".//iframe[contains(@class,'doc-lib-view-frame')]";
	public static String frame_pattern=".//iframe[contains(@id,'nameFrame')]";
	public static final String frame_DLG="Dlg";
	public static String frame_dlg_list_pattern="(.//iframe[contains(@id,'DlgFrame')])[framelist]";
	//choice objects
	public static String choice_select=".//*[@title='Add the highlighted item to this field']";
	public static String choice_ok=".//*[contains(@id,'OkButton')]";
	public static String choice_prvpage=".//*[@title='Previous Page']";
	public static String choice_nxtpage=".//*[@title='Next Page']";
	
	
	//popup Objects
	
	//autosuggest text box item objects
	public static String js_autosuggest_input="/../input[2]";
	public static String js_autosuggest_items="/../div/ul/li/a/div";
	
	//dropdown js
	public static String js_dropdown_items="//div[@class='option']/div";
	public static String js_dropdown_autosuggest_items="/div/ul/li";

	//Grid Container
	//public static String container_subMenu="//*[@class='ui popup inverted right center']";
	public static String container_subMenu=".//*[@class='leftnav-item-dropdown' and contains(@style,'block')]";
	public static String container_transmittals="Mail";
	public static String container_documentRegister="Document Register";
	public static String container_transmittalFiles="transmittalFiles";
	public static String container_supportingDocumentFiles="supportingDocumentFiles";
	public static String grid_nextButton=".//*[contains(@id,'next')]/a";
	public static String container_uploadedOrNewDocuments="Documents";
	public static String container_dmsLibrary="Automation DMS Library";
	public static String container_actions="txActionListTbl";
	
	//close
	public static String icon_close="//*[@title='Close dialog']";	
	
	
	
	
	//Cookies popup
	


	//session timeout objects



	//Icons

}
