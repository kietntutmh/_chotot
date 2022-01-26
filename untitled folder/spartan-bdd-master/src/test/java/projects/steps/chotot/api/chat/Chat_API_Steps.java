package projects.steps.chotot.api.chat;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;
import projects.functions.chotot.chat.Chat_API_Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.*;
import static com.vn.chotot.keywords.selenium.Utils.verifyEquals;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static desktop.configuration.LoginConfig.tempUserEmail;

public class Chat_API_Steps extends Chat_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Chat_API_Steps.class);

    private CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
    private HashMap<String, String> mapListIDChat = new HashMap<>();
    private String defaultTextMessage = "<%s> send a chat message to <%s>";
    private String defaultNewChannelMessage = "This is the first message for creating new chat channel";
    private String userAvatar = "https://cdn.chotot.org/uac2/%s";
    private String listIDX;
    private String adID;
    private List<String> listUserID = new ArrayList<>();
    private List<String> listChatToken = new ArrayList<>();
    private String channelID;
    private String tempChannelID;
    private Response resData = null;
    private List<String> listMessages = new ArrayList<>();
    private String defaultMessageType = "text";
    private String defaultUserName = "Test auto";
    private String sellerID;
    private String buyerID;
    private String privateChatToken;

    private void checkCurrentLoginData(String userName) {
        if (userName.equalsIgnoreCase("User A")) {
            switchLoginDataToOtherUser(1);
            privateChatToken = listChatToken.get(1);
            log.info(String.format("Switched to user_phone A <%s>, global_accountID <%s>, privateChatToken <%s>", current_phone, global_accountID, privateChatToken));
        } else if (userName.equalsIgnoreCase("User B")) {
            switchLoginDataToOtherUser(0);
            privateChatToken = listChatToken.get(0);
            log.info(String.format("Switched to user_phone B <%s>, global_accountID <%s>, privateChatToken <%s>", current_phone, global_accountID, privateChatToken));
        }
    }

    @Given("Reset test data for new Chat")
    public void resetNewChatTestData() {
        adID = "";
        listIDX = "";
        channelID = "";
        listUserID = new ArrayList<>();
        resData = null;
        listMessages = new ArrayList<>();
        listImageUrls = new ArrayList<>();
        privateChatToken = "";
    }

    @Given("{string} had a {string} on listing")
    public void userA_had_an_ad_on_listing(String userA, String adName) {
        setTempAccountCPAPIIndex(4);
        getAccessTokenOfNewUser();
        listUserID.add(global_accountID);
        sellerID = global_accountID;
        adID = cm_api_ads_insertEntertainment.insertNewAdInstrument(true, "Accept");
        listIDX = tempListID;
        mapListIDChat.put(adName, listIDX);
        // Insert Ad data
        //internalInsertAdChat(listIDX, FailureHandling.STOP_ON_FAILURE);
        // Insert user data
        //UserChatData userChatData = new UserChatData(global_accountID, defaultUserName, String.format(userAvatar, global_accountID), current_phone, tempUserEmail);
        //internalInsertOrUpdateUserData(userChatData, FailureHandling.STOP_ON_FAILURE);
        listChatToken.add(internalGetNewTokenChat(global_accountID));
    }


    @And("New account {string} for chat-api")
    public void create_new_account_to_chat(String newUser) {
        getAccessTokenOfNewUser();
        listUserID.add(global_accountID);
        buyerID = global_accountID;
        // Insert user data
        //UserChatData userChatData = new UserChatData(global_accountID, defaultUserName, String.format(userAvatar, global_accountID), current_phone, tempUserEmail);
        //internalInsertOrUpdateUserData(userChatData, FailureHandling.STOP_ON_FAILURE);
        listChatToken.add(internalGetNewTokenChat(global_accountID));
    }

    @When("{string} create a new chat channel with {string}, list_id empty and chat_type {string}")
    public void private_create_a_new_chat_channel_with_empty_listID(String user1, String user2, String chatType) {
        checkCurrentLoginData(user1);
        channelID = privateCreateNewChatChannel(privateChatToken, "", defaultNewChannelMessage, defaultMessageType, chatType);
    }

    @When("{string} recreate a new chat channel with {string}, list_id empty and chat_type {string}")
    public void private_recreate_a_new_chat_channel_with_empty_listID(String user1, String user2, String chatType) {
        checkCurrentLoginData(user1);
        tempChannelID = privateCreateNewChatChannel(privateChatToken, "", defaultNewChannelMessage, defaultMessageType, chatType);
    }

    @When("{string} create a new chat channel with {string} of {string} and chat_type {string}")
    public void private_create_a_new_chat_channel_with_listID(String user1, String listID, String user2, String chatType) {
        checkCurrentLoginData(user1);
        channelID = privateCreateNewChatChannel(privateChatToken, listIDX, "", defaultMessageType, chatType);
    }

    @When("{string} recreate a new chat channel with {string} of {string} and chat_type {string}")
    public void private_recreate_a_new_chat_channel_with_listID(String user1, String listID, String user2, String chatType) {
        checkCurrentLoginData(user1);
        tempChannelID = privateCreateNewChatChannel(privateChatToken, listIDX, "", "", chatType);
    }

    @When("{string} create a new chat channel with {string} and chat_type {string}")
    public void private_create_a_new_chat_channel_with_user(String user1, String user2, String chatType) {
        checkCurrentLoginData(user1);
        channelID = privateCreateNewChatChannel(privateChatToken, "", "", defaultMessageType, chatType);
    }

    @Then("A new channel should be created between {string} and {string}")
    public void internal_check_new_chat_channel_is_created_successfully(String user1, String user2) {
        verifyEquals(channelID.contains("error"), false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("A new channel should not be created between {string} and {string}")
    public void internal_check_new_chat_channel_is_NOT_created_successfully(String user1, String user2) {
        verifyMatch(channelID, tempChannelID, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("An error message is returned for missing list_id")
    public void internal_check_error_message_missing_listID() {
        verifyEquals(channelID.contains("error"), true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can send a text message to the channel between {string} and {string}")
    public void internal_send_text_message_to_channel(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = internalSendMessageChat(channelID, global_accountID, String.format(defaultTextMessage, user1, user2), false, FailureHandling.STOP_ON_FAILURE);
        String message = getResponseData(resData, "$.data.message");
        listMessages.add(message);
        String messageType = getResponseData(resData, "$.data.type");
        verifyMatch(messageType, "text", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can send an image message to the channel between {string} and {string}")
    public void internal_send_image_message_to_channel(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = internalSendMessageChat(channelID, global_accountID, String.format(defaultTextMessage, user1, user2), true, FailureHandling.STOP_ON_FAILURE);
        String messageType = getResponseData(resData, "$.data.type");
        verifyMatch(messageType, "image", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can send a text message to {string}")
    @And("{string} can send a text message to {string} again")
    public void private_send_text_message_to_channel(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = privateSendMessageChat(privateChatToken, channelID, String.format(defaultTextMessage, user1, user2), false, FailureHandling.STOP_ON_FAILURE);
        String message = getResponseData(resData, "$.data.message");
        listMessages.add(message);
        String messageType = getResponseData(resData, "$.data.type");
        verifyMatch(messageType, "text", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can send an image message to {string}")
    @And("{string} can send an image message to {string} again")
    public void private_send_image_message_to_channel(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = privateSendMessageChat(privateChatToken, channelID, String.format(defaultTextMessage, user1, user2), true, FailureHandling.STOP_ON_FAILURE);
        String messageType = getResponseData(resData, "$.data.type");
        verifyMatch(messageType, "image", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can get the existing channel between {string} and {string}")
    public void internal_get_existing_channels_chat(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = internalGetUserChannel(tempAccountID, FailureHandling.STOP_ON_FAILURE);
        String channelIDInfo = getResponseData(resData, "$.data[0].channel_id");
        verifyMatch(channelID, channelIDInfo, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can get the existing channel with {string}")
    public void private_get_existing_channels_chat(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = privateGetUserChannel(privateChatToken, FailureHandling.STOP_ON_FAILURE);
        String channel_id = getResponseData(resData, "$.data[0].channel_id");
        String user_id = getResponseData(resData, "$.data[0].user_id");
        verifyMatch(channelID, channel_id, false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(global_accountID, user_id, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can get the channel info between {string} and {string}")
    public void internal_get_channel_info_chat(String user1, String user2) {
        checkCurrentLoginData(user2);
        resData = internalGetChannelInfo(tempAccountID, channelID, FailureHandling.STOP_ON_FAILURE);
        String list_id = getResponseData(resData, "$.data.channel.list_id");
        String seller_id = getResponseData(resData, "$.data.channel.seller_id");
        String buyer_id = getResponseData(resData, "$.data.channel.buyer_id");
        verifyMatch(list_id, listIDX, false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(seller_id, sellerID, false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(buyer_id, buyerID, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can get the channel info with {string}")
    public void private_get_channel_info_chat(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = privateGetChannelInfo(privateChatToken, channelID, FailureHandling.STOP_ON_FAILURE);
        String list_id = getResponseData(resData, "$.data.channel.list_id");
        String seller_id = getResponseData(resData, "$.data.channel.seller_id");
        String buyer_id = getResponseData(resData, "$.data.channel.buyer_id");
        verifyMatch(list_id, listIDX, false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(seller_id, sellerID, false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(buyer_id, buyerID, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can insert {string}")
    @And("I can update {string}")
    public void internal_insert_or_update_user_data(String user1) {
        checkCurrentLoginData(user1);
        UserChatData userChatData = new UserChatData(global_accountID, "Test auto", String.format(userAvatar, global_accountID), current_phone, global_accountID);
        internalInsertOrUpdateUserData(userChatData, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can get info of {string}")
    public void internal_get_chat_user_info(String user1) {
        checkCurrentLoginData(user1);
        resData = internalGetUserInfo(tempAccountID, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(tempAccountID, getResponseData(resData, "$.data.id"), false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(defaultUserName, getResponseData(resData, "$.data.full_name"), false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(String.format(userAvatar, global_accountID), getResponseData(resData, "$.data.avatar"), false, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(current_phone, getResponseData(resData, "$.data.metadata.phone"), false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can get info of {string}")
    public void private_get_chat_user_info(String user1, String user2) {
        checkCurrentLoginData(user1);
        resData = privateGetUserInfo(privateChatToken, FailureHandling.STOP_ON_FAILURE);
        verifyMatch(tempAccountID, getResponseData(resData, "$.data.id"), false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can get {string} messages of {string}")
    public void private_get_chat_messages(String user1, String typeMessage, String user2) {
        checkCurrentLoginData(user1);
        resData = privateGetChatMessages(privateChatToken, channelID, "20", "asc", FailureHandling.STOP_ON_FAILURE);
        log.info("Response: " + getResponseBodyAsString(resData));
        if (typeMessage.equalsIgnoreCase("text")) {
            List<String> lstMsg = getResponseDataList(resData, "$.data[*][?(@.type == 'text')].message");
            for (int i = 0; i < listMessages.size(); i++) {
                log.info(String.format("Actual msg <%s> and expected msg <%s>", listMessages.get(i), lstMsg.get(i)));
                verifyMatch(listMessages.get(i), lstMsg.get(i + 1), false, FailureHandling.STOP_ON_FAILURE);
            }
        } else if (typeMessage.equalsIgnoreCase("image")) {
            List<String> lstImageUrls = getResponseDataList(resData, "$.data[*][?(@.type == 'image')].image_urls[*]");
            for (int i = 0; i < listImageUrls.size(); i++) {
                String expected = lstImageUrls.get(i);
                log.info(String.format("Actual img_url <%s> and expected img_url <%s>", listImageUrls.get(i), expected));
                verifyMatch(listImageUrls.get(i), expected, false, FailureHandling.STOP_ON_FAILURE);
            }
        }
    }

    @Then("{string} can get changes of {string} {string} message")
    public void private_get_chat_changes(String user1, String action, String typeMessage) {
        checkCurrentLoginData(user1);
        resData = privateGetChatChanges(privateChatToken, channelID, "20", "asc", FailureHandling.STOP_ON_FAILURE);
        log.info("Response: " + getResponseBodyAsString(resData));
        if (typeMessage.equalsIgnoreCase("text")) {
            List<String> lstAction = getResponseDataList(resData, "$.data[*][?(@.type == 'text')].action");
            List<String> lstMsg = getResponseDataList(resData, "$.data[*][?(@.type == 'text')].message");
            if (action.equalsIgnoreCase("created")) {
                for (int i = 0; i < listMessages.size(); i++) {
                    log.info(String.format("Actual msg <%s> and expected msg <%s>", listMessages.get(i), lstMsg.get(i)));
                    verifyMatch(listMessages.get(i), lstMsg.get(i + 1), false, FailureHandling.STOP_ON_FAILURE);
                    verifyMatch("create", lstAction.get(i + 1), false, FailureHandling.STOP_ON_FAILURE);
                }
            }
        } else if (typeMessage.equalsIgnoreCase("image")) {
            List<String> lstAction = getResponseDataList(resData, "$.data[*][?(@.type == 'image')].action");
            List<String> lstImageUrls = getResponseDataList(resData, "$.data[*][?(@.type == 'image')].image_urls[*]");
            if (action.equalsIgnoreCase("created")) {
                for (int i = 0; i < listMessages.size(); i++) {
                    log.info(String.format("Actual img_url <%s> and expected img_url <%s>", listImageUrls.get(i), lstImageUrls.get(i)));
                    verifyMatch(listImageUrls.get(i), lstImageUrls.get(i), false, FailureHandling.STOP_ON_FAILURE);
                    verifyMatch("create", lstAction.get(i), false, FailureHandling.STOP_ON_FAILURE);
                }
            }
        }
    }

    @Then("{string} gets the total unread count is {string}")
    public void private_get_unread_count_chat(String user1, String expectedUnreadCount) {
        checkCurrentLoginData(user1);
        int unreadCount = privateGetUnreadCountChat(privateChatToken);
        verifyEquals(unreadCount, Integer.parseInt(expectedUnreadCount), FailureHandling.STOP_ON_FAILURE);
    }

    @When("I sets read to the channel between {string} and {string}")
    public void internal_set_read_msg(String user1, String user2) {
        checkCurrentLoginData(user1);
        internalSetReadMessageChat(global_accountID, channelID, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I gets the total unread count of {string} is {string}")
    public void internal_get_unread_count_chat(String user1, String expectedUnreadCount) {
        checkCurrentLoginData(user1);
        int unreadCount = internalGetUnreadCountChat(tempAccountID);
        verifyEquals(unreadCount, Integer.parseInt(expectedUnreadCount), FailureHandling.STOP_ON_FAILURE);
    }

}
