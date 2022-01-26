package projects.functions.chotot.scooby;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static projects.functions.APISupportFunctions.*;

public class Scooby_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Scooby_API_Functions.class);
    static JsonObject bodyJson = null;
    static Response response = null;
    static String imagePath = System.getProperty("user.dir") + "/images/chat/chat_1.jpeg";

    public static String createNewChatRoom(String listID) {
        JsonObject itemJson = new JsonObject();
        itemJson.addProperty("item_id",  listID );
        bodyJson = new JsonObject();
        bodyJson.add("product", itemJson);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_CreateRoom);
        String roomID = getResponseData(response, "$.result._id");
        log.info("RoomID: " + roomID);
        return roomID;
    }

    public static boolean getRoomInfo(String roomID, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_RoomInfo);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static boolean sendChatMessage(String roomID, String message, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("text", message);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_SendMessage);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static boolean sendOfferMessage(String roomID, String message, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("text", message);
        bodyJson.addProperty("type", "offer");
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_SendMessage);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static String sendChatMessageReturnStatusCode(String roomID, String message, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("text", message);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_SendMessage);
        return getResponseCode(response);
    }

    public static boolean sendChatImage(String roomID, FailureHandling failureHandling) {
        // Upload image
        Response responseImage = RestAssure.instance().postImageFile(global_accessToken, gatewayChatPrivate_UploadImage, imagePath);
        String full_url = getResponseData(responseImage, "$.image_url");
        String thumbnail_url = getResponseData(responseImage, "$.thumb_url");

        // Send image
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("full_url", full_url);
        bodyJson.addProperty("thumbnail_url", thumbnail_url);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_SendMessage);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static List<String> getListRoomID(String accountID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("user_id", accountID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_ListRoom);
        List<String> listRoomID = getResponseDataList(response,"$.result[*]._id");
        log.info("List room id: " + listRoomID);
        return listRoomID;
    }

    public static void hideRoom(String roomID, String userID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("user_id", userID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_HideRoom);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void setRead(String roomID, String userID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("room_id", roomID.trim());
        bodyJson.addProperty("user_id", userID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_SetReadMessage);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void blockUser(String adID, String memberID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("ad_id", adID);
        bodyJson.addProperty("member_id", memberID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_BlockUSer);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
        delayStep(maxWaitTime * 3);
    }

    public static void unblockUser(String adID, String memberID) {
        delayStep(maxWaitTime);
        bodyJson = new JsonObject();
        bodyJson.addProperty("ad_id", adID);
        bodyJson.addProperty("member_id", memberID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_UnblockUSer);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void reportUser(String adID, String memberID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("ad_id", adID);
        bodyJson.addProperty("member_id", memberID);
        bodyJson.addProperty("reason", 1);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_ReportUSer);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static List<String> getListMessageHistory(String roomID, String userID, String memberID) {
        List<String> listMessageHistory;
        bodyJson = new JsonObject();
        bodyJson.addProperty("limit", "10");
        bodyJson.addProperty("room_id", roomID);
        bodyJson.addProperty("user_id", userID);
        bodyJson.addProperty("member_id", memberID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_GetMessageHistory);
        log.info("Body: "+getResponseBodyAsString(response));
        listMessageHistory = getResponseDataList(response,"$.result[*].text");
        log.info("List message history: " + listMessageHistory);
        return listMessageHistory;
    }

    public static String getUnreadMessage() {
        bodyJson = new JsonObject();
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_GetUnreadCount);
        log.info("Body: "+getResponseBodyAsString(response));
        String unreadCount = getResponseData(response,"$.result");
        return unreadCount;
    }

    public static List<String> getMessageTemplateByRoomID(String roomID) {
        response = RestAssure.instance().get(global_accessToken, String.format(gatewayChatPublic_GetMessageTemplateByRoomID, roomID));
        log.info("Body: "+getResponseBodyAsString(response));
        List<String> listTemplate = getResponseDataList(response,"$.result.messages");
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
        return listTemplate;
    }

    public static List<String> getMessageTemplateByListID(String listID) {
        response = RestAssure.instance().get(global_accessToken, String.format(gatewayChatPublic_GetMessageTemplateByListID, listID));
        log.info("Body: "+getResponseBodyAsString(response));
        List<String> listTemplate = getResponseDataList(response,"$.result.messages");
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
        return listTemplate;
    }

    public static void getPrivateUserInfo() {
        response = RestAssure.instance().get(global_accessToken, gatewayChatPublic_PrivateInfo);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void getPublicUserInfo(String accountID) {
        response = RestAssure.instance().get(global_accessToken, String.format(gatewayChatPublic_PublicInfo, accountID));
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void checkRegisterUser() {
        response = RestAssure.instance().get(global_accessToken, gatewayChatPublic_CheckRegister);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void registerUser(String accountID) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("user", accountID);
        response = RestAssure.instance().post(global_accessToken, bodyJson, gatewayChatPublic_RegisterUser);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void banUserChat(String accountID) {
        String strBody = String.format("{ \"member_ids\": [\"%s\"], \"reason\": \"ban\", \"by\": \"admin\"}", accountID);
        Response response = RestAssure.instance().post(global_accessTokenCP, strBody, gatewayChatAdminPrivate_Ban);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void unbanUserChat(String accountID) {
        String strBody = String.format("{ \"member_ids\": [\"%s\"], \"reason\": \"unban\", \"by\": \"admin\"}", accountID);
        Response response = RestAssure.instance().post(global_accessTokenCP, strBody, gatewayChatAdminPrivate_Unban);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }
}
