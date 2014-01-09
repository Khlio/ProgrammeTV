package fr.epsi.progtv.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.outils.ParserXML;

public class ServiceChainesTest {

	@BeforeClass
	public static void setUp() {
		ParserXML.execute(ServiceChainesTest.class.getClassLoader().getResourceAsStream("tnt_lite.xml"));
		service = ServiceChaines.getInstance();
		assertNotNull(service);
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererToutesLesChaines() {
		List<Chaine> lesChaines = service.tout();
		
		assertNotNull(lesChaines);
		assertEquals(19, lesChaines.size());
	}
	
	@Test
	public void peutRecupererUneChaine() {
		Chaine chaine = service.detailsDe(1);
		
		assertNotNull(chaine);
		assertEquals(1, chaine.getId().intValue());
		assertEquals("TF1", chaine.getNom());
		assertEquals(83, chaine.getProgrammes().size());
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChainePrecedente() {
		Chaine chainePrecedente = service.detailsDeLaChainePrecedente(1);
		
		assertNotNull(chainePrecedente);
		assertEquals(service.detailsDe(19), chainePrecedente);
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChaineSuivante() {
		Chaine chaineSuivante = service.detailsDeLaChaineSuivante(19);
		
		assertNotNull(chaineSuivante);
		assertEquals(service.detailsDe(1), chaineSuivante);
	}
	
	private static ServiceChaines service;
	
}
