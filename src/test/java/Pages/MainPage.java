package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	public WebDriver driver;
	JavascriptExecutor executor;
	Actions action;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public MainPage(WebDriver driver){
		this.wait = new WebDriverWait(driver, 10);
		this.driver = driver;
		this.action = new Actions(driver);
		this.executor = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id=\"menu-item-567\"]")
	public WebElement shopSectionLink;
	
	@FindBy(xpath = "//*[@id=\"menu-item-493\"]")
	public WebElement contactSectionLink;
	
	public void clickShopSectionLink() {
		shopSectionLink.click();
	} 
	
	public void clickContactSectionLink() {
		contactSectionLink.click();
	}

}
