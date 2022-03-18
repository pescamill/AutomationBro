package Steps;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import Pages.CartPage;
import Pages.ContactPage;
import Pages.MainPage;
import Pages.ShopSection;


public class PracticeAutomationSteps {
	WebDriver driver;
	JavascriptExecutor executor;
	Actions action;

	MainPage mainPage;
	ShopSection shopSection;
	ContactPage contactPage;
	CartPage cartPage;

	public PracticeAutomationSteps(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.executor = (JavascriptExecutor)driver;
		this.mainPage = new MainPage(driver);
		this.shopSection = new ShopSection(driver);
		this.contactPage = new ContactPage(driver);
		this.cartPage = new CartPage(driver);
	}
	
	public void sortHighToLow() {
		mainPage.clickShopSectionLink();
		shopSection.dropDownOrderClick();
		shopSection.highToLowClick();
		shopSection.checkOrderHtoL();
	}
	
	public void countProducts() {
		mainPage.clickShopSectionLink();
		shopSection.countProducts();	
	}
	
	public void sortLowToHigh() {
		mainPage.clickShopSectionLink();
		shopSection.dropDownOrderClick();
		shopSection.lowToHighClick();
		shopSection.checkOrderLtoH();
	}
	
	public void shopSearch(String keyword) {
		mainPage.clickShopSectionLink();
		shopSection.sendSearchKeys(keyword);
		shopSection.clickSearchBtn();
	}
	
	public void checkIfRecentlySeenA(int index) {
		mainPage.clickShopSectionLink();
		String productName = shopSection.selectShopProduct(index);
		mainPage.clickShopSectionLink();
		shopSection.checkIfRecentlySeen(productName);
	}
	
	public void checkIfRecentlySeenB(int index) {
		mainPage.clickShopSectionLink();
		String productName = shopSection.selectShopProduct(index);
		mainPage.clickShopSectionLink();
		shopSection.dropDownOrderClick();
		shopSection.lowToHighClick();
		shopSection.checkIfRecentlySeen(productName);
	}
	
	public void checkContactInfo() {
		mainPage.clickContactSectionLink();
		contactPage.checkIfAddressDisplays();
		contactPage.checkIfEmailDisplays();
		contactPage.checkIfPhoneDisplays();
		contactPage.checkIfTimeDisplays();
	}
	
	public void checkCallUsButton() {
		mainPage.clickContactSectionLink();
		contactPage.clickCallUsButton();
		contactPage.assertIfPopUp();
	}
	
	public void buyAndCheckout(int index) throws InterruptedException {
		Set<String> products = new HashSet<String>();
		mainPage.clickShopSectionLink();
		products.add(shopSection.addToCart(index));
		Thread.sleep(3000);
		shopSection.clickShoppingCart();
		cartPage.checkTableProducts(products);
		cartPage.clickCheckOutBtn();
	}
	
	public void buyAndDelete(int index) throws InterruptedException {
		String toDelete = "";
		Set<String> products = new HashSet<String>();
		mainPage.clickShopSectionLink();
		String product = shopSection.addToCart(index);
		products.add(product);
		toDelete = product;
		Thread.sleep(3000);
		shopSection.clickShoppingCart();
		cartPage.checkTableProducts(products);
		cartPage.deleteProduct(toDelete);
		cartPage.assertDelete(toDelete);
	}
	
}
