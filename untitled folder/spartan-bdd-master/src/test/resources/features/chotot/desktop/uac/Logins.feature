@SERVICE_UAC
@LOGIN_UI
Feature: Login Functionality of Chotot

  Background: Setup Environment
    Given I am on "Chotot" website

  @AUTHOR_VUHOANG_GAMMA_UI
  Scenario: As a User, I can login successfully
    Given I am on the web page "Login Page"
    When I login with username "0999112233" and password "123456"
    Then I should see all elements of the web page "Home Page"

  @AUTHOR_VUHOANG_GAMMA_UI
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

  @AUTHOR_VUHOANG_GAMMA_UI
  Scenario Outline: As leaving blank phone, I can't login successfully and see error message
    Given I am on the web page "Login Page"
    When I login with username "<username>" and password "<password>"
    Then I should see the "<field>" tool-tip field require displays as "Please fill out this field."
    Examples:
      | username   | password | field    |
      |            |          | username |
      | 0999112233 |          | password |