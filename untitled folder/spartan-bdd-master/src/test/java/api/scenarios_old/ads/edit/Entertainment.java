package api.scenarios_old.ads.edit;

import api.base.BaseAPITest;
import desktop.pages.Dashboard.PrivateDashboard;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditEntertainment;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;

import static api.configuration.DataConfig.tempAdID;

public class Entertainment extends BaseAPITest {
    CM_API_Ads_EditEntertainment cm_api_ads_editEntertainment;
    CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;

    public void initObjects() {
        cm_api_ads_editEntertainment = new CM_API_Ads_EditEntertainment();
        cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
    }

    public void verifyEditAPI_Entertainment_Instrument() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdInstrument(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdInstrument(tempAdID, "Accept");
    }

//    @Test(
//            description =
//                    "Edit Ad - Entertainment, Verify edit Instrument Buy ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Instrument_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdInstrumentBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdInstrumentBuy(tempAdID, "Accept");
    }

//    @Test(description = "Edit Ad - Entertainment, Verify edit Sport ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Sport() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdSport(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdSport(tempAdID, "Accept");
    }

//    @Test(
//            description = "Edit Ad - Entertainment, Verify edit Sport Buy ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Sport_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdSportBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdSportBuy(tempAdID, "Accept");
    }

//    @Test(description = "Edit Ad - Entertainment, Verify edit Book ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Book() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdBook(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdBook(tempAdID, "Accept");
    }

//    @Test(
//            description = "Edit Ad - Entertainment, Verify edit Book Buy ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Book_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdBookBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdBookBuy(tempAdID, "Accept");
    }

//    @Test(
//            description =
//                    "Edit Ad - Entertainment, Verify edit Collectibles ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Collectibles() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdCollectibles(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdCollectibles(tempAdID, "Accept");
    }

//    @Test(
//            description =
//                    "Edit Ad - Entertainment, Verify edit Collectibles Buy ad successfully, VU HOANG, SRE")
    public void verifyEditAPI_Entertainment_Collectibles_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdCollectiblesBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdCollectiblesBuy(tempAdID, "Accept");
    }

    public void verifyEditAPI_Entertainment_Gaming() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdGaming(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdGaming(tempAdID, "Accept");
    }

    public void verifyEditAPI_Entertainment_Gaming_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdGamingBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdGamingBuy(tempAdID, "Accept");
    }

    public void verifyEditAPI_Entertainment_Hobby() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdHobby(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdHobby(tempAdID, "Accept");
    }

    public void verifyEditAPI_Entertainment_Hobby_Buy() {
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdHobbyBuy(true, "Accept");
        cm_api_ads_editEntertainment.editNewAdHobbyBuy(tempAdID, "Accept");
    }
}
