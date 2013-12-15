package fr.epsi.progtv.outils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.ParserXML;

public class ParserXMLTest {
	
	@BeforeClass
	public static void setUp() {
		ParserXML.execute(Constantes.FICHIER_XML);
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
