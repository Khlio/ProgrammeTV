package fr.epsi.progtv.outils;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;

public class ParserXMLTest {
	
	@BeforeClass
	public static void setUp() throws JDOMException, IOException {
		ParserXML.execute(ParserXMLTest.class.getClassLoader().getResourceAsStream("tnt_lite.xml"));
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutParserXML() {
		assertThat(Entrepots.chaines().get()).isNotEmpty();
		assertThat(Entrepots.programmes().get()).isNotEmpty();
	}
	
}
