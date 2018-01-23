package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.Androidpageobjects.AndroidHomePage;
import com.build.qa.build.selenium.Androidpageobjects.AndroidSearchResultsProductPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class AndroidAppiumTest {
    private AppiumDriver driver;;

    @Before
    public void setUp() throws Exception {



        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("--full-reset", true);
        capabilities.setCapability("avd","Nexus_5X_API_27");
        capabilities.setCapability("devicereadytimeout", "10000");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformName", "Android");
       // capabilities.setCapability("platformVersion", "4.4");
      //  capabilities.setCapability("appPackage", "com.vividseats.android");
      //  capabilities.setCapability("appActivity", "views.activities.MainActivity");//views.activities.MainActivity
         capabilities.setCapability("app", "/Users/gabrielholmes/Documents/build/Build.com.apk");
        capabilities.setCapability("locationServicesEnabled", "true");
        capabilities.setCapability("prelaunch", "true");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);



    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * Search for the Quoizel MY1613 from the search bar
     * @assert: That the product page we land on is what is expected by checking the product title
     * @difficulty Easy
     */

    @Test
    public void addProductToCartFromCategoryDrop() throws InterruptedException {
        AndroidHomePage homePage = new AndroidHomePage(driver);
        homePage.clickloginBtn();
        homePage.enterEmail("gholmes86@yahoo.com");
        homePage.enterPassword("Pass7word1");
        homePage.enterBtn();
        homePage.clickStartShoppingBtn();
        homePage.enterSearch("Quoizel MY1613");
        homePage.enterBtn();
        AndroidSearchResultsProductPage androidSearchResultsProductPage = new AndroidSearchResultsProductPage(driver);
        androidSearchResultsProductPage.verifiyProductSearch("Quoizel MY1613");
        Thread.sleep(2000);



    }




}