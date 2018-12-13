package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.entity.Student;
import com.csye6225.fall2018.courseservice.resources.requestentity.CourseEnrollmentRequest;
import com.csye6225.fall2018.courseservice.resources.requestentity.StudentRequest;
import com.csye6225.fall2018.courseservice.service.CourseService;
import com.csye6225.fall2018.courseservice.service.StudentService;

@Path("/student")
public class StudentResource {

	private StudentService studentService;
	private CourseService courseService;

	public StudentResource() {
		this.studentService = new StudentService();
		this.courseService = new CourseService();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(StudentRequest studentRequest) {

		if (studentRequest == null || studentRequest.getFirstName() == null || studentRequest.getLastName() == null
				|| studentRequest.getDepartment() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + studentRequest);
		}
		String firstName = studentRequest.getFirstName();
		String lastName = studentRequest.getLastName();
		String department = studentRequest.getDepartment();
		String emailId = studentRequest.getEmailId();
		Student student = new Student(firstName, lastName, department, emailId);
		studentService.addStudent(student);

		return student;
	}

	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") String studentId, StudentRequest studentRequest)
			throws Exception {

		if (studentRequest == null) {
			throw new IllegalArgumentException("Invalid input passed: " + studentRequest);
		}
		String firstName = studentRequest.getFirstName();
		String lastName = studentRequest.getLastName();
		String department = studentRequest.getDepartment();
		String emailId = studentRequest.getEmailId();
		Student student = studentService.updateStudent(firstName, lastName, department, emailId, studentId);
		return student;
	}

	@GET
	@Path("/{studentId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Student getStudent(@PathParam("studentId") String studentId) {

		Student student = studentService.getStudentByStudentId(studentId);
		return student;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{studentId}/register")
	public void enrollStudentToCourse(@PathParam("studentId") String studentId,
			CourseEnrollmentRequest courseEnrollmentRequest) {

		if (courseEnrollmentRequest == null || courseEnrollmentRequest.getCourseId() == null || studentId == null) {
			throw new IllegalArgumentException("Invalid input passed: " + courseEnrollmentRequest);
		}
		String courseId = courseEnrollmentRequest.getCourseId();
		courseService.addStudentToCourse(studentId, courseId);
	}
}
