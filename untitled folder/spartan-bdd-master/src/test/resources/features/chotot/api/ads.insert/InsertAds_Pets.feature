@SERVICE_BRAINSTEM
@BRAINSTEM3
@INSERTAD_PETS_API
Feature: Insert Ad Pets API

  Background: Setup Environment
    Given I register a new Chotot User

  #PRIVATE
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Chicken Ad successfully using API
    Then I can post a new Chicken Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Dog Ad successfully using API
    Then I can post a new Dog Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Bird Ad successfully using API
    Then I can post a new Bird Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Cat Ad successfully using API
    Then I can post a new Cat Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other Pet Ad successfully using API
    Then I can post a new Other Pet Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Pet_Accessories Ad successfully using API
    Then I can post a new Pet_Accessories Ad using api successfully
    
  #PRO
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Chicken Pro Ad successfully using API
    Then I can post a new Chicken Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Dog Pro Ad successfully using API
    Then I can post a new Dog Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Bird Pro Ad successfully using API
    Then I can post a new Bird Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Cat Pro Ad successfully using API
    Then I can post a new Cat Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Other Pet Pro Ad successfully using API
    Then I can post a new Other Pet Pro Ad using api successfully

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As a Private User, I can post a Pet_Accessories Pro Ad successfully using API
    Then I can post a new Pet_Accessories Pro Ad using api successfully