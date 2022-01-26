package projects.steps.chotot.api.bankloan;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.chotot.bankloan.BankLoan_API_Functions;

import static projects.configuaration.CategoryConfig.CATEID_PTY_HOUSE;

public class BankLoan_API_Steps extends BankLoan_API_Functions {

    @Given("I use Bank Loan to buy a PTY House Ad with loan value {string}")
    public void i_use_Bank_Loan_to_buy_a_new_Ad_with_loan_value(String loan) {
        setAdCategory(Integer.parseInt(CATEID_PTY_HOUSE));
        setAdPrice(Integer.parseInt(loan));
    }

    @Given("I choose Tenure {string} months")
    public void i_choose_Tenure_months(String string) {
        setLoanMonth(Integer.parseInt(string));
    }

    @Given("I choose Tenure {string} years")
    public void i_choose_Tenure_years(String year) {
        setLoanYear(Integer.parseInt(year));
    }

    @Given("I choose bank UOB with Option 1")
    public void i_choose_bank_UOB_with_Option() {
        setLoadPackageIdByOption("1");
    }

    @Given("I choose Loan Package 18")
    public void i_cshoose_bank_UOB_with_Option() {
        usePackage18ForTesting();
    }

    @Given("I choose bank UOB with Option 2")
    public void i_choose_bank_UOB_with_Option2() {
        setLoadPackageIdByOption("2");
    }

    @Given("I choose bank UOB with Option 3")
    public void i_choose_bank_UOB_with_Option3() {
        setLoadPackageIdByOption("3");
    }


    @Then("My 1st month Instalment should be {string}")
    public void my_1st_month_Instalment_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should pay {string} first")
    public void my_1st_month_Insstalment_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should pay loan in {string} months")
    public void my_1st_month_Insstalmenst_should_be(String months) {
        verifyLoanMonthDuration(Integer.parseInt(months));
    }

    @Then("I should pay loan in {string} years")
    public void my_1st_month_Insstalmenst_shoulsd_be(String years) {
        verifyLoanYearDuration(Integer.parseInt(years));
    }

    @Then("My monthly rate should be yearly rate divided by the total month")
    public void my_1st_month_Insstalmenst_shosulsd_be() {
        verifyMonthlyRate();
    }

    @Then("My interest total should be equal to sum of interest per month")
    public void my_1st_month_Insstsalmenst_shosulsd_be() {
        verifyInterestSum();
    }

    @Then("Chotot calculates Bank Loan")
    public void my_1st_month_Insstalmsent_should_be() {
        calculateBankLoan();
    }

    @Then("My paid first money should be correct")
    public void my_1st_month_Insstalmsent_shsould_be() {
        verifyPaidFirst();
    }

    @Then("My loan principle should be correct")
    public void my_1sst_month_Insstalmsent_shsould_be() {
        verifyLoanPrinciple();
    }



    //----------------- Storage ----------------------

//    @Then("My 6th month Instalment should be {string}")
//    public void my_6th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 7th month Instalment should be {string}")
//    public void my_7th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 12th month Instalment should be {string}")
//    public void my_12th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 13th month Instalment should be {string}")
//    public void my_13th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 18th month Instalment should be {string}")
//    public void my_18th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 19th month Instalment should be {string}")
//    public void my_19th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 24th month Instalment should be {string}")
//    public void my_24th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 25th month Instalment should be {string}")
//    public void my_25th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 30th month Instalment should be {string}")
//    public void my_30th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 31st month Instalment should be {string}")
//    public void my_31st_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 36th month Instalment should be {string}")
//    public void my_36th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("My 37th month Instalment should be {string}")
//    public void my_37th_month_Instalment_should_be(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

}
