@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_IP @SHOP
Feature: PTY Shop Package & RCA - Impacts of Pro, Private Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Pro ad which is posted before creating a new Shop doesn't count free shop ad
    Given I login my Account LF
    Given I post a PTY House Pro Ad and Chotot accepted my Ad
    When I create PTY Shop with my current Account LF
    Then The Remaining free ad of my PTY Shop should be "6"









