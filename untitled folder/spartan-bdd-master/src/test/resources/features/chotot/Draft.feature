Feature: Below Test Scenarios haven't automated yet

  Scenario: Private Dashboard
  1. Chưa có Shop: insert Ad cá nhân or pro
  2. Shop bị expired -> ad lên Private Dashboard, shop extend -> Ad tự chuyển lên Shop Dashboard
  3. Shop is blocked -> ad tự chuyển qua Private Dashboard

  Scenario: Insert Ad
  1. Insert Ad onto Shop:
  1a. Accepted
  1b. Rejected
  1c. Being reviewed
  2. After Shop Ad is accepted, it displays on Listing & Shop Dashboard


Feature: Account Hierarchy phase one

  #------------- Account Management -------------
  # Account management and DT4B management in account for biz side
  Scenario: Register an account for biz
    # Đăng ký tài khoản cho doanh nghiệp

  Scenario: Liên kết tài khoản chợ tốt vào tài khoản doanh nghiệp

  Scenario: Liên kết 2 lần 1 tài khoản lần 2 báo lỗi.
  Có case chird accout đăng ký vào account for biz hay không?

  Scenario: Liên kết tới account bị block.

  Scenario: account bị deleted


  Scenario: Cannot link acount for biz to account for biz?

  Scenario: limited number of chirend account.
  1 chird được bao nhiêu parent.
  1 account con có thể link tới 1 account con hay không?

  Scenario: View list of chird account.

  Scenario: Gỡ bỏ 1 tài khoản chợ tốt ra khỏi tài khoản doanh nghiệp
  Account con có được quyền out ra khỏi biz hay không

  Scenario: Re-add chid account.


  #------------- Deposit -------------

  Scenario: Deposit DT4B to Business Account

  Scenario: Can't deposit DT4B to Child Account

  Scenario: Can't deposit DT4B to Normal Account

  #------------- Limitation -------------

  Scenario: Limitation for Business Account. Business Limitation <= Current DT4B
    Given Deposit "10000" to DT4B

    When Setup Limitation for Business Account with "11000"
    Then Business Limitation should not be setup

    When Setup Limitation for Business Account with "10000"
    Then Business Limitation should not setup

  Scenario: Limitation for per Child Account. Child Limitation <= Business Limitation
    Given Setup Limitation for Business Account with "10000"

    When Setup Child Limitation with "11000"
    Then Child Limitation should not be setup

    When Setup Child Limitation with "10000"
    Then Child Limitation should be setup

  Scenario: Limitation for per transaction. Trans Limitation <= The rest of Child Limitation
    Given Setup Limitation for Child Account with "10000"

    When Pay an order with "11000"
    Then Transaction should not be successful

    When Pay an order with "10000"
    Then Transaction should be successful

  Scenario: Limitation for per transaction. Concurrent transactions
    Given Setup Limitation for Child Account with "10000"

    When Pay concurrent orders with "6000"
    Then The first transaction should be successful
    And The second successful should not be successful

  #------------- DT4B order history -------------

  Scenario: History, Business Account views Business history

  Scenario: History, Business Account views Total of Child history

  Scenario: History, Business Account views each of Child history

  Scenario: History, Business Account views Deleted/Unlinked Child history

  Scenario: History, Child Account views Child history

  Scenario: History, Child Account views Child history after unlinked from Business Account (Ad-hoc)

  Scenario: History, Child Account can't view others's, Business Account's (Ad-hoc)

  #------------- Pay for Prenium Service -------------



#------------------------------------------------------------------------------------------------------------------------------
