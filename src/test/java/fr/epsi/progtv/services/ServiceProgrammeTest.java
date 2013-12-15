package fr.epsi.progtv.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Heure;
import fr.epsi.progtv.modeles.Programme;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.OutilDate;
import fr.epsi.progtv.outils.ParserXML;

public class ServiceProgrammeTest {
	
	private static ServiceProgrammes service;
	
	@BeforeClass
	public static void setUp() {
		ParserXML.execute(Constantes.FICHIER_XML);
		service = ServiceProgrammes.getInstance();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testTout() {
		List<Programme> lesProgrammes = service.tout();
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertTrue(!lesProgrammes.isEmpty());
	}
	
	@Test
	public void testDetailsDe() {
		Programme programme = service.detailsDe(1);
		
		Assert.assertNotNull(programme);
		Assert.assertEquals("L'édition de la nuit", programme.getNom());
	}
	
	@Test
	public void testProgrammesDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1);
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertTrue(!lesProgrammes.isEmpty());
	}
	
	@Test
	public void testProgrammesDuSoir() {
		List<Programme> lesProgrammes = service.programmesDuSoir();
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertEquals(2*19, lesProgrammes.size());
	}
	
	@Test
	public void testProgrammesDuSoirDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuSoirDeLaChaine(1);
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertEquals(2, lesProgrammes.size());
	}
	
	@Test
	public void testProgrammesEntre20het21h() {
		List<Programme> programmesDuneChaine = new ArrayList<>();
		Programme programme1 = new Programme("Programme1", "", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), 
				new Heure(19, 0), new Heure(20, 30), null, "", "");
		programmesDuneChaine.add(programme1);
		Programme programme2 = new Programme("Programme2", "", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), 
				new Heure(20, 50), new Heure(22, 15), null, "", "");
		programmesDuneChaine.add(programme2);
		List<Programme> lesProgrammes = service.programmesEntre20hEt21h(programmesDuneChaine);
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertEquals(1, lesProgrammes.size());
		Assert.assertEquals("Programme2", lesProgrammes.get(0).getNom());
	}
	
	@Test
	public void testProgrammesDuMomentDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuMomentDeLaChaine(1);
		
		Assert.assertNotNull(lesProgrammes);
		Assert.assertEquals(3, lesProgrammes.size());
	}
	
}
