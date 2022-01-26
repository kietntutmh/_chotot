package com.vn.chotot.base;

import com.vn.chotot.driver.selenium.DriverFactory;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        initPageObject();
    }

    private void initPageObject() {
        PageFactory.initElements(DriverFactory.instance().getWebDriver(), this);
    }
}
