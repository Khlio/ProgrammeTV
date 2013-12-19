package fr.epsi.progtv;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.ParserXML;
import fr.epsi.progtv.services.ServiceProgrammeTV;

public class MonListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ParserXML.execute(Constantes.FICHIER_XML);
		ServiceProgrammeTV.getInstance().executeTachePlanifiee();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
