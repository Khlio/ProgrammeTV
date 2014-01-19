package fr.epsi.progtv.outils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.domaine.chaine.EntrepotChaines;
import fr.epsi.progtv.domaine.programme.Acteur;
import fr.epsi.progtv.domaine.programme.EntrepotProgrammes;
import fr.epsi.progtv.domaine.programme.Personne;
import fr.epsi.progtv.domaine.programme.Programme;
import fr.epsi.progtv.domaine.programme.Realisateur;
import fr.epsi.progtv.services.ServiceChaines;

public final class ParserXML {
	
	private ParserXML() {
	}
	
	public static void execute(InputStream fichierXML) throws JDOMException, IOException {
		SAXBuilder sxb = new SAXBuilder();
		Document document = null;
		sxb.setXMLReaderFactory(XMLReaders.NONVALIDATING);
		sxb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		document = sxb.build(fichierXML);
		Entrepots.nettoie();
		chargerChaine(document);
		chargerProgrammes(document);
	}

	private static void chargerChaine(Document document) {
		EntrepotChaines entrepotChaines = Entrepots.chaines();
		
		List<Element> lesChaines = document.getRootElement().getChildren("channel");
		for (Element laChaine : lesChaines) {
			Integer id = Integer.valueOf(laChaine.getAttributeValue("id"));
			String nom = laChaine.getChildText("display-name");
			entrepotChaines.ajoute(new Chaine(id, nom));
		}
		entrepotChaines.trie();
	}
	
	private static void chargerProgrammes(Document document) {
		EntrepotProgrammes entrepotProgrammes = Entrepots.programmes();
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
			
			Programme programme = chaine.ajouteUnProgramme(new Programme(nom, description, OutilDate.parseDate(dateDebut), OutilDate.parseDate(dateFin), 
					OutilDate.parseHeure(dateDebut), OutilDate.parseHeure(dateFin), chaine, csa, categorie, acteurs, realisateur, image, 
					dateRealisation, deuxiemeNom));
			entrepotProgrammes.ajoute(programme);
		}
		entrepotProgrammes.trie();
	}

}
