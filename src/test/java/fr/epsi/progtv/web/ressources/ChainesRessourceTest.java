package fr.epsi.progtv.web.ressources;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.services.ServiceProgrammeTV;

public class ChainesRessourceTest {

	@BeforeClass
	public static void setUp() {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
		ressource = new ChainesRessource();
		assertThat(ressource).isNotNull();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererToutesLesChaines() {
		List<Chaine> listeDesChaines = ressource.liste();
		
		assertThat(listeDesChaines).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDuneChaine() {
		Chaine chaineRecuperee = ressource.detailsDe(1);
		
		assertThat(chaineRecuperee).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChainePrecedente() {
		Chaine chainePrecedente = ressource.detailsDeLaChainePrecedente(1);
		
		assertThat(chainePrecedente).isNotNull();
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChaineSuivante() {
		Chaine chaineSuivante = ressource.detailsDeLaChaineSuivante(1);
		
		assertThat(chaineSuivante).isNotNull();
	}
	
	private static ChainesRessource ressource;
	
}
