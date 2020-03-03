import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import reuse.DSL;

public class TestAlert {
	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}	

	@Test
	public void interagirComAlertSimples() {
		dsl.ClicarBotao("alert");
		String msgAlert = dsl.AlertObterTextoAceita();
		assertEquals("Alert Simples", msgAlert);
		dsl.Escreve("elementosForm:nome", msgAlert);				
	}
	
	@Test
	public void interagirComAlertDuploOK() {
		dsl.ClicarBotao("confirm");
		String TextoAlertDuplo = dsl.AlertTexto();
		assertEquals(TextoAlertDuplo, dsl.AlertObterTextoAceita());
		String AlertOk = dsl.AlertTexto();
		assertEquals(AlertOk, dsl.AlertObterTextoAceita());
	}
	
	@Test
	public void interagirComAlertDuploCancelar() {
		dsl.ClicarBotao("confirm");
		String TextoAlert = dsl.AlertTexto();
		assertEquals(TextoAlert, dsl.AlertObterTextoNegado());
		String TextoCancela = dsl.AlertTexto();
		assertEquals(TextoCancela, dsl.AlertObterTextoAceita());		
	}
	
	@Test
	public void interagirComPrompt() {
		String TextAlertNumero,textAlertConfirma,textAlertFim;
		dsl.ClicarBotao("prompt");
		TextAlertNumero = dsl.AlertTexto();
		assertEquals(TextAlertNumero, dsl.AlertTexto());
		dsl.AlertEscreverComParametro("123");
		textAlertConfirma = dsl.AlertTexto();
		assertEquals(textAlertConfirma, dsl.AlertObterTextoAceita());
		textAlertFim = dsl.AlertTexto();
		assertEquals(textAlertFim, dsl.AlertObterTextoAceita());
		
	}	
	
}