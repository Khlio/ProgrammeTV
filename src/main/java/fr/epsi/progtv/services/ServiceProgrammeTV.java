package fr.epsi.progtv.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import de.schlichtherle.truezip.nio.file.TPath;
import fr.epsi.progtv.JobProgrammeTV;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.ParserXML;

public class ServiceProgrammeTV {

	private static ServiceProgrammeTV instance;
	
	private Scheduler ordonnanceur;
	
	private ServiceProgrammeTV() {
	}
	
	public static ServiceProgrammeTV getInstance() {
		if (null == instance) {
			instance = new ServiceProgrammeTV();
		}
		return instance;
	}
	
	public void recupereLeProgrammeTNT() {
		InputStream fluxEntrant = null;
		try {
			Path chemin = new TPath(new URI(Constantes.URL_ZIP_XML));
			fluxEntrant = Files.newInputStream(chemin);
			ParserXML.execute(fluxEntrant);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fluxEntrant) {
					fluxEntrant.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void executeTachePlanifiee() {
		JobDetail job = JobBuilder.newJob(JobProgrammeTV.class).build();
		Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0 3 * * ?")).build();
		
		try {
			ordonnanceur = new StdSchedulerFactory().getScheduler();
			ordonnanceur.start();
			ordonnanceur.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void arreteTachePlanifiee() {
		try {
			if (null != ordonnanceur && ordonnanceur.isStarted()) {
				ordonnanceur.shutdown(true);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
