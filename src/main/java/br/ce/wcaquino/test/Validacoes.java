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

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;
import br.cr.wcaquino.page.ValidacoesPage;

public class Validacoes extends BaseTest{

	private DSL dsl;
	private ValidacoesPage page;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ValidacoesPage();
	}
		
	
	@Test
	public void ValidarCampoNome() {		
		page.cadastrar();
		assertEquals("Nome eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test 
	public void validarSobreNome() {		
		page.setNome("flavio");
		page.cadastrar();
		assertEquals("Sobrenome eh obrigatorio", dsl.AlertObterTextoAceita());		
	}	
		
	@Test
	public void validarSexo() {		
		page.setNome("Teste");
		page.setSobrenome("sobrenome");
		page.cadastrar();
		assertEquals("Sexo eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test
	public void validarComidaFavorita() {		
		page.setNome("Teste");
		page.setSobrenome("sobrenome");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		String msg = "Tem certeza que voce eh vegetariano?";
		assertEquals(msg, dsl.AlertObterTextoAceita());				
	}
	
	@Test
	public void validarEsporte() {				
		page.setNome("Teste");
		page.setSobrenome("sobrenome");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Natacao","O que eh esporte?");			
		assertEquals(2, dsl.ObterQuantidadeDeCombosSelecionados("elementosForm:esportes"));//opcional 
		page.cadastrar();
		String msgCampo = "Voce faz esporte ou nao?";
		assertEquals(msgCampo, dsl.AlertObterTextoAceita());
	}	
	

	
}
