@SERVICE_BRAINSTEM
@BRAINSTEM1
@INSERTAD_ELECTRIC-APPLIANCES_API
Feature: Insert Ad Electric Appliances API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Cooler Ad successfully using API
    Then I can post a new Cooler Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Refrigerator Ad successfully using API
    Then I can post a new Refrigerator Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Washing_Machine Ad successfully using API
    Then I can post a new Washing_Machine Ad using api successfully

  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Cooler Pro Ad successfully using API
    Then I can post a new Cooler Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Refrigerator Pro Ad successfully using API
    Then I can post a new Refrigerator Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Washing_Machine Pro Ad successfully using API
    Then I can post a new Washing_Machine Pro Ad using api successfully

