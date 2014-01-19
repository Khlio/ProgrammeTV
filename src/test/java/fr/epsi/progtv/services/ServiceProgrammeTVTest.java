package fr.epsi.progtv.services;

import static org.fest.assertions.Assertions.assertThat;

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
		assertThat(Entrepots.chaines().get()).isNotEmpty();
		assertThat(Entrepots.programmes().get()).isNotEmpty();
	}
	
	private static ServiceProgrammeTV service;
	
}
