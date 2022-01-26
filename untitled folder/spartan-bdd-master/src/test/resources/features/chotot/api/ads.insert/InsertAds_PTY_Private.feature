@SERVICE_BRAINSTEM
@BRAINSTEM6
@INSERTAD_PTY_API
Feature: Insert Ad PTY - Private API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Apartment Ad successfully using API
    Then I can post a new Apartment Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a House Ad successfully using API
    Then I can post a new House Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Land Ad successfully using API
    Then I can post a new Land Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Office Ad successfully using API
    Then I can post a new Office Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a RoomForLease Ad successfully using API
    Then I can post a new RoomForLease Ad using api successfully
    
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Apartment Pro Ad successfully using API
    Then I can post a new Apartment Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a House Pro Ad successfully using API
    Then I can post a new House Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Land Pro Ad successfully using API
    Then I can post a new Land Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Office Pro Ad successfully using API
    Then I can post a new Office Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a RoomForLease Pro Ad successfully using API
    Then I can post a new RoomForLease Pro Ad using api successfully