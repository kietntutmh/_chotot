package desktop.components;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementNotVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;
import static desktop.configuration.BaseConfig.maxNumberPopupDisplayed;
import static desktop.configuration.BaseConfig.numberPopupDisplayed;

public class Popup extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@class=\"ab-image-area\"]")
    private WebElement img_PopupAds;

    @FindBy(xpath = "//*[@aria-label=\"Close Message\"]")
    private WebElement img_ClosePopup;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement img_Success;

    public void closePopup(int waitTime) {
        // Check popup
        if (numberPopupDisplayed < maxNumberPopupDisplayed) {
            if (waitForElementVisible(img_ClosePopup, waitTime)) {
                moveAndClick(img_ClosePopup);
                waitForElementNotVisible(img_ClosePopup, minWaitTime);
                numberPopupDisplayed++;
                log.info("---------- Closed popup " + numberPopupDisplayed + " time(s) !!!!");
            }
        }
    }

    public void clickSuccess(int waitTime) {
        if (waitForElementVisible(img_Success, waitTime)) {
            moveAndClick(img_Success);
            waitForElementNotVisible(img_Success, minWaitTime);
            numberPopupDisplayed++;
            log.info("---------- Closed popup " + numberPopupDisplayed + " time(s) !!!!");
        }
    }
}
