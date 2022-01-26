@SERVICE_SHOPRCA_NATIONALWIDE
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_TUAN_THUAN
@LF_PTY_NW_IA @SHOP
Feature: Shop RCA National Wide - Insert Ad

  Background:
    Given I login my Account LF

  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: Old charge - TPHCM, 2nd Private Ad is charge
    Given I post a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Hồ Chí Minh"
    When I post a PTY "House" Ad and Chotot "is reviewing" my Ad - region "Hồ Chí Minh"
    Then My Ad should be on Tab CẦN THANH TOÁN
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: Old charge - Bình Dương, 2nd Private Ad is charge
    Given I post a PTY "Apartment" Ad and Chotot "is reviewing" my Ad - region "Bình Dương"
    When I post a PTY "Apartment" Ad and Chotot "is reviewing" my Ad - region "Bình Dương"
    Then My Ad should be on Tab CẦN THANH TOÁN
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: New charge, 2nd Private Ad is charge
    Given I post a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Hà Nội"
    When I post a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Hà Nội"
    Then My Ad should be on Tab CẦN THANH TOÁN
    Then My Cart LF should have "1" order


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: New Free - Đông Bắc Bộ - Bắc Cạn, 2nd Private Ad is free
    Given I post a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Bắc Cạn"
    When I post a PTY "Land" Ad and Chotot "is reviewing" my Ad - region "Bắc Cạn"
    And My Ad should be on Tab KHÁC - ĐỢI DUYỆT
    Then My Cart LF should have "0" order

