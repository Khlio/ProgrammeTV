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
		EntrepotChaines chaines = Entrepots.chaines();
		chaines.nettoie();
		
		List<Element> lesChaines = document.getRootElement().getChildren("channel");
		for (int i = 0; i < 19; i++) {
			Element uneChaine = lesChaines.get(i);
			Integer id = Integer.valueOf(uneChaine.getAttributeValue("id"));
			String nom = uneChaine.getChildText("display-name");
			chaines.ajoute(new Chaine(id, nom));
		}
	}
	
	private static void chargerProgrammes(Document document) {
		EntrepotProgrammes programmes = Entrepots.programmes();
		programmes.nettoie();
		ServiceChaines serviceChaines = ServiceChaines.getInstance();
		
		List<Element> lesProgrammes = document.getRootElement().getChildren("programme");
		for (Element unProgramme : lesProgrammes) {
			String nom = unProgramme.getChildText("title");
			String description = unProgramme.getChildText("desc");
			Chaine chaine = serviceChaines.detailsDe(Integer.valueOf(unProgramme.getAttributeValue("channel")));
			String dateDebut = unProgramme.getAttributeValue("start");
			String dateFin = unProgramme.getAttributeValue("stop");
			String csa = unProgramme.getChild("rating").getChildText("value");
			String categorie = unProgramme.getChildText("category");
			
			Element lesCredits = unProgramme.getChild("credits");
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
			
			Programme programme = chaine.ajouteProgramme(new Programme(nom, description, OutilDate.parseDate(dateDebut), OutilDate.parseDate(dateFin), 
					OutilDate.parseHeure(dateDebut), OutilDate.parseHeure(dateFin), chaine, csa, categorie, acteurs, realisateur));
			programmes.ajoute(programme);
		}
	}

}
