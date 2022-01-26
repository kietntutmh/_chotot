#@SERVICE_SHOPS
#@TELEGRAM_ME
#@TAG_VUHOANG_SONNGUYEN_THAM
#@PROMOTION_REJECT
Feature: Shop PTY Package Promotion - Reject & Edit Ad

  Background:
    Given I am back to Chợ Tốt Website to get Shop Promotion

  @AUTHOR_VUHOANG_ME_API  @DONE  @SMOKE
    Scenario: Smoke Test
    Given I login my account to get Promotion for Shop PTY Ad
    Then Chotot promotes "2" Free Listing for my Shop PTY
#    Then My Free Slot should be still available
#    Then SMOKE Post a Shop House Ad_To_Chotot
#    Then My Free Slot should be still available
    Then SMOKE Post and publish a Shop House Ad_To_Shop as free
#    Then SMOKE Check Ad_To_Shop is published
#    Then SMOKE Post and publish a Shop House Ad_To_Shop as paid
#    Then SMOKE Check Ad_To_Shop is published
#    Then SMOKE COMMON Check CATEGORY "", AD TYPE "" of User Profiler equals ""


  #--------------- Ad is rejected BEFORE Promotion ---------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected BEFORE Promotion. Edit is FREE & doesn't count free slot
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid
    Then My Free Slot should be still available

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected BEFORE Promotion. Edit to AdToChotot is FREE & counts free slot
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should edit my Shop House Ad_To_Chotot as Free Ad
    Then My Free Slot should be unavailable
    And I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Edit is FREE & counts free slot
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should edit my Shop House Ad_To_Chotot as Free Ad
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Edit to AdToShop is FREE & doesn't count free slot
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid
    Then My Free Slot should be still available

  #--------------- Ad is rejected AFTER Promotion ---------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected AFTER Promotion. Edit is FREE & doesn't count free slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid
    Then My Free Slot should be still available

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected AFTER Promotion. Edit to AdToChotot is FREE & counts free slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    Then I should edit my Shop House Ad_To_Chotot as Free Ad
    Then My Free Slot should be unavailable
    And I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Edit is FREE & counts free slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    Then I should edit my Shop House Ad_To_Chotot as Free Ad
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Edit to AdToShop is FREE & doesn't count free slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid
    Then My Free Slot should be still available

  #--------------- OutOfFree, Ad is rejected BEFORE Promotion ---------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected BEFORE Promotion. Edit is FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected BEFORE Promotion. Edit to AdToChotot is PAID when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Chotot as Paid Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Edit is PAID when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Chotot as Paid Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Edit to AdToShop is FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid

  #--------------- OutOfFree, Ad is rejected AFTER Promotion ---------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected AFTER Promotion. Edit is FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToShop is rejected AFTER Promotion. Edit to AdToChotot is PAID when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a Shop House Ad_To_Shop but Chotot rejects my Ad
    When I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Chotot as Paid Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Edit is PAID when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    When I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Chotot as Paid Ad

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Edit to AdToShop is FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    Then I should edit my Shop House Ad_To_Shop as Free Ad
    And I should publish Ad to Chotot as Paid


  #--------------- Editing Ad is refused ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Refund free slot when Editing Ad is refused
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I edit my rejected Ad but Chotot refused my request
    Then My Free Slot should be still available
    Then I should see my Ad displays on Shop Dashboard

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Refund free slot when Editing Ad is refused
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    And I edit my rejected Ad but Chotot refused my request
    Then My Free Slot should be still available
    Then I should see my Ad displays on Shop Dashboard

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: AdToChotot is rejected BEFORE Promotion. Refund free slot when Editing Ad is refused when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    And I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    And I edit my rejected Ad but Chotot refused my request
    Then My Free Slot should be unavailable
    Then I should see my Ad displays on Shop Dashboard

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: AdToChotot is rejected AFTER Promotion. Refund free slot when Editing Ad is refused when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    When I post "1" Shop House Ad to Chotot as Free
    Then My Free Slot should be unavailable
    And I edit my rejected Ad but Chotot refused my request
    Then My Free Slot should be unavailable
    Then I should see my Ad displays on Shop Dashboard

  #--------------- Editing Ad is reviewing ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Doesn't refund free slot when Editing request of a Free Ad is reviewing
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    And I edit my rejected Ad and Chotot is reviewing my request
    Then My Free Slot should be unavailable
    Then I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: BUG, Edit is rejected, Ad is still on Shop Dashbaoard
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad
    And I edit my rejected Ad but Chotot refused my request
    When I should see my Ad displays on Shop Dashboard


  #--------------- Promotion Affect: STARTED_DATE, END_DATED, STATUS ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Promotion is not started but active, Post & Publish Ad are paid
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing Ad for House Ad when the Promotion is NOT STARTED but ACTIVE
    Then My Free Slot should be unavailable
    Then I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Promotion is started but inactive, Post & Publish Ad are paid
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing Ad for House Ad when the Promotion is NOT STARTED but ACTIVE
    Then My Free Slot should be unavailable
    Then I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Promotion expires, Ad_To_Chotot is paid
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing Ad for House Ad but the Promotion is EXPIRED
    Then I shouldn't be able to post Free Shop House Ad

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Promotion expires, Shop_to_Chotot is paid with Ad_Before_Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    When Chotot promotes "2" Free Listing Ad for House Ad but the Promotion is EXPIRED
    Then I should publish Ad to Chotot as Paid
    Then I shouldn't be able to post Free Shop House Ad To Chotot

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Promotion expires, Shop_to_Chotot is paid with Ad_After_Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing Ad for House Ad but the Promotion is EXPIRED
    When I post "1" Shop House Ad to Shop Dashboard
    Then I should publish Ad to Chotot as Paid
    Then I shouldn't be able to post Free Shop House Ad To Chotot

