package fr.epsi.progtv.domaine;

import fr.epsi.progtv.domaine.chaine.EntrepotChaines;
import fr.epsi.progtv.domaine.programme.EntrepotProgrammes;

public class Entrepots {
	
	private Entrepots() {
	}
	
	public static EntrepotChaines chaines() {
		return instance.entrepotChaines();
	}
	
	public static EntrepotProgrammes programmes() {
		return instance.entrepotProgrammes();
	}
	
	public static void nettoie() {
		chaines().nettoie();
		programmes().nettoie();
	}
	
	private EntrepotChaines entrepotChaines() {
		return EntrepotChaines.getInstance();
	}
	
	private EntrepotProgrammes entrepotProgrammes() {
		return EntrepotProgrammes.getInstance();
	}
	
	private static Entrepots instance = new Entrepots();

}
