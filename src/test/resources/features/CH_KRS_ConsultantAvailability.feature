Feature: Functionality to check consultant's availability for
  knee replacement surgery of a patient based on their search criteria.

  As a patient wanting a knee replacement surgery
  I will provide my location details and select date of choice
  To check a consultant and validate their availability

  Background:
  Given I am on the Circle Health home page and clicking on - Book an appointment

    Scenario: <TestCaseID> Successfully validated the consultant availability for my postcode <locationType>
      Given I am a patient wanting a "Knee replacement surgery"
      When I provide my location as "EC1N" and click on search
      And I select my date of choice
#      And I select my availability as <preferredTime>
      Then I validate the time availability for the first available consultant in the list
#      Examples:
#        | TestCaseID | locationType | preferredTime |
#        | CH_KRS_001 | postcode | allDay  |
#        | CH_KRS_002 | postcode | morning |
#        | CH_KRS_003 | postcode     | afternoon     |
#        | CH_KRS_004 | postcode         | evening       |
#        | CH_KRS_005 | town             | allDay        |
#        | CH_KRS_006 | town             | morning       |
#        | CH_KRS_007 | town             | afternoon     |
#        | CH_KRS_008 | town             | evening       |
#        | CH_KRS_009 | current location | allDay        |
#        | CH_KRS_010 | current location | morning       |
#        | CH_KRS_011 | current location | afternoon     |
#        | CH_KRS_012 | current location | evening       |
#        | CH_KRS_013 | hospital         | allDay        |
#        | CH_KRS_014 | hospital         | morning       |
#        | CH_KRS_015 | hospital         | afternoon     |
#        | CH_KRS_016 | hospital         | evening       |



