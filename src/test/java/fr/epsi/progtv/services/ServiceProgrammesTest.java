package fr.epsi.progtv.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.programme.Date;
import fr.epsi.progtv.domaine.programme.Heure;
import fr.epsi.progtv.domaine.programme.Programme;
import fr.epsi.progtv.outils.OutilDate;

public class ServiceProgrammesTest {

	@BeforeClass
	public static void setUp() {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
		service = ServiceProgrammes.getInstance();
		assertNotNull(service);
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererTousLesProgrammes() {
		List<Programme> lesProgrammes = service.tout();
		
		assertNotNull(lesProgrammes);
		assertFalse(lesProgrammes.isEmpty());
	}
	
	@Test
	public void peutRecupererUnProgramme() {
		Programme programme = service.detailsDe(1);
		
		assertNotNull(programme);
		assertEquals(1, programme.getId().intValue());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoir() {
		List<Programme> lesProgrammes = service.programmesDuSoir();
		
		assertNotNull(lesProgrammes);
		assertEquals(2*19, lesProgrammes.size());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoirDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuSoirDeLaChaine(1);
		
		assertNotNull(lesProgrammes);
		assertEquals(2, lesProgrammes.size());
	}
	
	@Test
	public void peutRecupererLesProgrammesEntre20het21hDAujourdhui() {
		List<Programme> programmesDuneChaine = new ArrayList<>();
		Programme programme1 = new Programme("Programme1", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), new Heure(19, 0), new Heure(20, 30));
		programmesDuneChaine.add(programme1);
		Programme programme2 = new Programme("Programme2", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), new Heure(20, 50), new Heure(22, 15));
		programmesDuneChaine.add(programme2);
		List<Programme> lesProgrammes = service.programmesEntre20hEt21h(programmesDuneChaine);
		
		assertNotNull(lesProgrammes);
		assertEquals(1, lesProgrammes.size());
		assertEquals("Programme2", lesProgrammes.get(0).getNom());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMoment() {
		List<Programme> lesProgrammes = service.programmesDuMoment();
		
		assertNotNull(lesProgrammes);
		assertEquals(3*19, lesProgrammes.size());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMomentDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuMomentDeLaChaine(1);
		
		assertNotNull(lesProgrammes);
		assertEquals(3, lesProgrammes.size());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1);
		
		assertNotNull(lesProgrammes);
		assertFalse(lesProgrammes.isEmpty());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaineEnFonctionDuneDate() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		
		assertNotNull(lesProgrammes);
		assertFalse(lesProgrammes.isEmpty());
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaineEnFonctionDuneDateString() {
		Date aujourdhui = OutilDate.dateAujourdhui();
		String mois = (10 > aujourdhui.getMois() ? "0" + aujourdhui.getMois() : aujourdhui.getMois()).toString();
		String jour = (10 > aujourdhui.getJour() ? "0" + aujourdhui.getJour() : aujourdhui.getJour()).toString();
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, "" + aujourdhui.getAnnee() + mois + jour);
		
		assertNotNull(lesProgrammes);
		assertFalse(lesProgrammes.isEmpty());
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammePrecedent() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmePrecedent = service.detailsDuProgrammePrecedent(lesProgrammes.get(0).getId());
		
		assertNotNull(programmePrecedent);
		assertEquals(lesProgrammes.get(lesProgrammes.size()-1), programmePrecedent);
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammeSuivant() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmeSuivant = service.detailsDuProgrammeSuivant(lesProgrammes.get(lesProgrammes.size()-1).getId());
		
		assertNotNull(programmeSuivant);
		assertEquals(lesProgrammes.get(0), programmeSuivant);
	}
	
	private static ServiceProgrammes service;
	
}
