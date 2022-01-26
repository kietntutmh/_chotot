package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

public class Chat_VerifyUserCanSendSpecialMessage extends BaseTest {

    @Test(
            groups = {"uat", "dev"},
            description = "CHAT_WEB  >> Chat, Verify user can send special message, Quoc Tran, GROWTH")
    public void verifyUserCanSendSpecialMessage() {
        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();

        // Go to chat page
        cm_chat.createChatRoom();

        // Verify user cannot send blank message
        chatPage.verifyUserCannotSendBlankMessage();

        // Verify user can send special message: 1 and > 100 characters
        // Date for first message, content, position (right), sending time
        chatPage.verifyUserCanSendSingleCharacter();
        chatPage.verifyUserCanSendMore100Characters();
    }
}
