@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_RF @SHOP
Feature: PTY Shop Package & RCA - Refund,No refund Free Slot

  Background:
    Given I login my Account with PTY Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: Still 6 Free AAs, Refund free slot for refused Ad To Chotot
    Given I post "5" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "1"
    When I post a PTY House Shop Rent Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "1"
    Then My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 free AAs, No refund free slot for refused Ad To Chotot
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I post a PTY House Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
#    Then My Cart LF should have "0" order after 20 minutes
    Then My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: No refund free slot for refused Shop Ad
    Given I post "1" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    When I post a PTY House Shop Ad and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard




