package org.CircleHealth.pages;


import org.CircleHealth.pages.commons.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AppointmentPage {

    private WebDriver driver;

    private By treatment = By.id("treatment");
    private By listOfTreatments = By.xpath("//*[@class='f-dropdown']/div/ul/li");
    private By location = By.id("location");
    private By searchButton = By.xpath("//*[@class='f-btn f-btn-xlarge f-btn-primary']");
    private By infoMessage = By.xpath("//*[@id=\"digital-doorway\"]/div[3]/div/div/p");
    private By dateOf10oct23 = By.xpath("//*[@id='datepicker']//span[@aria-label='Tuesday, October 10, 2023']");
    private By dateOfChoice(String dateOfTreatment){
        By dateOfChoice = By.xpath("//*[@id='datepicker']//span[@aria-label='"+dateOfTreatment+"']");
        return dateOfChoice;
    }

    private By preferredTime = By.id("preferredTime");

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void selectTreatment(String treatmentName) throws Exception {
        driver.findElement(treatment).sendKeys(treatmentName);
        WebElement TreatmentDropdown=driver.findElement(listOfTreatments).findElement(By.xpath("//li[@data-value='"+treatmentName+"']"));
        ExplicitWait.waitFor(driver,TreatmentDropdown);
        TreatmentDropdown.click();
        //((RemoteWebDriver) driver).executeScript("arguments[0].click();", TreatmentDropdown);
        /*Thread.sleep(5000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();*/
    }

    public void selectLocationByPostcode(String locationByPostcode) throws InterruptedException {
        driver.findElement(location).sendKeys(locationByPostcode);
        Thread.sleep(500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void selectLocationByTown(String locationByTown){
        //to validate other scenarios
    }

    public void clickOnSearchButton() throws Exception {
        ExplicitWait.waitFor(driver,driver.findElement(searchButton));
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", driver.findElement(searchButton));
    }

    /*public void selectDate() throws Exception {
        ExplicitWait.waitFor(driver,driver.findElement(dateOf10oct23));
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", driver.findElement(dateOf10oct23));
    }*/

    public void dateSelection(String dateOfTreatment) throws Exception {
        WebElement date = driver.findElement(dateOfChoice(dateOfTreatment));
        ExplicitWait.waitFor(driver,date);
        ((RemoteWebDriver) driver).executeScript("arguments[0].click();", date);
    }

    public void selectPreferredTime(String preferredTimeSelected){
        //WebElement preferredTimeDropdown = driver.findElement(preferredTime);
        Select select = new Select(driver.findElement(preferredTime));
        select.selectByValue(preferredTimeSelected);
    }

    public int validateAvailabilityOfFirstConsultant(){
        List<WebElement> totalNumberOfConsultantAvailable = driver.findElements(By.xpath("//*[@class='consultant-results__content']/ul/li"));
        int availableConsultantIndex = 1;
        for(int i=1;i<=totalNumberOfConsultantAvailable.size();i++){
            WebElement isConsultantAvailable = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li["+i+"]/div"));
            if(isConsultantAvailable.isDisplayed()){
                availableConsultantIndex = i;
                break;
            }else continue;
        }

        WebElement firstAvailableConsultant = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li["+availableConsultantIndex+"]"));
        List<WebElement> listOfAvailableTimes= firstAvailableConsultant.findElements(By.xpath("//li[1]//div[@class='consultant-result-card__times-content']/a"));
        return listOfAvailableTimes.size();
    }

}
