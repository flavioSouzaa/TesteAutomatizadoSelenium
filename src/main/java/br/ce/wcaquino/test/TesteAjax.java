package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;
import br.cr.wcaquino.page.CadastroPage;

public class TesteAjax {
	private DSL dsl;
	private CadastroPage page;

	@Before
	public void Inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
		page = new CadastroPage(getDriver());
	}

	@After
	public void Finalizar() {
		DriverFacory.KillDriver();
	}

	@Test
	public void testAjax(){
		dsl.Escreve("j_idt720:name", "Teste");
		dsl.ClicarBotao("j_idt720:j_idt723");
		WebDriverWait wait = new WebDriverWait(getDriver(), 60);
		//wait.until(ExpectedConditions.textToBe(By.id("j_idt85:display"), "Teste"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("Teste", dsl.ObterTexto("j_idt720:display"));
	}
}
