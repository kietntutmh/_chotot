@SERVICE_SENDY-API
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_NGUYENTRAN
@SENDY-API
Feature: Sendy API

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can send an email for difference templates
    Then I can send email for template "ad_test_template"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can check a valid or invalid email
    Then I can check a valid email "quoctran@chotot.vn"
    And I can check an invalid email "quoctest@chotot.vn"