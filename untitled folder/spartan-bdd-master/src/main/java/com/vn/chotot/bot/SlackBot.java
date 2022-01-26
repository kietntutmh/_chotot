package com.vn.chotot.bot;

import java.io.File;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vn.chotot.api.rest_assured.VerifyRespone.getResponseBodyAsString;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.FileHelper.getProperties;
import static io.restassured.RestAssured.given;

public class SlackBot {
    private static Properties prop = getProperties("slack-bot");
    private static final String apiToken = prop.getProperty("apiToken");

    // Get Chat Group ID
    public static final String develop_test_channel = prop.getProperty("channel_develop");
    public static final String nightly_test_channel = prop.getProperty("channel_nightly");
    public static final String deploy_test_channel = prop.getProperty("channel_deploy");
    public static final String gamma_test_channel = prop.getProperty("channel_gamma");

    private static final String API_SEND_IMAGE = "https://slack.com/api/files.upload?token=%s&channels=%s";
    private static final String API_SEND_MSG = "https://slack.com/api/chat.postMessage?token=%s&channel=%s&text=%s&pretty=1";

    public static void sendImageToSlackChannel(String channelID, String imagePath) {
        String urlBot = String.format(API_SEND_IMAGE, apiToken, channelID);
        given()
                .multiPart("file", new File(imagePath))
                .when()
                .post(urlBot);
    }

    public static void sendMessageToSlackChannel(String channelID, String message) {
        String urlBot = String.format(API_SEND_MSG, apiToken, channelID, message);
        given()
                .when()
                .post(urlBot);
    }

    public static void sendMessageToSlackChannelByRunType(String message, String teamOwner) {
        String channelID = getChannelIDByTeamOwnerAndRunType(teamOwner);
        String newMessage = extractMessage(message); // Convert telegram to slack
        sendMessageToSlackChannel(channelID, newMessage);
    }

    private static String getChannelIDByTeamOwnerAndRunType(String teamOwner) {
        switch (TEST_RUN_TYPE.toLowerCase()) {
            case "nightly":
                return nightly_test_channel;
            case "deploy_service":
                return getChannelIDByTeamOwner(teamOwner);
            default:
                return develop_test_channel;
        }
    }

    private static String getChannelIDByTeamOwner(String teamOwner) {
        switch (teamOwner.toLowerCase()) {
            case "gamma":
                return gamma_test_channel;
            // Add more
            default:
                return deploy_test_channel;
        }
    }

   // Change slackName to slackID
   private static String extractMessage(String message) {
       message = getSlackID(message, "quoc", "<@UU8FHJG9K>");
       message = getSlackID(message, "hong", "<@U0186M21ELR>");
       message = getSlackID(message,"nguyentran", "<@U0186M21RPB>");
       message = getSlackID(message,"tuantran", "<@U0187Q4FRT5>");
       message = getSlackID(message,"khoa", "<@U0205HLHTDJ>");
       return message;
   }

    private static String getSlackID(String message, String slackName, String slackID) {
        String regex = "[\\S]*" + slackName + "[\\S]*";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            message = matcher.replaceAll(slackID);
        }
        return message;
    }
}
