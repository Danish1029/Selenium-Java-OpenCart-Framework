package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	public void clickMyAccount()
	{
	    WebDriverWait wait =
	            new WebDriverWait(driver,
	                    Duration.ofSeconds(20));

	    WebElement myAccount =
	            wait.until(
	                    ExpectedConditions
	                            .visibilityOfElementLocated(
	                                    By.xpath(
	                                            "//span[normalize-space()='My Account']")));

	    myAccount.click();
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