package fr.epsi.progtv.services;

import java.util.List;

import fr.epsi.progtv.entrepots.EntrepotChaines;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Chaine;

public class ServiceChaines {

	private static ServiceChaines instance;
	
	private final EntrepotChaines chaines;
	
	private ServiceChaines() {
		chaines = Entrepots.chaines();
	}
	
	public static ServiceChaines getInstance() {
		if (null == instance) {
			instance = new ServiceChaines();
		}
		return instance;
	}
	
	public List<Chaine> tout() {
		return chaines.get();
	}
	
	public Chaine detailsDe(Integer idChaine) {
		Chaine laChaine = null;
		for (Chaine chaine : chaines.get()) {
			if (idChaine.equals(chaine.getId())) {
				laChaine = chaine;
				break;
			}
		}
		return laChaine;
	}
	
}
