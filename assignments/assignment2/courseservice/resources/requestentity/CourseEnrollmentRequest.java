package com.csye6225.fall2018.courseservice.resources.requestentity;

public class CourseEnrollmentRequest {

	private String studentId;
	private String taId;
	private String professorId;
	private String courseId;

	public CourseEnrollmentRequest() {
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTaId() {
		return taId;
	}

	public void setTaId(String taId) {
		this.taId = taId;
	}

	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CourseEnrollmentRequest [studentId=" + studentId + ", taId=" + taId + ", professorId=" + professorId
				+ ", courseId=" + courseId + "]";
	}

}
