package steps;

import config.DriverManager;
import config.SetupDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

public class LoginSteps extends SetupDriver {
    private HomePage homePage;
    private LoginPage loginPage;
    private static final String HOME_PAGE_TITLE = "STORE";

    @Before(order = 1)
    public void init() throws IOException {
        setUp();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Given("User is on Home Page test")
    public void user_is_on_home_page_test() {
        Assert.assertTrue(driver.getTitle().contains(HOME_PAGE_TITLE));
    }

    @When("User navigates to Login Page")
    public void user_navigates_to_login_page() {
        homePage.clickLogin();
    }

    @When("User enters valid credentials {string} and {string}")
    public void user_enters_valid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("User is logged in successfully with message {string}")
    public void user_is_logged_in_successfully(String welcomeMessage) {
        Assert.assertTrue(homePage.getWelcomeMessage().contains(welcomeMessage));
    }

    @After(order = 1)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}