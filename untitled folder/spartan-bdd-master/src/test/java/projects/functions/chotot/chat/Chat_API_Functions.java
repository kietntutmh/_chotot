package projects.functions.chotot.chat;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static io.restassured.RestAssured.given;
import static projects.functions.APISupportFunctions.getResponseBodyAsString;
import static projects.functions.APISupportFunctions.getResponseData;

public class Chat_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Chat_API_Functions.class);
    static Response response = null;
    protected static List<String> listImageUrls = new ArrayList<>();
    static String imagePath = System.getProperty("user.dir") + "/images/chat/chat_1.jpeg";
    public static final String defaultInternalAPIToken = StringUtils.isEmpty(System.getenv("CHAT_API_TOKEN")) ? "ab72db36f7dc96fa406a5cbf4fc921957b2e0548572807edb9793bdefbf060e3" : System.getenv("CHAT_API_TOKEN");
    static RequestSpecification internalRequestDefaultSpec = given()
            .header("api-token", defaultInternalAPIToken).contentType("application/json");
    static String defaultImageURL = "https://static.chotot.com/storage/marketplace/transparent_logo.png";

    protected class UserChatData {
        private String id;
        private String fullName;
        private String avatar;
        private Map<String, String> metadata = new HashMap<>();

        public UserChatData(String id, String fullName, String avatar, String phone, String email) {
            this.id = id;
            this.fullName = fullName;
            this.avatar = avatar;
            this.metadata.put("phone", phone);
            this.metadata.put("email", email);
        }
    }

    protected static String  privateCreateNewChatChannel(String chatToken, String listID, String message, String messageType, String chatType) {
        String defaultChannelRequest = "{ \"list_id\": \"%s\", \"message\": \"%s\", \"message_type\": \"%s\", \"channel_type\": \"%s\"}";
        String strBody = String.format(defaultChannelRequest, listID, message, messageType, chatType);
        log.info("Channel request: " + strBody);
        response = RestAssure.instance().post(chatToken, strBody, gatewayPrivateCreateChannelNewChat);
        String channelID = getResponseData(response, "$.data.channel_id");
        if(StringUtils.isEmpty(channelID)) {
            channelID = getResponseBodyAsString(response);
        }
        log.info("channel_id: " + channelID);
        return channelID;
    }

    protected static String  internalCreateNewChatChannel(List<String> listUserID, String listID, String message, String messageType, String chatType) {
    String defaultChannelRequest =
        "{ \"user_ids\": %s, \"seller_id\": \"%s\", \"buyer_id\": \"%s\", \"list_id\": \"%s\", \"name\": \"%s\", \"message\": \"%s\", \"message_type\": \"%s\", \"channel_type\": \"%s\", \"item_name\": \"test item name\", \"item_image\": \"test item image\"}";
        String defaultChannelName = "test channel name";
        String strListUserID = listUserID.toString().replaceAll("\\[", "[\"").replaceAll("\\]", "\"]").replaceAll(", ","\",\"");
        String strBody = String.format(defaultChannelRequest,strListUserID, listUserID.get(1), listUserID.get(0), listID, defaultChannelName, message, messageType, chatType);
        log.info("Channel request: " + strBody);
        response = internalRequestDefaultSpec.body(strBody).post(gatewayInternalCreateChannelNewChat);
        log.info("\n----- POST url: " + gatewayInternalCreateChannelNewChat + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        String channelID = getResponseData(response, "$.data.channel_id");
        if(StringUtils.isEmpty(channelID)) {
            channelID = getResponseBodyAsString(response);
        }
        log.info("Channel id: " + channelID);
        return channelID;
    }

    protected static Response privateSendMessageChat(String chatToken, String channelID, String message, Boolean isImage, FailureHandling failureHandling) {
        String defaultSendMessageRequest = "{ \"channel_id\": \"%s\", \"message\": \"%s\", \"image_urls\": [\"%s\"], \"type\": \"%s\"}";
        String typeMessage = "text";
        String imageURL = defaultImageURL;
        if (isImage) {
            typeMessage = "image";
            // Upload image
            Response responseImage = RestAssure.instance().postImageFile(global_accessToken, gatewayChatPrivate_UploadImage, imagePath);
            imageURL = getResponseData(responseImage, "$.image_url");
            listImageUrls.add(imageURL);
        }
        String strBody = String.format(defaultSendMessageRequest, channelID, message, imageURL,typeMessage);
        log.info("Send message private request: " + strBody);
        response = RestAssure.instance().post(chatToken, strBody, gatewayPrivateSendMessageNewChat);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response internalInsertAdChat(String listID, FailureHandling failureHandling) {
        String defaultInsertAdRequest =
        "{\"list\":[{ \"list_id\": \"%s\", \"seller_id\": \"%s\", \"item_name\": \"%s\", \"item_price\": \"%s\", \"item_image\": \"%s\", \"category\": \"%s\"}]}";
        Map<String,String> adData = getAdDataFromListing(listID);
        String strBody = String.format(defaultInsertAdRequest, listID, adData.get("seller_id"), adData.get("item_name"), adData.get("item_price"), adData.get("item_image"), adData.get("category"));
        log.info("Insert Ad data chat request: " + strBody);
        response = internalRequestDefaultSpec.body(strBody).put(gatewayInternalInsertAdNewChat);
        log.info("\n----- PUT url: " + gatewayInternalInsertAdNewChat + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    private static Map<String, String> getAdDataFromListing(String listID) {
        response = RestAssure.instance().get(String.format(gatewayViewAdOnListing, listID));
        String seller_id = getResponseData(response, "$.ad.account_id");
        String item_name = getResponseData(response, "$.ad.subject");
        String item_price = getResponseData(response, "$.ad.price");
        String item_image = getResponseData(response, "$.ad.avatar");
        String category = getResponseData(response, "$.ad.category");
        Map<String, String> adData = new HashMap<>();
        adData.put("seller_id", seller_id);
        adData.put("item_name", item_name);
        adData.put("item_price", item_price);
        adData.put("item_image", item_image);
        adData.put("category", category);
        return adData;
    }

    protected static Response internalSendMessageChat(String channelID, String senderID, String message, Boolean isImage, FailureHandling failureHandling) {
        String defaultSendMessageRequest = "{\" send_socket_event\": false, \"channel_id\": \"%s\", \"sender_id\": \"%s\", \"message\": \"%s\", \"image_urls\": [\"%s\"], \"type\": \"%s\"}";
        String typeMessage = "text";
        String imageURL = defaultImageURL;
        if (isImage) {
            typeMessage = "image";
            // Upload image
            Response responseImage = RestAssure.instance().postImageFile(global_accessToken, gatewayChatPrivate_UploadImage, imagePath);
            imageURL = getResponseData(responseImage, "$.image_url");
        }
        String strBody = String.format(defaultSendMessageRequest, channelID, senderID, message, imageURL,typeMessage);
        log.info("Send message internal request: " + strBody);
        response = internalRequestDefaultSpec.body(strBody).post(gatewayInternalSendMessageNewChat);
        log.info("\n----- POST url: " + gatewayInternalSendMessageNewChat + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response internalGetUserChannel(String userID, FailureHandling failureHandling) {
        String uri = String.format(gatewayInternalGetUserChannelNewChat, userID);
        response = internalRequestDefaultSpec.get(uri);
        log.info("\n----- GET url: " + uri + "\n----- API-TOKEN: " + defaultInternalAPIToken);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response privateGetUserChannel(String chatToken, FailureHandling failureHandling) {
        response = RestAssure.instance().get(chatToken, gatewayPrivateGetUserChannelNewChat);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response internalGetChannelInfo(String userID, String channelID, FailureHandling failureHandling) {
        String uri = String.format(gatewayInternalGetChannelInfoNewChat, userID, channelID);
        response = internalRequestDefaultSpec.get(uri);
        log.info("\n----- GET url: " + uri + "\n----- API-TOKEN: " + defaultInternalAPIToken);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response privateGetChannelInfo(String chatToken, String channelID, FailureHandling failureHandling) {
        String uri = String.format(gatewayPrivateGetChannelInfoNewChat, channelID);
        response = RestAssure.instance().get(chatToken, uri);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response internalInsertOrUpdateUserData(UserChatData userChatData, FailureHandling failureHandling) {
        String uri = String.format(gatewayInternalPutUserDataNewChat, userChatData.id);
        String strBody = "{\"full_name\": \"%s\", \"avatar\": \"%s\", \"metadata\": %s}";
        String metadataStr = userChatData.metadata.toString().replaceAll("\\{", "{\"").replaceAll("\\}", "\"}").replaceAll(", ","\",\"").replaceAll("=","\":\"");
        strBody = String.format(strBody, userChatData.fullName, userChatData.avatar, metadataStr);
        log.info("Insert or update user internal request: " + strBody);
        response = internalRequestDefaultSpec
                .body(strBody)
                .put(uri);
        log.info("\n----- PUT url: " + uri + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response internalGetUserInfo(String userID, FailureHandling failureHandling) {
        String uri = String.format(gatewayInternalGetUserDataNewChat, userID);
        response = internalRequestDefaultSpec.get(uri);
        log.info("\n----- GET url: " + uri + "\n----- API-TOKEN: " + defaultInternalAPIToken);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response privateGetUserInfo(String chatToken, FailureHandling failureHandling) {
        response = RestAssure.instance().get(chatToken, gatewayPrivateGetUserDataNewChat);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response privateGetChatMessages(String chatToken, String channelID, String limitResult, String orderQuery, FailureHandling failureHandling) {
        String uri = String.format(gatewayPrivateGetChatMessagesNewChat, channelID, limitResult, orderQuery);
        response = RestAssure.instance().get(chatToken, uri);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static Response privateGetChatChanges(String chatToken, String channelID, String limitResult, String orderQuery, FailureHandling failureHandling) {
        String uri = String.format(gatewayPrivateGetChangesMessagesNewChat, channelID, limitResult, orderQuery);
        response = RestAssure.instance().get(chatToken, uri);
        verifyStatusCode(response, "200", failureHandling);
        return response;
    }

    protected static int privateGetUnreadCountChat(String chatToken) {
        response = RestAssure.instance().get(chatToken, gatewayPrivateGetUnreadCountNewChat);
        String unreadCount = getResponseData(response, "$.data.unread_count");
        log.info("unread_count: " + unreadCount);
        return Integer.parseInt(unreadCount);
    }

    protected static int internalGetUnreadCountChat(String userID) {
        String uri = String.format(gatewayInternalGetUnreadCountNewChat, userID);
        response = internalRequestDefaultSpec.get(uri);
        log.info("\n----- GET url: " + uri + "\n----- API-TOKEN: " + defaultInternalAPIToken);
        String unreadCount = getResponseData(response, "$.data.unread_count");
        return Integer.parseInt(unreadCount);
    }

    protected static void internalSetReadMessageChat(String userID, String channelID, FailureHandling failureHandling) {
        String strBody = "{\"user_id\": \"%s\", \"channel_id\": \"%s\"}";
        strBody = String.format(strBody, userID, channelID);
        response = internalRequestDefaultSpec.body(strBody).put(gatewayInternalSetReadMessageNewChat);
        log.info("\n----- PUT url: " + gatewayInternalSetReadMessageNewChat + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        verifyStatusCode(response, "200", failureHandling);
    }

    protected static String internalGetNewTokenChat(String userID) {
        String strBody = "{\"user_id\": \"%s\", \"access_token_ttl\": 3600, \"refresh_token_ttl\": 7776000}";
        strBody = String.format(strBody, userID);
        response = internalRequestDefaultSpec.body(strBody).post(gatewayInternalGetTokenNewChat);
        log.info("\n----- POST url: " + gatewayInternalGetTokenNewChat + "\n----- API-TOKEN: " + defaultInternalAPIToken + "\n----- BODY: " + strBody);
        String access_token = "Bearer " + getResponseData(response,"$.data.access_token");
        log.info("chat_token: " + access_token);
        return access_token;
    }

}
