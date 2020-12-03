package com.qa.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.flipkart.utils.BaseTest;
import com.qa.flipkart.utils.ConstantUtil;
import com.qa.flipkart.utils.ElementUtils;

import io.cucumber.java.Scenario;

public class MyCartPage extends BaseTest{
	private WebDriver driver;
	ElementUtils eleUtil;
	Scenario scenario;
	
	//Constructor of page
	public MyCartPage(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
		eleUtil = new ElementUtils(driver);
	}
	
	//Locator
	private By productName = By.xpath("//a[@class='_2Kn22P gBNbID']");
	private By saveForLater = By.xpath("(//div[@class='_3dsJAO'])[1]");
	private By remove = By.xpath("(//div[@class='_3dsJAO'])[2]");
	private By quantity = By.xpath("//input[@class='_253qQJ']");
	private By placeOrderbutton = By.xpath("//button[@class='_2KpZ6l _2ObVJD _3AWRsL']");
	
	//Actions
	public String getMyCartPageTitle() {
		return eleUtil.doGetTitle();
	}
	
	public String getMyCartPageURL() {
		if(eleUtil.waitForUrl(ConstantUtil.MYCART_URL, 10)) {
			return  eleUtil.doGetCurrentUrlPage();
		}
		return null;	
	}
	
	public boolean isRemoveLinkDisplay() {
		return eleUtil.doIsDisplayed(remove);
	}
	
	public boolean isSaveForLaterLinkDisplay() {
		return eleUtil.doIsDisplayed(saveForLater);
	}
	
	public boolean doCheckQuantity() {
		String attribute = "value";
		boolean flag = false;
		try {
			Thread.sleep(2000);
			String itemCount = eleUtil.doGetAttribute(quantity, attribute);
			if(! itemCount.isEmpty()) {
				System.out.println("No. Of products: " +itemCount);
				flag = true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return flag;	
	}
	
	public int getItemCartQuantity() {
		String attribute = "value";
		String itemCount = eleUtil.doGetAttribute(quantity, attribute);
		int iCount = Integer.parseInt(itemCount);
		return iCount;
	}
	
	public void clickOnProductName() {
		eleUtil.doClick(productName);
	}
	
	public void waitForPlaceOrderButton() {
		eleUtil.waitForElementToBeVisible(placeOrderbutton, 30);
	}

	

}
