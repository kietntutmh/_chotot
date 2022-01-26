@SERVICE_SHOPRCA_VEH
@CUSTOMER
@TAG_CUSTOMER
@SHOP_BENEFIT @SHOP
Feature: Shop Benefit RCA API

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Free Shop Ad, Pro Ad is moved to Shop Dashboard
    Given I login my Account
    And I have 500k Đồng Tốt
    When I post and pay "2" PTY House Pro Ad and Chotot accepted my Ad
    When I create PTY Shop and Chotot approves my Shop
    Then The Remaining free ad of my PTY Shop should be "6"
