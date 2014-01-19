package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class DateTest {

	@Test
	public void peutDonnerUnJour() {
		Date date = new Date();
		date.setJour(1);
		
		assertThat(date.getJour()).isEqualTo(1);
	}
	
	@Test
	public void peutDonnerUnMois() {
		Date date = new Date();
		date.setMois(1);
		
		assertThat(date.getMois()).isEqualTo(1);
	}
	
	@Test
	public void peutDonnerUneAnnee() {
		Date date = new Date();
		date.setAnnee(2000);
		
		assertThat(date.getAnnee()).isEqualTo(2000);
	}
	
	@Test
	public void peutAfficherUneDate() {
		Date date = new Date(1, 1, 2000);
		
		assertThat(date.toString()).isEqualTo("01-01-2000");
	}
	
	@Test
	public void peutComparerAUneDatePrecedente() {
		Date date = new Date(1, 1, 2014);
		Date datePrecedente = new Date(31, 12, 2013);
		
		assertThat(date.compareTo(datePrecedente)).isEqualTo(1);
	}
	
	@Test
	public void peutComparerAUneDateSuivante() {
		Date date = new Date(31, 12, 2013);
		Date dateSuivante = new Date(1, 1, 2014);
		
		assertThat(date.compareTo(dateSuivante)).isEqualTo(-1);
	}
	
	@Test
	public void peutComparerAUneDateIdentique() {
		Date date = new Date(25, 9, 1990);
		Date dateIdentique = new Date(25, 9, 1990);
		
		assertThat(date.compareTo(dateIdentique)).isEqualTo(0);
	}
	
	@Test
	public void peutDonnerUnHashCode() {
		Date date = new Date(1, 1, 2014);
		
		assertThat(date.hashCode()).isEqualTo(32797);
	}
	
}
