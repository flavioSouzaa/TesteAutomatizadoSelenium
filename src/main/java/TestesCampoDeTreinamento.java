import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import reuse.DSL;

public class TestesCampoDeTreinamento {

	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";	
	private WebDriver driver;
	private DSL dsl;
	
		
	@Before
	public void Inicializa() {
		InicializaWebDeiver();
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}	
	
	@Test
	public void TesteFild() {
		dsl.Escreve("elementosForm:nome", "texto incluidos");		
		String Text = dsl.ObterValorCampo("elementosForm:nome");//Método para pegar o conteudo da tela 
		assertEquals("texto incluidos", Text);	
	}
	
	@Test
	public void TestFildDuplo() {
		dsl.Escreve("elementosForm:nome", "flavio");
		assertEquals("flavio", dsl.ObterValorCampo("elementosForm:nome"));
		dsl.Escreve("elementosForm:nome", "souza");
		assertEquals("souza", dsl.ObterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void interagirCaixaDeTexto() {
		try {		
			Thread.sleep(2000);
			dsl.Escreve("elementosForm:sugestoes", "texto\ntexto");				
			assertEquals("texto\ntexto", dsl.ObterValorCampo("elementosForm:sugestoes"));			
			}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	
	@Test
	public void interagirRadioButton() {
		try {									
			Thread.sleep(3000);
			dsl.ClicarRadio("elementosForm:sexo:0");			
			assertTrue(dsl.isRadioMarcodo("elementosForm:sexo:0"));				
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void interagirCheckBox() {
		try {			
			Thread.sleep(2000);
			dsl.ClicarCheck("elementosForm:comidaFavorita:1");			
			boolean verificaCheckBox = dsl.isRadioMarcodo("elementosForm:comidaFavorita:1");									  
			assertTrue(verificaCheckBox);
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void interagirComBox() {
		try {			
			dsl.SelecionarCombo("elementosForm:escolaridade","2o grau completo");			
			Thread.sleep(1000);			
			String TextoVerificacao = "2o grau completo";
			String verificarCombox = dsl.ObterValorCombo("elementosForm:escolaridade");
			//String verificarCombox = combo.getFirstSelectedOption().getText();
			assertEquals(TextoVerificacao,verificarCombox);
		}catch(Exception e) {			
		}		
	}
	
	@Test
	public void deveVerificarValorCombo() {
		assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		assertTrue(dsl.VerificaOpcoesCombo("elementosForm:escolaridade", "Mestrado"));
	}

/*	
	@Test
	public void Teste1interagirValorCombo() {	
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
			}
		}		
		Assert.assertTrue(encontrou);
	}
	*/
	
	@Test
	public void interagirComboMultiplo() {
		dsl.SelecionarCombo("elementosForm:esportes", "Natacao");
		dsl.SelecionarCombo("elementosForm:esportes", "Corrida");
		dsl.SelecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		assertEquals(3, opcoesMarcadas.size());
		
		dsl.DeslecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		assertEquals(2, opcoesMarcadas.size());
		assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?")));
		
	}

	@Test
	public void interagircomBotao() {	
		//WebElement botao = driver.findElement(By.id("buttonSimple"));
		//botao.click();
		//Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
		dsl.ClicarBotao("buttonSimple");
		assertEquals("Obrigado!", dsl.ObterValorBotao("buttonSimple"));
	}

	@Test
	//@Ignore não executa o teste 
	public void interagircomLinks() {	
		dsl.ClicarLink("Voltar");
		assertEquals("Voltou!",dsl.ObterTexto("resultado"));
		//Assert.fail() caso queira executar o teste com erro
	}

	@Test
	public void DeveBuscarTextoPagina() {
		//assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		assertEquals("Campo de Treinamento", dsl.ObterTexto(By.tagName("h3")));
	//	assertEquals("Campo de Treinamento",   driver.findElement(By.tagName("h3")).getText());		
		String texto =  "Cuidado onde clica, muitas armadilhas...";		
		assertEquals(texto,dsl.ObterTexto(By.className("facilAchar")));
	}
/*
	@Test
	public void interagirComAlertSimples() {
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String msgAlert = alert.getText();
		assertEquals("Alert Simples", msgAlert);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msgAlert);		
	}
*/	
	
	public void InicializaWebDeiver() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
}
