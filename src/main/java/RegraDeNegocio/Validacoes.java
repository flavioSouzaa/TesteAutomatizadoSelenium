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

public class Validacoes {

	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	private WebDriver driver;
	
	@Before
	public void Inicializa() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Test
	public void ValidarCampoNome() {		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =  driver.switchTo().alert();
		String msgAlert = alert.getText();
		assertEquals("Nome eh obrigatorio", msgAlert);
		alert.accept();		
	}
	@Test 
	public void validarSobreNome() {		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msgAlert = alert.getText();
		assertEquals("Sobrenome eh obrigatorio", msgAlert);
		alert.accept();	
	}
	
	@Test
	public void validarSexo() {		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msgAlert = alert.getText();
		assertEquals("Sexo eh obrigatorio", msgAlert);
		alert.accept();		
	}
	
	@Test
	public void validarComidaFavorita() {		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		String msg = "Tem certeza que voce eh vegetariano?";
		String msgAlert = alert.getText();
		assertEquals(msg, msgAlert);
		alert.accept();		
	}
	
	@Test
	public void validarEsporte() {				
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
