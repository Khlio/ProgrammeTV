package fr.epsi.progtv.outils;

import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;

public class ParserXMLTest {
	
	@BeforeClass
	public static void setUp() {
		ParserXML.execute(ParserXMLTest.class.getClassLoader().getResourceAsStream("tnt_lite.xml"));
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutParserXML() {
		assertFalse(Entrepots.chaines().get().isEmpty());
		assertFalse(Entrepots.programmes().get().isEmpty());
	}
	
}
