package testselenium.SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testselenium.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{

	WebDriver driver;
	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement> orderProducts;
	
	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderProducts.stream().anyMatch(product1->product1.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
