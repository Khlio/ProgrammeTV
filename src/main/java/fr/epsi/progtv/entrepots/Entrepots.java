package fr.epsi.progtv.entrepots;

public class Entrepots {
	
	private static Entrepots instance = new Entrepots();
	
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

}
