package projects.functions.chotot.flashad;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;

import static api.configuration.DataConfig.*;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class FlashAd_VEH_Functions extends FlashAd_Core_Functions {
    private JsonObject bodyData;
    private String region, area, ward;
    private String subject, body;
    private long price;
    private String filePath = System.getProperty("user.dir") + "/vehicle";
    private boolean isPrivateAd, isProAd, isShopAd, isShopAdToChotot;

    private void restartValues() {
        bodyData = new JsonObject();
        region = "13000";
        area = "13096";
        ward = "9220";
        subject = "Automation Test VEH %s  " + getDateInStringFormat(null, "mmss");
        body = "Automation Test, Insert new Ad VEH. Support to create test data. Category: ";
        price = 1000000000;

        isPrivateAd = false;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = false;
    }

    //==================================================================
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSubject(){
        return subject;
    }

    //==================================================================
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

    private void setBodyData(String ownerToken, String adType, String categoryID, boolean isUploadNewImage) {
        bodyData.addProperty("category", categoryID);
        bodyData.addProperty("subCategory", categoryID);
        bodyData.addProperty("type", adType);
        bodyData.addProperty("region_v2", region);
        bodyData.addProperty("area_v2", area);
        bodyData.addProperty("price", price);
        bodyData.addProperty("body", body + categoryID);
        bodyData.addProperty("condition_ad", "2"); // xe moi

        switch (categoryID) {
            case CATEID_VEH_CAR:
                subject = String.format(subject, "Ô tô");

                bodyData.addProperty("carbrand", "1");
                bodyData.addProperty("carmodel", "21");
                bodyData.addProperty("mfdate", "2021");
                bodyData.addProperty("gearbox", "1");       //Hop so
                break;

            case CATEID_VEH_MOTORBIKE:
                subject = String.format(subject, "Xe máy");

                bodyData.addProperty("motorbikebrand", "1");
                bodyData.addProperty("motorbikemodel", "21");
                bodyData.addProperty("motorbiketype", "1");
                bodyData.addProperty("regdate", "2020");
                break;

            case CATEID_VEH_BICYCLES:
                subject = String.format(subject, "Xe đạp");

                bodyData.addProperty("bicycletype", "1");
                bodyData.addProperty("bicyclesport", "1");
                bodyData.addProperty("bicyclebrand", "1");
                break;

            case CATEID_VEH_TRUCK:
                subject = String.format(subject, "Xe tải");

                bodyData.addProperty("truckbrand", "1");
                bodyData.addProperty("truckweight", "1");
                break;

            case CATEID_VEH_ELECTRIC_VEHICLE:
                subject = String.format(subject, "Xe điện");

                bodyData.addProperty("evehicletype", "1");
                bodyData.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_VEHICLE_PARTS:
                subject = String.format(subject, "Phụ tùng xe");

                bodyData.addProperty("evehicletype", "1");
                bodyData.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_OTHER_VEHICLE:
                subject = String.format(subject, "Phương tiện khác");

                bodyData.addProperty("ovehicletype", "1");
                bodyData.addProperty("svehicletype", "16");
                break;
        }
        bodyData.addProperty("subject", subject);

        //Uplaod image
        if (isUploadNewImage) {
            bodyData.addProperty("image_id0", uploadNewImage(ownerToken, filePath + "/car/honda_civic_1.jpg"));
        } else {
            bodyData.addProperty("image_id0", "2677521862851286347.jpg");
        }
    }


    /**
     * @param adType           s, u , k , h
     * @param cpAction
     * @param isUploadNewImage
     */
    private String insertAdVEH(String ownerToken, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        String adID = insertAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdVEH(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdVEH(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdVEH(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdVEH(adID, cateID, bodyData, true);
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

    /**
     * @param adType           s, u , k , h
     * @param cpAction
     * @param isUploadNewImage
     */
    private String editAdVEH(String ownerToken, String adId, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        bodyData.addProperty("ad_id", adId);
        String adID = editAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdVEH(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdVEH(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdVEH(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdVEH(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdVEH(adID, cateID, bodyData, true);
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



    //==================== CAR ====================
    public String insertCarSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    public String insertCarSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    public String insertCarSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    //==================== Motorbike ====================
    public String insertMotorbikeSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }

    public String insertMotorbikeSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }

    public String insertMotorbikeSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }

    //==================== Bicycles ====================
    public String insertBicyclesSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }

    public String insertBicyclesSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }

    public String insertBicyclesSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }

    //==================== Truck ====================
    public String insertTruckSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }

    public String insertTruckSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }

    public String insertTruckSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }

    //==================== ElectricVehicle ====================
    public String insertElectricVehicleSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    public String insertElectricVehicleSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    public String insertElectricVehicleSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    //==================== OtherVehicle ====================
    public String insertOtherVehicleSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }

    public String insertOtherVehicleSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }

    public String insertOtherVehicleSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }

    //==================== VehiclePart ====================
    public String insertVehiclePartSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }

    public String insertVehiclePartSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }

    public String insertVehiclePartSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdVEH(ownerToken, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }



    //--------------------- EDIT AD ---------------------

    //==================== CAR ====================
    public String editCarSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    public String editCarSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    public String editCarSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_CAR, cpAction, isUploadNewImage);
    }

    //==================== ElectricVehicle ====================
    public String editElectricVehicleSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    public String editElectricVehicleSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    public String editElectricVehicleSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_ELECTRIC_VEHICLE, cpAction, isUploadNewImage);
    }

    //==================== Motorbike ====================
    public String editMotorbikeSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }

    public String editMotorbikeSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }

    public String editMotorbikeSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_MOTORBIKE, cpAction, isUploadNewImage);
    }


    //==================== Bicycles ====================
    public String editBicyclesSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }

    public String editBicyclesSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }

    public String editBicyclesSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_BICYCLES, cpAction, isUploadNewImage);
    }


    //==================== Truck ====================
    public String editTruckSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }

    public String editTruckSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }

    public String editTruckSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_TRUCK, cpAction, isUploadNewImage);
    }


    //==================== OtherVehicle ====================
    public String editOtherVehicleSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }

    public String editOtherVehicleSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }

    public String editOtherVehicleSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_OTHER_VEHICLE, cpAction, isUploadNewImage);
    }


    //==================== VehiclePart ====================
    public String editVehiclePartSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }

    public String editVehiclePartSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }

    public String editVehiclePartSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdVEH(ownerToken, adId, AD_TYPE_SELL, CATEID_VEH_VEHICLE_PARTS, cpAction, isUploadNewImage);
    }





    //--------------------- ACCEPT/ REFUSE AD ---------------------
    private String acceptAdVEH(String adID, String categoryID, JsonObject bodyData) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());

        switch (categoryID) {
            case CATEID_VEH_CAR:
                adParams.addProperty("carbrand", "1");
                adParams.addProperty("carmodel", "21");
                adParams.addProperty("mfdate", "2021");
                adParams.addProperty("gearbox", "1");       //Hop so
                break;

            case CATEID_VEH_MOTORBIKE:
                adParams.addProperty("motorbikebrand", "1");
                adParams.addProperty("motorbikemodel", "21");
                adParams.addProperty("motorbiketype", "1");
                adParams.addProperty("regdate", "2020");
                break;

            case CATEID_VEH_BICYCLES:
                adParams.addProperty("bicycletype", "1");
                adParams.addProperty("bicyclesport", "1");
                adParams.addProperty("bicyclebrand", "1");
                break;

            case CATEID_VEH_TRUCK:
                adParams.addProperty("truckbrand", "1");
                adParams.addProperty("truckweight", "1");
                break;

            case CATEID_VEH_ELECTRIC_VEHICLE:
                adParams.addProperty("evehicletype", "1");
                adParams.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_VEHICLE_PARTS:
                adParams.addProperty("evehicletype", "1");
                adParams.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_OTHER_VEHICLE:
                adParams.addProperty("ovehicletype", "1");
                adParams.addProperty("svehicletype", "16");
                break;
        }

        if (isPrivateAd) {
//            adParams.addProperty("company_ad", false);  //VUHOANG OUTDATE: api of CP accept: is removed this field
            bodyData.addProperty("company_ad", false);
        } else if (isProAd) {
//            adParams.addProperty("company_ad", true);
            bodyData.addProperty("company_ad", true);
        } else if (isShopAd) {
//            adParams.addProperty("shop_to_chotot", false);
            bodyData.addProperty("shop_to_chotot", false);
        } else {
//            adParams.addProperty("shop_to_chotot", true);
            bodyData.addProperty("shop_to_chotot", true);
        }

        adParams.addProperty("area", 96);       //VUHOANG DEBUG
        bodyData.add("ad_params", adParams);
        bodyData.addProperty("ad_id", Integer.valueOf(adID));
        bodyData.addProperty("orig_price", bodyData.get("price").getAsString());

        return acceptAd(bodyData);
    }


    private String refuseAdVEH(String adID, String categoryID, JsonObject bodyData, boolean isAbleToEdit) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());

        switch (categoryID) {
            case CATEID_VEH_CAR:
                adParams.addProperty("carbrand", "1");
                adParams.addProperty("carmodel", "21");
                adParams.addProperty("mfdate", "2021");
                adParams.addProperty("gearbox", "1");       //Hop so
                break;

            case CATEID_VEH_MOTORBIKE:
                adParams.addProperty("motorbikebrand", "1");
                adParams.addProperty("motorbikemodel", "21");
                adParams.addProperty("motorbiketype", "1");
                adParams.addProperty("regdate", "2020");
                break;

            case CATEID_VEH_BICYCLES:
                adParams.addProperty("bicycletype", "1");
                adParams.addProperty("bicyclesport", "1");
                adParams.addProperty("bicyclebrand", "1");
                break;

            case CATEID_VEH_TRUCK:
                adParams.addProperty("truckbrand", "1");
                adParams.addProperty("truckweight", "1");
                break;

            case CATEID_VEH_ELECTRIC_VEHICLE:
                adParams.addProperty("evehicletype", "1");
                adParams.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_VEHICLE_PARTS:
                adParams.addProperty("evehicletype", "1");
                adParams.addProperty("evehiclebrand", "1");
                break;

            case CATEID_VEH_OTHER_VEHICLE:
                adParams.addProperty("ovehicletype", "1");
                adParams.addProperty("svehicletype", "16");
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
}
