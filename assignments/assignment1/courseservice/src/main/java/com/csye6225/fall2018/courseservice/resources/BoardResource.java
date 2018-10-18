package com.csye6225.fall2018.courseservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.ws.rs.DELETE;
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

import com.csye6225.fall2018.courseservice.Announcement;
import com.csye6225.fall2018.courseservice.Board;
import com.csye6225.fall2018.courseservice.Course;
import com.csye6225.fall2018.courseservice.datamodel.InMemoryDataStore;

@Path("/course/board")
public class BoardResource {
//Post Announcements to Board
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postAnnouncements(@FormParam("courseID") String courseID, @FormParam("context") String context) {
		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Board board = course.getBoard();

		String announcementId = String.valueOf(new Random().nextInt());
		Announcement announcement = new Announcement(announcementId, context);
		board.addAnnouncement(announcement);
		return announcementId;
	}

	// Get Announcements from Board
	@GET
	@Path("/{courseID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnnouncements(@PathParam("courseID") String courseID) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Board board = course.getBoard();

		List<Announcement> announcements = board.getAnnouncements();

		List<String> announcementMessages = new ArrayList<>();
		for (Announcement announcement : announcements) {
			announcementMessages.add(announcement.getContext());
		}

		return Arrays.toString(announcementMessages.toArray());
	}

//Delete Announcements on Board
	@DELETE
	@Path("/{courseID}/{announcementID}")
	public void deleteAnnouncements(@PathParam("courseID") String courseID,
			@PathParam("announcementID") String announcementID) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null || announcementID == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Board board = course.getBoard();

		Announcement announcement = board.getAnnouncement(announcementID);
		if (announcement == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		board.removeAnnouncement(announcementID);
	}
//Update Announcements on Board

	@PUT
	public void updateAnnouncements(@FormParam("courseID") String courseID,
			@FormParam("announcementID") String announcementID, @FormParam("context") String context) {

		Course course = InMemoryDataStore.getCourse(courseID);

		if (course == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Board board = course.getBoard();
		Announcement announcement = board.getAnnouncement(announcementID);

		if (announcement == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		announcement.setContext(context);

	}
}
