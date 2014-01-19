package fr.epsi.progtv.web.ressources;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.programme.Date;
import fr.epsi.progtv.domaine.programme.Programme;
import fr.epsi.progtv.outils.OutilDate;
import fr.epsi.progtv.services.ServiceProgrammeTV;

public class ProgrammesRessourceTest {

	@BeforeClass
	public static void setUp() {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
		ressource = new ProgrammesRessource();
		assertThat(ressource).isNotNull();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererLaListeDesProgrammes() {
		List<Programme> lesProgrammes = ressource.liste();
		
		assertThat(lesProgrammes).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDunProgramme() {
		Programme programmeRecupere = ressource.detailsDe(1);
		
		assertThat(programmeRecupere).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoir() {
		List<Programme> programmesDuSoir = ressource.programmesDuSoir();
		
		assertThat(programmesDuSoir).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuSoirDuneChaine() {
		List<Programme> programmesDuSoir = ressource.programmesDuSoirDeLaChaine(1);
		
		assertThat(programmesDuSoir).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMoment() {
		List<Programme> programmesDuMoment = ressource.programmesDuMoment();
		
		assertThat(programmesDuMoment).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuMomentDuneChaine() {
		List<Programme> programmesDuMoment = ressource.programmesDuMomentDeLaChaine(1);
		
		assertThat(programmesDuMoment).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaine() {
		List<Programme> programmesDuneChaine = ressource.programmesDuneChaine(1);
		
		assertThat(programmesDuneChaine).isNotNull();
	}
	
	@Test
	public void peutRecupererLesProgrammesDuneChaineAUneCertaineDate() {
		Date aujourdhui = OutilDate.dateAujourdhui();
		String mois = (10 > aujourdhui.getMois() ? "0" + aujourdhui.getMois() : aujourdhui.getMois()).toString();
		String jour = (10 > aujourdhui.getJour() ? "0" + aujourdhui.getJour() : aujourdhui.getJour()).toString();
		List<Programme> programmesDuneChaine = ressource.programmesDuneChaine(1, "" + aujourdhui.getAnnee() + mois + jour);
		
		assertThat(programmesDuneChaine).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDunProgrammePrecedent() {
		Programme programmePrecedent = ressource.detailsDuProgrammePrecedent(1);
		
		assertThat(programmePrecedent).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDunProgrammeSuivant() {
		Programme programmeSuivant = ressource.detailsDuProgrammeSuivant(1);
		
		assertThat(programmeSuivant).isNotNull();
	}
	
	private static ProgrammesRessource ressource;
	
}
