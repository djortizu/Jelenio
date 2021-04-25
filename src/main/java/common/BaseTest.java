package common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.net.MalformedURLException;
import java.util.InputMismatchException;
import static common.WebDriverFactory.*;
import static common.WebDriverManager.*;

public class BaseTest {

    public void initializeTest(String baseUrl, String browserName, String useRemoteWebDriver, String hostOs){
        boolean useRemote = useRemoteWebDriver.equalsIgnoreCase("true");
        String driverPath = setDriverPath(selectDriverPath(hostOs), browserName);
        WebDriverType driverType = setDriverType(browserName);
        setDriver(startDriver(driverType, driverPath, useRemote));
        getDriver().navigate().to(baseUrl);

    }

    private WebDriver startDriver(WebDriverType type, String driverPath, boolean useRemoteDriver){
        WebDriver driver = null;
        try{
            if (useRemoteDriver)
                driver = initRemoteDriver(type, driverPath, ConfigReader.gridUrl);
            else
                driver = initWebDriver(type, driverPath);
        }
        catch (Exception X){
            Assert.fail(String.format("Unable to start the WebDriver. Exception error: %s", X.getMessage()));
        }

        return driver;
    }

    private WebDriverType setDriverType(String browserName){

        switch (browserName){
            case "chrome":
                return WebDriverType.CHROME;
            case "firefox":
                return WebDriverType.FIREFOX;
            default:
                throw new InputMismatchException("Selected browser is not supported.");
        }
    }

    private String selectDriverPath(String hostOs){
        switch (hostOs){
            case "windows":
                return ConfigReader.windowsPath;
            case "linux":
                return ConfigReader.linuxPath;
            default:
                throw new InputMismatchException("Invalid Operating System selected.");
        }
    }

    private String setDriverPath(String path, String browser){
        switch (browser){
            case "chrome":
                return String.format("%s/chromedriver.exe", path);
            case "firefox":
                return String.format("%s/geckodriver.exe", path);
            default:
                throw new InputMismatchException("No driver found for the selected browser.");
        }
    }

}
