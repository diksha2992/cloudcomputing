package com.csye6225.fall2018.courseservice.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.csye6225.fall2018.courseservice.Course;
import com.csye6225.fall2018.courseservice.Lecture;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/course/lectures")
public class LecturesResource {

//Add Notes to Lectures	
	@POST
	@Path("/notes")
	public void addNotesToLectures(@FormParam("courseID") String courseID, @FormParam("lectureID") String lectureID,
			@FormParam("notes") String notes) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Lecture lecture = course.getLecture(lectureID);

		if (lecture == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		lecture.addNotes(notes);
	}

	// Add Material to Lectures
	@POST
	@Path("/materials")
	public void addMaterialToLectures(@FormParam("courseID") String courseID, @FormParam("lectureID") String lectureID,
			@FormParam("material") String material) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Lecture lecture = course.getLecture(lectureID);

		if (lecture == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		lecture.addMaterials(material);
	}
}
