package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFacory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	

	/******************* TextField e TextArea *************/

	public void Escreve(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public void Escreve(String id_campo, String texto) {
		Escreve(By.id(id_campo), texto);
	}

	public String ObterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}

	/******************* Radio e Check *************/

	public void ClicarRadio(By by) {
		getDriver().findElement(by).click();
	}

	public void ClicarRadio(String id) {
		ClicarRadio(By.id(id));
	}
	public boolean isRadioMarcodo(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	public void ClicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isCheckMarcodo(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	/******************* Combo *************/

	/******************* Tipo de Combo *************/
	public void SelecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
		// combo.selectByIndex(3);
		// combo.selectByValue("doutorado");
	}

	public void SelecionarComboPorId(String id, int valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByIndex(valor);
	}

	public void SelecionarCamboPorPosicao(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByValue(valor);// "doutorado"
	}

	/******************* Fim *************/

	public void DeslecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String ObterValorCombo(String id) { // irá retornar todas as opções selecionadas
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> ObterValoresCombo(String id) {// irá retornar o valor de combo multiplos
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}

	public int obterQuantidadeOpcoesCombo(String id) {// irá retornar a quantidade de opções no combo
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public int ObterQuantidadeDeCombosSelecionados(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getAllSelectedOptions();
		return options.size();
	}

	public boolean VerificaOpcoesCombo(String id, String opcao) {// irá verificar se o item está presente no combo
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}

	/******************* Botão *************/

	public void ClicarBotao(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public String ObterValorBotao(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("Value");
	}

	/******************* Links *************/

	public void ClicarLink(String id) {
		getDriver().findElement(By.linkText(id)).click();
	}

	/******************* Texto *************/

	public String ObterTexto(By by) {
		return getDriver().findElement(by).getText();
	}

	public String ObterTexto(String id) {
		return ObterTexto(By.id(id));
	}

	/******************* Alert *************/

	public String AlertTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	public String AlertObterTextoAceita() {
		Alert alert = getDriver().switchTo().alert();
		String valoralert = alert.getText();
		alert.accept();
		return valoralert;
	}

	public String AlertObterTextoNegado() {
		Alert alert = getDriver().switchTo().alert();
		String ValorAlert = alert.getText();
		alert.dismiss();
		return ValorAlert;
	}

	public void AlertEscrever() {
		Alert alert = getDriver().switchTo().alert();
		String valorAlert = alert.getText();
		alert.sendKeys(valorAlert);
		alert.accept();
	}

	public void AlertEscreverComParametro(String texto) {
		Alert alert = getDriver().switchTo().alert();
		// String valorAlert = alert.getText();
		alert.sendKeys(texto);
		alert.accept();
	}

	/******************* Forms e Janelas *************/

	public void entrarForm(String id) {
		getDriver().switchTo().frame(id);
	}

	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}

	public void NevegaEntreJanelas(int NumeroPagina) {
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[NumeroPagina]);
	}

	/******************* Foco *************/

	public void estabelecerFoco(String id) {
		getDriver().switchTo().window(id);
	}

	/******************* Fechar janelas *************/

	public void FecharJanelas() {
		getDriver().close();
	}

	/*********************** JS *********************/

	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}

	/*********************** Tabela *********************/

	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
