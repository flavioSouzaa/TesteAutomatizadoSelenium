package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.KillDriver;
import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.cr.wcaquino.page.ValidacoesPage;

public class TestesCampoDeTreinamento extends BaseTest{
	private DSL dsl;
	private ValidacoesPage page;

	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ValidacoesPage();
	}

	@Test
	public void TesteFild() {
		page.SetTextFild("texto incluidos");
		assertEquals("texto incluidos", page.ObterResultadoTextFild());
	}

	@Test
	public void TestFildDuplo() {
		page.SetTextFildDuplo("flavio");
		assertEquals("flavio", page.ObterResultadoTextFildDuplo());
		page.SetTextFildDuplo("souza");
		assertEquals("souza", page.ObterResultadoTextFildDuplo());
	}

	@Test
	public void interagirCaixaDeTexto() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 2);
		page.SetCaixaDeTexto("texto\ntexto");
		assertEquals("texto\ntexto", page.ObterResultadoCaixaDeTexto());
	}

	@Test
	public void interagirRadioButton() {
		page.Radio();
		assertTrue(page.ObterResultadoRadioMarcadoMasculino());
	}

	@Test
	public void interagirCheckBox() {
		page.Check();
		assertTrue(page.ObterResultadoRadioMarcadoFeminino());
	}

	@Test
	public void interagirComBox() {
		page.SetEscolaridade("2o grau completo");
		// String verificarCombox = combo.getFirstSelectedOption().getText();
		assertEquals("2o grau completo", page.ObterResultadoEscolaridade());
	}

	@Test
	public void deveVerificarValorCombo() {
		assertEquals(8, page.ObeterResultadoDaQuantidadeCombo());
		assertTrue(page.SetOpcoesCombo("Mestrado"));
	}

	/*
	 * @Test public void Teste1interagirValorCombo() { WebElement element =
	 * driver.findElement(By.id("elementosForm:escolaridade")); Select combo = new
	 * Select(element); List<WebElement> options = combo.getOptions();
	 * Assert.assertEquals(8, options.size());
	 * 
	 * boolean encontrou = false; for(WebElement option: options) {
	 * if(option.getText().equals("Mestrado")) { encontrou = true; } }
	 * Assert.assertTrue(encontrou); }
	 */

	@Test
	public void interagirComboMultiplo() {
		page.SetTipoEsporte("Natacao");
		page.SetTipoEsporte("Corrida");
		page.SetTipoEsporte("O que eh esporte?");
		List<String> opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		assertEquals(3, page.ObeterQuantidadeDeEsportesSelecionados().size());
		page.SetDesmarcarEsport("Corrida");
		assertEquals(2, page.ObeterQuantidadeDeEsportesDesmarcadas().size());
	    assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?")));
		
	}	

	@Test
	public void interagircomBotao() {
		// WebElement botao = driver.findElement(By.id("buttonSimple"));
		// botao.click();
		// Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
		dsl.ClicarBotao("buttonSimple");
		assertEquals("Obrigado!", dsl.ObterValorBotao("buttonSimple"));
	}

	@Test
	// @Ignore não executa o teste
	public void interagircomLinks() {
		dsl.ClicarLink("Voltar");
		assertEquals("Voltou!", dsl.ObterTexto("resultado"));
		// Assert.fail() caso queira executar o teste com erro
	}

	@Test
	public void DeveBuscarTextoPagina() {
		// assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo
		// de Treinamento"));
		assertEquals("Campo de Treinamento", dsl.ObterTexto(By.tagName("h3")));
		// assertEquals("Campo de Treinamento",
		// driver.findElement(By.tagName("h3")).getText());
		String texto = "Cuidado onde clica, muitas armadilhas...";
		assertEquals(texto, dsl.ObterTexto(By.className("facilAchar")));
	}

	/*
	 * @Test public void interagirComAlertSimples() {
	 * driver.findElement(By.id("alert")).click(); Alert alert =
	 * driver.switchTo().alert(); String msgAlert = alert.getText();
	 * assertEquals("Alert Simples", msgAlert); alert.accept();
	 * driver.findElement(By.id("elementosForm:nome")).sendKeys(msgAlert); }
	 */
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		// js.executeScript("alert('Teste js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrita java script'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void deveclicarBotaoTable() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
