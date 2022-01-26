@SERVICE_POS
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@POS_BUMP
Feature: BUMP Đẩy tin nhanh - API

  Background:
    Given I register a new Chotot User and deposit "500000" Dong Tot via Momo

#-------------------- PTY --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PTY Apartment
    Given I post a new Apartment Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PTY House category
    When I post a new House Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PTY Land category
    When I post a new Land Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PTY Office category
    When I post a new Office Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PTY Room For Lease category
    When I post a new Room For Lease Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

    #-------------------- VEH --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Not exist NHÃN IN ĐẬM, VEH Car category
    When I post a new Car Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Motorbike category
    When I post a new Motorbike Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Truck category
    When I post a new Truck Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Electric VEH category
    When I post a new Electric Vehicles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Bicycles category
    When I post a new Bicycles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Other Vehicles category
    When I post a new Other Vehicles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, VEH Vehicles Parts category
    When I post a new Vehicles Parts Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

    #-------------------- ELT --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Phone category
    When I post a new Phone Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Tablet category
    When I post a new Tablet Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT TV Sound category
    When I post a new TV Sound Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT TV Sound category
    When I post a new TV Sound Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT PC category
    When I post a new PC Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT PC Component category
    When I post a new PC Component Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Wearable category
    When I post a new Wearable Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Camera category
    When I post a new Camera Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Laptop category
    When I post a new Laptop Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, ELT Accessories category
    When I post a new ELT Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm


    #-------------------- Job --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Job finds people
    When I post a new Job Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, People find job
    When I post a new Looking Job Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

    #-------------------- Pet --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Chicken
    When I post a new Chicken Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Dog
    When I post a new Dog Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Cat
    When I post a new Cat Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Bird
    When I post a new Bird Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Others
    When I post a new Other Pets Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, PET Accessories
    When I post a new Pet Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  #-------------------- Food --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Food
    When I post a new Food Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  #-------------------- Refrigeration --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Refrigeration Fridge
    When I post a new Refrigerator Fridge Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Refrigeration Air conditioner
    When I post a new Air Conditioner Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Refrigeration Washing Machine
    When I post a new Washing Machine Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  #-------------------- Household --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Table
    When I post a new Household Table Chair Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Bed
    When I post a new Household Bed Bedding Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Shelf
    When I post a new Household Drawer Shelf Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Items
    When I post a new Household Items Ad as a private ad
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Kitchen Appliance
    When I post a new Household Kitchen Appliance Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Fan
    When I post a new Household Fan Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Lighting
    When I post a new Household Lighting Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Plant
    When I post a new Household Plant Decoration Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Kitchen Utensil Dinnerware
    When I post a new Kitchen Utensil Dinnerware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Kitchen Utensil Dinnerware
    When I post a new Kitchen Utensil Dinnerware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Bathware
    When I post a new Household Bathware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Household Others
    When I post a new Other Household Items Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm




  #-------------------- Mom and Baby --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Mom and baby
    When I post a new Mom And Baby Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm


      #-------------------- Fashion --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Clothes
    When I post a new Clothes Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Watch
    When I post a new Watch Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Shoes
    When I post a new Shoes Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Perfume
    When I post a new Perfume Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Handbag
    When I post a new Handbag Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Fashion Accessories
    When I post a new Fashion Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm


 #-------------------- Entertainment --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Instrument
    When I post a new Instrument Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Book
    When I post a new Book Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Sport
    When I post a new Sport Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Collectibles
    When I post a new Collectibles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Gaming
    When I post a new Gaming Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Entertainment Hobby
    When I post a new Hobby Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  #-------------------- Office --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Office Requisite
    When I post a new Office Equipment Requisite Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Office Specialized Item Requisite
    When I post a new Specialized Item Requisite Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm


      #-------------------- Service --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Service services
    When I post a new Service Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm

  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Service travel
    When I post a new Travel Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Title displays
    When I add and pay for TITLE label to my ad
    Then I should see my ad being added TITLE In Đậm
    
    #--------------------- Shop ---------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: NHÃN IN ĐẬM, Shop PTY
    Given I login my account with Shop PTY
    When I post a new House Ad Shop then Chotot accepts my Ad
    And I publish my Shop Ad to Chợ Tốt
    When I add and pay for TITLE label to my PTY Shop "House" ad
    Then I should see my ad being added TITLE In Đậm

