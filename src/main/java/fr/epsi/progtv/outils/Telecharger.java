package fr.epsi.progtv.outils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Telecharger {

	private Telecharger() {
	}

	public static File execute(String libelleURL) {
		InputStream fluxEntrant = null;
		FileOutputStream ecriveur = null;
		File fichierTelecharge = null;
		
		try {
			URL url = new URL(libelleURL);
			fluxEntrant = url.openStream();
			String nomFichier = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
			ecriveur = new FileOutputStream(System.getProperty("user.home") + File.separator + nomFichier);
			byte[] buffer = new byte[1024];
			int nombreOctetsLus = 0;
			
			while (0 < (nombreOctetsLus = fluxEntrant.read(buffer))) {
				ecriveur.write(buffer, 0, nombreOctetsLus);
			}
			ecriveur.flush();
			fichierTelecharge = new File(System.getProperty("user.home") + File.separator + nomFichier);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fluxEntrant) {
					fluxEntrant.close();
				}
				if (null != ecriveur) {
					ecriveur.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fichierTelecharge;
	}
	
}
