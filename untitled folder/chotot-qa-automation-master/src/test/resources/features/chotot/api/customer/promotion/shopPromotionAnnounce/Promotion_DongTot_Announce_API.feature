#@SERVICE_PROMOTION
#@TELEGRAM_ME
#@TAG_VUHOANG_MINHTRAN_THAM
#@PROMOTION
Feature: Announcement Promotion (AP)

  @AUTHOR_VUHOANG_ME_API
  Scenario: Main Flow
#    Given I login my account to get Announce Promotion
    When Chotot creates AP Đồng Tốt Campaign
      | Đồng Tốt | Limit Total | Limit Per Account |
      | 13000    | 2           | 1                 |
    And Chotot assigns AP to me
    Then I should have "" Đồng Tốt from AP
      | ANNOUNCEMENT | Thông báo từ Chợ Tốt | Tặng bạn 10.000ĐT |