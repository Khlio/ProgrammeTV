package fr.epsi.progtv.domaine.chaine;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.epsi.progtv.domaine.EntrepotAggregatsTest;
import fr.epsi.progtv.domaine.Entrepots;

public class EntrepotChainesTest extends EntrepotAggregatsTest<Chaine> {

	@Before
	public void setUp() {
		entrepot = Entrepots.chaines();
	}
	
	@After
	public void tearDown() {
		entrepot.nettoie();
	}
	
	@Override
	public void peutRecuperer() {
		assertThat(entrepot).isNotNull();
		assertThat(entrepot.get()).isNotNull();
	}
	
	@Override
	public void peutAjouter() {
		entrepot.ajoute(new Chaine(1, "TF1"));
		
		assertThat(entrepot.get()).hasSize(1);
	}

	@Override
	public void peutNettoyer() {
		entrepot.ajoute(new Chaine(1, "TF1"));
		entrepot.nettoie();
		
		assertThat(entrepot.get()).isEmpty();
	}
	
	@Test
	public void peutTrier() {
		entrepot.ajoute(new Chaine(3, "France 3"));
		entrepot.ajoute(new Chaine(1, "TF1"));
		entrepot.ajoute(new Chaine(2, "France 2"));
		
		entrepot.trie();
		
		for (int i = 0; i < entrepot.get().size(); i++) {
			assertThat(entrepot.get().get(i).getId()).isEqualTo(i+1);
		}
	}
	
}
