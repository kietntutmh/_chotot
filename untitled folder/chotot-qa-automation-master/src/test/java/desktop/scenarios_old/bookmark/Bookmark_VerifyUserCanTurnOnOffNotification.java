package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.BookmarkPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Bookmark;

public class Bookmark_VerifyUserCanTurnOnOffNotification extends BaseTest {

    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    @Test(
            groups = {"staging", "k8s"},
            description = "Bookmark, Verify User can turn on off notification, Tuan Chieu, GROWTH")
    public void verifyUserCanTurnOnOffNotification() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_Bookmark cm_bookmark = new CM_Bookmark();
        BookmarkPage bookmarkPage = new BookmarkPage();

        // Go to Bookmark page
        cm_bookmark.goToBookmarkPage(false);

        // Remove all bookmark
        bookmarkPage.remoteAllBookmarked();
        bookmarkPage.clickStartSearch();

        // Bookmark random item
        cm_bookmark.BookmarkRandom(false);

        // Go to Bookmark page
        cm_bookmark.goToBookmarkPage(true);

        // Turn off notification of bookmark
        bookmarkPage.turnOffNotification(0);

        // Turn off notification of bookmark
        bookmarkPage.turnOnNotification(0);
    }
}
