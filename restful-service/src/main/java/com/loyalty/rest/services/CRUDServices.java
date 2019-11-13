package com.loyalty.rest.services;

import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

import com.loyalty.rest.constants.Constants;
import com.loyalty.rest.domain.DomainMapper;
import com.loyalty.rest.domain.PostResponses;
import com.loyalty.rest.domain.UserPost;
import com.loyalty.rest.mongodb.MongoDBClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CRUDServices {

	private static final Logger log = Logger.getLogger(CRUDServices.class);

	/*
	 * Insert method
	 */
	public void insert(UserPost userSubmission) throws UnknownHostException {
		log.debug("Start-----CRUD Service.insert");
		MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_SUBMISSIONS)
				.insert(DomainMapper.toDBObject(userSubmission));
		log.debug("End-----CRUD Service.insert");
	}

	/*
	 * Insert responses
	 */
	public void insertResponses(PostResponses postResponses) throws UnknownHostException {
		log.debug("Start-----CRUD Service.insertResponses");
		MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_RESPONSES)
				.insert(DomainMapper.toResponseDBObject(postResponses));
		log.debug("End-----CRUD Service.insertResponses");
	}

	/*
	 * Query Method
	 */
	public List<UserPost> queryUserPost(UserPost searchCriteria) throws ParseException {
		log.debug("Start-----CRUD Service.queryUserPost");
		BasicDBObject query = null;
		DBCursor cursor = null;

		if (searchCriteria != null) {
			query = new BasicDBObject();
			if (!StringUtils.isEmpty(searchCriteria.getUserName()))
				query.append(Constants.USER_NAME, searchCriteria.getUserName());
			if (!StringUtils.isEmpty(searchCriteria.getSubmission()))
				query.append(Constants.SUBMISSION, searchCriteria.getSubmission());
		}

		if (query != null) {
			cursor = MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_SUBMISSIONS).find(query);
		} else {
			cursor = MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_SUBMISSIONS).find();
		}
		log.debug("Start-----CRUD Service.queryUserPost");
        
		return DomainMapper.toDomainObject(cursor);
	}

	/*
	 * Query Method
	 */
	public List<PostResponses> queryPostResponses(PostResponses searchCriteria) throws ParseException {
		log.debug("Start-----CRUD Service.queryUserPost");
		BasicDBObject query = null;
		DBCursor cursor = null;

		if (searchCriteria != null) {
			query = new BasicDBObject();
			if (!StringUtils.isEmpty(searchCriteria.getUserSubmissionId()))
				query.append(Constants.USER_SUBMISSION_ID, searchCriteria.getUserSubmissionId());
			if (!StringUtils.isEmpty(searchCriteria.getResponder()))
				query.append(Constants.RESPONDER, searchCriteria.getResponder());
		}

		if (query != null) {
			cursor = MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_RESPONSES).find(query);
		} else {
			cursor = MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_RESPONSES).find();
		}
		log.debug("Start-----CRUD Service.queryUserPost");

		return DomainMapper.toResponseDomainObject(cursor);
	}

	/*
	 * Remove method
	 */
	public void remove(DBObject dbObj) throws UnknownHostException {
		MongoDBClient.getMongoDBCollection(Constants.MONGODB_DB_SUBMISSIONS).remove(dbObj);
	}

	/*
	 * Just for testing
	 */
	public static void main(String args[]) throws UnknownHostException, ParseException {
		CRUDServices mongoDB = new CRUDServices();
		mongoDB.remove(new BasicDBObject());
		UserPost userPost = new UserPost();
		// userPost.setSubmissionId(3l);
		userPost.setSubmission("Cooking");
		userPost.setSubmissionDate(new Date().toString());
		userPost.setUserName("sweta");
		userPost.setCity("MAnsa");
		// String[] array = {"a", "b", "c", "d", "e"};
		// userPost.setPostResponses(Arrays.asList(array));
		mongoDB.insert(userPost);

		userPost = new UserPost();
		// userPost.setSubmissionId(4l);
		userPost.setSubmission("studying-graduating");
		userPost.setSubmissionDate(new Date().toString());
		userPost.setUserName("Aryan");
		userPost.setCity("Ajax");
		// userPost.setPostResponses(Arrays.asList(array));
		mongoDB.insert(userPost);

		UserPost searchCriteria = new UserPost();
		// searchCriteria.setSubmissionId(3l);
		searchCriteria.setUserName("Reiny");
		searchCriteria.setSubmission("playing");
		List<UserPost> myList = mongoDB.queryUserPost(null);

		// Add Responses
		PostResponses postResponses = new PostResponses();
		postResponses.setUserSubmissionId(new ObjectId("5dcabaedaecccfc79192893b"));
		postResponses.setResponder("napatel");
		postResponses.setResponse("hello My subpost");
		postResponses.setResponseDate(new Date().toString());

		mongoDB.insertResponses(postResponses);

		// Add Responses
		postResponses = new PostResponses();
		postResponses.setUserSubmissionId(new ObjectId("5dcabaedaecccfc79192893b"));
		postResponses.setResponder("rushilpatel");
		postResponses.setResponse("York University");
		postResponses.setResponseDate(new Date().toString());

		mongoDB.insertResponses(postResponses);

		/*
		 * Search posts
		 */
		PostResponses searchResponses = new PostResponses();
		searchResponses.setUserSubmissionId(new ObjectId("5dcabaedaecccfc79192893b"));
		List<PostResponses> responseList = mongoDB.queryPostResponses(searchResponses);

		for (PostResponses usrObj : responseList) {
			System.out.println(usrObj.toString());
			
			  		 
		}
		for (UserPost usrObj : myList) {
			System.out.println(usrObj.toString());
			
		}

	}
}
