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
public class Date implements Serializable, Comparable<Date> {
	
	@XmlTransient
	private Integer jour;
	@XmlTransient
	private Integer mois;
	@XmlTransient
	private Integer annee;
	@XmlValue
	private String affichage;

	public Date() {
		this(1, 1, 2000);
	}
	
	public Date(Integer jour, Integer mois, Integer annee) {
		setJour(jour);
		setMois(mois);
		setAnnee(annee);
		setAffichage();
	}
	
	public Integer getJour() {
		return jour;
	}

	public void setJour(Integer jour) {
		this.jour = jour;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	private void setAffichage() {
		affichage = (10 > jour ? "0" : "") + jour + SEPARATEUR + (10 > mois ? "0" : "") + mois + SEPARATEUR + annee;
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
		} else if (unObjet instanceof Date) {
			Date uneDate = (Date)unObjet;
			if (getJour().equals(uneDate.getJour()) 
					&& getMois().equals(uneDate.getMois())
					&& getAnnee().equals(uneDate.getAnnee())) {
				egal = true;
			}
		}
		return egal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int resultat = 1;
		resultat = prime * resultat + ((null == getJour()) ? 0 : getJour().hashCode());
		resultat = prime * resultat + ((null == getMois()) ? 0 : getMois().hashCode());
		resultat = prime * resultat + ((null == getAnnee()) ? 0 : getAnnee().hashCode());
		return resultat;
	}
	
	@Override
	public int compareTo(Date uneDate) {
		Calendar cetteDate = OutilDate.calendrierAujourdhui();
		cetteDate.set(getAnnee(), getMois()-1, getJour());
		cetteDate.set(Calendar.SECOND, 0);
		cetteDate.set(Calendar.MILLISECOND, 0);
		Calendar dateAComparer = OutilDate.calendrierAujourdhui();
		dateAComparer.set(uneDate.getAnnee(), uneDate.getMois()-1, uneDate.getJour());
		dateAComparer.set(Calendar.SECOND, 0);
		dateAComparer.set(Calendar.MILLISECOND, 0);
		return cetteDate.compareTo(dateAComparer);
	}
	
	private static final long serialVersionUID = 1L;
	private static final String SEPARATEUR = "-";
	
}
