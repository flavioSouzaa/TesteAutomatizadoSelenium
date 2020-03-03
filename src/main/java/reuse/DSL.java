package reuse;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sun.org.apache.regexp.internal.recompile;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/******************* TextField e TextArea *************/

	public void Escreve(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void Escreve(String id_campo, String texto) {
		Escreve(By.id(id_campo), texto);
	}

	public String ObterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}

	/******************* Radio e Check *************/

	public void ClicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isRadioMarcodo(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	public void ClicarCheck(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isCheckMarcodo(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	/******************* Combo *************/

	/******************* Tipo de Combo *************/
	public void SelecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
		// combo.selectByIndex(3);
		// combo.selectByValue("doutorado");
	}

	public void SelecionarComboPorId(String id, int valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByIndex(valor);
	}

	public void SelecionarCamboPorPosicao(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByValue(valor);// "doutorado"
	}

	/******************* Fim  *************/

	public void DeslecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);		
	}

	public String ObterValorCombo(String id) { // irá retornar todas as opções selecionadas
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> ObterValoresCombo(String id) {//irá retornar o valor de combo multiplos
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores; 
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {//irá retornar a quantidade de opções no combo
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	

	public int ObterQuantidadeDeCombosSelecionados(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getAllSelectedOptions();
		return options.size();
	}
	
	public boolean VerificaOpcoesCombo(String id, String opcao) {//irá verificar se o item está presente no combo
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	/******************* Botão *************/	

	public void ClicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}

	public String ObterValorBotao(String id) {
		return driver.findElement(By.id(id)).getAttribute("Value");
	}

	/******************* Links *************/
	
	public void ClicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}
	
	/******************* Texto *************/
	
	public String ObterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String ObterTexto(String id) {
		return ObterTexto(By.id(id));
	}

	/******************* Alert *************/
	
	public String AlertTexto() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
		
	public String AlertObterTextoAceita() {
		Alert alert = driver.switchTo().alert();
		String valoralert = alert.getText();
		alert.accept();
		return valoralert;
	}
	
	public String AlertObterTextoNegado() {
		Alert alert = driver.switchTo().alert();
		String ValorAlert = alert.getText();
		alert.dismiss();
		return ValorAlert;
	}
	
	public void AlertEscrever() {
		Alert alert = driver.switchTo().alert();
		String valorAlert = alert.getText();
		alert.sendKeys(valorAlert);
		alert.accept();
	}
	
	public void AlertEscreverComParametro(String texto) {
		Alert alert = driver.switchTo().alert();
		//String valorAlert = alert.getText();
		alert.sendKeys(texto);
		alert.accept();
	}
	
	/******************* Forms e Janelas *************/
	
	public void entrarForm(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	public void NevegaEntreJanelas(int NumeroPagina) {
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[NumeroPagina]);
	}
	
	
	/******************* Foco *************/
	
	public void estabelecerFoco(String id) {
		driver.switchTo().window(id);
	}
	

	/******************* Fechar janelas *************/
	
	public void FecharJanelas() {
		driver.close();
	}
	
	
	
}
