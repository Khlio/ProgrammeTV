package fr.epsi.progtv.domaine.chaine;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import fr.epsi.progtv.domaine.Aggregat;
import fr.epsi.progtv.domaine.programme.Programme;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Chaine implements Aggregat, Comparable<Chaine> {

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
	
	public Programme ajouteUnProgramme(Programme programme) {
		programme.setChaine(this);
		programmes.add(programme);
		return programme;
	}
	
	public Programme supprimeUnProgramme(Programme programme) {
		programme.setChaine(null);
		programmes.remove(programme);
		return programme;
	}

	@Override
	public String toString() {
		return getNom();
	}
	
	@Override
	public int compareTo(Chaine uneChaine) {
		return getId().compareTo(uneChaine.getId());
	}
	
	private static final long serialVersionUID = 1L;
	
}
