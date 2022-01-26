@SERVICE_FLASHAD
@INSERTAD_ELT_UI
Feature: Insert Ad ETL - Private on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd ETL APIs are working
    Then All of InsertAd ETL APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Cellphone Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cellphone Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Tablet Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Tablet Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Laptop Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Laptop Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Desktop Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Desktop Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Camera Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Camera Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a TV Sound Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I pose a new TV Sound Ad as a Private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Smart Watch Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Smart Watch Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Accessories Monitor Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Monitor Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Accessories Ram Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ram Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Cellphone Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cellphone Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Tablet Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Tablet Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Laptop Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Laptop Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Desktop Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Desktop Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Camera Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Camera Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Private User, I can post a TV Sound Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new TV Sound Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Private User, I can post a Smart Watch Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Smart Watch Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Private User, I can post a Accessories Monitor Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Monitor Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Private User, I can post a Accessories Ram Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ram Ad as a Pro ad
    Then I should see Manage Ad page displayed


