package br.ce.wcaquino.core;

import javax.naming.directory.DirContext;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFacory {
	private static WebDriver driver;

	private DriverFacory() {
	}

	public static WebDriver getDriver() {
		String DriverFIREFOX  = "webdriver.gecko.driver";
		String caminhoDriverFIREFOX = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
		String DriverCHROME = "webdriver.chrome.driver";
		String caminhoDriverCHROME =  "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\chromedriver_win32\\chromedriver.exe";
		
		
		
		if (driver == null) {
			//System.setProperty(DriverFIREFOX, caminhoDriverFIREFOX);
			System.setProperty(DriverCHROME, caminhoDriverCHROME);
			switch (Propriedades.browser) {
			case FIREFOX: driver = new FirefoxDriver();break;
			case CHROME: driver = new ChromeDriver(); break;
			
			}			
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
