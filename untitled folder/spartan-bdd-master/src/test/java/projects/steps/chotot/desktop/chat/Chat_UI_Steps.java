package projects.steps.chotot.desktop.chat;

import desktop.pages.AdsView.AdView;
import desktop.pages.AdsView.ContactPanel;
import desktop.pages.ChatPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static api.configuration.DataConfig.tempListID;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class Chat_UI_Steps {
    private String adview_url = "https://www.chotot."+DOMAIN+"/%s.htm";
    private AdView adView = new AdView();
    private ChatPage chatPage = new ChatPage();
    private String tempMessage = "";

    @When("I can go to adview page by listID {string} of {string}")
    public void i_can_go_to_adview_page_by_listID(String list_id, String user_name) {
        String new_adview_url = String.format(adview_url, tempListID);
        delayStep(maxWaitTime);
        openURL(new_adview_url);
    }

    @And("I can click on Chat label of seller")
    public void i_can_click_on_chat_label_of_seller() {
        new ContactPanel().clickAndSwitchToChatPage();
    }

    @Then("I can send text message to chat")
    public void i_can_send_text_message_to_chat() {
        String textMessage = "Send text message to chat";
        chatPage.verifyUserCanSendTextMessage(textMessage);
    }

    @Then("I can send an image to chat")
    public void i_can_send_an_image_to_chat() {
        chatPage.verifyUserCanSendImage();
    }

    @When("I click on chat template message in adview screen")
    public void click_chat_template_adview() {
        tempMessage = adView.clickFirstChatTemplateInAdview();
    }

    @Then("I can go to chat page by click on Tiếp tục chat")
    public void i_can_go_to_chat_by_click_continue_chat() {
        adView.clickContinueChat();
    }

    @And("I can see the sent message in chat detail")
    public void i_can_see_sent_message_in_chat_detail() {
        chatPage.verifyUserCanSendTextMessage(tempMessage);
    }

    @When("I click on chat template message in chat detail screen")
    public void i_click_chat_template_in_chat_detail() {
        tempMessage = chatPage.clickChatTemplateInDetail();
    }

    @Then("I can report this user with fraud reason")
    public void i_can_report_fraud_reason() {
        chatPage.reportUser();
    }

    @Then("I can remove chat with this user")
    public void i_can_remove_chat_user() {
        chatPage.removeChatUser();
    }

    @When("I block chat with this user")
    public void i_block_chat_with_user() {
        chatPage.blockUser();

    }

    @When("I unblock chat with this user")
    public void i_unblock_chat_with_user() {
        chatPage.unblockUser();
    }

    @Then("I cannot send text message to chat")
    public void i_cannot_send_text_message_to_chat() {
        chatPage.verifyBlockedUser();
    }

    @Then("I can send an offer to chat {string}")
    public void i_can_send_offer_to_chat(String offerValue){
        chatPage.sendChatOffer(offerValue);
        chatPage.verifyChatOffer(offerValue);
    }

    @Then("I can go to user profile page")
    public void i_can_go_to_user_profile_from_chat(){
        chatPage.clickUserProfileInChat();
    }

    @When("I can send an appointment to chat with location {string}")
    public void i_can_send_chat_appointment(String location) {
        chatPage.createAppointment(location);
    }

    @Then("New Chat appointment is created")
    public void new_chat_appointment_is_created() {
        chatPage.verifyChatAppointment();
    }
}