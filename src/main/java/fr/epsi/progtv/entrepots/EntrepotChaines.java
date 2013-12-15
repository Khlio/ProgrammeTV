package fr.epsi.progtv.entrepots;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.progtv.modeles.Chaine;

public class EntrepotChaines {

	private static EntrepotChaines instance;
	
	private final List<Chaine> lesChaines = new ArrayList<>();
	
	public static EntrepotChaines getInstance() {
		if (null == instance) {
			instance = new EntrepotChaines();
		}
		return instance;
	}
	
	public List<Chaine> get() {
		return lesChaines;
	}
	
	public void ajoute(Chaine chaine) {
		lesChaines.add(chaine);
	}
	
	public void nettoie() {
		lesChaines.clear();
	}
	
}
