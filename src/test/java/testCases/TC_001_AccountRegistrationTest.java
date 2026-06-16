package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	
	@Feature("Registration Module")

	@Story("Valid User Registration")

	@Severity(
	        SeverityLevel.CRITICAL)

	@Description(
	        "Verify Account Registration using valid credentials")
	@Test(
		    groups = {"Regression","Master"},
		    retryAnalyzer = RetryAnalyzer.class
		)
    public void verify_account_registration() throws IOException {

        logger.info("********** Starting TC_001_AccountRegistrationTest **********");

        try {

            HomePage hp = new HomePage(driver);
            logger.info("Before Click My Account");
            hp.clickMyAccount();
            logger.info("Clicked My Account");

            hp.clickRegister();
            logger.info("Clicked Register");

            AccountRegistrationPage regPage =
                    new AccountRegistrationPage(driver);

            logger.info("Providing customer details");

            regPage.setFirstName(randomString().toUpperCase());
            regPage.setLastName(randomString().toUpperCase());
            regPage.setEmail(randomString() + "@gmail.com");
            regPage.setTelephone(randomNumber());
           
            String password = randomAlphaNumeric();

            regPage.setPassword(password);
         
    		regPage.setConfirmPassword(password);
            regPage.acceptPrivacyPolicy();
            Thread.sleep(3000);

            logger.info("Clicking Continue button");
            logger.info("Before Continue");
            regPage.clickContinue();
            logger.info("After Continue");
            logger.info("Validating confirmation message");

            String confMsg =
                    regPage.getConfirmationMsg();

            Assert.assertEquals(
                    confMsg,
                    "Your Account Has Been Created!",
                    "Registration confirmation message mismatch");

            logger.info("Test Passed");

        }
        catch (Exception e)
        {
        	
        	    String path = captureScreen("AccountRegistration");

        	    logger.error("Screenshot saved at: " + path);

        	    logger.error("Test Failed", e);

        	    Assert.fail(e.getMessage());
        	}
        
        
    
        finally {

            logger.info(
                    "********** Finished TC_001_AccountRegistrationTest **********");

        }
    }
}