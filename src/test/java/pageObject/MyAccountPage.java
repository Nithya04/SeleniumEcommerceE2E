package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//h2[text()='My Account']")
	WebElement labelMyAccount;
	
	@FindBy(xpath="(//a[text()='Logout'])[1]")
	WebElement btnLogOut;
	
	public boolean isMyAccountPageExists() {
		try {
			return (labelMyAccount.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogoutBtn() {
		btnLogOut.click();
	}
	
}
