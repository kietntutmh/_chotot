package com.vn.chotot.driver.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;

import static com.vn.chotot.configuration.Constant.DRIVER_VERSION;

public class SetupDriver {

    private final String browserName;

    public SetupDriver(String browserName) {
        this.browserName = browserName;
        setDriverExecutable();
    }

    private void setDriverExecutable() {
        String targetPath = "drivers";
        String pathToDriver;
        // Initiate new web-driver instance desktop.base on browserName
        if ("chrome".equals(browserName.toLowerCase().trim())) {
            WebDriverManager.chromedriver().targetPath(targetPath);
            if (DRIVER_VERSION != null) WebDriverManager.chromedriver().version(DRIVER_VERSION).setup();
            else WebDriverManager.chromedriver().setup();
            pathToDriver = WebDriverManager.chromedriver().getBinaryPath();
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, pathToDriver);
            System.out.println("Downloaded version: " + WebDriverManager.chromedriver().getDownloadedVersion());
        }
    }
}
