@SERVICE_SHOPRCA_VEH
@CUSTOMER
@TAG_VUHOANG
@LF_VEH_RCA_PACKAGE_DA @SHOP
Feature: VEH Shop Package & RCA - Delete Shop Ad

  Background:
    Given I login my Account with VEH Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 8 free AAs, Not refund Free slot when Ad is deleted
    Given I post "8" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    When I post a VEH Truck Shop Ad To Chotot and Chotot accepted my Ad
    When I delete my Shop Ad out of Chợ Tốt Listing
    Then The Remaining free ad of my VEH Shop should be "0"

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 8 free AAs, Not refund Free slot when Ad is deleted
    Given I post a VEH Bicycles Shop Ad To Chotot and Chotot accepted my Ad
    When I delete my Shop Ad out of Chợ Tốt Listing
    Then The Remaining free ad of my VEH Shop should be "7"



