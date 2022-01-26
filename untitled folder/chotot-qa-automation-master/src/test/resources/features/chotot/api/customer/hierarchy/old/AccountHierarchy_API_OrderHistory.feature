@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_LAC
@HIERARCHY_ORDERHISTORY
Feature: Account Hierarchy API - Order History


    #------------- pay Order History of Biz, Child -------------
  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Pay Order History, Biz can see payment of Child Private
    Given I register a new Biz Account that isn't linked to any Child Account
    And DT4B balance is "200000"
    Then I should pay for premium service Single Bump with my Child Account successfully
    And I should see the POS order of my Child Account in Biz Order History
    And I should see the POS order of my Child Account in Child Order History

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Pay Order History, Biz can see payment of Child Pro
    Given I register a new Biz Account that isn't linked to any Child Account
    And DT4B balance is "200000"
    Then I should pay for premium service Single Bump of Pro Ad with my Child Account successfully
    And I should see the POS order of my Child Account in Biz Order History
    And I should see the POS order of my Child Account in Child Order History



    #Listing fee for Child Pro, Child SHop, Biz Shop