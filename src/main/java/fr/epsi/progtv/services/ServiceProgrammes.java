package fr.epsi.progtv.services;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.progtv.entrepots.EntrepotProgrammes;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Chaine;
import fr.epsi.progtv.modeles.Date;
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
		List<Programme> lesProgrammesEntre20hEt21h = programmesEntre20hEt21h(lesProgrammesDeLaChaine);
		Programme premierProgramme = lesProgrammesEntre20hEt21h.get(lesProgrammesEntre20hEt21h.size()-1);
		
		for (int i = 0 ; i < lesProgrammesDeLaChaine.size(); i++) {
			Programme programme = lesProgrammesDeLaChaine.get(i);
			if (programme.equals(premierProgramme)) {
				lesProgrammesDuSoir.add(programme);
				lesProgrammesDuSoir.add(lesProgrammesDeLaChaine.get(i+1));
				break;
			}
		}
		return lesProgrammesDuSoir;
	}
	
	public List<Programme> programmesEntre20hEt21h(List<Programme> programmesDuneChaine) {
		List<Programme> lesProgrammesEntre20het21h = new ArrayList<>();
		for (Programme programme : programmesDuneChaine) {
			if (Constantes.AUJOURDHUI == OutilDate.compareADateDAujourdhui(programme.getDateDebut()) 
					&& 20 <= programme.getHeureDebut().getHeure() && 21 > programme.getHeureDebut().getHeure()) {
				lesProgrammesEntre20het21h.add(programme);
			}
		}
		return lesProgrammesEntre20het21h;
	}

	public List<Programme> programmesDuMoment() {
		List<Programme> lesProgrammesDuMoment = new ArrayList<>();
		List<Chaine> lesChaines = serviceChaines.tout();
		for (Chaine chaine : lesChaines) {
			lesProgrammesDuMoment.addAll(programmesDuMomentDeLaChaine(chaine.getId()));
		}
		return lesProgrammesDuMoment;
	}
	
	public List<Programme> programmesDuMomentDeLaChaine(Integer idChaine) {
		List<Programme> lesProgrammesDuMoment = new ArrayList<>();
		List<Programme> lesProgrammesDeLaChaine = programmesDuneChaine(idChaine);
		
		for (int i = 0; i < lesProgrammesDeLaChaine.size(); i++) {
			Programme programme = lesProgrammesDeLaChaine.get(i);
			if ((Constantes.ANTERIEUR == OutilDate.compareAAujourdhui(programme.getDateDebut(), programme.getHeureDebut())
					|| Constantes.AUJOURDHUI == OutilDate.compareAAujourdhui(programme.getDateDebut(), programme.getHeureDebut()))
					&& Constantes.POSTERIEUR == OutilDate.compareAAujourdhui(programme.getDateFin(), programme.getHeureFin())) {
				lesProgrammesDuMoment.add(programme);
				lesProgrammesDuMoment.add(lesProgrammesDeLaChaine.get(i+1));
				lesProgrammesDuMoment.add(lesProgrammesDeLaChaine.get(i+2));
				break;
			}
		}
		return lesProgrammesDuMoment;
	}
	
	public List<Programme> programmesDuneChaine(Integer idChaine) {
		Chaine chaine = serviceChaines.detailsDe(idChaine);
		return chaine.getProgrammes();
	}
	
	public List<Programme> programmesDuneChaine(Integer idChaine, Date date) {
		List<Programme> lesProgrammesDuneChaine = new ArrayList<>();
		for (Programme programme : programmesDuneChaine(idChaine)) {
			if (programme.getDateDebut().equals(date)) {
				lesProgrammesDuneChaine.add(programme);
			}
		}
		return lesProgrammesDuneChaine;
	}

	public List<Programme> programmesDuneChaine(Integer idChaine, String date) {
		Date laDate = OutilDate.parseDate(date);
		return programmesDuneChaine(idChaine, laDate);
	}

	public Programme detailsDuProgrammePrecedent(Integer idProgramme) {
		Programme programmePrecedent = null;
		Programme leProgramme = detailsDe(idProgramme);
		List<Programme> lesProgrammes = programmesDuneChaine(leProgramme.getChaine().getId(), leProgramme.getDateDebut());
		int positionDuProgramme = lesProgrammes.indexOf(leProgramme);
		if (0 < positionDuProgramme) {
			programmePrecedent = lesProgrammes.get(positionDuProgramme-1);
		}
		return programmePrecedent;
	}

	public Programme detailsDuProgrammeSuivant(Integer idProgramme) {
		Programme programmeSuivant = null;
		Programme leProgramme = detailsDe(idProgramme);
		List<Programme> lesProgrammes = programmesDuneChaine(leProgramme.getChaine().getId(), leProgramme.getDateDebut());
		int positionDuProgramme = lesProgrammes.indexOf(leProgramme);
		if (lesProgrammes.size()-1 > positionDuProgramme) {
			programmeSuivant = lesProgrammes.get(positionDuProgramme+1);
		}
		return programmeSuivant;
	}

}
