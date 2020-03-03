package RegraDeNegocio;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Page.ValidacoesPage;
import reuse.DSL;

public class Validacoes {

	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	private WebDriver driver;
	private DSL dsl;
	private ValidacoesPage page;
	
	@Before
	public void Inicializa() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new ValidacoesPage(driver);
	}
	
	@After
	public void Finalizar() {
		driver.quit();
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
