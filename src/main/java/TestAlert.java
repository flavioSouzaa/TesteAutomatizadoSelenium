import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Alert;

public class TestAlert {
	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	WebDriver driver;
	
	@Before
	public void Inicializa() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Test
	public void interagirComAlertSimples() {
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		// org.openqa.selenium.Alert alert = driver.switchTo().alert();
		String alertTexto = alert.getText();
		assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alertTexto);
	}

	@Test
	public void interagirComAlertDuploOK() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String TextAlertDuplo = alert.getText();
		assertEquals(TextAlertDuplo, alert.getText());
		alert.accept();
		String AlertOk = alert.getText();
		assertEquals(AlertOk, alert.getText());
		alert.accept();
	}
	
	@Test
	public void interagirComAlertDuploCancelar() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String TextAlert = alert.getText();
		assertEquals(TextAlert, alert.getText());
		alert.dismiss();
		String TextCancel = alert.getText();
		assertEquals(TextCancel, alert.getText());
		alert.accept();
	}

	@Test
	public void interagirComPrompt() {
		// Este caso de teste está realizando a verificação somete do caminho feliz.
		String TextAlertNumero,textAlertConfirma,textAlertFim;
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		TextAlertNumero = alert.getText();
		assertEquals(TextAlertNumero, alert.getText());
		alert.sendKeys("123");
		alert.accept();
		textAlertConfirma = alert.getText();
		assertEquals(textAlertConfirma, alert.getText());
		alert.accept();
		textAlertFim = alert.getText();
		assertEquals(textAlertFim, alert.getText());
		alert.accept();
	}

	
}