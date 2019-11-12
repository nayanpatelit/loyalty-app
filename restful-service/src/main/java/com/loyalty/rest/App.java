package com.loyalty.rest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.loyalty.rest.domain.UserPost;
import com.loyalty.rest.services.CRUDServices;



@Path("/usersubmissions")
public class App {
	
	private static final Logger log=Logger.getLogger(App.class);
	
		
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  getMsg(@QueryParam("userName") String userName)  {
    ObjectMapper mapper = new ObjectMapper();
    List<UserPost> userPostList=null;
    CRUDServices crudService=new CRUDServices();
    try {
    	userPostList=crudService.queryUserPost(StringUtils.isEmpty(userName)?null:new UserPost(userName,null,null));
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
@Consumes(MediaType.APPLICATION_JSON)
public Response  submitMsg( String jsonUserPost) throws JsonGenerationException, JsonMappingException, IOException {
	
	log.debug("Read Json from Post request"+jsonUserPost);
	CRUDServices crudService=new CRUDServices();
	Gson gson=new Gson();

	UserPost userPost=gson.fromJson(jsonUserPost, UserPost.class);
	
	crudService.insert(userPost);
	
	return Response.ok().build();

}

    
}
