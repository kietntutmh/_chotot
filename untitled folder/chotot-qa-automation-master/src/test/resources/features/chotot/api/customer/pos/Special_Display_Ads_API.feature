@SERVICE_POS
@CUSTOMER
@TAG_QUANGTRAN
@POS_SPECIAL_DISPLAY
Feature: Premium Service - Special Display Ads

  Background:
    Given I get info of Special Display Ads

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can see price of Special Display Ads service on POS is correct
    Then The price of Special Display Ads service of Category "1010" should be "95000"
    Then The price of Special Display Ads service of Category "1020" should be "95000"
    Then The price of Special Display Ads service of Category "1050" should be "95000"
    Then The price of Special Display Ads service of Category "2010" should be "95000"
    Then The price of Special Display Ads service of Category "2020" should be "95000"

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can see Special Display Ads service is displayed on POS (PTY and VEH ads in region HCM)
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    And I should see Special Display Ads service is displayed on POS and the price is correct

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can not see Special Display Ads service is displayed on POS (Except PTY and VEH ads in region HCM)
    Given I login my Account with 500k Đồng Tốt
    When I post a ELT Phone Private and Chotot accepted my Ad
    Then I should not see Special Display Ads service is displayed on POS

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can add Special Display Ads service to cart for purchasing
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    Then I can add Special Display Ads service to cart for purchasing

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can purchase Special Display Ads service
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    Then I can purchase Special Display Ads service successfully

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can see the order of Special Display Ads service is created after purchasing
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    And I can purchase Special Display Ads service successfully
    Then I should see the order in Order History

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can see the statistics of the Special Display Ads service
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    And I can purchase Special Display Ads service successfully
    Then I can see the statistics of the Special Display Ads service

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I receive the refund when the Ads is refused
    Given I login my Account with 500k Đồng Tốt
    When I post a new Ad and pay for POS Special Display Ads but ad is rejected and DT is refunded
    Then I can see refund order in Order History
    And I can see DT balance is correct

  @AUTHOR_QUANGTRAN_ME_API @FIXING
  Scenario: As an User, I can purchase the Special Display Ads with other services
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    Then I can purchase Special Display Ads with Bump successfully
    And I can see the order of Bump and Special Display Ads in Order History

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can purchase the Special Display Ads multiple times
    Given I login my Account with 500k Đồng Tốt
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    Then I can purchase Special Display Ads service successfully
    And I should see the order in Order History
    And I can purchase Special Display Ads service successfully one more time

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can purchase the Special Display Ads by Dong Tot Free
    Given I login my Account
    And I topup "100000" Dong Tot Free
    When I post a PTY Apartment Ad and Chotot accepted my Ad
    Then I can purchase Special Display Ads service successfully
    Then I should see the order in Order History

  @AUTHOR_QUANGTRAN_ME_API @DONE
  Scenario: As an User, I can purchase the Special Display Ads by Dong Tot Biz
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 100000                  | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can use DT4B to pay for an Ad with Special Display Ads service by child account
    And I should see the order in Order History