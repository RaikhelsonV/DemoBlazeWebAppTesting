Feature: User Registration

  Scenario: New user can sign up
    Given User is on Home Page
    When User navigates to Sign Up Page
    And User enters a unique username "val7171" and password "test7171"
    Then User sees a registration success message "Sign up successful."
