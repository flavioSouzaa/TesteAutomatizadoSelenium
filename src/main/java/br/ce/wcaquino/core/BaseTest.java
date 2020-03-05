package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFacory.KillDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void Finalizar() throws IOException {
		TakesScreenshot ss = (TakesScreenshot) DriverFacory.getDriver();// captura de imagem
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target"+ File.separator +"Screenshot"+ File.separator+  testName.getMethodName() +  ".jpg"));
		
		if (Propriedades.FECHAR_BROWSER) {//método responsável pelo fechamento do brawser
			KillDriver();
		}
	}

}
