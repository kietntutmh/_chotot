@SERVICE_SHOPRCA_ELT
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@LF_ELT_RCA_PACKAGE_HA @SHOP
Feature: ELT Shop Package & RCA - Hide Shop Ad

  Background:
    Given I login my Account with ELT Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 30 free AAs, Not refund Free slot when Ad is hiden
    Given I post "30" ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    When I post a ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    When I hide my Shop Ad from Chợ Tốt Listing
    Then The Remaining free ad of my ELT Shop should be "0"

  @AUTHOR_VUHOANG_ME_API  @DONEtu
  Scenario: Still 30 free AAs, Not refund Free slot when Ad is hiden
    Given I post a ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    When I hide my Shop Ad from Chợ Tốt Listing
    Then The Remaining free ad of my ELT Shop should be "29"



