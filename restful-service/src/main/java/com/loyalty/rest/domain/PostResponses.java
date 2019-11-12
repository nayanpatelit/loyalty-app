package com.loyalty.rest.domain;

import org.bson.types.ObjectId;

public class PostResponses {
	private String responder;
	private ObjectId userSubmissionId;
	private ObjectId responseId;
	private String response;
	private String responseDate;
	
	
	public String getResponder() {
		return responder;
	}
	public void setResponder(String responder) {
		this.responder = responder;
	}
	public ObjectId getUserSubmissionId() {
		return userSubmissionId;
	}
	public void setUserSubmissionId(ObjectId userSubmissionId) {
		this.userSubmissionId = userSubmissionId;
	}
	public ObjectId getResponseId() {
		return responseId;
	}
	public void setResponseId(ObjectId responseId) {
		this.responseId = responseId;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}
	@Override
	public String toString() {
		return "PostResponses [responder=" + responder + ", userSubmissionId=" + userSubmissionId + ", responseId="
				+ responseId + ", response=" + response + ", responseDate=" + responseDate + "]";
	}
	
	

}
