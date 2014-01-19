package fr.epsi.progtv.web.ressources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.epsi.progtv.domaine.chaine.Chaine;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.services.ServiceChaines;

@Path("/chaines")
public class ChainesRessource {
	
	@GET
	@Produces(Constantes.ENTETE_HTTP)
	public List<Chaine> liste() {
		return service.tout();
	}
	
	@GET
	@Path("{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Chaine detailsDe(@PathParam("id") Integer idChaine) {
		return service.detailsDe(idChaine);
	}
	
	@GET
	@Path("/precedente/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Chaine detailsDeLaChainePrecedente(@PathParam("id") Integer idChaine) {
		return service.detailsDeLaChainePrecedente(idChaine);
	}
	
	@GET
	@Path("/suivante/{id}")
	@Produces(Constantes.ENTETE_HTTP)
	public Chaine detailsDeLaChaineSuivante(@PathParam("id") Integer idChaine) {
		return service.detailsDeLaChaineSuivante(idChaine);
	}
	
	private ServiceChaines service = ServiceChaines.getInstance();
	
}
