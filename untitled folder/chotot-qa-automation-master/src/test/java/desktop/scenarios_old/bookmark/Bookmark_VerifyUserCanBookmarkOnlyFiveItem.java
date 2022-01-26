package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.BookmarkPage;
import projects.functions.chotot._common.CM_Bookmark;

public class Bookmark_VerifyUserCanBookmarkOnlyFiveItem extends BaseTest {

    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    public void verifyUserCanMarkedOnlyFiveItem() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_Bookmark cm_bookmark = new CM_Bookmark();
        BookmarkPage bookmarkPage = new BookmarkPage();

        // Go to Bookmark page and remove all Marked Item
        cm_bookmark.goToBookmarkPage(false);
        bookmarkPage.remoteAllBookmarked();

        // Verify user can marked only 5 item
        cm_bookmark.BookmarkOverLimit();

        // Go to bookmark page
        cm_bookmark.goToBookmarkAtListing();

        // Verify bookmark page display only 5 item
        bookmarkPage.verifyTotalBookmark();
    }
}
