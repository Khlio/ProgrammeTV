package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.epsi.progtv.domaine.EntrepotAggregatsTest;
import fr.epsi.progtv.domaine.Entrepots;

public class EntrepotProgrammesTest extends EntrepotAggregatsTest<Programme> {

	@Before
	public void setUp() {
		entrepot = Entrepots.programmes();
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
		entrepot.ajoute(new Programme("Dr House"));
		
		assertThat(entrepot.get()).hasSize(1);
	}

	@Override
	public void peutNettoyer() {
		entrepot.ajoute(new Programme("test"));
		entrepot.nettoie();
		
		assertThat(entrepot.get()).isEmpty();
	}
	
	@Test
	public void peutTrier() {
		entrepot.ajoute(new Programme("3", new Date(), new Date(), new Heure(10, 0), new Heure(11, 0)));
		entrepot.ajoute(new Programme("1", new Date(), new Date(), new Heure(8, 0), new Heure(9, 0)));
		entrepot.ajoute(new Programme("2", new Date(), new Date(), new Heure(9, 0), new Heure(10, 0)));
		
		entrepot.trie();
		
		for (int i = 0; i < entrepot.get().size(); i++) {
			assertThat(entrepot.get().get(i).getNom()).isEqualTo(String.valueOf(i+1));
		}
	}
	
}
