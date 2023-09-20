Feature: Functionality to check consultant's availability for knee replacement surgery of a patient based on their search criteria.

  Test requirement:
  I am a patient wanting a knee replacement
  I put in my location and date of leave 5th May* --> 11th Oct
  I can get a consultants* availability and location provided to make my decision

  Background:
  Given I am on the Circle Health home page and clicking on - Book an appointment

    Scenario: CH_KRS_001 Successfully validated the consultant availability for my postcode
      Given I am a patient wanting a "Knee replacement surgery"
      When I provide my location as "EC1N" and click on search
      And I select date as "Wednesday, October 11, 2023"
      Then I validate the time availability for the first available consultant in the list


    Scenario: CH_KRS_002 Successfully validated the unavailability of consultants for my postcode
      Given I am a patient wanting a "Knee replacement surgery"
      When I provide my location as "KW15" and click on search
      Then I validate the message of no available consultant nearby
  #      Expected: The system should gracefully display error / information of unavailability.
  #      Actual: The Webpage is hanging with the loader on the page, and message display is delayed.