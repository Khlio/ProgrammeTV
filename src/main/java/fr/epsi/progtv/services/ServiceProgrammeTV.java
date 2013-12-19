package fr.epsi.progtv.services;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.Decompresser;
import fr.epsi.progtv.outils.Telecharger;

public class ServiceProgrammeTV {

	private static ServiceProgrammeTV instance;
	
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
		Decompresser.execute(fichierZIP, Constantes.DOSSIER_RESOURCES, true);
	}
	
	public void executeTachePlanifiee() {
		Timer t = new Timer();
		Calendar instance = GregorianCalendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, 1);
		instance.set(Calendar.HOUR_OF_DAY, 3);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MILLISECOND, 0);
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				recupereLeProgrammeTNT();
			}
		}, instance.getTimeInMillis(), 24*3600*1000);
	}
	
}
