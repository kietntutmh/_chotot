package desktop.pages.AdsView;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.ChatPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class AdView extends BasePage {

    @FindBy(xpath = "//h1[@title]")
    private WebElement lbl_AdSubject;

    @FindBy(xpath = "//ol[@itemscope or @class=\"breadcrumb\"]//li/a")
    private List<WebElement> lst_Breadcrumb;

    @FindBy(xpath = "//*[contains(@class,\"templateItem\") and @role=\"menuitem\"]")
    private List<WebElement> lst_ChatTemplateMessage;

    @FindBy(xpath = "//*[text()=\"Tiếp tục chat\" and @role=\"button\"]")
    private WebElement lnk_ContinueChat;

    @FindBy(xpath = "//a[contains(.,\"CHAT\")]")
    private WebElement lnk_ChatToBuyer;

    @FindBy(xpath = "//h1[@itemprop=\"name\"]")
    private WebElement lbl_AdName;

    public void verifyAdSubjectDisplayed(String adSubject) {
        // Wait for ad item loaded
        for (int i = 1; i <= 40; i++) {
            refreshPage();
            if (waitForElementPresent(lbl_AdSubject, maxWaitTime)) break;
            else delayStep(maxWaitTime * 3);
        }

        // Verify ad subject displayed on ad view page
        String subject = lbl_AdSubject.getText();
        verifyMatch(adSubject, subject, false, FailureHandling.STOP_ON_FAILURE);

        // Go to listing page
        goToListingPage();

        // Verify ad subject displayed on listing page
        new AdsListing().verifyAdSubjectDisplayed(adSubject);
    }

    public void goToListingPage() {
        waitForElementStaleness(lbl_AdSubject, maxWaitTime);
        waitForAllElementVisible(lst_Breadcrumb, maxWaitTime);
        int lastLinkIndex = lst_Breadcrumb.size() - 1;
        clickJS(lst_Breadcrumb.get(lastLinkIndex));
        waitForLoadingIconDismissed(maxWaitTime, maxWaitTime * 2, false);
    }

    public String clickFirstChatTemplateInAdview() {
        scrollPage(lbl_AdName);
        // Get template value
        waitForAllElementPresent(lst_ChatTemplateMessage, maxWaitTime);
        String templateMsg = getText(lst_ChatTemplateMessage.get(0), maxWaitTime);

        clickJS(lst_ChatTemplateMessage.get(0));
        delayStep(maxWaitTime);

        return templateMsg;
    }

    public void clickContinueChat() {
        waitForElementVisible(lnk_ContinueChat, maxWaitTime * 2);
        clickJS(lnk_ContinueChat);
        waitNumberOfWindow(2, maxWaitTime * 2);
        switchToWindowTitle("Chợ Tốt Chat");

        // Wait for chat page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 10);

        // Close Request Modal if displayed
        new ChatPage().closeRequestModelSection();
    }

}
