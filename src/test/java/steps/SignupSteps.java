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
import pages.SignupPage;

import java.io.IOException;


public class SignupSteps extends SetupDriver {
    private HomePage homePage;
    private SignupPage signupPage;
    private static final String HOME_PAGE_TITLE = "STORE";

    @Before
    public void init() throws IOException {
        setUp();
        homePage = new HomePage(driver);
        signupPage = new SignupPage(driver);
    }

    @Given("User is on Home Page")
    public void verifyUserIsOnHomePage() {
        Assert.assertTrue(driver.getTitle().contains(HOME_PAGE_TITLE));
    }

    @When("User navigates to Sign Up Page")
    public void navigateToSignUpPage() {
        homePage.clickSignUp();
    }

    @When("User enters a unique username {string} and password {string}")
    public void enterValidCredentials(String username, String password) {
        signupPage.enterUsername(username);
        signupPage.enterPassword(password);
        signupPage.clickSignup();
    }

    @Then("User sees a registration success message {string}")
    public void verifyUserSeesRegistrationSuccessMessage(String signUpMessage) {
        Assert.assertTrue(homePage.alertText().contains(signUpMessage));
        homePage.alertOk();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
