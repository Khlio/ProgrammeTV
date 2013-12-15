package fr.epsi.progtv.entrepots;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import fr.epsi.progtv.entrepots.EntrepotChaines;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Chaine;

public class EntrepotChainesTest {

	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testAjouterChaine() {
		EntrepotChaines chaines = Entrepots.chaines();
		chaines.ajoute(new Chaine(1, "TF1"));
		
		Assert.assertNotNull(chaines);
		Assert.assertNotNull(chaines.get());
		Assert.assertEquals(1, chaines.get().size());
		Assert.assertEquals(1, chaines.get().get(0).getId().intValue());
		Assert.assertEquals("TF1", chaines.get().get(0).getNom());
	}
	
}
