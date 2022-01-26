package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

public class Chat_VerifyUserCanSendTextMessage extends BaseTest {

    @Test(
            groups = {"uat", "dev"},
            description = "CHAT_WEB  >> Chat, Verify user can send text message, Quoc Tran, GROWTH")
    public void verifyUserCanSendTextMessage() {
        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();

        // Go to chat page
        cm_chat.createChatRoom();

        // Verify user can send text message:
        // Date for first message, content, position (right), sending time
        String textMessage = "Send text message to chat";
        chatPage.verifyUserCanSendTextMessage(textMessage);
    }
}
