package fr.epsi.progtv.outils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.epsi.progtv.domaine.programme.Date;
import fr.epsi.progtv.domaine.programme.Heure;

public class OutilDateTest {

	@Test
	public void peutParserDate() {
		String dateATransformer = "20131205010500 +0100";
		Date date = OutilDate.parseDate(dateATransformer);
		
		assertNotNull(date);
		assertEquals("05-12-2013", date.toString());
	}
	
	@Test
	public void peutParserHeure() {
		String heureATransformer = "20131205125000 +0100";
		Heure heure = OutilDate.parseHeure(heureATransformer);
		
		assertNotNull(heure);
		assertEquals("12:50", heure.toString());
	}
	
	@Test
	public void peutComparerDateAnterieureADateDaujourdhui() {
		Date dateAnterieure = new Date();
		int compare = OutilDate.compareADateDAujourdhui(dateAnterieure);
		
		assertEquals(Constantes.ANTERIEUR, compare);
	}
	
	@Test
	public void peutComparerDatePosterieureADateDaujourdhui() {
		Date datePosterieure = new Date(1, 1, 3535);
		int compare = OutilDate.compareADateDAujourdhui(datePosterieure);
		
		assertEquals(Constantes.POSTERIEUR, compare);
	}
	
	@Test
	public void peutComparerDateIdentiqueADateDaujourdhui() {
		int compare = OutilDate.compareADateDAujourdhui(OutilDate.dateAujourdhui());
		
		assertEquals(Constantes.AUJOURDHUI, compare);
	}
	
	@Test
	public void peutComparerAnterieurAAujourdhui() {
		Heure heureAnterieure = new Heure();
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAnterieure);
		
		assertEquals(Constantes.ANTERIEUR, compare);
	}
	
	@Test
	public void peutComparerPosterieurAAujourdhui() {
		Heure heurePosterieure = new Heure(23, 59);
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heurePosterieure);
		
		assertEquals(Constantes.POSTERIEUR, compare);
	}
	
	@Test
	public void peutComparerIdentiqueAAujourdhui() {
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		assertEquals(Constantes.AUJOURDHUI, compare);
	}
	
	@Test
	public void peutVerifierQuAujourdhuiEstEntreUneDate() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), heureFin);
		
		assertTrue(estEntre);
	}
	
	@Test
	public void peutVerifierQuAujourdhuiNestPasEntreUneDate() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		assertFalse(estEntre);
	}
	
	@Test
	public void peutVerifierQuAujourdhuiEstEntreAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui(), OutilDate.dateAujourdhui(), heureFin);
		
		assertTrue(estEntre);
	}
	
	@Test
	public void peutRecupererLaDateDAujourdhui() {
		Date dateAujourdhui = OutilDate.dateAujourdhui();
		
		assertNotNull(dateAujourdhui);
		assertEquals(Constantes.AUJOURDHUI, OutilDate.compareADateDAujourdhui(dateAujourdhui));
	}
	
	@Test
	public void peutRecupererLHeureDAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		
		assertNotNull(heureAujourdhui);
		assertEquals(Constantes.AUJOURDHUI, OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAujourdhui));
	}
	
	@Test
	public void peutObtenirLaDuree() {
		Heure heureDebut = new Heure(12, 0);
		Heure heureFin = new Heure(16, 16);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertNotNull(duree);
		assertEquals(256, duree.intValue());
	}
	
	@Test
	public void peutObtenirLaDureeQuandHeureDebutSuperieureAHeureFin() {
		Heure heureDebut = new Heure(23, 10);
		Heure heureFin = new Heure(0, 45);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertNotNull(duree);
		assertEquals(95, duree.intValue());
	}
	
	@Test
	public void peutObtenirLaDureeQuandHeureIdentiqueMaisPasMinutes() {
		Heure heureDebut = new Heure(18, 0);
		Heure heureFin = new Heure(18, 59);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertNotNull(duree);
		assertEquals(59, duree.intValue());
	}
	
}
