package ie.gmit.sw.dsRESTfulServiceProject;



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

public interface Resource { 
	
	/*
	 * An interface to abstract the business logic from the RESTful annotations.
	 * The RESTful resources will implement this interface and process the requests.
	 */

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getIt();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{value}")
	public Response getById(@PathParam("value") String value);

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response create(String input);

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response update(@PathParam("id") final String id, String input);

	@DELETE
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Response delete(@PathParam("id") final String id, String input);

}
