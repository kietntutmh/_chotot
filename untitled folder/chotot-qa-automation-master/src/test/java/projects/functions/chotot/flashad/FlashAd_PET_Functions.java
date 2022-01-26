package projects.functions.chotot.flashad;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.openqa.selenium.json.Json;

import static api.configuration.DataConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class FlashAd_PET_Functions extends FlashAd_Core_Functions {

    private JsonObject bodyData;
    private String region, area, ward, address;
    private String subject, body;
    private long price;
    private String filePath = System.getProperty("user.dir") + "/elt";
    private boolean isPrivateAd, isProAd, isShopAd, isShopAdToChotot;

    private void restartValues() {
        bodyData = new JsonObject();
        region = "13000";
        area = "13096";
        ward = "9220";
        subject = "Automation Test PET %s " + getDateInStringFormat(null, "mmss");
        body = "Automation Test, Insert new Ad PET. Support to create test data. Category: ";
        address = "2 Đường Ngô Đức Kế (Duong Ngo Duc Ke), Quận 1, Ho Chi Minh City, Vietnam";
        price = 2000000;

        isPrivateAd = false;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = false;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // ----------------------------------------------

    private void setAdToPrivate() {
        restartValues();
        isPrivateAd = true;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = false;
        bodyData.addProperty("company_ad", "0");
    }

    private void setAdToPro() {
        restartValues();
        isPrivateAd = false;
        isProAd = true;
        isShopAd = false;
        isShopAdToChotot = false;
        bodyData.addProperty("company_ad", "1");
    }

    private void setAdToShop() {
        restartValues();
        isPrivateAd = false;
        isProAd = false;
        isShopAd = true;
        isShopAdToChotot = false;
        bodyData.addProperty("shop_to_chotot", "no");
    }

    private void setAdToShopToChotot() {
        restartValues();
        isPrivateAd = false;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = true;
        bodyData.addProperty("shop_to_chotot", "yes");
    }

    private void setBodyData(String ownerToken, String adType, String categoryID, boolean isUpLoadImage) {
        bodyData.addProperty("category", categoryID);
        bodyData.addProperty("subCategory", categoryID);
        bodyData.addProperty("type", adType);
        bodyData.addProperty("region_v2", region);
        bodyData.addProperty("area_v2", area);
        bodyData.addProperty("address", address);
        bodyData.addProperty("price", price);
        bodyData.addProperty("body", body + categoryID);
        bodyData.addProperty("condition_ad", "1"); // xe moi
        bodyData.addProperty("latitude", 10.77456);
        bodyData.addProperty("longitude", 106.706039);
        bodyData.addProperty("payment_delivery", "202");

        switch (categoryID) {
            case CATEID_PET_CHICKEN:
                subject = String.format(subject, "Gà");

                bodyData.addProperty("pet_breed", "2"); // Gà Brahma
                bodyData.addProperty("pet_age", "1"); // Gà lớn (từ 3 tháng tuổi)
                break;
            case CATEID_PET_DOG:
                subject = String.format(subject, "Chó");

                bodyData.addProperty("pet_breed", "1"); // Chó Alaska
                bodyData.addProperty("pet_age", "3"); // Chó trưởng thành (hơn 1 tuổi)
                bodyData.addProperty("pet_size", "4"); // Lớn
                break;
            case CATEID_PET_BIRD:
                subject = String.format(subject, "Chim");

                bodyData.addProperty("pet_breed", "6"); // Chim Hoàng Yến
                bodyData.addProperty("pet_age", "1"); // Chim lớn (từ 3 tháng tuổi)
                bodyData.addProperty("pet_gender", "1"); // 1: Chim trống, 2: Chim mái, 3: Không xác định được
                break;
            case CATEID_PET_CAT:

                break;
            case CATEID_PET_OTHERS:

                break;
            case CATEID_PET_ACCESSORIES:

                break;
        }
        bodyData.addProperty("subject", subject);

        // Upload Image
        if (isUpLoadImage) {
            bodyData.addProperty("image_id0", uploadNewImage(ownerToken, filePath + "/pet/Bird1.jpg"));
        } else {
            bodyData.addProperty("image_id0", "2677521862851286347.jpg");
        }
    }

    /**
     * @param adType           s, u , k , h
     * @param cpAction
     * @param isUploadNewImage
     */
    private String insertAdPET(String ownerToken, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        String adID = insertAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdPET(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "pay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                checkAdInUserAds_PaymentStatus(adID, "paid");
                break;

            case "paydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                checkAdInUserAds_PaymentStatus(adID, "paid");
                break;

            default:
                break;
        }

        tempAdID = adID;
        tempAdCategoryID = cateID;
        tempAdType = adType;
        tempAdSubject = subject;

        return adID;
    }

    /**
     * @param adType           s, u , k , h
     * @param cpAction
     * @param isUploadNewImage
     */
    private String editAdPET(String ownerToken, String adId, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        bodyData.addProperty("ad_id", adId);
        String adID = editAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPET(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdPET(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPET(adID, cateID, bodyData, true);
                break;

            case "pay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                break;

            case "paydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                break;

            default:
                break;
        }

        tempAdID = adID;
        tempAdCategoryID = cateID;
        tempAdType = adType;
        tempAdSubject = subject;
        return adID;
    }
    //--------------------- ACCEPT/ REFUSE AD ---------------------
    private String acceptAdPET(String adID, String categoryID, JsonObject bodyData) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());


        switch (categoryID) {
            case CATEID_PET_CHICKEN:
                adParams.addProperty("subject", String.format(subject, "Gà"));

                adParams.addProperty("pet_breed", "1"); // Gà Ác
                adParams.addProperty("pet_age", "1"); // Gà lớn (từ 3 tháng tuổi)
                break;

            case CATEID_PET_DOG:
                adParams.addProperty("subject", String.format(subject, "Chó"));

                adParams.addProperty("pet_breed", "1"); // Chó Alaska
                adParams.addProperty("pet_age", "3"); // Chó trưởng thành (hơn 1 tuổi)
                adParams.addProperty("pet_size", "4"); // Lớn
                break;
            case CATEID_PET_BIRD:
                adParams.addProperty("subject", String.format(subject, "Chim"));

                adParams.addProperty("pet_breed", "6"); // Chim Hoàng Yến
                adParams.addProperty("pet_age", "1"); // Chim lớn (từ 3 tháng tuổi)
                adParams.addProperty("pet_gender", "1"); // 1: Chim trống, 2: Chim mái, 3: Không xác định được
                break;
            case CATEID_PET_CAT:
                adParams.addProperty("subject", String.format(subject, "Mèo"));

                adParams.addProperty("pet_breed", "1"); // Mèo Anh lông dài
                adParams.addProperty("pet_age", "3"); // Mèo trưởng thành (hơn 1 tuổi)
                break;
            case CATEID_PET_OTHERS:
                adParams.addProperty("subject", String.format(subject, "Cá"));

                adParams.addProperty("pet_breed", "1"); // Cá cảnh
                break;
            case CATEID_PET_ACCESSORIES:
                adParams.addProperty("subject", String.format(subject, "Áo cho Chó"));
                break;
        }

        if (isPrivateAd) {
            adParams.addProperty("company_ad", false);
        } else if (isProAd) {
            adParams.addProperty("company_ad", true);
        } else if (isShopAd) {
            adParams.addProperty("shop_to_chotot", false);
        } else {
            adParams.addProperty("shop_to_chotot", true);
        }

        adParams.addProperty("area", 96);
        bodyData.add("ad_params", adParams);
        bodyData.addProperty("ad_id", Integer.valueOf(adID));
        bodyData.addProperty("orig_price", bodyData.get("price").getAsString());

        return acceptAd(bodyData);
    }


    private String refuseAdPET(String adID, String categoryID, JsonObject bodyData, boolean isAbleToEdit) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());

        switch (categoryID) {
            case CATEID_PET_CHICKEN:
                adParams.addProperty("subject", String.format(subject, "Gà"));

                adParams.addProperty("pet_breed", "1"); // Gà Ác
                adParams.addProperty("pet_age", "1"); // Gà lớn (từ 3 tháng tuổi)
                break;

            case CATEID_PET_DOG:
                adParams.addProperty("subject", String.format(subject, "Chó"));

                adParams.addProperty("pet_breed", "1"); // Chó Alaska
                adParams.addProperty("pet_age", "3"); // Chó trưởng thành (hơn 1 tuổi)
                adParams.addProperty("pet_size", "4"); // Lớn
                break;
            case CATEID_PET_BIRD: //pet_gender
                adParams.addProperty("subject", String.format(subject, "Chim"));

                adParams.addProperty("pet_breed", "6"); // Chim Hoàng Yến
                adParams.addProperty("pet_age", "1"); // Chim lớn (từ 3 tháng tuổi)
                adParams.addProperty("pet_gender", "1"); // 1: Chim trống, 2: Chim mái, 3: Không xác định được
                break;
            case CATEID_PET_CAT:
                adParams.addProperty("subject", String.format(subject, "Mèo"));

                adParams.addProperty("pet_breed", "1"); // Mèo Anh lông dài
                adParams.addProperty("pet_age", "3"); // Mèo trưởng thành (hơn 1 tuổi)
                break;
            case CATEID_PET_OTHERS:
                adParams.addProperty("subject", String.format(subject, "Cá"));

                adParams.addProperty("pet_breed", "1"); // Cá cảnh
                break;
            case CATEID_PET_ACCESSORIES:
                adParams.addProperty("subject", String.format(subject, "Áo cho Chó"));
                break;
        }

        if (isPrivateAd) {
            adParams.addProperty("company_ad", false);
        } else if (isProAd) {
            adParams.addProperty("company_ad", true);
        } else if (isShopAd) {
            adParams.addProperty("shop_to_chotot", false);
        } else {
            adParams.addProperty("shop_to_chotot", true);
        }

        adParams.addProperty("area", 96);
        bodyData.add("ad_params", adParams);

        bodyData.addProperty("ad_id", Integer.valueOf(adID));
        bodyData.addProperty("orig_price", bodyData.get("price").getAsString());

        return refuseAd(bodyData, isAbleToEdit);
    }

    // ----------------- CHICKEN ----------------- //
    public String insertChickenSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, issUploadNewImage);
    }

    public String insertChickenSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, issUploadNewImage);
    }

    public String insertChickenSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }
    // ----------------- DOG ----------------- //
    public String insertDogSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_DOG, cpAction, issUploadNewImage);
    }

    public String insertDogSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_DOG, cpAction, issUploadNewImage);
    }

    public String insertDogSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_DOG, cpAction, isUploadNewImage);
    }
    // ----------------- BIRD ----------------- //
    public String insertBirdSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_BIRD, cpAction, issUploadNewImage);
    }

    public String insertBirdSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_BIRD, cpAction, issUploadNewImage);
    }

    public String insertBirdSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_BIRD, cpAction, isUploadNewImage);
    }
    // ----------------- CAT ----------------- //
    public String insertCatSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CAT, cpAction, issUploadNewImage);
    }

    public String insertCatSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CAT, cpAction, issUploadNewImage);
    }

    public String insertCatSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_CAT, cpAction, isUploadNewImage);
    }
    // ----------------- OTHERS ----------------- //
    public String insertPetOthersSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_OTHERS, cpAction, issUploadNewImage);
    }

    public String insertPetOthersSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_OTHERS, cpAction, issUploadNewImage);
    }

    public String insertPetOthersSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_OTHERS, cpAction, isUploadNewImage);
    }
    // ----------------- ACCESSORIES ----------------- //
    public String insertAccessoriesSellPrivate(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPrivate();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_ACCESSORIES, cpAction, issUploadNewImage);
    }

    public String insertAccessoriesSellPro(String ownerToken, String cpAction, boolean issUploadNewImage) {
        setAdToPro();
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_ACCESSORIES, cpAction, issUploadNewImage);
    }

    public String insertAccessoriesSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPET(ownerToken, AD_TYPE_SELL, CATEID_PET_ACCESSORIES, cpAction, isUploadNewImage);
    }

    // ----------------- EDIT AD ----------------- //

    // ----------------- CHICKEN ----------------- //
    public String editChickenSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editChickenSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editChickenSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }
    // ----------------- DOG ------------------------- //
    public String editDogSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editDogSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editDogSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    // ----------------- BIRD ------------------------ //
    public String editBirdSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editBirdSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editBirdSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    // ----------------- CAT ------------------------- //

    public String editCatSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editCatSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editCatSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    // ----------------- OTHERS ---------------------- //
    public String editOthersSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editOthersSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editOthersSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    // ----------------- ACCESSORIES ----------------- //
    public String editAccessoriesSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_PET_CHICKEN, cpAction, isUploadNewImage);
    }

    public String editAccessoriesSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editAccessoriesSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPET(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }
}
