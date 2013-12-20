package fr.epsi.progtv;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.epsi.progtv.services.ServiceProgrammeTV;

public class JobProgrammeTV implements Job {

	@Override
	public void execute(JobExecutionContext contexte) throws JobExecutionException {
		ServiceProgrammeTV.getInstance().recupereLeProgrammeTNT();
	}

}
