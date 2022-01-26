@SERVICE_FLASHAD
@INSERTAD_VEH_UI
@TELEGRAM_MABU
Feature: Insert Ad VEH -Shop on UI

  Background: Setup Environment
    Given I login with Chotot User with Shop VEH

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd VEH APIs are working
    Then All of InsertAd VEH APIs should be working

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Shop, I can post a Car Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Car Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI8
  Scenario: As a Shop, I can post a Car Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Car Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Shop, I can post a Motorbike Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Motorbike Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI9
  Scenario: As a Shop, I can post a Motorbike Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Motorbike Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Trucks Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Trucks Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Trucks Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Trucks Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI11
  Scenario: As a Shop, I can post a Electric Vehicles Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Electric Vehicles Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI11
  Scenario: As a Shop, I can post a Electric Vehicles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Electric Vehicles Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI12
  Scenario: As a Shop, I can post a Bicycles Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bicycles Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI12
  Scenario: As a Shop, I can post a Bicycles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Bicycles Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI13
  Scenario: As a Shop, I can post a Other Vehicles Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other Vehicles Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI13
  Scenario: As a Shop, I can post a Other Vehicles Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Other Vehicles Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI14
  Scenario: As a Shop, I can post a Vehicle Parts Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Vehicle Parts Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI14
  Scenario: As a Shop, I can post a Vehicle Parts Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Vehicle Parts Ad as a Chotot ad
    Then I should see Manage Ad page displayed
