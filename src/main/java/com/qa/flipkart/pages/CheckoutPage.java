package com.qa.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.flipkart.utils.ElementUtils;

import io.cucumber.java.Scenario;

public class CheckoutPage {
	private WebDriver driver;
	ElementUtils eleUtil;
	Scenario scenario;
	
	//Constructor
	public CheckoutPage(WebDriver driver, Scenario scenario ) {
		this.driver = driver;
		this.scenario = scenario;
		eleUtil = new ElementUtils(driver);
	}
	
	//By Locator
	private By paymentOption = By.xpath("(//span[@class='_1aULyb'])[last()]");
	
	//Actions
	public boolean isPaymentOptionVisible() {
		boolean flag = false;
		if(eleUtil.doIsDisplayed(paymentOption)) {
			flag = true;
		}
		return flag;	
	}



}
