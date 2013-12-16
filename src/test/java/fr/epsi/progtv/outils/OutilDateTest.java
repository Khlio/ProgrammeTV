package fr.epsi.progtv.outils;

import org.junit.Assert;
import org.junit.Test;

import fr.epsi.progtv.modeles.Date;
import fr.epsi.progtv.modeles.Heure;

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
	public void testCompareDateAnterieureADateDaujourdhui() {
		Date dateAnterieure = new Date();
		int compare = OutilDate.compareADateDAujourdhui(dateAnterieure);
		
		Assert.assertEquals(Constantes.ANTERIEUR, compare);
	}
	
	@Test
	public void testCompareDatePosterieureADateDaujourdhui() {
		Date datePosterieure = new Date(1, 1, 3535);
		int compare = OutilDate.compareADateDAujourdhui(datePosterieure);
		
		Assert.assertEquals(Constantes.POSTERIEUR, compare);
	}
	
	@Test
	public void testCompareDateIdentiqueADateDaujourdhui() {
		int compare = OutilDate.compareADateDAujourdhui(OutilDate.dateAujourdhui());
		
		Assert.assertEquals(Constantes.AUJOURDHUI, compare);
	}
	
	@Test
	public void testCompareAnterieurAAujourdhui() {
		Heure heureAnterieure = new Heure();
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAnterieure);
		
		Assert.assertEquals(Constantes.ANTERIEUR, compare);
	}
	
	@Test
	public void testComparePosterieurAAujourdhui() {
		Heure heurePosterieure = new Heure(23, 59);
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heurePosterieure);
		
		Assert.assertEquals(Constantes.POSTERIEUR, compare);
	}
	
	@Test
	public void testCompareIdentiqueAAujourdhui() {
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		Assert.assertEquals(Constantes.AUJOURDHUI, compare);
	}
	
	@Test
	public void testDateAujourdhui() {
		Date dateAujourdhui = OutilDate.dateAujourdhui();
		
		Assert.assertNotNull(dateAujourdhui);
		Assert.assertEquals(Constantes.AUJOURDHUI, OutilDate.compareADateDAujourdhui(dateAujourdhui));
	}
	
	@Test
	public void testHeureAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		
		Assert.assertNotNull(heureAujourdhui);
		Assert.assertEquals(Constantes.AUJOURDHUI, OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAujourdhui));
	}
	
}
