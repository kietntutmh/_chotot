package projects.configuaration;

import java.util.Arrays;
import java.util.List;

public class CategoryConfig {
    // List category
    public static final String CATEID_PTY = "1000";
    public static final String CATEID_VEH = "2000";
    public static final String CATEID_ELT = "5000";
    public static final String CATEID_PET = "12000";
    public static final String CATEID_FOOD = "7000";
    public static final String CATEID_REFRIGERATION = "14000";
    public static final String CATEID_HOUSEHOLD = "9000";

    //CateID: 1010	Căn hộ/Chung cư
    public static final String CATEID_PTY_APARTMENT = "1010";
    //CateID: 1020	Nhà ở
    public static final String CATEID_PTY_HOUSE = "1020";
    //CateID: 1040	Đất
    public static final String CATEID_PTY_LAND = "1040";
    //CateID: 1030	Văn phòng, Mặt bằng kinh doanh
    public static final String CATEID_PTY_OFFICE = "1030";
    //CateID: 1050	Phòng trọ
    public static final String CATEID_PTY_ROOMFORRENT = "1050";
    public static final String CATEID_PTY_ROOM = "1050";


    //CateID: 2010	Ô tô
    public static final String CATEID_VEH_CAR = "2010";
    //CateID: 2020	Xe máy
    public static final String CATEID_VEH_MOTORBIKE = "2020";
    //CateID: 2030	Phụ tùng xe
    public static final String CATEID_VEH_VEHICLE_PARTS = "2030";
    //CateID: 2050	Xe tải, xe ben
    public static final String CATEID_VEH_TRUCK = "2050";
    //CateID: 2060	Xe đạp
    public static final String CATEID_VEH_BICYCLES = "2060";
    //CateID: 2080	Phương tiện khác
    public static final String CATEID_VEH_OTHER_VEHICLE = "2080";
    //CateID: 2090	Xe điện
    public static final String CATEID_VEH_ELECTRIC_VEHICLE = "2090";


    //CateID: 5010	Điện thoại
    public static final String CATEID_ELT_PHONE = "5010";
    //CateID: 5020	Tivi, Âm thanh
    public static final String CATEID_ELT_TVSOUND = "5020";
    //CateID: 5030	Laptop
    public static final String CATEID_ELT_LAPTOP = "5030";
    //CateID: 5040	Máy tính bảng
    public static final String CATEID_ELT_TABLET = "5040";
    //CateID: 5050	Máy ảnh, Máy quay
    public static final String CATEID_ELT_CAMERA = "5050";
    //CateID: 5060	Phụ kiện (Màn hình, Chuột...)
    public static final String CATEID_ELT_ACCESSORIES = "5060";
    //CateID: 5070	Máy tính để bàn
    public static final String CATEID_ELT_PC = "5070";
    //CateID: 5080	Linh kiện (RAM, Card...)
    public static final String CATEID_ELT_PCCOMPONENT = "5080";
    //CateID: 5090	Thiết bị đeo thông minh
    public static final String CATEID_ELT_WEARABLE = "5090";


    //CateID: 13010	Danh sách việc làm
    public static final String CATEID_JOB_JOB = "13010";
    //CateID: 13020	Danh sáchz người tìm việ
    public static final String CATEID_JOB_LOOKINGJOB = "13020";

    //CateID: 12010	Gà
    public static final String CATEID_PET_CHICKEN = "12010";
    //CateID: 12020	Chó
    public static final String CATEID_PET_DOG = "12020";
    //CateID: 12030	Chim
    public static final String CATEID_PET_BIRD = "12030";
    //CateID: 12050	Mèo
    public static final String CATEID_PET_CAT = "12050";
    //CateID: 12060	Thú cưng khác
    public static final String CATEID_PET_OTHERS = "12060";
    //CateID: 12040	Phụ kiện, Thức ăn, Dịch vụ
    public static final String CATEID_PET_ACCESSORIES = "12040";
    //CateID: 7010	Đồ ăn, thực phẩm và các loại khác
    public static final String CATEID_OTHER_FOOD = "7010";
    //CateID: 9030	Tủ lạnh
    public static final String CATEID_FUN_REFRIGERATOR = "9030";
    //CateID: 9060	Máy lạnh, điều hoà
    public static final String FUN_COOLER = "9060";
    //CateID: 9070	Máy giặt
    public static final String FUN_WASHINGMACHINE = "9070";
    //CateID: 14070	Bàn ghế
    public static final String CATEID_HOUSEHOLD_TABLECHAIR = "14070";
    //CateID: 14080	Tủ, kệ gia đình
    public static final String CATEID_HOUSEHOLD_DRAWERSHELF = "14080";
    //CateID: 14030	Giường, chăn ga gối nệm
    public static final String CATEID_HOUSEHOLD_BED = "14030";
    //CateID: 14010	Bếp, lò, đồ điện nhà bếp
    public static final String CATEID_HOUSEHOLD_KITCHENAPPLIANCE = "14010";
    //CateID: 14020	Dụng cụ nhà bếp
    public static final String CATEID_HOUSEHOLD_KITCHENUTENSILDINNERWARE = "14020";
    //CateID: 14050	Quạt
    public static final String CATEID_HOUSEHOLD_FAN = "14050";
    //CateID: 14060	Đèn
    public static final String CATEID_HOUSEHOLD_LIGHTING = "14060";
    //CateID: 14090	Cây cảnh, đồ trang trí
    public static final String CATEID_HOUSEHOLD_PLANTDECORATION = "14090";
    //CateID: 14040	Thiết bị vệ sinh, nhà tắm
    public static final String CATEID_HOUSEHOLD_BATHWARE = "14040";
    //CateID: 14110	Nội thất, đồ gia dụng khác
    public static final String CATEID_HOUSEHOLD_OTHERITEMS = "14110";
    //CateID: 11010	Mẹ và bé
    public static final String CATEID_MOMANDBABY = "11010";
    //CateID: 3030	Quần áo
    public static final String CATEID_FASHION_CLOTHES = "3030";
    //CateID: 3050	Đồng hồ
    public static final String CATEID_FASHION_WATCH = "3050";
    //CateID: 3060	Giày dép
    public static final String CATEID_FASHION_SHOES = "3060";
    //CateID: 3070	Túi xách
    public static final String CATEID_FASHION_HANDBAG = "3070";
    //CateID: 3080	Nước hoa
    public static final String CATEID_FASHION_PERFUME = "3080";
    //CateID: 3090	Phụ kiện thời trang khác
    public static final String CATEID_FASHION_ACCESSORIES = "3090";
    //CateID: 4040	Nhạc cụ
    public static final String ENT_INSTRUMENT = "4040";
    //CateID: 4070	Sách
    public static final String ENT_BOOK = "4070";
    //CateID: 4020	Đồ thể thao, Dã ngoại
    public static final String ENT_SPORT = "4020";
    //CateID: 4010	Đồ sưu tầm, đồ cổ
    public static final String ENT_COLLECTIBLES = "4010";
    //CateID: 4050	Thiết bị chơi game
    public static final String ENT_GAMING = "4050";
    //CateID: 4060	Sở thích khác
    public static final String ENT_HOBBY = "4060";
    //CateID: 8010	Đồ dùng văn phòng
    public static final String CATEID_REQUISITE_OFFICEEQUIPMENT = "8010";
    //CateID: 8030	Đồ chuyên dụng, Giống nuôi trồng
    public static final String CATEID_REQUISITE_SPECIALIZEDITEM = "8030";
    //CateID: 6020	Dịch vụ
    public static final String CATEID_SERVICE_SERVICE = "6020";
    public static final String CATEID_SERVICE_TRAVEL = "6030";

    public static final String REGION_VUNGTAU_ID = "2010";
    public static final String REGION_BINHDUONG_ID = "2011";
    public static final String REGION_BINHPHUOC_ID = "2012";
    public static final String REGION_DONGNAI_ID = "2013";
    public static final String REGION_TAYNINH_ID = "2014";
    public static final String REGION_HCM_ID = "13000";
    public static final String WARD_HCM_ID = "9220";        //Q1
    public static final String REGION_HANOI_ID = "12000";
    public static final List<String> REGION_PTY_PAID_LIST = Arrays.asList(REGION_HCM_ID, REGION_VUNGTAU_ID, REGION_BINHDUONG_ID, REGION_BINHPHUOC_ID, REGION_DONGNAI_ID, REGION_TAYNINH_ID);

    public static final List<String> CATEID_PTY_ALL =
            Arrays.asList(CATEID_PTY_HOUSE, CATEID_PTY_LAND, CATEID_PTY_APARTMENT, CATEID_PTY_OFFICE, CATEID_PTY_ROOM);
    public static final List<String> CATEID_VEH_ALL =
            Arrays.asList(CATEID_VEH_CAR, CATEID_VEH_MOTORBIKE, CATEID_VEH_TRUCK, CATEID_VEH_BICYCLES, CATEID_VEH_ELECTRIC_VEHICLE, CATEID_VEH_OTHER_VEHICLE, CATEID_VEH_VEHICLE_PARTS);
    public static final List<String> CATEID_ELT_ALL =
            Arrays.asList(CATEID_ELT_PHONE, CATEID_ELT_LAPTOP, CATEID_ELT_TABLET, CATEID_ELT_PC, CATEID_ELT_WEARABLE, CATEID_ELT_CAMERA, CATEID_ELT_PCCOMPONENT, CATEID_ELT_ACCESSORIES, CATEID_ELT_TVSOUND, CATEID_ELT_ACCESSORIES);

    public static final String AD_TYPE_SELL = "s";
    public static final String AD_TYPE_BUY = "k";
    public static final String AD_TYPE_RENT = "u";
    public static final String AD_TYPE_HIRE = "h";
    public static final List<String> AD_TYPE_PTY_ALL = Arrays.asList(AD_TYPE_SELL, AD_TYPE_BUY, AD_TYPE_RENT, AD_TYPE_HIRE);
    public static final List<String> AD_TYPE_VEH_ALL = Arrays.asList(AD_TYPE_SELL, AD_TYPE_BUY);
    public static final List<String> AD_TYPE_ELT_ALL = Arrays.asList(AD_TYPE_SELL, AD_TYPE_BUY);

    public static String getCategoryID(String cateStr){
        switch (cateStr.toUpperCase().trim()){
            case "HOUSE":
                return CATEID_PTY_HOUSE;
            case "APARTMENT":
                return CATEID_PTY_APARTMENT;
            case "LAND":
                return CATEID_PTY_LAND;
            case "OFFICE":
                return CATEID_PTY_OFFICE;
            case "ROOM":
                return CATEID_PTY_ROOM;
            default:
                return null;
        }
    }
}