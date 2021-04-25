package common;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static void setDriver(WebDriver driver) { webDriver.set(driver); }

    public static WebDriver getDriver(){ return webDriver.get(); }

    public static void closeDriver() {
        webDriver.get().quit();
        webDriver.remove();
    }
}