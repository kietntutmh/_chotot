package desktop.scenarios_old.cp.ad_review.reject;

import desktop.base.BaseTest;

import static desktop.configuration.CPConfig.setTempAccountCPIndex;

public class CP_RejectAd_SetAccount extends BaseTest {

//    @BeforeMethod
    public void setupAccountCP() {
        setTempAccountCPIndex(3);
    }
}
