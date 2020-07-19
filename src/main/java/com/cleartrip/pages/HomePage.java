package com.cleartrip.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cleartrip.base.BaseTest;

public class HomePage extends BaseTest {

	@FindBy(xpath = "//div[@class='row content']//a[@href='/flights']")
	WebElement flightsLink;

	@FindBy(xpath = "//input[@id='RoundTrip']")
	WebElement roundTripOption;

	@FindBy(xpath = "//input[@id='FromTag']")
	WebElement fromTextBox;

	@FindBy(xpath = "//input[@id='ToTag']")
	WebElement toTextBox;

	@FindBy(xpath = "//input[@id='DepartDate']")
	WebElement departDateBox;

	// @FindBy(xpath = "//a[@class='ui-state-default ui-state-highlight ui-state-active ']")
	@FindBy(xpath = "/descendant::a[@class='ui-state-default '][1]")
	WebElement todaysDate;

	@FindBy(xpath = "//input[@id='ReturnDate']")
	WebElement returnDateBox;

	@FindBy(xpath = "/descendant::a[@class='ui-state-default '][10]")
	WebElement returnDate;

	@FindBy(xpath = "//input[@title='Search flights']")
	WebElement searchFlightBtn;

	public void selectFlightsFromSideMenu() {
		clickElement(flightsLink);
	}

	public void selectRoundTripOption() {
		clickElement(roundTripOption);
	}
	
	public void selectFromAndToPlaceOption() {
		sendKeys(fromTextBox, "Delhi");
		sendKeys(toTextBox, "Mumbai");
	}
	
	public void selectFromAndToDateOption() {
		clickElement(departDateBox);
		clickElement(todaysDate);
		clickElement(returnDateBox);
		clickElement(returnDate);
	}

	public ResultPage searchFlights() {
		scrollTillElement(searchFlightBtn);
		clickElement(searchFlightBtn);
		return new ResultPage();

	}

}
