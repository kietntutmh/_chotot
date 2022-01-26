package desktop.scenarios_old.cp.ad_review.accept;

import desktop.base.BaseTest;

import static desktop.configuration.CPConfig.setTempAccountCPIndex;

public class CP_AcceptAd_SetAccount extends BaseTest {

//    @BeforeMethod
    public void setupAccountCP() {
        setTempAccountCPIndex(2);
    }
}
