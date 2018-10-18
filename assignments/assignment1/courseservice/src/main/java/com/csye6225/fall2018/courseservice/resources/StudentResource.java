package com.csye6225.fall2018.courseservice.resources;

import java.util.Random;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6225.fall2018.courseservice.Student;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/students")
public class StudentResource {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addStudent(@FormParam("name") String name, @FormParam("image") String image,
			@FormParam("programCode") String programCode) {

		if (InMemoryDataStore.doesProgramExist(programCode)) {

			String studentID = String.valueOf(new Random().nextInt());
			Student student = new Student(name, studentID, image, programCode);

			InMemoryDataStore.addStudent(student);
			System.out.println("Inserted " + studentID);
			return student.getStudentID();
		} else {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateStudent(@FormParam("name") String name, @FormParam("image") String image,
			@FormParam("programCode") String programCode, @FormParam("studentID") String studentID) {

		if (InMemoryDataStore.getStudent(studentID) == null || InMemoryDataStore.getProgram(programCode) == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Student student = InMemoryDataStore.getStudent(studentID);

		student.setName(name);
		student.setImage(image);
		student.setProgramCode(programCode);

		System.out.println("Updated " + studentID);
		return student.getStudentID();
	}

	@GET
	@Path("/{studentID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getStudent(@PathParam("studentID") String studentID) {

		System.out.println("Received request: " + studentID);
		Student student = InMemoryDataStore.getStudent(studentID);
		return student.toString();
	}
}
