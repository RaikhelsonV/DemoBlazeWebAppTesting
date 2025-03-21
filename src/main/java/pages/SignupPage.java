package pages;

import locators.SignupPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignupPage extends BasePage{
    private SignupPageLocators locator;

    public SignupPage(WebDriver driver) {
        super(driver);
        locator = new SignupPageLocators();
        PageFactory.initElements(driver, locator);
    }

    public void enterUsername(String username) {
        enterText(locator.usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(locator.passwordField, password);
    }

    public void clickSignup() throws InterruptedException {
        click(locator.signupBtn);
    }

}
