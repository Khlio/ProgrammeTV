package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateTest {

	@Test
	public void peutDonnerUnJour() {
		Date date = new Date();
		date.setJour(1);
		
		assertEquals(Integer.valueOf(1), date.getJour());
	}
	
	@Test
	public void peutDonnerUnMois() {
		Date date = new Date();
		date.setMois(1);
		
		assertEquals(Integer.valueOf(1), date.getMois());
	}
	
	@Test
	public void peutDonnerUneAnnee() {
		Date date = new Date();
		date.setAnnee(2000);
		
		assertEquals(Integer.valueOf(2000), date.getAnnee());
	}
	
	@Test
	public void peutAfficherUneDate() {
		Date date = new Date(1, 1, 2000);
		
		assertEquals("01-01-2000", date.toString());
	}
	
	@Test
	public void peutComparerAUneDatePrecedente() {
		Date date = new Date(1, 1, 2014);
		Date datePrecedente = new Date(31, 12, 2013);
		
		assertEquals(1, date.compareTo(datePrecedente));
	}
	
	@Test
	public void peutComparerAUneDateSuivante() {
		Date date = new Date(31, 12, 2013);
		Date dateSuivante = new Date(1, 1, 2014);
		
		assertEquals(-1, date.compareTo(dateSuivante));
	}
	
	@Test
	public void peutComparerAUneDateIdentique() {
		Date date = new Date(25, 9, 1990);
		Date dateIdentique = new Date(25, 9, 1990);
		
		assertEquals(0, date.compareTo(dateIdentique));
	}
	
}
