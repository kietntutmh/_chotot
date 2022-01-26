@SERVICE_BRAINSTEM
@BRAINSTEM2
@INSERTAD_MOM-AND-BABY_API
Feature: Insert Ad Mom and Baby API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Mom And Baby Ad successfully using API
    Then I can post a new Mom And Baby Ad using api successfully

  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Mom And Baby Pro Ad successfully using API
    Then I can post a new Mom And Baby Pro Ad using api successfully

