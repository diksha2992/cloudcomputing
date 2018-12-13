package com.csye6225.fall2018.courseservice.datamodel.entity;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Student")
public class Student {

	private String id;
	private String studentId;
	private String firstName;
	private String lastName;
	private String department;
	private String emailId;
	private Long joiningDate;
	private List<String> registeredCourses;

	public Student() {
	}

	public Student(String firstName, String lastName, String department, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.emailId = emailId;
		this.registeredCourses = new ArrayList<>();
	}

	@DynamoDBHashKey(attributeName = "Id")
	@DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBIndexHashKey(attributeName = "StudentId", globalSecondaryIndexName = "StudentId-index")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@DynamoDBAttribute(attributeName = "FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBAttribute(attributeName = "LastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DynamoDBAttribute(attributeName = "Department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName = "EmailId")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@DynamoDBAttribute(attributeName = "JoiningDate")
	public Long getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Long joiningDate) {
		this.joiningDate = joiningDate;
	}

	@DynamoDBAttribute(attributeName = "RegisteredCourses")
	public List<String> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<String> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	@DynamoDBIgnore
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", emailId=" + emailId + ", joiningDate=" + joiningDate
				+ ", registeredCourses=" + registeredCourses + "]";
	}
}
