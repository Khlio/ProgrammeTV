package fr.epsi.progtv.entrepots;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.progtv.modeles.Programme;

public class EntrepotProgrammes {

	private static EntrepotProgrammes instance;
	
	private final List<Programme> lesProgrammes = new ArrayList<>();
	
	private EntrepotProgrammes() {
	}
	
	public static EntrepotProgrammes getInstance() {
		if (null == instance) {
			instance = new EntrepotProgrammes();
		}
		return instance;
	}
	
	public List<Programme> get() {
		return lesProgrammes;
	}
	
	public void ajoute(Programme programme) {
		lesProgrammes.add(programme);
	}
	
	public void nettoie() {
		lesProgrammes.clear();
		Programme.resetId();
	}
	
}
