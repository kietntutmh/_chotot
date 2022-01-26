@SERVICE_FLASHAD
@INSERTAD_INDUSTRIAL_UI
@TELEGRAM_MABU
Feature: Insert Ad Industrial on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Industrial APIs are working
    Then All of InsertAd Industrial APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Requisite Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Requisite Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Specialized Item Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Specialized Item Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Office Equipment Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Equipment Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Requisite Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Requisite Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Specialized Item Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Specialized Item Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Office Equipment Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Equipment Ad as a Pro ad
    Then I should see Manage Ad page displayed
