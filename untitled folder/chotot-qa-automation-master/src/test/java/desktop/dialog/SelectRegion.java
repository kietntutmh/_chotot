package desktop.dialog;

import com.vn.chotot.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.keywords.selenium.Action.moveAndClick;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class SelectRegion extends BasePage {

    @FindBy(xpath = "//*[@class=\"modal-content\"]//button[@class=\"close\"]")
    private WebElement btn_Close;

    public void clickCloseButton(int waitTime) {
        if (waitForElementVisible(btn_Close, waitTime)) moveAndClick(btn_Close);
    }
}
