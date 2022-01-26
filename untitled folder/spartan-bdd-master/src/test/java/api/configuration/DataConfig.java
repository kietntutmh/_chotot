package api.configuration;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static api.utils.GetAccessToken.getAccessTokenOfExistingUser;
import static api.utils.Images.setUsingRandomImageAgain;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;

public class DataConfig {

    // Login data
    public static final String loginExcelFile = "data/excel/Login.xlsx";
    public static final String loginSheetName = "Login";
    public static final String loginCPSheetName = "CP";
    // App, Language and verify
    public static final String defaultAppID = "desktop_site_flashad";
    // Ads excel data
    public static final String adVehicleExcelFile = "data/excel/api/ads/Ad_Vehicle.xlsx";
    public static final String adPTYExcelFile = "data/excel/api/ads/Ad_PTY.xlsx";
    public static final String adELTExcelFile = "data/excel/api/ads/Ad_ELT.xlsx";
    public static final String adMomAndBabyExcelFile = "data/excel/api/ads/Ad_MomAndBaby.xlsx";
    public static final String adFashionExcelFile = "data/excel/api/ads/Ad_Fashion.xlsx";
    public static final String adElectricAppliancesExcelFile =
            "data/excel/api/ads/Ad_ElectricAppliances.xlsx";
    public static final String adEntertainmentExcelFile = "data/excel/api/ads/Ad_Entertainment.xlsx";
    public static final String adPetExcelFile = "data/excel/api/ads/Ad_Pet.xlsx";
    public static final String adRequisiteExcelFile = "data/excel/api/ads/Ad_Requisite.xlsx";
    public static final String adJobExcelFile = "data/excel/api/ads/Ad_Job.xlsx";
    public static final String adServiceExcelFile = "data/excel/api/ads/Ad_Service.xlsx";
    public static final String adOtherExcelFile = "data/excel/api/ads/Ad_Other.xlsx";
    public static final String adHousehold_Furniture_PlantExcelFile =
            "data/excel/api/ads/Ad_Household_Furniture_Plant.xlsx";
    // Edit Ads
    public static final String adEditELTExcelFile = "data/excel/api/ads_edit/Ad_ELT_EDIT.xlsx";
    public static final String adEditVehicleExcelFile =
            "data/excel/api/ads_edit/Ad_Vehicle_EDIT.xlsx";
    public static final String adEditPTYExcelFile = "data/excel/api/ads_edit/Ad_PTY_EDIT.xlsx";
    public static final String adEditEntertainmentExcelFile =
            "data/excel/api/ads_edit/Ad_Entertainment_EDIT.xlsx";
    public static final String adEditFashionExcelFile =
            "data/excel/api/ads_edit/Ad_Fashion_EDIT.xlsx";
    public static final String adEditServicesExcelFile =
            "data/excel/api/ads_edit/Ad_Services_EDIT.xlsx";
    // Payment
    public static final String napasExcelFile = "data/excel/ui/payment/Napas.xlsx";
    public static final String vimomoExcelFile = "data/excel/ui/payment/Momo.xlsx";
    public static final String defaultPassword = "123456";
    public static final String defaultOTP = "123456";
    public static final String defaultLanguage = "vi";
    static final Logger log = Log4jFactory.instance().createClassLogger(DataConfig.class);
    private static final int productionAccountRowIndex = 2;
    private static final int stagingAccountRowIndex = 5; // This value should be the same as UI config
    private static final int mainAccountAPIRowIndex =
            DOMAIN.equals("com") ? productionAccountRowIndex : stagingAccountRowIndex;
    public static String newUserPhone = "";
    public static String newUserID = "";
    // Temp ad data
    public static String tempAdID = "", tempAdName = "", tempAdCategoryID = "", tempAdType = "", tempAdSubject = "";
    public static List<String> tempAdIDList = null;
    public static String tempListID = "";
    public static String tempAdEditSubject = "";
    public static String tempUID = ""; // Using for CP. Get after insertAd
    public static String tempShopID = "";
    public static boolean isEditedAd = false;
    // Temp user data
    public static String tempAccountID = "";
    // Retry Insert Ad:
    public static int tempRetryInsertAd = 3; // Retry to insert Ad if failed
    public static int tempRetryCPActionAd = 5; // Retry to accept Ad CP if failed
    public static int tempRetryEditAd = 3; // Retry to accept Ad CP if failed
    public static int tempRetryPayOrder = 5; // Retry to pay order if failed
    private static int tempAccountAPIRowIndex = -1;
    private static int tempAccountCPAPIRowIndex = -1;
    private static int tempRetryCreateShop = 3;
    private static int tempRetry = 10; // Using for others

    private static boolean isTestingModeration = false;
    private static boolean isUploadingImage = false;
    private static boolean isGeneratingRandomImage = false;

    // Payment with MoMo
    public static final String VIMOMO_PASS = "000000";
    public static final String VIMOMO_OTP = "000000";

    public static void setIsTestingModeration(boolean isModeration) {
        isTestingModeration = isModeration;
    }

    public static boolean getIsTestingModeration() {
        return isTestingModeration;
    }

    public static void setIsGeneratingRandomImage(boolean isRandomImage) {
        isGeneratingRandomImage = isRandomImage;
    }

    public static boolean getIsGeneratingRandomImage() {
        return isGeneratingRandomImage;
    }

    public static void setIsUploadingImage(boolean doUploadImage) {
        isUploadingImage = doUploadImage;
    }

    public static boolean getIsUploadingImage() {
        return isUploadingImage;
    }

    public static String getPrefixAdSubjectModeration() {
        return getIsTestingModeration() ? "::":"";
    }

    public static void tempRetryResetToDefault() {
        tempRetryInsertAd = 3;
        tempRetryCPActionAd = 5;
        tempRetryEditAd = 3;
        tempRetryCreateShop = 3;
        tempRetryPayOrder = 2;
        tempRetry = 3;
        isEditedAd = false;
        setIsTestingModeration(false);
        setIsUploadingImage(false);
        setUsingRandomImageAgain(false);
    }

    public static void tempRetrySetToTest() {
        tempRetryInsertAd = 5;
        tempRetryCPActionAd = 8;
        tempRetryEditAd = 5;
        tempRetryCreateShop = 3;
        tempRetryPayOrder = 2;
        tempRetry = 6;
    }

    public static int getTempRetry() {
        return tempRetry;
    }

    public static int getTempRetryInsertAd() {
        return tempRetryInsertAd;
    }

    public static int getTempRetryCPActionAd() {
        return tempRetryCPActionAd;
    }

    public static int getTempRetryEditAd() {
        return tempRetryEditAd;
    }

    public static int getMainAccountAPIIndex() {
        return mainAccountAPIRowIndex;
    }

    public static int getTempRetryCreateShop() {
        return tempRetryCreateShop;
    }

    public static int getTempRetryRegister() {
        return 5;
    }

    public static int getTempAccountAPIIndex() {
        return tempAccountAPIRowIndex;
    }

    public static void setTempAccountAPIIndex(int index) {
        if (index > -1) {
            log.info("\n----- Setup API account to index: " + index);
            setExceptionDebug("Index of temp user phone (API): " + index);
        }
        tempAccountAPIRowIndex = index;
    }

    public static boolean checkTempAccountAPIUsed() {
        return tempAccountAPIRowIndex > 0;
    }

    public static void setTempAccountAPIAndGetToken(int index) {
        setTempAccountAPIIndex(index);
        getAccessTokenOfExistingUser();
    }

    public static int getMainAccountCPAPIIndex() {
        return 1;
    }

    public static int getTempAccountCPAPIIndex() {
        return tempAccountCPAPIRowIndex;
    }

    public static void setTempAccountCPAPIIndex(int index) {
        if (index > -1) log.info("\n----- Setup API account CP to index: " + index);
        tempAccountCPAPIRowIndex = index;
    }

    public static int getTempRetrySendChatMessage() {
        //Retry to send chat
        int tempRetrySendChatMessage = 3;
        return tempRetrySendChatMessage;
    }

    public static boolean checkTempAccountCPAPIUsed() {
        return tempAccountCPAPIRowIndex > 0;
    }
}
