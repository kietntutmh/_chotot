package api.utils;

import org.apache.commons.lang3.StringUtils;

import static api.configuration.DataConfig.defaultPassword;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accessTokenCP;
import static api.utils.GetAccessToken.*;
import static com.vn.chotot.configuration.Constant.CREATE_NEW_USER;
import static com.vn.chotot.configuration.Constant.EXPECTED_USER_PHONE;
import static desktop.configuration.LoginConfig.checkTempAccountUsed;

public class CheckAccessToken {

    public static void setAccessToken() {
        if (!StringUtils.isEmpty(EXPECTED_USER_PHONE)) {
            getAccessTokenOfUser(EXPECTED_USER_PHONE, defaultPassword);
            return;
        } else if (!CREATE_NEW_USER & global_accessToken.isEmpty()) {
            getAccessTokenOfExistingUser();
            return;
        } else if (CREATE_NEW_USER & global_accessToken.isEmpty()) {
            if (!checkTempAccountUsed()) {
                getAccessTokenOfNewUser();
//                updateDefaultProfile();       //Existed in getAccessToken)OfNewUser
            }
            return;
        } else if (CREATE_NEW_USER & checkTempAccountUsed()) {
            getAccessTokenOfExistingUser();
        }
    }

    public static void setAccessTokenOfCP() {
        if (global_accessTokenCP.isEmpty()) getAccessTokenOfCPUser();
    }

}
