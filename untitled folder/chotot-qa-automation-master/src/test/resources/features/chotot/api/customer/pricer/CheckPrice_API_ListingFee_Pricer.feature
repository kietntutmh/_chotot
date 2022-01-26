@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG_HUETAN_SON
#@PRICER_LISTINGFEE_PRICER
#@PRICER
Feature: ListingFee /pricer Pricer API - Internal & Private Check price VND, DT, Promotion

  Background:
    Given I download data from Google Sheet "Listing Fee Price"
    And I login my account to check price

  #---------------- PRIVATE ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Internal, Cates applied for All Regions
    Then Listing Fee Pricer Internal PTY Sell Ad Private Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Private, Cates applied for All Regions
    Then Listing Fee Pricer Private PTY Sell Ad Private Cate "1010, 1020, 1040" which are applied for "Hồ Chí Minh, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct

  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Rent Internal, Region 13000
    Then Listing Fee Pricer Internal PTY For Rent Ad which are applied for only region 13000 should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Rent Private, Region 13000
    Then Listing Fee Pricer Private PTY For Rent Ad which are applied for only region 13000 should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee VEH Internal, Cates applied for All Regions
    Then Listing Fee Pricer Internal VEH Sell Ad Private Cate "2010, 2020, 2050" which are applied for "All regions" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee VEH Private, Cates applied for All Regions
    Then Listing Fee Pricer Private VEH Sell Ad Private Cate "2010, 2020, 2050" which are applied for "All regions" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee ELT Internal, Cates applied for All Regions
    Then Listing Fee Pricer Internal ELT Sell Ad Private Cate "5030, 5010, 5020" which are applied for "All regions" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee ELT Private, Cates applied for All Regions
    Then Listing Fee Pricer Private ELT Sell Ad Private Cate "5030, 5010, 5020" which are applied for "All regions" should be correct


    #---------------- SHOP ----------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Shop Private, Cates applied for All Regions
    Then Listing Fee Pricer PTY Sell Ad Private Shop Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Shop Internal, Cates applied for All Regions
    Then Listing Fee Pricer PTY Sell Ad Internal Shop Dashboard To Chotot Cate "1010, 1020, 1040" which are applied for "HCM, Đồng Nai, Bà Rịa - VT, Tây Ninh, Bình Phước, Bình Dương" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Rent Shop Private, Region 13000
    Then Listing Fee PTY For Rent Ad Private Shop Dashboard To Chotot which are applied for only region 13000 should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee PTY Rent Shop Internal, Region 13000
    Then Listing Fee PTY For Rent Ad Internal Shop Dashboard To Chotot which are applied for only region 13000 should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee VEH Shop Private, Cates applied for All Regions
    Then Listing Fee Pricer VEH Sell Ad Private Shop Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All regions" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee VEH Shop Internal, Cates applied for All Regions
    Then Listing Fee Pricer VEH Sell Ad Internal Shop Dashboard To Chotot Cate "2010, 2020, 2050" which are applied for "All regions" should be correct


  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee ELT Shop Private, Cates applied for All Regions
    Then Listing Fee Pricer ELT Sell Ad Private Shop Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All regions" should be correct
  @AUTHOR_VUHOANG_ME_API
  Scenario: Listing Fee ELT Shop Internal, Cates applied for All Regions
    Then Listing Fee Pricer ELT Sell Ad Internal Shop Dashboard To Chotot Cate "5030, 5010, 5020" which are applied for "All regions" should be correct




#------- not run ---------
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee PTY Internal, All Cates applied for Region 13000
#    Then Listing Fee Pricer Internal PTY Sell Ad Private All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee PTY Private, All Cates applied for Region 13000
#    Then Listing Fee Pricer Private PTY Sell Ad Private All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH Shop Private, All Cates applied for Region 13000
#    Then Listing Fee Pricer VEH Sell Ad Private Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH Shop Internal, All Cates applied for Region 13000
#    Then Listing Fee Pricer VEH Sell Ad Internal Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT Shop Private, All Cates applied for Region 13000
#    Then Listing Fee Pricer ELT Sell Ad Private Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT Shop Internal, All Cates applied for Region 13000
#    Then Listing Fee Pricer ELT Sell Ad Internal Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH Internal, All Cates applied for Region 13000
#    Then Listing Fee Pricer Internal VEH Sell Ad Private All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee VEH Private, All Cates applied for Region 13000
#    Then Listing Fee Pricer Private VEH Sell Ad Private All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT Internal, All Cates applied for Region 13000
#    Then Listing Fee Pricer Internal ELT Sell Ad Private All Cates which are applied for only region 13000 should be correct
#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Listing Fee ELT Private, All Cates applied for Region 13000
#    Then Listing Fee Pricer Private ELT Sell Ad Private All Cates which are applied for only region 13000 should be correct