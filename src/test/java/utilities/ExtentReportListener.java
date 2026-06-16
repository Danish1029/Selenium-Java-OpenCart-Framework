/*
package utilities;

import java.awt.Desktop;
import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.BaseClass;

public class ExtentReportListener
        implements ITestListener {

    private final ExtentReports extent =
            ExtentReportManager
                    .getReportInstance();

    private static final ThreadLocal<ExtentTest>
            extentTest =
            new ThreadLocal<>();

    @Override
    public void onStart(
            ITestContext context) {

        extent.setSystemInfo(
                "Suite Name",
                context.getSuite().getName());

        String os =
                context.getCurrentXmlTest()
                        .getParameter("os");

        if (os != null) {

            extent.setSystemInfo(
                    "Operating System",
                    os);
        }

        String browser =
                context.getCurrentXmlTest()
                        .getParameter("browser");

        if (browser != null) {

            extent.setSystemInfo(
                    "Browser",
                    browser);
        }
    }

    @Override
    public void onTestStart(
            ITestResult result) {

        ExtentTest test =
                extent.createTest(
                        result.getTestClass()
                                .getRealClass()
                                .getSimpleName()
                                + " :: "
                                + result.getMethod()
                                        .getMethodName());

        test.assignCategory(
                result.getMethod()
                        .getGroups());

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        extentTest.get().log(
                Status.PASS,
                "Test Passed");
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        extentTest.get().log(
                Status.FAIL,
                "Test Failed");

        extentTest.get().fail(
                result.getThrowable());

        try {

            BaseClass testClass =
                    (BaseClass)
                            result.getInstance();

            String screenshotPath =
                    testClass.captureScreen(
                            result.getTestClass()
                                    .getRealClass()
                                    .getSimpleName()
                                    + "_"
                                    + result.getMethod()
                                            .getMethodName());

            extentTest.get()
                    .addScreenCaptureFromPath(
                            screenshotPath);

            AllureAttachmentManager
                    .attachScreenshot(
                            testClass
                                    .getScreenshotBytes());

        } catch (Exception e) {

            extentTest.get()
                    .warning(
                            "Unable to capture screenshot : "
                                    + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        extentTest.get().log(
                Status.SKIP,
                "Test Skipped");

        if (result.getThrowable() != null) {

            extentTest.get()
                    .skip(
                            result.getThrowable());
        }
    }

    @Override
    public void onFinish(
            ITestContext context) {

        extent.flush();

        extentTest.remove();

        try {

            File reportDir =
                    new File(
                            System.getProperty("user.dir")
                                    + File.separator
                                    + "reports");

            if (Desktop.isDesktopSupported()
                    && reportDir.exists()) {

                File latestReport =
                        reportDir.listFiles(
                                (dir, name) ->
                                        name.endsWith(".html"))
                                [reportDir.listFiles(
                                        (dir, name) ->
                                                name.endsWith(".html"))
                                        .length - 1];

                Desktop.getDesktop()
                        .browse(
                                latestReport.toURI());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}


*/