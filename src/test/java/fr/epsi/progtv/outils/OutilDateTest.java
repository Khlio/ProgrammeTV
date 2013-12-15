package fr.epsi.progtv.outils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import fr.epsi.progtv.modeles.Date;
import fr.epsi.progtv.modeles.Heure;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.outils.OutilDate;

public class OutilDateTest {

	@Test
	public void testParserDate() {
		String dateATransformer = "20131205010500 +0100";
		Date date = OutilDate.parseDate(dateATransformer);
		
		Assert.assertNotNull(date);
		Assert.assertEquals("05-12-2013", date.toString());
	}
	
	@Test
	public void testParserHeure() {
		String heureATransformer = "20131205205000 +0100";
		Heure heure = OutilDate.parseHeure(heureATransformer);
		
		Assert.assertNotNull(heure);
		Assert.assertEquals("20:50", heure.toString());
	}
	
	@Test
	public void testCompareDateAnterieurADateDaujourdhui() {
		Date dateAnterieur = new Date();
		int compare = OutilDate.compareADateDAujourdhui(dateAnterieur);
		
		Assert.assertEquals(Constantes.ANTERIEUR, compare);
	}
	
	@Test
	public void testCompareDatePosterieurADateDaujourdhui() {
		Date dateSuperieur = new Date(1, 1, 3535);
		int compare = OutilDate.compareADateDAujourdhui(dateSuperieur);
		
		Assert.assertEquals(Constantes.POSTERIEUR, compare);
	}
	
	@Test
	public void testCompareDateIdentiqueADateDaujourdhui() {
		java.util.Date aujourdhui = new java.util.Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(aujourdhui);
		Date dateIdentique = new Date(gc.get(Calendar.DAY_OF_MONTH), gc.get(Calendar.MONTH)+1, gc.get(Calendar.YEAR));
		int compare = OutilDate.compareADateDAujourdhui(dateIdentique);
		
		Assert.assertEquals(Constantes.AUJOURDHUI, compare);
	}
	
	@Test
	public void testHeureActuelleInferieureAUneHeure() {
		java.util.Date aujourdhui = new java.util.Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(aujourdhui);
		Heure heure = new Heure(gc.get(Calendar.HOUR_OF_DAY), gc.get(Calendar.MINUTE)+1);
		boolean estInferieure = OutilDate.heureActuelleInferieureA(heure);
		
		Assert.assertTrue(estInferieure);
	}
	
}
