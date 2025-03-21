Feature: User Registration

  Scenario: New user can sign up
    Given User is on Home Page
    When User navigates to Sign Up Page
    And User enters a unique username "val6" and password "test6"
    Then User sees a registration success message "Sign up successful."
