
import static br.ce.wcaquino.core.DriverFacory.getDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;

public class TestePrine {

	private DSL dsl;

	@Before
	public void Inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL();
	}

	@After
	public void Finalizar() {
		//driver.quit();
	}
		
	@Test
	public void deveInteragirComRadioPrine() {
		dsl.ClicarRadio(By.xpath("//label[.='PS4']/../span"));;
	}
}
