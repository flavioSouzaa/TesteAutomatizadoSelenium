package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;
import br.cr.wcaquino.page.ValidacoesPage;

public class Validacoes {

		private DSL dsl;
	private ValidacoesPage page;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ValidacoesPage(getDriver());
	}
	
	@After
	public void Finalizar() {
		DriverFacory.KillDriver();
	}
	
	@Test
	public void ValidarCampoNome() {		
		page.Cadastrar();
		assertEquals("Nome eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test 
	public void validarSobreNome() {		
		page.SetNome("flavio");
		page.Cadastrar();
		assertEquals("Sobrenome eh obrigatorio", dsl.AlertObterTextoAceita());		
	}	
		
	@Test
	public void validarSexo() {		
		page.SetNome("Teste");
		page.SetSobreNome("sobrenome");
		page.Cadastrar();
		assertEquals("Sexo eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test
	public void validarComidaFavorita() {		
		page.SetNome("Teste");
		page.SetSobreNome("sobrenome");
		page.SetSexoMasculino();
		page.SetComunidaFavoritaCarne();
		page.SetComunidaFavoritaVegetariano();
		page.Cadastrar();
		String msg = "Tem certeza que voce eh vegetariano?";
		assertEquals(msg, dsl.AlertObterTextoAceita());				
	}
	
	@Test
	public void validarEsporte() {				
		page.SetNome("Teste");
		page.SetSobreNome("sobrenome");
		page.SetSexoMasculino();
		page.SetComunidaFavoritaCarne();
		page.SetTipoEsporte("Natacao","O que eh esporte?");			
		assertEquals(2, dsl.ObterQuantidadeDeCombosSelecionados("elementosForm:esportes"));//opcional 
		page.Cadastrar();
		String msgCampo = "Voce faz esporte ou nao?";
		assertEquals(msgCampo, dsl.AlertObterTextoAceita());
	}	
	

	
}
