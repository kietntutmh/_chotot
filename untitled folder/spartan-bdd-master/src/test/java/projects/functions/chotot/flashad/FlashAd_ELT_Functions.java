package projects.functions.chotot.flashad;

import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;

import static api.configuration.DataConfig.*;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class FlashAd_ELT_Functions extends FlashAd_Core_Functions {
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
        subject = "Automation Test ELT %s  " + getDateInStringFormat(null, "mmss");
        body = "Automation Test, Insert new Ad ELT. Support to create test data. Category: ";
        address = "2 Đường Ngô Đức Kế (Duong Ngo Duc Ke), Quận 1, Ho Chi Minh City, Vietnam";
        price = 8000000;

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
        bodyData.addProperty("address", address);
        bodyData.addProperty("price", price);
        bodyData.addProperty("body", body + categoryID);
        bodyData.addProperty("elt_condition", "1"); // xe moi
        bodyData.addProperty("latitude", 10.77456);
        bodyData.addProperty("longitude", 106.706039);
        bodyData.addProperty("payment_delivery", "202");

        switch (categoryID) {
            case CATEID_ELT_PHONE:
                subject = String.format(subject, "Điện thoại");

                bodyData.addProperty("mobile_type", "1");
                bodyData.addProperty("mobile_brand", "1");
                bodyData.addProperty("mobile_model", "5");
                bodyData.addProperty("mobile_capacity", "2");
                bodyData.addProperty("mobile_color", "3");
                break;
            case CATEID_ELT_TVSOUND:
                subject = String.format(subject, "Tivi, Âm thanh");

                bodyData.addProperty("auvi_type", "1");
                bodyData.addProperty("auvi_brand", "1");
                bodyData.addProperty("tivi_type", "1");
                bodyData.addProperty("tivi_display_resolution", "1");
                bodyData.addProperty("auvi_resolution", "1");
                bodyData.addProperty("auvi_internet", "1");
                break;

            case CATEID_ELT_LAPTOP:
                subject = String.format(subject, "Laptop");

                bodyData.addProperty("pc_brand", "3");
                bodyData.addProperty("pc_model", "4");
                bodyData.addProperty("pc_cpu", "4");
                bodyData.addProperty("pc_ram", "7");
                bodyData.addProperty("pc_drive_capacity", "2");
                bodyData.addProperty("pc_drive_type", "2");
                bodyData.addProperty("pc_vga", "3");
                bodyData.addProperty("laptop_screen_size", "2");
                break;

            case CATEID_ELT_TABLET:
                subject = String.format(subject, "Máy tính bảng");

                bodyData.addProperty("tablet_brand", "1");
                bodyData.addProperty("tablet_model", "7");
                bodyData.addProperty("tablet_capacity", "2");
                bodyData.addProperty("tablet_screen_size", "1");
                break;

            case CATEID_ELT_CAMERA:
                subject = String.format(subject, "Máy ảnh");

                bodyData.addProperty("camera_type", "1");
                bodyData.addProperty("camera_brand", "7");
                break;

            case CATEID_ELT_ACCESSORIES:
                subject = String.format(subject, "Phụ kiện (Màn hình, Chuột...)");

                bodyData.addProperty("accessories_class", "1");
                bodyData.addProperty("accessories_type", "1");
                break;

            case CATEID_ELT_PC:
                subject = String.format(subject, "Máy tính để bàn");

                bodyData.addProperty("pc_cpu", "1");
                bodyData.addProperty("pc_ram", "1");
                bodyData.addProperty("pc_drive_capacity", "1");
                bodyData.addProperty("pc_drive_type", "1");
                bodyData.addProperty("pc_vga", "3");
                break;

            case CATEID_ELT_PCCOMPONENT:
                subject = String.format(subject, "Linh kiện (RAM, Card...)");

                bodyData.addProperty("component_class", "1");
                bodyData.addProperty("component_type", "1");
                break;

            case CATEID_ELT_WEARABLE:
                subject = String.format(subject, "Thiết bị đeo thông minh");

                bodyData.addProperty("swatch_brand", "2");
                bodyData.addProperty("swatch_model", "11");
                bodyData.addProperty("swatch_type", "1");
                break;
        }

        bodyData.addProperty("subject", subject);

        //Uplaod image
        if (isUploadNewImage) {
            bodyData.addProperty("image_id0", uploadNewImage(ownerToken, filePath + "/phone/phone1.jpg"));
            bodyData.addProperty("image_id1", uploadNewImage(ownerToken, filePath + "/phone/phone2.jpg"));
            bodyData.addProperty("image_id2", uploadNewImage(ownerToken, filePath + "/phone/phone3.jpg"));
        } else {
            bodyData.addProperty("image_id0", "2677521862851286347.jpg");
            bodyData.addProperty("image_id1", "2677521868394697232.jpg");
            bodyData.addProperty("image_id2", "2677521875585389899.jpg");
        }
    }

    /**
     * @param adType           s, u , k , h
     * @param cpAction
     * @param isUploadNewImage
     */
    private String insertAdELT(String ownerToken, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        String adID = insertAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdELT(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdELT(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdELT(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdELT(adID, cateID, bodyData, true);
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
    private String editAdELT(String ownerToken, String adId, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        bodyData.addProperty("ad_id", adId);
        String adID = editAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdELT(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdELT(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":   //VUHOANG DEBUG
                refuseAdELT(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdELT(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdELT(adID, cateID, bodyData, true);
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
    public String insertPhoneSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String insertPhoneSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String insertPhoneSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }



    public String insertTVSoundSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }

    public String insertTVSoundSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }

    public String insertTVSoundSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }



    public String insertLaptopSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }

    public String insertLaptopSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }

    public String insertLaptopSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }



    public String insertTabletSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }

    public String insertTabletSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }

    public String insertTabletSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }



    public String insertCameraSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }

    public String insertCameraSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }

    public String insertCameraSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }



    public String insertAccessoriesSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }

    public String insertAccessoriesSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }

    public String insertAccessoriesSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }



    public String insertPCComponentSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }

    public String insertPCComponentSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }

    public String insertPCComponentSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }



    public String insertWearableSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }

    public String insertWearableSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }

    public String insertWearableSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }



    public String insertPCSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }

    public String insertPCSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }

    public String insertPCSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdELT(ownerToken, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }


    //--------------------- EDIT AD ---------------------
    public String editPhoneSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editPhoneSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }

    public String editPhoneSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PHONE, cpAction, isUploadNewImage);
    }



    public String editTabletSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }

    public String editTabletSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }

    public String editTabletSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TABLET, cpAction, isUploadNewImage);
    }




    public String editLaptopSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }

    public String editLaptopSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }

    public String editLaptopSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_LAPTOP, cpAction, isUploadNewImage);
    }




    public String editPCSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }

    public String editPCSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }

    public String editPCSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PC, cpAction, isUploadNewImage);
    }




    public String editPCComponentSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }

    public String editPCComponentSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }

    public String editPCComponentSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_PCCOMPONENT, cpAction, isUploadNewImage);
    }




    public String editCameraSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }

    public String editCameraSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }

    public String editCameraSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_CAMERA, cpAction, isUploadNewImage);
    }




    public String editAccessoriesSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }

    public String editAccessoriesSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }

    public String editAccessoriesSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_ACCESSORIES, cpAction, isUploadNewImage);
    }




    public String editTVSoundSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }

    public String editTVSoundSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }

    public String editTVSoundSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_TVSOUND, cpAction, isUploadNewImage);
    }



    public String editWearableSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }

    public String editWearableSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }

    public String editWearableSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdELT(ownerToken, adId, AD_TYPE_SELL, CATEID_ELT_WEARABLE, cpAction, isUploadNewImage);
    }



    //--------------------- ACCEPT/ REFUSE AD ---------------------
    private String acceptAdELT(String adID, String categoryID, JsonObject bodyData) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());


        switch (categoryID) {
            case CATEID_ELT_PHONE:
                adParams.addProperty("subject", String.format(subject, "Điện thoại"));

                adParams.addProperty("mobile_type", "1");
                adParams.addProperty("mobile_brand", "1");
                break;

            case CATEID_ELT_TVSOUND:
                adParams.addProperty("subject", String.format(subject, "Tivi, Âm thanh"));

                adParams.addProperty("auvi_type", "1");
                adParams.addProperty("auvi_brand", "1");
                break;

            case CATEID_ELT_LAPTOP:
                adParams.addProperty("subject", String.format(subject, "Laptop"));

                adParams.addProperty("pc_brand", "3");
                adParams.addProperty("pc_model", "4");
                break;

            case CATEID_ELT_TABLET:
                adParams.addProperty("subject", String.format(subject, "Máy tính bảng"));

                adParams.addProperty("tablet_brand", "1");
                adParams.addProperty("tablet_model", "7");
                break;

            case CATEID_ELT_CAMERA:
                adParams.addProperty("subject", String.format(subject, "Máy ảnh"));

                adParams.addProperty("camera_type", "1");
                adParams.addProperty("camera_brand", "7");
                break;

            case CATEID_ELT_ACCESSORIES:
                adParams.addProperty("subject", String.format(subject, "Phụ kiện (Màn hình, Chuột...)"));

                adParams.addProperty("accessories_class", "1");
                adParams.addProperty("accessories_type", "1");
                break;

            case CATEID_ELT_PC:
                adParams.addProperty("subject", String.format(subject, "Máy tính để bàn"));

                adParams.addProperty("pc_cpu", "1");
                adParams.addProperty("pc_ram", "1");
                break;

            case CATEID_ELT_PCCOMPONENT:
                adParams.addProperty("subject", String.format(subject, "Linh kiện (RAM, Card...)"));

                adParams.addProperty("component_class", "1");
                adParams.addProperty("component_type", "1");
                break;

            case CATEID_ELT_WEARABLE:
                adParams.addProperty("subject", String.format(subject, "Thiết bị đeo thông minh"));

                adParams.addProperty("swatch_brand", "2");
                adParams.addProperty("swatch_model", "11");
                adParams.addProperty("swatch_type", "1");
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

        adParams.addProperty("area", 96);       //VUHOANG DEBUG
        bodyData.add("ad_params", adParams);
        bodyData.addProperty("ad_id", Integer.valueOf(adID));
        bodyData.addProperty("orig_price", bodyData.get("price").getAsString());

        return acceptAd(bodyData);
    }


    private String refuseAdELT(String adID, String categoryID, JsonObject bodyData, boolean isAbleToEdit) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());

        switch (categoryID) {
            case CATEID_ELT_PHONE:
                adParams.addProperty("subject", String.format(subject, "Điện thoại"));

                adParams.addProperty("mobile_type", "1");
                adParams.addProperty("mobile_brand", "1");
                break;

            case CATEID_ELT_TVSOUND:
                adParams.addProperty("subject", String.format(subject, "Tivi, Âm thanh"));

                adParams.addProperty("auvi_type", "1");
                adParams.addProperty("auvi_brand", "1");
                break;

            case CATEID_ELT_LAPTOP:
                adParams.addProperty("subject", String.format(subject, "Laptop"));

                adParams.addProperty("pc_brand", "3");
                adParams.addProperty("pc_model", "4");
                break;

            case CATEID_ELT_TABLET:
                adParams.addProperty("subject", String.format(subject, "Máy tính bảng"));

                adParams.addProperty("tablet_brand", "1");
                adParams.addProperty("tablet_model", "7");
                break;

            case CATEID_ELT_CAMERA:
                adParams.addProperty("subject", String.format(subject, "Máy ảnh"));

                adParams.addProperty("camera_type", "1");
                adParams.addProperty("camera_brand", "7");
                break;

            case CATEID_ELT_ACCESSORIES:
                adParams.addProperty("subject", String.format(subject, "Phụ kiện (Màn hình, Chuột...)"));

                adParams.addProperty("accessories_class", "1");
                adParams.addProperty("accessories_type", "1");
                break;

            case CATEID_ELT_PC:
                adParams.addProperty("subject", String.format(subject, "Máy tính để bàn"));

                adParams.addProperty("pc_cpu", "1");
                adParams.addProperty("pc_ram", "1");
                break;

            case CATEID_ELT_PCCOMPONENT:
                adParams.addProperty("subject", String.format(subject, "Linh kiện (RAM, Card...)"));

                adParams.addProperty("component_class", "1");
                adParams.addProperty("component_type", "1");
                break;

            case CATEID_ELT_WEARABLE:
                adParams.addProperty("subject", String.format(subject, "Thiết bị đeo thông minh"));

                adParams.addProperty("swatch_brand", "2");
                adParams.addProperty("swatch_model", "11");
                adParams.addProperty("swatch_type", "1");
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
