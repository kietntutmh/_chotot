package com.vn.chotot.keywords.selenium;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static com.vn.chotot.configuration.Custom.isUsingCPPage;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.driver.selenium.DriverFactory.instance;
import static com.vn.chotot.keywords.selenium.Utils.getProjectDir;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class Action {

    static final Logger log = LogManager.getLogger(Log4jFactory.class);

    private Action() {
    }

    public static List<String> getListText(List<WebElement> listElement) {
        List<String> listText = new ArrayList<>();
        for (WebElement e : listElement) {
            listText.add(e.getText().trim());
        }
        return listText;
    }

    public static List<String> getListAttributeValues(
            List<WebElement> listElement, String attributeName) {
        List<String> listAttributeValue = new ArrayList<>();
        for (WebElement e : listElement) {
            listAttributeValue.add(e.getAttribute(attributeName));
        }
        return listAttributeValue;
    }

    public static void setText(WebElement webElement, String value) {
        waitForElementVisible(webElement, minWaitTime);
        try{
            webElement.click();
        }catch (ElementClickInterceptedException e){
            log.info("Set Text: Element can't be clickable");
        }
        webElement.clear();
        webElement.sendKeys(value);
    }

    public static String getText(WebElement webElement, int timeout) {
        waitForElementVisible(webElement, timeout);
        return webElement.getText();
    }

    public static String getAttributeValue(WebElement webElement, String attributeName, int timeout) {
        waitForElementVisible(webElement, timeout);
        return webElement.getAttribute(attributeName);
    }

    public static void clickJS(WebElement webElement) {
        waitForElementClickable(webElement, minWaitTime);
        try {
            JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
            executor.executeScript("arguments[0].click();", webElement);
        } catch (IllegalArgumentException e) {
            log.debug("\n***** ClickJS got error: {0}", e.getMessage());
        }
        waitForLoadingIconDismissed(minWaitTime, isUsingCPPage);
    }

    public static void clickJS(WebElement webElement, int waitTime) {
        waitForElementClickable(webElement, waitTime);
        try {
            JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
            executor.executeScript("arguments[0].click();", webElement);
        } catch (IllegalArgumentException e) {
            log.debug("\n***** ClickJS got error: {0}", e.getMessage());
        }
        waitForLoadingIconDismissed(minWaitTime, isUsingCPPage);
    }

    public static void clickAction(WebElement webElement) {
        waitForElementClickable(webElement, minWaitTime);
        org.openqa.selenium.interactions.Actions action = new Actions(instance().getWebDriver());
        action.click(webElement).build().perform();
        waitForLoadingIconDismissed(minWaitTime, isUsingCPPage);
    }

    public static void doubleClickAction(WebElement webElement) {
        waitForElementClickable(webElement, minWaitTime);
        org.openqa.selenium.interactions.Actions action = new Actions(instance().getWebDriver());
        action.doubleClick(webElement).build().perform();
        waitForLoadingIconDismissed(minWaitTime, isUsingCPPage);
    }

    public static void moveToElement(WebElement webElement) {
        waitForElementPresent(webElement, minWaitTime);
        org.openqa.selenium.interactions.Actions action = new Actions(instance().getWebDriver());
        action.moveToElement(webElement).build().perform();
    }

    public static void moveAndClick(WebElement webElement) {
        moveToElement(webElement);
        clickAction(webElement);
    }

    public static void moveAndClick(WebElement webElement, int waitTime) {
        waitForElementVisible(webElement, waitTime);
        moveAndClick(webElement);
    }

    public static void moveAndDoubleClick(WebElement webElement) {
        moveToElement(webElement);
        doubleClickAction(webElement);
    }

    public static void moveAndClickJS(WebElement webElement) {
        moveToElement(webElement);
        clickJS(webElement);
    }

    public static void moveAndClickJS(WebElement webElement, int waitTime) {
        waitForElementVisible(webElement, waitTime);
        moveAndClickJS(webElement);
    }

    public static void uploadImage(WebElement inputElement, String imagePath) {
        waitForElementVisible(inputElement, minWaitTime);
        inputElement.sendKeys(getProjectDir() + imagePath);
    }

    public static void scrollPage(WebElement webElement) {
        waitForElementClickable(webElement, minWaitTime);
        JavascriptExecutor executor = (JavascriptExecutor) instance().getWebDriver();
        executor.executeScript("arguments[0].scrollIntoView();", webElement);
        waitForLoadingIconDismissed(minWaitTime, isUsingCPPage);
    }

    public static void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) instance().getWebDriver();
            js.executeScript(
                    "arguments[0].setAttribute('style', 'background: yellow: 2px solid red;');", element);
            js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
        } catch (Exception ex) {
            log.debug(ex);
        }
    }
}
