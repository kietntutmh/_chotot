package desktop.pages.AdsView;

import com.vn.chotot.base.BasePage;
import desktop.pages.ChatPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.clickJS;
import static com.vn.chotot.keywords.selenium.Page.switchToWindowTitle;
import static com.vn.chotot.keywords.selenium.Page.waitNumberOfWindow;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForLoadingPageIconDismissed;

public class ContactPanel extends BasePage {

    @FindBy(xpath = "//a[contains(.,\"CHAT\")]")
    private WebElement lnk_ChatToBuyer;

    public void clickAndSwitchToChatPage() {
        // Wait for page load
        waitForElementVisible(lnk_ChatToBuyer, maxWaitTime * 2);

        // Click chat
        clickJS(lnk_ChatToBuyer);
        waitNumberOfWindow(2, maxWaitTime);
        switchToWindowTitle("Chợ Tốt Chat");

        // Wait for chat page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 2);

        // Close Request Modal if displayed
        new ChatPage().closeRequestModelSection();
    }
}
