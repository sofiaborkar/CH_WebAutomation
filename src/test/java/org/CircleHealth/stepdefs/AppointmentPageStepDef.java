package org.CircleHealth.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.CircleHealth.pages.AppointmentPage;
import org.CircleHealth.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Locale;

public class AppointmentPageStepDef{

    WebDriver driver;
    ChromeOptions opt;
    AppointmentPage appointmentPage;

    @Before
    public void setup() {

        String browserName = System.getProperty("browserName").toLowerCase(Locale.ROOT);
        if(browserName.equals("firefox")){
            driver = new FirefoxDriver();
        }else if(browserName.equals("chrome")){
            /*opt = new ChromeOptions();
            opt.addArguments("incognito");
            driver = new ChromeDriver(opt);*/
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the Circle Health home page and clicking on - Book an appointment")
    public void iAmOnTheCircleHealthHomePageAndClickOnBookAnAppointment() throws Exception {
        driver.get("https://www.circlehealthgroup.co.uk/");
        HomePage homePage = new HomePage(driver);
        homePage.clickOnAcceptAllCookie();
        this.appointmentPage = homePage.clickOnBookAnAppointmentButtom();
    }

    @Given("I am a patient wanting a {string}")
    public void iAmAPatientWantingA(String treatmentName) throws Exception {
        appointmentPage.selectTreatment(treatmentName);
    }

    @When("I provide my location as {string}")
    public void iProvideMyLocationDetails(String locationPostcode) throws InterruptedException {
        appointmentPage.selectLocationByPostcode(locationPostcode);
    }

    @And("I select date as {string}")
    public void iSelectDateAs(String dateOfTreatment) throws Exception {
        appointmentPage.dateSelection(dateOfTreatment);
    }

    @And("I validate the time availability for the first available consultant in the list")
    public void iValidateTheTimeAvailabilityForTheFirstAvailableConsultantInTheList() {
        int i = appointmentPage.listOfTimesAvailabilityOfFirstConsultantOnTimeFilter();
        boolean testCondition;
        if (i > 0) {
            testCondition = true;
        } else {
            testCondition = false;
        }
        Assert.assertTrue(testCondition);
    }

    @When("I provide my location as {string} and click on search")
    public void iProvideMyLocationAsAndClickOnSearch(String locationPostcode) throws Exception {
        appointmentPage.selectLocationByPostcode(locationPostcode);
        appointmentPage.clickOnSearchButton();
    }

    @Then("I validate the message of no available consultant nearby")
    public void iValidateTheMessageOfNoAvailableConsultantNearby() throws Exception {
        Assert.assertTrue(appointmentPage.validateMessageOfUnavailability(),"No unavailability message received OR Wrong message.");
    }
}
