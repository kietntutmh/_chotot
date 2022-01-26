package projects.functions.chotot._common.cp;

import desktop.base.BaseTest;
import desktop.pages.CP.Chat.Chat_FilterKeyword;

import java.util.List;

public class CM_CP_AddFilterKeyword extends BaseTest {

    public void addNewFilterKeyWord(List<String> newKeyword) {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        Chat_FilterKeyword chat_filterKeyword = new Chat_FilterKeyword();

        cm_cp_login.loginAndGoToChat_FilterKeyword();

        for (String item : newKeyword) {
            if (item != "") {
                chat_filterKeyword.clickAddKeyword();
                chat_filterKeyword.addNewKeyword(item);
            }
        }
    }

    public List<String> getOldFilterKeyword() {
        // Initialize objects
        CM_CP_Login cm_cp_login = new CM_CP_Login();
        Chat_FilterKeyword chat_filterKeyword = new Chat_FilterKeyword();

        cm_cp_login.loginAndGoToChat_FilterKeyword();

        return chat_filterKeyword.getAllKeyword();
    }
}
