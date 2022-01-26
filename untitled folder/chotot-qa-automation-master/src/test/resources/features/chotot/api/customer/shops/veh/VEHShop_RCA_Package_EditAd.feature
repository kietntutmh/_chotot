@SERVICE_SHOPRCA_VEH
@CUSTOMER
@TAG_VUHOANG
@LF_VEH_RCA_PACKAGE_EA @SHOP
Feature: PTY Shop Package & RCA - Insert Shop Ad

  Background:
    Given I login my Account with VEH Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: Over 8 free AA, Editing refused ad doesn't refund free slot when the request is refused
    Given I post "8" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    When I post a VEH Truck Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my VEH Shop should be "0"
    When I edit my VEH "Truck" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my VEH Shop should be "0"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 8 free AA, Editing refused ad refunds free slot when the request is refused
    Given I post "1" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my VEH Shop should be "7"
    When I post a VEH Truck Shop Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my VEH Shop should be "7"
    When I edit my VEH "Car" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my VEH Shop should be "7"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 8 free AA, Editing refused ad to adToChotot doesn't refund free slot when the request is refused
    Given I post "8" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    When I post a VEH Car Shop Ad and Chotot refused my Ad
    Then The Remaining free ad of my VEH Shop should be "0"
    When I edit my VEH "Car" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my VEH Shop should be "0"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 8 free AA, Editing refused ad to adToChotot refunds free slot when the request is refused
    Given I post "1" VEH Car Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my VEH Shop should be "7"
    When I post a VEH Car Shop Ad and Chotot refused my Ad
    Then The Remaining free ad of my VEH Shop should be "7"
    When I edit my VEH "Other Vehicle" Sell Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my VEH Shop should be "7"


