package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getRandomEmailId() {
		
		String emailId = "userauto"+System.currentTimeMillis()+"@opencart.com";
		return emailId;
		
	}

}