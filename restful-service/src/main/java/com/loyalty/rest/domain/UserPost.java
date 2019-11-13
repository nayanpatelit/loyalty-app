package com.loyalty.rest.domain;

import java.util.List;

import org.bson.types.ObjectId;

public class UserPost {
	private String userName;
	private Long submissionId;
	private ObjectId userSubmissionId;
	private String submission;
	private String submissionDate;
	private String city;
	private List<PostResponses> postResponses;
	
	
		
	public UserPost() {
		}

	

	public UserPost(String userName, String submission, String submissionDate,String city) {
		super();
		this.userName = userName;
		this.submission = submission;
		this.submissionDate = submissionDate;
		this.city=city;
	}


	
	
	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public List<PostResponses> getPostResponses() {
		return postResponses;
	}



	public void setPostResponses(List<PostResponses> postResponses) {
		this.postResponses = postResponses;
	}



	public String getSubmission() {
		return submission;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public void setSubmission(String submission) {
		this.submission = submission;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}



	
	public ObjectId getUserSubmissionId() {
		return userSubmissionId;
	}



	public void setUserSubmissionId(ObjectId userSubmissionId) {
		this.userSubmissionId = userSubmissionId;
	}



	@Override
	public String toString() {
		return "UserPost [userName=" + userName + ", userSubmissionId=" + userSubmissionId + ", submission="
				+ submission + ", submissionDate=" + submissionDate + ", city=" + city + ", postResponses="
				+ postResponses + "]";
	}



	
		
	

}
