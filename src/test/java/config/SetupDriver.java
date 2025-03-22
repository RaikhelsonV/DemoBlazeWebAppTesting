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
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        }
        baseUrl = properties.getProperty("base_url");

        if (baseUrl == null) {
            throw new IOException("Base URL not found in the properties file.");
        }

        driver = DriverManager.getDriver();
        driver.get(baseUrl);
    }


    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
