package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.components.TopHeader;
import desktop.configuration.LoginConfig;
import desktop.pages.BookmarkPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Bookmark;

public class Bookmark_VerifyUserCanRemoveBookmarkAtListing extends BaseTest {
    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    @Test(
            groups = {"staging", "k8s"},
            description =
                    "Boommark, Verify user can remove Bookmark at listing, Tuan Chieu, GROWTH")
    public void verifyUserCanRemoveBookmarkAtListing() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        CM_Bookmark cm_bookmark = new CM_Bookmark();
        BookmarkPage bookmarkPage = new BookmarkPage();

        // Go to Bookmark page and remove all marked item
        cm_bookmark.goToBookmarkPage(false);
        bookmarkPage.remoteAllBookmarked();
        bookmarkPage.clickStartSearch();

        // Go to listing and mark item
        cm_bookmark.BookmarkRandom(false);

        // Go to Bookmark Page and select a marked
        new TopHeader().verifyClickBookmarkWhenLogin();
        bookmarkPage.goToRandomBookmarked();

        // Remove Bookmark on listing by click bookmark button
        cm_bookmark.removeBookmarkInListing();
    }
}
