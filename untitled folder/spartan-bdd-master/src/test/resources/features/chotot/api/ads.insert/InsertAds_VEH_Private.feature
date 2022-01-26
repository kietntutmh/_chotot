@SERVICE_BRAINSTEM
@BRAINSTEM7
@INSERTAD_VEH_API
Feature: Insert Ad VEH - Private API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Car Ad successfully using API
    Then I can post a new Car Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Motorbike Ad successfully using API
    Then I can post a new Motorbike Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Truck Ad successfully using API
    Then I can post a new Truck Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a ElectricVehicle Ad successfully using API
    Then I can post a new ElectricVehicle Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Bicycles Ad successfully using API
    Then I can post a new Bicycles Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a VehiclesPart Ad successfully using API
    Then I can post a new VehiclesPart Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other_Vehicle Ad successfully using API
    Then I can post a new Other_Vehicle Ad using api successfully
    
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Car Pro Ad successfully using API
    Then I can post a new Car Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Motorbike Pro Ad successfully using API
    Then I can post a new Motorbike Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Truck Pro Ad successfully using API
    Then I can post a new Truck Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a ElectricVehicle Pro Ad successfully using API
    Then I can post a new ElectricVehicle Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Bicycles Pro Ad successfully using API
    Then I can post a new Bicycles Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a VehiclesPart Pro Ad successfully using API
    Then I can post a new VehiclesPart Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other_Vehicle Pro Ad successfully using API
    Then I can post a new Other_Vehicle Pro Ad using api successfully