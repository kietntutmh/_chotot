package api.feature.profile;

import com.vn.chotot.api.rest_assured.RestAssure;
import io.restassured.response.Response;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;

public class ShopAlias {

    public static String getShopAliasByPhone(String phone) {
        Response response =
                RestAssure.instance().get(global_accessToken, String.format(gatewayShopByPhone, phone));
        String shop_alias = getPropertyValue(response, "[0].alias");
        global_shopAlias = shop_alias;
        return shop_alias;
    }

}
