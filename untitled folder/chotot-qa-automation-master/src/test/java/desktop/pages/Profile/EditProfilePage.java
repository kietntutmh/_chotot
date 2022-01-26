package desktop.pages.Profile;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Element.selectOptionByVisibleText;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class EditProfilePage extends BasePage {
    Logger log = Log4jFactory.instance().createClassLogger(getClass());

    @FindBy(xpath = "//div[@class='col-xs-12']//input[@name='name']")
    private WebElement input_UserName;

    @FindBy(xpath = "//div[@class='col-xs-12']//input[@name='email']")
    private WebElement input_Email;

    @FindBy(xpath = "//div[@class='col-xs-12']//input[@name='address']")
    private WebElement input_Address;

    @FindBy(xpath = "//div[@class='col-xs-12']//select[@name='gender']")
    private WebElement select_Gender;

    @FindBy(xpath = "//div[@class='col-xs-12']//input[@name='favorite']")
    private WebElement input_Favorite;

    @FindBy(xpath = "//div[@class='col-xs-12']//input[@name='birthdayPicker']")
    private WebElement select_DayOfBirth;

    @FindBy(xpath = "//div[@class='col-xs-12']//button[@type='submit']")
    private WebElement btn_SaveInfo;

    public void inputUserName(String userName) {
        waitForElementVisible(input_UserName, maxWaitTime);
        setText(input_UserName, userName);
    }

    public void chooseGender(String gender) {
        selectOptionByVisibleText(select_Gender, gender);
    }

    public void inputEmail(String email) {
        waitForElementVisible(input_Email, maxWaitTime);
        setText(input_Email, email);
    }

    public void inputAddress(String address) {
        waitForElementVisible(input_Address, maxWaitTime);
        setText(input_Address, address);
    }

    public void chooseDayOfBirth(int day, int month, int year) {
    }
}
