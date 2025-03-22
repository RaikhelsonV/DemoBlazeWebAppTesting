package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {
    @FindBy(css = "#login2")
    public WebElement loginMenuLink;
    @FindBy(css = "#signin2")
    public WebElement signupMenuLink;
    @FindBy(css = "#cartur")
    public WebElement cartMenuLink;
    @FindBy(css = "#nameofuser")
    public WebElement welcomeMessage;
    @FindBy(css = "#navbarExample .active .nav-link")
    public WebElement homeMenuLink;
    @FindBy(css = "#tbodyid")
    public WebElement productsList;
    @FindBy(css = "#tbodyid .name")
    public WebElement productTitle;
    @FindBy(css = ".btn-success")
    public WebElement addToCartBtn;
}
