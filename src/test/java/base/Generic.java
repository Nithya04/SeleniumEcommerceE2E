package base;
import  testCases.TestData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Generic {
    public static WebDriver driver;
    public Properties prop;
    protected Logger logger;

    @BeforeClass(groups= {"sanity","Regression"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) throws IOException {
        // Initialize logger
        logger = LogManager.getLogger(this.getClass());
        logger.info("Logger initialized");

        // Load config.properties
        prop = new Properties();
        try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
            prop.load(file);
            logger.info("Properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load config.properties file", e);
            throw e;
        }

        // Initialize the browser
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Driver setup
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }

    @AfterClass(groups= {"sanity","Regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed successfully");
        }
    }

    // Utility methods
    public String randomData() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    public String randomNumeric() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(6) + RandomStringUtils.randomNumeric(10);
    }
    
    protected void generateTestData() {
        String email = randomData() + "@example.com";
        String password = randomAlphaNumeric();

        TestData.getInstance().setUserEmail(email);;
        TestData.getInstance().setPassword(password);;

        System.out.println("Generated Email: " + email);
        System.out.println("Generated User ID: " + password);
    }
    
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }

}
