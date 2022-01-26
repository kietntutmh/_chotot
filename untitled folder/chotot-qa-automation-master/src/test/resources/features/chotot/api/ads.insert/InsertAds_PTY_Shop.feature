@SERVICE_BRAINSTEM
@BRAINSTEM6
@INSERTAD_PTY_API
Feature: Insert Ad PTY - Shop API

  Background: Setup Environment
    Given I login with Chotot User with Shop PTY

  ### To Shop ###
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Apartment Ad Shop successfully using API
    Then I can post a new Apartment Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a House Ad Shop successfully using API
    Then I can post a new House Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Land Ad Shop successfully using API
    Then I can post a new Land Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Office Ad Shop successfully using API
    Then I can post a new Office Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a RoomForLease Ad Shop successfully using API
    Then I can post a new RoomForLease Ad Shop using api successfully


  ### To Chotot ###
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Apartment Ad Chotot successfully using API
    Then I can post a new Apartment Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a House Ad Chotot successfully using API
    Then I can post a new House Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Land Ad Chotot successfully using API
    Then I can post a new Land Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Office Ad Chotot successfully using API
    Then I can post a new Office Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a RoomForLease Ad Chotot successfully using API
    Then I can post a new RoomForLease Ad Chotot using api successfully
