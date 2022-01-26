@SERVICE_FLASHAD
@INSERTAD_PTY_UI
@TELEGRAM_MABU
Feature: Insert Ad PTY - Private on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd PTY APIs are working
    Then All of InsertAd PTY APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a House Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new House Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Apartment Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Apartment Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Land Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Land Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Office Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Room Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Room Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a House Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new House Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Apartment Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Apartment Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Land Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Land Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Office Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Office Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Room Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Room Ad as a Pro ad
    Then I should see Manage Ad page displayed
