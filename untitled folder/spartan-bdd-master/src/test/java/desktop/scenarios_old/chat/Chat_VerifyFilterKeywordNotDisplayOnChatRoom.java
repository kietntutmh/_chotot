package desktop.scenarios_old.chat;

import api.feature.chat.SendMessage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.cp.CM_CP_AddFilterKeyword;

import java.util.List;

import static api.configuration.DataConfig.setTempAccountAPIAndGetToken;
import static api.utils.GetJSONString.extractJSONChat_FilterWord;
import static desktop.configuration.DataConfig.chatFilterKeywordExcelFile;

public class Chat_VerifyFilterKeywordNotDisplayOnChatRoom extends Chat_AddFilterKeyword_SetAccount {

    private static void setAccountAPITest() {
        setTempAccountAPIAndGetToken(17);
    }

    @Test
    public void verifyOldFilterKeywordNotDisplayAtChatRoom() {
        // Set account testing
        setAccountAPITest();

        CM_CP_AddFilterKeyword cm_cp_addFilterKeyword = new CM_CP_AddFilterKeyword();
        SendMessage sendMessage = new SendMessage();

        List<String> data = cm_cp_addFilterKeyword.getOldFilterKeyword();

        for (String item : data) {
            String keyword = extractJSONChat_FilterWord("9735094", "5da540adb5957c000c518721", item);
            sendMessage.verifyFilterKeywordMaskAsStar(keyword);
        }

    }

    @Test
    public void verifyNewFilterKeywordNotDisplayAtChatRoom() {
        // Set account testing
        setAccountAPITest();

        CM_CP_AddFilterKeyword cm_cp_addFilterKeyword = new CM_CP_AddFilterKeyword();
        SendMessage sendMessage = new SendMessage();

        // Setup test data
        excelDataProvider.getExcelFileSheet(chatFilterKeywordExcelFile, "FilterKeyword");

        // Get filter keyword data
        List<String> keywordData = excelDataProvider.getColumnData(0);

        cm_cp_addFilterKeyword.addNewFilterKeyWord(keywordData);

        for (String item : keywordData) {
            String keyword = extractJSONChat_FilterWord("9735094", "5da540adb5957c000c518721", item);
            sendMessage.verifyFilterKeywordMaskAsStar(keyword);
        }
    }
}
