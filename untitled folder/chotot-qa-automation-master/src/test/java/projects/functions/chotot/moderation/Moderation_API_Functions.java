package projects.functions.chotot.moderation;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot._common.api.ads.insert.*;

import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class Moderation_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Moderation_API_Functions.class);
    static CM_API_Ads_InsertPTY cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
    static CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
    static CM_API_Ads_InsertPets cm_api_ads_insertPets = new CM_API_Ads_InsertPets();
    static CM_API_Ads_InsertFashion cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
    static CM_API_Ads_InsertELT cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
    static CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
    static CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();

    public void postPrivateAdByCateID(String cate_id, String cp_action) {
        log.info("CATEGORY_ID: " + cate_id);
        switch (cate_id) {
            case "1020":
              cm_api_ads_insertPTY.insertNewAdHouse(true, cp_action);
              break;
            case "4010":
                cm_api_ads_insertEntertainment.insertNewAdCollectibles(true, cp_action);
                break;
            case "12010":
                cm_api_ads_insertPets.insertNewAd_Chicken_NoUploadNewImage(cp_action);
                break;
            case "3030":
                cm_api_ads_insertFashion.insertNewAdClothes(cp_action);
                break;
            case "5030":
                cm_api_ads_insertELT.insertNewAdLaptop(cp_action);
                break;
            case "7010":
                cm_api_ads_insertOther.insertNewFoodAd(cp_action);
                break;
            case "2020":
                cm_api_ads_insertVehicle.insertNewAdMotorbike(cp_action);
            default:
                break;
        }
    }

    public void postProAdByCateID(String cate_id, String cp_action) {
        log.info("CATEGORY_ID: " + cate_id);
        switch (cate_id) {
            case "1020":
                cm_api_ads_insertPTY.insertNewAdHousePro(true, cp_action);
                break;
            case "3030":
                cm_api_ads_insertFashion.insertNewAdClothesPro(cp_action);
                break;
            case "7010":
                cm_api_ads_insertOther.insertNewFoodAdPro(cp_action);
                break;
            default:
                break;
        }
    }

    public void postPrivateBuyAdByCateID(String cate_id, String cp_action) {
        log.info("CATEGORY_ID: " + cate_id);
        switch (cate_id) {
            case "1020":
                cm_api_ads_insertPTY.insertNewAdHouseBuy(true);
                break;
            case "4010":
                cm_api_ads_insertEntertainment.insertNewAdCollectiblesBuy(cp_action);
                break;
            case "3030":
                cm_api_ads_insertFashion.insertNewAdClothesBuy(cp_action);
                break;
            default:
                break;
        }
    }

    public void checkAdStatusOnDashboard(String adID, String expectedStatus) {
        for (int i=1; i<=5;i++) {
            delayStep(maxWaitTime*2); // Wait for ad updated
            if (checkAdOnDashboard(adID, expectedStatus, FailureHandling.WARNING)) return;
        }
        checkAdOnDashboard(adID, expectedStatus, FailureHandling.STOP_ON_FAILURE);
    }
}
