package com.loyalty.rest.mongodb;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.loyalty.rest.constants.Constants;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public abstract class MongoDBClient {
	private static final Logger log=Logger.getLogger(MongoDBClient.class);
	private  static MongoClient mongoClient=null;
	private static DB dbObj=null;
	/*
	 * Get MongoDBClient to connect with MOngo DB database 
	 */
	public static MongoClient getMongoDBClient()  {
		
		log.info("Entry-getMongoDBClient()");
		
		if(mongoClient == null) {
			try {
				mongoClient= new MongoClient(new MongoClientURI(Constants.MONGODB_URL));
				
			} catch (UnknownHostException e) {
				log.error("getMongoDBClient()-UnknownHostException"+e.getMessage());
			}catch(Exception e){
				log.error("Exception while connecting with Mongo DB server"+e.getMessage());
			}
		}
		log.info("Exist -getMongoDBClient()"+mongoClient.hashCode());
		
		return mongoClient;
	}
	/*
	 * Get MongoDb Collection
	 */
	public static DBCollection getMongoDBCollection(String collectionName)  {
		if(dbObj==null) {
		   dbObj=MongoDBClient.getMongoDBClient().getDB(Constants.MONGODB_DATABASE);
		}
		log.debug("getMongoDBCollection()"+dbObj.hashCode());
		
		return dbObj.getCollection(collectionName);
	}
	

}
