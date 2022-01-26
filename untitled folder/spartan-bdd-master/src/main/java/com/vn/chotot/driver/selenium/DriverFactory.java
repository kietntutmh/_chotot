package com.vn.chotot.driver.selenium;

import com.vn.chotot.driver.selenium.browser.ChromeConfiguration;
import com.vn.chotot.driver.selenium.browser.RemoteConfiguration;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.vn.chotot.configuration.Constant.BROWSER_NAME;
import static com.vn.chotot.configuration.Constant.SELENIUM_HOST;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.getProjectDir;

public class DriverFactory {
    private static DriverFactory webDriverFactory;
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());
    final String screenshotPath = getProjectDir() + File.separator + "screenshots" + File.separator;
    private final LinkedMap<Long, WebDriver> driverStorage = new LinkedMap<>();
    private WebDriver webDriver;
    private String executedBrowser = "";

    private DriverFactory() {
    }

    public static synchronized DriverFactory instance() {
        if (webDriverFactory == null) webDriverFactory = new DriverFactory();
        return webDriverFactory;
    }

    public void createWebDriver(String browserName) {
        if (BROWSER_NAME != null) browserName = BROWSER_NAME; // Select browser input from cmd
        this.executedBrowser = browserName;
        if (SELENIUM_HOST != null) {
            webDriver = new RemoteConfiguration().createDriver(SELENIUM_HOST);
        } else {
            if ("chrome".equals(browserName.toLowerCase().trim())) {
                webDriver = new ChromeConfiguration().createDriver();
            }
        }
        setDriverStorage(webDriver);
    }

    public String getExecutedBrowser() {
        return this.executedBrowser;
    }

    private void setDriverStorage(WebDriver webDriver) {
        driverStorage.put(Thread.currentThread().getId(), webDriver);
    }

    public WebDriver getWebDriver() {
        if (driverStorage.size() == 0) {
            log.info("***** No thread id *****\n");
            return null;
        }
        if (driverStorage.containsKey(Thread.currentThread().getId()))
            return driverStorage.get(Thread.currentThread().getId());
        return driverStorage.getValue(0);
    }

    public void disposeWebDriver() {
        if (!driverStorage.isEmpty()) {
            log.info("\n----- Close current web-driver\n");
            if (driverStorage.containsKey(Thread.currentThread().getId())) {
                driverStorage.get(Thread.currentThread().getId()).quit();
            }
            driverStorage.remove(Thread.currentThread().getId());
        }
    }

    public void disposeAllDriver() {
        if (!driverStorage.isEmpty()) {
            log.info("\n----- Close all web-driver\n");
            for (Map.Entry<Long, WebDriver> driver : driverStorage.entrySet()) {
                driver.getValue().quit();
            }
        }
    }

    public String takeScreenshot(String imagePath) {
        // Check screenshot directory exist or not
        File parentPath = new File(screenshotPath);
        if (!parentPath.exists()) parentPath.mkdirs();

        if (getWebDriver() != null) {
            try {
                TakesScreenshot scrShot = ((TakesScreenshot) getWebDriver());
                delayStep(minWaitTime); // wait for take screenshot complete
                File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
                File destFile = new File(screenshotPath + imagePath);
                log.info(
                        "\n----- Take screenshot of failed/skipped test into directory "
                                + destFile.getAbsolutePath());
                delayStep(minWaitTime);
                FileUtils.copyFile(srcFile, destFile);
                delayStep(minWaitTime);
                return destFile.getAbsolutePath();
            } catch (IOException | WebDriverException e) {
                log.debug("Take screenshot has error:\n" + e.getMessage());
                setExceptionDebug("Take screenshot has error:\n" + e.getMessage());
            }
        }
        return null;
    }
}
