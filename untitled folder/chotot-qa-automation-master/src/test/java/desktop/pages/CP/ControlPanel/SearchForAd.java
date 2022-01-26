package desktop.pages.CP.ControlPanel;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.refreshPage;
import static com.vn.chotot.keywords.selenium.Page.switchToAnotherWindow;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class SearchForAd extends BasePage {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//*[@data-otype=\"default\"]")
    private WebElement input_Keyword;

    @FindBy(xpath = "//*[@class=\"loader\"]")
    private WebElement lbl_LoadingProgress;

    @FindBy(xpath = "//input[@data-name=\"search_type\"]")
    private List<WebElement> lst_SearchType;

    @FindBy(xpath = "//button[@id=\"submit\"]")
    private WebElement btn_ShowListing;

    @FindBy(xpath = "//*[@class=\"row\"]//*[contains(@data-key,\"subject\")]/a")
    private List<WebElement> lst_AdSubject;

    @FindBy(xpath = "//*[@class=\"panel-group\"]/h2")
    private List<WebElement> lst_AdStatus;

    @FindBy(xpath = "//*[@class=\"row\"]//*[contains(@data-key,\"list_id\")]/a")
    private List<WebElement> lst_AdListID;

    public void enterKeyword(String keyword) {
        waitForElementVisible(input_Keyword, maxWaitTime);
        setText(input_Keyword, keyword);
    }

    public void selectSearchType(String type) {
        waitForAllElementVisible(lst_SearchType, maxWaitTime);
        clickJSElementOnListByAttribute(
                lst_SearchType, "value", type, true, FailureHandling.STOP_ON_FAILURE);
    }

    public String clickShowListing() {
        // Wait for loading result
        int resultNumber;
        for (int i = 0; i < 20; i++) {
            // Click show
            clickJS(btn_ShowListing);
            waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 3, true);

            // Get number result
            resultNumber = getNumberElement(lst_AdSubject);
            if (resultNumber > 0) break;
            else delayStep(maxWaitTime);
        }

        // Return the first ad subject
        waitForElementStaleness(lst_AdSubject.get(0), maxWaitTime);
        return getAttributeValueOfElementOnListByIndex(lst_AdSubject, 0, "innerText");
    }

    public String doSimpleSearch(String keyword, String type) {
        enterKeyword(keyword);
        selectSearchType(type);
        return clickShowListing();
    }

    public void selectSearchResultByIndex(int index) {
        // Select result
        waitForAllElementVisible(lst_AdListID, maxWaitTime);
        selectElementOnListByIndex(lst_AdSubject, index, FailureHandling.CONTINUE_ON_FAILURE);

        // Switch to Review window
        switchToAnotherWindow();
    }

    public void verifyAdStatusByIndex(String expectedStatus, int index) {
        // Wait for page load
        refreshPage();
        if (waitForElementVisible(lbl_LoadingProgress, minWaitTime))
            waitForElementStaleness(lbl_LoadingProgress, maxWaitTime);

        // Select result
        String currentStatus =
                getAttributeValueOfElementOnListByIndex(lst_AdStatus, index, "innerText");

        // Verify status
        verifyMatch(
                currentStatus.toUpperCase(),
                expectedStatus.toUpperCase(),
                false,
                FailureHandling.STOP_ON_FAILURE);
    }

    public void selectAdListIDByIndex(int index) {
        // Wait for page load
        waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 5, true);

        // Click on list-id
        waitForAllElementVisible(lst_AdListID, maxWaitTime);
        moveToElement(lst_AdListID.get(0));
        selectElementOnListByIndex(lst_AdListID, index, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyListIDNotExistingByAdIndex(int index) {
        if (getNumberElement(lst_AdListID) > 0)
            verifyElementNotPresent(
                    lst_AdListID.get(index), maxWaitTime, FailureHandling.CONTINUE_ON_FAILURE);
    }
}
