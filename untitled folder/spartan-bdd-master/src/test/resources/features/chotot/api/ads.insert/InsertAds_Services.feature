@SERVICE_BRAINSTEM
@BRAINSTEM3
@INSERTAD_SERVICE_API
Feature: Insert Ad Service API

  Background: Setup Environment
    Given I register a new Chotot User
    
  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Service Ad successfully using API
    Then I can post a new Service Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Travel Ad successfully using API
    Then I can post a new Travel Ad using api successfully
    
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Service Pro Ad successfully using API
    Then I can post a new Service Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Travel Pro Ad successfully using API
    Then I can post a new Travel Pro Ad using api successfully