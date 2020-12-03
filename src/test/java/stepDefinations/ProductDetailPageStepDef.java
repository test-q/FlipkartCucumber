package stepDefinations;

import org.junit.Assert;

import com.qa.flipkart.utils.BaseTest;
import com.qa.flipkart.utils.ConstantUtil;
import com.qa.flipkart.utils.GlobalTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;


public class ProductDetailPageStepDef extends BaseTest{

	private GlobalTest globalTest;
	Scenario scenario;
	
	public ProductDetailPageStepDef(GlobalTest globalTest) {
		this.globalTest = globalTest;
	}
	
	@Before
	public void setup(Scenario scenario) {
		this.scenario = scenario;
		System.out.println(scenario.getName() + " :: SCENARIO STARTING");
	}
	
	@After
	public void cleanUp() {
		System.out.println(scenario.getName() + " :: " + scenario.getStatus() +"\n \n");
		if(scenario.isFailed()) {
			byte[] srcData = BaseTest.getScreenshot();
			scenario.embed(srcData, "image/png");
		}
		BaseTest.closeBrowser();
		scenario.write("Browser is closed");
	}
	
	@Given("Browser is invoked.")
	public void browser_is_invoked() {
		driver = BaseTest.initialization();
		globalTest.setDriver(driver);
		globalTest.initializePageObject(driver, scenario);
		scenario.write("Browser is invoked");
	   
	}

	@When("User navigate to product detail page")
	public void user_navigate_to_product_detail_page() {
		boolean flag = globalTest.getProductDetailPage().getProductPage();
		Assert.assertEquals(true, flag);
		scenario.write("User On product Detail Page.");
		scenario.write("Page URL: " + ConstantUtil.PRODUCTDETAILPAGE_URL);
	}

	@Then("Validate page title.")
	public void validate_page_title() {
		String title = globalTest.getProductDetailPage().getProductPageTitle();
		scenario.write("Page Title: " +title);
		Assert.assertEquals(ConstantUtil.PRODUCTDETAILPAGE_TITLE, title);
	}

	@Given("Non logged in user on a product detail page")
	public void non_logged_in_user_on_a_product_detail_page() {
		boolean flag = globalTest.getProductDetailPage().getProductPage();
		Assert.assertEquals(true, flag);
		scenario.write("User is on product detail page");
	}

	@When("User select the colour\\/size")
	public void user_select_the_colour_size() {
		globalTest.getProductDetailPage().getProductColor();
		globalTest.getProductDetailPage().getProductSize();
		scenario.write("Color and size of the product is selected");
	}

	@When("Click the add to Add to cart button")
	public void Click_the_add_to_Add_to_cart_button() {
		globalTest.getProductDetailPage().clickAddToCart();
		scenario.write("Add to cart button clicked.");
	}

	@Then("The product is added to my shopping basket")
	public void the_product_is_added_to_my_shopping_basket() {
		boolean flag = globalTest.getMyCartpage().doCheckQuantity();
		Assert.assertEquals(true, flag);
		scenario.write("Product is successfully added to my shopping basket");
	
	}

	@Then("User redirected to My cart page.")
	public void user_redirected_to_My_cart_page() {
		String myCartTitle = globalTest.getMyCartpage().getMyCartPageTitle();
		Assert.assertEquals(ConstantUtil.MYCART_TITLE, myCartTitle);
		String myCartPageUrl = globalTest.getMyCartpage().getMyCartPageURL();
		Assert.assertEquals(ConstantUtil.MYCART_URL, myCartPageUrl);
		scenario.write("User Successfully redirected to My cart.");
	
	}
	

	@When("Click the Buy now button")
	public void click_the_Buy_now_button() {
		globalTest.getProductDetailPage().clickBuyNowButton();
		scenario.write("Buy Now button click Successfully");
	}
	
	@Then("User redirected to Checkout up page.")
	public void user_redirected_to_Checkout_up_page() {
		boolean flag = globalTest.getCheckoutPage().isPaymentOptionVisible();
		Assert.assertEquals(true, flag);
		scenario.write("User redirected to login page");
	}
	
	@When("Click on More Offer link")
	public void click_on_More_Offer_link() {
		globalTest.getProductDetailPage().clickMoreOffer();
		scenario.write("");
	}


	@Then("All the available offers are visible.")
	public void all_the_available_offers_are_visible() {
		boolean flag = globalTest.getProductDetailPage().getAllAvailableOffer();
		Assert.assertEquals(true,flag);
		scenario.write("All the available offers are visible.");
		
	}
	
	@When("User try to add same product in cart")
	public void user_try_to_add_same_product_in_cart() {
		globalTest.getProductDetailPage().getProductColor();
		globalTest.getProductDetailPage().getProductSize();
		globalTest.getProductDetailPage().clickAddToCart();
		globalTest.getMyCartpage().waitForPlaceOrderButton();
		globalTest.getMyCartpage().clickOnProductName();
		globalTest.getProductDetailPage().waitForBuyNow();
		globalTest.getProductDetailPage().getProductColor();
		globalTest.getProductDetailPage().getProductSize();
		
	}
	
	@Then("The product is not added to my shopping basket")
	public void the_product_is_not_added_to_my_shopping_basket() {
		globalTest.getMyCartpage().waitForPlaceOrderButton();
		int iQuantity = globalTest.getMyCartpage().getItemCartQuantity();
		Assert.assertEquals(1, iQuantity);
		scenario.write("Product is not added to my shopping basket and iteam quntity : " + iQuantity);
		
	}

	@When("Click the Go To Cart button")
	public void click_the_Go_To_Cart_button() {
		globalTest.getProductDetailPage().clickGoToCart();
	}

	
	@And("Go to same product detail page")
	public void go_to_same_product_detail_page() {
		globalTest.getMyCartpage().waitForPlaceOrderButton();
		globalTest.getMyCartpage().clickOnProductName();
	}
	
	@Then("Add To cart button text change to Go To Cart")
	public void add_To_cart_button_text_change_to_Go_To_Cart() {
		globalTest.getProductDetailPage().waitForBuyNow();
		String buttonText = globalTest.getProductDetailPage().getButtontext();
		Assert.assertEquals(ConstantUtil.PRODUCTDETAILPAGE_GOTOCART_BUTTON_TEXT, buttonText);
	}
	
	@Then("Please select a size to proceed text message display")
	public void please_select_a_size_to_proceed_text_message_display() {
		String sizeError = globalTest.getProductDetailPage().getSizeError();
		Assert.assertEquals(ConstantUtil.PRODUCTDETAILPAGE_SIZEERROR, sizeError);
		scenario.write("Product is not added to cart and Size error message display : " + sizeError);
	}
	
	@When("Click on WishList icon")
	public void click_on_WishList_icon() {
	  globalTest.getProductDetailPage().clickWishListIcon();
	}

	@Then("Login pop is open")
	public void login_pop_is_open() {
		String textMessage = globalTest.getProductDetailPage().getLoginPopUp();
		Assert.assertEquals(ConstantUtil.LOGIN_POPUP, textMessage);
		scenario.write("Message on Login Popup " + textMessage);
	  
	}
	
}
