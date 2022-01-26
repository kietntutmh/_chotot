@SERVICE_FLASHAD
@INSERTAD_ELECTRIC-APPLIANCES_UI
@TELEGRAM_MABU
Feature: Insert Ad Electric Appliances on UI

  Background: Setup Environment
    Given I register a new Chotot User

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Cooler Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cooler Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Refrigerator Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Refrigerator Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Washing_Machine Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Washing_Machine Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Cooler Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cooler Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Refrigerator Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Refrigerator Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Washing_Machine Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Washing_Machine Ad as a Pro ad
    Then I should see Manage Ad page displayed
