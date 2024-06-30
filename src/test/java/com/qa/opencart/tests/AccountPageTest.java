package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl().contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accPageHeadersList = accPage.getAccpageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppError.LIST_IS_NOT_MATCHED);
	}
	
	@DataProvider
	public Object[][] getSearchData(){
		return new Object[][] {
			{"iMac",1},
			{"macBook",3},
			{"iPhone",1},
			{"Airtel",0}
		};
	}
	
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, int resultSearch) {
		searchResultPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultPage.getSearchResultCounts(),resultSearch, AppError.RESULT_COUNT_MISMATCHED);
		
	}
	
	
}
