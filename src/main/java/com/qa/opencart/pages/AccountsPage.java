package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		return title;
	}
	
	
	public String getAccountPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	public List<String> getAccpageHeaders() {
		
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElemenetsLocated(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public boolean isSearchExist() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching: "+searchKey);
		
		if(isSearchExist()) {
			
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver); //TDD: Test Driven development 
		}
		else {
			System.out.println("Search field is not present on the page");
			return null;
		}
	}
	
	

}