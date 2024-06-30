package com.qa.opencart.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic number: With description")
@Story("Story ID: description")
@Feature("F!0:>>>>>")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{
	@Description("Login Page Title validation")
	@Severity(SeverityLevel.MINOR)
	@Owner("owner Name")
	@Issue("Bug ID")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);	
	}
	
	@Description("Login page URL validation")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageurl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);	
	}
	
	
	@Description("Forgot password link validation")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPasswordExistTest() {

		Assert.assertTrue(loginPage.checkForgotPasswordLinkExist(),AppError.ELEMENT_NOT_FOUND);
	
		
	}
	
	
	@Description("Validation login ")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
		
		
	
		
	}
	
	
	
	

}
