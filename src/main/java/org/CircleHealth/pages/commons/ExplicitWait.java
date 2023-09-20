package org.CircleHealth.pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {

    public static WebElement waitFor(WebDriver driver, WebElement webElement) throws Exception {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }catch (Exception e){
            throw new Exception("Element not found - "+ e.getMessage());
        }
        return webElement;
    }

public static void waitForInvisibilityOf(WebDriver driver, WebElement webElement) throws Exception {
    try{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }catch (Exception e){
        throw new Exception("--------Webpage hanged--------"+ e.getMessage());
    }

}







}
