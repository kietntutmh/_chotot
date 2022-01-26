package desktop.scenarios_old.chat;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.pages.ChatPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Chat;

public class Chat_VerifyIntroductionChatDisplayed extends BaseTest {

    @Test(
            description = "CHAT_WEB  >> Chat, Verify introduction chat displayed, Quoc Tran, GROWTH",
            enabled = false)
    public void verifyIntroductionDisplayed() {
        // Initiate object instances
        CM_Chat cm_chat = new CM_Chat();
        ChatPage chatPage = new ChatPage();
        TopHeader topHeader = new TopHeader();

        // Go to chat page
        cm_chat.goToChatPage();

        // Remove all existing thread chat
        chatPage.removeAllChatRoom();

        // Click Chat on top header
        topHeader.clickChatLink();

        // Verify introduction chat message displayed
        chatPage.verifyIntroductionChatDisplayed();
    }
}
