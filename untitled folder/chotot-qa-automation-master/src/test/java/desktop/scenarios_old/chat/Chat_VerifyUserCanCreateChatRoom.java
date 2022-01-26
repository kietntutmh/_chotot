package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

public class Chat_VerifyUserCanCreateChatRoom extends BaseTest {

    @Test(
            groups = {"production", "k8s"},
            description =
                    "CHAT_WEB  >> Chat, Verify user can create Chat room successfully, Quoc Tran, GROWTH")
    public void verifyUserCanCreateChatRoom() {
        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();

        // Go to chat page
        cm_chat.createChatRoom();

        // Verify Chat page shows fully info:
        // name, title, message section, send button, privacy button, safety tip
        if (!chatPage.checkAlertMessageDisplayed()) chatPage.verifyUIElements();
    }
}
