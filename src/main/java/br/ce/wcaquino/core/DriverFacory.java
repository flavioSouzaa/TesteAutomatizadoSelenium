package br.ce.wcaquino.core;

import javax.naming.directory.DirContext;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFacory {
	private static WebDriver driver;

	private DriverFacory() {
	}

	public static WebDriver getDriver() {
		String DriverChome = "webdriver.gecko.driver";
		String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
		if (driver == null) {
			System.setProperty(DriverChome, caminhoDriver);
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1200, 765));
		}
		return driver;
	}

	public static void KillDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
