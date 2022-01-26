package desktop.scenarios_old.ads.pos;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_Entertainment extends BaseTest {
    CM_API_Ads_InsertEntertainment cmApiAdsInsertEntertainment;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cmApiAdsInsertEntertainment = new CM_API_Ads_InsertEntertainment();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads Entertainment, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cmApiAdsInsertEntertainment.insertNewAdInstrument();
        checkAdOnDashboard(tempAdID);
        cmApiAdsInsertEntertainment.insertNewAdSport();
        checkAdOnDashboard(tempAdID);
        cmApiAdsInsertEntertainment.insertNewAdCollectibles();
        checkAdOnDashboard(tempAdID);
        cmApiAdsInsertEntertainment.insertNewAdGaming();
        checkAdOnDashboard(tempAdID);
        cmApiAdsInsertEntertainment.insertNewAdBook();
        checkAdOnDashboard(tempAdID);
        cmApiAdsInsertEntertainment.insertNewAdHobby();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads Entertainment, Verify POS of Instrument Ad, Vu Hoang, ME")
    public void verifyPOSOfInstrumentAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Instrument");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdInstrument();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads Entertainment, Verify POS of Sport Ad, Vu Hoang, ME")
    public void verifyPOSOfSportAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Sport");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdSport(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads Entertainment, Verify POS of Book Ad, Vu Hoang, ME")
    public void verifyPOSOfBookAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Book");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdBook(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads Entertainment, Verify POS of Collectibles Ad, Vu Hoang, ME")
    public void verifyPOSOfCollectiblesAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Collectibles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdCollectibles(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads Entertainment, Verify POS of Gaming Ad, Vu Hoang, ME")
    public void verifyPOSOfGamingAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Gaming");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdGaming(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads Entertainment, Verify POS of Hobby Ad, Vu Hoang, ME")
    public void verifyPOSOfHobbyAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Entertainment.xlsx", "Hobby"); // Hobby
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertEntertainment.insertNewAdHobby(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }
}
