package projects.functions.chotot._common.api.ads.shop;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptShop;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.feature.shop.ShopAds.*;
import static api.utils.GetJSONString.*;
import static desktop.configuration.LoginConfig.tempUserPhone;

public class CM_API_SHOP_CreateShop {
    private final CM_CP_API_AcceptShop cm_cp_api_acceptShop;
    private final CM_CP_API_RejectShop cm_cp_api_rejectShop;

    public CM_API_SHOP_CreateShop() {
        cm_cp_api_acceptShop = new CM_CP_API_AcceptShop();
        cm_cp_api_rejectShop = new CM_CP_API_RejectShop();
        ExcelProvider excelDataProvider = new ExcelProvider();
        List<String> dataKeys = new ArrayList<>();
        List<String> dataValues = new ArrayList<>();
        Map<String, Object> mapData = new HashMap<>();
    }

    public String createShopPTY(String cpAction) {
        // Upload cover photo

        // Upload avatar photo

        // Craete shops and getID to check on CP
        createShopPhotoCover();
        createShopPhotoAvatar();
        String bodyData = extractJSONShop_PTY_Create(tempUserPhone);
        String shopID = createShop(bodyData);

        // CP Action
        switch (cpAction.trim().toLowerCase()) {
            case "accept":
                cm_cp_api_acceptShop.acceptShop_PTY(shopID);
                break;
            case "reject":
                cm_cp_api_rejectShop.rejectShop_PTY(shopID);
                break;
        }
        return shopID;
    }

    public String createShopVEH(String cpAction) {
        // Craete shops and getID to check on CP
        createShopPhotoCover();
        createShopPhotoAvatar();
        String bodyData = extractJSONShop_VEH_Create(tempUserPhone);
        String shopID = createShop(bodyData);

        // CP Action
        switch (cpAction.trim().toLowerCase()) {
            case "accept":
                cm_cp_api_acceptShop.acceptShop_PTY(shopID);
                break;
            case "reject":
                cm_cp_api_rejectShop.rejectShop_PTY(shopID);
                break;
        }
        return shopID;
    }

    public String createShopELT(String cpAction) {
        // Craete shops and getID to check on CP
        createShopPhotoCover();
        createShopPhotoAvatar();
        String bodyData = extractJSONShop_ELT_Create(tempUserPhone);
        String shopID = createShop(bodyData);

        // CP Action
        switch (cpAction.trim().toLowerCase()) {
            case "accept":
                cm_cp_api_acceptShop.acceptShop_PTY(shopID);
                break;
            case "reject":
                cm_cp_api_rejectShop.rejectShop_PTY(shopID);
                break;
        }
        return shopID;
    }
}
