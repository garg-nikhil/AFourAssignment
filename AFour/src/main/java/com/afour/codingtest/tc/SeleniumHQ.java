package com.afour.codingtest.tc;

import org.testng.annotations.Test;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.afour.codingtest.testbase.TestBase;

public class SeleniumHQ extends TestBase {
	public static Logger log = Logger.getLogger(TestBase.class);
	TestBase a = new TestBase();
	
	@Test
	public void wikiTextOfSelenium() {

		WebElement element;
		String URL= a.getURL1();
		driver.get(URL);
		log.info("Launching the website:- "+URL);
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("selenium hq");

		driver.findElement(By.xpath("//input[@name='btnK'][1]")).click();

		try {
			element = driver.findElement(By.xpath("//span[contains(text(),'Edit this at Wikidata')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (NoSuchElementException e1) {
			driver.findElement(By.xpath("//a[@aria-label='Page 2']")).click();
			element = driver.findElement(By.xpath("//span[contains(text(),'Edit this at Wikidata')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}

		String str = element.getText();
		System.out.println("" + str);

	}
}
