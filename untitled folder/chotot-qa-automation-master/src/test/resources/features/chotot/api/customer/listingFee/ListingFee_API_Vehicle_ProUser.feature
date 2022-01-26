@SERVICE_USER_ADS
#@LISTING_FEE_PRO
  #VUHOANG_MAINTAIN
@CUSTOMER
@TAG_VUHOANG
Feature: Listing Fee API - Vehicle

  Background:
    Given I register a new Chotot User
    And I deposit Dong Tot via Momo

  @AUTHOR_VUHOANG_ME_API  @SMOKE
  Scenario: SMOKE TEST VEH, First Pro AD is charge
    Given I post a new Motorbike Pro Ad using api successfully
    And My new Ad should be free

  #----------- Obligated Pro User -----------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Obligated Pro User, First Obligate Pro Motorbike Ad is charge
    Given I post "3" new Motorbike Ad as a Private ad API and be accepted
    When I post a new Car Pro Ad using api successfully
    Then I should see my New Ad displayed on Listing Fee Tab CẦN THANH TOÁN
    And My new Ad should be charge
    And My new Ad should be a Pro ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Obligated Pro User, Obligated Pro Buying Ad is free
    Given I post "3" new Motorbike Ad as a Private ad API and be accepted
    When I post a new Car Ad as a Pro ad API for buying
    Then I should see my New Ad displayed on Listing Fee Tab KHÁC
    And I should see my New Ad displayed on Listing Fee filter ĐỢI DUYỆT
    And My new Ad should be free

 #----------- Refund -----------
  @AUTHOR_VUHOANG_ME_API     @DONE
  Scenario: Pro User, Refund listing fee after ad rejected
    Given I post a new Motorbike Ad as a Pro ad and pay for Listing Fee But ad is rejected and DT is refunded
    Then I should see my New Ad displayed on Listing Fee Tab BỊ TỪ CHỐI
    And I should see the POS refund in my Order History
