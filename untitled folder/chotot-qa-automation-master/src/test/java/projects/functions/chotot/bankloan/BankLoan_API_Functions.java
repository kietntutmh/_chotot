package projects.functions.chotot.bankloan;

import org.testng.Assert;
import projects.functions.APISupportFunctions;

import java.util.HashMap;
import java.util.List;

import static api.configuration.GatewayConfig.gatewayBankLoan_Calculate;

public class BankLoan_API_Functions extends APISupportFunctions {
    private static int AD_CATEGORY = 0;
    private static int loanYear = 0;
    private static int loanMonth = 0;
    private static int loanPrinciple = 0;
    private static String loanPackageId = "0";
    private static int adPrice = 0;
    private static boolean isTestData = true;

    //Calculation
    private static int paidFirst = 0;       //Tổng số tiền ad - Tỉ lệ vay tối đa = số tiền trả trước
    private static int principleRate = 0;       //Tỉ lệ vay tối đa theo gói package.
    private static int maxPrincipleDuration = 0;    //Thời gian vay
    private static int promotionRateDuration = 0;    //Thời gian ưu đãi khi vay
    private static double interestRate = 0.0;               //Lãi suất hàng năm (yearly_rate)
    private static double monthlyRate = 0.0;               //Lãi suất
    private static int interestSum = 0;               //Tổng tiền lãi

    private static HashMap<Integer, Double> monthApplyAIR;
//    private static double AIR = 0.0;               //Lãi suất lũy tiến
//    private static int monthApply = 0;               //Tháng áp dụng


    public static void usePackage18ForTesting() {
        interestRate = 8.75;
        promotionRateDuration = 18;
//        principleRate = 80;
        principleRate = 100;

        monthApplyAIR = new HashMap<>();
        monthApplyAIR.put(1, 10.0);     //MonthAppy - AIR
    }

    public static void setAdPrice(int loanPrice) {
        BankLoan_API_Functions.adPrice = loanPrice;
    }

    public static void setAdCategory(int adCategory) {
        AD_CATEGORY = adCategory;

        //if duration of package API is null
//        if(getmaxPrincipleDurationFromAPIPackage is null)
        if ((AD_CATEGORY / 1000) < 2) {         //big cate: 1000
            maxPrincipleDuration = 240; //default
        } else if ((AD_CATEGORY / 2000) < 2) {    //big cate: 2000
            maxPrincipleDuration = 84; //default
        } else {
        }
    }

    public static void setLoanYear(int loanYear) {
        BankLoan_API_Functions.loanYear = loanYear;
    }

    public static void setLoanMonth(int loanMonth) {
        BankLoan_API_Functions.loanMonth = loanMonth;
    }

    public static void setLoadPackageIdByOption(String optionName) {
        switch (optionName.toUpperCase()) {
            case "1":
                BankLoan_API_Functions.loanPackageId = "";
                break;
            case "2":
                BankLoan_API_Functions.loanPackageId = "";
                break;
            case "3":
                BankLoan_API_Functions.loanPackageId = "";
                break;
        }
    }


    private static int paidFirstActual, loanPrincipleActual, loanMonthActual, loanYearActual, interestSumActual, durationActual;
    private static double monthlyRateActual = 0.0;

    public static void calculateBankLoan() {
        initBodyJson();
        bodyJson.addProperty("ad_price", adPrice);
        bodyJson.addProperty("category", AD_CATEGORY);
        bodyJson.addProperty("years", loanYear);
        bodyJson.addProperty("package_id", "18");
        bodyJson.addProperty("bank_name", "msb");
        response = post(bodyJson, gatewayBankLoan_Calculate);
        verifyStatusCode200("CALCULATE BANK LOAD API FAILED");
        Assert.assertEquals(getResponseDataInt("$.ad_price"), adPrice, "Ad Price is incorrect");
        debugResponse();
        //get data from API

        paidFirstActual = getResponseDataInt("$.pay_first");
        principleRate = getResponseDataInt("$.principle_rate");
        loanPrincipleActual = getResponseDataInt("$.loan_principle");
        if (maxPrincipleDuration == 0) {
            maxPrincipleDuration = getResponseDataInt("$.max_duration");       // maxPrincipleDuration isn't set to default value by CategoryID
        }
        loanMonthActual = getResponseDataInt("$.duration");
        loanYearActual = getResponseDataInt("$.years");
        interestRate = getResponseDataDouble("$.interest_rate");
        monthlyRateActual = getResponseDataDouble("$.monthly_rate");
        interestSumActual = getResponseDataInt("$.sum_interest");
        durationActual = getResponseDataInt("$.duration");

        //Calculate
        paidFirst = (adPrice / 100) * (100 - principleRate);

        loanPrinciple = adPrice - paidFirst;

        if (loanYear > 0 && loanMonth == 0) {
            loanMonth = loanYear * 12;
        } else if (loanYear == 0 && loanMonth > 0) {
            loanYear = loanMonth / 12;
        }

        monthlyRate = interestRate / 12;

        List<Integer> interest = getResponseDataListInt("$.data[*].interest");
        for (int interestPerMonth : interest) {
            interestSum += interestPerMonth;
        }
    }

    public static void verifyPaidFirst() {
        Assert.assertEquals(paidFirst, paidFirstActual, "Paid First money is wrong");
    }

    public static void verifyLoanPrinciple() {
        Assert.assertEquals(loanPrinciple, loanPrincipleActual, "Loan Principle is wrong");
    }

    public static void verifyLoanMonthDuration(int loanMonths) {
        Assert.assertEquals(loanMonth, loanMonthActual, "Loan Month Duration is wrong");
        Assert.assertEquals(loanMonthActual, loanMonths, "Loan Month Duration is wrong");
    }

    public static void verifyLoanYearDuration(int loanYears) {
        Assert.assertEquals(loanYear, loanYearActual, "Loan Year Duration is wrong");
        Assert.assertEquals(loanYearActual, loanYears, "Loan Year Duration is wrong");
    }

    public static void verifyMonthlyRate() {
        Assert.assertEquals(monthlyRateActual, monthlyRate, "Monthly Rate is wrong");
    }

    public static void verifyInterestSum() {
        Assert.assertEquals(interestSumActual, interestSum, "Interest Sum is wrong");
    }


    @Deprecated
    public static void getLoanPackageInfo(String packageId) {
        initBodyJson();
        bodyJson.addProperty("ad_price", adPrice);
        bodyJson.addProperty("category", AD_CATEGORY);
        bodyJson.addProperty("years", loanYear);
        bodyJson.addProperty("package_id", "18");
        bodyJson.addProperty("bank_name", "msb");
        response = post(bodyJson, gatewayBankLoan_Calculate);       //VUHOANG DEBUG: CALL API PACKAGE TO GET principleRate
        verifyStatusCode200("GET LOAN PACKAGE API FAILS");

        principleRate = getResponseDataInt("$.principle_rate");
    }
}
