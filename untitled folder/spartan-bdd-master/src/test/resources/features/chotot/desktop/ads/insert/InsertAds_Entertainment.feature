@SERVICE_FLASHAD
@INSERTAD_ENTERTAINMENT_UI
Feature: Insert Ad Entertainment on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Entertainment APIs are working
    Then All of InsertAd Entertainment APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Instrument Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Instrument Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Book Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Book Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Sport Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Sport Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Collectibles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Collectibles Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Gaming Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Gaming Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Habit Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Habit Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Instrument Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Instrument Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Book Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Book Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Sport Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Sport Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Collectibles Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Collectibles Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Gaming Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Gaming Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Habit Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Habit Ad as a Pro ad
    Then I should see Manage Ad page displayed

