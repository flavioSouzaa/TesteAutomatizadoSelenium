import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Alert;

public class TestAlert {
	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";

	@Test
	public void interagirComAlertSimples() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		// org.openqa.selenium.Alert alert = driver.switchTo().alert();
		String alertTexto = alert.getText();
		assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alertTexto);
		driver.quit();
	}

	@Test
	public void interagirComAlertDuploOK() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String TextAlertDuplo = alert.getText();
		assertEquals(TextAlertDuplo, alert.getText());
		alert.accept();
		String AlertOk = alert.getText();
		assertEquals(AlertOk, alert.getText());
		alert.accept();
		driver.quit();
	}
	
	@Test
	public void interagirComAlertDuploCancelar() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String TextAlert = alert.getText();
		assertEquals(TextAlert, alert.getText());
		alert.dismiss();
		String TextCancel = alert.getText();
		assertEquals(TextCancel, alert.getText());
		alert.accept();
		driver.quit();
	}
}