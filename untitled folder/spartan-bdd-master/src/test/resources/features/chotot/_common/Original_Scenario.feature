
Feature: Original Test Scenario

  Background:
    Given I register my new account to create a Shop


  Scenario: Create Temp Shop, User can create a new Temp Food Shop
    Given I create a new Temp "Food" Shop
    Then My Temp Food Shop should not be active
    And All my Temp "Food" Shop settings should be correct


  Scenario Outline: Create New Shop, User cannot pay for my Temp Shop when I missed required information
    Given I create and update my Temp "Food" Shop but missing "<required_param>"
    When I pay for my Temp "Food" Shop
    Then I should not be able to activate my New Shop
    Examples:
      | required_param |
      | avatar         |
      | cover          |
      | name           |
      | address        |
      | phone          |
      | category       |


  Scenario Outline: As an invalid User, I cannot login successfully
    Given I am on the web page "Login Page"
    When I login with username "<username>" and password "<password>"
    Then I should see "Validation Err" displays with text "<validation_msg>"
    Examples:
      | username   | password | validation_msg                                                  | Note                        |
      | 0363845958 | a        | Password: Mật khẩu phải có ít nhất 5 kí tự.                     | Correct phone - Invalid pwd |
      | 0363845958 | 00000    | Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại. | Correct phone - Wrong pwd   |
      | 0363840000 | 123456   | Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại. | Incorrect phone             |
      | abc        | 123456   | Phone: Số điện thoại không hợp lệ.                              | Invalid phone               |




