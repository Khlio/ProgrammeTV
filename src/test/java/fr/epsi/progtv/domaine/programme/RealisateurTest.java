package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RealisateurTest {

	@Test
	public void peutDonnerUnNomComplet() {
		Realisateur realisateur = new Realisateur("Prénom Nom");
		
		assertEquals("Prénom Nom", realisateur.getNomComplet());
	}
	
	@Test
	public void peutAfficherUnActeur() {
		Realisateur realisateur = new Realisateur("Prénom Nom");
		
		assertEquals("Prénom Nom", realisateur.toString());
	}
	
}
