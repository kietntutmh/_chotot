package desktop.configuration;

import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;

public class DataConfig {
    // Ads excel data
    public static final String adVehicleExcelFile = "data/excel/ui/ads/Ads_Vehicle.xlsx";
    public static final String adPTYExcelFile = "data/excel/ui/ads/Ads_PTY.xlsx";
    public static final String adELTExcelFile = "data/excel/ui/ads/Ads_ELT.xlsx";
    public static final String adMomAndBabyExcelFile = "data/excel/ui/ads/Ads_MomAndBaby.xlsx";
    public static final String adFashionExcelFile = "data/excel/ui/ads/Ads_Fashion.xlsx";
    public static final String adElectricAppliancesExcelFile =
            "data/excel/ui/ads/Ads_ElectricAppliances.xlsx";
    public static final String adEntertainmentExcelFile = "data/excel/ui/ads/Ads_Entertainment.xlsx";
    public static final String adPetExcelFile = "data/excel/ui/ads/Ads_PETS.xlsx";
    public static final String adRequisiteExcelFile = "data/excel/ui/ads/Ads_Requisite.xlsx";
    public static final String adJobExcelFile = "data/excel/ui/ads/Ads_Job.xlsx";
    public static final String adServiceExcelFile = "data/excel/ui/ads/Ads_Services.xlsx";
    public static final String adOtherExcelFile = "data/excel/ui/ads/Ads_Other.xlsx";
    public static final String adHousehold_Furniture_PlantExcelFile =
            "data/excel/ui/ads/Ads_Household_Furniture_Plant.xlsx";
    // Ads POS excel data
    public static final String adVehicleBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx";
    public static final String adPTYBumpExcelFile = "data/excel/ui/pos/Bump_Ads_PTY.xlsx";
    public static final String adELTBumpExcelFile = "data/excel/ui/pos/Bump_Ads_ELT.xlsx";
    public static final String adJobBumpExcelFile = "data/excel/ui/pos/Bump_Ads_JOB.xlsx";
    public static final String adPetsBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Pets.xlsx";
    public static final String adMomAndBabyBumpExcelFile = "";
    public static final String adFurnitureBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Furniture.xlsx";
    public static final String adFashionBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Fashion.xlsx";
    public static final String adEntertainmentBumpExcelFile =
            "data/excel/ui/pos/Bump_Ads_Entertainment.xlsx";
    public static final String adIndustrialBumpExcelFile =
            "data/excel/ui/pos/Bump_Ads_Industrial.xlsx";
    public static final String adOfficeEquipmentBumpExcelFile =
            "data/excel/ui/pos/Bump_Ads_OfficeEquipment.xlsx";
    public static final String adServicesBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Services.xlsx";
    public static final String adOthersBumpExcelFile = "data/excel/ui/pos/Bump_Ads_Others.xlsx";
    public static final String adHousehold_Furniture_PlantBumpExcelFile = "";
    // Ads Listing_fee excel data
    public static final String adListing_feeExcelFile = "data/excel/ui/listing_fee/Ads_Vehicle.xlsx";
    // Ads Payment excel data
    public static final String adPaymentExcelFile = "data/excel/ui/payment/Napas.xlsx";
    static Logger log = Log4jFactory.instance().createClassLogger(api.configuration.DataConfig.class);
    //chat filter keyword excel data
    public static final String chatFilterKeywordExcelFile = "data/excel/ui/chat/ChatData.xlsx";
}
