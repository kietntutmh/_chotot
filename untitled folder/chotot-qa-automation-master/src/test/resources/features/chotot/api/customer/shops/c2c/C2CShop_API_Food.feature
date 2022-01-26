@SERVICE_SHOP-PROFILE
@C2CSHOPS_API
@CUSTOMER
@SHOPC2C
Feature: New C2C Shop API - FOOD Functionalities

  Background:
    Given I register my new account to create a Shop

  @AUTHOR_VUHOANG_ME_API   @CREATE_TEMP_SHOP
  Scenario: Create Temp Shop, User can create a new Temp Food Shop
    Given I create a new Temp "Food" Shop
    Then My Temp Food Shop should not be active
    And All my Temp "Food" Shop settings should be correct

  @AUTHOR_VUHOANG_ME_API   @UPDATE_SHOP
  Scenario: Update Temp Shop, User can create and update information of a new Temp Food Shop successfully
    Given I create a new Temp "Food" Shop
    And I update all information for my Temp "Food" Shop
    Then All my Temp "Food" Shop settings should be correct after updated

  @AUTHOR_VUHOANG_ME_API   @UPDATE_SHOP
  Scenario: Update Temp Shop, User can create and update information of a new Temp Food Shop many times
    Given I create a new Temp "Food" Shop
    And I update all information for my Temp "Food" Shop
    And I update all information for my Temp "Food" Shop
    And I update all information for my Temp "Food" Shop
    Then All my Temp "Food" Shop settings should be correct after updated

  @AUTHOR_VUHOANG_ME_API   @UPDATE_SHOP
  Scenario: Update Temp Shop, User can not update information When Chotot is reviewing
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    And I activate my New Shop
    Then I cannot update my New "Food" Shop when Chotot is reviewing

  @AUTHOR_VUHOANG_ME_API   @UPDATE_SHOP
  Scenario: Update Temp Shop, User can not update information When Chotot is reviewing after Chotot accepted the shop
    Given I register a New "Food" Shop successfully
    When Chotot approves my New Shop without updating information
    When I update all information for my New "Food" Shop except url, name
    Then I cannot update my New "Food" Shop when Chotot is reviewing

  @AUTHOR_VUHOANG_ME_API   @CREATE_TEMP_SHOP
  Scenario: Create Temp Shop, User can not create more than one new Tem Shop
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    And I create another new "Food" Shop
    Then I should see "1" Temp Shop created

  @AUTHOR_VUHOANG_ME_API   @CREATE_NEW_SHOP
  Scenario: Create New Shop, User cannot pay for a Temp Shop without updating all required information
    Given I create a new Temp "Food" Shop
    Then I cannot pay for my "Food" Shop order when I don't update required information

  @AUTHOR_VUHOANG_ME_API   @CREATE_NEW_SHOP
  Scenario: Create New Shop, User can create a New Shop from a Temp Shop
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    And I activate my New Shop
    Then My New Shop should appear in Chotot Shop Queue
    When Chotot approves my New Shop without updating information
    Then My "Food" Shop should be active with correct information
    And I can visit my "Food" Shop Dashboard
    And Buyers can visit my "Food" Shop

  @AUTHOR_VUHOANG_ME_API   @CREATE_NEW_SHOP
  Scenario: Create New Shop, User can update my Shop information after paid
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    Then I can update all information for my Temp "Food" Shop
    And All my Temp "Food" Shop settings should be correct after updated

  @AUTHOR_VUHOANG_ME_API   @CREATE_NEW_SHOP
  Scenario: Create New Shop, Chotot can update all my Shop information when approving
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    When I activate my New Shop
    When Chotot approves my New Shop with updating all information
    Then My "Food" Shop should be active with correct information

  @AUTHOR_VUHOANG_ME_API   @CREATE_NEW_SHOP
  Scenario Outline: Create New Shop, Chotot can update each of my Shop information when approving
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    When I activate my New Shop
    When Chotot approves my New Shop with updating param "<param>"
    Then My "Food" Shop should be active with correct information
    Examples:
      | param       |
      | url         |
      | name        |
      | description |
      | address     |

  @AUTHOR_VUHOANG_ME_API   @QUEUE
  Scenario: Queue, I can create a New Shop then the Shop exists in Queue
    Given I create a new Temp "Food" Shop
    Then My New Shop should appear in Chotot Shop Queue

  @AUTHOR_VUHOANG_ME_API   @REQUIRED_PARAM
  Scenario Outline: Create New Shop, User cannot pay for my Temp Shop when I missed required information
    Given I create and update my Temp "Food" Shop but missing "<required_param>"
    When I pay for my Temp "Food" Shop
    Then I should not be able to activate my New Shop
    Examples:
      | required_param |
      | avatar         |
      | cover          |
      | name           |
      | address        |
      | phone          |
      | category       |

    #------------------------ Edit Shop ------------------------

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User can edit URL when Chotot allows my request
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a new one
    When Chotot approves my New Shop URL
    Then The URL of my "Food" Shop should be updated correctly

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User can not edit URL when Chotot rejects my request
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a new one
    When Chotot rejects my New Shop URL
    Then The URL of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User can edit my URL only one time
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a new one
    When Chotot approves my New Shop URL
    Then I cannot edit my URL again
    And Chotot CS should see AllowEditURL is "false"

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User can edit my URL one time until the URL is legal
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a bad one
    When Chotot rejects my New Shop URL
    When Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a new one
    When Chotot approves my New Shop URL
    Then The URL of my "Food" Shop should be updated correctly

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User cannot edit the new URL when it's illegal
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing URL after my Shop is accepted
    When I edit my URL to a bad one
    When Chotot rejects my New Shop URL
    Then The URL of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @EDIT_URL
  Scenario: Edit URL, User can not edit URL when Chotot doesn't allow me
    Given I register a New "Food" Shop successfully
    And Chotot denies me editing URL after my Shop is accepted
    Then I cannot edit my URL again
    Then The URL of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can edit Shop Name when Chotot allows my request
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a new one
    When Chotot approves my New Shop Name
    Then The Shop Name of my "Food" Shop should be updated correctly

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can not edit Shop Name when Chotot rejects my request
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a new one
    When Chotot rejects my New Shop Name
    Then The Shop Name of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can edit my Shop Name only one time
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a new one
    When Chotot approves my New Shop Name
    Then I cannot edit my Shop Name again
    And Chotot CS should see AllowEditName is "false"

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can edit my Shop Name one time until the Shop Name is legal
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a bad one
    When Chotot rejects my New Shop Name
    When Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a new one
    When Chotot approves my New Shop Name
    Then The Shop Name of my "Food" Shop should be updated correctly

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can edit my Shop Name one time until the Shop Name is legal
    Given I register a New "Food" Shop successfully
    And Chotot allows me editing Shop Name after my Shop is accepted
    When I edit my Shop Name to a bad one
    When Chotot rejects my New Shop Name
    Then The Shop Name of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @EDIT_NAME
  Scenario: Edit Shop Name, User can not edit Shop Name when Chotot doesn't allow me
    Given I register a New "Food" Shop successfully
    And Chotot denies me editing Shop Name after my Shop is accepted
    Then I cannot edit my Shop Name again
    Then The Shop Name of my "Food" Shop should not be updated to the new one

  @AUTHOR_VUHOANG_ME_API   @REJECT_SHOP
  Scenario: Reject Shop, User can update all information after Chotot rejects my New Shop
    Given I create and update a new Temp "Food" Shop
    When I pay for my Temp "Food" Shop
    And I activate my New Shop
    When Chotot rejects my New Shop
    When I update all information for my Temp "Food" Shop
    When I activate my New Shop
    When Chotot approves my New Shop
    Then My "Food" Shop should be active with correct information

    #------------------------ Block Shop ------------------------

  @AUTHOR_VUHOANG_ME_API   @BLOCK_SHOP
  Scenario: Block Shop, I can close my Shop but I can still navigate to my Shop
    Given I register a New "Food" Shop successfully
    When I close my Shop
    Then My Shop Status is "closed"
    Then I should be able to visit my "Food" Shop Dashboard
    And Buyers should be able to visit my "Food" Shop
    And I should not be able to create another new "Food" Shop


  @AUTHOR_VUHOANG_ME_API   @BLOCK_SHOP
  Scenario: Block Shop, Chotot can block My Shop that is active. And Then I can create another new Shop
    Given I register a New "Food" Shop successfully
    When Chotot blocks my Shop
    Then My Shop is blocked
    Then I should not be able to visit my "Food" Shop Dashboard
    And Buyers should not be able to visit my "Food" Shop
    Then I should not be able to create another new "Food" Shop

    #------------------------ Expire / Extent Shop ------------------------
  @AUTHOR_VUHOANG_ME_API   @EXPIRE_SHOP
  Scenario: Expire Shop, Chotot can expire my Shop when it's out of date
    Given I register a New "Food" Shop successfully
    When Chotot expires my Shop
    Then My Shop Status is "expired"
    Then I should be able to visit my "Food" Shop Dashboard
    And Buyers should be able to visit my "Food" Shop
    Then I should not be able to create another new "Food" Shop

  @AUTHOR_VUHOANG_ME_API   @EXPIRE_SHOP
  Scenario: Extent Shop, Chotot can expire my Shop when it's out of date
    Given I register a New "Food" Shop successfully
    When Chotot expires my Shop
    When I extend my free "Food" Shop with "3" months
    Then My Shop Status is "accepted"
    Then I should be able to visit my "Food" Shop Dashboard
    And Buyers should be able to visit my "Food" Shop

    #------------------------ Insert Ad / Private Dashboard ------------------------

  @AUTHOR_VUHOANG_ME_API   @INSERT_AD
  Scenario: Insert Food Ad, I can insert a new Ad onto the Shop
    Given I register a New "Food" Shop successfully
    When I insert a new Food Ad onto my "Food" Shop
    Then My new Food Ad should display on my Shop

  @AUTHOR_VUHOANG_ME_API   @INSERT_AD
  Scenario: Insert Ad & Blocked Shop, I cann't insert a new Ad onto the Shop when it's blocked. My ad is moved to Private Dashboard
    Given I register a New "Food" Shop successfully
    When Chotot blocks my Shop
    When I insert a new Food Ad onto my "Food" Shop
    Then My new Food Ad should not display on my Shop
    And My new Food Ad should display on Private Dashboard

  @AUTHOR_VUHOANG_ME_API   @INSERT_AD
  Scenario: Insert Ad & Expired Shop, I can't insert New Ad onto my Shop when my Shop is expired. My ad is moved to Private Dashboard
    Given I register a New "Food" Shop successfully
    When Chotot expires my Shop
    When I insert a new Food Ad onto my "Food" Shop
    Then My new Food Ad should not display on my Shop
    And My new Food Ad should display on Private Dashboard

  @AUTHOR_VUHOANG_ME_API   @INSERT_AD
  Scenario: Insert Ad & Extended Shop, I can't insert New Ad onto my Shop when my Shop is expired. My ad is moved to Private Dashboard
    Given I register a New "Food" Shop successfully
    When Chotot expires my Shop
    When I extend my free "Food" Shop with "3" months
    And I insert a new Food Ad onto my "Food" Shop
    Then My new Food Ad should display on my Shop
    And My new Food Ad should not display on Private Dashboard


  #-------------------- Extent Cases: Out of Current Flow Scope but necessary -------------------

  @AUTHOR_VUHOANG_ME_API   @UPDATE_SHOP
  Scenario: EXTENT - Update Temp Shop, User can create and update information of a new Temp Food Shop successfully
    Given I create a new Temp "Food" Shop
    And I update the category of my Temp "Food" Shop
      | PTY Cate ID | 2000 |
    Then My Temp "Food" Shop's Category ID should not be updated

  @AUTHOR_VUHOANG_ME_API   @BLOCK_SHOP
  Scenario: EXTENT - Block Shop, I can't close my Shop 2 times
    Given I register a New "Food" Shop successfully
    When I close my Shop
    Then I should not be able to close my Shop again

  @AUTHOR_VUHOANG_ME_API   @BLOCK_SHOP
  Scenario: EXTENT - Block Shop, Chotot can block my Shop when it's closed
    Given I register a New "Food" Shop successfully
    When I close my Shop
    When Chotot should be able to block my Shop

  @AUTHOR_VUHOANG_ME_API   @BLOCK_SHOP
  Scenario: EXTENT - UnBlock Shop, Chotot can unblock My Shop
    Given I register a New "Food" Shop successfully
    And Chotot blocks my Shop
    When Chotot unblocks my Shop
    Then My Shop is unblocked

  @AUTHOR_VUHOANG_ME_API   @INSERT_AD
  Scenario: Insert Food Ad, I can't insert more than 30 New Food Ads on Listing eventhough I have a Shop
    Given I register a New "Food" Shop successfully
    When I insert "30" new Food Ad on Listing
    Then I cannot insert a new Food Ad on Listing

  #------------------------ Check Email ------------------------
  @AUTHOR_VUHOANG_ME_API   @EXPIRE_SHOP
  Scenario: Extent Shop, User receive an EMAIL to confirm that C2C Shop has been extended successfully
    Given I update my email to be "testchotot001@gmail.com" to receive Extended Shop Confirm Email
    And I register a New "Food" Shop successfully
    When Chotot expires my Shop
    When I extend my free "Food" Shop with "3" months
    Then My Shop Status is "accepted"
    Then I should receive an Email sent to "testchotot001@gmail.com" to confirm that C2C Shop has beed extended successfully


  @AUTHOR_VUHOANG_ME_API   @EXPIRE_SHOP
  Scenario: Extent Shop, User receive an EMAIL to confirm that C2C Shop has been extended successfully
    Given I should receive an Email sent to "testchotot001@gmail.com" to confirm that C2C Shop has beed extended successfully




  #------ Extent case of CP + Not yet finished cases--------
  # 1. Shop isn't updated completely that doesn't exist in Queue
  # 2. Insert 30 Ads
  # 3. Insert Food Ad to Shop
  # 4. Shop creates more than 1 url
  # 5. Shop edit one of Urls -> check status
  # 6. Shop edit URL then edit Name -> check status of Allow edit
  # 7. Shop new bi reject, User dc edit all fields
  # 8. Shop new sau khi dc accepted, thi chi dc edit address, description 1 lan duy nhat
  # 9. Show new sau khi dc accepted, khong dc edit URL, neu muon Edit, chotot phai manual bat Editing URL = on, roi user vao edit URL 1 lan duy nhat, roi ko dc edit gi nua

#  1. Chưa có Shop: insert Ad cá nhân or pro (done)
#  2. Shop bị expired -> ad lên Private Dashboard, shop extend -> Ad tự chuyển lên Shop Dashboard (done)
#  3. Shop is blocked -> ad tự chuyển qua Private Dashboard (done)


#Shop free: Create Temp shop -> Pay -> Screen activate -> activate -> tao order de CP duyet
  #Shop paid: create temp shop -> pay -> screen settings -> CP duyet -> roi moi activate dc

