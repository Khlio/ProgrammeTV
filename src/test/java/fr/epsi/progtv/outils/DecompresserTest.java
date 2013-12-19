package fr.epsi.progtv.outils;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DecompresserTest {
	
	@Test
	public void testDecompressionFichierZIP() {
		testDecompression(false);
	}
	
	@Test
	public void testDecompressionFichierZIPEtSuppressionDuZIP() {
		testDecompression(true);
	}
	
	private void testDecompression(boolean suppressionDuZIP) {
		File fichierZIP = Telecharger.execute(Constantes.URL_ZIP);
		List<File> lesFichiersDecompresses = Decompresser.execute(fichierZIP, Constantes.DOSSIER_RESOURCES_TEST, suppressionDuZIP);
		
		Assert.assertNotNull(lesFichiersDecompresses);
		Assert.assertEquals(1, lesFichiersDecompresses.size());
		Assert.assertEquals("tnt_lite.xml", lesFichiersDecompresses.get(0).getName());
		
		for (File fichier : lesFichiersDecompresses) {
			fichier.delete();
			Assert.assertFalse(fichier.exists());
		}
		
		if (!suppressionDuZIP) {
			fichierZIP.delete();
		}
		Assert.assertFalse(fichierZIP.exists());
	}
	
}
