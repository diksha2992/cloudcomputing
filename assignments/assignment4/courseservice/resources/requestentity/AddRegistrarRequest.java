package com.csye6225.fall2018.courseservice.resources.requestentity;

public class AddRegistrarRequest {

	private String courseId;

	public AddRegistrarRequest() {
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "AddCourseRequest [courseId=" + courseId + "]";
	}

}
