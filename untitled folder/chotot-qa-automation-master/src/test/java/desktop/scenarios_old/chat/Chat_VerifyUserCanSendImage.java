package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

public class Chat_VerifyUserCanSendImage extends BaseTest {

    @Test(
            groups = {"uat", "dev"},
            description = "CHAT_WEB  >> Chat, Verify user can send image, Quoc Tran, GROWTH")
    public void verifyUserCanSendImage() {
        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();

        // Go to chat page
        cm_chat.createChatRoom();

        // Verify user can send image
        chatPage.verifyUserCanSendImage();
    }
}
