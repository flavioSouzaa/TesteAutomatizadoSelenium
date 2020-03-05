package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFacory.KillDriver;

import org.junit.After;

public class BaseTest {

	@After
	public void Finalizar() {
		KillDriver();
	}

}
