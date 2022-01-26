package desktop.configuration;

import static com.vn.chotot.configuration.Constant.DOMAIN;

public class RegisterConfig {
    public static final String registerURL = "https://www.chotot." + DOMAIN + "/register";
    public static final String registerExcelFile = "data/excel/Login.xlsx";
    public static final String registerSheetName = "Register";

    public static final int phoneValidLength = 10;
    public static final String PASSWORD_DEFAULT = "123456";
    public static final String OTP_VALID = "123456";
    public static final String OTP_INVALID_MSG = "OTP: Mã OTP không hợp lệ.";

    public static final String requiredPhoneMsg = "Phone should be required.";
    public static final String requiredPasswordMsg = "Password should be required.";
}
