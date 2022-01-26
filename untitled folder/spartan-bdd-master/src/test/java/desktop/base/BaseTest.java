package desktop.base;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.driver.selenium.DriverFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Random;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessToken;
import static com.vn.chotot.configuration.Constant.*;
import static com.vn.chotot.exception.ExceptionHandler.resetExceptionDebug;
import static desktop.configuration.BaseConfig.numberPopupDisplayed;
import static desktop.configuration.LoginConfig.setTempAccountLoginIndex;

@Deprecated
public class BaseTest {
    public final ExcelProvider excelDataProvider = new ExcelProvider();

//    @BeforeMethod(alwaysRun = true)
    @Parameters("browserName")
    public void setUpTestMethod(@Optional("chrome") String browserName) {
        // Init driver
        DriverFactory.instance().createWebDriver(browserName);

        // Set access token
        setAccessToken();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTestMethod() {
        // Exit web driver
        if (!IS_DEBUGGING) DriverFactory.instance().disposeWebDriver();

        // Reset value to check popup closed for the next execution
        numberPopupDisplayed = 0;

        // Reset account id, access token, new phone
        global_accountID = "";
        global_accountOID = "";
        global_accessToken = "";
        global_accessTokenCP = "";
        newUserPhone = "";

        // Reset Retry_Ad
        tempRetryResetToDefault();

        // Reset exception debug for the next execution
        resetExceptionDebug();

        // Reset temp account login
        setTempAccountLoginIndex(-1);
        setTempAccountAPIIndex(-1);
    }

    public int getRandomOrder(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    //Nightly Mode executes all tests
    //This method only works when execute all Test Class
    public void checkActiveOrder(int actOrder, int expOrder) {
        if (!TEST_RUN_TYPE.equalsIgnoreCase("nightly") && RANDOM_TESTCASE.equals(true)) {
            if (actOrder != expOrder) throw new SkipException("SKIPPED");
        }
    }
}
