@SERVICE_PRICER
@CUSTOMER
@TAG_VUHOANG
#@NEWPRICER_DB
Feature: New Pricer API check with DB

  Background:
    Given Get data
    And I login my account to check price

    #------------- Check Vnd ------------
  @AUTHOR_VUHOANG_ME_API
  Scenario: Bump API
    Then Bump API Get-All should load same data with database

  @AUTHOR_VUHOANG_ME_API  @VUHOANG_DEBUG
  Scenario: Ad Feature Get-All API
    Then Ad Feature API Get-All should load same data with database

  @AUTHOR_VUHOANG_ME_API
  Scenario: Quick check all APIs with static categories
    Then Check all Pricer APIs with static category "1010"



