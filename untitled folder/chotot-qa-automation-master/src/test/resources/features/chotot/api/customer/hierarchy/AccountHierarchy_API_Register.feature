@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_THUANLY
@MAINTENANCE
Feature: Account Hierarchy API - Register Biz Account

  @AUTHOR_VUHOANG_ME_API
  Scenario: User becomes Biz when Chotot approves his register request
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot approves my Biz Register Request
    Then I should see my register request is sent to Chotot with status APPROVED
    Then My Registered Account should be Biz Account
    Then I can add some Child Account

#REGISTER
  @AUTHOR_VUHOANG_ME_API
  Scenario: User can register Biz Account with legal document & valid info
    Given I register a new Biz Account with valid info and legal document
    Then I should see my register request is sent to Chotot with status REVIEWING

  @AUTHOR_VUHOANG_ME_API
  Scenario: Chotot see status REVIEWING when User send a register request
    Given I register a new Biz Account with valid info and legal document
    Then Chotot should receive the register request with status REVIEWING

  @AUTHOR_VUHOANG_ME_API
  Scenario: User can't register when his Account is already Child of other Biz Account
    Given I login my Biz Account with "500000" Đồng Tốt
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | 0                      | 0                |
    When I login my Child Account
      | Total Amount | Child Amount Per Month | Amount Per Order |
      | 0            | -                      | 0                |
    Then I can't register a Biz Register request for my Child Account


#AD-HOC
  @AUTHOR_VUHOANG_ME_API
  Scenario: Chotot see only 1 request with status REVIEWING when user send 2 register requests
    Given I register a new Biz Account on 2 times
    Then Chotot should receive only 1 register request with status REVIEWING
    Then I should see only 1 register request is sent to Chotot with status REVIEWING

  @AUTHOR_VUHOANG_ME_API
  Scenario: Verify Correct Biz Account
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot approves my Biz Register Request
    Then My Registered Account should be Biz Account
    Then Budget Limitation of my Biz Account should be correct
      | Biz Total | Biz Child Per Month | Biz Child Per Order |
      | 0         | 0                   | 0                   |


#APPROVED  & Use
  @AUTHOR_VUHOANG_ME_API
  Scenario: Chotot see status APPROVED after approve the register request
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot approves my Biz Register Request
    Then Chotot should receive the register request with status APPROVED

  @AUTHOR_VUHOANG_ME_API
  Scenario: User can't edit info after Chotot approves the request
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot approves my Biz Register Request
    Then I can't update my Biz Register request and got Error: Register is exists


#REJECTED
  @AUTHOR_VUHOANG_ME_API
  Scenario: User doesn't become Biz after Chotot rejected the register request
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot rejects my Biz Register Request
    Then I should see my register request is sent to Chotot with status REJECTED
    Then I can't add Child Account

  @AUTHOR_VUHOANG_ME_API
  Scenario: Chotot see status REJECTED after approving the User request
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot rejects my Biz Register Request
    Then Chotot should receive the register request with status REJECTED


#EDIT REJECTED
  @AUTHOR_VUHOANG_ME_API
  Scenario: User can edit after his register request is rejected
    Given I register a new Biz Account and see my request is sent successfully
    When Chotot rejects my Biz Register Request
    When I update my Biz Register request when Chotot rejected my request
    Then I should see my register request is sent to Chotot with status REVIEWING
    Then Chotot should receive the register request with status REVIEWING