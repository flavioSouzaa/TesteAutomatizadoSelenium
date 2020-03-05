package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.KillDriver;
import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.BaseTest;
import br.cr.wcaquino.page.CadastroPage;

public class Cadastro extends BaseTest {
	
	private CadastroPage page;

	@Before
	public void Inicializa() {
		//DriverFacory.getDriver().get// irá importar uma parte do caminho.
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
		page = new CadastroPage();
	}


	@Test// modo convencional e sem a parte de estrutura de código. 
	public void CadastroTesteCompleto() {
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Flavio");
		String TextName = getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value");
		assertEquals("Flavio", TextName);

		getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("Souza");
		String TextSobreNome = getDriver().findElement(By.id("elementosForm:sobrenome")).getAttribute("value");
		assertEquals("Souza", TextSobreNome);

		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());

		getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		String TextTipo = "Mestrado";
		combo.selectByVisibleText("Mestrado");
		String VerificaCombo = combo.getFirstSelectedOption().getText();
		assertEquals(TextTipo, VerificaCombo);

		element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo1 = new Select(element); 
		combo1.selectByVisibleText("Natacao");
		String TextModalidade = "Natacao";
		assertEquals(TextModalidade, combo1.getFirstSelectedOption().getText());

		getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		Assert.assertTrue(getDriver().findElement(By.id("resultado")).getText().contains("Cadastrado!"));
		Assert.assertTrue(getDriver().findElement(By.id("descNome")).getText().contains("Flavio"));
		Assert.assertTrue(getDriver().findElement(By.id("descSobrenome")).getText().contains("Souza"));
		Assert.assertTrue(getDriver().findElement(By.id("descSexo")).getText().contains("Masculino"));
		Assert.assertTrue(getDriver().findElement(By.id("descComida")).getText().contains("Pizza"));
		Assert.assertTrue(getDriver().findElement(By.id("descEscolaridade")).getText().contains("mestrado"));
		Assert.assertTrue(getDriver().findElement(By.id("descEsportes")).getText().contains("Natacao"));
	}

	@Test // modo simplificado e estruturado 
	public void CadastroSimplificado() {
		page.SetNome("Flavio");
		page.SetSobreNome("Souza");
		page.SetSexoMasculino();
		page.SetComunidaFavoritaPizza();
		page.EscolaridadeMestrado("Mestrado");
		page.SetTipoEsporte("Natacao");
		page.Cadastrar();
		//new Select(getDriver().findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");			
		Assert.assertEquals("Cadastrado!",page.ObterResultadoCadastro());
		Assert.assertEquals("Flavio",page.ObterNomeCadastro());
		Assert.assertEquals("Souza",page.ObterSobreNomeCadastro());
		Assert.assertEquals("Masculino",page.ObterSexoCadastro());
		Assert.assertEquals("Pizza",page.ObterComidaFavoritaCadastro());
		Assert.assertEquals("mestrado",page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao",page.obterEsportCadastro());
	}
		/*
		Assert.assertTrue(page.ObterResultado().startsWith("Cadastrado!"));// verifica se a string começa com o valor esperado.
		Assert.assertTrue(page.ObterNome().endsWith("Flavio"));// verifica se a String termina com o valor esperado. 
		Assert.assertTrue(page.ObterSobreNome().contains("Souza"));
		Assert.assertTrue(page.ObterSexo().contains("Masculino"));
		Assert.assertTrue(page.ObterComidaFavorita().contains("Pizza"));
		Assert.assertTrue(page.obterEscolaridade().contains("mestrado"));
		Assert.assertTrue(dsl.ObterTexto("descEsportes").contains("Natacao"));
		*/
}
