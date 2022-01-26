@SERVICE_ESCROW
@CUSTOMER
@TAG_VUHOANG_THUANLY_QUANGTRAN_TUANCHIEU
@ESCROW_UNLINK @ESCROW

Feature: Escrow API - UnLink Account Payoo

  Background:
    Given I login my Account as a Seller that is already linked to Payoo
    And I post a ELT Phone Private and Chotot accepted my Ad

    # smoke
  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Seller cannot remove account Payoo after Buyer pay for deposit order
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    When Seller unlink "Payoo" account from his Chotot account fail
    Then Seller cannot unlink account Payoo successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Seller can remove account Payoo linked to ChoTot account
    When Seller unlink "Payoo" account from his Chotot account
    Then Seller should be able to unlink account successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Seller can remove account Payoo linked to ChoTot account then re-linked
    When Seller unlink "Payoo" account from his Chotot account
    When Seller should be able to unlink account successfully
    When I link my Account to a Payoo account that is valid
    Then My Account should be linked to Payoo account as a Seller after I verified with valid OTP

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Buyer can remove account Payoo after send request "Tôi muốn đặt cọc"
    When Buyer requests deposit for my new Ad via Chat
    When I link my Account as a Buyer to a Payoo account that is valid
    When Buyer unlink "Payoo" account from Chotot account
    Then Buyer should be able to unlink successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Seller cannot remove account Payoo after send request "Yêu cầu đặt cọc"
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Seller unlink "Payoo" account from his Chotot account fail
    Then Seller cannot unlink account Payoo successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Seller cannot remove account Payoo when the order is raised a dispute
    When Buyer requests deposit for my new Ad via Chat
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    When Buyer dispute order with reason Tôi đổi ý, không muốn mua nữa
    When Seller unlink "Payoo" account from his Chotot account fail
    Then Seller cannot unlink account Payoo successfully

  @AUTHOR_THUANLY_ME_API @DONE
  Scenario: Buyer cannot remove account Payoo when the order is raised a dispute
    When Buyer requests deposit for my new Ad via Chat
    When I link my Account as a Buyer to a Payoo account that is valid
    When I create a deposit order for the request of Seller with 100k VND
    When Buyer pays the order by Visa Card on Payoo Payment Page successfully
    When Payoo notifies to me that money is transfered
    When Buyer dispute order with reason Tôi đổi ý, không muốn mua nữa
    When Buyer unlink "Payoo" account from Chotot account fail
    Then Buyer cannot unlink account successfully

