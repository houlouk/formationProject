package fr.m2i.jerseygen;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.acceis.jpa.jpa.Dao;
import fr.acceis.jpa.jpa.MatiereDao;
import model.Matiere;


            

@Path("/matieres")
public class ServiceMatiere {

	private Dao<Matiere, Long> daoMatiere = new MatiereDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMatieres() {

		List<Matiere> matieres = daoMatiere.getAll();

		return Response.ok().entity(matieres).build();

	}
	
	
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatiere(@PathParam("id") Long id) {

        Matiere matiere= daoMatiere.get(id);

        return Response.ok().entity(matiere).build();

    }

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMatiere(Matiere matiere) {

	

		daoMatiere.add(matiere);

		return Response.ok().build();

	}

	@PUT
	@Path("{id}/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMatiere(@PathParam("id") Long id,Matiere matiere) {

				
		daoMatiere.update(id,matiere);

		return Response.ok().build();

	}

	@DELETE
	@Path("{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMatiere(@PathParam("id") Long id) {


		daoMatiere.delete(id);

		return Response.ok().build();

	}

}
