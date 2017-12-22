package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultsProductPage extends BasePage {

	public int filteredCount;

	private By productTitle=By.id("heading");

	public SearchResultsProductPage(WebDriver driver) {
		super(driver, 35);
	}

	public void verifyHeader(String text)
	{
		elementIsVisible(productTitle);
		assertTrue(find(productTitle).getText().contains(text));

	}

	public ProductPage addProduct()
	{

		elementIsClickable(By.xpath("//*[@class='product-tile-image margin-top-sm']"));
		findElements(By.xpath("//*[@class='product-tile-image margin-top-sm']")).get(1).click();
		return new ProductPage(driver);
	}

	public int getResultsCount() {
		elementIsVisible(By.xpath("//*[@class='js-num-results']"));
		String temp = find(By.xpath("//*[@class='js-num-results']")).getText();
		temp = temp.replaceAll(",","");
		System.out.println("ResultsCount-" + temp);
		return Integer.valueOf(temp);
	}

	public int getFilteredCount() {
		elementIsVisible(By.xpath("//*[@class='js-num-results']"));
		String fil = find(By.xpath("//*[@class='js-num-results']")).getText();
		fil= fil.replaceAll(",","");
		System.out.println("FilteredCount-" + fil);
		return Integer.valueOf(fil);
	}
	public void clickFilter(String text) {

		elementIsClickable(By.xpath("//*[@class='sub-item qa-facetGroup-Brand-facetValue-"+text+"']"));
		find(By.xpath("//*[@class='sub-item qa-facetGroup-Brand-facetValue-"+text+"']")).click();
		waitForDuration(5000);
		find(By.xpath("//*[@class='sub-item qa-facetGroup-Brand-facetValue-"+text+"']")).isSelected();

	//	if(find(By.xpath("//*[@class='sub-item qa-facetGroup-Brand-facetValue-"+text+"']")).isEnabled())
//		{
	//		find(By.xpath("//*[@class='sub-item qa-facetGroup-Brand-facetValue-"+text+"']")).click();

//		}
//		else
//			System.out.print("Filter selected: "+text);

	}

	public void filteredResults()
	{
		getFilteredCount();
		assertNotEquals(getResultsCount(),filteredCount);

	}

	public void verifyFilteredResults(int number)
	{
		getFilteredCount();
		assertEquals(number,getFilteredCount());

	}






}
