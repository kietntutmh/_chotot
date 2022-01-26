package projects.steps.chotot.api.scooby;

import api.utils.GetAccessToken;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;
import projects.functions.chotot.scooby.Scooby_API_Functions;

import java.util.HashMap;
import java.util.List;

import static api.configuration.DataConfig.setTempAccountCPAPIIndex;
import static api.configuration.DataConfig.tempListID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.*;
import static com.vn.chotot.keywords.selenium.Utils.*;

public class Scooby_API_Steps extends Scooby_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Scooby_API_Steps.class);

    private CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
    private HashMap<String, String> mapListIDChat = new HashMap<>();
    private String defaultTextMessage = "%s send chat message to ad_id <%s> of %s";
    private String adID;
    private String listID;
    private String roomID;
    private String otherRoomID;
    private String roomIDX;
    private String roomIDY;

    private void checkCurrentLoginData(String userName) {
        if (userName.equalsIgnoreCase("User A")) {
            switchLoginDataToOtherUser(0);
            log.info(String.format("Switched to user_phone A <%s>, token <%s>", current_phone, global_accessToken));
        } else if (userName.equalsIgnoreCase("User B")) {
            switchLoginDataToOtherUser(1);
            log.info(String.format("Switched to user_phone B <%s>, token <%s>", current_phone, global_accessToken));
        }
    }

    @Given("Reset test data of Chat")
    public void resetChatData() {
        adID = "";
        listID = "";
        roomID = "";
        otherRoomID = "";
        roomIDX = "";
        roomIDY = "";
    }

    @Given("{string} had an {string} on listing")
    public void user_had_ad_on_listing(String userName, String adName) {
        setTempAccountCPAPIIndex(4);
        getAccessTokenOfNewUser();
        adID = cm_api_ads_insertEntertainment.insertNewAdInstrument(true, "Accept");
        listID = tempListID;
        mapListIDChat.put(adName, listID);
    }

    @When("I ban an existing user using API")
    public void i_ban_an_user(){
    }

    @And("New account {string}")
    public void another_new_account(String userB) {
        getAccessTokenOfNewUser();
    }

    @And("I register another account {string}")
    public void third_new_account(String userC) {
        getAccessTokenOfNewUser();
    }

    @When("{string} create a new chat room with {string} of {string}")
    public void i_create_a_new_chat_room(String user1, String adName, String user2) {
//        roomID = createNewChatRoom(listID);
    }

    @Then("A new room should be created between {string} and {string}")
    public void verify_new_room_created_successfully(String user1, String user2){
        verifyNotMatch(roomID, "", false, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send a new message to {string} of {string}")
    public void i_send_a_new_message_to_other(String user1, String adName, String user2) {
        roomID = createNewChatRoom(listID);
        String message = String.format(defaultTextMessage, user1, adID , user2);
        sendChatMessage(roomID, message, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} send an offer to {string} of {string}")
    public void send_chat_offer_to_user(String user1, String adName, String user2) {
        roomID = createNewChatRoom(listID);
        String message = "Người mua trả giá 1.000.000 đ";
        sendOfferMessage(roomID, message, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send a banned keyword {string} to {string} of {string}")
    public void i_send_a_banned_kw_message_to_other(String user1, String message, String adName, String user2) {
        roomID = createNewChatRoom(listID);
        String statusCode = sendChatMessageReturnStatusCode(roomID, message, FailureHandling.WARNING);
        verifyMatch(statusCode, "400", false, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send a message {string} to {string} of {string}")
    public void i_send_a_message_to_user(String user1, String message, String adName, String user2) {
        String statusCode = sendChatMessageReturnStatusCode(roomID, message, FailureHandling.WARNING);
    }

    @Then("{string} is banned automatically and cannot send other message {string} to {string} of {string}")
    public void user_is_banned_automatically(String user1, String message,  String adName, String user2) {
        String statusCode = sendChatMessageReturnStatusCode(roomID, message, FailureHandling.WARNING);
        verifyMatch(statusCode, "400", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} is banned automatically and cannot send other message to {string} of {string}")
    public void user_is_banned_automatically(String user1, String adName, String user2) {
        String message = String.format(defaultTextMessage, user1, adID , user2);
        String statusCode = sendChatMessageReturnStatusCode(roomID, message, FailureHandling.WARNING);
        verifyMatch(statusCode, "404", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can send a new message to {string} of {string} again")
    public void i_send_a_new_message_again(String user1, String adName, String user2) {
        checkCurrentLoginData(user1);
        String message = String.format(defaultTextMessage, user1, adID , user2);
        sendChatMessage(roomID, message, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} can see the room info with {string}")
    public void get_room_info(String user1, String user2) {
        getRoomInfo(roomID, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} cannot see the room info of {string} and {string}")
    public void i_cannot_see_room_info_of_others(String user1, String user2, String user3) {
        Boolean check = getRoomInfo(roomID, FailureHandling.WARNING);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} cannot send a new message to {string} of {string}")
    public void i_cannot_send_a_new_message_to_blocked_user(String user1, String adName, String user2) {
        String message = String.format(defaultTextMessage, user1, adID , user2);
        Boolean check = sendChatMessage(roomID, message, FailureHandling.WARNING);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} cannot send a new message to {string} of {string} after banning")
    public void banned_user_cannot_send_a_new_message(String user1, String adName, String user2) {
        String message = String.format(defaultTextMessage, user1, adID , user2);
        Boolean check = sendChatMessage(roomID, message, FailureHandling.WARNING);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} send some messages to {string} of {string} again")
    public void i_send_some_messages_to_other_again(String user1, String adName, String user2, DataTable dataTable) {
        checkCurrentLoginData(user1);
        roomID = createNewChatRoom(listID);
        List<String> listMessages =  dataTable.asList();
        for (String s: listMessages)
            sendChatMessage(roomID, s, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send some messages to {string} of {string}")
    public void i_send_some_messages_to_other(String user1, String adName, String user2, DataTable dataTable) {
        checkCurrentLoginData(user1);
        roomID = createNewChatRoom(listID);
        List<String> listMessages =  dataTable.asList();
        for (String s: listMessages)
            sendChatMessage(roomID, s, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send a new message to existing room of {string} again")
    public void i_send_a_new_message_again(String user1, String user2) {
        checkCurrentLoginData(user1);
        String message = String.format(defaultTextMessage, user1, adID , user2);
        sendChatMessage(roomID, message, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} cannot send a new message to existing room of {string}")
    public void i_cannot_send_a_new_message_to_existing_room(String user1, String user2) {
        checkCurrentLoginData(user1);
        String message = String.format(defaultTextMessage, user1, adID , user2);
        Boolean check = sendChatMessage(roomID, message, FailureHandling.WARNING);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} can send a new message to existing room of {string} again")
    public void i_send_a_new_message_to_existing_room(String user1, String user2) {
        checkCurrentLoginData(user1);
        String message = String.format(defaultTextMessage, user1, adID , user2);
        sendChatMessage(roomID, message, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} create other room to {string} of {string}")
    public void i_create_other_room(String user1, String adName, String user2) {
        otherRoomID = createNewChatRoom(listID);
    }

    @Then("A new room shouldn't be created between {string} and {string}")
    public void a_new_room_should_not_create(String user1, String user2) {
        verifyMatch(roomID, otherRoomID, false, FailureHandling.STOP_ON_FAILURE);
    }

    @When("{string} send a new message to {string} and {string}")
    public void i_send_message_to_adX_adY(String userName, String adX, String adY) {
        roomIDX = createNewChatRoom(mapListIDChat.get(adX));
        roomIDY = createNewChatRoom(mapListIDChat.get(adY));
    }

    @Then("{string} can send an image to {string} of {string}")
    public void i_send_image(String user1, String adX, String user2) {
        sendChatImage(roomID, FailureHandling.STOP_ON_FAILURE);
    }

    @Then ("{string} cannot send a message with length > {int} characters")
    public void i_cannot_send_message_exceed_max_length(String userName, int maxLength) {
        String maxMessage = "";
        for(int i=0;i<=maxLength;i++) {
            maxMessage += "a";
        }
        Boolean check = sendChatMessage(roomID, maxMessage, FailureHandling.WARNING);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see the new rooms in the Room List")
    public void third_user_see_all_new_room(String userName){
        List<String> listRoomID = getListRoomID(global_accountID);
        Boolean check = listRoomID.contains(roomIDX) && listRoomID.contains(roomIDY);
        verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} hides the room with {string}")
    public void i_hide_a_room(String user1, String user2) {
        hideRoom(roomID, global_accountID);
    }

    @And("{string} blocks {string}")
    public void i_block_an_user(String user1, String user2) {
        GetAccessToken.User createdUserA = listNewUser.get(0);
        blockUser(adID,createdUserA.accountID);
    }

    @When("{string} unblocks {string}")
    public void i_unblock_an_user(String user1, String user2) {
        checkCurrentLoginData(user1);
        GetAccessToken.User createdUserA = listNewUser.get(0);
        unblockUser(adID,createdUserA.accountID);
    }

    @Then("{string} can report {string}")
    public void i_report_an_user(String user1, String user2) {
        reportUser(adID,global_accountID);
    }

    @Then("{string} shouldn't see the room in the Room List")
    public void user_should_not_see_hidden_room(String userName) {
        List<String> listRoomID = getListRoomID(global_accountID);
        Boolean check = listRoomID.contains(roomID);
        verifyEquals(check, false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see the room with {string} in the Room List")
    public void user_should_see_room_with_other(String user1, String user2) {
        List<String> listRoomID = getListRoomID(global_accountID);
        Boolean check = listRoomID.contains(roomID);
        verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see the room with {string} in the Room List again")
    public void user_should_see_room_with_other_again(String user1, String user2) {
        checkCurrentLoginData(user1);
        List<String> listRoomID = getListRoomID(global_accountID);
        Boolean check = listRoomID.contains(roomID);
        verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see the room with {string} in the Room List as well")
    public void user_should_see_room_with_other_as_well(String user1, String user2) {
        checkCurrentLoginData(user1);
        List<String> listRoomID = getListRoomID(global_accountID);
        System.out.println("Room ID: " + roomID);
        Boolean check = listRoomID.contains(roomID);
        verifyEquals(check, true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see all messages of {string} displaying in the room")
    public void i_should_see_all_messages_in_room(String user1, String adName, DataTable dataTable) {
        GetAccessToken.User createdUserA = listNewUser.get(0);
        List<String> listMessageHistory = getListMessageHistory(roomID, createdUserA.accountID, global_accountID);
        List<String> expectedMessage = dataTable.asList();
        verifyEquals(listMessageHistory, expectedMessage, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} still see all messages of {string} displaying in the room")
    public void i_still_see_all_messages_in_room(String user1, String adName, DataTable dataTable) {
        GetAccessToken.User createdUserA = listNewUser.get(0);
        List<String> listMessageHistory = getListMessageHistory(roomID, global_accountID, createdUserA.accountID);
        List<String> expectedMessage = dataTable.asList();
        verifyEquals(listMessageHistory, expectedMessage, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("{string} should see {string} unread messages")
    public void i_should_see_expected_unread_message(String userName, String expectedNumber) {
        checkCurrentLoginData(userName);
        String unreadCount = getUnreadMessage();
        verifyMatch(unreadCount, expectedNumber, false, FailureHandling.STOP_ON_FAILURE);
    }

    @And("{string} read all unread messages of {string} in the room")
    public void read_all_message_in_room(String userName, String adName) {
        checkCurrentLoginData(userName);
        setRead(roomID, global_accountID);
    }

    @Then("{string} can see an existing message template from {string}")
    public void get_template_from_ad(String userName, String adName) {
        getMessageTemplateByListID(listID);
    }

    @And("{string} can see existing template in the room with {string}")
    public void get_template_from_room(String user1, String user2) {
        getMessageTemplateByRoomID(roomID);
    }

    @Then("{string} can get public information of {string}")
    public void get_public_user_info(String user1, String user2) {
        GetAccessToken.User createdUserA = listNewUser.get(0);
        getPublicUserInfo(createdUserA.accountOID);
    }

    @And("{string} can get private information of {string}")
    public void get_private_user_info(String user1, String user2) {
        getPrivateUserInfo();
    }

    @Then("{string} can register")
    public void i_can_register_chat_user(String user1) {
        registerUser(global_accountID);
    }

    @And("{string} can check register")
    public void i_can_check_register_user(String userName) {
        checkRegisterUser();
    }

    @When("I ban {string} in CP")
    public void i_ban_an_user(String userName) {
        getAccessTokenOfCPUser();
        banUserChat(global_accountID);
    }

    @When("I unban {string} in CP")
    public void i_unban_an_user(String userName) {
        getAccessTokenOfCPUser();
        unbanUserChat(global_accountID);
    }

}
