package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.pageobjects.homepage.ProductPage;
import com.build.qa.build.selenium.pageobjects.homepage.SearchResultsProductPage;
import com.build.qa.build.selenium.pageobjects.homepage.ShoppingCartPage;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;

public class BuildTest extends BaseFramework { 
	
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver);
		homePage.enterEmail("galexholmes@gmail.com");
		homePage.submitEmail();
		homePage.BuildTheme();
	}
	
	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver);
		homePage.enterEmail("galexholmes@gmail.com");
		homePage.submitEmail();
		homePage.BuildTheme();
		homePage.searchProduct("Quoizel MY1613");
		SearchResultsProductPage searchResultsProductPage= new SearchResultsProductPage(driver);
		searchResultsProductPage.verifyHeader("Quoizel MY1613");
	}
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver);
		homePage.enterEmail("galexholmes@gmail.com");
		homePage.submitEmail();
		homePage.navigateToProduct("https://www.build.com/bathroom-sinks/c108504");
		SearchResultsProductPage searchResultsProductPage= new SearchResultsProductPage(driver);
		searchResultsProductPage.addProduct();
		ProductPage productPage= new ProductPage(driver);
		productPage.addToCart();
		productPage.verifyProductAdded();


	}
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver);
		homePage.enterEmail("galexholmes@gmail.com");
		homePage.submitEmail();
		homePage.navigateToProduct("https://www.build.com/bathroom-sinks/c108504");
		SearchResultsProductPage searchResultsProductPage= new SearchResultsProductPage(driver);
		searchResultsProductPage.addProduct();
		ProductPage productPage= new ProductPage(driver);
		productPage.addToCart();
		productPage.verifyProductAdded();
		productPage.clickCartIcon();
		ShoppingCartPage shoppingCartPage= new ShoppingCartPage(driver);
		shoppingCartPage.addCartToEmail();
		shoppingCartPage.enterEmailName("Gabriel Holmes");
		shoppingCartPage.enterEmailAddress("galexholmes@gmail.com");
		shoppingCartPage.enterRecepientName("JGilmore");
		shoppingCartPage.enterRecepientEmailAddress("jgilmore+SeleniumTest@build.com");
		shoppingCartPage.enterMessage("This is Gabriel Holmes, sending you a cart from my automation!");
		shoppingCartPage.clickEmailCartBtn();
		shoppingCartPage.verifyEmailSentMessage("Cart Sent! The cart has been submitted to the recipient via email.");


	}
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver);
		homePage.enterEmail("galexholmes@gmail.com");
		homePage.submitEmail();
		homePage.navigateToProduct("https://www.build.com/bathroom-sink-faucets/c108503");
		SearchResultsProductPage searchResultsProductPage= new SearchResultsProductPage(driver);
		searchResultsProductPage.getResultsCount();
		searchResultsProductPage.clickFilter("Delta");
		searchResultsProductPage.getFilteredCount();
		searchResultsProductPage.filteredResults();
		searchResultsProductPage.verifyFilteredResults(684);
		searchResultsProductPage.clickFilter("Kohler");
		searchResultsProductPage.getFilteredCount();
		searchResultsProductPage.getResultsCount();
		searchResultsProductPage.filteredResults();
		searchResultsProductPage.verifyFilteredResults(892);




	}
}
