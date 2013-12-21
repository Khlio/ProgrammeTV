package fr.epsi.progtv.services;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;

public class ServiceProgrammeTVTest {

	private static ServiceProgrammeTV service;
	
	@BeforeClass
	public static  void setUp() {
		service = ServiceProgrammeTV.getInstance();
		service.recupereLeProgrammeTNT();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testRecupereProgrammeTNT() {
		Assert.assertNotNull(Entrepots.chaines());
		Assert.assertNotNull(Entrepots.chaines().get());
		Assert.assertFalse(Entrepots.chaines().get().isEmpty());
		
		Assert.assertNotNull(Entrepots.programmes());
		Assert.assertNotNull(Entrepots.programmes().get());
		Assert.assertFalse(Entrepots.programmes().get().isEmpty());
	}
	
}
