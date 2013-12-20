package fr.epsi.progtv.outils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;

public class ParserXMLTest {
	
	@BeforeClass
	public static void setUp() {
		ParserXML.execute(ParserXMLTest.class.getClassLoader().getResource(Constantes.FICHIER_XML).getFile());
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testParserXML() {
		Assert.assertNotNull(Entrepots.chaines());
		Assert.assertNotNull(Entrepots.chaines().get());
		
		Assert.assertNotNull(Entrepots.programmes());
		Assert.assertNotNull(Entrepots.programmes().get());
	}
	
}
