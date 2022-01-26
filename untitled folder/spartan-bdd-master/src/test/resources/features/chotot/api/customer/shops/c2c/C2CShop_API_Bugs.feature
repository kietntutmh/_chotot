@SERVICE_SHOP-PROFILE
@C2CSHOPS_API
@CUSTOMER
@SHOPC2C
Feature: New C2C Shop API - Bugs

  Background:
    Given I register my new account to create a Shop

  @AUTHOR_VUHOANG_ME_API
  Scenario: Create New Shop, User can create a New Shop from a Temp Shop
    Given I register, pay and activate my new "Food" Shop
      | Name           | Address         | Desc           | Url     |
      | Shop Food Test | 2 Ngô Đức Kế Q1 | Shop Food Desc | food-03 |

    When Chotot approves my New Shop with updating information
      | Name           | Address         | Desc           | Url     |
      | Shop Food Test | 2 Ngô Đức Kế Q1 | Shop Food Desc | food-01 |
#    Then My "Food" Shop should be active with correct information
#    And I can visit my "Food" Shop Dashboard
#    And Buyers can visit my "Food" Shop


