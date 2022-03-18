package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShopSection {
	
	public WebDriver driver;
	JavascriptExecutor executor;
	Actions action;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public ShopSection(WebDriver driver){
		this.wait = new WebDriverWait(driver, 10);
		this.driver = driver;
		this.action = new Actions(driver);
		this.executor = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h2[class='woocommerce-loop-product__title']")
	public List<WebElement> productList;
	
	@FindBy(xpath = "//*[@class='button product_type_simple add_to_cart_button ajax_add_to_cart']")
	public List<WebElement> addToCartList;
	
	@FindBy(xpath = "//section[@id='woocommerce_recently_viewed_products-1']/ul/li")
	public List<WebElement> recentlyViewedList;
	
	@FindBy(xpath = "//a[contains(text(),'View cart')]")
	public WebElement viewCartLink;
	
	@FindBy(xpath = "//input[@id]")
	public WebElement searchInput;
	
	@FindBy(xpath = "//button[@value='Search']")
	public WebElement searchBtn;
	
	@FindBy(css = "select[name='orderby']")
	public WebElement dropDownOrder;
	
	@FindBy(css = "option[value='price']")
	public WebElement lowToHighOpt;
	
	@FindBy(css = "option[value='price-desc']")
	public WebElement highToLowOpt;
	
	@FindBy(xpath = "(//span[@class='price'])[4]")
	public WebElement currentPrice;
	
	@FindBy(xpath = "//*[@id=\"primary-menu\"]/li[9]/a/i")
	public WebElement shoppingCart;
	
	
	public void sendSearchKeys(String keyword) {
		searchInput.sendKeys(keyword);
	}
	
	public void clickSearchBtn() {
		searchBtn.click();
	}
	
	public void dropDownOrderClick() {
		dropDownOrder.click();
	}
	
	public void lowToHighClick() {
		lowToHighOpt.click();
	}
	
	public void highToLowClick() {
		highToLowOpt.click();
	}
	
	public void checkOrderHtoL() {
		float beforePrice = Float.parseFloat(currentPrice.getText().substring(1));
		float currentPriceF = 0.00F;
		String myPath = "";
		for(int i = 5; i < 10; i++) {
			myPath = "(//span[@class='price'])[" + i + "]";
			currentPrice = driver.findElement(By.xpath(myPath));
			currentPriceF = Float.parseFloat(currentPrice.getText().substring(1));
			System.out.println("my number " + (i-1) + " is: " + beforePrice);
			System.out.println("my number " + i + " is: " + currentPrice.getText().substring(1));
			Assert.assertTrue(beforePrice >= currentPriceF);
			beforePrice = currentPriceF;
		}
	}
	
	public void countProducts() {
		int numOfProducts = productList.size();
		Assert.assertEquals(numOfProducts, 9);
	}
	
	public String selectShopProduct(int index) {
		String productName = productList.get(index).getText();
		productList.get(index).click();
		return productName;
	}
	
	public void checkIfRecentlySeen(String productName) {
		System.out.println(recentlyViewedList.get(0).getText());
	}
	
	public String addToCart(int index) {
		String productName = productList.get(index).getText();
		addToCartList.get(index).click();
		return productName;
	}
	
	public void clickViewCartLink() {
		viewCartLink.click();
	}
	
	public void clickShoppingCart() {
		shoppingCart.click();
	}
	
	public void checkOrderLtoH() {
		float beforePrice = Float.parseFloat(currentPrice.getText().substring(1));
		float currentPriceF = 0.00F;
		String myPath = "";
		for(int i = 1; i < 10; i++) {
			myPath = "(//span[@class='price'])[" + i + "]";
			currentPrice = driver.findElement(By.xpath(myPath));
			currentPriceF = Float.parseFloat(currentPrice.getText().substring(1));
			System.out.println("my number " + (i-1) + " is: " + beforePrice);
			System.out.println("my number " + i + " is: " + currentPrice.getText().substring(1));
			Assert.assertTrue(beforePrice <= currentPriceF);
			beforePrice = currentPriceF;
		}
	}
}
