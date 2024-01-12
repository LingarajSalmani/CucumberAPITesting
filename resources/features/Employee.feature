Feature: API Testing

  Scenario: Verify Create new employee API Response
    Given Create new employee api endpoint
    When I make a POST request to create new employee endpoint
    Then the response status code should be 200
    And the response should contain name salary and age

  Scenario: Verify get single employee API Response
    Given Create new employee api endpoint
    When I make a POST request to create new employee endpoint
    Then the response status code should be 200
    And the response should contain name salary and age
    Then I make a GET request to get single employee details endpoint
    And the response should contains the name salary and age of newly created employee

  Scenario: Verify delete employee API Response
    Given Create new employee api endpoint
    When I make a POST request to create new employee endpoint
    Then the response status code should be 200
    And the response should contain name salary and age
    #Then I make a GET request to get single employee details endpoint
    #And the response should contains the name salary and age of newly created employee
    Then I make a GET request to delete the employee
    And verify that employee delete successfull message is returned


  Scenario: Verify get all employee details API Response
    Given GET all employees endpoint
    When I make a GET request to all employee endpoint
    Then the response status code should be 200
    And verify the success message of get all employee endpoint