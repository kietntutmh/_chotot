@SERVICE_TEST
@TELEGRAM_SUPPORT
@TAG_VUHOANG
@SUPPORT
Feature: VuHoang Supports Manual QC Team

  @SHOPELT_INSERTAD
  Scenario: Shop PTY, ELT, VEH Insert Ads
    Given Create Shop and post free ads
    #mvn clean test -Dcucumber.filter.tags="@SHOPELT_INSERTAD" -DcpAction=accept -DshopToChotot=true -DnumberOfAd=30 -Dphone="${phone}" -DisCreateNewShop="${isCreateNewShop}"

  @AUTOREVIEW
  Scenario: Test Autoreview
    Given Insert a new ad to test autoreview
    #mvn clean test -Dcucumber.filter.tags="@AUTOREVIEW" -DcreateNewUser=false

  @TOPUP
  Scenario: Topup momo
    Given Topup momo for specified account


  @STICKYAD
  Scenario: User post New Ad and buy Sticky Ad - region 13000, cate: 2060 (xe dap), 5060 (tablet)
    Given I login my account to post a New Ad and buy Sticky Ad for it

  @CREATE_LIST_USER
  Scenario: Support to create a list of new user
    Given Support to create a list of 4 new user
#    And I post a new House Ad and Cho Tot accept my Ad

  @REGISTER_TOPUP
  Scenario: Create User and Topup
    Given Create user and Topup for the account

  @DIEMTOT_ADD
  Scenario: Add Diem Tot
    Given Add Diem Tot to a specified user

