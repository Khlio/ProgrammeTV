package fr.epsi.progtv;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.epsi.progtv.services.ServiceProgrammeTV;

public class ListenerProgrammeTV implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServiceProgrammeTV service = ServiceProgrammeTV.getInstance();
		service.recupereLeProgrammeTNT();
		service.executeTachePlanifiee();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServiceProgrammeTV.getInstance().arreteTachePlanifiee();
	}

}
