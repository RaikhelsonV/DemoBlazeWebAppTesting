package config;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetupDriver {
    protected WebDriver driver;
    private static final String PROPERTIES_FILE_PATH = "src/test/resources/config.properties";
    private String baseUrl;

    @BeforeClass
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PROPERTIES_FILE_PATH));
        baseUrl = properties.getProperty("base_url");

        driver = DriverManager.getDriver();
        driver.get("https://www.demoblaze.com");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
