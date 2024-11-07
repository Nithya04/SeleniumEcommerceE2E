package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Generic;
import pageObject.AccountRegistrationPage;
import pageObject.BasePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import ultilites.DataProviders;

public class LoginTC_dataDriven extends Generic{

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
		 System.out.println("Page objects initialized successfully.");
	}
	@Test(dataProvider = "LoginData1",dataProviderClass = DataProviders.class)
	public void validate_loginddt(String email, String password, String res) {
		  System.out.println("validate_loginddt method called with email: " + email + ", password: " + password + ", expected result: " + res);
		try {
			 // Log data for confirmation
            System.out.println("Test started with email: " + email + ", password: " + password + ", expected result: " + res);
            logger.info("Starting login validation test...");
		//home page
			System.out.println("Navigating to My Account on the HomePage...");
			System.out.println("in Home Page");
		hp.clickMyAccount();
		System.out.println("clicked account");
		hp.clickLogin();
		System.out.println("clicked login");
		//login page
		lp.enterEmail(email);
		lp.enterPassword(password);
		logger.info("Credentials entered. Logging in...");
		lp.clickLoginBtn();
		boolean target = myp.isMyAccountPageExists();
		Assert.assertTrue(target, "Login Successful");
		logger.info("Logout00...");
		
		
		///VALIDATION-1
		if(res.equalsIgnoreCase("valid")) {
			if (target==true) {
				;
				logger.info("Login successful for valid credentials.");
				hp.clickMyAccount();
				myp.clickLogoutBtn();
				// Validate confirmation message
				String confirmLogOutMsg = lp.getLogOutMsg();
				Assert.assertTrue(true);
				Assert.assertEquals(confirmLogOutMsg, "LogOut Successful");
				logger.info("Account successfully logged out!");
			}
			else {
				Assert.fail("Expected valid login, but login failed.");
			}
		}
		
		//VALIDATION 2
		else if(res.equalsIgnoreCase("invalid")) {
			if (target==true) {
				;
				 logger.warn("Login should not have been successful for invalid credentials.");
				hp.clickMyAccount();
				myp.clickLogoutBtn();
				
				Assert.fail("Expected invalid login, but login was successful.");
				
			}
			else {
				 logger.info("Invalid login attempt correctly failed.");
                 Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			 logger.error("Exception encountered during login test: ", e);
			 Assert.fail("Login test failed due to an exception.");
		}
		
		}
	
	
	
}
