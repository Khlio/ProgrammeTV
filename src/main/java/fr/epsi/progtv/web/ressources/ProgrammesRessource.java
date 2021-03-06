package fr.epsi.progtv.web.ressources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.epsi.progtv.domaine.programme.Programme;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.services.ServiceProgrammes;

@Path("/programmes")
public class ProgrammesRessource {

	@GET
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> liste() {
		return service.tout();
	}
	
	@GET
	@Path("{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Programme detailsDe(@PathParam("id") Integer idProgramme) {
		return service.detailsDe(idProgramme);
	}
	
	@GET
	@Path("/soir")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuSoir() {
		return service.programmesDuSoir();
	}
	
	@GET
	@Path("/soir/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuSoirDeLaChaine(@PathParam("id") Integer idChaine) {
		return service.programmesDuSoirDeLaChaine(idChaine);
	}
	
	@GET
	@Path("/moment")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuMoment() {
		return service.programmesDuMoment();
	}
	
	@GET
	@Path("/moment/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuMomentDeLaChaine(@PathParam("id") Integer idChaine) {
		return service.programmesDuMomentDeLaChaine(idChaine);
	}
	
	@GET
	@Path("/chaine/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuneChaine(@PathParam("id") Integer idChaine) {
		return service.programmesDuneChaine(idChaine);
	}
	
	@GET
	@Path("/chaine/{id}/{date}")
	@Produces(Constantes.ENTETE_HTTP)
	public List<Programme> programmesDuneChaine(@PathParam("id") Integer idChaine, @PathParam("date") String date) {
		return service.programmesDuneChaine(idChaine, date);
	}
	
	@GET
	@Path("/precedent/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Programme detailsDuProgrammePrecedent(@PathParam("id") Integer idProgramme) {
		return service.detailsDuProgrammePrecedent(idProgramme);
	}
	
	@GET
	@Path("/suivant/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Programme detailsDuProgrammeSuivant(@PathParam("id") Integer idProgramme) {
		return service.detailsDuProgrammeSuivant(idProgramme);
	}
	
	private ServiceProgrammes service = ServiceProgrammes.getInstance();
	
}
