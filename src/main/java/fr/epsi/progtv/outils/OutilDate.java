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
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Calendar heureAComparer = GregorianCalendar.getInstance();
		heureAComparer.set(aujourdhui.get(Calendar.YEAR), aujourdhui.get(Calendar.MONTH), 
				aujourdhui.get(Calendar.DAY_OF_MONTH) + (23 == aujourdhui.get(Calendar.HOUR_OF_DAY) && 0 <= heure.getHeure() && 4 >= heure.getHeure() ? 1 : 0), 
				heure.getHeure(), heure.getMinute());
		return aujourdhui.before(heureAComparer);
	}

	public static Date dateAujourdhui() {
		Calendar aujourdhui = GregorianCalendar.getInstance();
		Date dateAujourdhui = new Date(aujourdhui.get(Calendar.DAY_OF_MONTH), aujourdhui.get(Calendar.MONTH)+1, aujourdhui.get(Calendar.YEAR));
		return dateAujourdhui;
	}
	
}
