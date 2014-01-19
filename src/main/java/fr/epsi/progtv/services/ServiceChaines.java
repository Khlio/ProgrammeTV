package fr.epsi.progtv.services;

import java.util.List;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.domaine.chaine.EntrepotChaines;

public class ServiceChaines {
	
	private ServiceChaines() {
		chaines = Entrepots.chaines();
	}
	
	private static class ServiceChainesHolder {
		private static final ServiceChaines INSTANCE = new ServiceChaines();
	}
	
	public static ServiceChaines getInstance() {
		return ServiceChainesHolder.INSTANCE;
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

	public Chaine detailsDeLaChainePrecedente(Integer idChaine) {
		Chaine chainePrecedente = null;
		List<Chaine> lesChaines = tout();
		int positionDeLaChaine = positionDeLaChaine(idChaine);
		if (0 < positionDeLaChaine) {
			chainePrecedente = lesChaines.get(positionDeLaChaine-1);
		} else {
			chainePrecedente = lesChaines.get(lesChaines.size()-1);
		}
		return chainePrecedente;
	}

	public Chaine detailsDeLaChaineSuivante(Integer idChaine) {
		Chaine chaineSuivante = null;
		List<Chaine> lesChaines = tout();
		int positionDeLaChaine = positionDeLaChaine(idChaine);
		if (lesChaines.size()-1 > positionDeLaChaine) {
			chaineSuivante = lesChaines.get(positionDeLaChaine+1);
		} else {
			chaineSuivante = lesChaines.get(0);
		}
		return chaineSuivante;
	}
	
	private int positionDeLaChaine(Integer idChaine) {
		Chaine laChaine = detailsDe(idChaine);
		int positionDeLaChaine = tout().indexOf(laChaine);
		return positionDeLaChaine;
	}
	
	private EntrepotChaines chaines;
	
}
