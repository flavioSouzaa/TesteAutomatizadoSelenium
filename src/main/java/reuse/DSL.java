package reuse;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void Escreve(String id_campo, String campo) {
		driver.findElement(By.id(id_campo)).sendKeys(campo);		
	}

	public String ObterValor(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void ClicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcodo(String id) {
		return driver.findElement(By.id(id)).isSelected();	
	}
	
	public String ObterValorCombo(String id) 
	{
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);			
		return combo.getFirstSelectedOption().getText();
	}
	
	public void SelecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);			
		combo.selectByVisibleText(valor);		
		//combo.selectByIndex(3);
		//combo.selectByValue("doutorado");	
	}
	
	public void SelecionarComboPorId(String id , int valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);		
		combo.selectByIndex(valor);		
	}
	
	public void SelecionarCamboPorPosicao(String id,String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);			
		combo.selectByValue(valor);//"doutorado"
	}
	
	public void ClicarBotao(String id) {
		driver.findElement(By.id(id)).click();		
	}
	
	public String ObterValorBotao(String id ) {
		return driver.findElement(By.id(id)).getAttribute("Value");
	}
	
	public void ClicarLink(String id) {
		driver.findElement(By.linkText(id)).click();
	}
	
	public String  ObterTexto(By by) {
		return driver.findElement(by).getText();
	}
	
	public String  ObterTexto(String id) {
		return ObterTexto(By.id(id));
	}
}
