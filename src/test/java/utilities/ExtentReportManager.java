package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;

    private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    private String reportName;

    @Override
    public void onStart(ITestContext context) {

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                        .format(new Date());

        reportName =
                "AutomationReport_"
                        + timeStamp
                        + ".html";

        String reportPath =
                System.getProperty("user.dir")
                        + File.separator
                        + "reports"
                        + File.separator
                        + reportName;

        sparkReporter =
                new ExtentSparkReporter(reportPath);

        sparkReporter.config()
                .setDocumentTitle(
                        "OpenCart Automation Report");

        sparkReporter.config()
                .setReportName(
                        "OpenCart Functional Test Execution");

        sparkReporter.config()
                .setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System Information

        extent.setSystemInfo(
                "Application",
                "OpenCart");

        extent.setSystemInfo(
                "Framework",
                "Selenium + TestNG");

        extent.setSystemInfo(
                "Automation Language",
                "Java");

        extent.setSystemInfo(
                "Environment",
                "QA");

        extent.setSystemInfo(
                "Tester",
                System.getProperty("user.name"));

        extent.setSystemInfo(
                "Execution Time",
                new SimpleDateFormat(
                        "dd-MMM-yyyy HH:mm:ss")
                        .format(new Date()));

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

        List<String> groups =
                context.getCurrentXmlTest()
                        .getIncludedGroups();

        if (!groups.isEmpty()) {

            extent.setSystemInfo(
                    "Executed Groups",
                    groups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                extent.createTest(
                        result.getTestClass().getName()
                                + " :: "
                                + result.getMethod()
                                .getMethodName());

        test.assignCategory(
                result.getMethod().getGroups());

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(
                Status.PASS,
                result.getMethod()
                        .getMethodName()
                        + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().log(
                Status.FAIL,
                result.getMethod()
                        .getMethodName()
                        + " FAILED");

        extentTest.get().fail(
                result.getThrowable());

        try {

            BaseClass testClass =
                    (BaseClass) result.getInstance();

            String screenshotPath =
                    testClass.captureScreen(
                            result.getMethod()
                                    .getMethodName());

            extentTest.get()
                    .addScreenCaptureFromPath(
                            screenshotPath);

        } catch (Exception e) {

            extentTest.get().warning(
                    "Unable to capture screenshot : "
                            + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().log(
                Status.SKIP,
                result.getMethod()
                        .getMethodName()
                        + " SKIPPED");

        if (result.getThrowable() != null) {

            extentTest.get().skip(
                    result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
        

        String reportPath =
                System.getProperty("user.dir")
                        + File.separator
                        + "reports"
                        + File.separator
                        + reportName;

        File reportFile =
                new File(reportPath);

        try {

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop()
                        .browse(reportFile.toURI());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
	/*  // Create the email message 
	  ImageHtmlEmail email = new ImageHtmlEmail();
	  email.setDataSourceResolver(new DataSourceUrlResolver(url));
	  email.setHostName("smtp.googlemail.com"); 
	  email.setSmtpPort(465);
	  email.setAuthenticator(new DefaultAuthenticator("danishdrive797@gmail.com","password")); 
	  email.setSSLOnConnect(true);
	  email.setFrom("danishdrive1029@gmail.com"); //Sender
	  email.setSubject("Test Results");
	  email.setMsg("Please find Attached Report....");
	  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
	  email.attach(url, "extent report", "please check report..."); 
	  email.send(); // send the email 
	  }
	  catch(Exception e) 
	  { 
		  e.printStackTrace(); 
		  }
	 */ 
	 

