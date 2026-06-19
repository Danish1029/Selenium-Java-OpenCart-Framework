package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;

	public void clickMyAccount() {

	    System.out.println("========== HOME PAGE ==========");
	    System.out.println(driver.getCurrentUrl());
	    System.out.println(driver.getTitle());

	    WebElement myAccount =
	            wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//span[normalize-space()='My Account']")));
	    System.out.println("Displayed = " + myAccount.isDisplayed());
	    System.out.println("Enabled   = " + myAccount.isEnabled());
	    System.out.println("Found My Account");


	    ((JavascriptExecutor)driver)
	            .executeScript("arguments[0].click();", myAccount);

	    System.out.println("Clicked My Account");
	}

@FindBy(xpath="//a[normalize-space()='Register']") 
WebElement lnkRegister;

@FindBy(xpath="//a[normalize-space()='Login']") 
WebElement linkLogin;


// //ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']





public void clickRegister()
{
	lnkRegister.click();
}
	
public void clickLogin() {
	    click(linkLogin);
	
}

}