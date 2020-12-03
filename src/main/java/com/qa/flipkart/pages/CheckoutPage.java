package com.qa.flipkart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.flipkart.utils.ConstantUtil;
import com.qa.flipkart.utils.ElementUtils;

import io.cucumber.java.Scenario;

public class CheckoutPage {
	private WebDriver driver;
	ElementUtils eleUtil;
	Scenario scenario;
	
	public CheckoutPage(WebDriver driver, Scenario scenario ) {
		this.driver = driver;
		this.scenario = scenario;
		eleUtil = new ElementUtils(driver);
	}
		
	public String getCheckoutPageURL() {
		if(eleUtil.waitForUrl(ConstantUtil.CHECKOUT_URL, 10)) {
			return  eleUtil.doGetCurrentUrlPage();
		}
		return null;	
	}


}
