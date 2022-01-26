@SERVICE_SHOPRCA_VEH
@CUSTOMER
@TAG_VUHOANG
@LF_VEH_RCA_PACKAGE_IA @SHOP
Feature: VEH Shop Package & RCA - Edit Shop Ad

  Background:
    Given I login my Account with VEH Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: User can post 30 free Ads monthly
    Given I post "8" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my VEH Shop should be "0"
    When I post a VEH Bicycles Shop Ad To Chotot and Chotot is reviewing my Ad
    Then The Remaining free ad of my VEH Shop should be "0"
#    Then My Cart LF should have "1" order

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: User can publish 30 free Ads monthly
    Given I post "7" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    When I post a VEH Car Shop Ad and Chotot accepted my Ad
    Then The Remaining free ad of my VEH Shop should be "1"
    And I publish my Shop Ad To Chotot as Free
    Then The Remaining free ad of my VEH Shop should be "0"
    When I post a VEH Car Shop Ad To Chotot and Chotot is reviewing my Ad
    Then My Cart LF should have "1" order
    When I post a VEH Car Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my VEH Shop should be "0"



