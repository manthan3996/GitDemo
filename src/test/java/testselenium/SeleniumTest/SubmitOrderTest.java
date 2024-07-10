package testselenium.SeleniumTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import testselenium.SeleniumTest.pageobjects.OrdersPage;
import testselenium.SeleniumTest.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		
		
		landingPage.loginApplication(input.get("email"), input.get("password"));
		
		
		//List<WebElement> productsList = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		productCatalogue.clickDashboardCart();
		//System.out.println(productCatalogue.getPageTitle());
		CartPage cartPage = new CartPage(driver);
		Assert.assertTrue(cartPage.confirmProductAddedIsAvailable(input.get("productName")));
		cartPage.clickCheckOutButton();
		checkoutPage.selectCountry("India");
		checkoutPage.placeOrder();
		String textString = confirmationPage.verifyConfirmationText();
		AssertJUnit.assertTrue(textString.equalsIgnoreCase("Thankyou for the order."));
	}
	
	//To Verify that the product is available in the Orders page.
	
	@Test (dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		landingPage.loginApplication("msacademy1@yopmail.com", "Password@1234567");
		landingPage.clickDashboardMyOrders();
		OrdersPage ordersPage = new OrdersPage(driver);
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "msacademy1@yopmail.com"); map.put("password","Password@1234567");
	 * map.put("productName","ADIDAS ORIGINAL"); return new Object[][] {{map}}; }
	 */

	
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		 
		 List<HashMap<String, String>> map= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\testselenium\\Data\\PurchaseOrder.json");
		 return new Object[][] {{map.get(0)}};  
	}
	
	// Extent Reports
}
