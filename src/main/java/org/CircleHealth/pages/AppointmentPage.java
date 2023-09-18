package org.CircleHealth.pages;


import org.CircleHealth.pages.commons.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AppointmentPage {

    private WebDriver driver;

    private By treatment = By.id("treatment");
    private By listOfTreatments = By.xpath("//*[@class='f-dropdown']/div/ul/li");
    private By location = By.id("location");
    private By searchButton = By.xpath("//*[@class='f-btn f-btn-xlarge f-btn-primary']");
    private By infoMessage = By.xpath("//*[@id=\"digital-doorway\"]/div[3]/div/div/p");
    private By dateOf10oct23 = By.xpath("//*[@id='datepicker']//span[@aria-label='Tuesday, October 10, 2023']");
    private By preferredTime = By.id("preferredTime");
    String treatmentName;

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void selectTreatment(String treatmentName) throws Exception {
        //ExplicitWait.waitFor(driver,driver.findElement(treatment));
        driver.findElement(treatment).sendKeys(treatmentName);
        WebElement TreatmentDropdown=driver.findElement(listOfTreatments).findElement(By.xpath("//li[@data-value='"+treatmentName+"']"));
        ExplicitWait.waitFor(driver,TreatmentDropdown);
        TreatmentDropdown.click();
        //Thread.sleep(500);
      //  Actions action = new Actions(driver);
      //  action.sendKeys(Keys.ENTER).perform();
    }

    public void selectLocationByPostcode(String locationByPostcode) throws InterruptedException {
        driver.findElement(location).sendKeys(locationByPostcode);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Thread.sleep(500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void selectLocationByTown(String locationByTown){
        //to validate other scenarios
    }

    public void clickOnSearchButton() throws Exception {
        ExplicitWait.waitFor(driver,driver.findElement(searchButton));
        driver.findElement(searchButton).click();
        Thread.sleep(5000);
    }

    public void selectDate() throws Exception {
        ExplicitWait.waitFor(driver,driver.findElement(dateOf10oct23));
        driver.findElement(dateOf10oct23).click();
    }

    public void selectPreferredTime(String preferredTimeSelected){
        WebElement preferredTimeDropdown = driver.findElement(preferredTime);
        Select select = new Select(preferredTimeDropdown);
        select.selectByValue(preferredTimeSelected);
    }

    public int validateAvailabilityOfFirstConsultant(){
        WebElement FirstConsultant = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li[1]"));
        List<WebElement> listOfAvailableTimes= FirstConsultant.findElements(By.xpath("//li[1]//div[@class='consultant-result-card__times-content']/a"));
        return listOfAvailableTimes.size();
    }

}
