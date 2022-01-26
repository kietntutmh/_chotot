@SERVICE_SHOPRCA_NATIONALWIDE
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_TUAN_THUAN
@LF_PTY_NW_EA @SHOP
Feature: Shop RCA National Wide - Edit Ad

  Background:
    Given I login my Account LF

  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: Old charge - TPHCM, Edit 2nd refused Ad is charge
    Given I post a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Hồ Chí Minh"
    Then I have total "1" ads of category "House"
    When I post a PTY "House" Ad and Chotot "refused after I pay" my Ad - region "Hồ Chí Minh"
    Then I have total "2" ads of category "House"
    #Need to maintain: remove payment status & state out of Check private dashboard
    Then My Ad should be on Tab BỊ TỪ CHỐI - SAU KHI THANH TOÁN
    And I edit a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Hồ Chí Minh"
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: Old Charge - Đông Nam Bộ - Bình Dương, Edit 2nd refused Ad is charge
    Given I post a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Bình Dương"
    Then I have total "1" ads of category "Land"
    When I post a PTY "Land" Ad and Chotot "refused after I pay" my Ad - region "Bình Dương"
    Then I have total "2" ads of category "Land"
    Then My Ad should be on Tab BỊ TỪ CHỐI - SAU KHI THANH TOÁN
    And I edit a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Bình Dương"
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: New Charge Hà Nội, Edit 2nd refused Ad is charge
    Given I post a PTY "Apartment" Ad and Chotot "is reviewing" my Ad - region "Hà Nội"
    Then I have total "1" ads of category "Apartment"
    When I post a PTY "Apartment" Ad and Chotot "refused after I pay" my Ad - region "Hà Nội"
    Then I have total "2" ads of category "Apartment"
    Then My Ad should be on Tab BỊ TỪ CHỐI - SAU KHI THANH TOÁN
    And I edit a PTY "Apartment" Ad and Chotot "is reviewing" my Ad - region "Hà Nội"
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: New Free Tây Bắc Bộ - Lai Châu, 2nd refused Ad is free
    Given I post a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Lai Châu"
    Then I have total "1" ads of category "House"
    When I post a PTY "House" Ad and Chotot "refused after I pay" my Ad - region "Lai Châu"
    Then I have total "2" ads of category "House"
    And I edit a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Lai Châu"
    And My Ad should be on Tab KHÁC - ĐỢI DUYỆT
    Then My Cart LF should have "0" order

