package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ActeurTest {

	@Test
	public void peutDonnerUnNomComplet() {
		Acteur acteur = new Acteur("Prénom Nom");
		
		assertEquals("Prénom Nom", acteur.getNomComplet());
	}
	
	@Test
	public void peutAfficherUnActeur() {
		Acteur acteur = new Acteur("Prénom Nom");
		
		assertEquals("Prénom Nom", acteur.toString());
	}
	
}
