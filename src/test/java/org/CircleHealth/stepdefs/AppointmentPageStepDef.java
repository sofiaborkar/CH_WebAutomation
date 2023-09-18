package org.CircleHealth.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.CircleHealth.pages.AppointmentPage;
import org.CircleHealth.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class AppointmentPageStepDef {

    WebDriver driver;
    AppointmentPage appointmentPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the Circle Health home page and clicking on - Book an appointment")
    public void iAmOnTheCircleHealthHomePageAndClickOnBookAnAppointment() {
        //System.out.println("Test........check");
        driver.get("https://www.circlehealthgroup.co.uk/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAcceptAllCookie();
        this.appointmentPage = homePage.clickOnBookAnAppointmentButtom();

    }

    @Given("I am a patient wanting a {string}")
    public void iAmAPatientWantingA(String treatmentName) throws InterruptedException {
        appointmentPage.selectTreatment(treatmentName);
    }

    @When("I provide my location as {string}")
    public void iProvideMyLocationDetails(String locationPostcode) throws InterruptedException {
        appointmentPage.selectLocationByPostcode(locationPostcode);
    }

    @And("I select my date of choice")
    public void iSelectMyDateOfChoice() throws InterruptedException {
        appointmentPage.selectDate();
    }

    @And("I select my availability as {}")
    public void iSelectMyPreferredTimes(String preferredTimes) {
        appointmentPage.selectPreferredTime(preferredTimes);
    }

    @And("I validate the time availability for the first available consultant in the list")
    public void iValidateTheTimeAvailabilityForTheFirstAvailableConsultantInTheList() {
        int i = appointmentPage.validateAvailabilityOfFirstConsultant();
        boolean testCondition;
        if (i > 0) {
            testCondition = true;
        } else {
            testCondition = false;
        }
        Assert.assertTrue(testCondition);
    }

    @When("I provide my location as {string} and click on search")
    public void iProvideMyLocationAsAndClickOnSearch(String locationPostcode) throws InterruptedException {
        appointmentPage.selectLocationByPostcode(locationPostcode);
        appointmentPage.clickOnSearchButton();
    }


}
