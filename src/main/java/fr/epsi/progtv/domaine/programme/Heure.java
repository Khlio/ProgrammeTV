package fr.epsi.progtv.domaine.programme;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import fr.epsi.progtv.outils.OutilDate;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Heure implements Serializable, Comparable<Heure> {

	@XmlTransient
	private Integer heure;
	@XmlTransient
	private Integer minute;
	@XmlValue
	private String affichage;
	
	public Heure() {
		this(0, 0);
	}
	
	public Heure(Integer heure, Integer minute) {
		setHeure(heure);
		setMinute(minute);
		setAffichage();
	}

	public Integer getHeure() {
		return heure;
	}

	public void setHeure(Integer heure) {
		this.heure = heure;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public void setAffichage() {
		affichage = (10 > heure ? "0" : "") + heure + ":" + (10 > minute ? "0" : "") + minute;
	}
	
	@Override
	public String toString() {
		return affichage;
	}

	@Override
	public boolean equals(Object unObjet) {
		boolean egal = false;
		if (this == unObjet) {
			egal = true;
		} else if (unObjet instanceof Heure) {
			Heure uneHeure = (Heure)unObjet;
			if (getMinute().equals(uneHeure.getMinute())
					&& getHeure().equals(uneHeure.getHeure())) {
				egal = true;
			}
		}
		return egal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int resultat = 1;
		resultat = prime * resultat + ((null == getMinute()) ? 0 : getMinute().hashCode());
		resultat = prime * resultat + ((null == getHeure()) ? 0 : getHeure().hashCode());
		return resultat;
	}
	
	@Override
	public int compareTo(Heure uneHeure) {
		Calendar cetteHeure = OutilDate.calendrierAujourdhui();
		cetteHeure.set(Calendar.HOUR_OF_DAY, getHeure());
		cetteHeure.set(Calendar.MINUTE, getMinute());
		cetteHeure.set(Calendar.SECOND, 0);
		cetteHeure.set(Calendar.MILLISECOND, 0);
		Calendar heureAComparer = OutilDate.calendrierAujourdhui();
		heureAComparer.set(Calendar.HOUR_OF_DAY, uneHeure.getHeure());
		heureAComparer.set(Calendar.MINUTE, uneHeure.getMinute());
		heureAComparer.set(Calendar.SECOND, 0);
		heureAComparer.set(Calendar.MILLISECOND, 0);
		return cetteHeure.compareTo(heureAComparer);
	}

	private static final long serialVersionUID = 1L;
	
}
