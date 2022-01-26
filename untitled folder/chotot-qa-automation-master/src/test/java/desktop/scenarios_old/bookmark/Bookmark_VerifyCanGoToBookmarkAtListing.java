package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.BookmarkPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Bookmark;

public class Bookmark_VerifyCanGoToBookmarkAtListing extends BaseTest {

    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    @Test(
            groups = {"staging", "k8s"},
            description = "Bookmark, Verify can go to Bookmark at listing, Tuan Chieu, GROWTH")
    public void verifyUserCanGoToBookmarkedAtListing() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_Bookmark cm_bookmark = new CM_Bookmark();
        BookmarkPage bookmarkPage = new BookmarkPage();

        // Go to Saved search page and remove all Saved search
        cm_bookmark.goToBookmarkPage(false);
        bookmarkPage.remoteAllBookmarked();

        // Verify user can not have Bookmark over total limit
        cm_bookmark.BookmarkByIndex(4);

        // Go to Saved search on listing page
        cm_bookmark.goToBookmarkAtListing();
    }
}
