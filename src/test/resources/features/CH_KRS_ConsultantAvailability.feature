Feature: Functionality to check consultant's availability for
  knee replacement surgery of a patient based on their search criteria.

  I am a patient wanting a knee replacement
  I put in my location and date of leave 5th May* --> 10th Oct
  I can get a consultants* availability and location provided to make my decision

  Background:
  Given I am on the Circle Health home page and clicking on - Book an appointment

    Scenario: CH_KRS_TC001 Successfully validated the consultant availability for my postcode
      Given I am a patient wanting a "Knee replacement surgery"
      When I provide my location as "EC1N" and click on search
      And I select date as "Tuesday, October 10, 2023"
      And I select my availability as "allDay"
      Then I validate the time availability for the first available consultant in the list
#      Examples:
#        | TestCaseID | locationType | preferredTime |
#        | CH_KRS_001 | postcode     | allDay        |
#        | CH_KRS_002 | postcode     | morning       |
#        | CH_KRS_003 | postcode     | afternoon     |
#        | CH_KRS_004 | postcode     | evening       |


#      Scenario: Consultant unavailable for a (out of range)location = post code as KW15
#      Expected: The system should gracefully display error / information.