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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
	
	public WebDriver driver;
	JavascriptExecutor executor;
	Actions action;
	WebDriverWait wait;
	
	@SuppressWarnings("deprecation")
	public CartPage(WebDriver driver){
		this.wait = new WebDriverWait(driver, 10);
		this.driver = driver;
		this.action = new Actions(driver);
		this.executor = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id=\"post-7\"]/div/div[2]/form/table")
	public WebElement productTable;
	
	@FindBy(css = "a[href='https://practice.automationbro.com/checkout/']")
	public WebElement checkOutBtn;
	
	@FindBy(xpath = "//*[@class=\"woocommerce-message\"]")
	public WebElement alertDelete;
	
	public void checkTableProducts(Set<String> productList) {
		List<WebElement> tableRows = productTable.findElements(By.tagName("tr"));
		int rowsNum = tableRows.size();
	
		for(int i = 1; i < rowsNum-1; i++) {
			List<WebElement> tds = tableRows.get(i).findElements(By.tagName("td"));
			Assert.assertTrue(productList.contains(tds.get(2).getText()));
		}
	}
	
	public void deleteProduct(String toDelete) throws InterruptedException {
		List<WebElement> tableRows = productTable.findElements(By.tagName("tr"));
		int rowsNum = tableRows.size();
		for(int i = 1; i < rowsNum-1; i++) {
			List<WebElement> tds = tableRows.get(i).findElements(By.tagName("td"));
			if(toDelete == tds.get(2).getText()) {
				WebElement element = tds.get(0).findElement(By.cssSelector("a[class='remove']"));
				wait.until(ExpectedConditions.elementToBeClickable(element));
				action.moveToElement(element).build().perform();
				action.click().perform();
			}
		}
	}
	
	
	public void assertDelete(String toDelete) {
		wait.until(ExpectedConditions.visibilityOf(alertDelete));
		Assert.assertEquals(alertDelete.getText(), "“" + toDelete + "” removed. Undo?");
	}
	
	public void clickCheckOutBtn() {
		checkOutBtn.click();
	}

}
