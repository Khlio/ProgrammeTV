package fr.epsi.progtv.services;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epsi.progtv.domaine.Entrepots;
import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.outils.ParserXML;

public class ServiceChainesTest {

	@BeforeClass
	public static void setUp() throws JDOMException, IOException {
		ParserXML.execute(ServiceChainesTest.class.getClassLoader().getResourceAsStream("tnt_lite.xml"));
		service = ServiceChaines.getInstance();
		assertThat(service).isNotNull();
	}
	
	@AfterClass
	public static void tearDown() {
		Entrepots.nettoie();
	}
	
	@Test
	public void peutRecupererToutesLesChaines() {
		List<Chaine> lesChaines = service.tout();
		
		assertThat(lesChaines).isNotNull();
		assertThat(lesChaines).hasSize(19);
	}
	
	@Test
	public void peutRecupererUneChaine() {
		Chaine chaine = service.detailsDe(1);
		
		assertThat(chaine).isNotNull();
		assertThat(chaine.getId()).isEqualTo(1);
		assertThat(chaine.getNom()).isEqualTo("TF1");
		assertThat(chaine.getProgrammes()).hasSize(83);
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChainePrecedente() {
		Chaine chainePrecedente = service.detailsDeLaChainePrecedente(10);
		
		assertThat(chainePrecedente).isNotNull();
		assertThat(chainePrecedente).isEqualTo(service.detailsDe(9));
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChainePrecedenteLaPremiere() {
		Chaine chainePrecedente = service.detailsDeLaChainePrecedente(1);
		
		assertThat(chainePrecedente).isNotNull();
		assertThat(chainePrecedente).isEqualTo(service.detailsDe(19));
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChaineSuivante() {
		Chaine chaineSuivante = service.detailsDeLaChaineSuivante(10);
		
		assertThat(chaineSuivante).isNotNull();
		assertThat(chaineSuivante).isEqualTo(service.detailsDe(11));
	}
	
	@Test
	public void peutRecupererLesDetailsDeLaChaineSuivanteLaDerniere() {
		Chaine chaineSuivante = service.detailsDeLaChaineSuivante(19);
		
		assertThat(chaineSuivante).isNotNull();
		assertThat(chaineSuivante).isEqualTo(service.detailsDe(1));
	}
	
	private static ServiceChaines service;
	
}
