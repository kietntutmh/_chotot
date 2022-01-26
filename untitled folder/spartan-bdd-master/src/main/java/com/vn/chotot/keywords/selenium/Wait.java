package com.vn.chotot.keywords.selenium;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.vn.chotot.driver.selenium.DriverFactory.instance;
import static com.vn.chotot.keywords.selenium.Element.getElementLocator;
import static com.vn.chotot.keywords.selenium.Element.getElementLocatorString;

public class Wait {

    static final Logger log = LogManager.getLogger(Log4jFactory.class);

    public static void waitForPageLoad(int timeout) {
        // Wait for page completed load
        try {
            WebDriver driver = instance().getWebDriver();
            ExpectedCondition<Boolean> pageLoadCondition =
                    driver1 ->
                            ((JavascriptExecutor) driver1)
                                    .executeScript("return document.readyState")
                                    .equals("complete");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(pageLoadCondition);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static boolean waitForElementVisible(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static boolean waitForElementStale(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.stalenessOf(webElement));
            return true;
        } catch (TimeoutException | StaleElementReferenceException  e) {
            return false;
        }
    }

    public static boolean waitForElementClickable(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public static boolean waitForPageTitleContainsText(String textValue, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.titleContains(textValue));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public static boolean waitForAllElementVisible(List<WebElement> listElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOfAllElements(listElement));
            return true;
        } catch (TimeoutException | NullPointerException e1) {
            try {
                if (listElement.isEmpty()) log.debug("All elements is empty and not visible");
                else
                    log.debug(
                            "All elements with locator"
                                    + getElementLocatorString(listElement.get(0))
                                    + " are NOT visible in "
                                    + waitTime
                                    + " second(s)");
            } catch (NullPointerException e2) {
                log.debug("List element is empty");
            }
            return false;
        }
    }

    public static boolean waitForAllElementPresent(List<WebElement> listElement, int waitTime) {
        By locator = By.xpath("");
        try {
            locator = getElementLocator(listElement.get(0));
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return true;
        } catch (TimeoutException | IndexOutOfBoundsException ex) {
            if (listElement.isEmpty()) log.debug("All elements is empty and not present");
            else
                log.debug(
                        "All elements with locator <"
                                + locator.toString()
                                + "> are NOT present in "
                                + waitTime
                                + " second(s)");

            return false;
        }
    }

    public static boolean waitForElementStaleness(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.stalenessOf(webElement));
            return true;
        } catch (TimeoutException | NullPointerException | IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean waitForNumberOfWindows(int expectedNumber, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumber));
            return true;
        } catch (TimeoutException | NullPointerException e) {
            return false;
        }
    }

    public static boolean waitForElementPresent(WebElement webElement, int waitTime) {
        long currentTime = System.currentTimeMillis();
        long maxTime = currentTime + waitTime * 1000;
        boolean check = false;
        while (currentTime < maxTime) {
            if (check) break;

            // Check element displayed or not
            try {
                check = webElement.isDisplayed();
            } catch (NoSuchElementException | NullPointerException ex) {
                check = false;
            }

            currentTime = System.currentTimeMillis();
        }
        return check;
    }

    public static boolean waitForElementNotPresent(WebElement webElement, int waitTime) {
        long currentTime = System.currentTimeMillis();
        long maxTime = currentTime + waitTime * 1000;
        boolean check = true;
        while (currentTime < maxTime) {
            if (!check) break;

            // Check element displayed or not
            try {
                check = webElement.isDisplayed();
            } catch (NoSuchElementException ex) {
                check = true;
            }

            currentTime = System.currentTimeMillis();
        }
        return check;
    }

    public static Boolean waitForElementNotVisible(WebElement webElement, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean waitForTextPresentInElement(
            WebElement webElement, String text, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitForListElementChangedByNumber(
            List<WebElement> listElement, int currentNumberElement, int waitTime) {
        long currentTime = System.currentTimeMillis();
        long maxTime = currentTime + waitTime * 1000;
        int newNumberElement = currentNumberElement;
        while (currentTime < maxTime) {
            currentTime = System.currentTimeMillis();
            try {
                newNumberElement = listElement.size();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (currentNumberElement != newNumberElement) {
                break;
            }
        }
    }

    public static boolean waitForLoadingIconDismissed(int waitTime, boolean isCPPage) {
        By selector = By.xpath("//*[@class=\"__skt-desktop-loader\"]");

        // Change selector if testing on CP page
        if (isCPPage) selector = By.xpath("//*[@class=\"fa fa-spinner fa-spin\"]");

        if (waitForLoadingIconDisplayed(selector, 1)) {
            long maxWaitTime = System.currentTimeMillis() + waitTime * 1000;
            long currentWaitTime = System.currentTimeMillis();
            while (currentWaitTime < maxWaitTime) {
                if (instance().getWebDriver().findElements(selector).size() == 0) return true;
                // Get current time
                currentWaitTime = System.currentTimeMillis();
            }
        }
        return false;
    }

    public static boolean waitForLoadingIconDismissed(
            int waitAppear, int waitDismissed, boolean isCPPage) {
        By selector = By.xpath("//*[@class=\"__skt-desktop-loader\"]");

        // Change selector if testing on CP page
        if (isCPPage) selector = By.xpath("//*[@class=\"fa fa-spinner fa-spin\"]");

        if (waitForLoadingIconDisplayed(selector, waitAppear)) {
            long maxWaitTime = System.currentTimeMillis() + waitDismissed * 1000;
            long currentWaitTime = System.currentTimeMillis();
            while (currentWaitTime < maxWaitTime) {
                if (instance().getWebDriver().findElements(selector).size() == 0) return true;
                // Get current time
                currentWaitTime = System.currentTimeMillis();
            }
        }
        return false;
    }

    public static boolean waitForLoadingPageIconDismissed(int waitAppear, int waitDisappear) {
        By selector = By.xpath("//span[style and div[@style]]");
        if (waitForLoadingIconDisplayed(selector, waitAppear)) {
            long maxWaitTime = System.currentTimeMillis() + waitDisappear * 1000;
            long currentWaitTime = System.currentTimeMillis();
            int numberItem;
            while (currentWaitTime < maxWaitTime) {
                numberItem = instance().getWebDriver().findElements(selector).size();
                if (numberItem == 0) {
                    return true;
                }
                // Get current time
                currentWaitTime = System.currentTimeMillis();
            }
        }
        return false;
    }

    public static boolean waitForLoadingIconDisplayed(By selector, int waitTime) {
        long maxWaitTime = System.currentTimeMillis() + waitTime * 1000;
        long currentWaitTime = System.currentTimeMillis();
        int numberItem;
        while (currentWaitTime < maxWaitTime) {
            numberItem = instance().getWebDriver().findElements(selector).size();
            if (numberItem > 0) return true;

            // Get current time
            currentWaitTime = System.currentTimeMillis();
        }
        return false;
    }

    //------------ For BDD ------------

    /**
     * @author Vu Hoang
     */
    public static boolean waitForElementVisible(By eLocator, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(eLocator));
            return true;
        } catch (TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }
}
