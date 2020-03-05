package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.KillDriver;
import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;

public class TesteFramesEjanelas {
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");	
		dsl = new DSL();
	}
	
	@After
	public void Finalizar() {
		KillDriver();
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
	public void DeveInteragirComFrameEscondido() {
		WebElement Frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", Frame.getLocation().y);
		dsl.entrarForm("frame2");		
		dsl.ClicarBotao("frameButton");
		String msg = dsl.AlertObterTextoAceita();
		assertEquals("Frame OK!", msg);
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
