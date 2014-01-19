package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class RealisateurTest {

	@Test
	public void peutDonnerUnNomComplet() {
		Realisateur realisateur = new Realisateur("Prénom Nom");
		
		assertThat(realisateur.getNomComplet()).isEqualTo("Prénom Nom");
	}
	
	@Test
	public void peutAfficherUnActeur() {
		Realisateur realisateur = new Realisateur("Prénom Nom");
		
		assertThat(realisateur.toString()).isEqualTo("Prénom Nom");
	}
	
}
