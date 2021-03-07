Feature: Test
  @GoldBarTest
  Scenario: Find the fake gold bar in two weighings
    Given I open the browser to navigate to the website
    When I enter the first set of gold bars from below, three in each bowl
      | goldBar0 | 0 |
      | goldBar1 | 1 |
      | goldBar2 | 2 |
      | goldBar3 | 3 |
      | goldBar4 | 4 |
      | goldBar5 | 5 |
    And I enter one gold bar on each side using the values below if the previous weighing was equal otherwise using the values from the unequal set
      | goldBar6 | 6 |
      | goldBar7 | 7 |
    Then I will click the number of the gold bar that is fake and validate the message on the alert 'Yay! You find it!'