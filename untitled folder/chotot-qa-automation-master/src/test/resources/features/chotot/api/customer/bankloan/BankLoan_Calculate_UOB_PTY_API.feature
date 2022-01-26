@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@LOAN
Feature: Bank Loan Calculate PTY API - UOB
# 5 points 1000 Dong Tot  And I choose Tenure "" months

  Background:
    Given I use Bank Loan to buy a PTY House Ad with loan value "1000000000"
    And I choose Tenure "2" years


  @AUTHOR_VUHOANG_ME_API  @DONE
  Scenario: As an User, I have to pay some money first when I buy a PTY ad by loan
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then My paid first money should be correct

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: As an User, I have to pay loan principle when I buy a PTY ad by loan
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then My loan principle should be correct

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: As an User, I have to pay loan in several months that has time equals to the loan year
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then I should pay loan in "24" months

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: As an User, I have to pay loan in several years that has time equals to the loan year
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then I should pay loan in "2" years

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: As an User, My monthly rate is equal to yearly rate divided by the total month
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then My monthly rate should be yearly rate divided by the total month

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: As an User, My interest total is equal to sum of interest per month
    Given I choose Loan Package 18
    When Chotot calculates Bank Loan
    Then My interest total should be equal to sum of interest per month


