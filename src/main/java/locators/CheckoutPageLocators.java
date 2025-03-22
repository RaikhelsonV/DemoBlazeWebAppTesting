package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageLocators {
    @FindBy(css = "#orderModalLabel")
    public WebElement orderFormTitle;
    @FindBy(css = "#name")
    public WebElement nameField;
    @FindBy(css = "#country")
    public WebElement countryField;
    @FindBy(css = "#city")
    public WebElement cityField;
    @FindBy(css = "#card")
    public WebElement cardField;
    @FindBy(css = "#month")
    public WebElement monthField;
    @FindBy(css = "#year")
    public WebElement yearField;
    @FindBy(css = "#orderModal .btn-primary")
    public WebElement purchaseBtn;
    @FindBy(css = ".sweet-alert ")
    public WebElement thanksForm;
    @FindBy(css = ".lead")
    public WebElement purchaseData;
    @FindBy(css = ".confirm")
    public WebElement okBtn;
}
