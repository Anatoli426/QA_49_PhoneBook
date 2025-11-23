package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.Duration;

public class ApplicationManager {

    public final static Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser = System.getProperty("browser" , "chrome");

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        //driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        switch (browser.toLowerCase()){
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Start test in browser FireFox");
                break;
            case "edge":
                driver = new EdgeDriver();
                logger.info("Start test in browser Edge");
                break;
            case "chrome":
                driver = new ChromeDriver(chromeOptions);
                logger.info("Start test in browser Chrome");
                break;
            default:
                driver = new ChromeDriver();
                logger.info("Start test in browser Chrome");
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}