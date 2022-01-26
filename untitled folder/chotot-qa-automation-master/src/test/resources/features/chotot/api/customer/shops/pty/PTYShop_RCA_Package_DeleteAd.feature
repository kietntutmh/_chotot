@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_DA @SHOP
Feature: PTY Shop Package & RCA - Delete Shop Ad

  Background:
    Given I login my Account with PTY Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Not Refund free ad when deleted
    Given I post "1" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    When I delete my Shop Ad out of Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "5"
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    And My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, Not Refund free ad when deleted
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I delete my Shop Ad out of Chợ Tốt Listing
    Then The Remaining free ad of my PTY Shop should be "0"
    And My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard
    And My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard








