package com.loyalty.rest.domain;



public class UserPost {
	private String userName;
	private Long submissionId;
	private String submission;
	private String submissionDate;
	
		
	public UserPost() {
		}

	

	public UserPost(String userName, String submission, String submissionDate) {
		super();
		this.userName = userName;
		this.submission = submission;
		this.submissionDate = submissionDate;
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



	@Override
	public String toString() {
		return "UserPost [userName=" + userName + ", submission=" + submission + ", submissionDate=" + submissionDate
				+ "]";
	}
	
	
		
	
	

}
