package com.loyalty.rest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.loyalty.rest.domain.UserPost;
import com.loyalty.rest.services.CRUDServices;



@Path("/usersubmissions")
public class App {
	
	private static final Logger log=Logger.getLogger(App.class);
	
		
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getMsg()  {
    ObjectMapper mapper = new ObjectMapper();
    List<UserPost> userPostList=null;
    CRUDServices crudService=new CRUDServices();
    try {
    	userPostList=crudService.queryUserPost(null);
	} catch (ParseException e) {
		log.error("Parse exception "+e.getStackTrace());
	}
    String userPostJsonStr=null;
		try {
			userPostJsonStr=mapper.writeValueAsString(userPostList);
			
		} catch (IOException e) {
			log.error("IOException"+e.getStackTrace());
		}
		return Response.ok().entity(userPostJsonStr).build();
 
	}

@POST
@Path("/{submission}")
@Consumes(MediaType.TEXT_PLAIN)
public Response  submitMsg(@PathParam("submission") String msg) throws JsonGenerationException, JsonMappingException, IOException {
	CRUDServices crudService=new CRUDServices();
	crudService.insert(new UserPost(msg,new Date().toString()));
	return Response.ok().build();

}

    
}
