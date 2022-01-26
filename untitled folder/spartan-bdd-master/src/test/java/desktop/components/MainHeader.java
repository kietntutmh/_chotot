package desktop.components;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.helper.StringHelper.removeAccent;
import static com.vn.chotot.keywords.selenium.Action.getListText;
import static com.vn.chotot.keywords.selenium.Action.getText;
import static com.vn.chotot.keywords.selenium.Element.verifyAllElementVisible;
import static com.vn.chotot.keywords.selenium.Element.verifyElementVisible;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class MainHeader extends BasePage {

    @FindBy(
            xpath =
                    "//ol[@itemscope or contains(@class,'breadcrumb') or @typeof='BreadcrumbList']//li[a or span]")
    private List<WebElement> lst_Breadcrumb;

    @FindBy(xpath = "//h1[text()]")
    private WebElement lbl_MainContent;

    public void verifyBreabcrumbItem(String expectedBreadcrumb) {
        if (waitForAllElementVisible(lst_Breadcrumb, maxWaitTime)) {
            boolean isDisplayed = false;
            List<String> listBreadcrumb = getListText(lst_Breadcrumb);
            for (String s : listBreadcrumb) {
                if (s.equalsIgnoreCase(expectedBreadcrumb)) {
                    isDisplayed = true;
                    break;
                }
            }
            verifyEquals(isDisplayed, true, FailureHandling.CONTINUE_ON_FAILURE);
        } else {
            exceptionHandler(
                    new NoSuchElementException(
                            "\nNot found expected breadcrumb: \"" + expectedBreadcrumb + "\""),
                    FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public void verifyBreabcrumbItemsDisplayed() {
        verifyAllElementVisible(lst_Breadcrumb, maxWaitTime * 2, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyMainContentDisplayed() {
        verifyElementVisible(lbl_MainContent, maxWaitTime * 5, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyMainHeaderContent(String expectedContent) {
        if (waitForElementVisible(lbl_MainContent, maxWaitTime)) {
            String mainHeaderContent = getText(lbl_MainContent, minWaitTime).toLowerCase();
            verifyMatch(
                    mainHeaderContent,
                    ".*" + expectedContent + ".*",
                    true,
                    FailureHandling.CONTINUE_ON_FAILURE);
        } else {
            exceptionHandler(
                    new NoSuchElementException(
                            "\nNot found main header for expected content with accent: \""
                                    + expectedContent
                                    + "\""),
                    FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public void verifyMainHeaderContentWithoutAccent(String expectedContent) {
        if (waitForElementVisible(lbl_MainContent, maxWaitTime)) {
            String mainHeaderContent = getText(lbl_MainContent, minWaitTime).toLowerCase();
            // Remove all special characters with empty strings
            mainHeaderContent = mainHeaderContent.replace(",", "").replace(" - ", " ");
            mainHeaderContent = removeAccent(mainHeaderContent); // Remove accent in main header
            verifyMatch(
                    mainHeaderContent,
                    ".*" + expectedContent + ".*",
                    true,
                    FailureHandling.CONTINUE_ON_FAILURE);
        } else {
            exceptionHandler(
                    new NoSuchElementException(
                            "\nNot found main header for expected content without accent: \""
                                    + expectedContent
                                    + "\""),
                    FailureHandling.CONTINUE_ON_FAILURE);
        }
    }
}
