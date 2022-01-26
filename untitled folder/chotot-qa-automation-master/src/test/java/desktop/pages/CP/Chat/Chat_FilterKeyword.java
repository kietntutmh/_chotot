package desktop.pages.CP.Chat;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;


public class Chat_FilterKeyword extends BasePage {
    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//a[@class='btn-action' and @data-modal='f_keyAddData']")
    private WebElement btn_AddKeyword;

    @FindBy(xpath = "//div[@class='ibox-content']//div[@class='feed-element']")
    private List<WebElement> lst_Keyword;

    @FindBy(xpath = "//div[@class='col-md-6'][1]//a[@class='close-link' and @id='remove']")
    private List<WebElement> lst_DeleteKeyword;

    @FindBy(xpath = "//div[@class='col-md-6'][1]//a[@id='removeall']")
    private WebElement btn_RemoveAllKeyword;

    @FindBy(xpath = "//input[@data-name='text']")
    private WebElement input_Keyword;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement btn_Submit;

    public void deleteKeyword(int index) {
        waitForAllElementVisible(lst_DeleteKeyword, maxWaitTime);
        clickJS(lst_DeleteKeyword.get(index), maxWaitTime);
    }

    public void clickAddKeyword() {
        waitForElementVisible(btn_AddKeyword, maxWaitTime);
        moveAndClick(btn_AddKeyword);
    }

    public void clickDeleteAllKeyword() {
        waitForElementVisible(btn_RemoveAllKeyword, maxWaitTime);
        moveAndClick(btn_RemoveAllKeyword, maxWaitTime);
    }

    public void enterKeyword(String keyword) {
        waitForElementVisible(input_Keyword, maxWaitTime);
        setText(input_Keyword, keyword);
    }

    public void addNewKeyword(String keyword) {
        clickAddKeyword();
        enterKeyword(keyword);
        moveAndClick(btn_Submit);
    }

    public List<String> getAllKeyword() {
        waitForAllElementVisible(lst_Keyword, maxWaitTime);
        List<String> keywordText = new ArrayList<>();

        for (int i = 0; i < lst_Keyword.size(); i++) {
            keywordText.add(getText(lst_Keyword.get(i), maxWaitTime));
        }

        return keywordText;
    }
}