package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ShoppingCartPage extends BasePage {


	private By cartToolsBtn=By.xpath("//*[@class='btn-standard btn-secondary ch-tools-button'][contains(text(),'Cart Tools')]");
	private By emailName=By.id("yourName");
	private By emailAddress=By.id("yourEmail");
	private By recepientName=By.id("recipientName");
	private By recepientEmailAddress=By.id("recipientEmail");
	private By message=By.id("quoteMessage");
	private By emailCartBtn=By.xpath("//*[contains(@class,'js-email-cart-submit-button')]");


	public ShoppingCartPage(WebDriver driver) {
		super(driver, 35);
	}



	public void addCartToEmail() {
		driver.navigate().refresh();
		waitForDuration(4000);
		//noticeable issue where cart tools button doesn't consistently display
		if(elementExists(cartToolsBtn)) {
			elementIsClickable(cartToolsBtn);
			find(cartToolsBtn).click();
			elementIsClickable(By.xpath("//*[@class='btn-standard btn-secondary js-email-cart-button'][contains(@title,'Email your cart')]"));
			find(By.xpath("//*[@class='btn-standard btn-secondary js-email-cart-button'][contains(@title,'Email your cart')]")).click();
		}
		else if(elementExists(By.xpath("//*[@class='cart-options pad-top']")))
		{
		elementIsClickable(By.xpath("//*[@class='btn-standard btn-secondary btn-email js-email-cart-button'][contains(@title,'Email your cart')]"));
		find(By.xpath("//*[@class='btn-standard btn-secondary btn-email js-email-cart-button'][contains(@title,'Email your cart')]")).click();
	}}


	public void enterEmailName(String text) {

		enterText(emailName,text);
	}
	public void enterEmailAddress(String text) {
		enterText(emailAddress,text);
	}


	public void enterRecepientName(String text) {
		enterText(recepientName,text);
	}

	public void enterRecepientEmailAddress(String text) {
		enterText(recepientEmailAddress,text);
	}

	public void enterMessage(String text) {
		enterText(message,text);
	}

	public void clickEmailCartBtn() {
		find(emailCartBtn).click();
	}

	public void verifyEmailSentMessage(String text) {
		elementIsPresent(By.name("zip"));
		waitForDuration(2000);
		assertTrue(find(By.xpath("//*[contains(text(),'"+text+"')]")).isDisplayed());
	}








}
