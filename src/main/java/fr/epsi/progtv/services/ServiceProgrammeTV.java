package fr.epsi.progtv.services;

import java.io.File;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import fr.epsi.progtv.JobProgrammeTV;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.Decompresser;
import fr.epsi.progtv.outils.ParserXML;
import fr.epsi.progtv.outils.Telecharger;

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
		File fichierZIP = Telecharger.execute(Constantes.URL_ZIP);
		File fichierXML = Decompresser.execute(fichierZIP, System.getProperty("user.home"), true).get(0);
		ParserXML.execute(fichierXML.getAbsolutePath());
		fichierXML.delete();
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
