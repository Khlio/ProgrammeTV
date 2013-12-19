package fr.epsi.progtv.outils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Decompresser {

	private Decompresser() {
	}
	
	public static List<File> execute(File fichierZIP, String dossierCible) {
		return execute(fichierZIP, dossierCible, false);
	}

	public static List<File> execute(File fichierZIP, String dossierCible, boolean suppressionDuZIP) {
		ZipInputStream fluxZIPEntrant = null;
		FileOutputStream ecriveur = null;
		byte[] buffer = new byte[1024];
		List<File> lesFichiersDecompresses = new ArrayList<>();
		
		try {
			File dossier = new File(dossierCible);
			if (!dossier.isDirectory()) {
				dossier.mkdirs();
			}
			
			fluxZIPEntrant = new ZipInputStream(new FileInputStream(fichierZIP));
			for (ZipEntry entree = fluxZIPEntrant.getNextEntry(); null != entree; entree = fluxZIPEntrant.getNextEntry()) {
				File fichier = new File(dossierCible + File.separator + entree.getName());
				ecriveur = new FileOutputStream(fichier);
				int nombreOctetsLus = 0;
				while (0 < (nombreOctetsLus = fluxZIPEntrant.read(buffer))) {
					ecriveur.write(buffer, 0, nombreOctetsLus);
				}
				ecriveur.flush();
				lesFichiersDecompresses.add(fichier);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fluxZIPEntrant) {
					fluxZIPEntrant.closeEntry();
					fluxZIPEntrant.close();
				}
				if (null != ecriveur) {
					ecriveur.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (suppressionDuZIP) {
			fichierZIP.delete();
		}
		return lesFichiersDecompresses;
	}

}
