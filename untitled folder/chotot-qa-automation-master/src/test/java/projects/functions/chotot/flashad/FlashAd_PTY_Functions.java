package projects.functions.chotot.flashad;


import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accountID;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.pos.POS_Functions.posBump_NotDevivery;
import static  projects.functions.chotot.pos.POS_Functions.posSpecialDisplay_AddCart;

public class FlashAd_PTY_Functions extends FlashAd_Core_Functions {
    private JsonObject bodyData;
    private String region, area, ward;
    private String subject, body;
    private long price;
    private String filePath = System.getProperty("user.dir") + "/pty";
    private boolean isPrivateAd, isProAd, isShopAdToChotot, isShopAd;

    private void restartValues() {
        bodyData = new JsonObject();
        region = "13000";
        area = "13096";
        ward = "9220";
        subject = "Automation Test PTY %s " + getDateInStringFormat(null, "mmss");
        body = "Automation Test, Insert new Ad PTY. Support to create test data. Category: ";
        price = 1000000000;

        isPrivateAd = false;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = false;
    }

    public void setRegion(String regionID) {
        region = regionID;
    }

    public void setArea(String areaID) {
        area = areaID;
        //getArea by region
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }

    //Set Region
    public void setRegion_HCM(){
        region = "13000";
        area = "13096";      //Quan 1
        ward = "9219";       //P. Ben Nghe
    }

    public void setRegion_BinhDuong(){
        region = "2011";
        area = "201101";     //Thủ dầu một
        ward = "8886";       //Phuong Chánh Mỹ
    }

    public void setRegion_HaNoi(){
        region = "12000";
        area = "12074";     //Quan Ba Dinh
        ward = "4";         //Phường Cống Vị
    }

    //Area 8
    public void setRegion_HaTinh(){
        region = "8049";
        area = "804901";     //Thành phố Hà Tĩnh
        ward = "6142";       //Phường Bắc Hà
    }

    //Area 10: Dong Bac Bo
    public void setRegion_BacCan(){
        region = "10059";
        area = "1005903";      //Huyện Ba Bể"
        ward = "997";          //Thị trấn Chợ Rã
    }

    //Area 11: Tay Bac Bo
    public void setRegion_LaiChau(){
        region = "11068";
        area = "1106801";      //Thành phố Lai Châu
        ward = "1539";         //Phường Đoàn Kết
    }

    //==================================================================
    private void setAdToPrivate() {
        restartValues();
        isPrivateAd = true;
        isProAd = false;
        isShopAd = false;
        isShopAdToChotot = false;
        bodyData.addProperty("company_ad", 0);
    }

    private void setAdToPro() {
        restartValues();
        isPrivateAd = false;
        isProAd = true;
        isShopAd = false;
        isShopAdToChotot = false;
        bodyData.addProperty("company_ad", 1);
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

    //PTY
    private void setBodyData(String ownerToken, String adType, String categoryID, boolean isUploadNewImage) {
        bodyData.addProperty("category", categoryID);
        bodyData.addProperty("subCategory", categoryID);
        bodyData.addProperty("type", adType);
        bodyData.addProperty("region_v2", region);
        bodyData.addProperty("area_v2", area);
        bodyData.addProperty("price", price);
        bodyData.addProperty("ward", ward);
        bodyData.addProperty("body", body + categoryID);

        //Common Info
        bodyData.addProperty("address", "88 Lê Lợi, P. Bến Thành, Quận 1,hcm");
        bodyData.addProperty("street_id", 0);
        bodyData.addProperty("projectid", 4609);
        bodyData.addProperty("direction", "1");     //Huong Dong
        bodyData.addProperty("property_legal_document", "1");     //1 - Đã có sổ, 2 - Đang chờ sổ, 3 - Giấy tờ khác
        bodyData.addProperty("size", "150");       //Dien tich

        switch (categoryID) {
            case CATEID_PTY_HOUSE:
                subject = String.format(subject, "HOUSE");

                //House info
                bodyData.addProperty("house_type", "1");    //Loai hinh nha o: 1 - Nha mat pho, 2 - Nha ngõ, 3 - Nhà biệt thự, 4 - Nhà phố liền kề
                bodyData.addProperty("rooms", "1");         //So phong ngu
                bodyData.addProperty("furnishing_sell", "1");     //Tinh trang Noi That: cao cap
                bodyData.addProperty("property_road_condition", "1");     //Hem xe hoi
                bodyData.addProperty("block", "Lô Auto");     //Lô
                bodyData.addProperty("floors", 1);     //Tầng
                bodyData.addProperty("unitnumber", "MaCanHoAuto");     //Mã căn hộ
                bodyData.addProperty("unitnumber_display", "1");     //Hiển thị Mã căn hộ trong tin rao
                break;

            case CATEID_PTY_APARTMENT:
                subject = String.format(subject, "APARTMENT");

                //Apartment Info
                bodyData.addProperty("apartment_type", "1");
                bodyData.addProperty("property_status", "2");     //1 - chua ban giao, 2 - da ban giao
                bodyData.addProperty("rooms", "1");
                bodyData.addProperty("toilets", "1");
                bodyData.addProperty("balconydirection", "1");    //Huong ban cong
                bodyData.addProperty("furnishing_sell", "1");     //Tinh trang Noi That: cao cap
                bodyData.addProperty("apartment_feature", "1");    //Căn góc
                bodyData.addProperty("block", "Lô Auto");     //Lô
                bodyData.addProperty("floors", 1);     //Tầng
                bodyData.addProperty("unitnumber", "MaCanHoAuto");     //Mã căn hộ
                bodyData.addProperty("unitnumber_display", "1");     //Hiển thị Mã căn hộ trong tin rao
                break;

            case CATEID_PTY_LAND:
                subject = String.format(subject, "LAND");

                //Land Info
                bodyData.addProperty("land_type", "1");    //Loai dat
                bodyData.addProperty("land_feature", "1");
                bodyData.addProperty("property_road_condition", "1");
                bodyData.addProperty("property_back_condition", "1");
                bodyData.addProperty("unitnumber", "MaCanHoAuto");     //Mã căn hộ
                bodyData.addProperty("unitnumber_display", "1");     //Hiển thị Mã căn hộ trong tin rao
                bodyData.addProperty("size_unit", "1");    //Dien tich
                break;

            case CATEID_PTY_OFFICE:
                subject = String.format(subject, "OFFICE");

                //Office Info
                bodyData.addProperty("commercial_type", "1");
                bodyData.addProperty("furnishing_sell", "1");     //Tinh trang Noi That: cao cap
                bodyData.addProperty("block", "Lô Auto");     //Lô
                bodyData.addProperty("floornumber", 1);     //Tầng
                bodyData.addProperty("unitnumber", "MaCanHoAuto");     //Mã căn hộ
                bodyData.addProperty("unitnumber_display", "1");     //Hiển thị Mã căn hộ trong tin rao
                break;

            case CATEID_PTY_ROOMFORRENT:
                subject = String.format(subject, "ROOM");

                //Room Info
                bodyData.addProperty("street_number", "123");
                bodyData.addProperty("streetnumber_display", "1");
                bodyData.addProperty("deposit", "100000");
                bodyData.addProperty("furnishing_rent", "1");
                bodyData.addProperty("street_id", 36091);
                bodyData.addProperty("address", "Đường Âu Cơ");
                break;
        }

        bodyData.addProperty("subject", subject);

        //Uplaod image
        if (isUploadNewImage) {
            bodyData.addProperty("image_id0", uploadNewImage(ownerToken, filePath + "/house/house01.jpg"));
            bodyData.addProperty("image_id1", uploadNewImage(ownerToken, filePath + "/house/house02.jpg"));
            bodyData.addProperty("image_id2", uploadNewImage(ownerToken, filePath + "/house/house03.jpg"));
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
    private String insertAdPTY(String ownerToken, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        String adID = insertAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdPTY(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            case "paydt4b":
                paymentOrderDT4B();
                break;

            // Buy POS while inserting new ad, then Ad having Bump is refused
            case "refusepay_withbump":
                posBump_NotDevivery(ownerToken, adID);
                paymentOrderWithDongTot();
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b_withbump":
                posBump_NotDevivery(ownerToken, adID);
                paymentOrderDT4B();
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusepay_withsda":
                posSpecialDisplay_AddCart(ownerToken, tempAdID, 7);
                paymentOrderWithDongTot();
                refuseAdPTY(adID, cateID, bodyData, true);
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
    private String editAdPTY(String ownerToken, String adId, String adType, String cateID, String cpAction, boolean isUploadNewImage) {
        setBodyData(ownerToken, adType, cateID, isUploadNewImage);     //s, u, k, h
        bodyData.addProperty("ad_id", adId);
        String adID = editAd(ownerToken, bodyData, "200");

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                acceptAdPTY(adID, cateID, bodyData);
                break;

            case "refuse":
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusenonedit":
                refuseAdPTY(adID, cateID, bodyData, false);
                break;

            case "refusepay":
                paymentOrderWithDongTot();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPTY(adID, cateID, bodyData, true);
                break;

            case "refusepaydt4b":
                paymentOrderDT4B();
                checkAdInUserAds_PaymentStatus(adID, "paid");
                refuseAdPTY(adID, cateID, bodyData, true);
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

    //==================== HOUSE ====================
    public String insertHouseSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseRentPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseRentPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseRentShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }


    public String insertHouseBuyPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdPTY(ownerToken, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseBuyPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdPTY(ownerToken, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String insertHouseBuyShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPTY(ownerToken, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    //================== APARTMENT ====================
    public String insertApartmentSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String insertApartmentSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String insertApartmentSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }


    public String insertApartmentRentPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String insertApartmentRentPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String insertApartmentRentShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    //================== LAND ====================
    public String insertLandSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String insertLandSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String insertLandSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }


    public String insertLandRentPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String insertLandRentPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String insertLandRentShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    //================== OFFICE ====================
    public String insertOfficeSellPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String insertOfficeSellPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String insertOfficeSellShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return insertAdPTY(ownerToken, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }


    public String insertOfficeRentPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String insertOfficeRentPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String insertOfficeRentShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    //================== ROOM ====================
    public String insertRoomRentPrivate(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }

    public String insertRoomRentPro(String ownerToken, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }

    public String insertRoomRentShop(String ownerToken, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return insertAdPTY(ownerToken, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }


    //--------------------- EDIT AD -------------------
    //==================== HOUSE ====================
    public String editHouseSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseRentPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseRentPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseRentShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }


    public String editHouseBuyPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPTY(ownerToken, adId, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseBuyPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPTY(ownerToken, adId, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    public String editHouseBuyShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPTY(ownerToken, adId, AD_TYPE_BUY, CATEID_PTY_HOUSE, cpAction, isUploadNewImage);
    }

    //================== APARTMENT ====================
    public String editApartmentSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String editApartmentSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String editApartmentSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }


    public String editApartmentRentPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String editApartmentRentPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    public String editApartmentRentShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_APARTMENT, cpAction, isUploadNewImage);
    }

    //================== LAND ====================
    public String editLandSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String editLandSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String editLandSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }


    public String editLandRentPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String editLandRentPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    public String editLandRentShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_LAND, cpAction, isUploadNewImage);
    }

    //================== OFFICE ====================
    public String editOfficeSellPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String editOfficeSellPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String editOfficeSellShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        return editAdPTY(ownerToken, adId, AD_TYPE_SELL, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }


    public String editOfficeRentPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String editOfficeRentPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    public String editOfficeRentShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_OFFICE, cpAction, isUploadNewImage);
    }

    //================== ROOM ====================
    public String editRoomRentPrivate(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPrivate();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }

    public String editRoomRentPro(String ownerToken, String adId, String cpAction, boolean isUploadNewImage) {
        setAdToPro();
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }

    public String editRoomRentShop(String ownerToken, String adId, String cpAction, boolean postToChotot, boolean isUploadNewImage) {
        if (postToChotot) {
            setAdToShopToChotot();
        } else {
            setAdToShop();
        }
        setRegion(REGION_HCM_ID);
        return editAdPTY(ownerToken, adId, AD_TYPE_RENT, CATEID_PTY_ROOMFORRENT, cpAction, isUploadNewImage);
    }


    //--------------------- ACCEPT/ REFUSE AD ---------------------
    private String acceptAdPTY(String adID, String categoryID, JsonObject bodyData) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());
        adParams.addProperty("street_id", bodyData.get("street_id").getAsInt());

        switch (categoryID) {
            case CATEID_PTY_HOUSE:
                adParams.addProperty("house_type", bodyData.get("house_type").getAsInt());
                break;
            case CATEID_PTY_APARTMENT:
                adParams.addProperty("apartment_type", bodyData.get("apartment_type").getAsInt());
                break;
            case CATEID_PTY_LAND:
                adParams.addProperty("land_type", bodyData.get("land_type").getAsInt());
                break;
            case CATEID_PTY_OFFICE:
                adParams.addProperty("commercial_type", bodyData.get("commercial_type").getAsInt());
                break;
            case CATEID_PTY_ROOMFORRENT:
            default:
                break;
        }

        /*
        if (isPrivateAd) {
            adParams.addProperty("company_ad", false);
        } else if (isProAd) {
            adParams.addProperty("company_ad", true);
        } else if (isShopAd) {
            adParams.addProperty("shop_to_chotot", false);
        } else {
            adParams.addProperty("shop_to_chotot", true);
        }
         */

        adParams.addProperty("area", 96);

        //Standardize JSON Body
        bodyData.add("ad_params", adParams);
        bodyData.addProperty("ad_id", Integer.valueOf(adID));
        bodyData.addProperty("orig_price", bodyData.get("price").getAsString());
        bodyData.addProperty("user_id", global_accountID);
        bodyData.addProperty("status", "inactive");
        bodyData.addProperty("region", 13);
        bodyData.addProperty("ad_has_images", true);
        bodyData.remove("subCategory");

        if (isPrivateAd) {
            bodyData.addProperty("company_ad", false);
        } else if (isProAd) {
            bodyData.addProperty("company_ad", true);
        } else if (isShopAd) {
            bodyData.addProperty("shop_to_chotot", false);
        } else {
            bodyData.addProperty("shop_to_chotot", true);
        }

        return acceptAd(bodyData);
    }


    private String refuseAdPTY(String adID, String categoryID, JsonObject bodyData, boolean isAbleToEdit) {
        JsonObject adParams = new JsonObject();
        adParams.addProperty("region_v2", bodyData.get("region_v2").getAsInt());
        adParams.addProperty("area_v2", bodyData.get("area_v2").getAsInt());
        adParams.addProperty("street_id", bodyData.get("street_id").getAsInt());

        switch (categoryID) {
            case CATEID_PTY_HOUSE:
                adParams.addProperty("house_type", bodyData.get("house_type").getAsInt());
                break;
            case CATEID_PTY_APARTMENT:
                adParams.addProperty("apartment_type", bodyData.get("apartment_type").getAsInt());
                break;
            case CATEID_PTY_LAND:
                adParams.addProperty("land_type", bodyData.get("land_type").getAsInt());
                break;
            case CATEID_PTY_OFFICE:
                adParams.addProperty("commercial_type", bodyData.get("commercial_type").getAsInt());
                break;
            case CATEID_PTY_ROOMFORRENT:
            default:
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
