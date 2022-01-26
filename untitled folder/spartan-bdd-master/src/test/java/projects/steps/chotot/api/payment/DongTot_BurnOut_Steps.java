package projects.steps.chotot.api.payment;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot.payment.DongTot_BurnOut_Functions;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getBalanceDT;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getBalanceDTFree;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.*;

public class DongTot_BurnOut_Steps extends DongTot_BurnOut_Functions {
    private int _DT365 = 0;
    private int _DTBT = 0;
    private int _DTF = 0;

    private String codeA, codeB, codeABalance, codeBBalance;

    @Given("I login my Account to burn Dong Tot")
    public void loginToBurnDT() {
        getAccessTokenOfNewUser();
        _DT365 = 0;
        _DTBT = 0;
        _DTF = 0;
    }

    @Given("I topup 20k Dong Tot 365")
    public void topup20kDT365Active() {
        topupDongTotWithMomo_20k(newUserPhone);
        _DT365 += 20000;
    }

    @Given("I topup 10k Dong Tot 365")
    public void topup10kDT365Active() {
        topupDongTotWithMomo_20k(newUserPhone);
        _DT365 += 10000;
    }

    @Given("I topup 20k Dong Tot Bank Transfer")
    public void topup20kDTBTActive() {
        topupDongTotExpiry_20k(global_accountID, newUserPhone);
        _DTBT += 20000;
    }

    @Given("I topup 20k Dong Tot Bank Transfer that expires at {string} MMddyyyy")
    public void topup20kDTBTActive_ByDay(String MMddyyyy) {
        topupDongTotExpiry_20k(global_accountID, newUserPhone, MMddyyyy);
        _DTBT += 20000;
    }

    @Given("I topup 10k Dong Tot Bank Transfer")
    public void topup10kDTBTActive() {
        topupDongTotExpiry_10k(global_accountID, newUserPhone);
        _DTBT += 10000;
    }

    @When("I topup {string} Dong Tot Free")
    public void i_get_Code_free(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        topupDongTotFree_ByRedeemPromotionCode(global_accountID, newUserPhone, Integer.parseInt(balance));
        _DTF += Integer.parseInt(balance);
    }

    @When("I topup {string} Dong Tot Free but expired")
    public void i_get_Code_free_expire(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        topupDongTotFree_ByRedeemPromotionCode(global_accountID, newUserPhone, Integer.parseInt(balance));
        expireDongTotFree(global_accountID);
    }

    @Given("I topup 20k Dong Tot Bank Transfer but expired")
    public void topup20kDTBTExpired() {
        String bankTransferContactID = topupDongTotExpiry_20k(global_accountID, newUserPhone);
        expireDongTotBankTransfer(global_accountID);
    }

    @Given("I topup 10k Dong Tot Bank Transfer but expired")
    public void topup10kDTBTExpired() {
        String bankTransferContactID = topupDongTotExpiry_10k(global_accountID, newUserPhone);
        expireDongTotBankTransfer(global_accountID);
    }

    //Expire Dong Tot
    @Given("My Dong Tot 365 is expired")
    public void myDTIsExpired() {
        expireDongTot365(global_accountID);
    }

    @Given("My Dong Tot Bank Transfer is expired")
    public void topup20kDT365Expireds() {
        expireDongTotBankTransfer(global_accountID);
    }

    @Then("My Dong Tot Free balance should be {string}")
    public void my_dt_Free_balance_should_be(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        Assert.assertEquals(getBalanceDTFree(global_accessToken), Integer.parseInt(balance));
    }

    @Then("My Dong Tot Paid balance should be {string}")
    public void my_dt_balance_should_bepaid(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        Assert.assertEquals(getBalanceDT(global_accessToken), Integer.parseInt(balance));
    }

    @Then("My Dong Tot Bank Transfer balance should be {string}")
    public void my_dt_Free_balance_shsould_be(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        Assert.assertEquals(getDongTotBankTransferFromDB(global_accountID), balance, "DTBT is incorrect");
    }

//    @Then("My Đồng Tốt Bank Transfer balance should be {string}")
//    public void my_Đồng_Tốt_Bank_Transfer_balance_should_be(String balance) {
//        balance = balance.toLowerCase().replace("k","000");
//        getBalanceDTBT();
//        //check DB
//    }
//
//    @Then("My Đồng Tốt 365 balance should be {string}")
//    public void my_Đồng_Tốt_balance_should_be(String balance) {
//        balance = balance.toLowerCase().replace("k","000");
//        Assert.assertEquals(getBalanceDT(global_accessToken), Integer.parseInt(balance));
//        //Check DB
//    }

    @When("I topup {string} Dong Tot Free from Code A")
    public void i_get_Code_freeA(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        topupDongTotFree_ByRedeemPromotionCode(global_accountID, newUserPhone, Integer.parseInt(balance));     //VUHOANG DEBUG
        _DTF += Integer.parseInt(balance);

        codeA = String.valueOf(getLastTopupID_DongTotFree(global_accountID));
        codeABalance = getDongTotFreeFromDB(global_accountID, codeA);
        updateExpiredDate_IncreaseDay(global_accountID, codeA, 1);
    }

    @When("I topup {string} Dong Tot Free from Code A but expired")
    public void i_get_Code_freeA_expired(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        topupDongTotFree_ByRedeemPromotionCode(global_accountID, newUserPhone, Integer.parseInt(balance));     //VUHOANG DEBUG
        _DTF += Integer.parseInt(balance);

        codeA = String.valueOf(getLastTopupID_DongTotFree(global_accountID));
        codeABalance = getDongTotFreeFromDB(global_accountID, codeA);
        expireDongTotFree(global_accountID, codeA);
    }

    @When("I topup {string} Dong Tot Free from Code B that has closer expired date than code A")
    public void i_get_Code_freeB(String balance) {
        balance = balance.toLowerCase().replace("k", "000");
        topupDongTotFree_ByRedeemPromotionCode(global_accountID, newUserPhone, Integer.parseInt(balance));     //VUHOANG DEBUG
        _DTF += Integer.parseInt(balance);

        waitConstant(15);
        codeB = String.valueOf(getLastTopupID_DongTotFree(global_accountID));
        codeBBalance = getDongTotFreeFromDB(global_accountID, codeB);
    }

    @Then("My Dong Tot Free balance of Code B should be {string}")
    public void my_Đồng_Tốt_Free_balance_of_Code_B_should_be(String balance) {
        codeBBalance = getDongTotFreeFromDB(global_accountID, codeB);
        Assert.assertEquals(codeBBalance, balance);
    }

    @Then("My Dong Tot Free balance of Code A should be {string}")
    public void my_Đồng_Tốt_Free_balance_of_Code_A_should_be(String balance) {
        codeABalance = getDongTotFreeFromDB(global_accountID, codeA);
        Assert.assertEquals(codeABalance, balance);
    }

}
