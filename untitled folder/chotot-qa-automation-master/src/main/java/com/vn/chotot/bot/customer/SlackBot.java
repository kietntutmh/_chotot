package com.vn.chotot.bot.customer;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.FileHelper.getProperties;
import static io.restassured.RestAssured.given;

public class SlackBot {
    private static Properties prop = getProperties("slack-bot-customer");
    private static final String botAPIToken = prop.getProperty("botApiToken");

    // Get Chat Group ID
    public static final String AUTOTEST_REPORT_CHANNEL = prop.getProperty("autotest_report_channel");

    // SLACK APIs
    private static final String API_SEND_IMAGE = "https://slack.com/api/files.upload?channels=%s";
    private static final String API_SEND_MSG = "https://slack.com/api/chat.postMessage?channel=%s&text=%s&pretty=1";

    // Functions
    public static void sendMessageToSlack(String channelID, String message) {
        String urlBot = String.format(API_SEND_MSG, channelID, message);
        given()
                .header("Authorization", "Bearer " + botAPIToken)
                .when()
                .post(urlBot);
    }

    public static void sendImageToSlack(String channelID, String imagePath) {
        String urlBot = String.format(API_SEND_IMAGE, channelID);
        given()
                .multiPart("file", new File(imagePath))
                .header("Authorization", "Bearer " + botAPIToken)
                .when()
                .post(urlBot);
    }

    public static void sendMessageToSlackAndTags_DeployService(String message, List<String> tagNameList) {
        sendMessageToSlackAndTags(AUTOTEST_REPORT_CHANNEL, message, tagNameList);
    }

    /**
     * @param tagNameList Null -> Missing Tag. Empty -> "Don't tag" doesn't happen
     */
    public static void sendMessageToSlackAndTags(String channelID, String message, List<String> tagNameList) {
        if (!TEST_RUN_TYPE.equalsIgnoreCase("Nightly")) {
            if(tagNameList != null){
                // Tagging
                String tagString = "\n\n" + getTagNameString(tagNameList) + "\n";
                message = tagString + message;
            }
            // Send Message
            sendMessageToSlack(channelID, message);
        }
    }

    // Change slackName to slackID
    private static String getTagNameString(List<String> tagNameList) {
        if (tagNameList == null)
            return "";

        // Remove Duplicate Feature Element in Feature List
        tagNameList = tagNameList.stream().distinct().collect(Collectors.toList());
        if (tagNameList.size() > 0) {
            String tagString = "", name = "";
            tagString = "<@UU81NCC1J> ";    //VUHOANG is fixed
            for (String tagName : tagNameList) {
                switch (tagName.toUpperCase()) {
                    case "QUANGTRAN":
                        name = "<@U026S10U9FV>";
                        break;
                    case "TUANCHIEU":
                        name = "<@U017U8R51MH>";
                        break;
                    case "THUANLY":
                        name = "<@U0185B9FBMG>";
                        break;
                    case "CUSTOMER":
                        return "<!subteam^S01KJV79668>";
                    default:
                        name = "";
                        break;
                }
                if (!name.isEmpty()) tagString += name + "  ";  // Add @ to before of name
            }
            return tagString;
        } else {
            return "++ Missing @TAG_ in the Scenario. FYI <@UU81NCC1J>";
        }
    }
}
