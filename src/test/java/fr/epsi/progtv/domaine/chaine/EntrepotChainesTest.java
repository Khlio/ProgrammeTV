package fr.epsi.progtv.domaine.chaine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		assertNotNull(entrepot);
		assertNotNull(entrepot.get());
	}
	
	@Override
	public void peutAjouter() {
		entrepot.ajoute(new Chaine(1, "TF1"));
		
		assertEquals(1, entrepot.get().size());
	}

	@Override
	public void peutNettoyer() {
		entrepot.ajoute(new Chaine(1, "TF1"));
		entrepot.nettoie();
		
		assertTrue(entrepot.get().isEmpty());
	}
	
	@Test
	public void peutTrier() {
		entrepot.ajoute(new Chaine(3, "France 3"));
		entrepot.ajoute(new Chaine(1, "TF1"));
		entrepot.ajoute(new Chaine(2, "France 2"));
		
		entrepot.trie();
		
		for (int i = 0; i < entrepot.get().size(); i++) {
			assertEquals(Integer.valueOf(i+1), entrepot.get().get(i).getId());
		}
	}
	
}
