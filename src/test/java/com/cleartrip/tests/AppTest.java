package com.cleartrip.tests;

import org.testng.annotations.Test;

import com.cleartrip.base.*;
import com.cleartrip.pages.HomePage;
import com.cleartrip.pages.ResultPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;

public class AppTest extends BaseTest {

	HomePage homePage;
	ResultPage resultPage;

	InputStream datais;
	JSONObject data;

	@BeforeMethod
	public void beforeMethod(Method m) {
		homePage = new HomePage();
		System.out.println("\n" + "**** Starting test:" + m.getName() + "*****" + "\n");
	}

	@AfterMethod
	public void afterMethod() {

	}

	private List<Integer> findPricesLessThan(List<Integer> prices, Integer maximumPrice) {
		List<Integer> result = new ArrayList<>();
		for (Integer price : prices) {
			if (price < maximumPrice) {
				result.add(price);
			}
		}
		return result;
	}

	@Test(priority = 1)
	public void selectFlights() {
		homePage.selectFlightsFromSideMenu();
	}

	@Test(priority = 2)
	public void selectRoundTrip() {
		homePage.selectRoundTripOption();
	}

	@Test(priority = 3)
	public void selectFromAndToPlace() {
		homePage.selectFromAndToPlaceOption();
	}

	@Test(priority = 4)
	public void selectFromAndToDate() {
		homePage.selectFromAndToDateOption();
	}

	@Test(priority = 5)
	public void searchFlight() {
		resultPage = homePage.searchFlights();
	}

	@Test(priority = 6)
	public void printDepartingFlightsAsPerPrice() {

		List<Integer> toPrices = resultPage.listOfToFlightPrices();
		List<Integer> resultTo = findPricesLessThan(toPrices, 5000);
		System.out
				.println("\nTotal departing flights are :" + toPrices.size() + "\n" + " Departing flights prices are :"
						+ toPrices + "\n" + "Number of flights having price less than Rs. 5000 are: " + resultTo.size()
						+ "\n" + "Departing flights prices which has price less than Rs. 5000 are: " + resultTo);
	}

	@Test(priority = 7)
	public void printReturnFlightsAsPerPrice() {

		List<Integer> fromPrices = resultPage.listOfReturnFlightPrices();
		List<Integer> resultFrom = findPricesLessThan(fromPrices, 5000);
		System.out.println(
				"\nTotal Return  flights are :" + fromPrices.size() + "\n" + " Return flights prices are :" + fromPrices
						+ "\n" + "Number of return flights having price less than Rs. 5000 are: " + resultFrom.size()
						+ "\n" + "Departing flights prices which has price less than Rs. 5000 are: " + resultFrom);
	}

	@Test(priority = 8)
	public void selectStopFilter() {
		resultPage.applyNonStopFilter();
		resultPage.applyOneStopFilter();
	}

	@Test(priority = 9)
	public void printDepartingFlightsAsPerNewPrice() {

		List<Integer> toPrices1 = resultPage.listOfToFlightPrices();
		List<Integer> toResult1 = findPricesLessThan(toPrices1, 7000);
		System.out.println("\nTotal departing flights are :" + toPrices1.size() + "\n"
				+ " Departing flights prices are :" + toPrices1 + "\n"
				+ "Number of flights having price less than Rs. 7000 are: " + toResult1.size() + "\n"
				+ "Departing flights prices which has price less than Rs. 7000 are: " + toResult1);

	}

	@Test(priority = 10)
	public void printReurnFlightsAsPerNewPrice() {

		List<Integer> fromPrices1 = resultPage.listOfReturnFlightPrices();
		List<Integer> resultFrom1 = findPricesLessThan(fromPrices1, 7000);
		System.out.println("\nTotal Return  flights are :" + fromPrices1.size() + "\n" + " Return flights prices are :"
				+ fromPrices1 + "\n" + "Number of return flights having price less than Rs. 7000 are: "
				+ resultFrom1.size() + "\n" + "Departing flights prices which has price less than Rs. 7000 are: "
				+ resultFrom1);
	}

	@Test(priority = 11)
	public void selectFlightToandFro() {

		resultPage.selectToFlightByIndex(1);
		resultPage.selectReturnFlightByIndex(4);
	}

	@Test(priority = 12)
	public void validatePrice() {

		Integer toPrice = resultPage.getToFlightPrice();
		Integer returnPrice = resultPage.getReturnFlightPrice();
		System.out.println("To Flight price is: " + toPrice);
		System.out.println("Return Flight price is: " + returnPrice);
		
		Integer total = toPrice + returnPrice;
		System.out.println("Total price is: " + total);
		
		Integer totalPriceOnPage = resultPage.getTotalPriceOnPage();
		System.out.println("Total price on page is: " + totalPriceOnPage);
		
		Assert.assertEquals(totalPriceOnPage, total);

	}

}
