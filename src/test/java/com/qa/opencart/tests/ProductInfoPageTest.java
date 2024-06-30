package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
			{"canon", "Canon EOS 5D"}
			

		};
	}
	
	
	
	@Test(dataProvider = "getProductData", priority = 1)
	public void ProductHeaderTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfo = searchResultPage.selectProduct(productName);
		Assert.assertTrue(productInfo.getProductHeader().contains(productName), AppError.HEADER_NOT_FOUND);
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", "4"},
			{"imac", "iMac", "3"},
			{"samsung", "Samsung SyncMaster 941BW", "1"},
			{"samsung", "Samsung Galaxy Tab 10.1", "7"},
			{"canon", "Canon EOS 5D", "3"}
			

		};
	}
	
	@DataProvider
	public Object[][] getProductDataImagesFromSheet(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_IMAGES_SHEET_NAME);
		
	}
	
	@Test(dataProvider = "getProductDataImagesFromSheet", priority = 2)
	public void ProductImageCountTest(String searchKey, String productName, String imagesCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfo = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfo.getProductImageCount(),Integer.parseInt(imagesCount), AppError.IMAGES_COUNT_MISMATCHED);
	}
	
	@DataProvider
	public Object[][] getProductDataCount() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
			
			
			

		};
	}
	
	
	
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfo = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfo.getProductInfoMap();
		System.out.println("======product information========");
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
		softAssert.assertAll();
		
		System.out.println("=====Test is completed======");
		
	}
	
	//Assert vs verify
		//hard assert(Assert) vs soft assert(verify - SoftAssert)
		//Assert---> methods (static)
		//SoftAssert ---> methods (non static)
		
		//single assertion -- hard assertion
		//multiple assertions -- soft assertion
	
	@Test(dataProvider = "getProductDataCount", priority = 3)
	public void AddToCartTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfo = searchResultPage.selectProduct(productName);
		Assert.assertTrue(productInfo.addToCart().contains("shopping cart!"), AppError.SUCCESS_MESSAGE_NOT_FOUND);
		
	}
	
	@Test(priority = 4)
	public void ShoppingCartPageTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfo = searchResultPage.selectProduct("MacBook Pro");
		productInfo.addToCart();
		shoppingCart = productInfo.getShoppingCartPage();
		Assert.assertEquals(shoppingCart.getShoppingPageTitle(),"Shopping Cart", AppError.TITLE_NOT_FOUND);
		
	}
	
	

}
