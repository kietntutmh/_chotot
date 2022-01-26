package desktop.configuration;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import static api.utils.GetAccessToken.getAccessTokenOfExistingUser;
import static com.vn.chotot.configuration.Constant.DOMAIN;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;

public class LoginConfig {

    public static final String loginURL = "https://www.accounts.chotot." + DOMAIN + "/login";
    public static final String requiredMessage = "Phone and Password should be required fields";
    public static final String invalidMessage =
            "Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại.";
    public static final String wrongPhone = "Phone: Số điện thoại không hợp lệ.";
    public static final String wrongPassword = "Password: Mật khẩu phải có ít nhất 5 kí tự.";
    // Data in excel file Login.xlsx
    public static final String loginExcelFile = "data/excel/Login.xlsx";
    public static final String loginSheetName = "Login";
    public static final String loginSheetNameInValid = "Login_Invalid";
    static final Logger log = Log4jFactory.instance().createClassLogger(LoginConfig.class);
    private static final int productionAccountRowIndex = 2;
    private static final int stagingAccountRowIndex =
            5; // This value should be the same as API config
    private static final int mainAccountRowIndex =
            DOMAIN.equalsIgnoreCase("com") ? productionAccountRowIndex : stagingAccountRowIndex;
    public static String tempUserPhone = "";
    public static String tempUserEmail = "";
    private static int tempAccountRowIndex = -1;

    public static int getMainAccountLoginIndex() {
        return mainAccountRowIndex;
    }

    public static int getTempAccountLoginIndex() {
        return tempAccountRowIndex;
    }

    public static void setTempAccountLoginIndex(int index) {
        if (index > -1) {
            log.info("\n----- Setup UI account Chotot to index: " + index);
            setExceptionDebug("Index of temp user phone (UI): " + index);
        }
        tempAccountRowIndex = index;
    }

    public static boolean checkTempAccountUsed() {
        return tempAccountRowIndex > 0;
    }

    public static void setTempAccountAndGetToken(int index) {
        setTempAccountLoginIndex(index);
        getAccessTokenOfExistingUser();
    }
}
