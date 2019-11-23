Feature: Basic eCommerce site functionality

  These tests should confirm the following functionality
  1. Logging in
  2. Product search
  3. Adding products to a shopping cart
  4. Removing products from a shopping cart
  5. Checking out
  6. Logging out

  Scenario: Valid Login
    Given I am a user on the website
    When I log in using valid credentials as "testingcps3230" with "what is the time69"
    Then I should be logged in

  Scenario: Invalid Login
    Given I am a user on the website
    When I log in using invalid credentials as "RandomGuy" with "RandomPassword"
    Then I should not be logged in

  Scenario: Product Search
    Given I am a logged in user on the website as "testingcps3230" with "what is the time69"
    When I search for a product
    And I select the first product in the list
    Then I should see the product details

  Scenario: Add product to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I view the details of a product
    And I choose to buy the product
    Then my shopping cart should contain 1 item

  Scenario Outline: Add multiple products to cart
    Given I am a logged in user on the website
    And my shopping cart is empty
    When I add <num_products> products to my shopping cart
    Then my shopping cart should contain <num_products> items

    Examples:
    |num_products|
    |3   |
    |5   |
    |10  |
#  Values for num-products: 3,5,10

  Scenario: Removing a product from cart
    Given I am a logged in user on the website
    And my shopping cart has 2 products
    When I remove the first product in my cart
    Then my shopping cart should contain 1 item