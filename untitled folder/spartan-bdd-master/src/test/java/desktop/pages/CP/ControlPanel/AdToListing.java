package desktop.pages.CP.ControlPanel;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Element.clickElementOnListByName;
import static com.vn.chotot.keywords.selenium.Element.selectOptionByVisibleText;
import static com.vn.chotot.keywords.selenium.Page.refreshPage;
import static com.vn.chotot.keywords.selenium.Page.switchToAnotherWindow;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementStaleness;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class AdToListing extends BasePage {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//input[@name=\"action\"]/following-sibling::span")
    private List<WebElement> lst_ReviewAction;

    @FindBy(xpath = "//select[@data-name=\"refusal_reason\"]")
    private WebElement lst_RefusalOption;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btn_NextAd;

    public void selectAdReviewAction(String action, String refusal) {
        // Wait for page load
        waitForElementVisible(btn_NextAd, maxWaitTime * 5);

        // Select action
        if (refusal == null)
            clickElementOnListByName(lst_ReviewAction, action, true, FailureHandling.STOP_ON_FAILURE);
        else selectOptionByVisibleText(lst_RefusalOption, refusal);

        // Click next_ad
        moveAndClick(btn_NextAd);
    }

    public void backToSearchForAdPage() {
        // Switch to search page
        switchToAnotherWindow();

        // Wait for page load
        waitForElementStaleness(btn_NextAd, maxWaitTime);

        // Refresh page
        refreshPage();
    }
}
