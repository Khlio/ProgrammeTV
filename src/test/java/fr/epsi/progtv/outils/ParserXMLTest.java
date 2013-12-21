package fr.epsi.progtv.outils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.services.ServiceProgrammeTV;

public class ParserXMLTest {
	
	@BeforeClass
	public static void setUp() {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testParserXML() {
		Assert.assertNotNull(Entrepots.chaines());
		Assert.assertNotNull(Entrepots.chaines().get());
		Assert.assertFalse(Entrepots.chaines().get().isEmpty());
		
		Assert.assertNotNull(Entrepots.programmes());
		Assert.assertNotNull(Entrepots.programmes().get());
		Assert.assertFalse(Entrepots.programmes().get().isEmpty());
	}
	
}
