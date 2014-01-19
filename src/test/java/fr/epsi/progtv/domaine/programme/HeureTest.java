package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class HeureTest {

	@Test
	public void peutDonnerUneHeure() {
		Heure heure = new Heure();
		heure.setHeure(1);
		
		assertThat(heure.getHeure()).isEqualTo(1);
	}
	
	@Test
	public void peutDonnerUneMinute() {
		Heure heure = new Heure();
		heure.setMinute(1);
		
		assertThat(heure.getMinute()).isEqualTo(1);
	}
	
	@Test
	public void peutAfficherUneHeure() {
		Heure heure = new Heure(1, 1);
		
		assertThat(heure.toString()).isEqualTo("01:01");
	}
	
	@Test
	public void peutComparerAUneHeurePrecedente() {
		Heure heure = new Heure(12, 0);
		Heure heurePrecedente = new Heure(11, 0);
		
		assertThat(heure.compareTo(heurePrecedente)).isEqualTo(1);
	}
	
	@Test
	public void peutComparerAUneHeureSuivante() {
		Heure heure = new Heure(11, 0);
		Heure heureSuivante = new Heure(12, 0);
		
		assertThat(heure.compareTo(heureSuivante)).isEqualTo(-1);
	}
	
	@Test
	public void peutComparerAUneHeureIdentique() {
		Heure heure = new Heure(16, 4);
		Heure heureIdentique = new Heure(16, 4);
		
		assertThat(heure.compareTo(heureIdentique)).isEqualTo(0);
	}
	
	@Test
	public void peutDonnerUnHashCode() {
		Heure heure = new Heure(16, 4);
		
		assertThat(heure.hashCode()).isEqualTo(1101);
	}
	
}
