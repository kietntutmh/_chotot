package projects.functions.chotot.flashad;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessTokenOfCP;
import static api.utils.GetAccessToken.getAccessTokenOfCPUser;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;

public class FlashAd_Core_Functions extends APISupportFunctions {
    public static final int retryToCountUserAds = 5;

    public static String insertAd(String ownerToken, JsonObject bodyData, String responseCode) {
        for (int i = 0; i < getTempRetryInsertAd(); i++) {
            try {
                response = post(ownerToken, standardizeBody(bodyData), gatewayFlashAdNew);
                verifyStatusCode200("API FLASHAD IS FAILED");

                //Get adID
                if (responseCode.startsWith("2")) {
                    tempAdID = getResponseData("$.ad_id");
                    Assert.assertNotNull(tempAdID, "FLASH AD: API doesn't response ad_id field");
                    Assert.assertFalse(tempAdID.isEmpty(), "FLASH AD: API response ad_id with null value");
                }
                break;
            } catch (AssertionError assertionError) {
                System.out.println("Retry to Insert Ad at [" + i + "] time: " + getResponseData("$.message"));
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("API FLASH AD IS DEAD: " + getResponseMessage());
        Assert.assertNotNull(tempAdID, "FLASH AD: API doesn't response ad_id field");
        Assert.assertFalse(tempAdID.isEmpty(), "FLASH AD: API response ad_id with null value");
        tempUID = getResponseData("$.uid");

        //To check ad is posted successfully
        checkAdInUserAds(tempAdID);

        //Check adID exists on user_ads
        return tempAdID;
    }

    public static String editAd(String ownerToken, JsonObject bodyData, String responseCode) {
        Assert.assertNotNull(bodyData.get("ad_id"), "Edit Ad FAILED with null ad_id");
        String editAdId = "";
        for (int i = 0; i < getTempRetryInsertAd(); i++) {
            try {
                response = post(ownerToken, standardizeBody(bodyData), gatewayFlashAdEdit);
                verifyStatusCode(response, "FLASH AD", gatewayFlashAdEdit, responseCode);

                //Get adID
                if (responseCode.startsWith("2")) {
                    editAdId = getResponseData("$.ad_id");
                    Assert.assertNotNull(editAdId, "FLASH AD: API doesn't response ad_id field");
                    Assert.assertFalse(editAdId.isEmpty(), "FLASH AD: API response ad_id with null value");
                }
                break;
            } catch (AssertionError assertionError) {
                System.out.println("Retry to Edit Ad at [" + i + "] time: " + getResponseData("$.message"));
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertFalse(editAdId.isEmpty(), "FLASH AD: API response ad_id with null value");
        isEditedAd = true;
        editAdId = getResponseData("$.ad_id");
        return editAdId;
    }

    private static JsonObject standardizeBody(JsonObject bodyDataInput) {
        try {
            Assert.assertNotNull(bodyDataInput.get("app_id"));
        } catch (AssertionError | NullPointerException e) {
            bodyDataInput.addProperty("app_id", "desktop_site_flashad");
            bodyDataInput.addProperty("skip_verify_phone", "1");
            bodyDataInput.addProperty("lang", "vi");
            bodyDataInput.addProperty("uid", tempUID);
        }
        return bodyDataInput;
    }

    public static List<String> uploadNewImages(String ownerToken, List<String> imagePaths) {
        List<String> imageURLs = new ArrayList<>();
        for (String imagePath : imagePaths) {
            imageURLs.add(uploadNewImage(ownerToken, imagePath));
        }
        return imagePaths;
    }

    public static String uploadNewImage(String ownerToken, String imagePath) {
        responseTemp1 = postImageFile(ownerToken, gatewayUploadImage, imagePath);
        verifyStatusCode200(responseTemp1, "UPLOAD IMAGE", gatewayUploadImage);
        String imageID = getResponseData(responseTemp1, "image_id");
        Assert.assertNotNull(imageID, "CAN'T GET IMAGE ID");
        return imageID;
    }

    //CP ACTION ============== ACCEPT
    protected static String acceptAd(JsonObject bodyData) {
        //is_paid_ad
        bodyData.addProperty("action_id", 1);
        bodyData.addProperty("action", "1");
        bodyData.addProperty("remote_addr", "192.168.100.197");
        bodyData.addProperty("reason", "Automation Test Accept Ad");
        bodyData.addProperty("phone", newUserPhone);

        //Standardize JSON body
        bodyData.addProperty("category", Integer.parseInt(bodyData.get("category").getAsString()));
        bodyData.remove("uid");

        for (int i = 0; i < getTempRetryCPActionAd(); i++) {
            try {
                getAccessTokenOfCPUser();
                bodyData.addProperty("token", global_accessTokenCP);
                response = post(global_accessTokenCP, bodyData, getGatewayCPHost() + gatewayCPAcceptAd);
                verifyStatusCode200(response, "CP", gatewayCPAcceptAd);
                Assert.assertTrue(getResponseDataInt("$.list_id") > -1);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200(response, "CP", gatewayCPAcceptAd);
        Assert.assertTrue(getResponseDataInt("$.list_id") > -1);

        tempListID = getPropertyValue(response, "list_id");
        waitForListIDExistedOnListing(tempListID);
        return tempListID;
    }

    private static void waitForListIDExistedOnListing(String listID) {
        for (int i = 0; i < 10; i++) {
            try {
                response = get(gatewayAdListing_GetListID + "/" + listID);
                verifyStatusCode200("LIST ID " + listID + " Doesn't display on Listing");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("LIST ID " + listID + " Doesn't display on Listing");
    }

    //CP ACTION ============== REFUSE
    protected static String refuseAd(JsonObject bodyData, boolean isAbleToEdit) {
        bodyData.addProperty("action_id", 1);
        bodyData.addProperty("action", "2");
        bodyData.addProperty("remote_addr", "192.168.100.197");
        bodyData.addProperty("reason_cat", bodyData.get("subCategory").getAsString());
        if (isAbleToEdit) {
//            bodyData.addProperty("reason", "rr1_duplicate_house");        //VUHOANG DEBUG
            bodyData.addProperty("reason", "rr3_officecommercialproperties_20170724");
        } else {
            bodyData.addProperty("reason", "rr3_officecommercialproperties_20170724");
        }

        if (getTempRetryCPActionAd() > 0) {
            for (int i = 0; i < getTempRetryCPActionAd(); i++) {
                try {
                    setAccessTokenOfCP();
                    bodyData.addProperty("token", global_accessTokenCP);
                    response = post(global_accessTokenCP, bodyData, getGatewayCPHost() + gatewayCPRejectAd);
                    // Failed because of expired token
                    if (getResponseCode(response).equalsIgnoreCase("400")
                            && getResponseData("$.message").equalsIgnoreCase("ERROR_TOKEN_OLD")) {
                        getAccessTokenOfCPUser();
                    }
                    verifyStatusCode200("");
                    break;
                } catch (AssertionError assertionError) {
                    waitConstant(3);
                }
            }
        }
        debugResponse();
        // Check status code
        verifyStatusCode200("CP", gatewayCPRejectAd);

        return getResponseData("$.payment_status");
    }

    public static void checkUserAdTotal(String accountID, String categoryID, int expectedTotal) {
        int actualTotal = 0;
        for (int i = 0; i < retryToCountUserAds; i++) {
            try {
                responseTemp1 = get(String.format(gatewayInternalUserAds_Count, accountID));
                verifyStatusCode200("COUNT USER ADS FAILED");
                Assert.assertEquals(actualTotal, expectedTotal, "USER ADS TOTAL IS INCORRECT");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        actualTotal = getResponseDataInt(responseTemp1, "$." + categoryID + ".total");
        Assert.assertEquals(actualTotal, expectedTotal, "USER ADS TOTAL IS INCORRECT");
    }

    public static void checkAdInUserAds(String adID) {
        String expectAdId = "";
        for (int i = 0; i < 6; i++) {
            try {
                response = get(String.format(gatewayUserAdsInternal, adID));
                verifyStatusCode200("API USER ADS IS DEAD");
                expectAdId = getResponseData("$.ad_id");
                Assert.assertEquals(expectAdId, adID, "AD " + adID + " doesn't exist in User Ads");
                break;
            } catch (AssertionError a) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertNotNull(expectAdId, "AD " + adID + " doesn't exist in User Ads");
    }

    public static void checkAdInUserAds_PaymentStatus(String adID, String paymentStatus) {
        for (int i = 0; i < 6; i++) {
            try {
                response = get(String.format(gatewayUserAdsInternal, adID));
                verifyStatusCode200("API USER ADS IS DEAD");
                Assert.assertEquals(paymentStatus, getResponseData("$.payment_status")
                        , "AD payment_status" + adID + " doesn't change");
                break;
            } catch (AssertionError a) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertEquals(paymentStatus, getResponseData("$.payment_status")
                , "AD payment_status" + adID + " doesn't change");
    }
}
