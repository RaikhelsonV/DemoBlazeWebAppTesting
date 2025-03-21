Feature: Login

  Scenario: User logs in with valid credentials
    Given User is on Home Page test
    When User navigates to Login Page
    And User enters valid credentials "val1" and "test1"
    Then User is logged in successfully with message "Welcome val1"