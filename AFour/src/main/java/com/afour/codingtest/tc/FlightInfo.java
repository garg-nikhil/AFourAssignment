package com.afour.codingtest.tc;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import com.afour.codingtest.testbase.TestBase;

public class FlightInfo extends TestBase {
	public static Logger log = Logger.getLogger(TestBase.class);
	TestBase a = new TestBase();
	
	@Test
	public void checkFlightsSchedule() {
		String URL = a.getURL2();
		driver.get(URL);
		log.info("Launching the website: "+URL);

		// Create a HashMap
		HashMap<String, String> map = new HashMap<>();

		// Populate the HashMap
		map.put("Bengaluru", "data not available");
		map.put("Delhi", "data not available");
		map.put("Chandigarh", "data not available");
		map.put("Hyderabad", "data not available");
		map.put("Nagpur", "data not available");
		map.put("Kolkata", "data not available");

		int rowCount = driver
				.findElements(By.xpath(
						"//*[@class='row cnt-schedule-table']/table/tbody//tr[@class='hidden-xs hidden-sm ng-scope']"))
				.size();
		log.info("No of rows in the table: " + rowCount);

		for (int i = 1; i <= rowCount; i++) {

			String cityName = driver.findElement(By.xpath(
					"(//*[@class='row cnt-schedule-table']/table/tbody//tr[@class='hidden-xs hidden-sm ng-scope'])[" + i
							+ "]//td[3]//span"))
					.getText();

			if (map.containsKey(cityName)) {

				String cityStatus = driver.findElement(By.xpath(
						"(//*[@class='row cnt-schedule-table']/table/tbody//tr[@class='hidden-xs hidden-sm ng-scope'])["
								+ i + "]//td[7]//span"))
						.getText();

				map.replace(cityName, cityStatus);
			}

		}

		// Print the result
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());

		}

	}
}
