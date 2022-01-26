package projects.steps.chotot.api.reward;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.reward.Reward_API_Functions;

import static api.configuration.GatewayConfig.global_accessToken;
import static projects.functions.chotot.hierarchy.AccountHierarchy_Functions.*;

public class Reward_API_Steps extends Reward_API_Functions {


    @Given("I login my account to redeem Điểm Tốt to Đồng Tốt")
    @Given("I login my account to pay Đồng Tốt FREE")
    public void i_login() {
        setNewUserInfo();
    }

    @Given("I have {string} points in my Account")
    public void i_have_points(String point) {
        addPointToUser(Integer.parseInt(point));
        verifyPointReward(Integer.parseInt(point));
    }

    @Then("My point number should be {string}")
    public void i_check_points(String point) {
        verifyPointReward(Integer.parseInt(point));
    }

    @When("I redeem Code 5000 with 25 points")
    public void i_get_Code_5() {
        generateCode5k(getUserAccountID());
        setUserPointRedeem(25);
        setUserPointAfterRedeem(getUserPoint() - getUserPointRedeem());
    }

    @When("I redeem Code 10000 with 50 points")
    public void i_get_Code_10() {
        generateCode10k(getUserAccountID());
        setUserPointRedeem(50);
        setUserPointAfterRedeem(getUserPoint() - getUserPointRedeem());
    }

    @When("I redeem Code 15000 with 75 point")
    public void i_get_Code_15() {
        generateCode15k(getUserAccountID());
        setUserPointRedeem(75);
        setUserPointAfterRedeem(getUserPoint() - getUserPointRedeem());
    }

    @When("I redeem my Code to Đồng Tốt")
    public void i_redeem_code_to_DT(){
        redeemCodeToDongTot(getUserToken(), getUserCode());
    }

    @When("I redeem all my Codes to Đồng Tốt")
    public void i_redeem_code_tos_DT(){
        for(String code : allMyCodes){
            redeemCodeToDongTot(global_accessToken, code);
        }
    }

    @Then("My Dong Tot balance should be {string} after trading Voucher")
    public void i_redeem_csode_to_DT(String amount){
        checkTopupBalanceDT(global_accessToken, Integer.parseInt(amount));
    }

    @Then("My Dong Tot Free balance should be {string} after trading Voucher")
    public void i_redeem_csodess_to_DT(String amount){
        checkTopupBalanceDTFree(global_accessToken, Integer.parseInt(amount));
    }

    @Given("I redeem my points to {string} voucher 5000")
    public void i_havess_points(String numberOfVoucher) {
        addPointToUser(10000);
        verifyPointReward(10000);

        for(int i = 0; i < Integer.parseInt(numberOfVoucher); i++){
            allMyCodes.add(generateCode5k(getUserAccountID()));
        }
    }

    @Given("I redeem my points to {string} voucher 10000")
    public void i_havesss_points(String numberOfVoucher) {
        addPointToUser(10000);
        verifyPointReward(10000);

        for(int i = 0; i < Integer.parseInt(numberOfVoucher); i++){
            allMyCodes.add(generateCode10k(getUserAccountID()));
        }

        for(String code : allMyCodes){
            redeemCodeToDongTot(getUserToken(), code);
        }
    }

    @Given("I redeem my points to {string} voucher 15000")
    public void i_havesss_psoints(String numberOfVoucher) {
        addPointToUser(10000);
        verifyPointReward(10000);

        for(int i = 0; i < Integer.parseInt(numberOfVoucher); i++){
            allMyCodes.add(generateCode15k(getUserAccountID()));
        }

        for(String code : allMyCodes){
            redeemCodeToDongTot(getUserToken(), code);
        }
    }

    @Then("My Dong Tot Free should be expired after {string} month")
    public void i_havesss_spsoints(String monthNumber) {
        checkTopupBalanceDTFreeExpire(global_accessToken, Integer.parseInt(monthNumber));
    }

    @Then("I have 5k Đồng Tốt Free")
    public void i_have_5_k(){
        redeemDongTotFree("5000");
    }

    @Then("I have 50k Đồng Tốt Free")
    public void i_havse_5_k(){
        redeemDongTotFree("50000");
    }

    @Then("I have 100k Đồng Tốt Free")
    public void i_havsse_5_k(){
        redeemDongTotFree("100000");
    }

    @Then("I have 200k Đồng Tốt Free")
    public void i_havsse_2_k(){
        redeemDongTotFree("200000");
    }

    @Then("I have {string} Đồng Tốt Free")
    public void i_have_dongtotfree_k(String dongTotFree){
        redeemDongTotFree(dongTotFree);
    }

    @Then("I login and I have {string} Đồng Tốt Free")
    public void i_havessss_sspsoints(String dongTotFree) {
        setNewUserInfo();
        redeemDongTotFree(dongTotFree);
    }

    @Given("Test vu")
    public void i_hsavesss_points() {
        addPointToUser(4000000);
//        String code = generateCode(global_accountID, 4000000);
        //acountId: 10306034
//        getAccessTokenOfUser("0375833816", "123456");
//        redeemCodeToDongTot(global_accessToken, "LRNKY9");
    }


}
