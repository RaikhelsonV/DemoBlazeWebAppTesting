package pages;

import locators.CheckoutPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    public CheckoutPageLocators locator;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        locator = new CheckoutPageLocators();
        PageFactory.initElements(driver, locator);
    }

    public String getFormTitle() {
        return getText(locator.orderFormTitle);
    }

    public boolean isOrderFormVisible() {
        return isDisplayed(locator.orderFormTitle);
    }

    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        enterText(locator.nameField, name);
        enterText(locator.countryField, country);
        enterText(locator.cityField, city);
        enterText(locator.cardField, creditCard);
        enterText(locator.monthField, month);
        enterText(locator.yearField, year);
    }

    public void clickPurchase() {
        click(locator.purchaseBtn);
    }

    public boolean isThanksFormVisible() {
        return isDisplayed(locator.thanksForm);
    }

    public String getPurchaseData() {
        return getText(locator.purchaseData);
    }

    public void clickOk() {
        click(locator.okBtn);
    }
}
