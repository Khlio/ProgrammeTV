package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeureTest {

	@Test
	public void peutDonnerUneHeure() {
		Heure heure = new Heure();
		heure.setHeure(1);
		
		assertEquals(Integer.valueOf(1), heure.getHeure());
	}
	
	@Test
	public void peutDonnerUneMinute() {
		Heure heure = new Heure();
		heure.setMinute(1);
		
		assertEquals(Integer.valueOf(1), heure.getMinute());
	}
	
	@Test
	public void peutAfficherUneHeure() {
		Heure heure = new Heure(1, 1);
		
		assertEquals("01:01", heure.toString());
	}
	
	@Test
	public void peutComparerAUneHeurePrecedente() {
		Heure heure = new Heure(12, 0);
		Heure heurePrecedente = new Heure(11, 0);
		
		assertEquals(1, heure.compareTo(heurePrecedente));
	}
	
	@Test
	public void peutComparerAUneHeureSuivante() {
		Heure heure = new Heure(11, 0);
		Heure heureSuivante = new Heure(12, 0);
		
		assertEquals(-1, heure.compareTo(heureSuivante));
	}
	
	@Test
	public void peutComparerAUneHeureIdentique() {
		Heure heure = new Heure(16, 4);
		Heure heureIdentique = new Heure(16, 4);
		
		assertEquals(0, heure.compareTo(heureIdentique));
	}
	
}
