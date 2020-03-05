package br.cr.wcaquino.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ce.wcaquino.core.BasePage;
import br.ce.wcaquino.core.DSL;


public class CadastroPage extends BasePage{
		
	public void SetNome(String nome) {
		dsl.Escreve("elementosForm:nome",nome);
	}
	
	public void SetSobreNome(String sobrenome) {
		dsl.Escreve("elementosForm:sobrenome",sobrenome);
	}
	
	public void SetSexoMasculino() {
		dsl.ClicarCheck("elementosForm:sexo:0");
	}
	
	public void SetComunidaFavoritaCarne() {
		dsl.ClicarRadio("elementosForm:comidaFavorita:0");
	}
	public void SetComunidaFavoritaFrango() {
		dsl.ClicarRadio("elementosForm:comidaFavorita:1");
	}
	public void SetComunidaFavoritaPizza() {
		dsl.ClicarRadio("elementosForm:comidaFavorita:2");
	}
	public void SetComunidaFavoritaVegetariano() {
		dsl.ClicarRadio("elementosForm:comidaFavorita:3");
	}
	public void EscolaridadeMestrado(String tipo) {
		dsl.SelecionarCombo("elementosForm:escolaridade",tipo);
	}
	public void SetTipoEsporte (String tipo) {
		dsl.SelecionarCombo("elementosForm:esportes",tipo);
	}
	public void Cadastrar() {
		dsl.ClicarBotao("elementosForm:cadastrar");
	}
	public String ObterResultadoCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	public String ObterNomeCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	public String ObterSobreNomeCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	public String ObterSexoCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	public String ObterComidaFavoritaCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	public String obterEscolaridadeCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	public String obterEsportCadastro() {
		return dsl.ObterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
}
