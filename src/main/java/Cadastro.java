import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import reuse.DSL;

public class Cadastro {
	String DriverChome = "webdriver.gecko.driver";
	String caminhoDriver = "C:\\Users\\Flavio\\Downloads\\Teste Automatizados Selenium WebDriver\\Driver\\Drivers browser\\geckodriver-v0.26.0-win64\\geckodriver.exe";
	private WebDriver driver;
	private DSL dsl;
	private CadastroPage page;

	@Before
	public void Inicializa() {
		System.setProperty(DriverChome, caminhoDriver);
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1260, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CadastroPage(driver);
	}

	@After
	public void Finalizar() {
		driver.quit();
	}

	@Test
	public void CadastroTesteCompleto() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Flavio");
		String TextName = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		assertEquals("Flavio", TextName);

		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Souza");
		String TextSobreNome = driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value");
		assertEquals("Souza", TextSobreNome);

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		String TextTipo = "Mestrado";
		combo.selectByVisibleText("Mestrado");
		String VerificaCombo = combo.getFirstSelectedOption().getText();
		assertEquals(TextTipo, VerificaCombo);

		element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo1 = new Select(element);
		combo1.selectByVisibleText("Natacao");
		String TextModalidade = "Natacao";
		assertEquals(TextModalidade, combo1.getFirstSelectedOption().getText());

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().contains("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().contains("Flavio"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().contains("Souza"));
		Assert.assertTrue(driver.findElement(By.id("descSexo")).getText().contains("Masculino"));
		Assert.assertTrue(driver.findElement(By.id("descComida")).getText().contains("Pizza"));
		Assert.assertTrue(driver.findElement(By.id("descEscolaridade")).getText().contains("mestrado"));
		Assert.assertTrue(driver.findElement(By.id("descEsportes")).getText().contains("Natacao"));
	}

	@Test
	public void CadastroSimplificado() {
		page.SetNome("Flavio");
		page.SetSobreNome("Souza");
		page.SetSexoMasculino();
		page.SetComunidaFavoritaPizza();
		page.EscolaridadeMestrado("Mestrado");
		page.SetTipoEsporte("Natacao");
		page.Cadastrar();
		//new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");		
		Assert.assertTrue(page.ObterResultado().startsWith("Cadastrado!"));// verifica se a string começa com o valor esperado.
		Assert.assertTrue(page.ObterNome().endsWith("Flavio"));// verifica se a String termina com o valor esperado. 
		Assert.assertTrue(page.ObterSobreNome().contains("Souza"));
		Assert.assertTrue(page.ObterSexo().contains("Masculino"));
		Assert.assertTrue(page.ObterComidaFavorita().contains("Pizza"));
		Assert.assertTrue(page.obterEscolaridade().contains("mestrado"));
		Assert.assertTrue(dsl.ObterTexto("descEsportes").contains("Natacao"));
	}
}
