package com.cleartrip.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.cleartrip.base.BaseTest;

public class ResultPage extends BaseTest {
	
	Actions actions;

	@FindAll(value = { @FindBy(xpath = "//div[@data-test-attrib='onward-view']/div/div"),
			@FindBy(xpath = "//div[@data-fromto][1]//ul[@class='listView flights']/li") })
	List<WebElement> toFlights;

	@FindAll(value = { @FindBy(xpath = "//div[@data-test-attrib='return-view']/div/div"),
			@FindBy(xpath = "//div[@data-fromto][2]//ul[@class='listView flights']/li") })
	List<WebElement> returnFlights;

	@FindAll(value = {
			@FindBy(xpath = "//div[@data-test-attrib='onward-view']/div/div//div[contains(@class, 'rt-tuple-container--selected')]"), 
			@FindBy(xpath = "//div[@data-fromto][1]//ul[@class='listView flights']//div[contains(@class,'selected')]")
	})
	List<WebElement> selectedToFlight;

	@FindAll(value = {
			@FindBy(xpath = "//div[@data-test-attrib='return-view']/div/div//div[contains(@class, 'rt-tuple-container--selected')]"), 
			@FindBy(xpath = "//div[@data-fromto][2]//ul[@class='listView flights']//div[contains(@class,'selected')]")
	})
	List<WebElement> selectedReturnFlight;
	
	@FindAll(value = {
			@FindBy(xpath = "//*[@class='c-neutral-900 mx-4  fw-700 flex flex-right fs-7']"), 
			@FindBy(xpath = "//h2[@class='totalAmount']")
	})
	List<WebElement> totalPrice;
	
	@FindAll(value = {
	@FindBy(xpath = "//p[contains(text(), 'Non-stop')]"),
	@FindBy(xpath = "//*[contains(text(), '0 stop')]")
	})
	List<WebElement> nonStop;

	@FindBy(xpath = "//*[contains(text(), '1 stop')]")
	WebElement oneStop;

	public List<Integer> listOfToFlightPrices() {
		return findPriceFromFlightList(toFlights);
	}
	

	public List<Integer> listOfReturnFlightPrices() {
		return findPriceFromFlightList(returnFlights);
	}

	private List<Integer> findPriceFromFlightList(List<WebElement> toOrReturnflighList) {
		List<Integer> result = new ArrayList<>();
		for (WebElement webElement : toOrReturnflighList) {
			result.add(findPriceFromWebElement(webElement, false));
		}
		return result;
	}

	private Integer findPriceFromWebElement(WebElement webElement, boolean selectedPrice) {
		String price;
		try {
			if (selectedPrice) {
				price = webElement.findElement(By.xpath("div/div[3]/div[2]")).getText();
			} else {
				price = webElement.findElement(By.xpath("div/div/div[3]/div[2]")).getText();
			}
			return findPriceFromString(price);
		} catch (Exception e) {
		}
		try {
			if (selectedPrice) {
				price = webElement.getAttribute("data-price");
			} else {
				price = webElement.findElement(By.xpath("div")).getAttribute("data-price");
			}
			return findPriceFromString(price);
		} catch (Exception e) {
		}
		return null;
	}

	private Integer findPriceFromString(String str) {
		Integer price = 0;
		try {
			return Integer.valueOf(str.replaceAll("[^0-9]", ""));
		} catch (Exception e) {
		}
		return price;
	}

	public void applyNonStopFilter() {
		clickElement(nonStop.get(0));
	}

	public void applyOneStopFilter() {
		clickElement(oneStop);
	}

	
	public boolean selectToFlightByIndex(int index) {
		return selectFlight(toFlights, index);
	}

	public boolean selectReturnFlightByIndex(int index) {
		return selectFlight(returnFlights, index);
	}
	
	private boolean selectFlight(List<WebElement> flights, int index) {
		if(flights.size() > index) {
			System.out.println(flights.size());
			WebElement flight = flights.get(index);
			scrollTillElement(flight);
			clickElement(flight);
			
			
			return true;
		}
		return false;
	}
	
	public Integer getToFlightPrice() {
		return findPriceFromWebElement(selectedToFlight.get(0), true);
	}

	public Integer getReturnFlightPrice() {
		return findPriceFromWebElement(selectedReturnFlight.get(0), true);
	}

	public Integer getTotalPriceOnPage() {
		return findPriceFromString(totalPrice.get(0).getText());
	}
}
