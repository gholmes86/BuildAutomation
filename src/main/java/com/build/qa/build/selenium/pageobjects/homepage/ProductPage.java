package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ProductPage extends BasePage {

	private By addToCart=By.cssSelector("#configure-product-wrap");
	private By cart=By.xpath("//*[@class='header-cart-quantity']");

	public ProductPage(WebDriver driver) {
		super(driver, 35);
	}



	public void addToCart() {
		//inconsistent click added temporary workaround..hate sleeps
		waitForDuration(4000);
		elementIsClickable(addToCart);
		find(addToCart).click();
		elementIsPresent(By.xpath("//*[@class='icon text-primary pad-right-small']"));

	}

	public void verifyProductAdded()
	{
		elementIsVisible(cart);
		assertTrue(find(By.xpath("//*[@class='header-cart-quantity-amount js-cart-quantity'][contains(text(),'1')]")).isDisplayed());
		assertTrue(find(By.xpath("//*[contains(text(),'Product Added to Cart')]")).isDisplayed());
		assertTrue(find(By.xpath("//*[contains(@class,'alert-success')]")).isDisplayed());

	}

	public ShoppingCartPage clickCartIcon()
	{

		elementIsClickable(cart);
		find(cart).click();

		return new ShoppingCartPage(driver);

	}







}
