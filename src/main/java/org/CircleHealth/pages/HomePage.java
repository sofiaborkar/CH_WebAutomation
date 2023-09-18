package org.CircleHealth.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public AppointmentPage clickOnBookAnAppointmentButtom() throws InterruptedException {
        driver.findElement(bookAnAppointment).click();
        Thread.sleep(5000);

        return new AppointmentPage(driver);
    }

}
