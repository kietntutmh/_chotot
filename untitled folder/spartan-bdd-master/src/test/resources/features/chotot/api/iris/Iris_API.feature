@SERVICE_IRIS-FAS
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_HONG
@IRIS-FAS
Feature: Iris API

  Background:
    Given Reset test data for iris

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload image with type chat, banner, admincentre
    Then I can upload image with type "chat"
    And I can upload image with type "banner"
    And I can upload image with type "admincentre"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload image with formats jpg, png, gif, bmp
    Then I can upload image format ".jpg"
    And I can upload image format ".png"
    And I can upload image format ".gif"
    And I can upload image format ".bmp"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I cannot upload image without formats tiff, bat, svg
    Then I cannot upload image format ".tiff"
    And I cannot upload image format ".bat"
    And I cannot upload image format ".svg"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload image with size less than 10mb
    Then I can upload image less than "10mb"
    And  I cannot upload image greater than "10mb"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I cannot upload image too small
    Then  I can upload image with dimension <= "10000"
    And  I cannot upload image too small with dimension < "240"

#  # Only run for Chotot staging env
#  @AUTHOR_QUOCTRAN_GAMMA_API
#  Scenario: As an User, I cannot upload too many image in short time for chat type
#    Given I register a new account
#    Then I cannot upload more than "10" images per hour for "chat" type

  # Only run for Mudah staging env
  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I cannot upload too many image in short time for avatar type in MD
    Given I logged in to Mudah site
    Then I cannot upload more than "100" images per hour for "avatar" type in MD

  # Should not be run frequently, because of impacts to GCS
#  @AUTHOR_QUOCTRAN_GAMMA_API @UPLOAD_1000_IMAGES
#  Scenario: As an User, I cannot upload too many image in short time
#    Given I register a new account
#    Then I cannot upload more than "1000" images per hour
