@SERVICE_BRAINSTEM
@BRAINSTEM3
@INSERTAD_REQUISITE_API
Feature: Insert Ad Requisite API

  Background: Setup Environment
    Given I register a new Chotot User
    
  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Requisite Ad successfully using API
    Then I can post a new Requisite Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Specialized_Item Ad successfully using API
    Then I can post a new Specialized_Item Ad using api successfully
    
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Requisite Pro Ad successfully using API
    Then I can post a new Requisite Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Specialized_Item Pro Ad successfully using API
    Then I can post a new Specialized_Item Pro Ad using api successfully