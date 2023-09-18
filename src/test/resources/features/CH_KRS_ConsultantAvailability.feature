Feature: Functionality to check consultant's availability for
  knee replacement surgery of a patient based on their search criteria.

  As a patient wanting a knee replacement surgery
  I will provide my location details and select date of choice
  To check a consultant and validate their availability

  Background:
  Given I am on the Circle Health home page and clicking on - Book an appointment

    Scenario: CH_KRS_TC001 Successfully validated the consultant availability for my postcode
      Given I am a patient wanting a "Knee replacement surgery"
      When I provide my location as "EC1N" and click on search
      And I select my date of choice
#      And I select my availability as <preferredTime>
      Then I validate the time availability for the first available consultant in the list
#      Examples:
#        | TestCaseID | locationType | preferredTime |
#        | CH_KRS_001 | postcode     | allDay        |
#        | CH_KRS_002 | postcode     | morning       |
#        | CH_KRS_003 | postcode     | afternoon     |
#        | CH_KRS_004 | postcode     | evening       |


