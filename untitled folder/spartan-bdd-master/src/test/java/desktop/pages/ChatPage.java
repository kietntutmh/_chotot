package desktop.pages;

import com.vn.chotot.base.BasePage;
import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.exception.FailureHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Action.*;
import static com.vn.chotot.keywords.selenium.Element.*;
import static com.vn.chotot.keywords.selenium.Page.*;
import static com.vn.chotot.keywords.selenium.Utils.*;
import static com.vn.chotot.keywords.selenium.Wait.*;

public class ChatPage extends BasePage {
    // ***** MESSAGE SECTION *****
    @FindBy(xpath = "//button[@id=\"bg-nested-dropdown\"]")
    private WebElement btn_Privacy;

    @FindBy(xpath = "//*[@id=\"ChatNameSection\"]//*[@class=\"NameFeild\"]")
    private WebElement lbl_ChatName;

    @FindBy(xpath = "//*[@id=\"ChatTitleSection\"]")
    private WebElement lbl_ChatTitle;

    @FindBy(xpath = "//a[text()=\"An toàn khi chat\"]")
    private WebElement lnk_SafetyTip;

    @FindBy(xpath = "//ul[@class=\"MessageListContainer\"]//li[@class=\"clearfix\"]")
    private List<WebElement> lst_Message;

    @FindBy(xpath = "//ul[@class=\"MessageListContainer\"]/*[li[@class=\"TimeHistory\"]]")
    private List<WebElement> lst_FirstMessage;

    @FindBy(xpath = "//textarea[@id=\"message\"]")
    private WebElement txa_Message;

    @FindBy(xpath = "//*[@class=\"TemplateMessage\"]//li")
    private List<WebElement> lst_ChatTemplateMessage;

    @FindBy(xpath = "//*[@type=\"submit\" and contains(@id,\"button\")]")
    private WebElement btn_SendMessage;

    @FindBy(xpath = "//input[@id=\"file\"]")
    private WebElement input_Image;

    @FindBy(xpath = "//*[@class=\"alert alert-danger\"]/span")
    private WebElement lbl_AlertMessage;

    @FindBy(xpath = "//*[@class=\"loadingLarge\"]")
    private WebElement lbl_LoadingImage;

    @FindBy(xpath = "//*[@class=\"EmptyChatRoom\"]")
    private WebElement lbl_EmptyChat;

    @FindBy(xpath = "//*[@id=\"bg-nested-dropdown\"]")
    private WebElement btn_MenuMore;

    @FindBy(xpath = "//*[@role=\"menuitem\" and contains(.,\"Chặn\")]")
    private WebElement lnk_BlockUser;

    @FindBy(xpath = "//*[@role=\"menuitem\" and contains(.,\"chặn\")]")
    private WebElement lnk_UnblockUser;

    @FindBy(xpath = "//*[@class=\"ResendText\"]")
    private WebElement lbl_RetrySendMessage;

    @FindBy(xpath = "//button[text()=\"Xác nhận\"]")
    private WebElement btn_Confirm;

    @FindBy(xpath = "//button[text()=\"Hủy\"]")
    private WebElement btn_Cancel;

    @FindBy(xpath = "//*[@role=\"menuitem\" and contains(.,\"Báo\")]")
    private WebElement lnk_ReportUser;

    @FindBy(xpath = "//button[contains(text(),\"lừa đảo\")]")
    private WebElement btn_ReportFraud;

    @FindBy(xpath = "//button[contains(text(),\"spam\")]")
    private WebElement btn_ReportSpam;

    @FindBy(xpath = "//*[text()=\"Cám ơn\"]")
    private WebElement lbl_ReportUserSuccedd;

    @FindBy(xpath = "//*[@role=\"menuitem\" and contains(.,\"Xoá\")]")
    private WebElement lnk_RemoveChat;

    @FindBy(xpath = "//*[contains(@class,\"CalendarButton\")]")
    private WebElement btn_Appointment;

    @FindBy(xpath = "//*[contains(@class,\"BargainButton\")]")
    private WebElement btn_Offer;

    @FindBy(xpath = "//input[@class=\"inputPayment\"]")
    private WebElement input_Offer;

    @FindBy(xpath = "//img[@alt=\"time-ico\"]")
    private WebElement img_Appointment_Time;

    @FindBy(xpath = "//*[contains(@class,\"selected react-datepicker\")]/following-sibling::*")
    private WebElement lbl_Appointment_Time_NextDay;

    @FindBy(xpath = "//*[contains(@class,\"time-list\")]/*[contains(@class,\"time-list-item\")]")
    private List<WebElement> lst_TimeItem;

    @FindBy(xpath = "//img[@alt=\"location-ico\"]/following-sibling::*")
    private WebElement img_Appointment_Location;

    @FindBy(xpath = "//input[@class=\"form-control\"]")
    private WebElement input_Location;

    @FindBy(xpath = "//*[contains(@class,\"suggestion\")]//*[contains(@class,\"desc\")]")
    private List<WebElement> lst_LocationSuggestion;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btn_Save_Location;

    @FindBy(xpath = "//textarea[contains(@class,\"notiForm\")]")
    private WebElement input_Appointment_Note;

    @FindBy(xpath = "//button[text()=\"Xong\"]")
    private WebElement btn_Save_Appointment;

    @FindBy(xpath = "//*[@class=\"MessageLeftWrapper\"]")
    private WebElement lbl_Appointment_Details;

    // ***** THREAD SECTION *****
    @FindBy(
            xpath =
                    "//li[contains(@class,\"ThreadItem\")]//*[@class=\"Link-Message\" and .//img[contains(@class,\"UserAvatar\")]]")
    private List<WebElement> lst_ThreadMessage;

    @FindBy(xpath = "//*[contains(@class,\"ChatMenu\")]/li[@id=\"tab-all\"]/a")
    private WebElement lnk_TabAll;

    @FindBy(xpath = "//*[contains(@class,\"ChatMenu\")]/li[@id=\"tab-buy\"]/a")
    private WebElement lnk_TabBuy;

    @FindBy(xpath = "//*[contains(@class,\"ChatMenu\")]/li[@id=\"tab-sell\"]/a")
    private WebElement lnk_TabSell;

    @FindBy(xpath = "//img[contains(@class,\"UserAvatar\")]")
    private List<WebElement> lst_UserAvatar;

    @FindBy(xpath = "//*[@class=\"userName\"]")
    private List<WebElement> lst_UserName;

    @FindBy(xpath = "//*[@class=\"UpdateTime\"]")
    private List<WebElement> lst_UpdateTime;

    @FindBy(xpath = "//*[@class=\"ProductName\"]")
    private List<WebElement> lst_ProductName;

    @FindBy(xpath = "//*[@class=\"ProductImageDiv\"]")
    private List<WebElement> lst_ProducImage;

    @FindBy(xpath = "//input[@class=\"threadCheckBox checkbox\"]")
    private List<WebElement> lst_CheckboxThread;

    @FindBy(xpath = "//button[contains(.,\" Xóa cuộc trò chuyện\")]")
    private WebElement btn_RemoveThread;

    @FindBy(xpath = "//button[text()=\" Xóa\"]")
    private WebElement btn_Remove;

    @FindBy(xpath = "//button[text()=\"Xác nhận\"]")
    private WebElement btn_ConfirmRemove;

    @FindBy(xpath = "//*[@class=\"RequestModal\"]//button[text()=\"Đóng\"]")
    private WebElement btn_CloseRequestModel;

    public void closeRequestModelSection() {
        if (waitForElementVisible(btn_CloseRequestModel, maxWaitTime))
            moveAndClickJS(btn_CloseRequestModel);
    }

    public boolean checkAlertMessageDisplayed() {
        return waitForTextPresentInElement(
                lbl_AlertMessage,
                "Bạn hoặc Người nhận không thể Chat vì vi phạm qui định của Chợ Tốt.",
                maxWaitTime);
    }

    public void verifyUIElements() {
        // Check ui elements
        if (checkAlertMessageDisplayed()) {
            // Chat name
            waitForElementStaleness(lbl_ChatName, minWaitTime);
            verifyElementVisible(lbl_ChatName, maxWaitTime * 3);

            // Chat title: content, thumbnail, price
            verifyElementVisible(lbl_ChatTitle, maxWaitTime);

            // Privacy button
            verifyElementVisible(btn_Privacy, maxWaitTime);

            // Input message section and Send button
            verifyElementVisible(txa_Message, maxWaitTime);
            verifyElementVisible(btn_SendMessage, maxWaitTime);

            // Safety tip
            if (lst_Message.size() > 0) verifyElementVisible(lnk_SafetyTip, maxWaitTime);
        }
    }

    public void verifyMainUIElements() {
        // Chat name
        waitForElementStaleness(lbl_ChatName, minWaitTime);
        verifyElementVisible(lbl_ChatName, maxWaitTime * 3);

        // Chat title: content, thumbnail, price
        verifyElementVisible(lbl_ChatTitle, maxWaitTime);

        // Privacy button
        verifyElementVisible(btn_Privacy, maxWaitTime);

        // Input message section and Send button
        verifyElementVisible(txa_Message, maxWaitTime);
        verifyElementVisible(btn_SendMessage, maxWaitTime);

        // Safety tip
        if (lst_Message.size() > 0) verifyElementVisible(lnk_SafetyTip, maxWaitTime);
    }

    public void verifyUIElementsForEachChatRoom() {
        // Wait for page load
        waitForAllElementVisible(lst_ThreadMessage, maxWaitTime);

        // Check format for each chat room
        if (!lst_ThreadMessage.isEmpty()) {
            for (int i = 0; i < lst_ThreadMessage.size(); i++) {
                // Move to chat thread
                moveAndClickJS(lst_ThreadMessage.get(i));

                // Verify UI elements
                verifyMainUIElements();
            }
        }
    }

    public void verifyFormatOfListChatRoom() {
        // Wait for page load
        waitForAllElementVisible(lst_ThreadMessage, maxWaitTime);

        // Check format for each chat room
        if (!lst_ThreadMessage.isEmpty()) {
            // Verify update time is ascending
            verifyOrderListDateTime(lst_UpdateTime, true, FailureHandling.CONTINUE_ON_FAILURE);

            // Check format for each chat room
            for (int i = 0; i < lst_ThreadMessage.size(); i++) {
                // Move to chat thread
                moveAndClickJS(lst_ThreadMessage.get(i));

                // Avatar, username, time, title, content, product image
                verifyElementVisible(lst_UserAvatar.get(i), maxWaitTime);
                verifyElementVisible(lst_UserName.get(i), maxWaitTime);
                verifyElementVisible(lst_UpdateTime.get(i), maxWaitTime);
                verifyElementVisible(lst_ProductName.get(i), maxWaitTime);
                verifyElementVisible(lst_ProducImage.get(i), maxWaitTime);
            }
        }
    }

    public void verifyFormatOfChatRoomIBuy() {
        // Click IBuy tab
        moveAndClickJS(lnk_TabBuy);

        // Wait for page load
        waitForAllElementVisible(lst_ThreadMessage, maxWaitTime);

        // Check format for each chat room
        if (!lst_ThreadMessage.isEmpty()) {
            // Wait for page load
            waitForElementStaleness(lst_UpdateTime.get(0), minWaitTime);

            // Verify update time is ascending
            verifyOrderListDateTime(lst_UpdateTime, true, FailureHandling.CONTINUE_ON_FAILURE);

            // Check format for each chat room
            for (int i = 0; i < lst_ThreadMessage.size(); i++) {
                // Move to chat thread
                moveAndClickJS(lst_ThreadMessage.get(i));

                // Avatar, username, time, title, content, product image
                verifyElementVisible(lst_UserAvatar.get(i), maxWaitTime);
                verifyElementVisible(lst_UserName.get(i), maxWaitTime);
                verifyElementVisible(lst_UpdateTime.get(i), maxWaitTime);
                verifyElementVisible(lst_ProductName.get(i), maxWaitTime);
                verifyElementVisible(lst_ProducImage.get(i), maxWaitTime);
            }
        }
    }

    public void verifyFormatOfChatRoomISell() {
        // Click ISell tab
        moveAndClickJS(lnk_TabSell);

        // Wait for page load
        waitForAllElementVisible(lst_ThreadMessage, maxWaitTime);

        // Check format for each chat room
        if (!lst_ThreadMessage.isEmpty()) {
            waitForElementStaleness(lst_ThreadMessage.get(0), minWaitTime);
            for (int i = 0; i < lst_ThreadMessage.size(); i++) {
                // Move to chat thread
                moveAndClickJS(lst_ThreadMessage.get(i));

                // Avatar, username, time, title, content, product image
                verifyElementVisible(lst_UserAvatar.get(i), maxWaitTime);
                verifyElementVisible(lst_UserName.get(i), maxWaitTime);
                verifyElementVisible(lst_UpdateTime.get(i), maxWaitTime);
                verifyElementVisible(lst_ProductName.get(i), maxWaitTime);
                verifyElementVisible(lst_ProducImage.get(i), maxWaitTime);
            }
        }
    }

    public void verifyUserCannotSendBlankMessage() {
        // Click ISell tab
        moveAndClickJS(lnk_TabBuy);

        // Select the first chat room
        waitForElementStaleness(lst_ThreadMessage.get(0), minWaitTime);
        clickRandomElementOnList(lst_ThreadMessage);

        // Get current number of message
        waitForAllElementVisible(lst_Message, maxWaitTime);
        int previous = lst_Message.size();

        // Send blank message
        moveAndClickJS(btn_SendMessage);

        // Verify message does not send
        waitForAllElementVisible(lst_Message, maxWaitTime);
        verifyEquals(lst_Message.size(), previous, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyUserCanSendSingleCharacter() {
        // Send text message
        String textMessage = "1";
        sendTextMessage(textMessage);

        // Get last message
        waitForAllElementVisible(lst_Message, minWaitTime);
        verifyLastMessageInChat(textMessage);
    }

    public void verifyUserCanSendMore100Characters() {
        // Send text message
        String textMessage =
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        sendTextMessage(textMessage);

        // Get last message
        waitForAllElementVisible(lst_Message, minWaitTime);
        verifyLastMessageInChat(textMessage);
    }

    public void removeAllChatRoom() {
        // Get existing chat rooms
        int numberChatroom = getNumberElement(lst_CheckboxThread);

        if (numberChatroom > 0) {
            // Click Remove thread button
            moveAndClickJS(btn_RemoveThread);

            // Remove all thread
            for (WebElement e : lst_CheckboxThread) {
                selectCheckBox(e);
            }

            // Click Remove
            moveAndClickJS(btn_Remove);

            // Confirm remove
            moveAndClickJS(btn_ConfirmRemove);
        }
    }

    public void verifyIntroductionChatDisplayed() {
        verifyEquals(lst_ThreadMessage.size(), 0, FailureHandling.CONTINUE_ON_FAILURE);
        verifyElementVisible(lbl_EmptyChat, maxWaitTime);
    }

    //==============================================================================================================================

    private void verifyLastMessageInChat(String expectedMessage) {
        // Get last message
        waitForElementStale(lst_Message.get(0), maxWaitTime);
        List<WebElement> lastElements = DriverFactory.instance().getWebDriver().findElements(By.xpath("//ul[@class=\"MessageListContainer\"]//li[@class=\"clearfix\"]"));
        WebElement lastMessage = lastElements.get(lastElements.size()-1);
        String messageContent = getAttributeValue(lastMessage, "innerText", maxWaitTime);
        String[] messageRow = messageContent.split("\n\n");

        // Verify message info
        verifyMatch(messageRow[0], expectedMessage, false, FailureHandling.CONTINUE_ON_FAILURE);
    }

    public void verifyUserCanSendTextMessage(String textMessage) {
        // Send text message
        sendTextMessage(textMessage);

        // Verify send message
        verifyLastMessageInChat(textMessage);
    }

    public void verifyUserCanSendImage() {
        // Send image
        sendImage();

        // Get last message
        waitForAllElementVisible(lst_Message, minWaitTime);
        WebElement img_el = DriverFactory.instance().getWebDriver().findElement(By.xpath("//*[@class=\"MessageListContainer\"]//*[@class=\"img-thumbnail\"]"));
        verifyElementVisible(img_el, maxWaitTime);
    }

    public void verifyChatOffer(String value) {
        String expectedMessage = "Người mua trả giá 1.000.000 đ";
        verifyLastMessageInChat(expectedMessage);
    }

    public void verifyChatAppointment() {
        verifyElementVisible(lbl_Appointment_Details, maxWaitTime*2);
        verifyElementNotPresent(btn_Appointment, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public void verifyBlockedUser() {
        refreshPage();
        waitForElementVisible(input_Image, maxWaitTime);
        clickChatTemplateInDetail();
        verifyElementVisible(lbl_RetrySendMessage, maxWaitTime);
    }

    public void verifyUnblockedUser() {
        refreshPage();
        sendTextMessage("new chat message");
        verifyElementNotVisible(lbl_RetrySendMessage, maxWaitTime);
    }

    public String clickChatTemplateInDetail(){
        clickAction(lst_ChatTemplateMessage.get(0));
        return getText(lst_ChatTemplateMessage.get(0), maxWaitTime);
    }

    public void sendTextMessage(String textMessage) {
        // Select random chat room
        waitForElementStaleness(lst_ThreadMessage.get(0), minWaitTime);
        clickRandomElementOnList(lst_ThreadMessage);

        // Enter text message
        setText(txa_Message, textMessage);

        // Get current number of messages
        int currentNumberMessage = lst_Message.size();

        // Click Send
        waitForElementVisible(btn_SendMessage, maxWaitTime);
        clickAction(btn_SendMessage);

        // Wait for sending message successfully
        waitForListElementChangedByNumber(lst_Message, currentNumberMessage, maxWaitTime);
    }

    public void sendImage() {
        // Select random chat room
        clickRandomElementOnList(lst_ThreadMessage);

        // Upload image
        uploadImage(input_Image, "/images/chat/chat_1.jpeg");
        waitForElementVisible(lbl_LoadingImage, maxWaitTime);
    }

    public void blockUser() {
        clickJS(btn_MenuMore, maxWaitTime);
        clickJS(lnk_BlockUser, maxWaitTime);
        clickJS(btn_Confirm, maxWaitTime);
    }

    public void unblockUser() {
        clickJS(btn_MenuMore, maxWaitTime);
        clickJS(lnk_UnblockUser, maxWaitTime);
        clickJS(btn_Confirm, maxWaitTime);
    }

    public void reportUser() {
        clickJS(btn_MenuMore, maxWaitTime);
        clickJS(lnk_ReportUser, maxWaitTime);
        clickJS(btn_ReportFraud, maxWaitTime);
        //verifyElementVisible(lbl_ReportUserSuccedd, maxWaitTime);
    }

    public void removeChatUser() {
        int beforeRemoving = getNumberElement(lst_ThreadMessage);
        clickJS(btn_MenuMore, maxWaitTime);
        clickJS(lnk_RemoveChat, maxWaitTime);
        clickJS(btn_Confirm, maxWaitTime);
        int afterRemoving = getNumberElement(lst_ThreadMessage);
        verifyEquals(beforeRemoving, afterRemoving+1, FailureHandling.STOP_ON_FAILURE);
    }

    public void sendChatOffer(String value) {
        clickJS(btn_Offer, maxWaitTime);
        waitForElementVisible(input_Offer, maxWaitTime);
        setText(input_Offer, value);
        delayStep(minWaitTime);
        input_Offer.sendKeys(Keys.ENTER);
    }

    public void clickUserProfileInChat() {
        String userName = getAttributeValue(lbl_ChatName, "outerText", maxWaitTime).split("\n\n")[0];
        clickAction(lbl_ChatName);

        // Switch to new window
        String title = "Trang cá nhân của " + userName;
        switchToWindowTitle(title);

        // Wait for chat page loaded
        waitForLoadingPageIconDismissed(maxWaitTime, maxWaitTime);
        verifyPageTitle(title, true, maxWaitTime, FailureHandling.STOP_ON_FAILURE);
    }

    public void createAppointment(String location){
        clickJS(btn_Appointment, maxWaitTime);
        waitForElementVisible(img_Appointment_Time, maxWaitTime*2);
        clickJS(img_Appointment_Time, maxWaitTime);
        waitForElementClickable(lbl_Appointment_Time_NextDay, maxWaitTime);
        clickJS(lbl_Appointment_Time_NextDay, maxWaitTime);
        clickJSRandomElementOnList(lst_TimeItem);
        waitForElementClickable(img_Appointment_Location, maxWaitTime);
        clickJS(img_Appointment_Location);
        waitForElementVisible(input_Location, maxWaitTime);
        clickJS(input_Location, maxWaitTime);
        setText(input_Location, location);
        delayStep(maxWaitTime);
        clickJS(lst_LocationSuggestion.get(0), maxWaitTime);
        delayStep(minWaitTime);
        clickJS(btn_Save_Location, maxWaitTime);
        setText(input_Appointment_Note, "This is a note for the appointment");
        clickJS(btn_Save_Appointment, maxWaitTime);
    }
}
