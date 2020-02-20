import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class Testes {

	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	
	
	@Test
	public void TesteFild() {
		//caso não coloque o caminho do driver no path deve executar o comando abaixo.
		System.setProperty(DriverChome,caminhoDriver);
		WebDriver driver = new FirefoxDriver();//após indicar o caminho do driver deve-se instaciar o driver.
		driver.manage().window().setSize(new Dimension(1260, 960));//isso irá setar o tamanho da janela do browser		
	
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");//caso aplicação esteje rodando no sistema estará no src main resources
		driver.findElement(By.id("elementosForm:nome")).sendKeys("texto incluidos");//Método para pegar o elemento da tela juntamento com o método de escrita na tela.
		String Text = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");//Método para pegar o conteudo da tela 
		assertEquals("texto incluidos", Text);//teste de verificação com parâmetro passa com o esperado
		driver.quit();
	}
	
	@Test
	public void interagirCaixaDeTexto() {
		try {
			System.setProperty(DriverChome, caminhoDriver);
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1260, 765));
			
			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			Thread.sleep(2000);
			driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("texto\ntexto");
			
			String Verificacao = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
			assertEquals("texto\ntexto", Verificacao);
			driver.quit();
			}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	
	@Test
	public void interagirRadioButton() {
		try {
			System.setProperty(DriverChome, caminhoDriver);
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1260, 765));
			
			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
						
			Thread.sleep(3000);
			driver.findElement(By.id("elementosForm:sexo:0")).click();
			assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
			driver.quit();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void interagirCheckBox() {
		try {
			
			System.setProperty(DriverChome, caminhoDriver);
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1260, 765));
			
			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
			
			Thread.sleep(2000);
			driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
			boolean verificaCheckBox = driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected();
			assertTrue(verificaCheckBox);
			driver.quit();
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void interagirComBox() {
		try {
			System.setProperty(DriverChome, caminhoDriver);
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1260, 765));

			driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
						
			WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
			Select combo = new Select(element);
			Thread.sleep(3000);
			//combo.selectByIndex(3);
			//combo.selectByValue("doutorado");
			String TextoVerificacao = "2o grau completo";
			combo.selectByVisibleText("2o grau completo");
			String verificarCombox = combo.getFirstSelectedOption().getText();
			assertEquals(TextoVerificacao,verificarCombox);
			driver.quit();
		}catch(Exception e) {			
		}		
	}
	
	@Test
	public void interagirValorCombo() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");		
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
		driver.quit();
	}

	@Test
	public void interagirComboMultiplo() {

		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element =  driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> AllSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, AllSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		List<WebElement> AllSelectedOptions1 = combo.getAllSelectedOptions();
		assertEquals(2, AllSelectedOptions1.size());
		driver.quit();
	}

	@Test
	public void interagircomBotao() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
		driver.quit();
	}

	@Test
	//@Ignore não executa o teste 
	public void interagircomLinks() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		//Assert.fail() caso queira executar o teste com erro;
		driver.quit();
	}

	@Test
	public void DeveBuscarTextoPagina() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		String text =  "Cuidado onde clica, muitas armadilhas...";
		assertEquals(text, driver.findElement(By.className("facilAchar")).getText());
		driver.quit();
	}

	@Test
	public void interagirComAlertSimples() {
		System.setProperty(DriverChome, caminhoDriver);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") +"/src/main/resources/componentes.html");
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		//org.openqa.selenium.Alert alert = driver.switchTo().alert();
		assertEquals("Alert Simples", alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alert.getText());
		//driver.quit();
	}
}
