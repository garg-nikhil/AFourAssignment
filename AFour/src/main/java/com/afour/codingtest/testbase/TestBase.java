package com.afour.codingtest.testbase;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	protected static WebDriver driver;
	static Properties prop;
	public static String OS_NAME = System.getProperty("os.name"); // Variable will print the underlying OS name
	public static String USER_DIR = System.getProperty("user.dir"); // Variable will print the user directory
	public static Logger log = Logger.getLogger(TestBase.class); // Initializing the logger class

	public TestBase() {

		// initializing constructor to load the properties file

		prop = new Properties();
		try {
			FileInputStream a = new FileInputStream(
					"C:\\Users\\nikhil.garg\\eclipse-workspace\\AFour\\src\\test\\resources\\Properties\\config.properties");
			prop.load(a);
			log.info("Properties file initialization is completed");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public static void openBrowser() {
		driver = null;

		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = getChromeBrowser();
			log.info("Launching " + browserName + " in " + OS_NAME);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = getFirefoxBrowser();
			log.info("Launching " + browserName);
		}

		driver.manage().window().maximize();
		log.info("Maximizing the window");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	private static WebDriver getChromeBrowser() {
		String chromePath = getChromePath();
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", chromePath);
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		return driver;

	}

	private static String getChromePath() {
		String chromePath = null;
		if (OS_NAME.contains("Win")) {
			chromePath = USER_DIR + ("\\src\\test\\resources\\Drivers\\chromedriver.exe");
			log.info("ChromePath: " + chromePath);
		} else if (OS_NAME.contains("linux")) {

		} else {
			log.info("Operating System not supported: " + OS_NAME);
		}
		return chromePath;
	}

	private static WebDriver getFirefoxBrowser() {
		String firefoxPath = getFireforPath();
		System.setProperty("webdriver.firefox.driver", firefoxPath);
		return driver;
	}

	private static String getFireforPath() {
		String path = null;
		if (OS_NAME.contains("win")) {
			path = USER_DIR + ("\\src\\test\\resources\\Drivers\\geckodriver.exe");
		} else if (OS_NAME.contains("linux")) {

		} else {
			log.info("OS not supported: " + OS_NAME);
		}
		return path;
	}

	public String getURL1() {
		String URL = prop.getProperty("URL1");
		return URL;
	}

	public String getURL2() {
		String URL = prop.getProperty("URL2");
		return URL;
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
		log.info("Closing " + prop.getProperty("browser") + " driver instance");
	}

}