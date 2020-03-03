package Page;
import org.openqa.selenium.WebDriver;

import reuse.DSL;


public class CadastroPage {

	private DSL dsl;

	public CadastroPage(WebDriver driver) {
		dsl = new  DSL(driver);
	}
	
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
	public void SetTipoEsportevoid (String tipo) {
		dsl.SelecionarCombo("elementosForm:esportes",tipo);
	}
	public void Cadastrar() {
		dsl.ClicarBotao("elementosForm:cadastrar");
	}
	public String ObterResultado() {
		return dsl.ObterTexto("resultado");
	}
	public String ObterNome() {
		return dsl.ObterTexto("descNome");
	}
	public String ObterSobreNome() {
		return dsl.ObterTexto("descSobrenome");
	}
	public String ObterSexo() {
		return dsl.ObterTexto("descSexo");
	}
	public String ObterComidaFavorita() {
		return dsl.ObterTexto("descComida");
	}
	public String obterEscolaridade() {
		return dsl.ObterTexto("descEscolaridade");
	}
	public String obterEsport() {
		return dsl.ObterTexto("descEsportes");
	}
}
