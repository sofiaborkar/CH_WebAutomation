package org.CircleHealth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    private By acceptAllCookie = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private By bookAnAppointment = By.xpath("//*[@class='f-btn f-btn-tertiary f-btn--no-shadow']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void clickOnAcceptAllCookie(){
        driver.findElement(acceptAllCookie).click();
    }

    public AppointmentPage clickOnBookAnAppointmentButtom() throws Exception {
        driver.findElement(bookAnAppointment).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//input"))));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id=\"digital-doorway\"]/div[1]/div"))));
        return new AppointmentPage(driver);
    }

}
