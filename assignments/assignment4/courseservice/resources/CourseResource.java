package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice.datamodel.entity.Course;
import com.csye6225.fall2018.courseservice.resources.requestentity.AddCourseRequest;
import com.csye6225.fall2018.courseservice.resources.requestentity.CourseEnrollmentRequest;
import com.csye6225.fall2018.courseservice.service.CourseService;

@Path("/course")
public class CourseResource {

	private CourseService courseService;

	public CourseResource() {
		courseService = new CourseService();
	}

	// Add Course
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course addCourse(AddCourseRequest addCourseRequest) {

		if (addCourseRequest == null || addCourseRequest.getCourseId() == null
				|| addCourseRequest.getDepartment() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + addCourseRequest);
		}
		String courseId = addCourseRequest.getCourseId();
		String department = addCourseRequest.getDepartment();
		Course course = new Course(courseId, department);
		courseService.addCourse(course);
		return course;
	}

	// Get Course
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {

		if (courseId == null) {
			throw new IllegalArgumentException("CourseId not passed in the path.");
		}

		return courseService.getCourseByCourseId(courseId);
	}

	// Enroll Student into Course
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/student")
	public void enrollStudentToCourse(CourseEnrollmentRequest courseEnrollmentRequest) {

		if (courseEnrollmentRequest == null || courseEnrollmentRequest.getCourseId() == null
				|| courseEnrollmentRequest.getStudentId() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + courseEnrollmentRequest);
		}
		String courseId = courseEnrollmentRequest.getCourseId();
		String studentId = courseEnrollmentRequest.getStudentId();
		courseService.addStudentToCourse(studentId, courseId);
	}

	// Assign Course to Professor
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/professor")
	public void assignCourseToProfessor(CourseEnrollmentRequest courseEnrollmentRequest) throws Exception {

		if (courseEnrollmentRequest == null || courseEnrollmentRequest.getCourseId() == null
				|| courseEnrollmentRequest.getProfessorId() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + courseEnrollmentRequest);
		}
		String courseId = courseEnrollmentRequest.getCourseId();
		String professorId = courseEnrollmentRequest.getProfessorId();
		courseService.assignProfessorToCourse(professorId, courseId);
	}

	// Assign TA to Course
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ta")
	public void assignTAtoCourse(CourseEnrollmentRequest courseEnrollmentRequest) {

		if (courseEnrollmentRequest == null || courseEnrollmentRequest.getCourseId() == null
				|| courseEnrollmentRequest.getTaId() == null) {
			throw new IllegalArgumentException("Invalid input passed: " + courseEnrollmentRequest);
		}
		String courseId = courseEnrollmentRequest.getCourseId();
		String taId = courseEnrollmentRequest.getTaId();
		courseService.assignTAToCourse(taId, courseId);
	}

	// Delete TA to Course
	@DELETE
	@Path("/{courseId}/ta/{studentId}")
	public void deleteTAtoCourse(@PathParam("studentId") String studentId, @PathParam("courseId") String courseId) {
		if (courseId == null || studentId == null) {
			throw new IllegalArgumentException("Input variables not passed correctly.");
		}
		courseService.deleteTAForCourse(studentId, courseId);
	}
}
