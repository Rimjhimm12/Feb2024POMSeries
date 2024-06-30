package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice.Return;

public class ShoppingCartPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	
	}
	
	public String getShoppingPageTitle() {
		String title = eleUtil.waitForTitleContains("Shopping",TimeUtil.DEFAULT_TIME);
		System.out.println("Title is :- "+title);
		return title;
	}

}
