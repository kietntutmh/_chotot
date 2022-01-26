@SERVICE_USER_ADS
#@LISTING_FEE_PRO
  #VUHOANG_MAINTAIN
@CUSTOMER
@TAG_VUHOANG_HUE_SONNGUYEN_THAM
Feature: Listing Fee API - TV Sound Pro User

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST TVSOUND, First Pro AD is charge
    Given I post a new TV Sound Ad as a Pro ad API
    And My new Ad should be charge

    #----------- PRO User -----------
  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_5
  Scenario: Pro User, First Pro Ad is charge
    Given I post a new TV Sound Ad as a Pro ad API
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_5
  Scenario: Pro User, Pro Buying Ad is free
    Given I post a new TV Sound Ad as a Pro ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Obligated Pro User -----------
  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_6
  Scenario: Obligated Pro User, First Obligate Pro TV Sound Ad is charge
    Given I post "3" new TV Sound Ads as Private ads API and be accepted
    When I post a new TV Sound Ad as a Pro ad API
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @LISTING_FEE_6  
  Scenario: Obligated Pro User, Obligated Pro Buying Ad is free
    Given I post "3" new TV Sound Ads as Private ads API and be accepted
    And I post a new TV Sound Ad as a Pro ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free