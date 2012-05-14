/**
 *
 */
package camel.workshop.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/service")
@Produces("text/xml")
public interface MyService {

	@GET
	@Path("/resource/{resourceId}")
	public Response getResource(@PathParam("resourceId") String resourceId);
}
