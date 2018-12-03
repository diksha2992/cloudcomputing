package com.csye6225.fall2018.courseservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Student;

public class StudentService {

	DynamoDBMapper mapper;

	public StudentService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
	}

	public Student getStudentByStudentId(String studentId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(studentId));

		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("StudentId-index").withConsistentRead(false)
				.withKeyConditionExpression("StudentId = :v1").withExpressionAttributeValues(eav);

		List<Student> iList = mapper.query(Student.class, queryExpression);
		Student student = iList.size() > 0 ? iList.get(0) : null;
		return student;
	}

	public Student addStudent(Student student) {
		String studentId = UUID.randomUUID().toString();
		Long joiningDate = new Date().getTime();

		student.setStudentId(studentId);
		student.setJoiningDate(joiningDate);

		mapper.save(student);
		return student;
	}

	public Student updateStudent(String firstName, String lastName, String department, String emailId,
			String studentId) {
		Student student = getStudentByStudentId(studentId);
		if (student == null) {
			throw new IllegalArgumentException("Student with id: " + studentId + " doesn't exists.");
		}

		if (firstName != null) {
			student.setFirstName(firstName);
		}
		if (lastName != null) {
			student.setLastName(lastName);
		}
		if (department != null) {
			student.setDepartment(department);
		}
		if (emailId != null) {
			student.setEmailId(emailId);
		}
		mapper.save(student);
		return student;
	}
}
