package desktop.pages.InsertAds;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import desktop.components.Popup;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Date;
import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.helper.DateTime.subtractDateTimeToString;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Wait.*;
import static desktop.configuration.RegisterConfig.OTP_VALID;
import static projects.functions.chotot._common.ads.CM_Ads.startTimeInsert;

public class AdsGroup extends BasePage {

    final Logger log = Log4jFactory.instance().createClassLogger(getClass());
    public long endTimeInsert;
    @FindBy(xpath = "//a[contains(@class,'list-group-item')]")
    private List<WebElement> lst_AdGroup;

    @FindBy(xpath = "//a[contains(@class,'list-group-item')]")
    private WebElement lbl_FirstAdGroup;

    @FindBy(xpath = "//*[@type='submit'][last()]")
    private WebElement btn_Continue;

    @FindBy(xpath = "//button[text()='ĐĂNG NGAY']")
    private WebElement btn_PostNow;

    @FindBy(xpath = "//button[contains(@class,'btn-close')]")
    private List<WebElement> lst_CloseImage;

    @FindBy(xpath = "//*[@type='button' and text()='XEM & ĐĂNG']")
    private WebElement btn_ViewAndPost;

    @FindBy(xpath = "//input[contains(@class,\"checkbox\")]/following-sibling::label")
    private List<WebElement> lst_Checkbox;

    @FindBy(xpath = "//input[contains(@class,\"checkbox\")]//following-sibling::label")
    private List<WebElement> lst_CheckboxText;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement input_Image;

    @FindBy(
            xpath =
                    "//input[@type='file']/preceding-sibling::div/div[contains(@class,'thumbnail') and contains(@style, 'background-image')]")
    private List<WebElement> lst_Image_Thumbail;

  @FindBy(xpath = "//*[@role=\"progressbar\" and contains(@class,\"progress-bar-danger\")]")
  private WebElement lbl_Image_ProgressBar;

    @FindBy(xpath = "//*[@class='alert alert-danger']")
    private WebElement lbl_AlertMessage;

    @FindBy(xpath = "//*[contains(@class,'input-group')]/input")
    private WebElement input_RequiredField;

    @FindBy(xpath = "//*[contains(@class,'input-group')]/input")
    private List<WebElement> lst_txtField;

    @FindBy(xpath = "//input[@placeholder='Số nhà']")
    private WebElement input_HouseNumber;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement input_Password;

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"VUI LÒNG XÁC NHẬN SỐ ĐIỆN THOẠI\"]")
    private WebElement lbl_VerifyOTP;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement input_OTP;

    @FindBy(xpath = "//input[@name='price_m2']")
    private WebElement input_PriceM2;

    @FindBy(xpath = "//textarea[@id and @name]")
    private WebElement txt_Description;

    @FindBy(xpath = "//*[a and span[contains(text(),'Vui lòng chọn')]]/a")
    private WebElement input_RequiredInfoField;

    @FindBy(xpath = "//*[contains(@class, 'panel panel-default')]")
    private List<WebElement> lst_PanelGroup;

    @FindBy(xpath = "//*[@role='tab']")
    private WebElement lbl_FirtPanelGroup;

    @FindBy(xpath = "//select[contains(@class,'form-control') or @class]")
    private List<WebElement> lst_SelectOption;

    @FindBy(xpath = "//span[@class='pull-left']/button[text()='-']")
    private WebElement btn_NumSub;

    @FindBy(xpath = "//span[@class='pull-left']/button[text()='+']")
    private WebElement btn_NumAdd;

    @FindBy(xpath = "//span[@class='pull-left']/span")
    private WebElement input_NumText;

    @FindBy(xpath = "//button[contains(@class, 'dropdown') and @type='button']")
    private List<WebElement> lst_DropDownList;

    @FindBy(
            xpath = "//button[contains(@class, 'dropdown') and @type='button']/following-sibling::ul//a")
    private List<WebElement> lst_DropDownList_TextValue;

    // Get all radio buttons on current page
    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> lst_RadioButton;

    @FindBy(xpath = "//input[@type='radio']/following-sibling::label")
    private List<WebElement> lst_RadioButtonText;

    @FindBy(xpath = "//input[@type='radio']/..")
    private List<WebElement> lst_RadioButtonDiv;

    // Get all checkboxes on current page
    @FindBy(xpath = "//input[@type='checkbox']/following-sibling::label")
    private List<WebElement> lst_CheckboxInput;

    @FindBy(xpath = "//input[@type='checkbox']/following-sibling::label/span")
    private List<WebElement> lst_CheckboxInputText;

    @FindBy(xpath = "//div[@class='Select-input' or starts-with(@class,'Select')]/input")
    private WebElement input_SelectSuggestion;

    @FindBy(xpath = "//div[@class='Select-value']/span")
    private List<WebElement> lst_SelectSuggestionInputValue;

    @FindBy(xpath = "//div[@class='Select-menu-outer']//span")
    private List<WebElement> lst_SearchSuggestion;

    @FindBy(
            xpath = "//div[contains(@class,'input')]/input[contains(@class, 'form-control') or @class]")
    private List<WebElement> lst_TextFields;

    @FindBy(xpath = "//*[contains(@class,\"alert alert-danger\")]")
    private WebElement lbl_ErrorMessage;

    @FindBy(xpath = "//*[contains(@class,'input-group')]/input")
    private List<WebElement> lst_RequiredField;

    public void selectPanelByName(String panelName) {
        // Wait for page load
        waitForAllElementVisible(lst_PanelGroup, maxWaitTime);
        waitForElementStaleness(lbl_FirtPanelGroup, minWaitTime);

        // Select panel
        log.info("\nSelect panel: " + panelName);
        clickElementOnListByName(lst_PanelGroup, panelName, true, FailureHandling.STOP_ON_FAILURE);

        // Wait for page reload
        if (!waitForElementStaleness(lbl_FirtPanelGroup, minWaitTime))
            waitForElementStaleness(lbl_FirtPanelGroup, minWaitTime);
    }

    public void selectGroupByAttribute(String groupName, String attributeName) {
        // Wait for page load
        waitForAllElementVisible(lst_AdGroup, maxWaitTime);
        waitForElementStaleness(lbl_FirstAdGroup, maxWaitTime);

        // Select group
        log.info("\nSelect group: " + groupName);
        clickJSElementOnListByAttribute(
                lst_AdGroup, attributeName, groupName, false, FailureHandling.STOP_ON_FAILURE);

        // Wait for page reload
        waitForElementStaleness(lbl_FirstAdGroup, maxWaitTime);
    }

    public void selectCheckboxValue(String value) {
        // Select checkbox by value
        String textValue;
        lst_Checkbox.clear();
        waitForAllElementVisible(lst_Checkbox, maxWaitTime);
        for (int i=0; i< lst_Checkbox.size(); i++) {
            try {
                textValue = getPropertyValueByJS(lst_Checkbox.get(i), "nextElementSibling.innerText");
            } catch (JavascriptException ex) {
                textValue = getPropertyValueByJS(lst_Checkbox.get(i), "innerText");
            }
            if (textValue.equals(value)) {
                waitForElementStaleness(lst_Checkbox.get(i), maxWaitTime);
                selectCheckBoxJS(lst_Checkbox.get(i));
                log.info("\nSelect checkbox: " + value);
                return;
            }
        }
        // Step failed immediately if checkbox isn't exist
        exceptionHandler(new StepFailedException("No existing checkbox value: " + value), FailureHandling.STOP_ON_FAILURE);
    }

    public void selectCheckboxByText(String text) {
        //new Popup().closePopup(minWaitTime);
        waitForAllElementVisible(lst_CheckboxInput, maxWaitTime);
        waitForElementStaleness(lst_CheckboxInput.get(0), minWaitTime);
        for (int index = 0; index < lst_CheckboxInput.size(); index++) {
            if (lst_CheckboxInputText.get(index).getText().equals(text)) {
                WebElement element = lst_CheckboxInput.get(index);
                if (!Boolean.parseBoolean(element.getAttribute("checked"))) clickJS(element);
                break;
            }
        }
    }

    public void uploadMultiImagesToAds(String filePaths) {
        // Check alert message
        waitForElementVisible(input_Image, maxWaitTime);
        uploadImage(input_Image, "");

        // Wait for warning message displayed
        waitForElementVisible(lbl_AlertMessage, maxWaitTime * 2);

        // Upload multiple image
        String[] filePathArr;
        if (filePaths.contains(",")) filePathArr = filePaths.split(",");
        else {
            filePathArr = new String[1];
            filePathArr[0] = filePaths;
        }

        for (String s : filePathArr) {
            String imagePath = s.trim();
            log.info("\nUpload image: " + imagePath);
            uploadImage(input_Image, imagePath);
            waitForElementStaleness(lbl_Image_ProgressBar, maxWaitTime);
        }

        // Wait for complete loading
        waitForElementNotVisible(lbl_AlertMessage, maxWaitTime * 2);

        // Click continue
        clickContinue();
    }

    public void enterRequiredData(String data) {
        new Popup().closePopup(minWaitTime);
        log.info("\nEnter required data: " + data);
        waitForElementVisible(input_RequiredField, maxWaitTime);
        waitForElementStaleness(input_RequiredField, minWaitTime);
        setText(input_RequiredField, data);
        clickContinue();

        // Wait for page reload
        waitForElementStaleness(input_RequiredField, maxWaitTime);
    }

    //A group of Required Input
    public void enterRequiredDataList(String data, int index) {
        new Popup().closePopup(minWaitTime);
        log.info("\nEnter required data: " + data);
        waitForAllElementVisible(lst_RequiredField, maxWaitTime);
        waitForElementStaleness(lst_RequiredField.get(index), minWaitTime);
        setText(lst_RequiredField.get(index), data);
    }

    @Deprecated  //->enterRequiredDataList
    public void enterNonRequiredDataList(String data, int index) {
        new Popup().closePopup(minWaitTime);
        log.info("\nEnter non required data: " + data);
        waitForAllElementVisible(lst_txtField, maxWaitTime);
        waitForElementStaleness(lst_txtField.get(index), minWaitTime);
        setText(lst_txtField.get(index), data);
    }

    @Deprecated     // -> enterRequireDataList
    public void enterRequireDataHaveIndex(String data, int index) {
        new Popup().closePopup(minWaitTime);
        log.info("\nEnter required data have index: " + data);
        waitForAllElementVisible(lst_TextFields, maxWaitTime);
        waitForElementStaleness(lst_TextFields.get(index), minWaitTime);
        setText(lst_TextFields.get(index), data);
    }

    public void enterOptionalData(String data, int index) {
        new Popup().closePopup(minWaitTime);
        log.info("\nEnter Optional data: " + data);
        waitForAllElementVisible(lst_txtField, minWaitTime);
        waitForElementStaleness(lst_txtField.get(index), minWaitTime);
        setText(lst_txtField.get(index), data);
    }

    public void enterPhoneAndPassword(String phone, String password) {
        // Enter phone
        log.info("Login with phone number: " + phone);
        //new Popup().closePopup(minWaitTime);
        waitForElementStaleness(input_RequiredField, maxWaitTime);
        setText(input_RequiredField, phone);
        waitForElementStaleness(input_RequiredField, minWaitTime);
        clickJS(btn_Continue);
        waitForElementStaleness(btn_Continue, minWaitTime);

        // Enter password
        //new Popup().closePopup(minWaitTime);
        waitForElementStaleness(input_Password, minWaitTime);
        setText(input_Password, password);
        waitForElementStaleness(input_Password, minWaitTime);
        clickJS(btn_Continue);
        waitForElementStaleness(btn_Continue, maxWaitTime);

        // Enter OTP if needed
        //new Popup().closePopup(minWaitTime);
        enterOTP();

        // Wait for page load
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 2);
    }

    public void enterOTP() {
        if (waitForElementVisible(lbl_VerifyOTP, minWaitTime)) {
            setText(input_OTP, OTP_VALID);
            clickJS(btn_Continue);
        }
    }

    public void enterDescription(String description) {
        log.info("\nEnter description: " + description);
        waitForElementVisible(txt_Description, maxWaitTime);
        setText(txt_Description, description);
        clickContinue();

        // Wait for page reload
        waitForElementStaleness(txt_Description, minWaitTime);
    }

    public void clickViewAndPost() {
        if (waitForElementVisible(btn_ViewAndPost, minWaitTime)) {
            moveAndClickJS(btn_ViewAndPost);
            verifyElementNotVisible(lbl_ErrorMessage, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
            waitForLoadingPageIconDismissed(minWaitTime, maxWaitTime);
        }
    }

    public void clickPostNow() {
        // Click Post
        waitForElementVisible(btn_PostNow, maxWaitTime);
        clickJS(btn_PostNow);

        // Wait for page load
        waitForLoadingPageIconDismissed(maxWaitTime * 2, maxWaitTime * 6);

        // Get timestamp
        endTimeInsert = System.currentTimeMillis();

        // Log execution time for insert ad
        String executionTime =
                subtractDateTimeToString(new Date(endTimeInsert), new Date(startTimeInsert), "mm:ss");
        log.info("\n----- Total execution time (mm:ss) for insert new ad: " + executionTime);
    }

    public void clickContinue() {
        waitForElementVisible(btn_Continue, maxWaitTime);
        clickJS(btn_Continue);
        waitForElementStaleness(btn_Continue, maxWaitTime);
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime * 5);
    }

    public void selectOptionByText(String data, int index) {
        log.info("\nOption value: " + data);
        lst_SelectOption.clear();
        waitForAllElementVisible(lst_SelectOption, maxWaitTime);
        waitForElementVisible(lst_SelectOption.get(index), minWaitTime);
        selectOptionByVisibleText(lst_SelectOption.get(index), data);
        waitForElementStaleness(lst_SelectOption.get(index), minWaitTime);
    }

    public void selectDropdownListByText(String data, int index) {
        log.info("\nSelect option text of dropdown list: " + data);
        waitForAllElementVisible(lst_DropDownList, minWaitTime);
        waitForElementStaleness(lst_DropDownList.get(index), maxWaitTime);
        selectDropdownButtonByText(lst_DropDownList.get(index), lst_DropDownList_TextValue, data);
    }

    public void addSubNumber(String data) {
        int actualNum = Integer.parseInt(getText(input_NumText, maxWaitTime));
        int expectNum = Integer.parseInt(data.trim());
        int count;
        boolean isAdd = false; // Actual < Expected

        if (actualNum < expectNum) {
            count = expectNum - actualNum;
            isAdd = true;
        } else if (actualNum > expectNum) {
            count = actualNum - expectNum;
        } else count = 0;

        while (count > 0) {
            if (isAdd) moveAndClickJS(btn_NumAdd);
            else moveAndClickJS(btn_NumSub);
            count--;
        }
    }

    public void enterHouseNumber(String data) {
        log.info("Enter house number input: " + data);
        waitForElementVisible(input_HouseNumber, minWaitTime);
        setText(input_HouseNumber, data);
        clickContinue();

        // Wait for page reload
        waitForElementStaleness(input_HouseNumber, minWaitTime);
    }

    public void enterSelectSuggestionInput(String data, int indexOfSuggestion) {
        log.info("Enter suggestion input: " + data);
        new Popup().closePopup(minWaitTime);

        // Enter data
        waitForElementVisible(input_SelectSuggestion, maxWaitTime);
        setText(input_SelectSuggestion, data);

        // Wait and click on suggestion
        waitForListElementChangedByNumber(lst_SearchSuggestion, 0, maxWaitTime * 3);

        // Select suggestion by index
        selectElementOnListByIndex(lst_SearchSuggestion, indexOfSuggestion, FailureHandling.CONTINUE_ON_FAILURE);
    }
}
