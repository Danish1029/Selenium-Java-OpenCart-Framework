package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountRegistrationPage extends BasePage {

    // Constructor
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }
	
// 2. Element Locators (@FindBy)

@FindBy(xpath="//input[@id='input-firstname']") 
WebElement txtFirstname;

@FindBy(xpath="//input[@id='input-lastname']") 
WebElement txtLasttname;

@FindBy(xpath="//input[@id='input-email']") 
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']") 
WebElement txtTelephone;

@FindBy(xpath="//input[@id='input-password']") 
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']") 
WebElement txtConfirmPassword;

@FindBy(xpath="//input[@name='agree']") 
WebElement chkdPolicy;



@FindBy(xpath = "//input[@value='Continue']")
WebElement btnContinue;



@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

//input[@id='input-firstname']

//input[@id='input-lastname']

//input[@id='input-email']
//input[@id='input-password']

//input[@name='agree']

//button[normalize-space()='Continue']

// ...........Action Methods..............................

public void setFirstName(String firstName) {
    type(txtFirstname, firstName);
}

public void setLastName(String lastName) {
    type(txtLasttname, lastName);
}

public void setEmail(String email) {
    type(txtEmail, email);
}
public void setTelephone(String tel) {
	txtTelephone.sendKeys(tel);
}

public void setPassword(String password) {
    type(txtPassword, password);
}
public void setConfirmPassword(String pwd) {
	txtConfirmPassword.sendKeys(pwd);
}

public void acceptPrivacyPolicy() {
    click(chkdPolicy);
}

public void clickContinue() {
    click(btnContinue);
}

public String getConfirmationMessage() {
    return getText(msgConfirmation);
}
	//sol2 
	//btnContinue.submit();
	
	//sol3
	//Actions act=new Actions(driver);
	//act.moveToElement(btnContinue).click().perform();
				
	//sol4
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btnContinue);
	
	//Sol 5
	//btnContinue.sendKeys(Keys.RETURN);
	
	//Sol6  
	//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	

public String getConfirmationMsg() {
	try {
		// 🟢 Optional Improvement: Wait for the confirmation success header to appear too
		wait.until(ExpectedConditions.visibilityOf(msgConfirmation));
		return (msgConfirmation.getText());
	} catch (Exception e) {
		return (e.getMessage());
	}
}
}