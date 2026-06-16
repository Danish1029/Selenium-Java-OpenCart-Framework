package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC_002_LoginTest extends BaseClass
{
	
	@Feature("Login Module")

	@Story("Valid User Login")

	@Severity(
	        SeverityLevel.CRITICAL)

	@Description(
	        "Verify login using valid credentials")
	@Test(
		    groups = {"Sanity","Master"},
		    retryAnalyzer = RetryAnalyzer.class
		)

	public void verify_account_registration() {

	}
	public void verify_login()
	{
		logger.info("**** Starting TC_002_LoginTest  ****");
		logger.debug("capturing application debug logs....");
		try
		{
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link on the home page..");
		hp.clickLogin(); //Login link under MyAccount
		logger.info("clicked on login link under myaccount..");
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		logger.info("Entering valid email and password..");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin(); //Login button
		logger.info("clicked on ligin button..");
		
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
				
		boolean targetPage=macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true,"Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC_002_LoginTest  ****");
	}
}
