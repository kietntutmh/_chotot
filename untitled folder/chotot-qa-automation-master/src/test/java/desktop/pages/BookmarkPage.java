package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import desktop.configuration.SaveConfig;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.getPageTitile;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;

public class BookmarkPage extends BasePage {
    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//img[@alt='Xoá']")
    private List<WebElement> lst_RemoveBookmark;

    @FindBy(xpath = "//a[contains(@class,'SaveSearchItem__A')]")
    private List<WebElement> lst_BookmarkedTitle;

    @FindBy(xpath = "//span[contains(@class,'SaveSearchItem__NotificationText')]")
    private List<WebElement> lst_Notification;

    @FindBy(xpath = "//div[@class='react-switch-handle']")
    private List<WebElement> lst_TurnOnOffNotification;

    @FindBy(xpath = "//h1[contains(@class, 'BreadCrumb__Title')]")
    private WebElement txt_Bookmark;

    @FindBy(xpath = "//a[contains(@class, 'NotFound__PrimaryLink')]")
    private WebElement btn_StartSearch;

    public void remoteAllBookmarked() {
        int totalSearchSaved = lst_RemoveBookmark.size();
        if (totalSearchSaved > 0) {
            for (int i = totalSearchSaved - 1; i >= 0; i--) {
                moveAndClickJS(lst_RemoveBookmark.get(i));
            }
        }
    }

    public void removeBookmarkedItem(int index) {
        if (lst_RemoveBookmark.size() == 0) {
            exceptionHandler(
                    new StepFailedException("Saved search = 0"), FailureHandling.STOP_ON_FAILURE);
        }
        if (index >= lst_RemoveBookmark.size()) {
            exceptionHandler(
                    new StepFailedException(
                            "Index of item: "
                                    + index
                                    + "is OVER total Saved search: "
                                    + lst_RemoveBookmark.size()
                                    + 1),
                    FailureHandling.STOP_ON_FAILURE);
        }
        moveAndClickJS(lst_RemoveBookmark.get(index));
    }

    public int getTotalBookmark() {
        if (waitForAllElementVisible(lst_BookmarkedTitle, maxWaitTime))
            return lst_BookmarkedTitle.size();
        else return 0;
    }

    public void verifyUserCanBookmarkNew() {
        if (getTotalBookmark() == SaveConfig.totalSearchCanSave) {
            for (int i = lst_RemoveBookmark.size(); i >= 1; i--) {
                moveAndClickJS(lst_RemoveBookmark.get(i - 1));
            }
        }
    }

    public String getTitleBookmark(int index) {
        verifyElementVisible(lst_BookmarkedTitle.get(index), maxWaitTime * 5);

        return lst_BookmarkedTitle.get(index).getText();
    }

    public void turnOnNotification(int index) {
        verifyElementVisible(lst_TurnOnOffNotification.get(index), maxWaitTime);
        if (lst_TurnOnOffNotification.get(index).getAttribute("aria-checked").contains("true")) {
            exceptionHandler(
                    new StepFailedException("Notification of saved search is TURN ON now"),
                    FailureHandling.STOP_ON_FAILURE);
        }
        moveAndClick(lst_TurnOnOffNotification.get(index));
    }

    public void turnOffNotification(int index) {
        verifyElementVisible(lst_TurnOnOffNotification.get(index), maxWaitTime);
        if (lst_TurnOnOffNotification.get(index).getAttribute("aria-checked").contains("false")) {
            exceptionHandler(
                    new StepFailedException("Notification of saved search is TURN ON now"),
                    FailureHandling.STOP_ON_FAILURE);
        }
        moveAndClick(lst_TurnOnOffNotification.get(index));
    }

    public boolean verifyTotalBookmark() {
        boolean iValue = true;
        if (lst_BookmarkedTitle.size() > SaveConfig.totalSearchCanSave) {
            iValue = false;
            exceptionHandler(
                    new StepFailedException(
                            "Actual Saved search "
                                    + lst_BookmarkedTitle.size()
                                    + " is OVER expect Saved search "
                                    + SaveConfig.totalSearchCanSave),
                    FailureHandling.STOP_ON_FAILURE);
        }
        return iValue;
    }

    public String verifyNotificationBookmark(int indexElement, boolean haveNotification) {
        String value;
        if (haveNotification) {
            verifyElementVisible(
                    lst_Notification.get(indexElement), maxWaitTime, FailureHandling.STOP_ON_FAILURE);
            value = lst_Notification.get(indexElement).getText();
        } else {
            if (getNumberElement(lst_Notification) > 0) {
                verifyElementNotPresent(
                        lst_Notification.get(indexElement), minWaitTime, FailureHandling.STOP_ON_FAILURE);
            }
            value = null;
        }
        return value;
    }

    public String goToRandomBookmarked() {
        verifyAllElementVisible(lst_BookmarkedTitle, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
        clickJSRandomElementOnList(lst_BookmarkedTitle);
        return getPageTitile(maxWaitTime * 5);
    }

    public void clickStartSearch() {
        verifyElementVisible(btn_StartSearch, maxWaitTime);
        clickJS(btn_StartSearch);
    }
}
