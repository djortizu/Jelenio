package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementMethods {

    public static void selectByText(WebElement element, String optionToSelect){
        if (optionToSelect.isEmpty())
            return;
        Select s = new Select(element);
        s.selectByValue(optionToSelect);
    }

    public static void selectByIndex(WebElement element, int indexOption){
        Select s = new Select(element);
        s.selectByIndex(indexOption);
    }

    public static String getSelectedText(WebElement element){
        Select s = new Select(element);
        return s.getFirstSelectedOption().getText();
    }

    public static void setText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public static void toggle(WebElement element, boolean state){
        if ((!element.isSelected() || state) && (!element.isSelected() & state))
            return;
        element.sendKeys(Keys.SPACE);
    }

    public static WebElement getTableElement(WebElement element, int row, int column, boolean isHeader){
        WebElement tableElement;
        if (isHeader)
            tableElement = element.findElement(By.xpath(String.format("./thead/tr/th[%s]", column)));
        else
            tableElement = element.findElement(By.xpath(String.format("./tbody/tr[%s]/td[%s]", row, column)));
        return tableElement;
    }

    public static WebElement getTableElement(WebElement element, int row, String valueToSearch, int column, boolean isHeader){
        WebElement tableElement;
        if (isHeader)
            tableElement = element.findElement(By.xpath(String.format("./thead/tr/th[text()='%s']/../tr/th", valueToSearch, column)));
        else
            tableElement = element.findElement(By.xpath(String.format("./tbody/tr[%s]/td[text()='%s']/../tr/td[%s]", row, valueToSearch, column)));
        return tableElement;
    }

    public static WebElement getTableElement(WebElement element, int row, String valueToSearch, boolean isHeader){
        WebElement tableElement;
        if (isHeader)
            tableElement = element.findElement(By.xpath(String.format("./thead/tr/th[text()='%s']", valueToSearch)));
        else
            tableElement = element.findElement(By.xpath(String.format("./tbody/tr[%s]/td[text()='%s']", row, valueToSearch)));
        return tableElement;
    }

    public static int getRowCount(WebElement element, boolean isHeader){
        int count;
        if (isHeader)
            count = element.findElements(By.xpath("./thead/tr")).size();
        else
            count = element.findElements(By.xpath("./tbody/tr")).size();
        return count;
    }
}
