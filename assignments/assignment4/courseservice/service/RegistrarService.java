package com.csye6225.fall2018.courseservice.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Course;
import com.csye6225.fall2018.courseservice.datamodel.entity.Registrar;

public class RegistrarService {
	private DynamoDBMapper mapper;
	private CourseService courseService;

	public RegistrarService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
		courseService = new CourseService();
	}

	public Registrar addRegistrarForCourse(String courseId) {

		Course course = courseService.getCourseByCourseId(courseId);

		Registrar registrar = new Registrar("Course", courseId, course.getDepartment(), 10);
		System.out.println("Adding registrar to database: " + registrar);
		mapper.save(registrar);

		return registrar;
	}
}
