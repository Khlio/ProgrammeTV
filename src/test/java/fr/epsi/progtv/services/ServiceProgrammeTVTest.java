package fr.epsi.progtv.services;

import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;

public class ServiceProgrammeTVTest {

	@BeforeClass
	public static void setUp() {
		service = ServiceProgrammeTV.getInstance();
		service.recupereLeProgrammeTNT();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererProgrammeTNT() {
		assertFalse(Entrepots.chaines().get().isEmpty());
		assertFalse(Entrepots.programmes().get().isEmpty());
	}
	
	private static ServiceProgrammeTV service;
	
}
