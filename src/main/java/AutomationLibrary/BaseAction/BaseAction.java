package AutomationLibrary.BaseAction;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;

public abstract class BaseAction {
    protected WebDriver driver;

    protected BaseAction(WebDriver driver) {

        this.driver = driver;
    }
    /**
     * Wait for element present
     **/
    protected void waitFor( By by , int time_out){
        try {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(time_out)).pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class, NullPointerException.class);
              wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException ignored) {

        }
    }

    /**
     * Get element
     * @param by locator of element
     * @return element if found
     */
    protected WebElement getElement(By by){
        try {
             return driver.findElement(by);
        }catch (NoSuchElementException e){
            setFail("Element with locator ["+by+"] is not displayed");
            return null;
        }
    }

    /**
     * Send key to input field
     * @param by locator of input field
     * @param text input text
     */
    protected void sendKey(By by, String text){
        WebElement element = getElement(by);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Click on element
     * @param by locator of element
     */
    protected void click(By by){
        getElement(by).click();

    }

    /**
     * Verify element is present after a timeout
     * @param by locator of element
     * @param time_out timeout to wait
     * @param log log when no element is found
     */
    protected void verifyExistedElement(By by,int time_out, String log){
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(time_out)).pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class, NullPointerException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException ignored) {
            setFail(log);
        }
    }

    /**
     * Set the Test is fail and stop
     * @param log log when failed
     */
    private void setFail(String log){

        Assert.fail(log);
    }
}
