@SERVICE_BRAINSTEM
@BRAINSTEM2
@INSERTAD_OTHERS_API
Feature: Insert Ad Other API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other Ad successfully using API
    Then I can post a new Other Ad using api successfully

  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other Pro Ad successfully using API
    Then I can post a new Other Pro Ad using api successfully

