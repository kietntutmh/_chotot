@SERVICE_SHOPRCA_PTY
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN_THUAN
@LF_PTY_RCA_PACKAGE_IA @SHOP
Feature: PTY Shop Package & RCA - Insert Shop Ad

  Background:
    Given I login my Account with PTY Shop LF

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
  Scenario: User can post only 6 free Ads
    Given I post "4" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    And I post "1" PTY Apartment Shop Ad To Chotot and Chotot accepted my Ad
    And I post "1" PTY Room Shop Rent Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I post "1" PTY House Shop Ad To Chotot and Chotot is reviewing my Ad
    Then My Cart LF should have "1" order
    When I pay for my New Ad
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 Free AAs, User can pusblish Ads to Chotot free
    Given I post "5" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "1"
    When I post a PTY House Shop Rent Ad and Chotot accepted my Ad
    And I publish my Shop Ad To Chotot as Free
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN CHỢ TỐT of Shop Dashboard
    Then My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 Free AAs, User can't pusblish Ads to Chotot free
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I post a PTY House Shop Rent Ad and Chotot accepted my Ad
    And I publish my Shop Ad To Chotot as Paid
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "1" order
    Then My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard
    Then My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard


#Insert Ad to Shop: Free
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 free AAs, Ad to Shop doesn't count free slot
    Given I post a PTY House Shop Rent Ad and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "6"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 free AAs, Not refund free slot when Ad To Shop is refused
    Given I post "1" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    When I post a PTY House Shop Rent Ad and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "5"


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 free AAs, Ad to Shop is always free
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    When I post a PTY House Shop Rent Ad and Chotot is reviewing my Ad
    Then My Cart LF should have "0" order
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Still 6 free AAs, Refund free slot when Inserted Ad is refused
    Given I post a PTY House Shop Rent Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "6"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 free AAs, Refund free slot when Inserted Ad is refused
    Given I post "6" PTY House Shop Ad To Chotot and Chotot accepted my Ad
    Given I post a PTY House Shop Rent Ad To Chotot and Chotot refused my Ad
    Then The Remaining free ad of my PTY Shop should be "0"
    Then My Cart LF should have "1" order
    Then My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Over 6 free AAs, Count free slot when Inserted Ad is reviewing
    Given I post "1" PTY House Shop Ad To Chotot and Chotot is reviewing my Ad
    Then The Remaining free ad of my PTY Shop should be "5"
    Then My Cart LF should have "0" order
    Then My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard


