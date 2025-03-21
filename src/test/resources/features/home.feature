Feature: Product Management and Checkout

  Background:
    Given The user is on the homepage of Demoblaze

  Scenario: User adds a product to the cart
    When The user selects a product with title "Nokia lumia 1520"
    And The user adds the product to the cart
    Then The cart should contain 1 item

  Scenario: User removes a product from the cart
    Given The user has added "Nokia lumia 1520" to the cart
    When The user removes the "Nokia lumia 1520" from the cart
    Then The cart should be empty

  Scenario: User proceeds to checkout after adding a product to the cart
    Given The user has added "Nokia lumia 1520" to the cart
    When The user clicks on Place Order
    When The user fills out the order form with name "Val", country "Israel", city "Kfar Saba", credit card "1234567890123456", month "12", year "2025"
    Then The user should see a confirmation message with order details "Val" and "1234567890123456"
