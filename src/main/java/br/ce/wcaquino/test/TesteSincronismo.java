package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;
import br.cr.wcaquino.page.CadastroPage;

public class TesteSincronismo {
	private DSL dsl;
	private CadastroPage page;

	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CadastroPage();
	}

	@After
	public void Finalizar() {
		DriverFacory.KillDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException  {		
		dsl.ClicarBotao("buttonDelay");		
		Thread.sleep(5000);
		dsl.Escreve("novoCampo", "Deu Certo?");		
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException  {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.ClicarBotao("buttonDelay");		
		dsl.Escreve("novoCampo", "Deu Certo?");	
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException  {	//Utilizar explicita	
		dsl.ClicarBotao("buttonDelay");		
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.Escreve("novoCampo", "Deu Certo?");		
	}
}
