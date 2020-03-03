package RegraDeNegocio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import reuse.DSL;

public class Validacoes {

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
	
	//@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Test
	public void ValidarCampoNome() {		
		dsl.ClicarBotao("elementosForm:cadastrar");
		assertEquals("Nome eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test 
	public void validarSobreNome() {		
		dsl.Escreve(By.id("elementosForm:nome"), "Teste");
		dsl.ClicarBotao("elementosForm:cadastrar");
		assertEquals("Sobrenome eh obrigatorio", dsl.AlertObterTextoAceita());		
	}	
		
	@Test
	public void validarSexo() {		
		dsl.Escreve("elementosForm:nome", "Teste");
		dsl.Escreve("elementosForm:sobrenome", "sobrenome");
		dsl.ClicarBotao("elementosForm:cadastrar");
		assertEquals("Sexo eh obrigatorio", dsl.AlertObterTextoAceita());			
	}
	
	@Test
	public void validarComidaFavorita() {		
		dsl.Escreve("elementosForm:nome", "Teste");
		dsl.Escreve("elementosForm:sobrenome", "sobrenome");
		dsl.ClicarRadio("elementosForm:sexo:0");
		dsl.ClicarCheck("elementosForm:comidaFavorita:0");
		dsl.ClicarCheck("elementosForm:comidaFavorita:3");
		dsl.ClicarBotao("elementosForm:cadastrar");
		String msg = "Tem certeza que voce eh vegetariano?";
		assertEquals(msg, dsl.AlertObterTextoAceita());				
	}
	
	@Test
	public void validarEsporte() {				
		dsl.Escreve("elementosForm:nome", "Teste");
		dsl.Escreve("elementosForm:sobrenome", "sobrenome");
		dsl.ClicarRadio("elementosForm:sexo:0");
		dsl.ClicarBotao("elementosForm:comidaFavorita");
		dsl.SelecionarCombo("elementosForm:esportes", "Natacao");
		dsl.SelecionarCombo("elementosForm:esportes", "O que eh esporte?");			
		assertEquals(2, dsl.ObterQuantidadeDeCombosSelecionados("elementosForm:esportes"));//opcional 
		dsl.ClicarBotao("elementosForm:cadastrar");	
		String msgCampo = "Voce faz esporte ou nao?";
		assertEquals(msgCampo, dsl.AlertObterTextoAceita());
	}	
	
	@Test
	public void testevalidarEsporte() {				
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");		
		List<WebElement> AllSelectedOptions = combo.getAllSelectedOptions();
		assertEquals(2, AllSelectedOptions.size());//opcional 
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();		
		Alert alert = driver.switchTo().alert();
		String msgAlert = alert.getText();
		String msgCampo = "Voce faz esporte ou nao?";
		assertEquals(msgCampo, msgAlert);
		alert.accept();		
	}
	
}
