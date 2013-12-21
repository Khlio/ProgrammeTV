package fr.epsi.progtv.services;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Chaine;

public class ServiceChainesTest {

	private static ServiceChaines service;
	
	@BeforeClass
	public static void setUp() {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
		service = ServiceChaines.getInstance();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testTout() {
		List<Chaine> lesChaines = service.tout();
		
		Assert.assertNotNull(lesChaines);
		Assert.assertEquals(19, lesChaines.size());
	}
	
	@Test
	public void testDetailsDe() {
		Chaine chaine = service.detailsDe(1);
		
		Assert.assertNotNull(chaine);
		Assert.assertEquals("TF1", chaine.getNom());
	}
	
}
