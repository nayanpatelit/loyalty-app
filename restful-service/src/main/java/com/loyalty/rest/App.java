package com.loyalty.rest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.loyalty.rest.domain.PostResponses;
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
    	userPostList=crudService.queryUserPost(StringUtils.isEmpty(userName)?null:new UserPost(userName,null,null,null));
	   PostResponses searchCriteria=null;
       for(UserPost userPost:userPostList) {
    	   searchCriteria=new PostResponses();
    	   searchCriteria.setUserSubmissionId(userPost.getUserSubmissionId());
    	   userPost.setPostResponses(crudService.queryPostResponses(searchCriteria));
    	   searchCriteria=null;
       }
    } catch (ParseException e) {
		log.error("Parse exception "+e.getStackTrace());
	}
    String userPostJsonStr=null;
		try {
			userPostJsonStr=mapper.writeValueAsString(userPostList);
			
		} catch (IOException e) {
			log.error("IOException"+e.getStackTrace());
		}
		userPostList=null;
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
@GET
@Path("/{userSubmissionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response  geResponse(@PathParam("userSubmissionId") String userSubmissionId)  {
   ObjectMapper mapper = new ObjectMapper();
   List<PostResponses> userPostList=null;
   CRUDServices crudService=new CRUDServices();
   try {
	   PostResponses resposes=new PostResponses();
	   resposes.setUserSubmissionId(new ObjectId(userSubmissionId));
	   
   	userPostList=crudService.queryPostResponses(resposes);
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

    
}
