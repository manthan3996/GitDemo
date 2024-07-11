package testselenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testselenium.SeleniumTest.pageobjects.CartPage;
import testselenium.SeleniumTest.pageobjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") 
	WebElement dashboardCart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']") 
	WebElement dashboardMyOrders;

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		
		/* Thread.sleep(1000); */
		
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.invisibilityOf(element));
		 
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public CartPage clickDashboardCart(){
		dashboardCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrdersPage clickDashboardMyOrders(){
		dashboardMyOrders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
