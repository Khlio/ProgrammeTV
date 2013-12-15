package fr.epsi.progtv.services;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.progtv.entrepots.EntrepotProgrammes;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Chaine;
import fr.epsi.progtv.modeles.Programme;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.OutilDate;

public class ServiceProgrammes {

	private static ServiceProgrammes instance;
	
	private final EntrepotProgrammes programmes;
	private final ServiceChaines serviceChaines;
	
	private ServiceProgrammes() {
		programmes = Entrepots.programmes();
		serviceChaines = ServiceChaines.getInstance();
	}
	
	public static ServiceProgrammes getInstance() {
		if (null == instance) {
			instance = new ServiceProgrammes();
		}
		return instance;
	}
	
	public List<Programme> tout() {
		return programmes.get();
	}
	
	public Programme detailsDe(Integer idProgramme) {
		Programme leProgramme = null;
		for (Programme programme : programmes.get()) {
			if (idProgramme.equals(programme.getId())) {
				leProgramme = programme;
				break;
			}
		}
		return leProgramme;
	}
	
	public List<Programme> programmesDuneChaine(Integer idChaine) {
		Chaine chaine = serviceChaines.detailsDe(idChaine);
		return chaine.getProgrammes();
	}
	
	public List<Programme> programmesDuSoir() {
		List<Programme> lesProgrammesDuSoir = new ArrayList<>();
		List<Chaine> lesChaines = serviceChaines.tout();
		for (Chaine chaine : lesChaines) {
			lesProgrammesDuSoir.addAll(programmesDuSoirDeLaChaine(chaine.getId()));
		}
		return lesProgrammesDuSoir;
	}

	public List<Programme> programmesDuSoirDeLaChaine(Integer idChaine) {
		List<Programme> lesProgrammesDuSoir = new ArrayList<>();
		List<Programme> lesProgrammesDeLaChaine = programmesDuneChaine(idChaine);
		int compteur = 0;
		
		for (Programme programme : lesProgrammesDeLaChaine) {
			if (Constantes.AUJOURDHUI == OutilDate.compareADateDAujourdhui(programme.getDateDebut())
					&& 20 <= programme.getHeureDebut().getHeure() && 2 > compteur) {
				lesProgrammesDuSoir.add(programme);
				compteur++;
			}
		}
		return lesProgrammesDuSoir;//TODO algo heures ?
	}

	public List<Programme> programmesDuMomentDeLaChaine(Integer idChaine) {
		List<Programme> lesProgrammesDuMoment = new ArrayList<>();
		List<Programme> lesProgrammesDeLaChaine = programmesDuneChaine(idChaine);
		int compteur = 0;
		
		for (Programme programme : lesProgrammesDeLaChaine) {
			if (Constantes.AUJOURDHUI == OutilDate.compareADateDAujourdhui(programme.getDateDebut())
					&& OutilDate.heureActuelleInferieureA(programme.getHeureFin()) && 3 > compteur) {
				lesProgrammesDuMoment.add(programme);
				compteur++;
			}
		}
		return lesProgrammesDuMoment;
	}

}
