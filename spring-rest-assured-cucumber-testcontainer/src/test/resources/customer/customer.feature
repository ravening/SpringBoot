Feature: Customer API

  Scenario: As an API consumer I want to be able to create a new customer
    Given some customer details
    When I invoke the create customer operation with the customer details
    Then the customer details should be persisted
