package fr.epsi.progtv.domaine.programme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.epsi.progtv.domaine.chaine.Chaine;

public class ProgrammeTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Programme programme = new Programme();
		
		assertNotNull(programme.getId());
	}
	
	@Test
	public void peutDonnerUnNom() {
		Programme programme = new Programme();
		programme.setNom("test");
		
		assertEquals("test", programme.getNom());
	}
	
	@Test
	public void peutDonnerUneDescription() {
		Programme programme = new Programme();
		programme.setDescription("test");
		
		assertEquals("test", programme.getDescription());
	}
	
	@Test
	public void peutDonnerUneDateDeDebut() {
		Programme programme = new Programme();
		programme.setDateDebut(new Date());
		
		assertNotNull(programme.getDateDebut());
		assertEquals(new Date(), programme.getDateDebut());
	}
	
	@Test
	public void peutDonnerUneDateDeFin() {
		Programme programme = new Programme();
		programme.setDateFin(new Date());
		
		assertNotNull(programme.getDateFin());
		assertEquals(new Date(), programme.getDateFin());
	}
	
	@Test
	public void peutDonnerUneHeureDeDebut() {
		Programme programme = new Programme();
		programme.setHeureDebut(new Heure());
		
		assertNotNull(programme.getHeureDebut());
		assertEquals(new Heure(), programme.getHeureDebut());
	}
	
	@Test
	public void peutDonnerUneHeureDeFin() {
		Programme programme = new Programme();
		programme.setHeureFin(new Heure());
		
		assertNotNull(programme.getHeureFin());
		assertEquals(new Heure(), programme.getHeureFin());
	}
	
	@Test
	public void peutDonnerUneChaine() {
		Programme programme = new Programme();
		programme.setChaine(new Chaine("test"));
		
		assertNotNull(programme.getChaine());
		assertEquals("test", programme.getChaine().getNom());
	}
	
	@Test
	public void peutDonnerUnCSA() {
		Programme programme = new Programme();
		programme.setCsa("test");
		
		assertEquals("test", programme.getCsa());
	}
	
	@Test
	public void peutDonnerUneCategorie() {
		Programme programme = new Programme();
		programme.setCategorie("test");
		
		assertEquals("Test", programme.getCategorie());
	}
	
	@Test
	public void peutDonnerDesActeurs() {
		Programme programme = new Programme();
		List<Personne> acteurs = new ArrayList<>();
		acteurs.add(new Acteur("test"));
		programme.setActeurs(acteurs);
		
		assertNotNull(programme.getActeurs());
		assertEquals(1, programme.getActeurs().size());
		assertEquals("test", programme.getActeurs().get(0).getNomComplet());
	}
	
	@Test
	public void peutDonnerUnRealisateur() {
		Programme programme = new Programme();
		programme.setRealisateur(new Realisateur("test"));
		
		assertNotNull(programme.getRealisateur());
		assertEquals("test", programme.getRealisateur().getNomComplet());
	}
	
	@Test
	public void peutDonnerUneImage() {
		Programme programme = new Programme();
		programme.setImage("test");
		
		assertEquals("test", programme.getImage());
	}
	
	@Test
	public void peutDonnerUneDateDeRealisation() {
		Programme programme = new Programme();
		programme.setDateRealisation("test");
		
		assertEquals("test", programme.getDateRealisation());
	}
	
	@Test
	public void peutDonnerUnDeuxiemeNom() {
		Programme programme = new Programme();
		programme.setDeuxiemeNom("test");
		
		assertEquals("test", programme.getDeuxiemeNom());
	}
	
	@Test
	public void peutDonnerUneDuree() {
		Programme programme = new Programme();
		programme.setHeureFin(new Heure(1, 25));
		programme.setDuree();
		
		assertEquals(Integer.valueOf(85), programme.getDuree());
	}
	
	@Test
	public void peutResetLIdentifiant() {
		new Programme();
		Programme programme = new Programme();
		
		Programme.resetId();
		Programme premierProgramme = new Programme();
		
		assertNotNull(premierProgramme.getId());
		assertEquals(Integer.valueOf(1), premierProgramme.getId());
		assertNotEquals(programme.getId(), premierProgramme.getId());
	}
	
	@Test
	public void peutAfficherUnProgramme() {
		Programme programme = new Programme("test");
		
		assertEquals("test", programme.toString());
	}
	
	@Test
	public void peutComparerAUnProgrammePrecedent() {
		Programme programme = new Programme("19", new Date(), new Date(), new Heure(12, 0), new Heure(13, 0));
		Programme programmePrecedente = new Programme("1", new Date(), new Date(), new Heure(11, 0), new Heure(12, 0));
		
		assertEquals(1, programme.compareTo(programmePrecedente));
	}
	
	@Test
	public void peutComparerAUnProgrammeSuivant() {
		Programme programme = new Programme("1", new Date(), new Date(), new Heure(11, 0), new Heure(12, 0));
		Programme programmeSuivante = new Programme("19", new Date(), new Date(), new Heure(12, 0), new Heure(13, 0));
		
		assertEquals(-1, programme.compareTo(programmeSuivante));
	}
	
	@Test
	public void peutComparerAUnProgrammeIdentique() {
		Programme programme = new Programme("9", new Date(), new Date(), new Heure(), new Heure());
		Programme programmeIdentique = new Programme("9", new Date(), new Date(), new Heure(), new Heure());
		
		assertEquals(0, programme.compareTo(programmeIdentique));
	}
	
}
