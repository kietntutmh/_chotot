@SERVICE_BRAINSTEM
@BRAINSTEM2
@INSERTAD_JOBS_API
Feature: Insert Ad Jobs API

  Background: Setup Environment
    Given I register a new Chotot User
    
  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Job Ad successfully using API
    Then I can post a new Job Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Looking Job Ad successfully using API
    Then I can post a new Looking Job Ad using api successfully
  
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Job Pro Ad successfully using API
    Then I can post a new Job Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Looking Job Pro Ad successfully using API
    Then I can post a new Looking Job Pro Ad using api successfully