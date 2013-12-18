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
		String heureATransformer = "20131205125000 +0100";
		Heure heure = OutilDate.parseHeure(heureATransformer);
		
		Assert.assertNotNull(heure);
		Assert.assertEquals("12:50", heure.toString());
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
	public void testAujourdhuiEstEntre() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), heureFin);
		
		Assert.assertTrue(estEntre);
	}
	
	@Test
	public void testAujourdhuiNestPasEntre() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		Assert.assertFalse(estEntre);
	}
	
	@Test
	public void testAujourdhuiEstEntreAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui(), OutilDate.dateAujourdhui(), heureFin);
		
		Assert.assertTrue(estEntre);
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
	
	@Test
	public void testObtenirLaDuree() {
		Heure heureDebut = new Heure(12, 0);
		Heure heureFin = new Heure(16, 16);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		Assert.assertNotNull(duree);
		Assert.assertEquals(256, duree.intValue());
	}
	
	@Test
	public void testObtenirLaDureeQuandHeureDebutSuperieureAHeureFin() {
		Heure heureDebut = new Heure(23, 10);
		Heure heureFin = new Heure(0, 45);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		Assert.assertNotNull(duree);
		Assert.assertEquals(95, duree.intValue());
	}
	
	@Test
	public void testObtenirLaDureeQuandHeureIdentiqueMaisPasMinutes() {
		Heure heureDebut = new Heure(18, 0);
		Heure heureFin = new Heure(18, 59);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		Assert.assertNotNull(duree);
		Assert.assertEquals(59, duree.intValue());
	}
	
}
