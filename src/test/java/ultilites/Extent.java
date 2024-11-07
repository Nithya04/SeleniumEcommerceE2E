package ultilites;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent implements ITestListener {



		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;

		String repName;

		@Override
		public void onStart(ITestContext testContext) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report-" + timeStamp + ".html";

		    // Ensure the reports directory exists
	        String reportPath = System.getProperty("user.dir") + "\\reports\\";
	        File reportDir = new File(reportPath);
	        if (!reportDir.exists()) {
	            reportDir.mkdirs();
	            System.out.println("Created reports directory at: " + reportPath);
	        }

			sparkReporter.config().setDocumentTitle("Selenium Java EComeerce");
			sparkReporter.config().setReportName("Open Cart Application");
			sparkReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Open Cart Application");
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Enviroment", "QA");
			extent.setSystemInfo("user", "Nithya");

		}

		@Override
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.PASS, "Test Passed");

		}

		@Override
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());

		}

		@Override
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.createNode(result.getName());
			test.log(Status.SKIP, "Test Skipped");
			test.log(Status.SKIP, result.getThrowable().getMessage());

		}

		@Override
		public void onFinish(ITestContext testContext) {
			extent.flush();

		}

	}


