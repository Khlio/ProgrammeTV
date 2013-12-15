package fr.epsi.progtv.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fr.epsi.progtv.modeles.Date;
import fr.epsi.progtv.modeles.Heure;

public class OutilDate {

	private OutilDate() {
	}
	
	public static Date parseDate(String dateATransformer) {
		String annee = dateATransformer.substring(0, 4);
		String mois = dateATransformer.substring(4, 6);
		String jour = dateATransformer.substring(6, 8);
		Date date = new Date(Integer.valueOf(jour), Integer.valueOf(mois), Integer.valueOf(annee));
		return date;
	}
	
	public static Heure parseHeure(String heureATransformer) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		java.util.Date date = null;
		try {
			date = sdf.parse(heureATransformer);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		Heure heure = new Heure(gc.get(Calendar.HOUR_OF_DAY), gc.get(Calendar.MINUTE));
		return heure;
	}

	public static int compareADateDAujourdhui(Date date) {
		int compare;
		java.util.Date aujourdhui = new java.util.Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(aujourdhui);
		
		if (date.getAnnee() == gc.get(Calendar.YEAR)) {
			if (date.getMois() == gc.get(Calendar.MONTH)+1) {
				if (date.getJour() == gc.get(Calendar.DAY_OF_MONTH)) {
					compare = Constantes.AUJOURDHUI;
				} else if (date.getJour() > gc.get(Calendar.DAY_OF_MONTH)) {
					compare = Constantes.POSTERIEUR;
				} else {
					compare = Constantes.ANTERIEUR;
				}
			} else if (date.getMois() > gc.get(Calendar.MONTH)+1) {
				compare = Constantes.POSTERIEUR;
			} else {
				compare = Constantes.ANTERIEUR;
			}
		} else if (date.getAnnee() > gc.get(Calendar.YEAR)) {
			compare = Constantes.POSTERIEUR;
		} else {
			compare = Constantes.ANTERIEUR;
		}
		return compare;
	}

	public static boolean heureActuelleInferieureA(Heure heure) {
		boolean estInferieure = false;
		java.util.Date aujourdhui = new java.util.Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(aujourdhui);
		
		if (heure.getHeure() > gc.get(Calendar.HOUR_OF_DAY)
				|| (heure.getHeure() == gc.get(Calendar.HOUR_OF_DAY) && heure.getMinute() > gc.get(Calendar.MINUTE))) {
			estInferieure = !estInferieure;
		}
		return estInferieure;//TODO gérer 23h < 00h
	}
	
}
