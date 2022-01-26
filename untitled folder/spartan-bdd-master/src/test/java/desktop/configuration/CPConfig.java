package desktop.configuration;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

import static com.vn.chotot.configuration.Constant.DOMAIN;

public class CPConfig {

    public static final String loginCPURL = "https://cp.chotot." + DOMAIN + "/signin";
    // Data in excel file Login.xlsx
    public static final String loginCPSheetName = "CP";
    static final Logger log = Log4jFactory.instance().createClassLogger(CPConfig.class);
    private static int tempAccountCPRowIndex = -1;

    public static int getMainAccountCPIndex() {
        return 1;
    }

    public static int getTempAccountCPIndex() {
        return tempAccountCPRowIndex;
    }

    public static void setTempAccountCPIndex(int index) {
        log.info("\n----- Set up account CP to index: " + index);
        tempAccountCPRowIndex = index;
    }

    public static boolean checkTempAccountCPUsed() {
        return tempAccountCPRowIndex > 0;
    }
}
