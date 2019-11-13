package com.loyalty.rest.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.loyalty.rest.constants.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DomainMapper {
	private static final Logger log=Logger.getLogger(DomainMapper.class);
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/*
	 * Map User Post with MongoDB Object
	 */
	public static  final DBObject toDBObject(UserPost submission) {
	    return new BasicDBObject(Constants.SUBMISSION, submission.getSubmission())
	                     .append(Constants.SUBMISSION_DATE, sdf.format(new Date()))
	                     .append(Constants.USER_NAME, submission.getUserName())
	                     .append(Constants.CITY, submission.getCity());
	                    
	}
	
	
	/*
	 * Map User Post with MongoDB Response Object
	 */
	public static  final DBObject toResponseDBObject(PostResponses response) {
	    return new BasicDBObject(Constants.RESPONSE, response.getResponse())
	                     .append(Constants.RESPONSE_DATE, sdf.format(new Date()))
	                     .append(Constants.RESPONDER, response.getResponder())
	                     .append(Constants.USER_SUBMISSION_ID, response.getUserSubmissionId());
	                     
	                    
	}
	/*
	 * Map DBCursor value from MongoDB to UserPost List
	 */
	
	@SuppressWarnings("unchecked")
	public  static final List<UserPost> toDomainObject(DBCursor cursor) throws ParseException {
		
		List<UserPost> userPostList=new ArrayList<UserPost>();
		
		UserPost userPost=null;
		DBObject dbObject=null;
		
		while(cursor.hasNext()) {
			dbObject=cursor.next();
			userPost=new UserPost();
			userPost.setSubmission((String)dbObject.get(Constants.SUBMISSION));
			userPost.setSubmissionDate((String)dbObject.get(Constants.SUBMISSION_DATE));
			userPost.setUserName((String)dbObject.get(Constants.USER_NAME));
			userPost.setUserSubmissionId((ObjectId)dbObject.get(Constants.MONGODB_UNIQUE_ID));
			userPost.setCity((String)dbObject.get(Constants.CITY));
			userPostList.add(userPost);
			
			userPost=null;
			dbObject=null;
		}
		cursor.close();
		return userPostList;
	}

	
	@SuppressWarnings("unchecked")
	public  static final List<PostResponses> toResponseDomainObject(DBCursor cursor) throws ParseException {
		
		List<PostResponses> postResponsesList=new ArrayList<PostResponses>();
		
		PostResponses postResponses=null;
		DBObject dbObject=null;
		
		while(cursor.hasNext()) {
			dbObject=cursor.next();
			postResponses=new PostResponses();
			postResponses.setUserSubmissionId((ObjectId)dbObject.get(Constants.USER_SUBMISSION_ID));
			postResponses.setResponseId((ObjectId)dbObject.get(Constants.MONGODB_UNIQUE_ID));
			postResponses.setResponder((String)dbObject.get(Constants.RESPONDER));
			postResponses.setResponse((String)dbObject.get(Constants.RESPONSE));
			postResponses.setResponseDate((String)dbObject.get(Constants.RESPONSE_DATE));
			
			postResponsesList.add(postResponses);
			
			postResponses=null;
			dbObject=null;
		}
		cursor.close();
		return postResponsesList;
	}

}
