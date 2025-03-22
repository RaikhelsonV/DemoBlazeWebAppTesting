package steps;

import config.DriverManager;

import config.SetupDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeSteps extends SetupDriver {
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    Map<String, Product> productMap;

    @Before
    public void init() throws IOException {
        setUp();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productMap = new HashMap<>();
    }

    @Given("The user is on the homepage of Demoblaze")
    public void givenUserIsOnHomePage() {
        Assert.assertTrue(homePage.isProductListVisible(), "Product list is not visible.");
        List<WebElement> allProductsOnHomePage = homePage.getAllProductsOnHomePage();
        Assert.assertEquals(allProductsOnHomePage.size(), 9, "The number of elements is not equal to 9");
    }

    @When("The user selects a product with title {string}")
    public void whenUserSelectsProductWithTitle(String productTitle) {
        List<WebElement> allProductsOnHomePage = homePage.getAllProductsOnHomePage();
        boolean productFound = false;

        for (WebElement element : allProductsOnHomePage) {
            String title = homePage.getElementByTagInside(element, "h4").getText();

            if (title.equals(productTitle)) {
                homePage.waitUntilElementIsClickable(element);
                storeProductInMap(title, homePage.getProductPrice(element));
                element.click();
                productFound = true;
                break;
            }
        }
        Assert.assertTrue(productFound, "Product with title " + productTitle + " was not found.");
    }

    private void storeProductInMap(String title, int price) {
        productMap.put(title, new Product(title, price));
    }

    @Then("The product with title {string} should be displayed")
    public void thenProductShouldBeDisplayed(String productTitle) {
        Assert.assertEquals(homePage.getProductTitle(), productTitle, "Product title does not match.");
    }

    @When("The user adds the product to the cart")
    public void whenUserAddsProductToCart() throws InterruptedException {
        homePage.clickAddToCart();
        Assert.assertTrue(homePage.alertText().contains("Product added"), "Expected alert message not found.");
        homePage.alertOk();
    }

    @Then("The cart should contain 1 item")
    public void thenCartShouldContainOneItem() {
        homePage.clickCart();
        List<WebElement> allProductsOnCartPage = cartPage.getAllProductsOnCartPage();
        Assert.assertFalse(allProductsOnCartPage.isEmpty(), "Cart is empty!");

        int totalPrice = 0;

        for (WebElement element : allProductsOnCartPage) {
            List<WebElement> tdElements = cartPage.getElementsByTagInside(element, "td");
            if (tdElements == null || tdElements.isEmpty()) {
                System.out.println("No <td> elements found inside the product element.");
                continue;
            }

            String title = tdElements.get(1).getText();
            int price = extractPrice(tdElements.get(2).getText(), title);
            WebElement deleteButton = tdElements.get(3).findElement(By.xpath(".//a"));

            totalPrice += price;
            Product foundProduct = productMap.get(title);
            validateProductInfo(foundProduct, price);
            foundProduct.setDeleteButton(deleteButton);
        }
        validateTotalPrice(totalPrice);
    }

    private int extractPrice(String priceText, String title) {
        int price = 0;
        try {
            price = Integer.parseInt(priceText.trim());
        } catch (NumberFormatException e) {
            Assert.fail("Failed to parse price for product in cart: " + title);
        }
        return price;
    }

    private void validateProductInfo(Product foundProduct, int price) {
        Assert.assertNotNull(foundProduct, "Product not found in stored map.");
        Assert.assertEquals(price, foundProduct.getPrice(), "Product price mismatch.");
    }

    private void validateTotalPrice(int totalPrice) {
        int displayedTotalPrice = Integer.parseInt(cartPage.getTotalPrice().trim());
        Assert.assertEquals(totalPrice, displayedTotalPrice, "Total price mismatch in cart.");
    }

    @Given("The user has added {string} to the cart")
    public void givenUserHasAddedProductToCart(String productTitle) throws InterruptedException {
        whenUserSelectsProductWithTitle(productTitle);
        whenUserAddsProductToCart();
        thenCartShouldContainOneItem();
    }

    @When("The user removes the {string} from the cart")
    public void whenUserRemovesProductFromCart(String productTitle) throws InterruptedException {
        Product foundProduct = productMap.get(productTitle);
        cartPage.click(foundProduct.getDeleteButton());
        cartPage.waitUntilElementIsVisibleOff(foundProduct.getDeleteButton());
    }

    @Then("The cart should be empty")
    public void the_cart_is_empty() {
        List<WebElement> allProductsOnCartPage = cartPage.getAllProductsOnCartPage();
        Assert.assertTrue(allProductsOnCartPage.isEmpty(), "The cart is not empty.");
    }

    @When("The user clicks on Place Order")
    public void whenUserClicksPlaceOrder() {
        cartPage.clickPlaceOrder();
        Assert.assertTrue(checkoutPage.isOrderFormVisible(), "Order form is not visible.");
    }

    @When("The user fills out the order form with name {string}, country {string}, city {string}, credit card {string}, month {string}, year {string}")
    public void whenUserFillsOutOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        checkoutPage.fillOrderForm(name, country, city, creditCard, month, year);
        checkoutPage.clickPurchase();
    }

    @Then("The user should see a confirmation message with order details {string} and {string}")
    public void thenUserShouldSeeConfirmationMessage(String name, String creditCard) {
        Assert.assertTrue(checkoutPage.isThanksFormVisible(), "Thanks form is not visible.");
        Assert.assertTrue(checkoutPage.getPurchaseData().contains(name) && checkoutPage.getPurchaseData().contains(creditCard));
        checkoutPage.clickOk();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}