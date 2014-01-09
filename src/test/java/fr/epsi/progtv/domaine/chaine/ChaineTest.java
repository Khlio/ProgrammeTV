package fr.epsi.progtv.domaine.chaine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.epsi.progtv.domaine.programme.Programme;

public class ChaineTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Chaine chaine = new Chaine();
		
		assertNotNull(chaine);
		assertEquals(Integer.valueOf(0), chaine.getId());
	}
	
	@Test
	public void peutDonnerUnNom() {
		Chaine chaine = new Chaine("test");
		
		assertEquals("test", chaine.getNom());
	}
	
	@Test
	public void peutAjouterUnProgramme() {
		Chaine chaine = new Chaine();
		Programme programmeAjoute = chaine.ajouteUnProgramme(new Programme("test"));
		
		assertNotNull(programmeAjoute);
		assertEquals("test", programmeAjoute.getNom());
		assertEquals(chaine, programmeAjoute.getChaine());
		assertNotNull(chaine.getProgrammes());
		assertEquals(1, chaine.getProgrammes().size());
	}
	
	@Test
	public void peutSupprimerUnProgramme() {
		Chaine chaine = new Chaine();
		Programme programme = chaine.ajouteUnProgramme(new Programme("test"));
		Programme programmeSupprime = chaine.supprimeUnProgramme(programme);
		
		assertNotNull(programmeSupprime);
		assertEquals("test", programmeSupprime.getNom());
		assertNull(programmeSupprime.getChaine());
		assertNotNull(chaine.getProgrammes());
		assertTrue(chaine.getProgrammes().isEmpty());
	}
	
	@Test
	public void peutAfficherUneChaine() {
		Chaine chaine = new Chaine("test");
		
		assertEquals("test", chaine.toString());
	}
	
	@Test
	public void peutComparerAUneChainePrecedente() {
		Chaine chaine = new Chaine(19, "19");
		Chaine chainePrecedente = new Chaine(1, "1");
		
		assertEquals(1, chaine.compareTo(chainePrecedente));
	}
	
	@Test
	public void peutComparerAUneChaineSuivante() {
		Chaine chaine = new Chaine(1, "1");
		Chaine chaineSuivante = new Chaine(19, "19");
		
		assertEquals(-1, chaine.compareTo(chaineSuivante));
	}
	
	@Test
	public void peutComparerAUneChaineIdentique() {
		Chaine chaine = new Chaine(9, "9");
		Chaine chaineIdentique = new Chaine(9, "9");
		
		assertEquals(0, chaine.compareTo(chaineIdentique));
	}
	
}
