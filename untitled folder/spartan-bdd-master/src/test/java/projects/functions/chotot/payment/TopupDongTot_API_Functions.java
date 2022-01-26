package projects.functions.chotot.payment;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import projects.functions.APISupportFunctions;
import projects.functions.PSQLSupportFunctions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static com.vn.chotot.helper.Utils.convertMapToJsonString;
import static com.vn.chotot.helper.Utils.sha1Decode;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class TopupDongTot_API_Functions extends APISupportFunctions {
    private static int retryToConnectDB = 5;

    //Nạp tiền, not pay
    public static void topupDongTotWithMomo_100k(String phone) {
        topupDongTotWithMomo(phone, 100000);
    }

    public static void topupDongTotWithMomo_200k(String phone) {
        topupDongTotWithMomo(phone, 200000);
    }

    public static void topupDongTotWithMomo_500k(String phone) {
        topupDongTotWithMomo(phone, 500000);
    }

    public static void topupDongTotWithMomo_1000k(String phone) {
        topupDongTotWithMomo(phone, 1000000);
    }

    public static void topupDongTotWithMomo_2000k(String phone) {
        topupDongTotWithMomo(phone, 2000000);
    }

    public static void topupDongTotWithMomo_3000k(String phone) {
        topupDongTotWithMomo(phone, 3000000);
    }

    public static void topupDongTotWithMomo_20k(String phone) {
        topupDongTotWithMomo(phone, 20000);
    }

    public static void topupDongTotWithMomo_50k(String phone) {
        topupDongTotWithMomo(phone, 50000);
    }

    private static String transId;

    private static String getCheckSumSHA1(String phone, String packageId, String transactionID) {
        String secretKey = "0203e731c12698efb222d1867832fba9";
        String checksum = packageId + phone + transactionID + secretKey;
        String checksumSHA1 = sha1Decode(checksum);
        System.out.println("Checksum: " + checksumSHA1);
        return checksumSHA1;
    }

    @Deprecated
    public static void topupDongTotWithMomo(String phone) {
        topupDongTotWithMomo(phone, -1);
    }

    public static void topupDongTotWithMomo() {
        topupDongTotWithMomo(tempUserPhone, -1);
    }

    public static void topupDongTotWithMomo(String phone, int amount) {
        // Get checksum
        String package_id = null;
        switch (amount) {
            case 100000:
                package_id = getPackageIDMomo_100k(phone);
                break;
            case 200000:
                package_id = getPackageIDMomo_200k(phone);
                break;
            case 500000:
                package_id = getPackageIDMomo_500k(phone);
                break;
            case 1000000:
                package_id = getPackageIDMomo_1000k(phone);
                break;
            case 2000000:
                package_id = getPackageIDMomo_2000k(phone);
                break;
            case 3000000:
                package_id = getPackageIDMomo_3000k(phone);
                break;
            case 20000:
                package_id = getPackageIDMomo_20k(phone);
                break;
            case 50000:
                package_id = getPackageIDMomo_50k(phone);
                break;
            default:
                package_id = getPackageIDMOmo(phone);
                break;
        }

        Assert.assertNotNull("CAN'T GET PACKAGE ID", package_id);
        transId = String.valueOf(System.currentTimeMillis());
        String checksumSHA1 = getCheckSumSHA1(phone, package_id, transId);

        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("trans_id", transId);
        mapData.put("phone", phone);
        mapData.put("package_id", package_id);

        String bodyData = convertMapToJsonString(mapData);

        // Post data
        response = postCheckSum(bodyData, checksumSHA1, gatewayTopupDongTotWithMomo);
        debugResponse();

        // Verify response code
        verifyStatusCode200(response, "TOPUP MOMO", gatewayTopupDongTotWithMomo);


//        setAmountBeforePayDT(getAmountBeforePayDT() + amount);
    }


    //get package that has maximum money
    public static String getPackageIDMOmo(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        int size = response.getBody().path("list.size()");
        return getPropertyValue(response, "[" + (size - 1) + "].package_id");
    }

    //100k, 200k, 500k, 1m, 2m, 3m
    public static String getPackageIDMomo_100k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 100000)].package_id");
    }

    public static String getPackageIDMomo_200k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 200000)].package_id");
    }

    public static String getPackageIDMomo_500k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 500000)].package_id");
    }

    public static String getPackageIDMomo_1000k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 1000000)].package_id");
    }

    public static String getPackageIDMomo_2000k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 2000000)].package_id");
    }

    public static String getPackageIDMomo_3000k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 3000000)].package_id");
    }

    public static String getPackageIDMomo_20k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 20000)].package_id");
    }

    public static String getPackageIDMomo_50k(String phone) {
        response = get(gatewayMomoPackage + "/" + phone);
        debugResponse();
        verifyStatusCode200("CAN'T GET MOMO PACKAGE: " + getResponseCode());
        return getResponseData(response, "$.[*][?(@.price == 50000)].package_id");
    }

    public static void expireDongTot365(String accountID) {
        PSQLSupportFunctions creditDB = null;
        try {
            creditDB = new PSQLSupportFunctions("10.60.7.122", "credit", "postgres", "");
        } catch (SQLException throwables) {
        }
        Assert.assertNotNull("CAN'T CONNECT TO PSQL credit", creditDB);
        String query = "SELECT balancedetail.id FROM balancedetail\n" +
                "    WHERE balancedetail.expired_time :: DOUBLE PRECISION >= date_part('epoch' :: TEXT, timezone('utc' :: TEXT, now())) AND\n" +
                "     balancedetail.type_id = 'paid' AND\n" +
                "      balancedetail.account_id = " + accountID + " AND\n" +
                "      balancedetail.amount > 0\n" +
                "ORDER BY priority, balancedetail.id, expired_time";

        String balance_id = creditDB.queryGetTopValue(query, 1).toString();
        System.out.println("DEBUG: " + balance_id);

        //Get Last deposite
        initBodyJson();
        bodyJson.addProperty("account_id", accountID);
        bodyJson.addProperty("balance_detail_id", balance_id);
        bodyJson.addProperty("expire_time", "1610786205");  //Jan 2021
        response = post(bodyJson, gatewayDongTot365_SetExpire_ByTime);
        verifyStatusCode200("EXPIRE DONG TOT 365: FAILED");
        debugResponse();
    }

    public static String topupDongTotExpiry_10k(String accountID, String userPhone) {
        return topupDongTotExpiry(accountID, userPhone, 10000, false, false);
    }

    public static String topupDongTotExpiry_20k(String accountID, String userPhone) {
        return topupDongTotExpiry(accountID, userPhone, 20000, false, false);
    }

    public static String topupDongTotExpiry_20k_Expired(String accountID, String userPhone) {
        return topupDongTotExpiry(accountID, userPhone, 20000, false, true);
    }

    public static String topupDongTotExpiry_10k_Expired(String accountID, String userPhone) {
        return topupDongTotExpiry(accountID, userPhone, 10000, false, true);
    }

    public static String topupDongTotExpiry_20k(String accountID, String userPhone, String expiredDate_MMddyyyy) {
        return topupDongTotExpiry(accountID, userPhone, 20000, false, expiredDate_MMddyyyy);    // MM-dd-yyyy
    }


    //Return contractID: used to expire/update ....
    //userHasToApprove : Online Contract
    private static String topupDongTotExpiry(String accountID, String userPhone, int dongtot, boolean userHasToApprove, Object expired) {
        String expiredDate = "%s 01:00:00+00:00";
//        String expiredDate = "%sT01:00:00Z";
        int nextYear = Integer.parseInt(getYearCurrent()) + 1;
        if (expired instanceof Boolean) {
            boolean isExpired = Boolean.parseBoolean(expired.toString());
            if (isExpired) {
                expiredDate = String.format(expiredDate, "01-01-2000");     // fixed "MM-dd-YYYY"
            } else {
                expiredDate = String.format(expiredDate, "01-01-" + nextYear);
            }
        } else { // Input expired date
            expiredDate = String.format(expiredDate, expired.toString());
        }
        System.out.println("Expired date: " + expiredDate);

        //Setup package ID
        String packageId = null;
        switch (dongtot) {
            case 10000:
                packageId = "563";      //NEVER CHANGE
                break;

            case 20000:
                packageId = "561";
                break;

            default:
                break;
        }
        Assert.assertNotNull("NOT FOUNT ANY PACKAGE ID MATCHING WITH VALUE " + dongtot, packageId);

        //Add package for account ID
        PSQLSupportFunctions bankTransferAdminCenter = null;
        try {
            bankTransferAdminCenter = new PSQLSupportFunctions("10.60.3.3", "bank_transfer", "postgres", "");
        } catch (SQLException throwables) {
        }
        Assert.assertNotNull("CAN'T CONNECT TO DATABASE BANK TRANSFER", bankTransferAdminCenter);

        String query = "\n" +
                "INSERT INTO \"bt_order\"(\n" +
                "    phone,\n" +
                "    account_id,\n" +
                "    expiration_time,\n" +
                "    package_id,\n" +
                "    amount_dongtot,\n" +
                "    amount,\n" +
                "    created_user,\n" +
                "    created_date,\n" +
                "    modified_date,\n" +
                "    is_approval,\n" +
                "    is_online_contract,\n" +
                "    contract_status,\n" +
                "    contract_info,\n" +
                "    contract_invoice,\n" +
                "    is_deleted\n" +
                "  )\n" +
                "VALUES(\n" +
                "    '" + userPhone + "',\n" +
                "    " + accountID + ",\n" +
                "    '" + expiredDate + "',\n" +   // Vu Hoang update expired Date
                "    " + packageId + ",\n" +
                "    '" + dongtot + "',\n" +
                "    '" + dongtot + "',\n" +
                "    'admin-ds@chotot.vn',\n" +
                "    1615889570,\n" +
                "    1615889570,\n" +
                "    'false',\n" +
                "    '" + userHasToApprove + "',\n" +
                "    'pending',\n" +
                "    '{\n" +
                "      \"contract_number\": \"123123\",\n" +
                "      \"card_id\": \"\",\n" +
                "      \"permanent_residence\": \"\",\n" +
                "      \"announce_id\": \"\"\n" +
                "    }',\n" +
                "    '{\n" +
                "      \"company_name\": \"\",\n" +
                "      \"company_address\": \"\",\n" +
                "      \"tax_id\": \"\",\n" +
                "      \"email\": \"\"\n" +
                "    }',\n" +
                "    false\n" +
                "  )\n";

        int id = bankTransferAdminCenter.insertToDB(query);
        Assert.assertTrue("Insert Package for account ID " + accountID + " doesn't work", id > 0);

        //Query Package ID from the above record
        query = "select id from bt_order bo"
                + "\nwhere phone = '" + userPhone + "'"
                + "\norder by id DESC"
                + "\nlimit 1";
        String contractID = bankTransferAdminCenter.queryGetTopValue(query, 1).toString();

        //Approve Bank Transer

        query = "UPDATE bt_order\n" +
                "SET modified_date = " + getCurrentTimeStampUnix() + " \n" +
                ", is_approval = true\n" +
                ", contract_status = 'approved'\n" +
                "WHERE id=" + contractID + "";

        int numberOfUpdatedRecord = bankTransferAdminCenter.updateToDB(query);
        Assert.assertTrue("FAILED TO UPDATE " + userPhone, numberOfUpdatedRecord > -1);

        initBodyJson();
        bodyJson.addProperty("package_id", Integer.parseInt(packageId));
        bodyJson.addProperty("account_id", Integer.parseInt(accountID));
        bodyJson.addProperty("contract_id", Integer.parseInt(contractID));
        bodyJson.addProperty("phone", userPhone);
        bodyJson.addProperty("transaction_ref", getCurrentDateByFormat("YYYYMMDDHHMM"));
        bodyJson.addProperty("bank_id", "BANKTRANSFER");
        bodyJson.addProperty("ipn_id", getCurrentDateByFormat("YYYYMMDDHHMM"));
        bodyJson.addProperty("source", "on_behalf");

        for (int i = 0; i < 5; i++) {
            try {
                response = post(bodyJson, gatewayDongTot_BankTransfer_Approve);
                verifyStatusCode200("FAILED TO APPROVE BANK TRANSFER");
                Assert.assertEquals("FAILED TO APPROVE BANK TRANSFER FOR APPROVEMENT OF USER " + userPhone
                        , getResponseData("$.status"), "true");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("FAILED TO APPROVE BANK TRANSFER");
        Assert.assertEquals("FAILED TO APPROVE BANK TRANSFER FOR APPROVEMENT OF USER " + userPhone
                , getResponseData("$.status"), "true");
        return contractID;
    }


    public static void topupDongTotFree_ByRedeemPromotionCode(String userAccountID, String userPhone, int dongtot) {
        String campaignID = createCampaign(10, 10, dongtot, 0);
        String code = generateCodeCampaign(campaignID).get(0);
        redeemCodeForAccount(userAccountID, userPhone, code);
    }

    //PROMOTION
    public static String createCampaign(int limitTotal, int limitPerAccount, int freeValue, int paidValue) {
        int nextYear = Integer.parseInt(getYearCurrent()) + 1;
        String body = "{\n" +
                "    \"apply_for\": \"credit\",\n" +
                "    \"campaign_type\": \"public\",\n" +
                "    \"name\": \"Automation Test Campaign " + getCurrentTimeStampUnix() + "\",\n" +
                "    \"start_at\": \"2020-01-01T17:00:00.000Z\",\n" +
                "    \"end_at\": \"" + nextYear + "-01-01T17:00:00.000Z\",\n" +
                "    \"limit_total\": " + limitTotal + ",\n" +
                "    \"limit_per_account\": " + limitPerAccount + ",\n" +
                "    \"free_value\": " + freeValue + ",\n" +
                "    \"paid_value\": " + paidValue + ",\n" +
                "    \"params\": {\n" +
                "        \"code_type\": \"auto\",\n" +
                "        \"code_quantity\": 1,\n" +
                "        \"code_redeem_limit\": 100,\n" +
                "        \"custom_code\": null\n" +
                "    }\n" +
                "}";

        response = post(body, gatewayPromotion_CreateCampaign);
        debugResponse();
        verifyStatusCode200("API CREATE CAMPAIGN OF PROMOTION IS DEAD");
        String campaignId = getResponseData("$.id");

        //Active & Approve Campaign
        String query = "update campaign\n" +
                "set \n" +
                "is_active = true\n" +
                ",status = 'approved'\n" +
                "where id = " + campaignId + "";
        int updatedRow = -1;
        for (int i = 0; i < retryToConnectDB; i++) {
            try {
                updatedRow = PSQLSupportFunctions.instance("promotion").updateToDB(query);
                Assert.assertTrue("CAMPAIGN " + campaignId + " IS INACTIVE & NOT APPROVED", updatedRow > 0);
                break;
            } catch (SQLException throwables) {
                Assert.assertTrue("CAN'T CONNECT TO PROMOTION DB", false);
                waitConstant(1);
            }
        }
        debugResponse();
        Assert.assertTrue("CAMPAIGN " + campaignId + " IS INACTIVE & NOT APPROVED", updatedRow > 0);
        return campaignId;
    }

    public static List<String> generateCodeCampaign(String campaignId) {
        return generateCodeCampaign(campaignId, 1, 1);
    }

    public static List<String> generateCodeCampaign(String campaignId, int numberOfCode, int maxUsePerCode) {
        initBodyJson();
        bodyJson.addProperty("campaign_id", campaignId);
        bodyJson.addProperty("number_of_code", numberOfCode);
        bodyJson.addProperty("max_use_per_code", maxUsePerCode);
        response = post(bodyJson, gatewayPromotion_GenerateCode);
        debugResponse();
        verifyStatusCode200("API GENERATE CODE IS DEAD");
        List<String> codeList = getResponseDataList("$.codes[*][?(@.campaign_id == " + campaignId + ")].code");
        return codeList;
    }

    public static void redeemCodeForAccount(String userAccountID, String userPhone, String code) {
        initBodyJson();
        bodyJson.addProperty("account_id", userAccountID);
        bodyJson.addProperty("code", code);
        bodyJson.addProperty("phone", userPhone);
        bodyJson.addProperty("source", "on_behalf");

        for (int i = 0; i < 5; i++) {
            try {
                response = post(bodyJson, gatewayPromotion_RedeemCode);
                verifyStatusCode200("API REDEEM CODE FOR ACCOUNT IS DEAD");
                Assert.assertTrue(Boolean.parseBoolean(getResponseData("$.ok")));
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        Assert.assertTrue(Boolean.parseBoolean(getResponseData("$.ok")));
    }

    public static void expireDongTotFree(String userAccountID, String lastTopupDongTotFreeId) {
        expireDongTotFree(userAccountID, lastTopupDongTotFreeId, "1600000000");
    }

    public static void expireDongTotFree(String userAccountID, String lastTopupDongTotFreeId, String expiredDate) {
        long timeStamp;
        try {
            timeStamp = Long.parseLong(expiredDate);
        } catch (NumberFormatException e) {
            timeStamp = Long.parseLong(getTimeStampUnix_yyyyMMdd(expiredDate));
        }

        String query = "update balancedetail\n" +
                "set expired_time = " + timeStamp + "\n" +
                "where \n" +
                "id = " + lastTopupDongTotFreeId + "\n";
        System.out.println("QUERY DB: " + query);
        int updatedRow = -1;
        try {
            updatedRow = PSQLSupportFunctions.instance("credit").updateToDB(query);
        } catch (SQLException throwables) {
            Assert.assertTrue("FAILED TO EXPIRE DONG TOT OF USER " + userAccountID, updatedRow > 0);
        }
    }


    @Deprecated // Use internal Dong Tot Burnout
    public static void updateExpiredDate_IncreaseDay(String userAccountID, String recordID, int numberOfDay) {
        String query = "select expired_time from balancedetail b2 \n" +
                "where account_id = " + userAccountID + "\n" +
                "and id = " + recordID + "\n";
        int expired_time = 0;
        try {
            expired_time = PSQLSupportFunctions.instance("credit").queryGetTopValueInt(query, 1);
        } catch (SQLException throwables) {
            Assert.assertTrue("FAILED TO GET RECORD ID OF LAST TOPUP DTF", false);
        }

        for (int i = 0; i < numberOfDay; i++) {
            expired_time += 100000;
        }

        query = "update balancedetail\n" +
                "set expired_time = " + expired_time + "\n" +
                "where account_id = " + userAccountID + "\n" +
                "and id = " + recordID + "";
        int updatedRow = -1;
        try {
            updatedRow = PSQLSupportFunctions.instance("credit").updateToDB(query);
        } catch (SQLException throwables) {
        }
        Assert.assertTrue("FAILED TO UPDATE EXPIRED DATE FOR DT FREE", updatedRow == 1);
    }

    //Last Record ID of credit table
    public static int getLastTopupID_DongTotFree(String userAccountID) {
        String query = "select id from balancedetail b2 \n" +
                "where account_id = " + userAccountID + "\n" +
                "and type_id = 'free'\n" +
                "order by created_time desc \n" +
                "limit 1";
        int recordID = -1;

        System.out.println("QUERY: \n" + query);

        for (int i = 0; i < retryToConnectDB; i++) {
            try {
                recordID = PSQLSupportFunctions.instance("credit").queryGetTopValueInt(query, 1);
                Assert.assertTrue("FAILED TO GET RECORD ID OF LAST TOPUP DTF", recordID > 0);
                return recordID;
            } catch (SQLException throwables) {
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        return recordID;
    }

    public static String getDongTotFreeFromDB(String userAccountID, String recordID) {
        String query = "select amount from balancedetail b2 \n" +
                "where account_id = " + userAccountID + "\n" +
                "and id = " + recordID + "\n";
        String amount = "0";
        System.out.println("QUERY DB: " + query);
        try {
            amount = String.valueOf(PSQLSupportFunctions.instance("credit").queryGetTopValue(query, 1));
//            amount = String.valueOf(PSQLSupportFunctions.instance("credit").queryGetTopValueInt(query, 1));
            return amount.replace(",", "");
        } catch (SQLException throwables) {
            Assert.assertTrue("FAILED TO GET RECORD ID OF LAST TOPUP DTF", false);
            return null;
        }
    }


    public static String getDongTotBankTransferFromDB(String userAccountID) {
        String query = "select amount from balancedetail b2 \n" +
                "where account_id = " + userAccountID + "\n" +
                "and type_id = 'paid'\n" +
                "and priority = 0\n";
        String amount = "0";
        System.out.println("QUERY DB: " + query);
        try {
            amount = String.valueOf(PSQLSupportFunctions.instance("credit").queryGetTopValueInt(query, 1));
            return amount.replace(",", "");
        } catch (SQLException throwables) {
            Assert.assertTrue("FAILED TO GET RECORD ID OF LAST TOPUP DTBT", false);
            return null;
        }
    }

    //Last Record ID of credit table
    public static String getLastTopupID_DongTotBankTransfer(String userAccountID) {
        String query = "select id from balancedetail b2 \n" +
                "where account_id = " + userAccountID + "\n" +
                "and type_id = 'paid'\n" +
                "and priority = 0\n" +
                "order by expired_time desc \n" +
                "limit 1";
        System.out.println("QUERY DB: " + query);
        try {
            return String.valueOf(PSQLSupportFunctions.instance("credit").queryGetTopValue(query, 1));
        } catch (SQLException throwables) {
            Assert.assertTrue("FAILED TO GET RECORD ID OF LAST TOPUP DTBT", false);
            return null;
        }
    }

    @Deprecated
    public static void expireDongTotExpiry(String userAccountID) {
        expireDongTotBankTransfer(userAccountID);
    }

    @Deprecated
    public static void expireDongTotBankTransfer(String userAccountID) {
        //Update expired Time
        String query = "update balancedetail\n" +
                "set expired_time = 1607945690\n" +
                "where account_id = " + userAccountID + "\n" +
                "and type_id = 'paid'\n" +
                "and priority = 0";
        int updatedRow = -1;

        for (int i = 0; i < retryToConnectDB; i++) {
            try {
                updatedRow = PSQLSupportFunctions.instance("credit").updateToDB(query);
                Assert.assertTrue("FAILED TO EXPIRE DONG TOT BT OF USER " + userAccountID, updatedRow > 0);
                break;
            } catch (SQLException throwables) {
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        Assert.assertTrue("FAILED TO EXPIRE DONG TOT BT OF USER " + userAccountID, updatedRow > 0);
        System.out.println("Expired Dong Tot Expiry successfully");
    }

    public static void expireDongTotFree(String userAccountID) {
        //Update expired Time
        String query = "update balancedetail\n" +
                "set expired_time = 1607945690\n" +
                "where account_id = " + userAccountID + "\n" +
                "and type_id = 'free'\n" +
                "and priority = 365";
        int updatedRow = -1;

        for (int i = 0; i < retryToConnectDB; i++) {
            try {
                updatedRow = PSQLSupportFunctions.instance("credit").updateToDB(query);
                Assert.assertTrue("FAILED TO EXPIRE DONG TOT FREE OF USER " + userAccountID, updatedRow > 0);
                break;
            } catch (SQLException throwables) {
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        Assert.assertTrue("FAILED TO EXPIRE DONG TOT FREE OF USER " + userAccountID, updatedRow > 0);
    }

    /*
    @Deprecated
    public static void topupDongTotWithPayoo(String phone, String amount) {
        topupDongTotWithPayoo(phone, Integer.parseInt(amount.trim().replace(".", "")));
    }

    @Deprecated
    public static void topupDongTotWithPayoo(String phone, int amount) {
        // Get checksum
        String secretKey = "cadae731dbdd194c64721dafca4e47607d3c87bc";
        String trans_id = "PY12" + System.currentTimeMillis() + "CHOTOT";
        String checkSum = amount + phone + trans_id + secretKey;
        String checksumSHA1 = sha1Decode(checkSum);
        log.info("\n---- " + checksumSHA1);

        // Set body data
        Map<Object, Object> mapData = new HashMap<>();
        mapData.put("trans_id", trans_id);
        mapData.put("phone", phone);
        mapData.put("amount", amount);
        String bodyData = convertMapToJsonString(mapData);

        // Post data
        log.info("\n----- POST url: " + gatewayTopupDongTotWithPayoo + "\n----- Body: " + bodyData);
        Response response =
                given()
                        .contentType("application/json")
                        .header("checksum", checksumSHA1)
                        .body(bodyData)
                        .post(gatewayTopupDongTotWithPayoo);

        // Verify response code
        verifyStatusCode(response, "200", "ACCOUNT " + phone + " CAN'T TOPUP DONG TOT WITH PAYOO: " + amount);
        log.info(getResponseBodyAsString(response));
    }


    public static void expireDongTotBankTransfer_expireOrderNotExpireDT(String userPhone, String contractID) {
        PSQLSupportFunctions bankTransferAdminCenter = null;
        try {
            bankTransferAdminCenter = new PSQLSupportFunctions("10.60.3.3", "bank_transfer", "postgres", "");
        } catch (SQLException throwables) {
        }
        Assert.assertNotNull("CAN'T CONNECT TO DATABASE BANK TRANSFER", bankTransferAdminCenter);

        String query = "UPDATE bt_order bo \n" +
                "SET expiration_time = '2020-01-01'\n" +
                ", is_expired = true\n" +
                "WHERE phone = '" + userPhone + "'\n" +
                "\tand id = '" + contractID + "'";
        System.out.println("" + query);
        int recordUpdated = bankTransferAdminCenter.updateToDB(query);
        Assert.assertTrue("CAN'T UPDATE EXPIRATION TIME OF PHONE " + userPhone, recordUpdated > 0);
    }
    */
}
