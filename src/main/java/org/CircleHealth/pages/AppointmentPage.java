package org.CircleHealth.pages;


import org.CircleHealth.pages.commons.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class AppointmentPage {

    private WebDriver driver;

    private By treatment = By.id("treatment");
    private By listOfTreatments = By.xpath("//*[@class='f-dropdown']/div/ul/li");
    private By location = By.id("location");
    private By searchButton = By.xpath("//*[@class='f-btn f-btn-xlarge f-btn-primary']");
    private By messageOnSearch = By.xpath("//*[@id='digital-doorway']/div[3]/div/div/p");
    private By loader = By.xpath("//*[@id=\"digital-doorway\"]/div[1]/div");
    private By preferredTime = By.id("preferredTime");
    private By dateOfChoice(String dateOfTreatment){
        By dateOfChoice = By.xpath("//*[@id='datepicker']//span[@aria-label='"+dateOfTreatment+"']");
        return dateOfChoice;
    }

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void selectTreatment(String treatmentName) throws Exception {
        driver.findElement(treatment).sendKeys(treatmentName);
        WebElement TreatmentDropdown=driver.findElement(listOfTreatments).findElement(By.xpath("//li[@data-value='"+treatmentName+"']"));
        ExplicitWait.waitFor(driver,TreatmentDropdown);
        TreatmentDropdown.click();
    }

    public void selectLocationByPostcode(String locationByPostcode) throws InterruptedException {
        driver.findElement(location).sendKeys(locationByPostcode);
        Thread.sleep(500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void selectLocationByTown(String locationByTown){
        //to validate other search scenarios
    }

    public void clickOnSearchButton() throws Exception {
        ExplicitWait.waitFor(driver,driver.findElement(searchButton));
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", driver.findElement(searchButton));
    }

    public void dateSelection(String dateOfTreatment) throws Exception {
        WebElement date = driver.findElement(dateOfChoice(dateOfTreatment));
        ExplicitWait.waitFor(driver,date);
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", date);
    }

    public int listOfTimesAvailabilityOfFirstConsultantOnTimeFilter(){
        List<WebElement> totalNumberOfConsultantAvailable = driver.findElements(By.xpath("//*[@class='consultant-results__content']/ul/li"));
        int availableConsultantIndex = 1;
        //COMMENT: below is the code for preferred time filter when selected
            /*for(int i=1;i<=totalNumberOfConsultantAvailable.size();i++){
                WebElement isConsultantAvailable = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li["+i+"]/div"));
                if(isConsultantAvailable.isDisplayed()){
                    availableConsultantIndex = i;
                    break;
                }else continue;
            }*/

        WebElement firstAvailableConsultant = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li["+availableConsultantIndex+"]"));
        List<WebElement> listOfAvailableTimes= firstAvailableConsultant.findElements(By.xpath("//li[1]//div[@class='consultant-result-card__times-content']/a"));
        return listOfAvailableTimes.size();
    }

    public boolean validateMessageOfUnavailability() throws Exception {
        ExplicitWait.waitForInvisibilityOf(driver, driver.findElement(loader));
        if(driver.findElement(messageOnSearch).getText().contains("Sorry, we don't have any appointments available online that match your exact search location")){
            return true;
        }else return false;
    }

}
