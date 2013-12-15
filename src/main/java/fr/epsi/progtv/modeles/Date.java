package fr.epsi.progtv.modeles;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Date implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String SEPARATEUR = "-";
	
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
	
	public String getAffichage() {
		return affichage;
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

	public void setAffichage() {
		affichage = (10 > jour ? "0" : "") + jour + SEPARATEUR + (10 > mois ? "0" : "") + mois + SEPARATEUR + annee;
	}
	
	@Override
	public String toString() {
		return getAffichage();
	}
	
}
