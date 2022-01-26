package desktop.components;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClickJS;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class Footer extends BasePage {
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(
            xpath =
                    "//*[@class=\"appWrapper-DesktopFooter\"]//a[@class=\"appWrapper-Footer-a\"][not(img)]")
    private List<WebElement> lst_FooterLinkText;

    @FindBy(xpath = "//*[@class=\"appWrapper-DesktopFooter\"]//a[@class=\"appWrapper-Footer-a\"]/img")
    private List<WebElement> lst_FooterLinkImg;

    public void verifyUserCanSelectLinkUnderFooter() {
        // Close popup if displayed
        new Popup().closePopup(minWaitTime);

        this.verifyUserCanSelectLinkImage();
        this.verifyUserCanSelectLinkText();
    }

    private void verifyUserCanSelectLinkImage() {
        String newPageURL;
        String altText;
        waitForAllElementVisible(lst_FooterLinkImg, maxWaitTime);

        String currentWindowHandle = DriverFactory.instance().getWebDriver().getWindowHandle();
        String currentPageURL = DriverFactory.instance().getWebDriver().getCurrentUrl();
        // Check link image
        for (WebElement webElement : lst_FooterLinkImg) {
            altText = webElement.getAttribute("alt");

            // Click on footer link
            waitForElementVisible(webElement, maxWaitTime * 2);
            moveAndClickJS(webElement);

            // Wait for new windows
            waitForNumberOfWindows(2, maxWaitTime * 2);

            // Switch to current window
            switchToAnotherWindow();

            // Get new page url
            newPageURL = getPageURL(maxWaitTime * 2);

            // Check Link is external
            if (verifyNotMatch(currentPageURL, newPageURL, false, FailureHandling.CONTINUE_ON_FAILURE))
                log.info("IMG text: " + altText);
            else log.info("IMG text: " + altText + " FAILED (Not an external link)"); // If Matched

            // Back to previous window
            closeAndSwitchToWindowHandle(currentWindowHandle);
        }
    }

    /**
     * This function is to verify URL of Texts are correct. Click on Texts then check URL Verify URL
     * on new tab is same with URL of "src" attribute (NOT YET) Verify Text link is external
     *
     * @author Quoc Tran Last Updated: 2019/06/21 Change Log: 2019/06/21 Vu Hoang public -> private
     * FailureHandling -> CONTINUE_ON_FAILURE Add log.info for non-external links
     */
    private void verifyUserCanSelectLinkText() {
        String newPageURL;
        String linkText;
        waitForAllElementVisible(lst_FooterLinkText, minWaitTime);

        String currentWindowHandle = DriverFactory.instance().getWebDriver().getWindowHandle();
        String currentPageURL = DriverFactory.instance().getWebDriver().getCurrentUrl();
        // Check link text
        for (WebElement webElement : lst_FooterLinkText) {
            linkText = webElement.getText();
            log.info("Link text: " + linkText);

            // Click on footer link
            waitForElementVisible(webElement, maxWaitTime);
            moveAndClickJS(webElement);

            // Wait for new windows
            waitForNumberOfWindows(2, maxWaitTime * 2);

            // Switch to current window
            switchToAnotherWindow();

            // Get new page url
            newPageURL = getPageURL(maxWaitTime * 2);

            // Check Link is external
            if (verifyNotMatch(currentPageURL, newPageURL, false, FailureHandling.CONTINUE_ON_FAILURE))
                log.info("Link text: " + linkText);
            else log.info("Link text: " + linkText + " FAILED (Not an external link)"); // If Matched

            // Check new page url displayed
            verifyNotMatch(currentPageURL, newPageURL, false, FailureHandling.STOP_ON_FAILURE);

            // Back to previous window
            closeAndSwitchToWindowHandle(currentWindowHandle);
        }
    }
}
