package fr.epsi.progtv.services;

import static org.fest.assertions.Assertions.assertThat;

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
		assertThat(service).isNotNull();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererTousLesProgrammes() {
		List<Programme> lesProgrammes = service.tout();
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).isNotEmpty();
	}
	
	@Test
	public void peutRecupererUnProgramme() {
		Programme programme = service.detailsDe(1);
		
		assertThat(programme).isNotNull();
		assertThat(programme.getId()).isEqualTo(1);
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoir() {
		List<Programme> lesProgrammes = service.programmesDuSoir();
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).hasSize(2*19);
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoirDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuSoirDeLaChaine(1);
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).hasSize(2);
	}
	
	@Test
	public void peutRecupererLesProgrammesEntre20het21hDAujourdhui() {
		List<Programme> programmesDuneChaine = new ArrayList<>();
		Programme programme1 = new Programme("Programme1", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), new Heure(19, 0), new Heure(20, 30));
		programmesDuneChaine.add(programme1);
		Programme programme2 = new Programme("Programme2", OutilDate.dateAujourdhui(), OutilDate.dateAujourdhui(), new Heure(20, 50), new Heure(22, 15));
		programmesDuneChaine.add(programme2);
		List<Programme> lesProgrammes = service.programmesEntre20hEt21h(programmesDuneChaine);
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).hasSize(1);
		assertThat(lesProgrammes.get(0).getNom()).isEqualTo("Programme2");
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMoment() {
		List<Programme> lesProgrammes = service.programmesDuMoment();
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).hasSize(3*19);
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMomentDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuMomentDeLaChaine(1);
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).hasSize(3);
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaine() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1);
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).isNotEmpty();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaineEnFonctionDuneDate() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).isNotEmpty();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaineEnFonctionDuneDateString() {
		Date aujourdhui = OutilDate.dateAujourdhui();
		String mois = (10 > aujourdhui.getMois() ? "0" + aujourdhui.getMois() : aujourdhui.getMois()).toString();
		String jour = (10 > aujourdhui.getJour() ? "0" + aujourdhui.getJour() : aujourdhui.getJour()).toString();
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, "" + aujourdhui.getAnnee() + mois + jour);
		
		assertThat(lesProgrammes).isNotNull();
		assertThat(lesProgrammes).isNotEmpty();
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammePrecedent() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmePrecedent = service.detailsDuProgrammePrecedent(lesProgrammes.get(1).getId());
		
		assertThat(programmePrecedent).isNotNull();
		assertThat(programmePrecedent).isEqualTo(lesProgrammes.get(0));
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammePrecedentLePremier() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmePrecedent = service.detailsDuProgrammePrecedent(lesProgrammes.get(0).getId());
		
		assertThat(programmePrecedent).isNotNull();
		assertThat(programmePrecedent).isEqualTo(lesProgrammes.get(lesProgrammes.size() - 1));
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammeSuivant() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmeSuivant = service.detailsDuProgrammeSuivant(lesProgrammes.get(lesProgrammes.size() - 2).getId());
		
		assertThat(programmeSuivant).isNotNull();
		assertThat(programmeSuivant).isEqualTo(lesProgrammes.get(lesProgrammes.size() - 1));
	}
	
	@Test
	public void peutRecupererLesDetailsDuProgrammeSuivantLeDernier() {
		List<Programme> lesProgrammes = service.programmesDuneChaine(1, OutilDate.dateAujourdhui());
		Programme programmeSuivant = service.detailsDuProgrammeSuivant(lesProgrammes.get(lesProgrammes.size() - 1).getId());
		
		assertThat(programmeSuivant).isNotNull();
		assertThat(programmeSuivant).isEqualTo(lesProgrammes.get(0));
	}
	
	private static ServiceProgrammes service;
	
}
