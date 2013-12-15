package fr.epsi.progtv;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.ParserXML;

public class MonListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ParserXML.execute(Constantes.FICHIER_XML);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
