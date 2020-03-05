package br.ce.wcaquino.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	@Test
	public  void teste() {
		// este caminho abaixo só é utilizado caso não tenha colocado o caminho no path.
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\Drivers browser\\geckodriver-v0.26.0-win64\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\Flavio\\\\Downloads\\\\Teste Automatizados Selenium WebDriver\\\\Driver\\Drivers browser\\chromedriver_win32\\chromedriver.exe");
		//System.setProperty("webdriver.ie.driver", "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Drivers browser\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().setSize(new org.openqa.selenium.Dimension(1200, 765));
		driver.get("http://www.google.com");
		String title = driver.getTitle();
		String URL = driver.getCurrentUrl();
		System.out.println(title);
		System.out.print(URL);
		assertEquals("Google",title);
		driver.quit();
		
	}
}
