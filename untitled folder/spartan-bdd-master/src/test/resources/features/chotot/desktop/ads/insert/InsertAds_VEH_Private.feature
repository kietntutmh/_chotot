@SERVICE_FLASHAD
@INSERTAD_VEH_UI
@TELEGRAM_MABU
Feature: Insert Ad VEH - Private on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd VEH APIs are working
    Then All of InsertAd VEH APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Car Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Car Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Motorbike Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Motorbike Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Trucks Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Trucks Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Electric Vehicles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Electric Vehicles Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Bicycles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bicycles Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Other Vehicles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other Vehicles Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Vehicle Parts Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Vehicle Parts Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Car Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Car Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Motorbike Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Motorbike Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Trucks Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Trucks Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Electric Vehicles Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Electric Vehicles Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Bicycles Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bicycles Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Other Vehicles Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other Vehicles Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Vehicle Parts Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Vehicle Parts Ad as a Pro ad
    Then I should see Manage Ad page displayed