package com.csye6225.fall2018.courseservice.resources;

import java.util.Random;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.csye6225.fall2018.courseservice.Course;
import com.csye6225.fall2018.courseservice.Lecture;
import com.csye6225.fall2018.courseservice.Professor;
import com.csye6225.fall2018.courseservice.Student;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/course")
public class CourseResource {

	// Add Course
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCourse(@FormParam("courseName") String courseName, @FormParam("programCode") String programCode) {

		String courseID = String.valueOf(new Random().nextInt());
		Course course = new Course(courseName, courseID, programCode);
		InMemoryDataStore.addCourse(course);
		return courseID;
	}

	// Enroll Student into Course
	@POST
	@Path("/student")
	public void enrollStudentToCourse(@FormParam("studentID") String studentID,
			@FormParam("courseID") String courseID) {

		Student student = InMemoryDataStore.getStudent(studentID);
		Course course = InMemoryDataStore.getCourse(courseID);

		if (student == null || course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		student.addCourse(courseID);
		course.addStudent(studentID);
	}

	// Assign Course to Professor
	@POST
	@Path("/professor")
	public void assignCourseToProfessor(@FormParam("professorID") String professorID,
			@FormParam("courseID") String courseID) {

		Professor professor = InMemoryDataStore.getProfessor(professorID);
		Course course = InMemoryDataStore.getCourse(courseID);

		if (professor == null || course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		course.setProfessorID(professorID);
	}

	// Assign TA to Course
	@POST
	@Path("/ta")
	public void assignTAtoCourse(@QueryParam("studentID") String studentID, @QueryParam("courseID") String courseID) {

		Student student = InMemoryDataStore.getStudent(studentID);
		Course course = InMemoryDataStore.getCourse(courseID);

		if (student == null || course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		course.setTaStudentID(studentID);
	}

	// Delete TA to Course
	@DELETE
	@Path("/{courseID}/student/{studentID}")
	public void deleteTAtoCourse(@PathParam("studentID") String studentID, @PathParam("courseID") String courseID) {

		Student student = InMemoryDataStore.getStudent(studentID);
		Course course = InMemoryDataStore.getCourse(courseID);

		if (student == null || course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		String currentTA = course.getTaStudentID();
		if (studentID.equals(currentTA)) {
			course.setTaStudentID(null);
		} else {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}

	// Add lecture
	@POST
	@Path("/lecture")
	@Produces(MediaType.TEXT_PLAIN)
	public String addLecture(@FormParam("courseId") String courseID) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		String lectureID = String.valueOf(new Random().nextInt());
		Lecture lecture = new Lecture(lectureID);

		course.addLecture(lecture);
		return lectureID;
	}
}
