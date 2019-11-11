package com.loyalty.rest.services;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.loyalty.rest.domain.DomainMapper;
import com.loyalty.rest.domain.UserPost;
import com.loyalty.rest.mongodb.MongoDBClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CRUDServices  {
	
	private static final Logger log=Logger.getLogger(CRUDServices.class);
	/*
	 * Insert method
	 */
	public void insert(UserPost userSubmission) throws UnknownHostException {
		log.debug("Start-----CRUD Service.insert");
		MongoDBClient.getMongoDBCollection().insert(DomainMapper.toDBObject(userSubmission));
		log.debug("End-----CRUD Service.insert");
	}
	/*
	 * Query Method
	 */
	public  List<UserPost> queryUserPost(UserPost searchCriteria) throws  ParseException{
		log.debug("Start-----CRUD Service.queryUserPost");
		DBObject query=null;
		DBCursor cursor=null;
		
		if(searchCriteria!=null)		
		    query=new BasicDBObject("_id", searchCriteria.getSubmissionId());
		
		if(query!=null)
			cursor=MongoDBClient.getMongoDBCollection().find(query);
		else
			cursor=MongoDBClient.getMongoDBCollection().find();
		
		log.debug("Start-----CRUD Service.queryUserPost");
	
	  return DomainMapper.toDomainObject(cursor);
	}
		/*
		 * Remove method
		 */
	public void remove(DBObject dbObj) throws UnknownHostException {
		MongoDBClient.getMongoDBCollection().remove(dbObj);
	}
	
	
	/*
	 * Just for testing
	 */
	public static void main(String args[]) throws UnknownHostException, ParseException {
		CRUDServices mongoDB=new CRUDServices();
		//mongoDB.remove(new BasicDBObject());
		UserPost userPost=new UserPost();
		//userPost.setSubmissionId(3l);
		userPost.setSubmission("ThirdSubmission");
		userPost.setSubmissionDate(new Date().toString());
		mongoDB.insert(userPost);
		
		userPost=new UserPost();
		//userPost.setSubmissionId(4l);
		userPost.setSubmission("fifth Submission");
		userPost.setSubmissionDate(new Date().toString());
		mongoDB.insert(userPost);
		
		UserPost searchCriteria=new UserPost();
		searchCriteria.setSubmissionId(3l);
		List<UserPost> myList=mongoDB.queryUserPost(null);
		
		for(UserPost usrObj: myList) {
			System.out.println(usrObj.toString());
		}
		
		
	}
}

