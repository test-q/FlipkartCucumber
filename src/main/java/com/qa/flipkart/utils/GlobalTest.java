package com.qa.flipkart.utils;

import org.openqa.selenium.WebDriver;

import com.qa.flipkart.pages.CheckoutPage;
import com.qa.flipkart.pages.MyCartPage;
import com.qa.flipkart.pages.ProductDetailPage;
import io.cucumber.java.Scenario;

public class GlobalTest {
	protected  WebDriver driver;
	protected Scenario scenario;
	protected ProductDetailPage productDetailPage;
	protected MyCartPage myCartPage;
	protected CheckoutPage checkoutPage;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public ProductDetailPage getProductDetailPage() {
		return productDetailPage;
	}
	
	public MyCartPage getMyCartpage() {
		return myCartPage;
	}
	
	public CheckoutPage getCheckoutPage() {
		return checkoutPage;
	}
	
	public void initializePageObject(WebDriver driver, Scenario scenario) {
		productDetailPage = new ProductDetailPage(driver, scenario);
		myCartPage = new  MyCartPage(driver,scenario);
		checkoutPage = new CheckoutPage(driver, scenario);
		
	}

}
