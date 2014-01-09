package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		assertNotNull(entrepot);
		assertNotNull(entrepot.get());
	}
	
	@Override
	public void peutAjouter() {
		entrepot.ajoute(new Programme("Dr House"));
		
		assertEquals(1, entrepot.get().size());
	}

	@Override
	public void peutNettoyer() {
		entrepot.ajoute(new Programme("test"));
		entrepot.nettoie();
		
		assertTrue(entrepot.get().isEmpty());
	}
	
	@Test
	public void peutTrier() {
		entrepot.ajoute(new Programme("3", new Date(), new Date(), new Heure(10, 0), new Heure(11, 0)));
		entrepot.ajoute(new Programme("1", new Date(), new Date(), new Heure(8, 0), new Heure(9, 0)));
		entrepot.ajoute(new Programme("2", new Date(), new Date(), new Heure(9, 0), new Heure(10, 0)));
		
		entrepot.trie();
		
		for (int i = 0; i < entrepot.get().size(); i++) {
			assertEquals(String.valueOf(i+1), entrepot.get().get(i).getNom());
		}
	}
	
}
