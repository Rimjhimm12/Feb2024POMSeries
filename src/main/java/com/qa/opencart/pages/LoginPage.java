package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Page Object : By locators 
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By register = By.xpath("//div[@class='list-group']/a[text()='Register']");
	
	//2.public constructor of this page
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
		
	}
	
	//3. Public page actions/methods 
	@Step("getting login page title ")
	public String getLoginPageTitle() {
		
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		return title;
	}
	@Step("getting login page url ")
	public String getLoginPageurl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		return url;
	}
	
	public boolean checkForgotPasswordLinkExist() {
		 return eleUtil.doIsDisplayed(forgetPwdLink);
	}
	@Step("getting login username: {0} and password:{1} ")
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegistationPage() {
		eleUtil.doClick(register,TimeUtil.DEFAULT_TIME);
		return new RegistrationPage(driver);
	}

}
