package com.qa.opencart.constants;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebElement;

public class AppConstants {
	private AppConstants() {}
	
	public static final String CONFIG_FILE_PATH = "./src/tests/resources/config/config.properties";
	public static final String QA_FILE_PATH = "./src/tests/resources/config/qa.properties";
	public static final String DEV_FILE_PATH = "./src/tests/resources/config/dev.properties";
	public static final String STAGE_FILE_PATH = "./src/tests/resources/config/stage.properties";
	public static final String UAT_FILE_PATH = "./src/tests/resources/config/uat.properties";
	
	
	
	
	
	public static final String LOGIN_PAGE_TITLE= "Account Login11";
	public static final String LOGIN_PAGE_FRACTION_URL= "login";
	public static final String ACCOUNT_PAGE_TITLE= "My Account";
	public static final String ACCOUNT_PAGE_FRACTION_URL = "account";
	public static final List<String>  ACC_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders","My Affiliate Account","Newsletter");
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	
	
	//*****************SHEET CONSTANTS************//
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_IMAGES_SHEET_NAME = "productimages";
	
			
	


}
