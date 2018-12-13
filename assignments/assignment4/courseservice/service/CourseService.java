package com.csye6225.fall2018.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.fall2018.courseservice.datamodel.entity.Board;
import com.csye6225.fall2018.courseservice.datamodel.entity.Course;
import com.csye6225.fall2018.courseservice.datamodel.entity.Professor;
import com.csye6225.fall2018.courseservice.datamodel.entity.Student;

public class CourseService {
	private DynamoDBMapper mapper;
	private StudentService studentService;
	private ProfessorService professorService;
	private SnsAccessor snsAccessor;

	public CourseService() {
		AmazonDynamoDB dynamoDb = DynamoDbConnector.getClient();
		mapper = new DynamoDBMapper(dynamoDb);
		studentService = new StudentService();
		professorService = new ProfessorService();
		snsAccessor = new SnsAccessor();
	}

	public String addCourse(Course course) {
		System.out.println("Adding course to database: " + course);

		mapper.save(course);
		return course.getId();
	}

	public String addBoardForCourse(String courseId) {
		String boardId = UUID.randomUUID().toString();

		Course course = getCourseByCourseId(courseId);

		String id = course.getId();
		Board board = new Board(boardId, id);
		System.out.println("Adding board to database: " + board);
		mapper.save(board);

		System.out.println("Adding course to database: " + course);

		course.setBoardId(boardId);
		mapper.save(course);

		return id;
	}

	public void addSnsTopicForCourse(String courseId, String notificationArn) {

		Course course = getCourseByCourseId(courseId);

		course.setNotificationTopic(notificationArn);

		mapper.save(course);
	}

	public Course getCourseById(String id) {
		Course course = mapper.load(Course.class, id);
		return course;
	}

	public Course getCourseByCourseId(String courseId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("CourseId-index").withConsistentRead(false).withKeyConditionExpression("CourseId = :v1")
				.withExpressionAttributeValues(eav);

		List<Course> iList = mapper.query(Course.class, queryExpression);
		Course course = iList.size() > 0 ? iList.get(0) : null;
		return course;
	}

	public void addStudentToCourse(String studentId, String courseId) {

		Course course = getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}
		Student student = studentService.getStudentByStudentId(studentId);
		if (student == null) {
			throw new IllegalArgumentException("Student with id: " + studentId + " doesn't exists.");
		}

		List<String> currentCourses = student.getRegisteredCourses();
		if (currentCourses.size() >= 3) {
			throw new IllegalArgumentException("Student with id: " + studentId + " is already registered to "
					+ currentCourses.size() + " courses.");
		}

		course.getRoster().add(studentId);
		mapper.save(course);

		student.getRegisteredCourses().add(courseId);
		mapper.save(student);

		String notificationTopic = course.getNotificationTopic();
		String emailId = student.getEmailId();

		snsAccessor.subscribe(notificationTopic, emailId);
	}

	public void assignProfessorToCourse(String professorId, String courseId) {

		Professor professor = professorService.getProfessorByProfessorId(professorId);
		if (professor == null) {
			throw new IllegalArgumentException("Professor with id: " + courseId + " doesn't exists.");
		}

		Course course = getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}

		course.setProfessorId(professorId);
		mapper.save(course);
	}

	public void assignTAToCourse(String taId, String courseId) {

		Course course = getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}
		Student student = studentService.getStudentByStudentId(taId);
		if (student == null) {
			throw new IllegalArgumentException("Student with id: " + taId + " doesn't exists.");
		}

		course.setTaId(taId);
		mapper.save(course);
	}

	public void deleteTAForCourse(String taId, String courseId) {
		Course course = getCourseByCourseId(courseId);
		if (course == null) {
			throw new IllegalArgumentException("Course with id: " + courseId + " doesn't exists.");
		}
		Student student = studentService.getStudentByStudentId(taId);
		if (student == null) {
			throw new IllegalArgumentException("Student with id: " + taId + " doesn't exists.");
		}

		String currentTaId = course.getTaId();

		if (!taId.equals(currentTaId)) {
			throw new IllegalArgumentException("Course : " + courseId + " has different TA: " + currentTaId);
		}

		course.setTaId(null);
		mapper.save(course);
	}

}
