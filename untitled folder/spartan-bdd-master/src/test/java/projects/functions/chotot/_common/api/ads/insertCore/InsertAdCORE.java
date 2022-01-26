package projects.functions.chotot._common.api.ads.insertCore;

import projects.functions.APISupportFunctions;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

public class InsertAdCORE extends APISupportFunctions {
    private final CM_CP_API_AcceptAd cpAccept = new CM_CP_API_AcceptAd();
    private final CM_CP_API_RejectAd cpReject = new CM_CP_API_RejectAd();


//    private String insertAdCORE_PTY(JsonObject bodyJSONData, String cpAction, String subCateID, boolean isEditAd) {
//        Assert.assertFalse(subCateID.isEmpty(), "Insert Ad CORE: SubCateID is null");
//
//        if (isEditAd)
//            editNewAd(bodyJSONData);
//        else
//            insertNewAd(bodyJSONData);
//
//        // Do based on input action
//        switch (cpAction.toLowerCase().trim()) {
//            case "accept":
//                acceptNewAd(generateAdBodyForCP_PTY(bodyJSONData));
//                break;
//
//            case "acceptpay":
//                paymentOrderWithDongTot();
//                cpAccept.acceptAd_PTY(dataKeys, dataValues, subcateID);
//                break;
//
//            case "acceptpaydt4b":
//                paymentOrderDT4B();
//                cpAccept.acceptAd_PTY(dataKeys, dataValues, subcateID);
//                acceptNewAd(bodyDataToAcceptAd);
//                break;
//
//            case "reject":
//                cpReject.rejectAd_PTY(dataKeys, dataValues, subcateID);
//                break;
//
//            case "rejectpay":
//                // Pay the order
//                paymentOrderWithDongTot();
//                // Reject
//                cpReject.rejectAd_PTY(dataKeys, dataValues, subcateID);
//                break;
//
//            case "rejectpaydt4b":
//                paymentOrderDT4B();
//                cpReject.rejectAd_PTY(dataKeys, dataValues, subcateID);
//                break;
//
//            case "pay":
//                paymentOrderWithDongTot();
//                break;
//
//            default:
//                break;
//        }
//        return tempAdID;
//    }
//
//    private static String generateAdBodyForCP_PTY(String bodyJSONData){
//        JsonParser parser = new JsonParser();
//        JsonObject bodyJsonSrc = parser.parse(bodyJSONData).getAsJsonObject();
////        String imgurl = obj.get("imgurl").getAsString();
//
//
//        initBodyJson();
//        initBodyChild();
//
//        bodyChildJson.addProperty("subject", bodyJsonSrc.get("").get);
//    }
}
