package com.vn.chotot.driver.selenium.browser;

import com.vn.chotot.configuration.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteConfiguration {

    private DesiredCapabilities capabilities;

    public WebDriver createDriver(String host) {
        try {
            initRemoteCapabilities();
            URL gridUrl = new URL("http://" + host + ":4444/wd/hub");
            return new RemoteWebDriver(gridUrl, this.capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initRemoteCapabilities() {
        if ("chrome".equals(Constant.BROWSER_NAME.toLowerCase().trim())) {
            this.capabilities = DesiredCapabilities.chrome();
            this.capabilities.setAcceptInsecureCerts(true);
            this.capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        }
    }
}
