package com.csye6225.fall2018.courseservice.resources.requestentity;

public class AddCourseRequest {

	private String courseId;
	private String department;

	public AddCourseRequest() {
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "AddCourseRequest [courseId=" + courseId + ", department=" + department + "]";
	}
}
