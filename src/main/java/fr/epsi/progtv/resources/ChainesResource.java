package fr.epsi.progtv.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fr.epsi.progtv.modeles.Chaine;
import fr.epsi.progtv.outils.Constantes;
import fr.epsi.progtv.services.ServiceChaines;

@Path("/chaines")
public class ChainesResource {
	
	private final ServiceChaines service = ServiceChaines.getInstance();
	
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
	
}
