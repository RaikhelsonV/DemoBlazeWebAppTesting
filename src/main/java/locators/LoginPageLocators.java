package locators;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {
    @FindBy(css = "#loginusername")
    public WebElement usernameField;
    @FindBy(css = "#loginpassword")
    public WebElement passwordField;
    @FindBy(css = "#logInModal .btn-primary")
    public WebElement loginBtn;
}
