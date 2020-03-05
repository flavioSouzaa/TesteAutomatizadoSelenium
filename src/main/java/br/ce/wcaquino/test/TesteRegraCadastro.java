package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFacory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.cr.wcaquino.page.CadastroPage;
import br.cr.wcaquino.page.ValidacoesPage;

@RunWith(Parameterized.class)
public class TesteRegraCadastro extends BaseTest {
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
		page = new ValidacoesPage();
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
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")){
			page.setSexoMasculino();
		}
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		if(comidas.contains("Carne"))page.setComidaCarne();
		if(comidas.contains("Frango"))page.setComunidaFrango();
		if(comidas.contains("Pizza"))page.setComidaPizza();
		if(comidas.contains("Vegetariano"))page.setComidaVegetariano();
		page.setEsporte(esportes);		
		page.cadastrar();		
		assertEquals(msg, dsl.AlertObterTextoAceita());
	}	
	
	
		
	
}
