package fr.epsi.progtv.domaine.programme;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.epsi.progtv.domaine.chaine.Chaine;

public class ProgrammeTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Programme programme = new Programme();
		
		assertThat(programme.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerUnNom() {
		Programme programme = new Programme();
		programme.setNom("test");
		
		assertThat(programme.getNom()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneDescription() {
		Programme programme = new Programme();
		programme.setDescription("test");
		
		assertThat(programme.getDescription()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneDateDeDebut() {
		Programme programme = new Programme();
		
		assertThat(programme.getDateDebut()).isNotNull();
		assertThat(programme.getDateDebut()).isEqualTo(new Date());
	}
	
	@Test
	public void peutDonnerUneDateDeFin() {
		Programme programme = new Programme();
		
		assertThat(programme.getDateFin()).isNotNull();
		assertThat(programme.getDateFin()).isEqualTo(new Date());
	}
	
	@Test
	public void peutDonnerUneHeureDeDebut() {
		Programme programme = new Programme();
		
		assertThat(programme.getHeureDebut()).isNotNull();
		assertThat(programme.getHeureDebut()).isEqualTo(new Heure());
	}
	
	@Test
	public void peutDonnerUneHeureDeFin() {
		Programme programme = new Programme();
		
		assertThat(programme.getHeureFin()).isNotNull();
		assertThat(programme.getHeureFin()).isEqualTo(new Heure());
	}
	
	@Test
	public void peutDonnerUneChaine() {
		Programme programme = new Programme();
		programme.setChaine(new Chaine("test"));
		
		assertThat(programme.getChaine()).isNotNull();
		assertThat(programme.getChaine().getNom()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUnCSA() {
		Programme programme = new Programme();
		programme.setCsa("test");
		
		assertThat(programme.getCsa()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneCategorie() {
		Programme programme = new Programme();
		programme.setCategorie("test");
		
		assertThat(programme.getCategorie()).isEqualTo("Test");
	}
	
	@Test
	public void peutDonnerDesActeurs() {
		Programme programme = new Programme();
		List<Personne> acteurs = new ArrayList<>();
		acteurs.add(new Acteur("test"));
		programme.setActeurs(acteurs);
		
		assertThat(programme.getActeurs()).isNotNull();
		assertThat(programme.getActeurs()).hasSize(1);
		assertThat(programme.getActeurs().get(0).getNomComplet()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUnRealisateur() {
		Programme programme = new Programme();
		programme.setRealisateur(new Realisateur("test"));
		
		assertThat(programme.getRealisateur()).isNotNull();
		assertThat(programme.getRealisateur().getNomComplet()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneImage() {
		Programme programme = new Programme();
		programme.setImage("test");
		
		assertThat(programme.getImage()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneDateDeRealisation() {
		Programme programme = new Programme();
		programme.setDateRealisation("test");
		
		assertThat(programme.getDateRealisation()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUnDeuxiemeNom() {
		Programme programme = new Programme();
		programme.setDeuxiemeNom("test");
		
		assertThat(programme.getDeuxiemeNom()).isEqualTo("test");
	}
	
	@Test
	public void peutDonnerUneDuree() {
		Programme programme = new Programme();
		programme.setHeureFin(new Heure(1, 25));
		programme.setDuree();
		
		assertThat(programme.getDuree()).isEqualTo(85);
	}
	
	@Test
	public void peutResetLIdentifiant() {
		new Programme();
		Programme programme = new Programme();
		
		Programme.resetId();
		Programme premierProgramme = new Programme();
		
		assertThat(premierProgramme.getId()).isNotNull();
		assertThat(premierProgramme.getId()).isEqualTo(1);
		assertThat(premierProgramme.getId()).isNotEqualTo(programme.getId());
	}
	
	@Test
	public void peutAfficherUnProgramme() {
		Programme programme = new Programme("test");
		
		assertThat(programme.toString()).isEqualTo("test");
	}
	
	@Test
	public void peutComparerAUnProgrammePrecedent() {
		Programme programme = new Programme("19", new Date(), new Date(), new Heure(12, 0), new Heure(13, 0));
		Programme programmePrecedente = new Programme("1", new Date(), new Date(), new Heure(11, 0), new Heure(12, 0));
		
		assertThat(programme.compareTo(programmePrecedente)).isEqualTo(1);
	}
	
	@Test
	public void peutComparerAUnProgrammeSuivant() {
		Programme programme = new Programme("1", new Date(), new Date(), new Heure(11, 0), new Heure(12, 0));
		Programme programmeSuivante = new Programme("19", new Date(), new Date(), new Heure(12, 0), new Heure(13, 0));
		
		assertThat(programme.compareTo(programmeSuivante)).isEqualTo(-1);
	}
	
	@Test
	public void peutComparerAUnProgrammeIdentique() {
		Programme programme = new Programme("9", new Date(), new Date(), new Heure(), new Heure());
		Programme programmeIdentique = new Programme("9", new Date(), new Date(), new Heure(), new Heure());
		
		assertThat(programme.compareTo(programmeIdentique)).isEqualTo(0);
	}
	
}
