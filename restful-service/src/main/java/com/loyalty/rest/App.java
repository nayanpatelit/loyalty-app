package com.loyalty.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Hello world!
 *
 */
@Path("/usersubmissions")
public class App {

@GET
	@Path("/{submission}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getMsg(@PathParam("submission") String msg) {
 
		return Response.ok().entity(msg).build();
 
	}

    
}
