package fr.epsi.progtv.services;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.epsi.progtv.outils.Constantes;

public class ServiceProgrammeTVTest {

	private ServiceProgrammeTV service;
	
	@Before
	public void setUp() {
		service = ServiceProgrammeTV.getInstance();
	}
	
	@Test
	public void testRecupereProgrammeTNT() {
		service.recupereLeProgrammeTNT();
		File fichierXML = new File(System.getProperty("user.home") + File.separator + Constantes.FICHIER_XML);
		
		Assert.assertNotNull(fichierXML);
		Assert.assertFalse(fichierXML.exists());
	}
	
}
