package com.vn.chotot.driver.selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.vn.chotot.helper.FileHelper.getProperties;

public class ConfigDriver {

    private boolean maximize;
    private int width;
    private int height;
    private int implicitlyWait;
    private int pageLoadTimeout;
    private int scriptTimeout;

    protected ConfigDriver() {
        Properties prop = getProperties("driver");
        this.maximize = Boolean.parseBoolean(prop.getProperty("maximize"));
        this.width = Integer.parseInt(prop.getProperty("width"));
        this.height = Integer.parseInt(prop.getProperty("height"));
        this.implicitlyWait = Integer.parseInt(prop.getProperty("implicitlyWait"));
        this.pageLoadTimeout = Integer.parseInt(prop.getProperty("pageLoadTimeout"));
        this.scriptTimeout = Integer.parseInt(prop.getProperty("scriptTimeout"));
    }

    protected void manage(final WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        if (maximize) driver.manage().window().maximize();
        if (height > 0 && width > 0 && !maximize)
            driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().deleteAllCookies();
    }

    protected boolean isMaximize() {
        return maximize;
    }

    protected void setMaximize(boolean maximize) {
        this.maximize = maximize;
    }

    protected int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = Math.max(width, 0);
    }

    protected int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = Math.max(height, 0);
    }

    protected int getImplicitlyWait() {
        return implicitlyWait;
    }

    protected void setImplicitlyWait(int implicitlyWait) {
        this.implicitlyWait = Math.max(implicitlyWait, 0);
    }

    protected int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    protected void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = Math.max(pageLoadTimeout, 0);
    }

    protected int getScriptTimeout() {
        return scriptTimeout;
    }

    protected void setScriptTimeout(int scriptTimeout) {
        this.scriptTimeout = Math.max(scriptTimeout, 0);
    }
}
