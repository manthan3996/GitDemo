package testselenium.SeleniumTest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testselenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword"))
	//driver.findElement(By.id("login"))
	//PAge Factory - REduce syntax for WebElements.
	//Page Objects file should not have any data.. It should focus on logic 
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrorMessageToaster;
	//.ng-tns-c4-18.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	
	
	public void loginApplication(String object,String object2) {
		userEmail.sendKeys(object);
		userPassword.sendKeys(object2);
		submit.click();
	}
	//div[@aria-label='Incorrect email or password.']
	
	public String getLoginErrorMessage() {
		return loginErrorMessageToaster.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
