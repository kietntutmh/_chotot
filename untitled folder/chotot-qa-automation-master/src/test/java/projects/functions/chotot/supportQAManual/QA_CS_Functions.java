package projects.functions.chotot.supportQAManual;

import org.apache.commons.lang3.StringUtils;
import projects.functions.APISupportFunctions;

import static api.configuration.DataConfig.defaultPassword;
import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.*;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class QA_CS_Functions extends APISupportFunctions {
    public static void csSupport_CreateUser() {
        String shopType = StringUtils.isEmpty(System.getProperty("shopType")) ? "" : System.getProperty("shopType");
        String resultStr = "";
        switch (shopType.toLowerCase().trim()) {
            case "pty":
                getAccessTokenNewUserShopPTY();
                resultStr = "SHOP PTY";
                break;
            case "veh":
                getAccessTokenNewUserShopVEH();
                resultStr = "SHOP VEH";
                break;
            case "elt":
                getAccessTokenNewUserShopELT();
                resultStr = "SHOP ELT";
                break;
            case "":
                getAccessTokenOfNewUser();
                resultStr = "PRIVATE";
                break;
            default:
                resultStr = "FAILED. PLEASE TRY AGAIN !!!!!";
                break;
        }

        //Print & send Result
        System.out.println("\n\n================ CREATE NEW USER : " + resultStr.toUpperCase() + " ================\n");
        System.out.println("PHONE NUMBER: " + newUserPhone);
        System.out.println("PASSWORD: " + defaultPassword);
        System.out.println("ACCOUNT ID: " + global_accountID);
        System.out.println("\n=====================================================\n\n\n");
    }

    public static void topupDongTot(){
        String userPhone = StringUtils.isEmpty(System.getProperty("userPhone")) ? "" : System.getProperty("userPhone");
        String dongTotAmount = StringUtils.isEmpty(System.getProperty("dongTotAmount")) ? "" : System.getProperty("dongTotAmount");
        String dongTotFreeAmount = StringUtils.isEmpty(System.getProperty("dongTotFreeAmount")) ? "" : System.getProperty("dongTotFreeAmount");
        String dongTotBTAmount = StringUtils.isEmpty(System.getProperty("dongTotFreeAmount")) ? "" : System.getProperty("dongTotFreeAmount");

        if(userPhone.isEmpty()){
            getAccessTokenOfNewUser();
            userPhone = newUserPhone;
        }

        if(!dongTotAmount.isEmpty() && dongTotBTAmount.isEmpty() && dongTotFreeAmount.isEmpty()){
            switch(dongTotAmount.trim().toLowerCase()){
                case "20k":
                    topupDongTotWithMomo_20k(userPhone);
                    break;
                case "50k":
                    topupDongTotWithMomo_50k(userPhone);
                    break;
                case "100k":
                    topupDongTotWithMomo_100k(userPhone);
                    break;
                case "200k":
                    topupDongTotWithMomo_200k(userPhone);
                    break;
                case "500k":
                    topupDongTotWithMomo_500k(userPhone);
                case "1000k":
                    topupDongTotWithMomo_1000k(userPhone);
                case "2000k":
                    topupDongTotWithMomo_2000k(userPhone);
                    break;
            }
        }else if(dongTotAmount.isEmpty() && dongTotBTAmount.isEmpty() && !dongTotFreeAmount.isEmpty()){
            dongTotFreeAmount = dongTotFreeAmount.trim().toLowerCase().replace("k", "000");
            topupDongTotFree_ByRedeemPromotionCode(global_accountID, userPhone, Integer.parseInt(dongTotFreeAmount));
        }else { //bt

        }


        System.out.println("\n\n================ NẠP ĐỒNG TỐT " + dongTotAmount.toUpperCase() + " ================\n");
        System.out.println("PHONE NUMBER: " + userPhone);
        System.out.println("PASSWORD: " + defaultPassword);
        System.out.println("\n=====================================================\n\n\n");

    }
}
