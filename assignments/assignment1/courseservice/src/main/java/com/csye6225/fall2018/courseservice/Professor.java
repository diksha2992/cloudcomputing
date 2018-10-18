package com.csye6225.fall2018.courseservice;

public class Professor {

	private String firstName;
	private String lastName;
	private String department;
	private String professorID;

	public Professor(String firstName, String lastName, String department, String professorID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.professorID = professorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfessorID() {
		return professorID;
	}

	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}

	@Override
	public String toString() {
		return "Professor [firstName=" + firstName + ", lastName=" + lastName + ", department=" + department
				+ ", professorID=" + professorID + "]";
	}

}
