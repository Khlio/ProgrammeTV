package fr.epsi.progtv.domaine.programme;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Personne implements Serializable {

	private String nomComplet;
	
	public Personne() {
		this("");
	}
	
	public Personne(String nomComplet) {
		setNomComplet(nomComplet);
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	
	@Override
	public String toString() {
		return getNomComplet();
	}
	
	private static final long serialVersionUID = 1L;
	
}
