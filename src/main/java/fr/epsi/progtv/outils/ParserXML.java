package fr.epsi.progtv.outils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import fr.epsi.progtv.entrepots.EntrepotChaines;
import fr.epsi.progtv.entrepots.EntrepotProgrammes;
import fr.epsi.progtv.entrepots.Entrepots;
import fr.epsi.progtv.modeles.Acteur;
import fr.epsi.progtv.modeles.Chaine;
import fr.epsi.progtv.modeles.Personne;
import fr.epsi.progtv.modeles.Programme;
import fr.epsi.progtv.modeles.Realisateur;
import fr.epsi.progtv.services.ServiceChaines;

public class ParserXML {
	
	private ParserXML() {
	}
	
	public static void execute(String fichierXML) {
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		try {
			document = sxb.build(ParserXML.class.getClassLoader().getResourceAsStream(fichierXML));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		chargerChaine(document);
		chargerProgrammes(document);
	}

	private static void chargerChaine(Document document) {
		EntrepotChaines entrepotChaines = Entrepots.chaines();
		entrepotChaines.nettoie();
		
		List<Element> lesChaines = document.getRootElement().getChildren("channel");
		for (int i = 0; i < 19; i++) {
			Element laChaine = lesChaines.get(i);
			Integer id = Integer.valueOf(laChaine.getAttributeValue("id"));
			String nom = laChaine.getChildText("display-name");
			entrepotChaines.ajoute(new Chaine(id, nom));
		}
	}
	
	private static void chargerProgrammes(Document document) {
		EntrepotProgrammes entrepotProgrammes = Entrepots.programmes();
		entrepotProgrammes.nettoie();
		ServiceChaines serviceChaines = ServiceChaines.getInstance();
		
		List<Element> lesProgrammes = document.getRootElement().getChildren("programme");
		for (Element leProgramme : lesProgrammes) {
			String nom = leProgramme.getChildText("title");
			String description = leProgramme.getChildText("desc");
			Chaine chaine = serviceChaines.detailsDe(Integer.valueOf(leProgramme.getAttributeValue("channel")));
			String dateDebut = leProgramme.getAttributeValue("start");
			String dateFin = leProgramme.getAttributeValue("stop");
			String csa = leProgramme.getChild("rating").getChildText("value");
			String categorie = leProgramme.getChildText("category");
			Element lesCredits = leProgramme.getChild("credits");
			List<Personne> acteurs = new ArrayList<>();
			Personne realisateur = null;
			if (null != lesCredits) {
				List<Element> lesActeurs = lesCredits.getChildren("actor");
				for (Element element : lesActeurs) {
					Personne acteur = new Acteur(element.getText());
					acteurs.add(acteur);
				}
				realisateur = new Realisateur(lesCredits.getChildText("director"));
			}
			Element icone = leProgramme.getChild("icon");
			String image = (null != icone ? icone.getAttributeValue("src") : null);
			String dateRealisation = leProgramme.getChildText("date");
			String deuxiemeNom = leProgramme.getChildText("sub-title");
			
			Programme programme = chaine.ajouteProgramme(new Programme(nom, description, OutilDate.parseDate(dateDebut), OutilDate.parseDate(dateFin), 
					OutilDate.parseHeure(dateDebut), OutilDate.parseHeure(dateFin), chaine, csa, categorie, acteurs, realisateur, image, 
					dateRealisation, deuxiemeNom));
			entrepotProgrammes.ajoute(programme);
		}
	}

}
