package fr.epsi.progtv.outils;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.progtv.domaine.programme.Date;
import fr.epsi.progtv.domaine.programme.Heure;

public class OutilDateTest {

	@Test
	public void peutParserDate() {
		String dateATransformer = "20131205010500 +0100";
		Date date = OutilDate.parseDate(dateATransformer);
		
		assertThat(date).isNotNull();
		assertThat(date.toString()).isEqualTo("05-12-2013");
	}
	
	@Test
	public void peutParserHeure() {
		String heureATransformer = "20131205125000 +0100";
		Heure heure = OutilDate.parseHeure(heureATransformer);
		
		assertThat(heure).isNotNull();
		assertThat(heure.toString()).isEqualTo("12:50");
	}
	
	@Test
	public void peutComparerDateAnterieureADateDaujourdhui() {
		Date dateAnterieure = new Date();
		int compare = OutilDate.compareADateDAujourdhui(dateAnterieure);
		
		assertThat(compare).isEqualTo(Constantes.ANTERIEUR);
	}
	
	@Test
	public void peutComparerDatePosterieureADateDaujourdhui() {
		Date datePosterieure = new Date(1, 1, 3535);
		int compare = OutilDate.compareADateDAujourdhui(datePosterieure);
		
		assertThat(compare).isEqualTo(Constantes.POSTERIEUR);
	}
	
	@Test
	public void peutComparerDateIdentiqueADateDaujourdhui() {
		int compare = OutilDate.compareADateDAujourdhui(OutilDate.dateAujourdhui());
		
		assertThat(compare).isEqualTo(Constantes.AUJOURDHUI);
	}
	
	@Test
	public void peutComparerAnterieurAAujourdhui() {
		Heure heureAnterieure = new Heure();
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAnterieure);
		
		assertThat(compare).isEqualTo(Constantes.ANTERIEUR);
	}
	
	@Test
	public void peutComparerPosterieurAAujourdhui() {
		Heure heurePosterieure = new Heure(23, 59);
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heurePosterieure);
		
		assertThat(compare).isEqualTo(Constantes.POSTERIEUR);
	}
	
	@Test
	public void peutComparerIdentiqueAAujourdhui() {
		int compare = OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		assertThat(compare).isEqualTo(Constantes.AUJOURDHUI);
	}
	
	@Test
	public void peutVerifierQuAujourdhuiEstEntreUneDate() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), heureFin);
		
		assertThat(estEntre).isTrue();
	}
	
	@Test
	public void peutVerifierQuAujourdhuiNestPasEntreUneDate() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureDebut = new Heure(heureAujourdhui.getHeure()-1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), heureDebut, OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui());
		
		assertThat(estEntre).isFalse();
	}
	
	@Test
	public void peutVerifierQuAujourdhuiEstEntreAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		Heure heureFin = new Heure(heureAujourdhui.getHeure()+1, heureAujourdhui.getMinute());
		boolean estEntre = OutilDate.aujourdhuiEstEntre(OutilDate.dateAujourdhui(), OutilDate.heureAujourdhui(), OutilDate.dateAujourdhui(), heureFin);
		
		assertThat(estEntre).isTrue();
	}
	
	@Test
	public void peutRecupererLaDateDAujourdhui() {
		Date dateAujourdhui = OutilDate.dateAujourdhui();
		
		assertThat(dateAujourdhui).isNotNull();
		assertThat(OutilDate.compareADateDAujourdhui(dateAujourdhui)).isEqualTo(Constantes.AUJOURDHUI);
	}
	
	@Test
	public void peutRecupererLHeureDAujourdhui() {
		Heure heureAujourdhui = OutilDate.heureAujourdhui();
		
		assertThat(heureAujourdhui).isNotNull();
		assertThat(OutilDate.compareAAujourdhui(OutilDate.dateAujourdhui(), heureAujourdhui)).isEqualTo(Constantes.AUJOURDHUI);
	}
	
	@Test
	public void peutObtenirLaDuree() {
		Heure heureDebut = new Heure(12, 0);
		Heure heureFin = new Heure(16, 16);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertThat(duree).isNotNull();
		assertThat(duree).isEqualTo(256);
	}
	
	@Test
	public void peutObtenirLaDureeQuandHeureDebutSuperieureAHeureFin() {
		Heure heureDebut = new Heure(23, 10);
		Heure heureFin = new Heure(0, 45);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertThat(duree).isNotNull();
		assertThat(duree).isEqualTo(95);
	}
	
	@Test
	public void peutObtenirLaDureeQuandHeureIdentiqueMaisPasMinutes() {
		Heure heureDebut = new Heure(18, 0);
		Heure heureFin = new Heure(18, 59);
		Integer duree = OutilDate.obtenirLaDuree(heureDebut, heureFin);
		
		assertThat(duree).isNotNull();
		assertThat(duree).isEqualTo(59);
	}
	
}
