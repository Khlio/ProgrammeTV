package fr.epsi.progtv.outils;

import javax.ws.rs.core.MediaType;

public class Constantes {

	public static final String FICHIER_XML = "tnt.xml";
	
	public static final int AUJOURDHUI = 0;
	public static final int ANTERIEUR = -1;
	public static final int POSTERIEUR = 1;
	
	public static final String ENTETE_HTTP = MediaType.APPLICATION_JSON + ";charset=UTF-8";
	
	private Constantes() {
	}
	
}
