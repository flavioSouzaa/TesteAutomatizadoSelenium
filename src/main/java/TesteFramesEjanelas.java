import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.Alert;

public class TesteFramesEjanelas {
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
	public void TestFrame() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);		
	}
	
	@Test
	public void InteragirComJanelas() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora");
	}
	
	@Test
	public void InteragiComJanelasSemTitulo() {		
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo!");
		driver.close();
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("Voltou");		
	}
}
