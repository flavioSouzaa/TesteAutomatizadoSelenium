import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import reuse.DSL;

public class TesteFramesEjanelas {
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
	
	@After
	public void Finalizar() {
		driver.quit();
	}	
	
	@Test
	public void TestFrame() {
		dsl.entrarForm("frame1");
		dsl.ClicarBotao("frameButton");	
		String msg = dsl.AlertObterTextoAceita();
		assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.Escreve("elementosForm:nome", msg);			
	}	
	
	@Test
	public void InteragirComJanelas() {
		dsl.ClicarBotao("buttonPopUpEasy");
		dsl.estabelecerFoco("Popup");
		dsl.Escreve(By.tagName("textarea"), "Deu certo!");
		dsl.FecharJanelas();
		dsl.estabelecerFoco("");
		dsl.Escreve(By.tagName("textarea"),"e agora");		
	}		
	
	@Test
	public void InteragiComJanelasSemTitulo() {		
		dsl.ClicarBotao("buttonPopUpHard");
		dsl.NevegaEntreJanelas(1);
		dsl.Escreve(By.tagName("textarea"), "Deu certo!");
		dsl.FecharJanelas();
		dsl.NevegaEntreJanelas(0);
		dsl.Escreve(By.tagName("textarea"), "Voltou");			
	}	
	//System.out.println(driver.getWindowHandle());//verifica o id da página atual
	//System.out.println(driver.getWindowHandles());// verifica o id de todas as páginas abertas	
}
