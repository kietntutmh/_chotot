@SERVICE_ESCROW
@CUSTOMER
@TAG_VUHOANG_THUANLY
@ESCROW_LINKING  @ESCROW
Feature: Escrow API - Link to Payoo

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Link to Payoo, Succeed with valid Payoo account
    Given I login my Account
    When I link my Account to a Payoo account that is valid
    Then My Account should be linked to Payoo account after I verified with valid OTP

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Link to Payoo, Succeed with linked main Payoo account
    Given I login my Account
    Then I can link my Account to a Payoo account that is linked as a main account

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Link to Payoo, Failed with locked Payoo account
    Given I login my Account
    Then I can not link my Account to a Payoo account that is locked

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Link to Payoo, Failed with non-private Payoo account
    Given I login my Account
    Then I can not link my Account to a Payoo account that is non-private

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Link to Payoo, Failed with linked sub Payoo account
    Given I login my Account
    Then I can not link my Account to a Payoo account that is linked as a sub account

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Link to Payoo, Failed with invalid OTP
    Given I login my Account
    When I link my Account to a Payoo account that is valid
    Then My Account should not be linked to Payoo account after I verified with invalid OTP

  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: Create data test escrow
    Given I login my Account with 500k Đồng Tốt
    And I post a new Phone Ad as a Private ad API and be accepted
    When I link my Account to a Payoo account that is valid
    Then My Account should be linked to Payoo account after I verified with valid OTP



