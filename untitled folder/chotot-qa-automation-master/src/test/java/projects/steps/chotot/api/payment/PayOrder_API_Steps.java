package projects.steps.chotot.api.payment;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertServices;
import projects.functions.chotot.payment.PayOrder_API_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static projects.functions.chotot.hierarchy.AccountHierarchy_Functions.checkTopupBalanceDT;
import static projects.functions.chotot.hierarchy.AccountHierarchy_Functions.checkTopupBalanceDTFree;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.functions.chotot.pos.POS_Functions.posBump;

public class PayOrder_API_Steps extends PayOrder_API_Functions {
    @Then("I pay for my New Ad")
    public void i_pay_for_my_New_Ad() {
        paymentOrderWithDongTot();
    }

    @Given("I deposit Dong Tot via Momo")
    public void i_deposit_Dong_Tot_via_Momo() {
        topupDongTotWithMomo();
    }


    //================ COMMON STEPS ===================
    // The below steps can be used for other Scenarios

    @Then("My Dong Tot balance should be {string}")
    public void my_DT4B_balance_biz_should_be(String amount) {
        checkTopupBalanceDT(global_accessToken, Integer.parseInt(amount));
    }

    @When("I pay Đồng Tốt for Premium Service of {string} new Ads that are published")
    public void post_many_ad_child(String numberOfAd) {
        setAmountBeforePayDT(getBalanceDT());
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            CM_API_Ads_InsertServices.instance().insertNewServiceAd_NoUploadNewImage("accept");
            posBump(global_accessToken, tempAdID);
            paymentOrder(global_accessToken);
            setAmountPayDTTotal(getAmountPayDTTotal() + getAmountAfterPayDT());
        }
    }

    @When("I pay Đồng Tốt for Premium Service of {string} new Ads that aren't published yet")
    public void post_many_ad_cshild(String numberOfAd) {
        setAmountBeforePayDT(getBalanceDT());
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            CM_API_Ads_InsertServices.instance().insertNewServiceAd_NoUploadNewImage();
            posBump(global_accessToken, tempAdID);
            paymentOrder(global_accessToken);
            setAmountPayDTTotal(getAmountPayDTTotal() + getAmountAfterPayDT());
        }
    }

    //================ SPECIFIED STEPS ===================
    // The below steps are ONLY used for PayOrder Sceanario

    @Then("My Dong Tot balance should be matched with total paid")
    public void my_DT4B_balsance_biz_should_be() {
//        checkTopupBalanceDT(global_accessToken, Integer.parseInt(amount));
        int currentDT = getBalanceDT();
//        Assert.assertEquals(getAmountBeforePayDT(), getBalanceDT() + getBalanceDTToPayPOS(),
//                "Dong Tot balance isn't incorrect.");
    }

    @Then("My Dong Tot Free balance should be matched with total paid")
    public void my_DT4B_balsance_biz_sshould_be(String amount) {
        checkTopupBalanceDTFree(global_accessToken, Integer.parseInt(amount));
    }


}