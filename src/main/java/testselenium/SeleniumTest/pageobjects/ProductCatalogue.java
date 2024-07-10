package testselenium.SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testselenium.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By toasterBy = By.cssSelector("#toast-container");

	By productNameText = By.cssSelector("b");
	By addToCartBtnBy = By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		//products = driver.findElements(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(productNameText).getText().equals(productName)).findFirst().orElse(null);
		//System.out.println(prod);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prodElement = getProductByName(productName);
		prodElement.findElement(addToCartBtnBy).click();
		waitForElementToAppear(toasterBy);
		waitForElementToDisappear(spinner);
	}
}
