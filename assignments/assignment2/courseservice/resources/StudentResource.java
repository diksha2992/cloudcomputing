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
import com.csye6225.fall2018.courseservice.resources.requestentity.StudentRequest;
import com.csye6225.fall2018.courseservice.service.StudentService;

@Path("/students")
public class StudentResource {

	private StudentService studentService;

	public StudentResource() {
		this.studentService = new StudentService();
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
		Student student = new Student(firstName, lastName, department);
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
		Student student = studentService.updateStudent(firstName, lastName, department, studentId);
		return student;
	}

	@GET
	@Path("/{studentId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Student getStudent(@PathParam("studentId") String studentId) {

		Student student = studentService.getStudentByStudentId(studentId);
		return student;
	}
}
