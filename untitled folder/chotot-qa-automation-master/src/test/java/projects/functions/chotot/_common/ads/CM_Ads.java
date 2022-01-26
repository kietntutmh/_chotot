package projects.functions.chotot._common.ads;

import com.vn.chotot.base.BasePage;
import desktop.components.TopHeader;
import desktop.pages.Dashboard.ManageAds;
import projects.functions.chotot._common.CM_Login;

import java.util.List;

public class CM_Ads extends BasePage {

    public static long startTimeInsert;
    CM_Login cm_login;
    TopHeader topHeader;
    ManageAds manageAds;

    public String goToInsertAds() {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();

        // Login and insert ad
        cm_login.loginAndInsertAd();

        // Go to manage ads dashboard
        //topHeader.clickIBuy();

        // Get current ads info: ĐANG BÁN (12) BỊ TỪ CHỐI (33) CẦN THANH TOÁN (221) KHÁC (410)
        //String currentAdsInfo = new ManageAds().getTabMenuInfo();

        // Get timestamp
        startTimeInsert = System.currentTimeMillis();

        // Go to insert ad
        topHeader.clickInsertAds();

        return null;
    }

    public List<String> goToInsertAdsShop() {
        // Initiate object instances
        cm_login = new CM_Login();
        topHeader = new TopHeader();
        manageAds = new ManageAds();

        // Login and insert ad
        cm_login.loginAndInsertAd();

        // Go to manage ads dashboard on Shop
        //topHeader.clickIBuy();

        // Get current Private ads info
        //String currentPrivateAdsInfo = manageAds.getTabMenuInfo();

        // Go to manage ads dasboard on Shop
        //manageAds.goToShop();

        // Get current manage ads dashboard on Shop
        //String currentShopAdsInfo = manageAds.getTabMenuInfo();

        // Get timestamp
        startTimeInsert = System.currentTimeMillis();

        // Go to insert ad
        topHeader.clickInsertAds();

        //List<String> result = new ArrayList<>();
        //result.add(currentPrivateAdsInfo);
        //result.add(currentShopAdsInfo);

        return null;
    }
}
