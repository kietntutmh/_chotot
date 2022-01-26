package desktop.scenarios_old.chat;

import desktop.base.BaseTest;

import static desktop.configuration.CPConfig.setTempAccountCPIndex;

public class Chat_AddFilterKeyword_SetAccount extends BaseTest {
//    @BeforeTest
    public void setupAccountCP() {
        setTempAccountCPIndex(2);
    }
}
