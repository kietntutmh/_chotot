package desktop.scenarios_old.bookmark;

import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.AdsListing.AdsListing;
import desktop.pages.BookmarkPage;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Bookmark;

public class Bookmark_VerifyBookmarkAtListingSuccess extends BaseTest {
    CM_Bookmark cm_bookmark;
    BookmarkPage bookmarkPage;
    AdsListing adsListing;

    private void setupAccount() {
        LoginConfig.setTempAccountLoginIndex(13);
    }

    @Test(
            groups = {"staging", "k8s"},
            description =
                    "Bookmark, Verify Bookmark with Grid-view successfully, Tuan Chieu, GROWTH")
    public void verifyBookmarkWithGridViewSuccess() {
        // setup account test
        setupAccount();

        // Initiate object instances
        cm_bookmark = new CM_Bookmark();
        bookmarkPage = new BookmarkPage();

        // Go to home page
        cm_bookmark.goToBookmarkPage(false);

        // Remote all save search
        bookmarkPage.remoteAllBookmarked();
        bookmarkPage.clickStartSearch();

        // Save search with Grid-view
        cm_bookmark.BookmarkRandom(true);
    }

    @Test(
            description =
                    "Bookmark, Verify Bookmark with List-view successfully, Tuan Chieu, GROWTH")
    public void verifyBookmarkWithListViewSuccess() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        cm_bookmark = new CM_Bookmark();
        bookmarkPage = new BookmarkPage();

        // Go to home page
        cm_bookmark.goToBookmarkPage(false);

        // Remote all saved search
        bookmarkPage.remoteAllBookmarked();
        bookmarkPage.clickStartSearch();

        // Save search with List-view
        cm_bookmark.BookmarkRandom(false);
    }

    @Test(
            description =
                    "Bookmark, Verify Bookmark with button save at bottom page, Tuan Chieu, GROWTH")
    public void verifyBookmarktonBottomPageSuccess() {
        // Setup account test
        setupAccount();

        // Initiate object instances
        cm_bookmark = new CM_Bookmark();
        bookmarkPage = new BookmarkPage();

        // Go to home page
        cm_bookmark.goToBookmarkPage(false);

        // Remote saved search before
        bookmarkPage.remoteAllBookmarked();
        bookmarkPage.clickStartSearch();

        // Save search with List-view
        cm_bookmark.BookmarkWithButtonBottomPage();
    }
}
