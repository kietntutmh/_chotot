package com.vn.chotot.bot.customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.FileHelper.getProperties;
import static io.restassured.RestAssured.given;

public class TelegramBot {

    private static final Properties prop = getProperties("telegram-bot-customer");
    private static final String apiToken = prop.getProperty("apiToken");

    // Get Chat Group ID
    private static final String chatID_CUSTOMER = prop.getProperty("chatID_CUSTOMER");

//    private static final String chatID_GROWTH = prop.getProperty("chatID_GROWTH");
//    private static final String chatID_MABU = prop.getProperty("chatID_MABU");
//    private static final String chatID_DEBUG = prop.getProperty("chatID_DEBUG");
//    private static final String chatID_DEVELOP = prop.getProperty("chatID_DEVELOP");

    private static final Properties propGoogle = getProperties("google-sheet");

    public static void sendMessageToTelegram(String chatGroupID, String message, boolean isParseMode) {
        // Set Bot URL
        String botURL = "https://api.telegram.org/bot%s/sendMessage";
        if (isParseMode)
            botURL = "https://api.telegram.org/bot%s/sendMessage?parse_mode=markdown";
        // Set Bot Token
        botURL = String.format(botURL, apiToken);
        // Send message
        given()
                .when()
                .queryParam("chat_id", chatGroupID)
                .queryParam("text", message)
                .post(botURL);
    }

    public static void sendImageToTelegram(String chatGroupID, String imagePath) {
        // Set Bot URL
        String botURL = "https://api.telegram.org/bot%s/sendPhoto?chat_id=%s";
        // Set Bot Token
        botURL = String.format(botURL, apiToken, chatGroupID);
        // Send image
        given()
                .multiPart("photo", new File(imagePath))
                .when()
                .post(botURL);
    }

    public static void sendFileToTelegram(String chatGroupID, String filePath) {
        // Set Bot URL
        String botURL = "https://api.telegram.org/bot%s/sendDocument?chat_id=%s";

        // Set Bot Token
        botURL = String.format(botURL, apiToken, chatGroupID);

        // Send file
        given()
                .multiPart("document", new File(filePath))
                .when()
                .post(botURL);
    }

    //Using for only CUSTOMER
    public static void sendMsgToTelegramAndTags(String teamOwnerOfFeature, String message, List<String> tagNameList) {
        if (teamOwnerOfFeature.equalsIgnoreCase("ME") || teamOwnerOfFeature.equalsIgnoreCase("CUSTOMER")) {
            if (!TEST_RUN_TYPE.equalsIgnoreCase("Nightly")) {
                if (tagNameList != null) {
                    // Tagging
                    String tagString = "\n\n*" + getTagNameString(tagNameList) + "*\n";
                    message = tagString + message;
                }
                // Send message to Telegram
                sendMessageToTelegram(chatID_CUSTOMER, message, true);
            }
        }
    }

    public static void sendMsgToTelegramNotTags(String teamOwnerOfFeature, String message) {
        sendMsgToTelegramAndTags(teamOwnerOfFeature, message, new ArrayList<>());
    }

    // Chotot's Scope. Detect tagName XXXX of from @AUTHOR_XXXXX
    private static String getTagNameString(List<String> tagNameList) {

        if (tagNameList == null)
            return "";

        // Remove Duplicate Feature Element in Feature List
        tagNameList = tagNameList.stream().distinct().collect(Collectors.toList());

        if (tagNameList.size() > 0) {
            String tagString = "", name = "";
            for (String tagName : tagNameList) {
                switch (tagName.toUpperCase()) {
                    case "VUHOANG":
                        name = "vu_hoang";
                        break;
                    case "QUANGTRAN":
                        name = "trquang106";
                        break;
                    case "TUANCHIEU":
                        name = "Draigon";
                        break;
                    case "THUANLY":
                        name = "thuanlyminh";
                        break;
                    case "CUSTOMER":
                        return "@vu_hoang @Draigon @thuanlyminh @trquang106";
                    default:
                        name = "";
                        break;
                }
                if (!name.isEmpty()) tagString += "@" + name + "  ";  // Add @ to before of name
            }
            return tagString;
        } else {
            return "++ Missing @TAG_ in the Scenario. FYI @vu_hoang";
        }
    }

    public static String getGoogleReportURLByFeature(String featureName) {
        String googleSheet = "https://docs.google.com/spreadsheets/d/1lCDJYDOf2reMlVOHXeCGbRw03ol6RivNuouVif1AN3E/edit#gid=";
        featureName = featureName.trim().toUpperCase();
        if (featureName.contains("USER PROFILER"))
            return googleSheet + propGoogle.getProperty("sheet.user.profiler.bdd");
        else if (featureName.contains("ESCROW"))
            return googleSheet + propGoogle.getProperty("sheet.escrow.bdd");
        else if (featureName.contains("RCA"))
            return googleSheet + propGoogle.getProperty("sheet.shop.rca.bdd");
        else if (featureName.contains("DONG TOT"))
            return googleSheet + propGoogle.getProperty("sheet.payment.bdd");
        else if (featureName.contains("HIERARCHY")) {
            if (featureName.contains("DEBIT"))
                return googleSheet + propGoogle.getProperty("sheet.hierarchy.debit.bdd");
            else
                return googleSheet + propGoogle.getProperty("sheet.hierarchy.bdd");     // old features of AH
        } else if (featureName.contains("SCOOBY"))
            return googleSheet + propGoogle.getProperty("sheet.scooby.bdd");
        else if (featureName.contains("LISTING FEE"))
            return googleSheet + propGoogle.getProperty("sheet.listing.fee.bdd");
        else if (featureName.contains("PRICER") || featureName.contains("PRICE"))
            return googleSheet + propGoogle.getProperty("sheet.pricer.bdd");
        else if (featureName.contains("POS"))
            return googleSheet + propGoogle.getProperty("sheet.pos.bdd");
        else if (featureName.contains("REWARD"))
            return googleSheet + propGoogle.getProperty("sheet.reward.bdd");
        else if (featureName.contains("LOAN"))
            return googleSheet + propGoogle.getProperty("sheet.bankloan.bdd");
        else if (featureName.contains("PROMOTION"))
            return googleSheet + propGoogle.getProperty("sheet.promotion.bdd");
        else
            return googleSheet + "767362580";
    }

    /*
        public static void sendMessageToTelegramGroupDebug(String message) {
            sendMessageToTelegram(chatID_DEBUG, message, false);
        }

        private static String getChatGroupId(String team) {
            if (TEST_RUN_TYPE.equalsIgnoreCase("Develop_Script")) return chatID_DEVELOP;
            else if (TEST_RUN_TYPE.equalsIgnoreCase("Debug")) return chatID_DEBUG;
            else if (TEST_RUN_TYPE.equalsIgnoreCase("Deploy_Service") && team.equalsIgnoreCase("ME"))
                return chatID_ME;
            else if (TEST_RUN_TYPE.equalsIgnoreCase("Deploy_Service") && team.equalsIgnoreCase("MABU"))
                return chatID_MABU;
            else if (TEST_RUN_TYPE.equalsIgnoreCase("Deploy_Service") && team.equalsIgnoreCase("GROWTH"))
                return chatID_GROWTH;
            else return chatID_DEVELOP; // Send to Debug group until scripts more stability
        }
     */

}
