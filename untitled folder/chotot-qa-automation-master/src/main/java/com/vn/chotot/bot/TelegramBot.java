package com.vn.chotot.bot;

import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.vn.chotot.api.rest_assured.VerifyRespone.getResponseBodyAsString;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.FileHelper.getProperties;
import static com.vn.chotot.helper.Utils.removeDuplicateListString;
import static io.restassured.RestAssured.given;

public class TelegramBot {

    static final Logger log = Log4jFactory.instance().createClassLogger(TelegramBot.class);
    private static final Properties prop = getProperties("telegram-bot");
    private static final String apiToken = prop.getProperty("apiToken");
    private static final String chatId = prop.getProperty("chatID");
    private static final String chatID_DEVELOP = prop.getProperty("chatID_DEVELOP");
    private static final String chatID_ME = prop.getProperty("chatID_ME");
    private static final String chatID_GROWTH = prop.getProperty("chatID_GROWTH");
    private static final String chatID_MABU = prop.getProperty("chatID_MABU");
    private static final String chatID_DEBUG = prop.getProperty("chatID_DEBUG");
    private static final String chatID_HEIRARCHY = prop.getProperty("chatID_hierarchy");
    private static final String chatID_PRICER = prop.getProperty("chatID_pricer");
    private static final String chatID_VUHOANGTEST = prop.getProperty("chatID_vuhoangtest");

    private static final Properties propGoogle = getProperties("google-sheet");

    private static String getChatId(String team) {
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

    @Deprecated
    private static String getChatIDByFeatureName(String featureName) {
        featureName = featureName.trim().toUpperCase();
        if (featureName.contains("HIERARCHY")) {
            return chatID_HEIRARCHY;
        } else if (featureName.contains("VUHOANGTEST")) {
            return chatID_VUHOANGTEST;
        } else if (featureName.contains("PRICER")){
            return chatID_PRICER;
        }
        else {
            if (TEST_RUN_TYPE.equalsIgnoreCase("Develop_Script"))
                return chatID_DEVELOP;
            else
                return chatID_DEBUG;
        }
    }

    public static String sendMessageToTelegramGroup(String message, String team) {
        String chatID = getChatId(team);
        String urlString = "https://api.telegram.org/bot%s/sendMessage";
        urlString = String.format(urlString, apiToken);
        // Send message
        log.info("\n----- Sending message to Telegram!!!");
        Response response =
                given().when().queryParam("chat_id", chatID).queryParam("text", message).post(urlString);
        System.out.println(response);
        return getResponseBodyAsString(response);
    }

    public static String sendMessageToTelegramGroupDebug(String message) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage";
        urlString = String.format(urlString, apiToken);
        // Send message
        log.info("\n----- Sending message to Telegram - DEBUG !!!");
        Response response =
                given().when().queryParam("chat_id", chatID_DEBUG).queryParam("text", message).post(urlString);
        System.out.println(response);
        return getResponseBodyAsString(response);
    }

    public static String sendImageToTelegramGroup(String imagePath, String team) {
        String chatID = getChatId(team);
        String urlString = "https://api.telegram.org/bot%s/sendPhoto?chat_id=%s";
        urlString = String.format(urlString, apiToken, chatID);
        // Send image
        log.info("\n----- Sending photo to Telegram!!!");
        Response response = given().multiPart("photo", new File(imagePath)).when().post(urlString);
        return getResponseBodyAsString(response);
    }

    public static String sendImageToTelegramGroupDebug(String imagePath) {
        String urlString = "https://api.telegram.org/bot%s/sendPhoto?chat_id=%s";
        urlString = String.format(urlString, apiToken, chatID_DEBUG);
        // Send image
        log.info("\n----- Sending photo to Telegram - DEBUG !!!");
        Response response = given().multiPart("photo", new File(imagePath)).when().post(urlString);
        return getResponseBodyAsString(response);
    }

    public static String sendFileToTelegramGroup(String filePath, String team) {
        String chatID = getChatId(team);
        String urlString = "https://api.telegram.org/bot%s/sendDocument?chat_id=%s";
        urlString = String.format(urlString, apiToken, chatID);
        // Send image
        log.info("\n----- Sending photo to Telegram!!!");
        Response response = given().multiPart("document", new File(filePath)).when().post(urlString);
        return getResponseBodyAsString(response);
    }

    public static String sendFileToTelegramGroup(
            String srcFilePath, String destFilePath, String team) {
        // Process file as reading/writing on
        File srcFle = new File(srcFilePath);
        File destFile = new File(destFilePath);
        try {
            FileUtils.copyFile(srcFle, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String chatID = getChatId(team);
        String urlString = "https://api.telegram.org/bot%s/sendDocument?chat_id=%s";
        urlString = String.format(urlString, apiToken, chatID);
        // Send image
        log.info("\n----- Sending photo to Telegram!!!");
        Response response = given().multiPart("document", destFile).when().post(urlString);

        return getResponseBodyAsString(response);
    }

    //@param featureName   Using to detect chatID -> replaced by featureTeamOwner
    public static String sendMsgToTelegramGroupByFeature(String message, String featureTeamOwner) {
//        String chatID = getChatIDByFeatureName(featureName);
        if(!TEST_RUN_TYPE.equalsIgnoreCase("Debug") && !TEST_RUN_TYPE.equalsIgnoreCase("Develop_Script")){
            String chatID = getChatId(featureTeamOwner);
            String urlString = "https://api.telegram.org/bot%s/sendMessage?parse_mode=markdown";
            urlString = String.format(urlString, apiToken);
            Response response = given().when().queryParam("chat_id", chatID).queryParam("text", message).post(urlString);
            System.out.println(response);
            return getResponseBodyAsString(response);
        }
        return "";
    }

    public static String getTagNameByFeatureTag(List<String> featureTagNames) {
        featureTagNames = removeDuplicateListString(featureTagNames);
        if (featureTagNames.size() > 0) {
            String tagLine = "", name = "";
            for (String tagName : featureTagNames) {
                switch (tagName.toUpperCase()) {
                    case "VUHOANG":
                        name = "vu_hoang";
                        break;
                    case "QUOCTRAN":
                        name = "quoc_tran";
                        break;
                    case "QUANGTRAN":
                        name = "trquang106";
                        break;
                    case "TRINGUYEN":
                    case "TRI":
                        name = "tri_nguyen_89";
                        break;
                    case "TUANCHIEU":
                    case "TUAN":
                        name = "Draigon";
                        break;
                    case "THAMLE":
                    case "THAM":
                        name = "thamlect";
                        break;
                    case "SON":
                    case "SONNGUYEN":
                        name = "sonnht";
                        break;
                    case "MINH":
                    case "MINHTRAN":
                        name = "minhtranfu";
                        break;
                    case "NGUYENTRAN":
                        name = "nguyentran";
                        break;
                    case "HONG":
                        name = "hongnh96";
                        break;
                    case "TUANTRAN":
                        name = "tuantran";
                        break;
                    case "KHOADO":
                        name = "khoado";
                        break;
                    case "CUSTOMER":
                        return "@vuhoang @Draigon @thuanlyminh @trquang106";
                    default:
                        name = "";
                        break;
                }
                if (!name.isEmpty()) tagLine += "@" + name + "  ";  // Add @ to before of name
            }
            return tagLine;
        } else {
            return "";
        }
    }

    public static String getGoogleURLByFeature(String featureName) {
        String googleSheet = "https://docs.google.com/spreadsheets/d/1lCDJYDOf2reMlVOHXeCGbRw03ol6RivNuouVif1AN3E/edit#gid=";
        featureName = featureName.trim().toUpperCase();
        if (featureName.contains("SCOOBY"))
            return googleSheet + propGoogle.getProperty("sheet.scooby.bdd");
        else if (featureName.contains("CHAT-API"))
            return googleSheet + propGoogle.getProperty("sheet.chat.api.bdd");
        else
            return googleSheet + "767362580";
    }
}
