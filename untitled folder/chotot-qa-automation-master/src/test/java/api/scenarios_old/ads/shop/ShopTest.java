package api.scenarios_old.ads.shop;

import api.base.BaseAPITest;
import projects.functions.chotot._common.api.ads.shop.CM_API_SHOP_CreateShop;

import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class ShopTest extends BaseAPITest {
    CM_API_SHOP_CreateShop cm_api_shop_createShop;

    public void createPTYShop() {
        //        setAccessToken();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_shop_createShop = new CM_API_SHOP_CreateShop();
        //        cm_api_shop_createShop.createShopPTY("");
    }
}
