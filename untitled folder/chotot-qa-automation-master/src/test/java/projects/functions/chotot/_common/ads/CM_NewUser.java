package projects.functions.chotot._common.ads;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class CM_NewUser extends BaseAPITest {
    @Test
    public void createNewUserForTest() {
        topupDongTotWithMomo(tempUserPhone);
    }
}
