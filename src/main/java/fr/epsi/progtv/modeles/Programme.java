package fr.epsi.progtv.modeles;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"id", "nom", "description", "dateDebut", "dateFin", "heureDebut", "heureFin", "chaine", "csa", "categorie", "acteurs", "realisateur"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Programme implements Serializable {
	
	private static final long serialVersionUID = 4512555169699413723L;
	
	private static Integer idCourant = 1;
	
	@XmlAttribute
	private Integer id;
	private String nom;
	private String description;
	private Date dateDebut;
	private Date dateFin;
	private Heure heureDebut;
	private Heure heureFin;
	private Chaine chaine;
	private String csa;
	private String categorie;
	private List<Personne> acteurs;
	private Personne realisateur;
	
	public Programme() {
		this("");
	}
	
	public Programme(String nom) {
		this(nom, "", new Date(), new Date(), new Heure(), new Heure(), new Chaine(), "", "", null, null);
	}
	
	public Programme(String nom, String description, Date dateDebut, Date dateFin, Heure heureDebut, Heure heureFin, 
			Chaine chaine, String csa, String categorie, List<Personne> acteurs, Personne realisateur) {
		setId(incrementeId());
		setNom(nom);
		setDescription(description);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setHeureDebut(heureDebut);
		setHeureFin(heureFin);
		setChaine(chaine);
		setCsa(csa);
		setCategorie(categorie);
		setActeurs(acteurs);
		setRealisateur(realisateur);
	}
	
	public static void resetId() {
		idCourant = 1;
	}
	
	public static Integer decrementeId() {
		return idCourant--;
	}
	
	public static Integer incrementeId() {
		return idCourant++;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Heure getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Heure heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Heure getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Heure heureFin) {
		this.heureFin = heureFin;
	}

	public Chaine getChaine() {
		return chaine;
	}

	public void setChaine(Chaine chaine) {
		this.chaine = chaine;
	}
	
	public String getCsa() {
		return csa;
	}

	public void setCsa(String csa) {
		this.csa = csa;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		if (2 <= categorie.length()) {
			this.categorie = categorie.substring(0, 1).toUpperCase() + categorie.substring(1);
		} else {
			this.categorie = categorie;
		}
	}

	public List<Personne> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<Personne> acteurs) {
		this.acteurs = acteurs;
	}
	
	public Personne ajouteActeur(Personne acteur) {
		acteurs.add(acteur);
		return acteur;
	}
	
	public Personne supprimeActeur(Personne acteur) {
		acteurs.remove(acteur);
		return acteur;
	}

	public Personne getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Personne realisateur) {
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return getNom();
	}
	
}
