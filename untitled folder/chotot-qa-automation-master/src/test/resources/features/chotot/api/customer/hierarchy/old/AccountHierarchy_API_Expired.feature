@SERVICE_HIERARCHY
@CUSTOMER
@TAG_VUHOANG_MINHTRAN
@HIERARCHY_MASLOW_EXPIRED
Feature: Account Hierarchy API - Maslow Expired

  Background:
    Given I login with my Biz Account with balance "100000" that is expired

  #------------- Account Management : Expired -------------
  # when Parent is expired -> child can't pay by DT4B, VUhoang rewrite test case