@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_HA  @SHOP
Feature: PTY Shop Package & RCA - Hide Shop Ad

  Background:
    Given I login my Account with PTY Shop LF


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, No Refund free ad when hide
    Given I post "1" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    When I hide my Shop Ad from Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "5"
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    And My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, No Refund free ad when hide
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I hide my Shop Ad from Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "0"
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    And My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Free & No Count slot when unhide a free ad
    Given I post "1" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    When I hide my Shop Ad from Chợ Tốt Listing
    When I unhide my Shop Ad on Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "5"
    And My Cart LF should have "0" order
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    And My Ad should be on Tab TIN CHỢ TỐT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, Free & No Count slot when unhide a free ad
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    And I post a PTY House Shop Rent Ad To Chotot and Chotot accepted my Ad
    And I pay for my New Ad
    When I hide my Shop Ad from Chợ Tốt Listing
    When I unhide my Shop Ad on Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "0"
    And My Cart LF should have "0" order
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    And My Ad should be on Tab TIN CHỢ TỐT of Shop Dashboard







