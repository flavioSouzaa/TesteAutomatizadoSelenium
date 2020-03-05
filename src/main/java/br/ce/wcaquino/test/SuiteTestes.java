package br.ce.wcaquino.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	//TestAlert.class,
	//TestesCampoDeTreinamento.class,
	//TesteFramesEjanelas.class,	
	TesteRegraCadastro.class
	//TesteSincronismo.class
})
public class SuiteTestes {

}
