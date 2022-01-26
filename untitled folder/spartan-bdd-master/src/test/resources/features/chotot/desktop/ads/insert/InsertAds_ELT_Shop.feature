@SERVICE_FLASHAD
@INSERTAD_ELT_UI
Feature: Insert Ad ETL - Shop on UI

  Background: Setup Environment
    Given I login with Chotot User with Shop ELT

  @SMOKE @AUTHOR_QUOCTRAN_MABU_API
  Scenario: All InsertAd ETL APIs are working
    Then All of InsertAd ETL APIs should be working

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Cellphone Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cellphone Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI10
  Scenario: As a Shop, I can post a Cellphone Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Cellphone Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI11
  Scenario: As a Shop, I can post a Tablet Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Tablet Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI11
  Scenario: As a Shop, I can post a Tablet Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Tablet Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI12
  Scenario: As a Shop, I can post a Laptop Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Laptop Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI12
  Scenario: As a Shop, I can post a Laptop Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Laptop Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI13
  Scenario: As a Shop, I can post a Desktop Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Desktop Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI13
  Scenario: As a Shop, I can post a Desktop Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Desktop Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI14
  Scenario: As a Shop, I can post a Camera Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Camera Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI14
  Scenario: As a Shop, I can post a Camera Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Camera Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI15
  Scenario: As a Shop, I can post a TV Sound Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new TV Sound Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI15
  Scenario: As a Shop, I can post a TV Sound Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new TV Sound Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI16
  Scenario: As a Shop, I can post a Smart Watch Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Smart Watch Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI16
  Scenario: As a Shop, I can post a Smart Watch Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Smart Watch Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI17
  Scenario: As a Shop, I can post a Accessories Monitor Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Monitor Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI17
  Scenario: As a Shop, I can post a Accessories Monitor Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Monitor Ad as a Chotot ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI18
  Scenario: As a Shop, I can post a Accessories Ram Ad on my Shop successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ram Ad as a Shop ad
    Then I should see Manage Ad page displayed

  @AUTHOR_QUOCTRAN_MABU_UI @CI18
  Scenario: As a Shop, I can post a Accessories Ram Ad successfully
    Given I am on Chợ Tốt - Tôi Bán
    When I post a new Accessories Ram Ad as a Chotot ad
    Then I should see Manage Ad page displayed


