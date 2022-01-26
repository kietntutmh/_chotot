@SERVICE_ESCROW
@CUSTOMER
@TAG_VUHOANG_THUANLY_TUANCHIEU
@ESCROW_DEPOSIT  @ESCROW
Feature: Escrow API - Deposit flow & Order Management

  Background:
    Given I login my Account as a Seller that is already linked to Payoo
#    And I post a ELT Phone Private and Chotot accepted my Ad
    And I post a VEH Car Ad and Chotot accepted my Ad

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Dispute order by buyer after paying | DISPUTE_RAISED_BY_BUYER
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    Then Buyer dispute order with reason Tôi đổi ý, không muốn mua nữa
    Then Buyer should see Order Status is DISPUTE_RAISED_BY_BUYER

  @AUTHOR_VUHOANG_ME_API @DONE
  Scenario: Deposit flow
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    Then I can confirm that I already delivered my product
    Then Buyer confirms his payment on Chotot after paid on Payoo Page
    Then Buyer can confirm that he already received my product

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: As a seller, i dispute the order after sending request deposit | CANCELLED_BY_SELLER
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    And I dispute a deposit before buyers pay
    Then I should be able to dispute the deposit successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Dispute order by seller after buyer paying | DISPUTE_RAISED_BY_SELLER
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    Then I dispute order with reason Tôi không muốn bán nữa
    Then I should see Order Status is DISPUTE_RAISED_BY_SELLER

#  @AUTHOR_VUHOANG_ME_API
#  Scenario: Deposit flow
#    When Buyer requests deposit for my new Ad via Chat
#    When I click on Tab TÔI MUA of Order Management
#    When I click on Tab TÔI BÁN of Order Management
#    Then I should see Buyer's request with status ĐÃ GỬI YÊU CẦU ĐẶT CỌC on ĐANG GIAO DỊCH tab of Order Management



