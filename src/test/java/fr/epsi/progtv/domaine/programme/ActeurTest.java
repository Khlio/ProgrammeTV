package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ActeurTest {

	@Test
	public void peutDonnerUnNomComplet() {
		Acteur acteur = new Acteur("Prénom Nom");
		
		assertThat(acteur.getNomComplet()).isEqualTo("Prénom Nom");
	}
	
	@Test
	public void peutAfficherUnActeur() {
		Acteur acteur = new Acteur("Prénom Nom");
		
		assertThat(acteur.getNomComplet()).isEqualTo("Prénom Nom");
	}
	
}
