@SERVICE_USER_ADS
#@LISTING_FEE_PRIVATE
  #VUHOANG_MAINTAIN
@CUSTOMER
@TAG_VUHOANG_HUE_SONNGUYEN_THAM
Feature: Listing Fee API - Vehicle

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST VEH, Second Private AD is charge
    Given I post a new Motorbike Ad using api successfully
    And My new Ad should be free

  #----------- Selling : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Private User, First Ad is free
    Given I post a new Motorbike Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second Motorbike Ad is charged
    Given I post "2" new Motorbike Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second Car Ad is charged
    Given I post a new Motorbike Ad using api successfully
    When I post a new Car Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Private User, Second Truck Ad is charged
    Given I post a new Motorbike Ad using api successfully
    When I post a new Truck Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second Ad is free when First Ad is rejected
    Given I post a new Motorbike Ad as a Private ad API and be rejected
    When I post a new Motorbike Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Buying : Insert Ad -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Buy Ad is free
    Given I post a new Motorbike Ad as a private ad API for buying
    When I post a new Truck Ad as a private ad API for buying
    And I post a new Car Ad as a private ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

  #----------- Paying -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Second Ad displays on ĐỢI DUYỆT after paid
    Given I post "2" new Motorbike Ad using api successfully
    When I pay for my New Ad
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT



  #----------- Selling : Edit Ad &n rejected ad-----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Private User, Ad displays on BỊ TỪ CHỐI after rejected
    Given I post a new Motorbike Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI

  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: Private User, Edit Ad, Ad displays on BỊ TỪ CHỐI after rejected
    Given I post a new Motorbike Ad as a Private ad API and be rejected
    When I edit a new Motorbike Ad as a Private ad API and be rejected
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI

  #----------- Refund -----------
  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: Private User, Refund listing fee after ad rejected
    Given I post a new Motorbike Ad using api successfully
    When I post a new Motorbike Ad and pay for Listing Fee But ad is rejected and DT is refunded
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI
    And I should see the POS refund in my Order History

