package Pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactPage {
	
	public WebDriver driver;
	JavascriptExecutor executor;
	Actions action;
	WebDriverWait wait;
	
	private final String addressContent = "Moon Street , 446 Jupiter, JP 44600";
	private final String emailContent = "zakra@demos.com first.last@demos.com";
	private final String phoneContent = "Call : +(123) 456-7890 Call : +977 123-456789";
	private final String timeContent = "Monday - Saturday : 9AM - 6 PM Sunday : Closed";
	
	@SuppressWarnings("deprecation")
	public ContactPage(WebDriver driver){
		this.wait = new WebDriverWait(driver, 10);
		this.driver = driver;
		this.action = new Actions(driver);
		this.executor = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*/*/*[@class='elementor-image-box-content']")
	public List<WebElement> contactElements;
	
	@FindBy(css = "span[class='elementor-button-text']")
	public WebElement callUsButton;
	
	public void checkIfAddressDisplays() {
		Assert.assertEquals(contactElements.get(0)
					.findElement(By.tagName("p")).getText(),addressContent);
	}
	
	public void checkIfEmailDisplays() {
		Assert.assertEquals(contactElements.get(1)
				.findElement(By.tagName("p")).getText(),emailContent);
	}
	
	public void checkIfPhoneDisplays() {
		Assert.assertEquals(contactElements.get(2)
				.findElement(By.tagName("p")).getText(),phoneContent);
	}
	
	public void checkIfTimeDisplays() {
		Assert.assertEquals(contactElements.get(3)
				.findElement(By.tagName("p")).getText(),timeContent);
	}
	
	public void clickCallUsButton() {
		callUsButton.click();
	}
	
	public void assertIfPopUp() {
		Set<String> handles = driver.getWindowHandles();
		Assert.assertTrue(handles.size() > 1);
	}
	
	
}
