package pages;

import locators.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {
    private HomePageLocators locator;

    public HomePage(WebDriver driver) {
        super(driver);
        locator = new HomePageLocators();
        PageFactory.initElements(driver, locator);
    }

    public void clickLogin() {
        click(locator.loginMenuLink);
    }

    public void clickSignUp() {
        click(locator.signupMenuLink);
    }

    public void clickCart() {
        click(locator.cartMenuLink);
    }

    public String getWelcomeMessage() {
        return getText(locator.welcomeMessage);
    }

    public List<WebElement> getAllProductsOnHomePage() {
        return getElementsByClassName(locator.productsList, "col-lg-4");
    }

    public boolean isProductListVisible() {
        return isDisplayed(locator.productsList);
    }

    public String getProductTitle() {
        return getText(locator.productTitle);
    }

    public void clickAddToCart() throws InterruptedException {
        Thread.sleep(10000);
        click(locator.addToCartBtn);
    }

}
