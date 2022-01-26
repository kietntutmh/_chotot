package desktop.pages.CP;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.keywords.selenium.Element.clickElementOnListByName;

public class CP_HomePage extends BasePage {

    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//ul[@id=\"side-menu\"]/li/a")
    private List<WebElement> lst_LeftParentMenuItem;

    @FindBy(xpath = "//ul[@id=\"side-menu\"]/li/ul[@aria-expanded=\"true\"]/li/a")
    private List<WebElement> lst_LefChildtMenuItem;

    public void selectLeftMenu(String parentItem, String childItem) {
        clickElementOnListByName(
                lst_LeftParentMenuItem, parentItem, false, FailureHandling.STOP_ON_FAILURE);
        clickElementOnListByName(
                lst_LefChildtMenuItem, childItem, false, FailureHandling.STOP_ON_FAILURE);
    }
}
