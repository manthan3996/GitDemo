package testselenium.SeleniumTest;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testselenium.SeleniumTest.pageobjects.CartPage;
import testselenium.SeleniumTest.pageobjects.CheckoutPage;
import testselenium.SeleniumTest.pageobjects.ConfirmationPage;
import testselenium.SeleniumTest.pageobjects.LandingPage;
import testselenium.SeleniumTest.pageobjects.ProductCatalogue;
import testselenium.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		landingPage.loginApplication("msacademyr@yopmail.com", "Password@1234567");
		String errorMessage = landingPage.getLoginErrorMessage();		
		Assert.assertEquals("Incorrect email or password.", errorMessage);
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		
		
		landingPage.loginApplication("msacademy1@yopmail.com", "Password@1234567");
		
		
		//List<WebElement> productsList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.clickDashboardCart();
		//System.out.println(productCatalogue.getPageTitle());
		CartPage cartPage = new CartPage(driver);
		Assert.assertFalse(cartPage.confirmProductAddedIsAvailable("ADDIDAS ORIGINAL"));
	}


}
