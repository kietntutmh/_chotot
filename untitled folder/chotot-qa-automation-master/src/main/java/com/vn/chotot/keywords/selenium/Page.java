package com.vn.chotot.keywords.selenium;

import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.exception.FailureHandling;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.driver.selenium.DriverFactory.instance;
import static com.vn.chotot.keywords.selenium.Utils.*;
import static com.vn.chotot.keywords.selenium.Wait.waitForLoadingIconDismissed;
import static com.vn.chotot.keywords.selenium.Wait.waitForPageLoad;

public class Page {

    public static void openURL(String url) {
        instance().getWebDriver().get(url);
        waitForPageLoad(maxWaitTime * 10);
    }

    public static void navigateToURL(String url) {
        instance().getWebDriver().navigate().to(url);
    }

    public static void backToPreviousURL(String previousURL) {
        String currentURL;
        boolean check = false;
        for (int i = 1; i < 5; i++) {
            if (check) {
                waitForLoadingIconDismissed(minWaitTime, maxWaitTime * 2, false);
                break;
            } else {
                instance().getWebDriver().navigate().back();
                waitForLoadingIconDismissed(minWaitTime, maxWaitTime * 2, false);
                currentURL = getPageURL(minWaitTime);
                check = previousURL.equals(currentURL);
            }
        }
    }

    public static String getWindowHandle() {
        return DriverFactory.instance().getWebDriver().getWindowHandle();
    }

    public static void closeAndSwitchToWindowHandle(String windowsHandle) {
        Set<String> setWindowHandles = DriverFactory.instance().getWebDriver().getWindowHandles();
        for (String w : setWindowHandles) {
            if (!w.equals(windowsHandle)) {
                DriverFactory.instance().getWebDriver().switchTo().window(w);
                DriverFactory.instance().getWebDriver().close();
            }
        }
        DriverFactory.instance().getWebDriver().switchTo().window(windowsHandle);
        delayStep(minWaitTime); // Wait for page load
    }

    public static void switchToAnotherWindow() {
        Set<String> setWindowHandles = DriverFactory.instance().getWebDriver().getWindowHandles();
        String currentWindow = DriverFactory.instance().getWebDriver().getWindowHandle();
        for (String w : setWindowHandles) {
            if (!w.equals(currentWindow)) {
                DriverFactory.instance().getWebDriver().switchTo().window(w);
                delayStep(minWaitTime); // Wait for page load
            }
        }
    }

    public static void switchToWindowTitle(String windowTitle) {
        Set<String> setWindowHandles = DriverFactory.instance().getWebDriver().getWindowHandles();
        String currentWindow = DriverFactory.instance().getWebDriver().getWindowHandle();
        for (String w : setWindowHandles) {
            if (!w.equals(currentWindow)) {
                DriverFactory.instance().getWebDriver().switchTo().window(w);
                if (DriverFactory.instance().getWebDriver().getTitle().contains(windowTitle)) {
                    delayStep(minWaitTime); // Wait for page load
                    break;
                }
            }
        }
    }

    public static boolean waitNumberOfWindow(int expectedNumber, int waitTime) {
        try {
            long maxWaitTime = System.currentTimeMillis() + waitTime * 1000;
            long currentWaitTime = System.currentTimeMillis();
            int numberItem;
            while (currentWaitTime < maxWaitTime) {
                numberItem = instance().getWebDriver().getWindowHandles().size();
                if (numberItem == expectedNumber) {
                    return true;
                }
                currentWaitTime = System.currentTimeMillis();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return false;
    }

    public static void verifyPageTitleContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.titleContains(textValue));
    }

    public static void verifyPageURLContainsText(String textValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(instance().getWebDriver(), waitTime);
        wait.until(ExpectedConditions.urlContains(textValue));
    }

    public static void verifyPageTitleChanged(
            String previousTitle, int waitTime, FailureHandling failureHandling) {
        String currentTitle = getPageTitile(waitTime);
        verifyNotMatch(currentTitle, previousTitle, false, failureHandling);
    }

    public static void refreshPage() {
        instance().getWebDriver().navigate().refresh();
    }

    public static String getPageTitile(int waitTime) {
        long maxWaitTime = System.currentTimeMillis() + waitTime * 1000;
        long currentWaitTime = System.currentTimeMillis();
        String title = "";
        while (currentWaitTime < maxWaitTime) {
            title = instance().getWebDriver().getTitle();
            if (!title.equals("")) return title;

            // Get current time
            currentWaitTime = System.currentTimeMillis();
        }
        return title;
    }

    public static boolean verifyPageTitle(
            String expectedTitle, boolean isExactly, int waitTime, FailureHandling failureHandling) {
        String pageTitle = getPageTitile(waitTime);
        return verifyMatch(pageTitle, expectedTitle, isExactly, failureHandling);
    }

    public static boolean verifyPageTitleNotMatch(
            String expectedTitle, boolean isExactly, int waitTime, FailureHandling failureHandling) {
        String pageTitle = getPageTitile(waitTime);
        return verifyNotMatch(pageTitle, expectedTitle, isExactly, failureHandling);
    }

    public static String getPageURL(int waitTime) {
        long maxWaitTime = System.currentTimeMillis() + waitTime * 1000;
        long currentWaitTime = System.currentTimeMillis();
        String url = "";
        while (currentWaitTime < maxWaitTime) {
            try {
                url = instance().getWebDriver().getCurrentUrl();
                if (!url.equals("")) return url;
            } catch (WebDriverException e) {
                return null;
            }

            // Get current time
            currentWaitTime = System.currentTimeMillis();
        }
        return url;
    }
}
