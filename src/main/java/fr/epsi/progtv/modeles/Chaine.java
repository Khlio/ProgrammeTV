package fr.epsi.progtv.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Chaine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute
	private Integer id;
	private String nom;
	@XmlTransient
	private List<Programme> programmes = new ArrayList<>();
	
	public Chaine() {
		this(0, "");
	}
	
	public Chaine(String nom) {
		this(0, nom);
	}
	
	public Chaine(Integer id, String nom) {
		setId(id);
		setNom(nom);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public List<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}
	
	public Programme ajouteProgramme(Programme programme) {
		programme.setChaine(this);
		programmes.add(programme);
		return programme;
	}
	
	public Programme supprimeProgramme(Programme programme) {
		programme.setChaine(null);
		programmes.add(programme);
		return programme;
	}

	@Override
	public String toString() {
		return getNom();
	}
	
}
