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
		Calendar instance = GregorianCalendar.getInstance();
		instance.setTime(date);
		Heure heure = new Heure(instance.get(Calendar.HOUR_OF_DAY), instance.get(Calendar.MINUTE));
		return heure;
	}

	public static int compareADateDAujourdhui(Date date) {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Calendar dateAComparer = GregorianCalendar.getInstance();
		dateAComparer.set(date.getAnnee(), date.getMois()-1, date.getJour());
		return dateAComparer.compareTo(aujourdhui);
	}

	public static boolean heureActuelleInferieureA(Heure heure) {
		boolean estInferieure = false;
		Calendar aujourdhui = GregorianCalendar.getInstance();
		
		if (heure.getHeure() > aujourdhui.get(Calendar.HOUR_OF_DAY)
				|| (heure.getHeure() == aujourdhui.get(Calendar.HOUR_OF_DAY) && heure.getMinute() > aujourdhui.get(Calendar.MINUTE))) {
			estInferieure = !estInferieure;
		}
		return estInferieure;//TODO gérer 23h < 00h
	}
	
}
