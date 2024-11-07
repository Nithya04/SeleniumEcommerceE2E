package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//locators
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	@FindBy(xpath="//select[@name=\"country_id\"]")
	WebElement countryDropDown;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement txtPhone;
	
	
	//actions
	
	
	public void enterFirstName(String name) {
		txtFirstName.sendKeys(name);
	}
	
	public void enterLastName(String Lname) {
		txtLastName.sendKeys(Lname);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterPhoneNo(String phoneNo) {
		txtPhone.sendKeys(phoneNo);
	}
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmpassword) {
		txtConfirmPassword.sendKeys(confirmpassword);
	}
	
	public void clickAgree() {
		chkAgree.click();
	}
	public void clickContinue() {
		btnContinue.click();
	}
	
	
	  public void selectCountry(String countryName) {
	        Select countrySelect = new Select(countryDropDown);
	        countrySelect.selectByVisibleText(countryName);
	    }
	
	
	public String getConfirmationMsg() {
		try {
			return(msgConfirmation.getText());
			
		} catch(Exception e) {
			return(e.getMessage());
		}
	}
	
}
