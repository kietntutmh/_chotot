package projects.steps.chotot.desktop;

import desktop.pages.Dashboard.ManageAds;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.TestBDDFunctions;

import java.util.List;

import static com.vn.chotot.keywords.selenium.Page.openURL;
import static desktop.configuration.BaseConfig.showPOS;
import static desktop.configuration.HomeConfig.homeURL;

public class ChototVerifySteps extends TestBDDFunctions {
    private static String oldAdsInfo = "";  //Info of Ad needs to verify displaying on Private Dashboard
    private static List<String> oldListAdsInfro = null;  //Info of Ad needs to verify displaying on Private Dashboard

    public static String getOldAdsInfo() {
        return oldAdsInfo;
    }

    public static void setOldAdsInfo(String oldAdsInfo) {
        ChototVerifySteps.oldAdsInfo = oldAdsInfo;
    }

    public static List<String> getOldListAdsInfo() {
        return oldListAdsInfro;
    }

    public static void setOldListAdsInfo(List<String> oldListAdsInfo) {
        ChototVerifySteps.oldListAdsInfro = oldListAdsInfo;
    }

    //Navigation Steps
    @When("I am on Chợ Tốt - Trang Chủ")
    public void i_am_on_Chotot_Home_Page(){
        openURL(homeURL);
    }

    @When("I am on Chợ Tốt - Tôi Bán")
    public void i_am_on_Chotot_ToiBan(){
//        openURL(insertAdURL);     //Need to split step: Login and go to page
    }

    //Common Steps
    @When("I login with registered username and password")
    public void i_login_with_registered_username_and_password() {
    }

    @Given("I am on Đăng Tin page")
    public void i_am_on_post_ad_page() {
    }

    //Private Dashboard
    @Then("I should see Manage Ad page displayed")
    public void verifyManageAdPageDisplayed(){
        if (!showPOS) new ManageAds().verifyManageAdPage();
    }

    //Private Dashboard
    @Then("I should see my New Ad displays on Private Dashboard")
    public void verifyAdDisplayOnDashboard(){
        ManageAds manageAds = new ManageAds();

        // Verify new add inserted successfully
        Assert.assertNotEquals(getOldAdsInfo(), "");
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(getOldAdsInfo());

        //Reinit
        setOldAdsInfo("");
    }

    //Shop Dashboard
    //VUHOANGDEBUG: xoa, unused, nhung chua co time xoa tan goc
//    @Then("I should see my New Ad displays on Shop Dashboard")
//    public void verifyAdDisplayOnShopDashboard(){
//        ManageAds manageAds = new ManageAds();
//
//        // Verify new add inserted successfully
//        Assert.assertNotEquals(getOldListAdsInfo(), null);
//        manageAds.verifyNewAdDisplayedOnShopDashboard(getOldListAdsInfo());
//
//        //Reinit
//        setOldListAdsInfo(null);
//    }

}
