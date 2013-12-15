package fr.epsi.progtv.entrepots;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import fr.epsi.progtv.entrepots.EntrepotProgrammes;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Programme;

public class EntrepotProgrammesTest {

	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void testAjouterProgramme() {
		EntrepotProgrammes programmes = Entrepots.programmes();
		programmes.ajoute(new Programme("Dr House"));
		
		Assert.assertNotNull(programmes);
		Assert.assertNotNull(programmes.get());
		Assert.assertEquals(1, programmes.get().size());
		Assert.assertEquals(1, programmes.get().get(0).getId().intValue());
		Assert.assertEquals("Dr House", programmes.get().get(0).getNom());
	}
	
}
