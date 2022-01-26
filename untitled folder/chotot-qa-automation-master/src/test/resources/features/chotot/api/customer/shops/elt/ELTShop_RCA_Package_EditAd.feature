@SERVICE_SHOPRCA_ELT
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@LF_ELT_RCA_PACKAGE_EA @SHOP
Feature: PTY Shop Package & RCA - Insert Shop Ad

  Background:
    Given I login my Account with ELT Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: Over 30 free AA, Editing refused ad doesn't refund free slot when the request is refused
    Given I post "30" ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    When I post a ELT Phone Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my ELT Shop should be "0"
    When I edit my ELT "Phone" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my ELT Shop should be "0"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 30 free AA, Editing refused ad refunds free slot when the request is refused
    Given I post "1" ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my ELT Shop should be "29"
    When I post a ELT Phone Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my ELT Shop should be "29"
    When I edit my ELT "Phone" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my ELT Shop should be "29"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 30 free AA, Editing refused ad to adToChotot doesn't refund free slot when the request is refused
    Given I post "30" ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    When I post a ELT Phone Shop Ad and Chotot refused my Ad
    Then The Remaining free ad of my ELT Shop should be "0"
    When I edit my ELT "Phone" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my ELT Shop should be "0"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 30 free AA, Editing refused ad to adToChotot refunds free slot when the request is refused
    Given I post "1" ELT Phone Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my ELT Shop should be "29"
    When I post a ELT Phone Shop Ad and Chotot refused my Ad
    Then The Remaining free ad of my ELT Shop should be "29"
    When I edit my ELT "Phone" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my ELT Shop should be "29"


