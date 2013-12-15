package fr.epsi.progtv.modeles;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Heure implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
	public String getAffichage() {
		return affichage;
	}
	
	public void setAffichage() {
		affichage = (10 > heure ? "0" : "") + heure + ":" + (10 > minute ? "0" : "") + minute;
	}
	
	@Override
	public String toString() {
		return getAffichage();
	}
	
}
