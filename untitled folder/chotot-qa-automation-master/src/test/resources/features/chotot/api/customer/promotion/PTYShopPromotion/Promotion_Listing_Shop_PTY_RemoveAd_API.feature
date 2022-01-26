#@SERVICE_SHOPS
#@TELEGRAM_ME
#@TAG_VUHOANG_SONNGUYEN_THAM
#@PROMOTION_REMOVE
Feature: Shop PTY Package Promotion - Hide/Unhide, Remove/Republish Ad

  Background:
    Given I am back to Chợ Tốt Website to get Shop Promotion

  #--------------- REMOVE when available slots ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Paid Ad_To_Chotot BEFORE Promotion is removed then republished as FREE
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Chotot as Paid and Pay for it
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    And My Free Slot should be still available

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Paid Shop_To_Chotot BEFORE Promotion is removed then republished as FREE
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    And My Free Slot should be still available


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Paid Ad_To_Chotot AFTER Promotion is removed then republished as FREE
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    And My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Paid Shop_To_Chotot AFTER Promotion is removed then republished as FREE
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    And My Free Slot should be still available

    #--------------- REMOVE when over slots & Ad BEFORE Promotion ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove BEFORE over free, Paid Ad_To_Chotot BEFORE Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Chotot as Paid and Pay for it
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I remove all of my Ad out of Chợ Tốt
    When I post "1" Shop House Ad to Chotot as Free
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove AFTER over free, Paid Ad_To_Chotot BEFORE Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Chotot as Paid and Pay for it
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove BEFORE over free, Paid Shop_To_Chotot BEFORE Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I remove all of my Ad out of Chợ Tốt
    And I post "1" Shop House Ad to Chotot as Free
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove AFTER over free, Paid Shop_To_Chotot BEFORE Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

    #--------------- REMOVE when over slots & Ad AFTER Promotion ---------------

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove BEFORE over free, Paid Ad_To_Chotot AFTER Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    And I post "1" Shop House Ad to Chotot as Free
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove AFTER over free, Paid Ad_To_Chotot AFTER Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove BEFORE over free, Paid Shop_To_Chotot AFTER Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    When I remove all of my Ad out of Chợ Tốt
    And I post "1" Shop House Ad to Chotot as Free
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Remove AFTER over free, Paid Shop_To_Chotot AFTER Promotion is removed then republished as FREE when free slot is over
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    And I publish Ad to Chotot as Paid
    And I post "1" Shop House Ad to Chotot as Free
    When I remove all of my Ad out of Chợ Tốt
    Then I should republish my removed Ad To Chotot again Free
    Then My Free Slot should be unavailable




   #--------------- HIDE / UNHIDE ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad_To_Shop BEFORE Promotion. Unhiding Ad doesn't refund Free Slot
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Shop Dashboard
    When I hide my Ad on Shop Dashboard
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Chotot as Free
    When I unhide my Ad
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad_To_Shop AFTER Promotion. Unhiding Ad doesn't refund Free Slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    When I hide my Ad on Shop Dashboard
    When I post "1" Shop House Ad to Chotot as Free
    When I unhide my Ad
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad_To_Chotot BEFORE Promotion. Unhiding Ad doesn't refund Free Slot
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "1" Shop House Ad to Chotot as Paid and Pay for it
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I hide my Ad on Chợ Tốt
    When I post "1" Shop House Ad to Chotot as Free
    When I unhide my Ad
    Then My Free Slot should be unavailable

  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: Ad_To_Chotot AFTER Promotion. Unhiding Ad doesn't refund Free Slot
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Chotot as Free
    When I hide my Ad on Chợ Tốt
    When I unhide my Ad
    Then My Free Slot should be unavailable

  #--------------- OUTSIDE THE SCOPE ---------------
  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: VEH Category is still paid when User get PTY Shop Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then I shouldn't be able to post Free Motorbike Ad


  @AUTHOR_VUHOANG_ME_API   @DONE
  Scenario: ELT Category is still paid when User get PTY Shop Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "2" Free Listing for my Shop PTY
    And I post "1" Shop House Ad to Chotot as Free
    Then I shouldn't be able to post Free Phone Ad

  # ---------------------------------------------------------------------------

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Available Free-ad-slot, Shop_tot_chotot which is on ShopDashboard before I get Promotion is published as paid
    Given I login my account to get Promotion for Shop PTY Ad
    When I post "2" Shop House Ad to Shop Dashboard
    When Chotot promotes "1" Free Listing for my Shop PTY
    #API move to chotot free (with PAID AD) by pass --> BUG
#    Then I should be able to publish to Chotot "1" Free Shop House Ad
    Then I should publish Ad to Chotot as Paid

  @AUTHOR_VUHOANG_ME_API    @DONE
  Scenario: Available Free-ad-slot, Ad_to_chotot is free after I get Promotion
    Given I login my account to get Promotion for Shop PTY Ad
    When Chotot promotes "1" Free Listing for my Shop PTY
    When I post "1" Shop House Ad to Shop Dashboard
    Then I should publish Ad to Chotot as Paid
    Then I should be able to post "1" Free Shop House Ad to Chotot
