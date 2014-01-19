package fr.epsi.progtv.domaine.chaine;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.progtv.domaine.programme.Programme;

public class ChaineTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Chaine chaine = new Chaine();
		
		assertThat(chaine).isNotNull();
		assertThat(chaine.getId()).isEqualTo(0);
	}
	
	@Test
	public void peutDonnerUnNom() {
		Chaine chaine = new Chaine("test");
		
		assertThat(chaine.getNom()).isEqualTo("test");
	}
	
	@Test
	public void peutAjouterUnProgramme() {
		Chaine chaine = new Chaine();
		Programme programmeAjoute = chaine.ajouteUnProgramme(new Programme("test"));
		
		assertThat(programmeAjoute).isNotNull();
		assertThat(programmeAjoute.getNom()).isEqualTo("test");
		assertThat(programmeAjoute.getChaine()).isEqualTo(chaine);
		assertThat(chaine.getProgrammes()).isNotNull();
		assertThat(chaine.getProgrammes()).hasSize(1);
	}
	
	@Test
	public void peutSupprimerUnProgramme() {
		Chaine chaine = new Chaine();
		Programme programme = chaine.ajouteUnProgramme(new Programme("test"));
		Programme programmeSupprime = chaine.supprimeUnProgramme(programme);
		
		assertThat(programmeSupprime).isNotNull();
		assertThat(programmeSupprime.getNom()).isEqualTo("test");
		assertThat(programmeSupprime.getChaine()).isNull();
		assertThat(chaine.getProgrammes()).isNotNull();
		assertThat(chaine.getProgrammes()).isEmpty();
	}
	
	@Test
	public void peutAfficherUneChaine() {
		Chaine chaine = new Chaine("test");
		
		assertThat(chaine.toString()).isEqualTo("test");
	}
	
	@Test
	public void peutComparerAUneChainePrecedente() {
		Chaine chaine = new Chaine(19, "19");
		Chaine chainePrecedente = new Chaine(1, "1");
		
		assertThat(chaine.compareTo(chainePrecedente)).isEqualTo(1);
	}
	
	@Test
	public void peutComparerAUneChaineSuivante() {
		Chaine chaine = new Chaine(1, "1");
		Chaine chaineSuivante = new Chaine(19, "19");
		
		assertThat(chaine.compareTo(chaineSuivante)).isEqualTo(-1);
	}
	
	@Test
	public void peutComparerAUneChaineIdentique() {
		Chaine chaine = new Chaine(9, "9");
		Chaine chaineIdentique = new Chaine(9, "9");
		
		assertThat(chaine.compareTo(chaineIdentique)).isEqualTo(0);
	}
	
}
