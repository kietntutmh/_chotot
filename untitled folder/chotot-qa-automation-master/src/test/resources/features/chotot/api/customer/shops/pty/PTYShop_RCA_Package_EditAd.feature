@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_EA @SHOP
Feature: PTY Shop Package & RCA - Edit Shop Ad

  Background:
    Given I login my Account with PTY Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Count 1 free ad when edit request is accepted
    Given I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "is reviewing" my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: Still 6 Free AAs, User can edit ad free
    Given I post "5" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "1"
    And I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "is reviewing" my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, User can't edit ad free
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    And I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "is reviewing" my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "1" order
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Refund 1 free ad when Edit request is refused
    Given I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my PTY Shop should be "6"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, Not refund 1 free ad when Edit request is refused
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    When I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "refused" my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "1" order
    #Cart is automatically erased after 20m
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard
    Then My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard
    Then My Ad shouldn't be on Tab TIN CHUYÊN TRANG of Shop Dashboard

#Insert AdToChotot is refused, edit to AdToShop

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Not Count 1 free ad when edit AdToChotot to AdToShop
    Given I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad and Chotot "accepted" my Ad
    Then The Remaining free ad of my PTY Shop should be "6"
    Then My Cart LF should have "0" order


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, Not Refund 1 free ad when edit AdToChotot to AdToShop is refused
    Given I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    When I edit my PTY "Room" Rent Shop Ad and Chotot "refused" my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    Then My Cart LF should have "0" order


#Insert AdToShop is refused, edit to AdToChotot

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Not Count 1 free ad when edit AdToChotot to AdToShop is accepted
    Given I post a PTY Room Shop Rent Ad and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad To Chotot and Chotot "accepted" my Ad
    Then The Remaining free ad of my PTY Shop should be "6"
    Then My Cart LF should have "0" order


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, Refund 1 free ad when edit AdToChotot to AdToShop is refused
    When I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad and Chotot "refused" my Ad
    Then The Remaining free ad of my PTY Shop should be "6"
    Then My Cart LF should have "0" order


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, No Refund 1 free ad when edit AdToChotot to AdToShop is refused
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    When I post a PTY Room Shop Rent Ad To Chotot and Chotot refused my Ad
    When I edit my PTY "Room" Rent Shop Ad and Chotot "refused" my Ad
    Then The Remaining free ad of my PTY Shop should be "0"









