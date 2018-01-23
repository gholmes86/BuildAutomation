package com.build.qa.build.selenium.Androidpageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidHomePage {

    private final AppiumDriver driver;

  //homepage


    public AndroidHomePage(AppiumDriver driver) {
        this.driver = driver;

    }

    public void enterEmail(String email) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Email Address']"))).click();
        driver.findElement(By.xpath("//*[@content-desc='Email Address']")).sendKeys(email);
    }



    public void enterPassword(String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Password']"))).click();
        driver.findElement(By.xpath("//*[@content-desc='Password']")).sendKeys(password);
    }

    public void clickloginBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Login Button']"))).click();
    }

    public void clickStartShoppingBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Start Shopping']"))).click();
    }

    public void enterBtn() throws InterruptedException {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.ENTER);

    }

    public AndroidSearchResultsProductPage enterSearch(String text) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 35);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Header Search Input']"))).click();
        driver.findElement(By.xpath("//*[@content-desc='Header Search Input']")).sendKeys(text);
        return new AndroidSearchResultsProductPage(driver);

    }

}