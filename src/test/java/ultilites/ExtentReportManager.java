package ultilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import base.Generic;

public class ExtentReportManager extends Generic implements ITestListener {

	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	


	String repName;

	// Initialize the report
	public void onStart(ITestContext testContext) {
		
		System.out.println(System.getProperty("user.dir"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		String timeStamp = df.format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Set system info
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}

	// Log success
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	// Log failure and capture screenshot
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		// Capture screenshot
		try {
			String screenshotPath =captureScreen(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Log skipped test
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	// Flush report
	public void onFinish(ITestContext testContext) {
		extent.flush();
//		//TO open report automatically after eecution
//		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
//	    File extentReport = new File(pathOfExtentReport);
//
//	    try {
//	        Desktop.getDesktop().browse(extentReport.toURI());
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	    //TO SEND EMAIL 
//	    
//	    try {
//	        URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
//
//	        // Create the email message
//	        ImageHtmlEmail email = new ImageHtmlEmail();
//	        email.setDataSourceResolver(new DataSourceUrlResolver(url));
//	        email.setHostName("smtp.googlemail.com");
//	        email.setSmtpPort(465);
//	        email.setAuthenticator(new DefaultAuthenticator("nithya.j493@gmail.com", "Nivaan@2020"));
//	        email.setSSLOnConnect(true);
//	        email.setFrom("nithya.j493@gmail.com"); // Sender's email
//	        email.setSubject("Test Results");
//	        email.setMsg("Please find the attached report...");
//	        email.addTo("jesintha14@gmail.com"); // Recipient's email
//
//	        // Attach the report
//	        email.attach(url, "Extent Report", "Please check the attached report.");
//
//	        email.send(); // Send the email
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
	}
}
