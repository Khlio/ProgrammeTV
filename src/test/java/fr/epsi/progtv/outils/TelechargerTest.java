package fr.epsi.progtv.outils;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TelechargerTest {

	@Test
	public void testTelechargementDunFichierZIP() {
		File fichierZIP = Telecharger.execute(Constantes.URL_ZIP);
		
		Assert.assertNotNull(fichierZIP);
		Assert.assertTrue(fichierZIP.isFile());
		Assert.assertEquals("tnt_lite.zip", fichierZIP.getName());
		
		fichierZIP.delete();
		Assert.assertFalse(fichierZIP.exists());
	}
	
}
