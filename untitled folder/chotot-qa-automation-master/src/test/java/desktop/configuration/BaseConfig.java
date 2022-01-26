package desktop.configuration;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;

public class BaseConfig {
    public static final int maxNumberPopupDisplayed = 2;
    public static boolean showPOS = false;

    // Check popup closed or not
    public static int numberPopupDisplayed = 0;

    // Check POS
    public static boolean checkPOSDisplayed() {
        showPOS = true; // To reuse in manage ad page
        return IS_CHECKING_POS;
    }
}
