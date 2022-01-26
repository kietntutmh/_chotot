@SERVICE_FLASHAD
@INSERTAD_HOUSEHOLD_UI
Feature: Insert Ad Household on UI

  Background: Setup Environment
    Given I register a new Chotot User

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd Household APIs are working
    Then All of InsertAd Household APIs should be working

  # Private
  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Kitchen Appliance Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Kitchen Appliance Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI1
  Scenario: As a Private User, I can post a Kitchen Utensil Dinnerware Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Kitchen Utensil Dinnerware Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Bed Bedding Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bed Bedding Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI2
  Scenario: As a Private User, I can post a Bathware Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bathware Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Fan Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Fan Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI3
  Scenario: As a Private User, I can post a Lighting Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Lighting Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Household Table Chair Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Table Chair Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI4
  Scenario: As a Private User, I can post a Household Drawer Shelf Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Drawer Shelf Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Ornamental Plant Decoration Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Ornamental Plant Decoration Ad as a private ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI5
  Scenario: As a Private User, I can post a Household Items Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Items Ad as a private ad
    Then I should see Manage Ad page displayed

  # Pro
  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Kitchen Appliance Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Kitchen Appliance Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI6
  Scenario: As a Private User, I can post a Kitchen Utensil Dinnerware Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Kitchen Utensil Dinnerware Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Bed Bedding Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bed Bedding Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI7
  Scenario: As a Private User, I can post a Bathware Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bathware Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Private User, I can post a Fan Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Fan Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Private User, I can post a Lighting Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Lighting Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Private User, I can post a Household Table Chair Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Table Chair Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Private User, I can post a Household Drawer Shelf Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Drawer Shelf Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Private User, I can post a Ornamental Plant Decoration Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Ornamental Plant Decoration Ad as a Pro ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Private User, I can post a Household Items Pro Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Household Items Ad as a Pro ad
    Then I should see Manage Ad page displayed

