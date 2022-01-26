package com.vn.chotot.keywords.selenium;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.driver.selenium.DriverFactory.instance;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Wait.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Element {

    static final Logger log = LogManager.getLogger(Element.class);

    public static void verifyElementPresent(
            WebElement webElement, int waitTime, FailureHandling failureHandling) {
        if (!waitForElementPresent(webElement, waitTime))
            exceptionHandler(
                    new StepFailedException(
                            "\nWeb element " + webElement + " is NOT present in " + waitTime + " seconds"),
                    failureHandling);
    }

    public static void verifyElementNotPresent(
            WebElement webElement, int waitTime, FailureHandling failureHandling) {
        if (!waitForElementNotPresent(webElement, waitTime))
            exceptionHandler(
                    new StepFailedException(
                            "\nWeb element " + webElement + " is present in " + waitTime + " seconds"),
                    failureHandling);
    }

    public static void verifyElementVisible(WebElement webElement, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void verifyElementVisible(
            WebElement webElement, int waitTime, FailureHandling failureHandling) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException ex) {
            exceptionHandler(new StepFailedException(ex.getMessage()), failureHandling);
        }
    }

    public static void verifyElementNotVisible(WebElement webElement, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void verifyElementNotVisible(
            WebElement webElement, int waitTime, FailureHandling failureHandling) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch (TimeoutException ex) {
            exceptionHandler(new StepFailedException(ex.getMessage()), failureHandling);
        }
    }

    public static void verifyAllElementVisible(
            List<WebElement> lisElement, int waitTime, FailureHandling failureHandling) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOfAllElements(lisElement));
        } catch (TimeoutException ex) {
            exceptionHandler(new StepFailedException(ex.getMessage()), failureHandling);
        }
    }

    public static boolean verifyTextPresent(
            String textValue, Boolean isExactly, int waitTime, FailureHandling failureHandling) {
        String xpath;
        if (isExactly) xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (TimeoutException e) {
            exceptionHandler(new StepFailedException(e.getMessage()), failureHandling);
            return false;
        }
    }

    public static void verifyTextNotPresent(
            String textValue, Boolean isExactly, int waitTime, FailureHandling failureHandling) {
        String xpath;
        if (isExactly) xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        else xpath = "//*[contains(text(),\"" + textValue + "\")]";
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            exceptionHandler(
                    new StepFailedException("Web element with xpath : \"" + xpath + "\" is present"),
                    failureHandling);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public static String clickRandomElementOnList(List<WebElement> listElement) {
        waitForAllElementVisible(listElement, minWaitTime);
        int numberElement = listElement.size();
        if (numberElement == 1) {
            String value = getText(listElement.get(0), minWaitTime);
            log.debug("Click on the first element text: " + listElement.get(0).getText());
            moveAndClick(listElement.get(0));
            return value;
        } else {
            int randomIndex = (int) (Math.random() * (numberElement - 1) + 1);
            String value = getText(listElement.get(randomIndex), minWaitTime);
            log.debug(
                    "Click on random index "
                            + randomIndex
                            + " of element text: "
                            + listElement.get(randomIndex).getText());
            moveAndClick(listElement.get(randomIndex));
            return value;
        }
    }

    public static String clickJSRandomElementOnList(List<WebElement> listElement) {
        waitForAllElementVisible(listElement, minWaitTime);
        int numberElement = listElement.size();
        if (numberElement == 1) {
            String value = getText(listElement.get(0), minWaitTime);
            log.debug("ClickJS on the first element text: " + listElement.get(0).getText());
            moveAndClick(listElement.get(0));
            return value;
        } else {
            int randomIndex = (int) (Math.random() * (numberElement - 1) + 1);
            String value = getText(listElement.get(randomIndex), minWaitTime);
            log.debug(
                    "ClickJS on random index "
                            + randomIndex
                            + " of element text: "
                            + listElement.get(randomIndex).getText());
            moveAndClickJS(listElement.get(randomIndex));
            return value;
        }
    }

    public static void clickElementOnListByName(
            List<WebElement> listElement,
            String name,
            Boolean isExactly,
            FailureHandling failureHandling) {
        waitForAllElementVisible(listElement, maxWaitTime);
        for (WebElement webElement : listElement) {
            if (isExactly) {
                if (webElement.getText().equals(name)) {
                    moveAndClick(webElement);
                    return;
                }
            } else {
                if (webElement.getText().contains(name)) {
                    moveAndClick(webElement);
                    return;
                }
            }
        }
        exceptionHandler(
                new StepFailedException("No existing element <" + name + "> on list"), failureHandling);
    }

    public static void clickJSElementOnListByName(
            List<WebElement> listElement,
            String name,
            Boolean isExactly,
            FailureHandling failureHandling) {
        waitForAllElementVisible(listElement, maxWaitTime);
        for (WebElement webElement : listElement) {
            if (isExactly) {
                if (webElement.getText().equals(name)) {
                    moveAndClick(webElement);
                    return;
                }
            } else {
                if (webElement.getText().contains(name)) {
                    moveAndClick(webElement);
                    return;
                }
            }
        }
        exceptionHandler(
                new StepFailedException("No existing element <" + name + "> on list"), failureHandling);
    }

    public static void clickJSElementOnListByAttribute(
            List<WebElement> listElement,
            String attName,
            String attValue,
            boolean isExact,
            FailureHandling failureHandling) {
        waitForAllElementVisible(listElement, maxWaitTime);
        attValue = attValue.trim();
        for (WebElement webElement : listElement) {
            String actualValue = getPropertyValueByJS(webElement, attName).trim();
            if (isExact) {
                if (actualValue.equals(attValue)) {
                    clickJS(webElement);
                    return;
                }
            } else {
                if (actualValue.toLowerCase().contains(attValue.toLowerCase())) {
                    clickJS(webElement);
                    return;
                }
            }
        }
        exceptionHandler(
                new StepFailedException("No existing element <" + attValue + "> on list"), failureHandling);
    }

    public static String selectElementOnListByIndex(
            List<WebElement> listElement, int index, FailureHandling failureHandling) {
        waitForAllElementVisible(listElement, minWaitTime);
        if (!listElement.isEmpty()) {
            String value = getText(listElement.get(index), minWaitTime);
            clickAction(listElement.get(index));
            return value;
        } else {
            exceptionHandler(new StepFailedException("No existing element on list"), failureHandling);
        }
        return null;
    }

    public static void selectAllElementOnList(
            List<WebElement> listElement, FailureHandling failureHandling) {
        waitForAllElementVisible(listElement, minWaitTime);
        if (!listElement.isEmpty()) {
            for (WebElement webElement : listElement) clickAction(webElement);
        } else {
            exceptionHandler(new StepFailedException("No existing list element"), failureHandling);
        }
    }

    public static void selectCheckBox(WebElement webElement) {
        waitForElementVisible(webElement, minWaitTime);
        if (!Boolean.parseBoolean(webElement.getAttribute("checked"))) clickAction(webElement);
    }

    public static void selectCheckBoxJS(WebElement webElement) {
        waitForElementVisible(webElement, minWaitTime);
        if (!Boolean.parseBoolean(webElement.getAttribute("checked"))) clickJS(webElement);
    }

    public static String getPropertyValueByJS(WebElement webElement, String propertyName) {
        JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
        return String.valueOf(
                executor.executeScript("return arguments[0]." + propertyName + ";", webElement));
    }

    public static int getNumberElement(List<WebElement> listElement) {
        try {
            waitForAllElementPresent(listElement, minWaitTime);
            return listElement.size();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public static List<String> getAttributeValueOfListElement(
            List<WebElement> listElement, String attributeName) {
        waitForAllElementVisible(listElement, minWaitTime);
        List<String> values = new ArrayList<>();
        for (WebElement e : listElement) {
            values.add(e.getAttribute(attributeName));
        }
        return values;
    }

    public static String getAttributeValueOfElementOnListByIndex(
            List<WebElement> listElement, int index, String attributeName) {
        waitForAllElementVisible(listElement, minWaitTime);
        return listElement.get(index).getAttribute(attributeName);
    }

    public static int getIndexOfElementOnListByAttributeValue(
            List<WebElement> listElement,
            String attributeName,
            String attributeValue,
            boolean isExactly) {
        waitForAllElementVisible(listElement, minWaitTime);
        for (int i = 0; i <= listElement.size(); i++) {
            if (isExactly) {
                if (listElement.get(i).getAttribute(attributeName).equals(attributeValue)) return i;
            } else {
                if (listElement.get(i).getAttribute(attributeName).contains(attributeValue)) return i;
            }
        }
        return -1;
    }

    public static void selectOptionByVisibleText(WebElement webElement, String text) {
        waitForElementVisible(webElement, minWaitTime);
        Select options = new Select(webElement);
        options.selectByVisibleText(text);
    }

    public static String getElementLocatorString(WebElement webElement) {
        return webElement.toString().split("->")[1].replaceFirst("(?s)(.*)]", "$1" + "");
    }

    public static By getElementLocator(WebElement webElement) {
        String strLocator = getElementLocatorString(webElement);
        String xpathLocator = strLocator.substring(strLocator.indexOf(':') + 2);
        return By.xpath(xpathLocator);
    }

    public static boolean verifyElementText(
            WebElement webElement,
            boolean isExactly,
            String expectText,
            FailureHandling failureHandling) {
        waitForElementVisible(webElement, minWaitTime);
        try {
            if (isExactly) Assert.assertEquals(webElement.getText().trim(), expectText);
            else Assert.assertTrue(webElement.getText().contains(expectText));
            return true;
        } catch (AssertionError ex) {
            String msg =
                    "Web element does not "
                            + (isExactly ? "equal" : "contains")
                            + " text \""
                            + expectText
                            + "\"";
            exceptionHandler(new StepFailedException(msg), failureHandling);
            return false;
        }
    }

    /**
     * This function is to select option by Text of A DROP-DOWN LIST Note: //button //ul //li
     *
     * @param elementDropdown
     * @param elementList
     * @param data            Last Update: 2019/06/24
     * @author Vu Hoang
     */
    public static void selectDropdownButtonByText(
            WebElement elementDropdown, List<WebElement> elementList, String data) {
        waitForElementVisible(elementDropdown, maxWaitTime);
        moveAndClickJS(elementDropdown);
        for (WebElement e : elementList) {
            waitForElementVisible(e, minWaitTime);
            if (e.getText().trim().equals(data)) {
                moveAndClick(e);
                break;
            }
        }
    }

}
