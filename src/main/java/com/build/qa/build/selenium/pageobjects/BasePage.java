package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.framework.BaseFramework;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

//import com.oracle.tools.packager.Log;

public abstract class BasePage extends BaseFramework {

    protected final int waitTimeOutSeconds;


    public BasePage(WebDriver driver, int waitTimeOutSeconds) {
        this.driver = driver;
        this.waitTimeOutSeconds = waitTimeOutSeconds;
        PageFactory.initElements(driver, this);
    }


    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * wait until condition is true or timeout kicks in
     */
    protected <V> V wait_until_true_or_timeout(ExpectedCondition<V> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, waitTimeOutSeconds)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(isTrue);
    }

    public void elementIsVisible(By e) {
        wait_until_true_or_timeout(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void elementIsPresent(By e) {
        wait_until_true_or_timeout(ExpectedConditions.presenceOfElementLocated(e));
    }

    public void elementIsClickable(By e) {
        wait_until_true_or_timeout(ExpectedConditions.elementToBeClickable(e));
    }

    public void enterText(By by, String text) {
        waitUntilElementIsClickable(by).sendKeys(text);
    }

    public WebElement waitUntilElementIsClickable(By by) {
        return new WebDriverWait(driver, waitTimeOutSeconds).until(ExpectedConditions.elementToBeClickable(by));
    }

    public boolean elementExists(By by) {
        try {
            find(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public void waitForDuration(int n)
    {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




}




