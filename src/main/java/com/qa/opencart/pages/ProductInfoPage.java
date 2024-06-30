package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	
	}
	private By productHeader = By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String> productMap;
	
	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("product header ==== "+header);
		
		return header;
	}
	
	public int getProductImageCount() {
		
		int imageCount = eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_TIME).size();
		return imageCount;
		
		
	}
	
	public Map<String, String> getProductInfoMap() {
		//productMap = new HashMap<String, String>(); //it gives the random value depending on the hashkey
		//productMap = new LinkedHashMap<String, String>();// it follows the insertion order
		productMap = new TreeMap<String, String>();//it follows the alphabetic order
		
		productMap.put("productname", getProductHeader());
		productMap.put("productimagescount", String.valueOf(getProductImageCount()));


		getProductMetaData();
		getProductPriceData();
		return productMap;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> MataList = eleUtil.getElements(productMetaData);
		for (WebElement e : MataList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
			
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
			
		}
	
	
	
	public String addToCart() {

		eleUtil.doSendKeys(By.id("input-quantity"), "2", TimeUtil.DEFAULT_LONG_TIME);
		eleUtil.doClick(By.xpath("//button[text()='Add to Cart']"));
		
		String successMessage = eleUtil.waitForElementVisible(By.xpath("//div[@class='alert alert-success alert-dismissible']"), 5).getText();
		System.out.println("Success message is:- "+successMessage);
		return successMessage;
		
		
	}
	
	public ShoppingCartPage getShoppingCartPage() {
		eleUtil.doClick(By.xpath("//div[@class='alert alert-success alert-dismissible']/a[text()='shopping cart']"), TimeUtil.DEFAULT_LONG_TIME);
		return new ShoppingCartPage(driver);
	}
	

}
