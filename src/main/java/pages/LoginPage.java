package pages;

import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private LoginPageLocators locator;

    public LoginPage(WebDriver driver) {
        super(driver);
        locator = new LoginPageLocators();
        PageFactory.initElements(driver, locator);
    }

    public void enterUsername(String username) {
        enterText(locator.usernameField, username);
    }

    public void enterPassword(String password) {
        enterText(locator.passwordField, password);
    }

    public void clickLogin() {
        click(locator.loginBtn);
    }


}
