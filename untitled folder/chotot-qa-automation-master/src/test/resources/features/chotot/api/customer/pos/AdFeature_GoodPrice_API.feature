@SERVICE_POS
@CUSTOMER
@TAG_VUHOANG_SONNGUYEN
@POS_ADFEATURE_GOODPRICE
Feature: POS Ad Feature GÍA TÓT (GOOD PRICE) - API

  Background:
    Given I register a new Chotot User and deposit "500000" Dong Tot via Momo

#-------------------- PTY --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PTY Apartment
    Given I post a new Apartment Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PTY House category
    When I post a new House Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PTY Land category
    When I post a new Land Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PTY Office category
    When I post a new Office Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PTY Room For Lease category
    When I post a new Room For Lease Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

    #-------------------- VEH --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Not exist GIÁ TỐT Label, VEH Car category
    When I post a new Car Ad and Cho Tot accept my Ad for ad feature
    Then I should not see Ad Feature Price displays

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Motorbike category
    When I post a new Motorbike Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Truck category
    When I post a new Truck Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Electric VEH category
    When I post a new Electric Vehicles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Bicycles category
    When I post a new Bicycles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Other Vehicles category
    When I post a new Other Vehicles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, VEH Vehicles Parts category
    When I post a new Vehicles Parts Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

    #-------------------- ELT --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Phone category
    When I post a new Phone Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Tablet category
    When I post a new Tablet Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT TV Sound category
    When I post a new TV Sound Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT TV Sound category
    When I post a new TV Sound Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT PC category
    When I post a new PC Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT PC Component category
    When I post a new PC Component Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Wearable category
    When I post a new Wearable Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Camera category
    When I post a new Camera Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Laptop category
    When I post a new Laptop Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, ELT Accessories category
    When I post a new ELT Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT


    #-------------------- Job --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Job finds people
    When I post a new Job Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, People find job
    When I post a new Looking Job Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

    #-------------------- Pet --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Chicken
    When I post a new Chicken Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Dog
    When I post a new Dog Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Cat
    When I post a new Cat Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Bird
    When I post a new Bird Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Others
    When I post a new Other Pets Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, PET Accessories
    When I post a new Pet Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  #-------------------- Food --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Food
    When I post a new Food Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  #-------------------- Refrigeration --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Refrigeration Fridge
    When I post a new Refrigerator Fridge Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Refrigeration Air conditioner
    When I post a new Air Conditioner Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Refrigeration Washing Machine
    When I post a new Washing Machine Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  #-------------------- Household --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Table
    When I post a new Household Table Chair Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Bed
    When I post a new Household Bed Bedding Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Shelf
    When I post a new Household Drawer Shelf Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Items
    When I post a new Household Items Ad as a private ad
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Kitchen Appliance
    When I post a new Household Kitchen Appliance Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Fan
    When I post a new Household Fan Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Lighting
    When I post a new Household Lighting Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Plant
    When I post a new Household Plant Decoration Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Kitchen Utensil Dinnerware
    When I post a new Kitchen Utensil Dinnerware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Kitchen Utensil Dinnerware
    When I post a new Kitchen Utensil Dinnerware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Bathware
    When I post a new Household Bathware Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Household Others
    When I post a new Other Household Items Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT




  #-------------------- Mom and Baby --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Mom and baby
    When I post a new Mom And Baby Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT


      #-------------------- Fashion --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Clothes
    When I post a new Clothes Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Watch
    When I post a new Watch Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Shoes
    When I post a new Shoes Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Perfume
    When I post a new Perfume Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Handbag
    When I post a new Handbag Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Fashion Accessories
    When I post a new Fashion Accessories Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT


 #-------------------- Entertainment --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Instrument
    When I post a new Instrument Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Book
    When I post a new Book Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Sport
    When I post a new Sport Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Collectibles
    When I post a new Collectibles Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Gaming
    When I post a new Gaming Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Entertainment Hobby
    When I post a new Hobby Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  #-------------------- Office --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Office Requisite
    When I post a new Office Equipment Requisite Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Office Specialized Item Requisite
    When I post a new Specialized Item Requisite Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT


      #-------------------- Service --------------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Service services
    When I post a new Service Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT

  @AUTHOR_VUHOANG_ME_API
  Scenario: GIÁ TỐT Label, Service travel
    When I post a new Travel Ad and Cho Tot accept my Ad for ad feature
    Then I should see Ad Feature Price displays
    When I add and pay for GIÁ TỐT label to my ad
    Then I should see my ad being added Nhãn GIÁ TỐT
