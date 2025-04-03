#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Car Insurance Plan Selection and Checkout

  Scenario: Verify insurance plan prices match between cards and table
    Given I access the car CTPL insurance website
    Then I verify that the card prices match the table prices
    
  Scenario: Select a random insurance plan and verify checkout details
    Given I access the car CTPL insurance website
    When I select a random insurance plan
    Then I verify I am on the checkout payment page
    And I verify checkout summary information
    When I change policy start date to maximum allowed
    Then I verify policy start date changed
    When I change policy start date to minimum allowed
    Then I verify policy start date changed
    
  Scenario: Select a specific insurance plan and verify checkout details
    Given I access the car CTPL insurance website
    When I select the insurance plan "ERGO CTPL"
    Then I verify I am on the checkout payment page
    And I verify checkout summary information
    When I change policy start date to maximum allowed
    Then I verify policy start date changed
    When I change policy start date to minimum allowed
    Then I verify policy start date changed
