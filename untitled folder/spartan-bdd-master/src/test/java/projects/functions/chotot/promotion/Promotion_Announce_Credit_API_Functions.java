package projects.functions.chotot.promotion;

import projects.functions.APISupportFunctions;
import projects.functions.PSQLSupportFunctions;

import java.sql.SQLException;

public class Promotion_Announce_Credit_API_Functions extends APISupportFunctions {
    private static PSQLSupportFunctions promotionDB = null;
    private static String userAccountID = "";
    private static String userToken = "";
    private static String userPhone = "";

    //Announce Promotion
    private static String campaignID = "";
    private static String campaignName = "";
    private static String campaignTitle = "";
    private static String campaignContent = "";
    private static int apCredit;
    private static int apLimitTotal;
    private static int apLimitPerAccount;

    public static String getUserAccountID() {
        return userAccountID;
    }

    public static void setUserAccountID(String userAccountID) {
        Promotion_Announce_Credit_API_Functions.userAccountID = userAccountID;
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        Promotion_Announce_Credit_API_Functions.userToken = userToken;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void setUserPhone(String userPhone) {
        Promotion_Announce_Credit_API_Functions.userPhone = userPhone;
    }

    public static String getCampaignID() {
        return campaignID;
    }

    public static void setCampaignID(String campaignID) {
        Promotion_Announce_Credit_API_Functions.campaignID = campaignID;
    }

    public static String getCampaignName() {
        return campaignName;
    }

    public static void setCampaignName(String campaignName) {
        Promotion_Announce_Credit_API_Functions.campaignName = campaignName;
    }

    public static String getCampaignTitle() {
        return campaignTitle;
    }

    public static void setCampaignTitle(String campaignTitle) {
        Promotion_Announce_Credit_API_Functions.campaignTitle = campaignTitle;
    }

    public static String getCampaignContent() {
        return campaignContent;
    }

    public static void setCampaignContent(String campaignContent) {
        Promotion_Announce_Credit_API_Functions.campaignContent = campaignContent;
    }

    public static int getAPCredit() {
        return apCredit;
    }

    public static void setAPCredit(int apCredit) {
        Promotion_Announce_Credit_API_Functions.apCredit = apCredit;
    }

    public static int getAPLimitTotal() {
        return apLimitTotal;
    }

    public static void setAPLimitTotal(int apLimitTotal) {
        Promotion_Announce_Credit_API_Functions.apLimitTotal = apLimitTotal;
    }

    public static int getAPLimitPerAccount() {
        return apLimitPerAccount;
    }

    public static void setAPLimitPerAccount(int apLimitPerAccount) {
        Promotion_Announce_Credit_API_Functions.apLimitPerAccount = apLimitPerAccount;
    }

    //Functionality
    public static void connectToCampaign() {
        try {
            promotionDB = new PSQLSupportFunctions("10.60.3.3", "promotion", "postgres", "");
        } catch (SQLException throwables) {
            System.out.println("CAN'T CONNECT TO DATABASE");
            throwables.printStackTrace();
        }
    }

    public static void createAPCampaign(int apLimitTotal, int apLimitPerAccount, String apType) {
        boolean isActive = false;
        boolean is_deleted = false;
        setCampaignName("Automation Test Vuhoang creates Campaign");
        String admin = "admin-ds@chotot.vn";
        String startDate = "";
        String endDate = "";
        String query =
                "INSERT INTO \"campaign\"(\n" +
                        "    name,\n" +
                        "    apply_for,\n" +
                        "    campaign_type,\n" +
                        "    start_at,\n" +
                        "    end_at,\n" +
                        "    limit_total,\n" +
                        "    limit_per_account,\n" +
                        "    is_active,\n" +
                        "    is_deleted,\n" +
                        "    created_at,\n" +
                        "    updated_at,\n" +
                        "    created_by\n" +
                        "  )\n" +
                        "values(\n" +
                        "  '" + getCampaignName() + "',\n" +
                        "  '" + apType + "',\n" +
                        "  'private',\n" +
                        "  '12-05-2020 17:00:00+00:00',\n" +
                        "  '03-30-2021 17:00:00+00:00',\n" +
                        "  " + apLimitTotal + ",\n" +
                        "  " + apLimitPerAccount + ",\n" +
                        "  " + isActive + ",\n" +
                        "  " + is_deleted + ",\n" +
                        "  '2020-12-07 05:36:31.542000',\n" +
                        "  '2020-12-07 05:36:31.543000',\n" +
                        "  '" + admin + "'\n" +
                        ")\n" +
                        "RETURNING id";
        promotionDB.insertToDB(query);
    }

    public static void addAPCredit(int freeCredit, int paidCredit) {

    }
}
