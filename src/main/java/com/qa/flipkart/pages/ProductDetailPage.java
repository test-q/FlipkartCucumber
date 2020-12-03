package com.qa.flipkart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.flipkart.utils.BaseTest;
import com.qa.flipkart.utils.ConstantUtil;
import com.qa.flipkart.utils.ElementUtils;
import io.cucumber.java.Scenario;

public class ProductDetailPage extends BaseTest {

	private WebDriver driver;
	ElementUtils eleUtil;
	Scenario scenario;

	// Constructor of page
	public ProductDetailPage(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
		eleUtil = new ElementUtils(driver);
	}

	// By Locator
	private By allavailableOffers = By.xpath("//div[@class='XUp0WS']/span");
	private By addToCartButton = By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']");
	private By colorOption = By.xpath("//span[@id='Color']");
	private By colorChoice = By.xpath("(//div[@class='_2C41yO _1pH70n _31hAvz'])[position()=3]");
	private By sizeChoice = By.xpath("(//a[@class='_1fGeJ5 _2UVyXR _31hAvz'])[position()=1]");
	private By sizeOption = By.xpath("//span[@id='Size']");
	private By buyNow = By.xpath("//button[@class='_2KpZ6l _2U9uOA ihZ75k _3AWRsL']");
	private By viewMoreOffer = By.xpath("//button[@class='_1JIkBw']");
	private By sizeError = By.xpath("//span[text()='Please select a Size to proceed']");
	private By wishlist = By.xpath("//div[@class='_36FSn5']");
	private By login = By.xpath("//span[@class='_36KMOx']");
	private By loginMessage = By.xpath("//p[@class='_1-pxlW']/span");

	// Actions
	public Boolean getProductPage() {
		eleUtil.doGetPage(ConstantUtil.PRODUCTDETAILPAGE_URL);
		return eleUtil.waitForUrl(ConstantUtil.PRODUCTDETAILPAGE_URL, 10);
	}

	public String getProductPageTitle() {
		return eleUtil.doGetTitle();
	}

	public String getProductPageURL() {
		if (eleUtil.waitForUrl(ConstantUtil.PRODUCTDETAILPAGE_URL, 10)) {
			return eleUtil.doGetCurrentUrlPage();
		}
		return null;
	}

	public void getProductColor() {
		if (eleUtil.doIsDisplayed(colorOption)) {
			eleUtil.doClick(colorChoice);
		} else {
			System.out.println("Color choice option is not available");
		}
	}

	public void getProductSize() {
		if (eleUtil.doIsDisplayed(sizeOption)) {
			eleUtil.doClick(sizeChoice);
		} else {
			System.out.println("Size choice option is not available");
		}
	}

	public void clickAddToCart() {
		eleUtil.doClick(addToCartButton);
	}

	public void clickGoToCart() {
		eleUtil.doClick(addToCartButton);
	}

	public void clickBuyNowButton() {
		eleUtil.doClick(buyNow);
	}

	public void clickMoreOffer() {
		eleUtil.doClick(viewMoreOffer);
	}

	public boolean getAllAvailableOffer() {
		boolean flag = false;
		List<WebElement> offerListEle = eleUtil.getElementList(allavailableOffers);
		List<String> offerList = new ArrayList<String>();
		int noOfOffers = offerList.size();
		System.out.println("No of Offers Available: " + noOfOffers);
		for (WebElement ele : offerListEle) {
			String offerName = ele.getText();
			offerList.add(offerName);
		}
		for (int i = 0; i < offerList.size(); i++) {
			System.out.println("Offer " + (i + 1) + " : " + offerList.get(i));
		}

		if (!offerList.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	public void waitForBuyNow() {
		eleUtil.waitForElementToBeVisible(buyNow, 30);
	}

	public void waitForSizeError() {
		eleUtil.waitForElementToBeVisible(sizeError, 30);
	}

	public String getButtontext() {
		return eleUtil.doGetText(addToCartButton);
	}

	public String getSizeError() {
		return eleUtil.doGetText(sizeError);
	}

	public void clickWishListIcon() {
		eleUtil.doClick(wishlist);
	}

	public String getLoginPopUp() {
		eleUtil.waitForElementToBeVisible(login, 30);
		String loginmessage = eleUtil.doGetText(loginMessage);
		return loginmessage;
	}

}
