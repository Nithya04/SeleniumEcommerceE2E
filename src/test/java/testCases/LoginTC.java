package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Generic;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testCases.TestData;

public class LoginTC extends Generic {

	HomePage hp;
	AccountRegistrationPage agp;
	LoginPage lp;
	MyAccountPage myp;

	@BeforeClass
	public void setUpPages() {
		// Initialize page objects
		hp = new HomePage(driver);
		agp = new AccountRegistrationPage(driver);
		lp = new LoginPage(driver);
		myp = new MyAccountPage(driver);
	}

	@BeforeMethod
	public void navigateToHome() {
		// Ensure the browser is always on the homepage before each test
		driver.get(prop.getProperty("url"));
	}

	@Test(priority = 0,groups="sanity")
	public void validate_login() {
		try {
			logger.info("Starting validate_login test ************");

			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Login page opened.");

			// Get credentials from properties file
			String email = prop.getProperty("email");
			String password = prop.getProperty("password");

			// Perform login
			lp.enterEmail(email);
			lp.enterPassword(password);
			logger.info("Credentials entered. Logging in...");
			lp.clickLoginBtn();

			// Validate login success
			boolean target = myp.isMyAccountPageExists();
			Assert.assertTrue(target, "Login Successful");
			logger.info("Logout00...");
			hp.clickMyAccount();
			myp.clickLogoutBtn();
			// Validate confirmation message
			String confirmLogOutMsg = lp.getLogOutMsg();
			Assert.assertEquals(confirmLogOutMsg, "LogOut Successful");
			logger.info("Account successfully logged out!");

		} catch (Exception e) {
			logger.error("Exception during validate_login: ", e);
			Assert.fail("validate_login test failed due to an exception.");
		}
	}

	@Test(priority = 1,groups={"sanity","Regression"})
	public void validateLogin_Singleton() {
		try {
			logger.info("Starting validateLogin_Singleton test ************");

			// Generate and set test data in the Singleton
			String username = TestData.getInstance().getUserEmail();
			String password = TestData.getInstance().getPassword();

			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Login page opened.");

			// Use generated credentials from Singleton
			lp.enterEmail(username);
			lp.enterPassword(password);
			logger.info("Singleton credentials entered. Logging in...");
			lp.clickLoginBtn();
			myp.clickLogoutBtn();
			// Validate confirmation message
			String confirmLogOutMsg = lp.getLogOutMsg();
			Assert.assertEquals(confirmLogOutMsg, "LogOut Successful");
			logger.info("Account successfully logged out!");

			// Validate login success
			boolean target = myp.isMyAccountPageExists();
			Assert.assertTrue(target, "Login Successful");
			hp.clickMyAccount();
		} catch (Exception e) {
			logger.error("Exception during validateLogin_Singleton: ", e);
			Assert.fail("validateLogin_Singleton test failed due to an exception.");
		}
		
		//DATA DRIVEN TEST CASE
		
		
	}
}
