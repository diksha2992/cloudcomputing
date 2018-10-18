package com.csye6225.fall2018.courseservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
	private String courseName;
	private String programCode;
	private String courseID;
	private List<String> students;
	private String professorID;
	private String taStudentID;
	private Board board;
	private Map<String, Lecture> lectureMap;

	public Course(String courseName, String courseID, String programCode) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.programCode = programCode;
		this.students = new ArrayList<>();
		this.board = new Board(courseID);
		this.lectureMap = new HashMap<>();
	}

	public void addLecture(Lecture lecture) {
		lectureMap.put(lecture.getLectureId(), lecture);
	}

	public void removeLecture(String lectureID) {
		lectureMap.remove(lectureID);
	}

	public List<Lecture> getAllLectures() {
		return new ArrayList<>(lectureMap.values());
	}

	public Lecture getLecture(String lectureID) {
		return lectureMap.get(lectureID);
	}

	public String getTaStudentID() {
		return taStudentID;
	}

	public void setTaStudentID(String taStudentID) {
		this.taStudentID = taStudentID;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getProfessorID() {
		return professorID;
	}

	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public List<String> getStudents() {
		return students;
	}

	public void addStudent(String student) {
		this.students.add(student);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", programCode=" + programCode + ", courseID=" + courseID
				+ ", students=" + students + ", professorID=" + professorID + ", taStudentID=" + taStudentID
				+ ", board=" + board + ", lectureMap=" + lectureMap + "]";
	}

}
