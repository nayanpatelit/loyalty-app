package com.loyalty.rest;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.loyalty.rest.domain.UserPost;

/**
 * Hello world!
 *
 */
@Path("/usersubmissions")
public class App {

@GET
	@Path("/{submission}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getMsg(@PathParam("submission") String msg) throws JsonGenerationException, JsonMappingException, IOException {
 System.out.println("****My data"+msg);
    ObjectMapper mapper = new ObjectMapper();
    UserPost userPost=new UserPost();
    userPost.setSubmission(msg);
		return Response.ok().entity(mapper.writeValueAsString(userPost))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
 
	}

    
}
