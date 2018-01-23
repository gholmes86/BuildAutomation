package com.build.qa.build.selenium.Androidpageobjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidSearchResultsProductPage {

    private final AppiumDriver driver;


    public AndroidSearchResultsProductPage(AppiumDriver driver) {
        this.driver = driver;

    }

    public void verifiyProductSearch(String text) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@index='0']"))).click();
        driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).getText().contains(text);


    }
}