package testselenium.SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testselenium.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword"))
	//driver.findElement(By.id("login"))
	//PAge Factory - REduce syntax for WebElements.
	//Page Objects file should not have any data.. It should focus on logic 
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;

	@FindBy(id="login")
	WebElement submit;
	
	public Boolean confirmProductAddedIsAvailable(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product1->product1.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void clickCheckOutButton() {
		checkoutButton.click();
	}
}
