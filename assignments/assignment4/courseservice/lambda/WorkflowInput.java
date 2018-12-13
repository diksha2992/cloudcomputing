package com.csye6225.fall2018.courseservice.lambda;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkflowInput {

	@JsonProperty
	private String courseId;

	@JsonProperty
	private boolean isNewCourse;

	public WorkflowInput() {
		super();
	}

	public WorkflowInput(String courseId, boolean isNewCourse) {
		super();
		this.courseId = courseId;
		this.isNewCourse = isNewCourse;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public boolean getIsNewCourse() {
		return isNewCourse;
	}

	public void setIsNewCourse(boolean isNewCourse) {
		this.isNewCourse = isNewCourse;
	}

	@Override
	public String toString() {
		return "WorkflowInput [courseId=" + courseId + ", isNewCourse=" + isNewCourse + "]";
	}
}
