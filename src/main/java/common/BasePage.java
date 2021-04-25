package common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static common.WebDriverManager.getDriver;

public class BasePage {

    protected WebDriverWait wait;

    public BasePage(){ wait = new WebDriverWait(getDriver(), 30); }

    public void waitForElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForTextInElement(WebElement element, String expectedText){
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public void waitForValue(WebElement element, String expectedValue){
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, expectedValue));
    }
}
