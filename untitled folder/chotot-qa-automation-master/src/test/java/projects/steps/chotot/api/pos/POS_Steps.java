package projects.steps.chotot.api.pos;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.flashad.FlashAd_PTY_Functions;
import projects.functions.chotot.pos.POS_Functions;
import projects.functions.chotot.hierarchy.AccountHierarchy_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;

public class POS_Steps extends POS_Functions {
    private FlashAd_PTY_Functions flashAd_pty = new FlashAd_PTY_Functions();

    @Given("I pay for Bump by Đồng Tốt")
    public void i_pay_for_Bump_by_DT() {
        posBump(global_accessToken, tempAdID);
        paymentOrder(global_accessToken);
    }

    @Given("I pay for Bump by Đồng Tốt Doanh Nghiệp")
    public void i_pay_for_Bump_by_DT4b() {
        posBump(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
    }

    @Given("I pay for Bump 3 Days by Đồng Tốt")
    public void i_pay_for_Bumps_by_DT() {
        posBump3Days(global_accessToken, tempAdID);
        paymentOrder(global_accessToken);
    }

    @Given("I pay for Bump 3 Days by Đồng Tốt Doanh Nghiệp")
    public void i_pay_for_Bumps_by_DT4b() {
        posBump3Days(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
    }

    @Given("I pay for Bump 7 Days by Đồng Tốt")
    public void i_pay_for_Bump_7_by_DT() {
        posBump7Days(global_accessToken, tempAdID);
        paymentOrder(global_accessToken);
    }

    @Given("I pay for Bump 7 Days by Đồng Tốt Doanh Nghiệp")
    public void i_pay_for_Bump_7_by_DT4b() {
        posBump7Days(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
    }

    //QUANGTRAN - SPECIAL DISPLAY ADS

    @Then("I should see Special Display Ads service is displayed on POS and the price is correct")
    public void iShouldSeeSpecialDisplayAdsServiceIsDisplayedOnPOS() {
        posSpecialDisplay(global_accessToken, REGION_HCM_ID, CATEID_PTY_APARTMENT, tempAdID, getPriceSDA(), getDurationSDA());
    }

    @Then("I should not see Special Display Ads service is displayed on POS")
    public void iShouldNotSeeSpecialDisplayAdsServiceIsDisplayedOnPOS() {
        posSpecialDisplay_NotDisplay(global_accessToken, REGION_HCM_ID, CATEID_ELT_PHONE, tempAdID);
    }

    @Then("I can add Special Display Ads service to cart for purchasing")
    public void iCanAddSpecialDisplayAdsServiceToCartForPurchasing() {
        posSpecialDisplay_AddCart(global_accessToken, tempAdID, getDurationSDA());
    }

    @Then("I can purchase Special Display Ads service successfully")
    @Then("I can purchase Special Display Ads service successfully one more time")
    public void iCanPurchaseSpecialDisplayAdsServiceSuccessfully() {
        posSpecialDisplay_AddCart(global_accessToken, tempAdID, getDurationSDA());
        paymentOrder(global_accessToken);
    }

    @Then("I should see the order in Order History")
    public void iShouldSeeTheOrderInOrderHistory() {
        posSpecialDisplay_CheckOrderHistory(global_accessToken, tempAdID, getPriceSDA());
    }

    @Then("I can see the statistics of the Special Display Ads service")
    public void iCanSeeTheStatisticsOfTheSpecialDisplayAdsService() {
        posSpecialDisplay_CheckStatistics(global_accessToken, tempAdID);
    }

    @When("I post a new Ad and pay for POS Special Display Ads but ad is rejected and DT is refunded")
    public void iPostANewAdAndPayForPOSSpecialDisplayAdsButAdIsRejectedAndDTIsRefunded() {
        flashAd_pty.insertApartmentSellPrivate(global_accessToken, "refusepay_withsda", false);
    }

    @Then("I can see DT balance is correct")
    public void iCanSeeDTBalanceIsCorrect() {
        posSpecialDisplay_CheckBalanceAfterRefund(global_accessToken, "500000");
    }

    @And("I can see refund order in Order History")
    public void iCanSeeRefundOrderInOrderHistory() {
        posSpecialDisplay_CheckRefundOrderHistory(global_accessToken, getPriceSDA());
    }

    @Then("I can use DT4B to pay for an Ad with Special Display Ads service by child account")
    public void iCanUseDTBToPayForAnAdWithSpecialDisplayAdsServiceByChildAccount() {
        global_accessToken = AccountHierarchy_Functions.getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        posSpecialDisplay_AddCart(global_accessToken, tempAdID, getDurationSDA());
        paymentOrderDT4B(global_accessToken);
    }

    @Then("I can purchase Special Display Ads with Bump successfully")
    public void iCanPurchaseSpecialDisplayAdsWithBumpSuccessfully() {
        posSpecialDisplay_AddCart(global_accessToken, tempAdID, getDurationSDA());
        posBump(global_accessToken, tempAdID);
        paymentOrder(global_accessToken);
    }

    @And("I can see the order of Bump and Special Display Ads in Order History")
    public void iCanSeeTheOrderOfBumpAndSpecialDisplayAdsInOrderHistory() {
        posSpecialDisplay_CheckOrderBumpWithDSA(global_accessToken);
    }

    @Then("The price of Special Display Ads service of Category {string} should be {string}")
    public void thePriceOfSpecialDisplayAdsServiceOfCategoryShouldBe(String cateID, String priceSDA) {
        switch (cateID) {
            case "1010":
                posSpecialDisplay_Price(CATEID_PTY_APARTMENT, "7", REGION_HCM_ID, priceSDA);
                break;
            case "1020":
                posSpecialDisplay_Price(CATEID_PTY_HOUSE, "7", REGION_HCM_ID, priceSDA);
                break;
            case "1050":
                posSpecialDisplay_Price(CATEID_PTY_ROOMFORRENT, "7", REGION_HCM_ID, priceSDA);
                break;
            case "2010":
                posSpecialDisplay_Price(CATEID_VEH_CAR, "7", REGION_HCM_ID, priceSDA);
                break;
            case "2020":
                posSpecialDisplay_Price(CATEID_VEH_MOTORBIKE, "7", REGION_HCM_ID, priceSDA);
                break;
        }
    }

    @Given("I get info of Special Display Ads")
    public void iGetInfoOfSpecialDisplayAds() {
        posSpecialDisplay_Price(CATEID_PTY_APARTMENT, "7", REGION_HCM_ID, "95000");
    }
}
