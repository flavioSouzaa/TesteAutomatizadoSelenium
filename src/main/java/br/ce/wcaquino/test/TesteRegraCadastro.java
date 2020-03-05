package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFacory;
import br.cr.wcaquino.page.ValidacoesPage;
@RunWith(Parameterized.class)
public class TesteRegraCadastro {
	private DSL dsl;
	private ValidacoesPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String esportes []; 
	@Parameter(value=5)
	public String msg;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new ValidacoesPage(getDriver());
	}
	
	@After
	public void Finalizar() {
		DriverFacory.KillDriver();
	}
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
		{"", "" , "",  Arrays.asList(), new String[] {}, "Nome eh obrigatorio"},
		{"flavio", "" , "",  Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
		{"flavio", "souza" , "",  Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
		{"flavio", "souza" , "Masculino", Arrays.asList("Carne","Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
		{"flavio", "souza" , "Masculino", Arrays.asList("Carne"), new String[] {"Natacao","O que eh esporte?"}, "Voce faz esporte ou nao?"}

		});
	}
	
	@Test
	public void deveValidarRegras() {				
		page.SetNome(nome);
		page.SetSobreNome(sobrenome);
		if(sexo.equals("Masculino")){
			page.SetSexoMasculino();
		}
		if(sexo.equals("Feminino")){
			page.SetSexoFeminino();
		}
		if(comidas.contains("Carne"))page.SetComunidaFavoritaCarne();
		if(comidas.contains("Frango"))page.SetComunidaFavoritaFrango();
		if(comidas.contains("Pizza"))page.SetComunidaFavoritaPizza();
		if(comidas.contains("Vegetariano"))page.SetComunidaFavoritaVegetariano();
		page.SetTipoEsporte(esportes);		
		page.Cadastrar();		
		assertEquals(msg, dsl.AlertObterTextoAceita());
	}	
	
	
		
	
}
