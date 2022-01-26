package com.vn.chotot.driver.selenium.browser;

import com.vn.chotot.driver.selenium.ConfigDriver;
import com.vn.chotot.driver.selenium.SetupDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import static com.vn.chotot.configuration.Constant.HEADLESS_MODE;

public class ChromeConfiguration extends ConfigDriver implements SeleniumDriver {

    private final boolean headLessMode = Boolean.parseBoolean(HEADLESS_MODE);
    private ChromeOptions options;

    private ChromeOptions getOptions() {
        if (options == null) {
            options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            if (this.headLessMode) {
                options.setHeadless(true);
                options.setAcceptInsecureCerts(true);
                options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--window-size=1024,768");
                options.addArguments("--disable-features=VizDisplayCompositor");
                options.addArguments("--enable-features=NetworkServiceInProcess");
                // options.addArguments("--dns-prefetch-disable");
                // options.addArguments("--disable-browser-side-navigation");
                // options.addArguments("--disable-features=NetworkService");
                // options.addArguments("--allow-insecure-localhost");
                // options.addArguments("--proxy-bypass-list=*");
                // options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            }
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        new SetupDriver("chrome");
        WebDriver driver = new ChromeDriver(getOptions());
        manage(driver);
        return driver;
    }

    @Override
    public void setDriverOptions(Object options) {
        this.options = (ChromeOptions) options;
    }
}
