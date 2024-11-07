package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Generic;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class AccountRegistrationTC extends Generic {
    private static final Logger logger = LogManager.getLogger(AccountRegistrationTC.class);

    @Test(groups={"sanity","Regression"})
    public void verify_account_registration() {
        try {
        	 generateTestData();
             String username = TestData.getInstance().getUserEmail();
             String password = TestData.getInstance().getPassword();
            // Navigate to HomePage and click MyAccount -> Register
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickRegister();
            logger.info("Navigated to the Register page.");

            // Initialize AccountRegistrationPage object and enter registration details
            AccountRegistrationPage agp = new AccountRegistrationPage(driver);
            logger.info("Entering registration details...");

            // Fill in registration form fields with random data
            agp.enterFirstName("FN" + randomData().toUpperCase());
            agp.enterLastName("LN" + randomData());
            
            agp.enterEmail(username);
     agp.enterPhoneNo(randomNumeric());
//            String pass = randomAlphaNumeric();
            agp.enterPassword(password);
            agp.enterConfirmPassword(password);

            logger.info("Registration details filled with email: {} and password: {}", username, password);

            // Agree to terms and submit registration
            agp.clickAgree();
            agp.clickContinue();

            // Validate confirmation message
            String confirmMsg = agp.getConfirmationMsg();
            Assert.assertEquals(confirmMsg, "Your Account Has Been Created!", "Account creation confirmation message did not match.");
            logger.info("Account successfully created!");

        } catch (Exception e) {
            logger.error("An error occurred during the registration process", e);
            Assert.fail("Test failed due to an exception.");
        }
    }
}
