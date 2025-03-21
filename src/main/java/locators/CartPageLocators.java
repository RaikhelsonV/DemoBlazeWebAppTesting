package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPageLocators {
    @FindBy(css = "#tbodyid")
    public WebElement productsList;
    @FindBy(css = "#totalp")
    public WebElement totalPrice;
    @FindBy(css = ".btn-success")
    public WebElement placeOrderBtn;
}
