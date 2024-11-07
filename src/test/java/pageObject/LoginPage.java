package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	//locators
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	
	@FindBy(xpath="//h1[text()='Account Logout']")
	WebElement labelLogOut;
	//interactions
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		btnLogin.click();
	}
	
	public String getLogOutMsg() {
		try {
			return(labelLogOut.getText());
			
		} catch(Exception e) {
			return(e.getMessage());
		}
	}
}
