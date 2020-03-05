package br.cr.wcaquino.page;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import br.ce.wcaquino.core.BasePage;

public class ValidacoesPage extends BasePage {

	public void setNome(String nome) {
		dsl.Escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.Escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoMasculino(){
		dsl.ClicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		dsl.ClicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		dsl.ClicarRadio("elementosForm:comidaFavorita:0");
	}
	public void setComunidaFrango() {
		dsl.ClicarRadio("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza(){
		dsl.ClicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		dsl.ClicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor) {
		dsl.SelecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores) {
		for(String valor: valores)
			dsl.SelecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		dsl.ClicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	
	public String obterNomeCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsportesCadastro(){
		return dsl.ObterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
	
	
	
	
	/********************* Escrita ****************/

	public void SetTextFild(String nome) {
		dsl.Escreve("elementosForm:nome", nome);
	}
	public void SetNome(String nome) {
		dsl.Escreve("elementosForm:nome", nome);
	}
	public void SetTextFildDuplo(String nome) {
		dsl.Escreve("elementosForm:nome", nome);
	}
	public void SetCaixaDeTexto(String nome) {
		dsl.Escreve("elementosForm:sugestoes", nome);
	}
	public void SetEscolaridade(String nome) {
		dsl.SelecionarCombo("elementosForm:escolaridade",nome);
	}
	public boolean SetOpcoesCombo(String nome) {
		return dsl.VerificaOpcoesCombo("elementosForm:escolaridade", nome);
	}
	public void SetTipoEsporte (String tipo) {
		dsl.SelecionarCombo("elementosForm:esportes",tipo);
	}
	public void SetDesmarcarEsport(String tipo) {
		dsl.DeslecionarCombo("elementosForm:esportes", tipo);
	}


	/********************* Clicks botões ****************/
	public void Cadastrar() {
		dsl.ClicarBotao("elementosForm:cadastrar");
	}
	public void Radio() {
		dsl.ClicarRadio("elementosForm:sexo:0");
	}
	public void Check() {
		dsl.ClicarCheck("elementosForm:comidaFavorita:1");	
	}

	/********************* Obter Campos ****************/
	public String ObterResultadoTextFild() {
		return dsl.ObterValorCampo("elementosForm:nome");
	}

	public String ObterResultadoTextFildDuplo() {
		return dsl.ObterValorCampo("elementosForm:nome");
	}
	public String ObterResultadoCaixaDeTexto() {
		return dsl.ObterValorCampo("elementosForm:sugestoes");
	}
	public boolean ObterResultadoRadioMarcadoMasculino() {
		return dsl.isRadioMarcodo("elementosForm:sexo:0");
	}
	public boolean ObterResultadoRadioMarcadoFeminino() {
		return dsl.isRadioMarcodo("elementosForm:comidaFavorita:1");
	}
	public boolean ObterRadioMarcadoComidaFrango() {
		return dsl.isRadioMarcodo("elementosForm:comidaFavorita:1");
	}
	
	public String ObterResultadoEscolaridade() {
		return dsl.ObterValorCombo("elementosForm:escolaridade");
	}
	public int ObeterResultadoDaQuantidadeCombo() {
		return dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade");
	}
	public List<String> ObeterQuantidadeDeEsportesSelecionados() {
		List<String> opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		return opcoesMarcadas;
	}
	public List<String> ObeterQuantidadeDeEsportesDesmarcadas() {
		List<String> opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		return opcoesMarcadas;
	}
	
	public List<String> ObeterQuantidadeDeObsoesMarcadas(String tipo1 , String tipo2) {
		List<String> opcoesMarcadas = dsl.ObterValoresCombo("elementosForm:esportes");
		opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?"));
		return opcoesMarcadas;
	}
	
	
	
}
