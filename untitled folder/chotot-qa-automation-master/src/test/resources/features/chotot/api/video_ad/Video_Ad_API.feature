@SERVICE_PROXY_VIDEO_UPLOAD
@TELEGRAM_GAMMA
@TAG_QUOCTRAN_NGUYENTRAN
@PROXY_VIDEO_UPLOAD
Feature: Upload Video Ad API

Background:
  Given New account for video_ad

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload a video ad for ios
    Then I can upload a video ad with type ".mp4" for "ios"
    Then I can upload a video ad with type ".mov" for "ios"
    Then I can upload a video ad with type ".flv" for "ios"
    Then I can upload a video ad with type ".wmv" for "ios"
    Then I can upload a video ad with type ".avi" for "ios"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload a video ad for android
    Then I can upload a video ad with type ".mp4" for "android"
    Then I can upload a video ad with type ".mov" for "android"
    Then I can upload a video ad with type ".flv" for "android"
    Then I can upload a video ad with type ".wmv" for "android"
    Then I can upload a video ad with type ".avi" for "android"

  @AUTHOR_QUOCTRAN_GAMMA_API
  Scenario: As an User, I can upload a new ad PTY with video for ios
    Then I can upload a new ad with type ".mp4" for "ios"


#  features.video.max_size=200mb
#  features.video.max_duration=180s
#  feature.video.max_video_per_month=10
# https://gateway.chotot.org/v1/public/chapy-pro/conf
#  feature.video.category.enable
#  feature.video.location.enable

#  # Validate on FE
#  @AUTHOR_QUOCTRAN_GAMMA_API
#  Scenario: As an User, I can upload a video ad with valid size
#    Then I can upload a video ad with size "<200mb" for "ios"
#
#  @AUTHOR_QUOCTRAN_GAMMA_API
#  Scenario: As an User, I can upload a video ad with valid duration
#    Then I can upload a video ad with duration "180s" for "ios"
#
#  @AUTHOR_QUOCTRAN_GAMMA_API
#  Scenario: As an User, I can upload a video ad in limit times
#    Then I can upload a video ad up to "10" times per month for "ios"

