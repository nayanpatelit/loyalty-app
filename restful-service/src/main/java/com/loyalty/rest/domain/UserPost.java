package com.loyalty.rest.domain;



public class UserPost {
	private Long submissionId;
	private String submission;
	private String submissionDate;
	
		
	public UserPost() {
		}

	public UserPost(String submission, String submissionDate) {
		super();
		this.submission = submission;
		this.submissionDate = submissionDate;
	}

	public String getSubmission() {
		return submission;
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
		return "UserPost [submissionId=" + submissionId + ", submission=" + submission + ", submissionDate="
				+ submissionDate + "]";
	}
	
		
	
	

}
