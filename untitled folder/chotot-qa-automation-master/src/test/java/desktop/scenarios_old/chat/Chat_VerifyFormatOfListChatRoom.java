package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

import static desktop.configuration.LoginConfig.setTempAccountLoginIndex;

public class Chat_VerifyFormatOfListChatRoom extends BaseTest {

    @Test(
            groups = {"production", "k8s"},
            description = "CHAT_WEB  >> Chat, Verify format of list chat room, Quoc Tran, GROWTH")
    public void verifyFormatOfListChatRoom() {
        // Set account testing
        setTempAccountLoginIndex(3);

        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();

        // Go to chat page
        cm_chat.goToChatPage();

        // Verify list chat room shows properly info:
        // username, time, product name, product image
        chatPage.verifyFormatOfListChatRoom();
    }
}
