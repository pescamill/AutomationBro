package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Steps.PracticeAutomationSteps;

public class TestCases {
		
	public WebDriver driver;
	PracticeAutomationSteps paSteps;
	
	public final String driverPath = "src/main/resources/chromedriver";
	public final String baseUrl = "https://practice.automationbro.com/";
	public final String keyword = "converse";
	
	@BeforeTest
	private void setup() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		paSteps = new PracticeAutomationSteps(driver);
	}
	
	@BeforeMethod
	private void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", driverPath);
	}
	
	// Test Keyword 'Converse'
	@Test
	private void testShopSearchBox() {
		paSteps.shopSearch(keyword);
	}
	
	// Test if list is ordered from Low to High
	@Test
	private void testSortLtoH() {
		paSteps.sortLowToHigh();
	}

	// Test if list is ordered from High to Low
	@Test
	private void testSortHtoL() {
		paSteps.sortHighToLow();
	}
	
	// Test if there are 9 products in Shop per page
	@Test
	private void testLimitProducts() {
		paSteps.countProducts();
	}
	
	//Test if product last seen is in recent viewed Section after clicking shop link
	@Test
	private void checkIfLastSeenA() {
		paSteps.checkIfRecentlySeenA(0);
	}
	
	@Test
	//Test if product last seen is in recent viewed Section after clicking dropdown sort
	private void checkIfLastSeenB() {
		paSteps.checkIfRecentlySeenB(0);
	}
	
	// Check if all contact info appears correctly on Contact Link
	@Test
	private void checkIfContactInfo() {
		paSteps.checkContactInfo();
	}
	
	//Assert if Call Us Button makes a pop up appear with phone number
	@Test
	private void checkCallUsButton() {
		paSteps.checkCallUsButton();
	}
	
	
	// Assert you can check out an item
	@Test
	private void goToCheckOut() throws InterruptedException {
		paSteps.buyAndCheckout(2);
	}
	
	// Assert you can delete items from cart
	@Test
	private void deleteFromCartTest() throws InterruptedException {
		paSteps.buyAndDelete(2);
	}
	

}   
