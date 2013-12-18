package fr.epsi.progtv.outils;

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
		String heure = heureATransformer.substring(8, 10);
		String minute = heureATransformer.substring(10, 12);
		Heure instance = new Heure(Integer.valueOf(heure), Integer.valueOf(minute));
		return instance;
	}

	public static int compareADateDAujourdhui(Date date) {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Calendar dateAComparer = GregorianCalendar.getInstance();
		dateAComparer.set(date.getAnnee(), date.getMois()-1, date.getJour());
		return dateAComparer.compareTo(aujourdhui);
	}
	
	public static int compareAAujourdhui(Date date, Heure heure) {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		aujourdhui.set(Calendar.SECOND, 0);
		aujourdhui.set(Calendar.MILLISECOND, 0);
		Calendar aComparer = GregorianCalendar.getInstance();
		aComparer.set(date.getAnnee(), date.getMois()-1, date.getJour(), heure.getHeure(), heure.getMinute(), 0);
		aComparer.set(Calendar.MILLISECOND, 0);
		return aComparer.compareTo(aujourdhui);
	}
	
	public static boolean aujourdhuiEstEntre(Date dateDebut, Heure heureDebut, Date dateFin, Heure heureFin) {
		boolean estEntre = false;
		if ((Constantes.ANTERIEUR == compareAAujourdhui(dateDebut, heureDebut) || Constantes.AUJOURDHUI == compareAAujourdhui(dateDebut, heureDebut))
				&& Constantes.POSTERIEUR == compareAAujourdhui(dateFin, heureFin)) {
			estEntre = !estEntre;
		}
		return estEntre;
	}

	public static Date dateAujourdhui() {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Date dateAujourdhui = new Date(aujourdhui.get(Calendar.DAY_OF_MONTH), aujourdhui.get(Calendar.MONTH)+1, aujourdhui.get(Calendar.YEAR));
		return dateAujourdhui;
	}

	public static Heure heureAujourdhui() {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Heure heureAujourdhui = new Heure(aujourdhui.get(Calendar.HOUR_OF_DAY), aujourdhui.get(Calendar.MINUTE));
		return heureAujourdhui;
	}

	public static Integer obtenirLaDuree(Heure heureDebut, Heure heureFin) {
		Integer heureDebutEnMinutes = heureDebut.getHeure()*60 + heureDebut.getMinute();
		Integer heureFinEnMinutes = (heureFin.getHeure() + (heureDebut.getHeure() > heureFin.getHeure() ? 24 : 0)) * 60 + heureFin.getMinute();
		Integer duree = heureFinEnMinutes - heureDebutEnMinutes;
		return duree;
	}
	
}
