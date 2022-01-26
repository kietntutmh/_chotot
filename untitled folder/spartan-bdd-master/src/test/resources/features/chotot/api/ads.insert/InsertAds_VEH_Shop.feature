@SERVICE_BRAINSTEM
@BRAINSTEM7
@INSERTAD_VEH_API
Feature: Insert Ad VEH - Shop API

  Background: Setup Environment
    Given I login with Chotot User with Shop VEH

  ### To Shop ###
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Car Ad Shop successfully using API
    Then I can post a new Car Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Motorbike Ad Shop successfully using API
    Then I can post a new Motorbike Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Truck Ad Shop successfully using API
    Then I can post a new Truck Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a ElectricVehicle Ad Shop successfully using API
    Then I can post a new ElectricVehicle Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Bicycles Ad Shop successfully using API
    Then I can post a new Bicycles Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a VehiclesPart Ad Shop successfully using API
    Then I can post a new VehiclesPart Ad Shop using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Other_Vehicle Ad Shop successfully using API
    Then I can post a new Other_Vehicle Ad Shop using api successfully



  ### To Chotot ###
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Car Ad Chotot successfully using API
    Then I can post a new Car Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Motorbike Ad Chotot successfully using API
    Then I can post a new Motorbike Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Truck Ad Chotot successfully using API
    Then I can post a new Truck Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a ElectricVehicle Ad Chotot successfully using API
    Then I can post a new ElectricVehicle Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Bicycles Ad Chotot successfully using API
    Then I can post a new Bicycles Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a VehiclesPart Ad Chotot successfully using API
    Then I can post a new VehiclesPart Ad Chotot using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Shop User, I can post a Other_Vehicle Ad Chotot successfully using API
    Then I can post a new Other_Vehicle Ad Chotot using api successfully
