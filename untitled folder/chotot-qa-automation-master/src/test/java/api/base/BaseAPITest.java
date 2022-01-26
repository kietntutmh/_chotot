package api.base;

import com.vn.chotot.data.ExcelProvider;
import org.testng.annotations.AfterMethod;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetAccessToken.listNewUser;
import static com.vn.chotot.exception.ExceptionHandler.resetExceptionDebug;

@Deprecated
public class BaseAPITest {
    public final ExcelProvider excelDataProvider = new ExcelProvider();

//    @BeforeMethod(alwaysRun = true)
    public void setUpTestClass() {
        // Set access token
        setAccessToken();

        // Reset temp account login
        setTempAccountAPIIndex(-1);

        // Retry
        tempRetrySetToTest();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTestMethod() {
        // Reset exception debug for the next execution
        resetExceptionDebug();

        // Reset account id, access token, new phone
        global_accountID = "";
        global_accountOID = "";
        global_accessToken = "";
        global_accessTokenCP = "";
        newUserPhone = "";
        listNewUser.clear();

        // Reset values
        tempRetryResetToDefault();
    }
}
