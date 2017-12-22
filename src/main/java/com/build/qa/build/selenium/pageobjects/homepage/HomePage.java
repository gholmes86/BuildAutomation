package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class HomePage extends BasePage {
	
	private By buildThemeBody;
	private By newsLetterEmail=By.id("newsletterPopupEmail");
	private By submitBtn=By.id("submitEmailModal");
	private By searchBar=By.id("search_txt");
	private By searchIcon=By.xpath("//*[@class='button-primary search-site-search']");
	
	public HomePage(WebDriver driver) {
		super(driver, 35);
		buildThemeBody = By.cssSelector("body.build-theme");
	}

	public void enterEmail(String text)
	{	elementIsClickable(newsLetterEmail);
		find(newsLetterEmail).click();
		enterText(newsLetterEmail,text);

	}

	public void submitEmail()
	{	elementIsClickable(submitBtn);
		find(submitBtn).click();
	}

	public void BuildTheme()
	{
		assertTrue(find(buildThemeBody).isDisplayed());
	}

	public SearchResultsProductPage searchProduct(String text)
	{
		enterText(searchBar,text);
		find(searchBar).sendKeys(Keys.ENTER);
		return new SearchResultsProductPage(driver);


	}

	public SearchResultsProductPage navigateToProduct(String url3)
	{
		String url= driver.getCurrentUrl();
		driver.navigate().to(url3);
		return new SearchResultsProductPage(driver);


	}



	public boolean onBuildTheme() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}
}
