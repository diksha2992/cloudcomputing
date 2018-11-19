package com.csye6225.fall2018.courseservice.resources.requestentity;

public class ProfessorRequest {

	private String firstName;
	private String lastName;
	private String department;

	public ProfessorRequest() {
		super();
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

	@Override
	public String toString() {
		return "ProfessorRequest [firstName=" + firstName + ", lastName=" + lastName + ", department=" + department
				+ "]";
	}

}
