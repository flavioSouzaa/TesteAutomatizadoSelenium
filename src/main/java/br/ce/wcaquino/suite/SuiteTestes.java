package br.ce.wcaquino.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.test.Cadastro;
import br.ce.wcaquino.test.TestAlert;
import br.ce.wcaquino.test.TesteFramesEjanelas;
import br.ce.wcaquino.test.TesteRegraCadastro;
import br.ce.wcaquino.test.TesteSincronismo;
import br.ce.wcaquino.test.TestesCampoDeTreinamento;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	TestAlert.class,
	TestesCampoDeTreinamento.class,
	TesteFramesEjanelas.class,	
	TesteRegraCadastro.class,
	TesteSincronismo.class
})
public class SuiteTestes {

}
