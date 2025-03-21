package config;

import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.plugin.event.Status;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements EventListener {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORTS_DIR = "target/reports/";

    static {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = REPORTS_DIR + "ExtentReport_" + timeStamp + ".html";

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Test Execution Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseStarted(TestCaseStarted event) {
        test = extent.createTest(event.getTestCase().getName());
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        if (event.getResult().getStatus() == Status.PASSED) {
            test.pass("Test Passed");
        } else {
            test.fail("Test Failed: " + event.getResult().getError());
        }
        extent.flush();
    }
}
