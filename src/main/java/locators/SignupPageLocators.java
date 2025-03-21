package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPageLocators {
    @FindBy(css = "#sign-username")
    public WebElement usernameField;
    @FindBy(css = "#sign-password")
    public WebElement passwordField;
    @FindBy(css = "#signInModal .btn-primary")
    public WebElement signupBtn;
}
