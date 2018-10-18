package com.csye6225.fall2018.courseservice;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private String studentID;
	private String image;
	private String programCode;
	private List<String> courses;

	public Student(String name, String studentID, String image, String programCode) {
		this.name = name;
		this.studentID = studentID;
		this.image = image;
		this.programCode = programCode;
		courses = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void addCourse(String course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", studentID=" + studentID + ", image=" + image + ", programCode="
				+ programCode + ", courses=" + courses + "]";
	}

}
