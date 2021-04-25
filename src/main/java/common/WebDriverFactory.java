package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver initWebDriver(WebDriverType webDriverType, String driverPath) {
        if (Objects.nonNull(driverPath))
            System.setProperty(webDriverType.driverName(), driverPath);
        WebDriver driver;
        switch (webDriverType) {
            case FIREFOX:
                driver = initFireFoxDriver(false, null);
                break;
            case EDGE:
                driver = initEdgeDriver(false, null);
                break;
            case CHROME:
                driver = initChromeDriver(false, null);
                break;
            default:
                throw new WebDriverException("Invalid driver selected.");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static WebDriver initRemoteChromeDriver(String driverPath, String gridUrl) throws MalformedURLException {
        if (Objects.nonNull(driverPath)) {
            System.setProperty(WebDriverType.CHROME.driverName(), driverPath);
        }

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver initRemoteDriver(WebDriverType type, String driverPath, String gridUrl){
        if (driverPath.isEmpty())
            System.setProperty(type.driverName(), driverPath);
        WebDriver driver;
        switch (type){
            case CHROME:
                driver = initChromeDriver(true, gridUrl);
                break;
            case FIREFOX:
                driver = initFireFoxDriver(true, gridUrl);
                break;
            case EDGE:
                driver = initEdgeDriver(true, gridUrl);
                break;
            default:
                throw new WebDriverException("Invalid remote driver selected.");
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initChromeDriver(boolean isRemote, @Nullable String gridUrl){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        WebDriver driver = null;

        if (isRemote)
            try{
                driver = new RemoteWebDriver(new URL(gridUrl), options);
            }
            catch(MalformedURLException X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }
        else{
            try{
                driver = new ChromeDriver(options);
            }
            catch(Exception X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }
        }

        return driver;
    }

    private static WebDriver initFireFoxDriver(boolean isRemote, @Nullable String gridUrl){
        FirefoxOptions ffOpts = new FirefoxOptions();
        WebDriver driver = null;
        if (isRemote){
            try{
                driver = new RemoteWebDriver(new URL(gridUrl), ffOpts);
            }
            catch(MalformedURLException X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }
        }
        else{
            try{
                driver = new FirefoxDriver(ffOpts);
            }
            catch(Exception X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }

        }
        return driver;
    }

    private static WebDriver initEdgeDriver(boolean isRemote, @Nullable String gridUrl){
        EdgeOptions eOptions = new EdgeOptions();
        WebDriver driver = null;
        if (isRemote){
            try{
                driver = new RemoteWebDriver(new URL(gridUrl), eOptions);
            }
            catch(MalformedURLException X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }
        }
        else{
            try{
                driver = new EdgeDriver(eOptions);
            }
            catch(Exception X){
                Assert.fail(String.format("Initializing Remote Driver failed. Error: %s", X.getMessage()));
            }
        }
        return driver;
    }

    public enum WebDriverType {
        CHROME("webdriver.chrome.driver"),
        EDGE("webdriver.edge.driver"),
        FIREFOX("webdriver.gecko.driver");

        private String driverString;

        WebDriverType(String driverString) {
            this.driverString = driverString;
        }

        public String driverName() {
            return this.driverString;
        }
    }
}