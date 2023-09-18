package org.CircleHealth.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public AppointmentPage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void selectTreatment(String treatmentName) throws InterruptedException {
        driver.findElement(treatment).sendKeys(treatmentName);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(listOfTreatments).findElement(By.xpath("//li[@data-value='"+treatmentName+"']")))).click();
/*
        Thread.sleep(1000);
        driver.findElement(listOfTreatments).findElement(By.xpath("//li[@data-value='"+treatmentName+"']")).click();
*/

    }

    public void selectLocationByPostcode(String locationByPostcode) throws InterruptedException {
        driver.findElement(location).sendKeys(locationByPostcode);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Thread.sleep(500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public void selectLocationByTown(String locationByTown){}

    public void clickOnSearchButton() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(searchButton).click();
    }

    public void selectDate() throws InterruptedException {//String date
        Thread.sleep(1000);
        driver.findElement(dateOf10oct23).click();
    }

    public void selectPreferredTime(String preferredTimeSelected){
        WebElement preferredTimeDropdown = driver.findElement(preferredTime);
        Select select = new Select(preferredTimeDropdown);
        select.selectByValue(preferredTimeSelected);

    }

    public int validateAvailabilityOfFirstConsultant(){
        WebElement FirstConsultant = driver.findElement(By.xpath("//*[@class='consultant-results__content']/ul/li[1]"));
        //System.out.println(FirstConsultant.findElement(By.className("consultant-result-card__name")).getText());
        //System.out.println(FirstConsultant.findElement(By.className("consultant-result-card__date")).getText());

        List<WebElement> listOfAvailableTimes= FirstConsultant.findElements(By.xpath("//li[1]//div[@class='consultant-result-card__times-content']/a"));
        //System.out.println("Consultant's availability: ");

        return listOfAvailableTimes.size();

        /*for(int i=1;i<=listOfAvailableTimes.size();i++){
            System.out.println(FirstConsultant.findElement(By.xpath("//li[1]//div[@class='consultant-result-card__times-content']/a["+i+"]/span[1]")).getText());
        }*/


    }




}
