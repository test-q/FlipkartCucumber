package com.qa.flipkart.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	
	WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public void doGetPage(String url) {
		driver.get(url);
	}
	
	public String doGetCurrentUrlPage() {
		return driver.getCurrentUrl();
	}
	
	public String doGetTitle() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}
	
	public String doGetAttribute(By locator, String key) {
		String valueOfAttribute = "";
		if(key.equals("value")) {
			valueOfAttribute = getElement(locator).getAttribute(key);
		}else {
			System.out.println("Pass the correct Attribute key.");
		}
		return valueOfAttribute;
	}

	public List<WebElement> getElementList(By locator) {
		List<WebElement> eleList = driver.findElements(locator);
		return eleList;
	}
	
	public void doClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator).isDisplayed();
	}
	
	// ***************************** Wait UTILITY ******************************


	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public Boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public String waitForTitlePresent(String titleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
	// ***************************** Flash  UTILITY ******************************
	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 20; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}
	
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(BaseTest.flashElement))
		flash(element);
		return element;
	}
	
}
