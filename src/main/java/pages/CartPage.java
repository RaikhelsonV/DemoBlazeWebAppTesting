package pages;

import locators.CartPageLocators;
import locators.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage{
    public CartPageLocators locator;

    public CartPage(WebDriver driver) {
        super(driver);
        locator = new CartPageLocators();
        PageFactory.initElements(driver, locator);
    }
    public List<WebElement> getAllProductsOnCartPage() {
        return getElementsByTagInside(locator.productsList, "tr");
    }

    public void clickPlaceOrder(){
        click(locator.placeOrderBtn);
    }

    public String getTotalPrice(){
        return  getText(locator.totalPrice);
    }
}
